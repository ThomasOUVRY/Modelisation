import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import modele.lecteurFichier.Lecteur;
import modele.lecteurFichier.NombreElementNegatifException;

/**
 * Differents test li�s aux verifications du fichier PLY
 * 
 */

public class ValiditePLY {

	File fichierTest = new File("sources-du-projet/ressources/3carre.ply");
	File fichierTestFaux = new File("sources-du-projet/ressources/3carreFaux.ply");
	Lecteur lecteur = new Lecteur();

	/** La premiere ligne correspond au format du fichier **/
	@Test
	void testFormatEnPremiereLigne() {
		lecteur.setFichierPLY(fichierTest);
		assertEquals("ply", lecteur.getPremiereLigne());
	}

	/**
	 * Le nombre de points inscrits dans le fichier selon la definition "element
	 * vertex nbElements pour un fichier correct
	 **/
	@Test
	void testNbVertex() {
		lecteur.setFichierPLY(fichierTest);
		try {
			assertEquals(10, lecteur.getNbVertex());
		} catch (NombreElementNegatifException e) {
			e.getMessage();
		}
	}

	/**
	 * Le nombre de faces inscrits dans le fichier selon la definition "element face
	 * nbFaces
	 **/
	@Test
	void testNbFace() {
		lecteur.setFichierPLY(fichierTest);
		try {
			assertEquals(3, lecteur.getNbFaces());
		} catch (NombreElementNegatifException e) {
			e.getMessage();
		}
	}

	@Test
	void testNbVertexFaute() {
		lecteur.setFichierPLY(fichierTestFaux);
		try {
			assertEquals(-1, lecteur.getNbVertex());
		} catch (NombreElementNegatifException e) {
			e.getMessage();
		}
	}

}
