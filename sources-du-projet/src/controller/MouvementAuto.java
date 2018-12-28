package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import modele.structure.Modele3D;
import vue.VueSimple;

/**
 * Mouvement auto
 * 
 * @author Groupe N3
 *
 */
public class MouvementAuto extends Controller implements EventHandler<ActionEvent> {

	private Timeline tm;
	private boolean played;

	/**
	 * Controleur pour permettre le mouvement auto de la figure, constructeur
	 * @param model Model, le modèle courant contenant les données
	 */
	public MouvementAuto(VueSimple vueFXML, Modele3D modele) {
		super(vueFXML, modele);
		
		this.tm = new Timeline();
		this.played = false;
		tm.setCycleCount(Integer.MAX_VALUE);

		final double VITESSE_TURN = 70;

		tm.getKeyFrames().add(new KeyFrame(Duration.millis(VITESSE_TURN), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				modele.rotationCentre(0.05, vueFXML.getCanvas().getWidth()/2, vueFXML.getCanvas().getHeight()/2, 'y');
				modele.rotationCentre(0.05, vueFXML.getCanvas().getWidth()/2, vueFXML.getCanvas().getHeight()/2, 'z');
				modele.rotationCentre(0.05, vueFXML.getCanvas().getWidth()/2, vueFXML.getCanvas().getHeight()/2, 'x');
			}
		
		}));
	}

	/**
	 * Activer ou desactiver le mouvement
	 * 
	 * @return boolean qui determine si le mouvement a été activé ou non.
	 */
	public boolean action() {
		if (!this.played) {
			tm.setCycleCount(Integer.MAX_VALUE);
			tm.play();
		} else {
			tm.stop();
		}
		this.played = !this.played;
		return this.played;
	}

	@Override
	public void handle(ActionEvent event) {

		this.action();

	}

}
