package controller.controllerVueAvancee;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modele.structure.Modele3D;
import vue.VueAvancee;
import vue.VueSimple;

/**
 * Controller de la rotation � la souris
 * 
 * @author thoma
 *
 */
public class ControllerRotationSouris extends ControllerAvancee implements EventHandler<MouseEvent> {

	private double anciennePosXMouse = -1;
	private double anciennePosYMouse = -1;

	/**
	 * Constructeur
	 * 
	 * @param vueAvancee
	 * @param modele
	 */
	public ControllerRotationSouris(VueAvancee vueAvancee, Modele3D modele) {
		super(vueAvancee, modele);
	}

	/**
	 * Gere les actions de l'utilisateur pour effectuer une rotation, i�i la
	 * rotation � la souris
	 */
	@Override
	public void handle(MouseEvent event) {
		if (modele.getFichier() != null) {
			if (anciennePosXMouse == -1 && anciennePosYMouse == -1) {
				anciennePosXMouse = vueAvancee.getPosXMouse();
				anciennePosYMouse = vueAvancee.getPosYMouse();
			}
			double deplacementX = event.getX() - anciennePosXMouse;
			double deplacementY = event.getY() - anciennePosYMouse;
			modele.rotationCentre(deplacementX, vueAvancee.getCanvas().getWidth() / 2, vueAvancee.getCanvas().getHeight() / 2,
					'y');
			modele.rotationCentre(deplacementY, vueAvancee.getCanvas().getWidth() / 2, vueAvancee.getCanvas().getHeight() / 2,
					'x');
			setAnciennePosMouse(event.getX(), event.getY());
		}
	}

	public void setAnciennePosMouse(double posX, double posY) {
		anciennePosXMouse = posX;
		anciennePosYMouse = posY;
	}

}
