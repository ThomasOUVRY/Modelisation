package controller.controllerVueAvancee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modele.structure.Modele3D;
import vue.VueAvancee;
import vue.VueSimple;
/**
 * Controller du zoom, au bouton
 * @author thoma
 *
 */
public class ControllerZoom extends ControllerAvancee implements EventHandler<ActionEvent> {

	public ControllerZoom(VueAvancee vueAvancee, Modele3D modele) {
		super(vueAvancee, modele);
	}
	
	/**
	 * Gere les actions de l'utilisateur pour effectuer une zoom, i�i cliquer sur un bouton
	 */
	@Override
	public void handle(ActionEvent event) {
		System.out.println("zoom");
		if (modele.getFichier() != null) {
			if (event.getSource() == vueAvancee.getZoomIn()) {
				modele.zoom(1.1);
			} else if (event.getSource() == vueAvancee.getZoomOut()) {
				modele.zoom(0.9);
			}
		}
	}

	/**
	 * Cette fonction permet d'ajuster la matrice
	 */
	public void ajustementMatrice() {
		double X = modele.recherchePlusGrandX();
		double Y = modele.recherchePlusGrandY();
		if (X > Y) {
			double a = (vueAvancee.getCanvas().getWidth() / (3 * X));
			modele.zoom(a);
		} else {
			double a = (vueAvancee.getCanvas().getHeight() / (3 * Y));
			modele.zoom(a);
		}
	}

}