package controller;

import modele.structure.Modele3D;
import vue.VueSimple;

/*
 * Classe de controller principale
 *
 * @author poteaua
 */
public class Controller {

    protected VueSimple vueFXML;
    protected Modele3D modele;

    /**
     * Constructeur de la classe
     *
     * @param vueFXML
     * @param modele
     */
    public Controller(VueSimple vueFXML, Modele3D modele) {
        this.vueFXML = vueFXML;
        this.modele = modele;
    }
}