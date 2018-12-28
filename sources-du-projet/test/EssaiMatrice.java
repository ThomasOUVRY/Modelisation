import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;

import modele.lecteurFichier.Lecteur;
import modele.lecteurFichier.NombreElementNegatifException;
import modele.structure.Matrix;
import modele.structure.Modele3D;

/**
 * Cette classe nous permet de test et de débuguer les classes "Lecteur" et
 * "Modele3D"
 * 
 * @author poulyt
 *
 *
 */
public class EssaiMatrice {

	@Test
	public static void main(String[] args) throws FileNotFoundException, NombreElementNegatifException {
		Lecteur lec = new Lecteur(new File("sources-du-projet/ressources/dolphin.ply"));
		Modele3D modeleTest = lec.creerModele3D();
		double[][] matrice = modeleTest.getMatricePoint();
		Matrix m = new Matrix(matrice);
		System.out.println("X : " + matrice[0][7] + " Y : " + matrice[1][7] + " Z : " + matrice[2][7]);
		int indicepts = modeleTest.getListFace().get(5).getTabPoint()[1];
		System.out.println("Face 0 point " + modeleTest.getListFace().get(5).getTabPoint()[0]);
		System.out.println("Face 0 point " + modeleTest.getListFace().get(5).getTabPoint()[1]);
		System.out.println("Face 0 point " + modeleTest.getListFace().get(5).getTabPoint()[2]);
		System.out.println(
				"X : " + matrice[0][indicepts] + " Y : " + matrice[1][indicepts] + " Z : " + matrice[2][indicepts]);
//		System.out.println(matrice.length + " " + matrice[0].length);
//		System.out.println(Arrays.deepToString(Matrix.translation(matrice, 1, 1, 1)));
	}
}
