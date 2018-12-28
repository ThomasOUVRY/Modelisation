import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import modele.structure.Matrix;
import org.junit.jupiter.api.Test;


/**
 * Test les fonctions inclus dans "Matrix"
 * 
 * @author poulyt
 *
 */
class MatrixTest {

	/**
	 * Tets de la fonction de multiplication de matrices entre elles
	 */
	@Test
	public void testMultiply() {
		double[][] matrice1 = new double[][] { { 1.0, 2.0, 3.0, 4.0 }, { 1.0, 2.0, 3.0, 4.0 }, { 1.0, 2.0, 3.0, 4.0 },
				{ 1.0, 2.0, 3.0, 4.0 } };
		double[][] matrice2 = new double[][] { { 1.0, 1.0, 3.0, 4.0 }, { 1.0, 1.0, 3.0, 4.0 }, { 1.0, 1.0, 3.0, 4.0 },
				{ 1.0, 1.0, 3.0, 4.0 } };
		double[][] matriceR = new double[][] { { 10.0, 10.0, 30.0, 40.0 }, { 10.0, 10.0, 30.0, 40.0 },
				{ 10.0, 10.0, 30.0, 40.0 }, { 10.0, 10.0, 30.0, 40.0 } };
		assertArrayEquals(Matrix.multiply(matrice1, matrice2), matriceR);
	}

	/**
	 * Tets de la fonction de translation de matrices
	 */
	@Test
	public void testTranslation() {
		double[][] point = { { 1 }, { 2 }, { 0 }, { 1 } }; // coordonn�es homog�nes
		double[][] pointExpected = { { 2 }, { 2 }, { 0 }, { 1 } };
		assertTrue(Arrays.deepEquals(pointExpected, Matrix.translation(point, 1, 0, 0)));
	}

	/**
	 * Tets de la fonction de zoom de matrices
	 */
	@Test
	public void testZoom() {
		double[][] point = { { 1 }, { 2 }, { 3 }, { 1 } }; // coordonn�es homog�nes
		double[][] pointExpected = { { 3 }, { 6 }, { 9 }, { 1 } };
		assertTrue(Arrays.deepEquals(pointExpected, Matrix.zoom(point, 3)));
	}

	/**
	 * Test de la fonction rotation de matrice
	 */
	@Test
	public void testRotation() {
		double[][] point = { { 1 }, { 2 }, { 3 }, { 1 } };
		double[][] pointExpected = { { 1 }, { -Math.cos(Math.toRadians(45)) },
				{ 2 * Math.sin(Math.toRadians(45)) + 3 * Math.cos(Math.toRadians(45)) }, { 1 } };
		assertTrue(Arrays.deepEquals(pointExpected, Matrix.rotation(point, 45, 'x')));
	}
}
