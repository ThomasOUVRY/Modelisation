package controller.controllerVueSimple;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modele.structure.Modele3D;
import vue.VueSimple;
/**
 * Controller du zoom, au bouton
 * @author thoma
 *
 */
public class ControllerZoom extends ControllerSimple implements EventHandler<ActionEvent> {

	public ControllerZoom(VueSimple vueFXML, Modele3D modele) {
		super(vueFXML, modele);
	}
	
	/**
	 * Gere les actions de l'utilisateur pour effectuer une zoom, i�i cliquer sur un bouton
	 */
	@Override
	public void handle(ActionEvent event) {
		if (modele.getFichier() != null) {
			if (event.getSource() == vueFXML.getZoomIn()) {
				modele.zoom(1.1);
			} else if (event.getSource() == vueFXML.getZoomOut()) {
				modele.zoom(0.9);
			}
		}
		vueFXML.getCanvas().requestFocus();
	}

	/**
	 * Cette fonction permet d'ajuster la matrice
	 */
	public void ajustementMatrice() {
		double X = modele.recherchePlusGrandX();
		double Y = modele.recherchePlusGrandY();
		if (X > Y) {
			double a = (vueFXML.getCanvas().getWidth() / (3 * X));
			modele.zoom(a);
		} else {
			double a = (vueFXML.getCanvas().getHeight() / (3 * Y));
			modele.zoom(a);
		}
	}

}