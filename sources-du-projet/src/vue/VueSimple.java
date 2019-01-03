package vue;

import java.io.File;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import controller.controllerVueSimple.ControllerFactory;
import controller.controllerVueSimple.ControllerFichier;
import controller.controllerVueSimple.ControllerKeyboard;
import controller.controllerVueSimple.ControllerMouvementSouris;
import controller.controllerVueSimple.ControllerRotation;
import controller.controllerVueSimple.ControllerTranslation;
import controller.controllerVueSimple.ControllerZoom;
import controller.controllerVueSimple.ControllerZoomScroll;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.lecteurFichier.Lecteur;
import modele.structure.Modele3D;
import vue.dessin.Dessin;

/**
 * Classe qui permet d'afficher l'application. C'est la vue du MVC
 */
public final class VueSimple extends Application implements Observer {

	// Le modele utlisé et à afficher sur le canvas
	private static Modele3D modele;

	public static Modele3D getModele() {
		return modele;
	}

	/**
	 * Lanceur de l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@FXML
	private AnchorPane anchorPaneOption;

	@FXML
	private Button translateUp;

	@FXML
	private MenuItem menuItemQuitter;

	@FXML
	private Button rotationYLeft;

	@FXML
	private Button zoomOut;

	@FXML
	private Button rotationZRight;

	@FXML
	private CheckBox checkboxPoints;

	@FXML
	private VBox vboxTranslation;

	@FXML
	private MenuBar menuBar;

	@FXML
	private Button rotationZLeft;

	@FXML
	private MenuItem menuAide;

	@FXML
	private Button translateRight;

	@FXML
	private CheckBox checkboxFaces;

	@FXML
	private HBox hboxRotation;

	@FXML
	private HBox hboxZoom;

	@FXML
	private Button translateDown;

	@FXML
	private Button rotationXRight;

	@FXML
	private Canvas canvas;

	@FXML
	private Button zoomIn;

	@FXML
	private Button rotationXLeft;

	@FXML
	private Button rotationYRight;

	@FXML
	private CheckBox checkboxSegments;

	@FXML
	private Button translateLeft;

	@FXML
	private HBox hboxRotationAuto;

	@FXML
	private MenuItem menuItemOuvrir;
	@FXML
	private Button lancerAvance;
	private GraphicsContext graphicsContext;

	// Servent d'indicateurs des checkboxs de la section "Affichage"
	private boolean affichagePoints;

	private boolean affichageSegments;

	private boolean affichageFaces;

	private ControllerFactory controles = ControllerFactory.getInstance;

	// Le fichier associé au modèle
	private File fichier;
	private Stage stage;

	// Position de la souris
	private double posXMouse;

	private double posYMouse;

	// Ensemble des touches préssées (réactif) utilisé pour les raccourcis claviers
	private Set<KeyCode> keyPressed;

	private final String VUE = "simple";

	private VueAvancee vueAvancee;
	
	public void ajustementTailleMatrice() {
		if (fichier != null) {
			((ControllerZoom) controles.fabrique("zoom", this, modele)).ajustementMatrice();
		}
	}

	/**
	 * Permet de centrer et avoir un zoom correct sur l'objet lors de sa crï¿½ation
	 **/
	public void cadrage() {
		if (fichier != null) {
			double width = canvas.getWidth();
			double height = canvas.getHeight();
			double[][] matrice = modele.getMatricePoint();
			double xMin = matrice[0][0];
			double xMax = matrice[0][0];
			double yMin = matrice[1][0];
			double yMax = matrice[1][0];
			for (int i = 1; i < matrice[0].length; i++) {
				if (xMin > matrice[0][i])
					xMin = matrice[0][i];
				if (xMax < matrice[0][i])
					xMax = matrice[0][i];
				if (yMin > matrice[1][i])
					yMin = matrice[1][i];
				if (yMax < matrice[1][i])
					yMax = matrice[1][i];
			}
			double ecartX = xMax - xMin;
			double ecartY = yMax - yMin;
			double coeff = Math.min(width / ecartX, height / ecartY);
			modele.zoom(coeff * 0.65);
			matrice = modele.getMatricePoint();
			for (int i = 1; i < matrice[0].length; i++) {
				if (xMin > matrice[0][i])
					xMin = matrice[0][i];
				if (xMax < matrice[0][i])
					xMax = matrice[0][i];
				if (yMin > matrice[1][i])
					yMin = matrice[1][i];
				if (yMax < matrice[1][i])
					yMax = matrice[1][i];
			}
			ecartX = xMax - xMin;
			ecartY = yMax - yMin;
			double transX = -xMin + ((canvas.getWidth() - ecartX) / 2);
			double transY = -yMin + ((canvas.getHeight() - ecartY) / 2);
			modele.translation(transX, transY, 0);
		}
	}

