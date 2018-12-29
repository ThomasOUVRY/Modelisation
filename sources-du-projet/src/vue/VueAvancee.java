package vue;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import controller.controllerVueAvancee.ControllerFactory;
import controller.controllerVueAvancee.ControllerKeyboard;
import controller.controllerVueAvancee.ControllerMouvementSouris;
import controller.controllerVueAvancee.ControllerRotation;
import controller.controllerVueAvancee.ControllerTranslation;
import controller.controllerVueAvancee.ControllerZoom;
import controller.controllerVueAvancee.ControllerZoomScroll;
import javafx.application.Application;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.structure.Modele3D;
import vue.dessin.Dessin;

public class VueAvancee extends Application implements Observer {

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
	private CheckBox checkboxEclairage;

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
	private CheckBox checkboxOmbrage;

	@FXML
	private Button zoomIn;

	@FXML
	private CheckBox checkboxLissage;

	@FXML
	private Button rotationXLeft;

	@FXML
	private Button rotationYRight;

	@FXML
	private CheckBox checkboxSegments;

	@FXML
	private Button translateLeft;

	@FXML
	private MenuItem menuItemOuvrir;

	@FXML
	private GraphicsContext graphicsContext;

	@FXML
	private Button centrerButton;

	@FXML
	private Button updateButton;

	private Modele3D modele;

	private final String VUE = "avance";

	int idxUpdate = 0;

	private double posXMouse;

	private double posYMouse;

	private Set<KeyCode> keyPressed;

	private ControllerFactory controles = ControllerFactory.getInstance;

	public void changerModele() {
		modele = VueSimple.getModele();
		modele.addObserver(this);
		update(modele, modele);
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

	public void initialize() throws Exception {
		graphicsContext = canvas.getGraphicsContext2D();
		keyPressed = new HashSet<>();
		modele = VueSimple.getModele();
		modele.addObserver(this);
		updateButton.setOnMouseClicked(e -> {
			changerModele();
			initZoomButton();
			initTranslateButton();
			initRotationButton();
			initMouseTracking();
			initTranslateMouse();
			initZoomMouse();
			initKeyboard();
		});
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

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getResource("ihmAvance.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Projet M4 : affichage ++");
		stage.getIcons().add(new Image("/vue/iconeProjet.png"));
		stage.show();
	}

	public void update(Observable arg0, Object arg1) {
		System.out.println("update " + idxUpdate++);
		try {
			graphicsContext.clearRect(0, 0, 2000, 2000);
			dessinerModele(false, false, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
