package controller.controllerVueSimple;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import modele.structure.Modele3D;
import vue.VueSimple;

/**
 * Controller clavier
 * 
 * @author thoma
 *
 */
public class ControllerKeyboard extends ControllerSimple implements EventHandler<KeyEvent> {

	/**
	 * Constructeur de la classe
	 * 
	 * @param vueFXML
	 * @param modele
	 */
	public ControllerKeyboard(VueSimple vueFXML, Modele3D modele) {
		super(vueFXML, modele);
	}

	/**
	 * Effectue des actions en fonction des touches press�es par l'utilisateur
	 */
	@Override
	public void handle(KeyEvent event) {
		KeyCode touche = event.getCode();
		if (modele.getFichier() != null) {
			if (touche == KeyCode.Z)
				modele.translation(0, -10, 0);
			if (touche == KeyCode.Q)
				modele.translation(-20, 0, 0);
			if (touche == KeyCode.S)
				modele.translation(0, 10, 0);
			if (touche == KeyCode.D)
				modele.translation(10, 0, 0);
			if (touche == KeyCode.R)
				modele.zoom(1.2);
			if (touche == KeyCode.F)
				modele.zoom(0.8);
			if (touche == KeyCode.T)
				modele.rotation(15, 'x');
			if (touche == KeyCode.G)
				modele.rotation(-15, 'x');
			if (touche == KeyCode.Y)
				modele.rotation(15, 'y');
			if (touche == KeyCode.H)
				modele.rotation(-15, 'y');
			if (touche == KeyCode.U)
				modele.rotation(15, 'z');
			if (touche == KeyCode.J)
				modele.rotation(-15, 'z');
		}
	}

}
