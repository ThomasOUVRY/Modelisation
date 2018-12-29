package controller.controllerVueAvancee;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import modele.structure.Modele3D;
import vue.VueAvancee;
import vue.VueSimple;

/**
 * Controller souris
 * 
 * @author thoma
 *
 */
public class ControllerMouvementSouris extends ControllerAvancee implements EventHandler<MouseEvent> {

	private double anciennePosXMouse;
	private double anciennePosYMouse;

	/**
	 * Constructeur
	 * 
	 * @param vueFXML
	 * @param modele
	 */
	public ControllerMouvementSouris(VueAvancee vueAvancee, Modele3D modele) {
		super(vueAvancee, modele);
		anciennePosXMouse =vueAvancee.getPosXMouse();
		anciennePosYMouse = vueAvancee.getPosYMouse();
	}

	/**
	 * Effectue des actions en fonction des mouvements de la souris
	 */
	@Override
	public void handle(MouseEvent e) {
		if (modele.getFichier() != null) {
			double deplacementX = (e.getX() - anciennePosXMouse);
			double deplacementY = (e.getY() - anciennePosYMouse);
			if (deplacementX < 5 && deplacementX > -5 && deplacementY < 5 && deplacementY > -5) {
				if (!vueAvancee.getKeyPressed().contains(KeyCode.CONTROL)) {
					modele.translation(deplacementX * 1.5, deplacementY * 1.5, 0);
				} else {
					modele.rotationCentre(deplacementX/20, vueAvancee.getCanvas().getWidth() / 2, vueAvancee.getCanvas().getHeight() / 2,
							'y');
					modele.rotationCentre(deplacementY/20,vueAvancee.getCanvas().getWidth() / 2, vueAvancee.getCanvas().getHeight() / 2,
							'x');
				}
			}
			setAnciennePosMouse(e.getX(), e.getY());
		}
		vueAvancee.getCanvas().requestFocus();
	}

	/**
	 * Setter sur les anciennes coordonn�es de la souris
	 * 
	 * @param posX
	 * @param posY
	 */
	public void setAnciennePosMouse(double posX, double posY) {
		anciennePosXMouse = posX;
		anciennePosYMouse = posY;
	}
}
