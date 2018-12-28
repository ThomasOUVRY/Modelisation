package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import modele.structure.Modele3D;
import vue.VueSimple;

/**
 * Controller souris
 * 
 * @author thoma
 *
 */
public class ControllerMouvementSouris extends Controller implements EventHandler<MouseEvent> {

	private double anciennePosXMouse;
	private double anciennePosYMouse;

	/**
	 * Constructeur
	 * 
	 * @param vueFXML
	 * @param modele
	 */
	public ControllerMouvementSouris(VueSimple vueFXML, Modele3D modele) {
		super(vueFXML, modele);
		anciennePosXMouse = vueFXML.getPosXMouse();
		anciennePosYMouse = vueFXML.getPosYMouse();
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
				if (!vueFXML.getKeyPressed().contains(KeyCode.CONTROL)) {
					modele.translation(deplacementX * 1.5, deplacementY * 1.5, 0);
				} else {
					modele.rotationCentre(deplacementX/20, vueFXML.getCanvas().getWidth() / 2, vueFXML.getCanvas().getHeight() / 2,
							'y');
					modele.rotationCentre(deplacementY/20, vueFXML.getCanvas().getWidth() / 2, vueFXML.getCanvas().getHeight() / 2,
							'x');
				}
			}
			setAnciennePosMouse(e.getX(), e.getY());
		}

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