	public void dessinerModele(boolean affichagePoints, boolean affichageSegments, boolean affichageFaces)
			throws Exception {
		if (affichageFaces && affichageSegments) {
			Dessin.dessinerSegmentsFaces(canvas, modele, "graphicscontext", VUE);
		} else if (affichageFaces) {
			Dessin.dessinerFaces(canvas, modele, "graphicscontext", VUE);
		} else if (affichageSegments) {
			Dessin.dessinerLignes(canvas, modele, "graphicscontext");
		}
		if (affichagePoints) {
			Dessin.dessinerPoints(canvas, modele, "graphicscontext");
		}

	}

	public Canvas getCanvas() {
		return canvas;
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public Set<KeyCode> getKeyPressed() {
		return keyPressed;
	}

	public double getPosXMouse() {
		return posXMouse;
	}

	public double getPosYMouse() {
		return posYMouse;
	}

	public Button getRotationXLeft() {
		return rotationXLeft;
	}

	public Button getRotationXRight() {
		return rotationXRight;
	}

	public Button getRotationYLeft() {
		return rotationYLeft;
	}

	public Button getRotationYRight() {
		return rotationYRight;
	}

	public Button getRotationZLeft() {
		return rotationZLeft;
	}

	public Button getRotationZRight() {
		return rotationZRight;
	}

	public Button getTranslateDown() {
		return translateDown;
	}

	public Button getTranslateLeft() {
		return translateLeft;
	}

	public Button getTranslateRight() {
		return translateRight;
	}

	public Button getTranslateUp() {
		return translateUp;
	}

	public Button getZoomIn() {
		return zoomIn;
	}

	public Button getZoomOut() {
		return zoomOut;
	}

	private void initAdvancedButton() {
		lancerAvance.setOnMouseClicked(e -> {
			try {
				vueAvancee = new VueAvancee();
				vueAvancee.start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	private void initDisplayOption() {
		checkboxFaces.setSelected(true);
		checkboxSegments.setSelected(true);
		checkboxPoints.setOnAction(e -> {
			affichagePoints = checkboxPoints.isSelected();
			canvas.requestFocus();
			update();
		});
		checkboxSegments.setOnAction(e -> {
			affichageSegments = checkboxSegments.isSelected();
			canvas.requestFocus();
			update();
		});
		checkboxFaces.setOnAction(e -> {
			affichageFaces = checkboxFaces.isSelected();
			canvas.requestFocus();
			update();
		});
	}

	/**
	 * 
	 * Initialise l'interface et la vue
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
		graphicsContext = canvas.getGraphicsContext2D();
		resetAffichage();

		keyPressed = new HashSet<>();

		if (fichier == null) {
			modele = new Modele3D(0, 0);
		} else {
			Lecteur lec = new Lecteur(fichier);
			modele = lec.creerModele3D();
			resetAffichage();
			cadrage();
		}
		fichier = modele.getFichier();
		modele.addObserver(this);

		// Initialisation des controllers
		initMenu();
		initZoomButton();
		initTranslateButton();
		initRotationButton();
		initMouseTracking();
		initTranslateMouse();
		initZoomMouse();
		initKeyboard();
		initDisplayOption();
		initAdvancedButton();
	}

	private void initKeyboard() {
		canvas.setOnKeyPressed((ControllerKeyboard) controles.fabrique("clavier", this, modele));
		canvas.getParent().getParent().setOnKeyPressed(e -> {
			keyPressed.add(e.getCode());
		});
		canvas.getParent().getParent().setOnKeyReleased(e -> {
			keyPressed.remove(e.getCode());
		});
	}

	private void initMenu() {
		menuItemOuvrir.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				FileChooser fichierChoisi = new FileChooser();
				fichierChoisi.setTitle("Ouvrir un fichier .ply");
				File file = fichierChoisi.showOpenDialog(stage);
				new ControllerFichier(modele, file);
			}
		});

		menuItemQuitter.setOnAction(e -> {
			Platform.exit();
		});

		menuAide.setOnAction(e -> {
			VBox root = new VBox();
			Scene scene = new Scene(root, 900, 150);
			Stage stageAide = new Stage();
			TextArea ta = new TextArea();
			ta.setEditable(false);
			ta.setText("================================= Menu d'aide ===============================\n \n"
					+ "Les boutons << + >> et << - >> vous permettent de zoomer et de dezoomer la figure  \n "
					+ "=> ou avec la souris : Scroll) \n"
					+ "Les boutons << ← >>, << ↑ >> , << ↓ >> et << → >> vous permettent de translater à gauche, en haut, en bas et à droite\n"
					+ "=> ou avec la souris (maintenir clic droit + mouvement souris)"
					+ "Les boutons << ↰  >>,  << ↱ >> vous permettent de faire des rotations autour des axes X, Y, et Z \n"
					+ "=> ou avec la souris (maintenir clic droit + maintenir CTRL + mouvement souris)"
					+ "Vous avez la possibilité d'activer l'affichage des points, segments et faces. Ces 3 options sont cumulables \n"
					+ "Vous disposez d'un bouton 'mode avancée' vous offrant la possibilité de faire une rotation automatique de l'objet, de centrer l'objet et d'avoir un éclairage \n");
			root.getChildren().add(ta);
			stageAide.setScene(scene);
			stageAide.setTitle("Aide");
			stageAide.setX(500);
			stageAide.setY(200);
			stageAide.initOwner(stage);
			stageAide.initModality(Modality.WINDOW_MODAL);
			stageAide.show();
		});
	}

	private void initMouseTracking() {
		canvas.setOnMouseMoved(e -> {
			posXMouse = e.getX();
			posYMouse = e.getY();
		});
	}

	private void initRotationButton() {
		rotationXLeft.setOnAction((ControllerRotation) controles.fabrique("rotation", this, modele));
		rotationXRight.setOnAction((ControllerRotation) controles.fabrique("rotation", this, modele));
		rotationYLeft.setOnAction((ControllerRotation) controles.fabrique("rotation", this, modele));
		rotationYRight.setOnAction((ControllerRotation) controles.fabrique("rotation", this, modele));
		rotationZLeft.setOnAction((ControllerRotation) controles.fabrique("rotation", this, modele));
		rotationZRight.setOnAction((ControllerRotation) controles.fabrique("rotation", this, modele));
	}

	private void initTranslateButton() {
		translateLeft.setOnAction((ControllerTranslation) controles.fabrique("translation", this, modele));
		translateRight.setOnAction((ControllerTranslation) controles.fabrique("translation", this, modele));
		translateDown.setOnAction((ControllerTranslation) controles.fabrique("translation", this, modele));
		translateUp.setOnAction((ControllerTranslation) controles.fabrique("translation", this, modele));
	}

	private void initTranslateMouse() {
		canvas.setOnMouseDragged((ControllerMouvementSouris) controles.fabrique("mouvement", this, modele));
	}

	private void initZoomButton() {
		zoomIn.setOnAction((ControllerZoom) controles.fabrique("zoom", this, modele));
		zoomOut.setOnAction((ControllerZoom) controles.fabrique("zoom", this, modele));
	}

	private void initZoomMouse() {
		canvas.setOnScroll((ControllerZoomScroll) controles.fabrique("scroll", this, modele));
	}

	/** Remet les paramètres d'affichages par défaut (Segments et faces) **/
	private void resetAffichage() {
		checkboxPoints.setSelected(false);
		affichagePoints = false;
		checkboxSegments.setSelected(true);
		affichageSegments = true;
		checkboxFaces.setSelected(true);
		affichageFaces = true;
	}

	/**
	 * Demarre la scene
	 */
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ihmProjet.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Projet M4 : affichage simple");
		stage.getIcons().add(new Image("/vue/iconeProjet.png"));
		stage.show();
	}

	public void update() {
		graphicsContext.clearRect(0, 0, 2000, 2000);
		try {
			dessinerModele(affichagePoints, affichageSegments, affichageFaces);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if ((File) arg1 != null) {
			try {
				fichier = (File) arg1;
				this.initialize();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		graphicsContext.clearRect(0, 0, 2000, 2000);
		try {
			dessinerModele(affichagePoints, affichageSegments, affichageFaces);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
