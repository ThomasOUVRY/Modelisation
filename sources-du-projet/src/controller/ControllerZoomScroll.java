package controller;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import modele.structure.Modele3D;
import vue.VueSimple;

/**
 * Controller du zoom avec le scroll de la souris
 * 
 * @author thoma
 *
 */
public class ControllerZoomScroll extends Controller implements EventHandler<ScrollEvent> {

	public ControllerZoomScroll(VueSimple vueFXML, Modele3D modele) {
		super(vueFXML, modele);
	}

	/**
	 * Gere les actions de l'utilisateur pour effectuer une zoom, i�i scroller avec
	 * la souris
	 */
	@Override
	public void handle(ScrollEvent event) {
		if (modele.getFichier() != null) {
			double deltaY = event.getDeltaY();
			if (deltaY > 0) {
				modele.zoomCentre(1.2, event.getX(), event.getY());
			} else {
				modele.zoomCentre(0.8, event.getX(), event.getY());
			}
		}

	}

}
