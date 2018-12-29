package controller.controllerVueSimple;

import java.util.Arrays;

import javax.security.auth.x500.X500PrivateCredential;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.structure.Matrix;
import modele.structure.Modele3D;
import vue.VueSimple;

/**
 * Controller des boutons de rotation
 * 
 * @author thoma
 *
 */
public class ControllerRotation extends ControllerSimple implements EventHandler<ActionEvent> {

	/**
	 * Constructeur
	 * 
	 * @param vueFXML
	 * @param modele
	 */
	public ControllerRotation(VueSimple vueFXML, Modele3D modele) {
		super(vueFXML, modele);
	}

	/**
	 * Gere les actions de l'utilisateur pour effectuer une rotation, i�i cliquer
	 * sur un bouton
	 */
	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		if (modele.getFichier() != null) {
			double xCentre = vueFXML.getCanvas().getWidth() / 2;
			double yCentre = vueFXML.getCanvas().getHeight() / 2;
			if (source == vueFXML.getRotationXLeft()) {
				modele.rotationCentre(0.1, xCentre, yCentre, 'x');
			} else if (source == vueFXML.getRotationXRight()) {
				modele.rotationCentre(-0.1, xCentre, yCentre, 'x');
			} else if (source == vueFXML.getRotationYLeft()) {
				modele.rotationCentre(0.1, xCentre, yCentre, 'y');
			} else if (source == vueFXML.getRotationYRight()) {
				modele.rotationCentre(-0.1, xCentre, yCentre, 'y');
			} else if (source == vueFXML.getRotationZLeft()) {
				modele.rotationCentre(0.1, xCentre, yCentre, 'z');
			} else if (source == vueFXML.getRotationZRight()) {
				modele.rotationCentre(-0.1, xCentre, yCentre, 'z');
			}
		}
	}

}
