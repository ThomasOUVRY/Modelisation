package controller.controllerVueAvancee;

import controller.controllerVueAvancee.MouvementAuto;
import modele.structure.Modele3D;
import vue.VueAvancee;
import vue.VueSimple;

public class ControllerFactory {
    protected static String controle = "";
    public static ControllerFactory getInstance = new ControllerFactory();

    public ControllerAvancee fabrique(String controle, VueAvancee vue, Modele3D modele) {
        switch (controle) {
            case "clavier":
                return new ControllerKeyboard(vue, modele);
            case "mouvement":
                return new ControllerMouvementSouris(vue, modele);
            case "auto":
                return new MouvementAuto(vue, modele);
            case "rotation":
                return new ControllerRotation(vue, modele);
            case "rotationSouris":
                return new ControllerRotationSouris(vue, modele);
            case "translation":
                return new ControllerTranslation(vue, modele);
            case "zoom":
                return new ControllerZoom(vue, modele);
            case "scroll":
                return new ControllerZoomScroll(vue, modele);
            default:
                return null;
        }
    }
}
