package controller.controllerVueAvancee;

import modele.structure.Modele3D;
import vue.VueAvancee;
import vue.VueSimple;

/*
 * Classe de controller principale
 *
 * @author poteaua
 */
public class ControllerAvancee {

    protected VueAvancee vueAvancee;
    protected Modele3D modele;

    /**
     * Constructeur de la classe
     *
     * @param vueFXML
     * @param modele
     */
    public ControllerAvancee(VueAvancee vueAvancee, Modele3D modele) {
        this.vueAvancee = vueAvancee;
        this.modele = modele;
    }
}