package controller.controllerVueAvancee;

import java.util.Arrays;

import javax.security.auth.x500.X500PrivateCredential;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.structure.Matrix;
import modele.structure.Modele3D;
import vue.VueAvancee;
import vue.VueSimple;

/**
 * Controller des boutons de rotation
 * 
 * @author thoma
 *
 */
public class ControllerRotation extends ControllerAvancee implements EventHandler<ActionEvent> {

	/**
	 * Constructeur
	 * 
	 * @param vueAvancee
	 * @param modele
	 */
	public ControllerRotation(VueAvancee vueAvancee, Modele3D modele) {
		super(vueAvancee, modele);
	}

	/**
	 * Gere les actions de l'utilisateur pour effectuer une rotation, i�i cliquer
	 * sur un bouton
	 */
	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		if (modele.getFichier() != null) {
			double xCentre = vueAvancee.getCanvas().getWidth() / 2;
			double yCentre =vueAvancee.getCanvas().getHeight() / 2;
			if (source == vueAvancee.getRotationXLeft()) {
				modele.rotationCentre(0.1, xCentre, yCentre, 'x');
			} else if (source == vueAvancee.getRotationXRight()) {
				modele.rotationCentre(-0.1, xCentre, yCentre, 'x');
			} else if (source == vueAvancee.getRotationYLeft()) {
				modele.rotationCentre(0.1, xCentre, yCentre, 'y');
			} else if (source == vueAvancee.getRotationYRight()) {
				modele.rotationCentre(-0.1, xCentre, yCentre, 'y');
			} else if (source == vueAvancee.getRotationZLeft()) {
				modele.rotationCentre(0.1, xCentre, yCentre, 'z');
			} else if (source == vueAvancee.getRotationZRight()) {
				modele.rotationCentre(-0.1, xCentre, yCentre, 'z');
			}
		}
	}

}
