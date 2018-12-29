package controller.controllerVueSimple;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.structure.Modele3D;
import vue.VueSimple;
/**
 * Controller de la translation avec les boutons
 * @author thoma
 *
 */
public class ControllerTranslation extends ControllerSimple implements EventHandler<ActionEvent> {

	/**
	 * Constructeur
	 * @param vueFXML
	 * @param modele
	 */
	public ControllerTranslation(VueSimple vueFXML, Modele3D modele) {
		super(vueFXML, modele);
	}

	/**
	 * Gere les actions de l'utilisateur pour effectuer une translation, i�i cliquer sur un bouton
	 */
	@Override
	public void handle(ActionEvent event) {
		if (modele.getFichier() != null) {
			if (event.getSource() == vueFXML.getTranslateLeft())
				modele.translation(-10, 0, 0);
			else if (event.getSource() == vueFXML.getTranslateRight())
				modele.translation(10, 0, 0);
			else if (event.getSource() == vueFXML.getTranslateDown())
				modele.translation(0, 10, 0);
			else if (event.getSource() == vueFXML.getTranslateUp())
				modele.translation(0, -10, 0);
		}
		vueFXML.getCanvas().requestFocus();
	}

}
