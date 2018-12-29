package controller.controllerVueAvancee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.structure.Modele3D;
import vue.VueAvancee;
import vue.VueSimple;
/**
 * Controller de la translation avec les boutons
 * @author thoma
 *
 */
public class ControllerTranslation extends ControllerAvancee implements EventHandler<ActionEvent> {

	/**
	 * Constructeur
	 * @param vueAvancee
	 * @param modele
	 */
	public ControllerTranslation(VueAvancee vueAvancee, Modele3D modele) {
		super(vueAvancee, modele);
	}

	/**
	 * Gere les actions de l'utilisateur pour effectuer une translation, i�i cliquer sur un bouton
	 */
	@Override
	public void handle(ActionEvent event) {
		if (modele.getFichier() != null) {
			if (event.getSource() == vueAvancee.getTranslateLeft())
				modele.translation(-10, 0, 0);
			else if (event.getSource() == vueAvancee.getTranslateRight())
				modele.translation(10, 0, 0);
			else if (event.getSource() == vueAvancee.getTranslateDown())
				modele.translation(0, 10, 0);
			else if (event.getSource() == vueAvancee.getTranslateUp())
				modele.translation(0, -10, 0);
		}
		vueAvancee.getCanvas().requestFocus();
	}

}
