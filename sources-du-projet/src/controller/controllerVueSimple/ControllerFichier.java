package controller.controllerVueSimple;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modele.structure.Modele3D;
/**
 * Permet d'ouvir un nouveau fichier "ply" grace au menu
 * @author thoma
 *
 */
public class ControllerFichier {

    private Modele3D modele;
    private File fichier;

    public ControllerFichier(Modele3D m, File fichier) {
        this.modele = m;
        this.fichier = fichier;
        setNouveauFichier();
    }

    private void setNouveauFichier() {
        String fich = fichier.getAbsolutePath();
        if (fich.substring(fich.length() - 4, fich.length()).equals(".ply")) {
            modele.setFichier(fichier);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur Fichier");
            alert.setContentText("Le fichier que vous souhaitez ouvrir n'est pas un fichier .ply");
            alert.showAndWait();
        }
    }

}
