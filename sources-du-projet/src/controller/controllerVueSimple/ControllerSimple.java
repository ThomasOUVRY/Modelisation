package controller.controllerVueSimple;

import modele.structure.Modele3D;
import vue.VueSimple;

/*
 * Classe de controller principale
 *
 * @author poteaua
 */
public class ControllerSimple {

    protected VueSimple vueFXML;
    protected Modele3D modele;

    /**
     * Constructeur de la classe
     *
     * @param vueFXML
     * @param modele
     */
    public ControllerSimple(VueSimple vueFXML, Modele3D modele) {
        this.vueFXML = vueFXML;
        this.modele = modele;
    }
}