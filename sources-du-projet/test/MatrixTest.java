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
	 * Tests de la fonction de multiplication de matrices entre elles
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
	 * Tests de la fonction de translation de matrices
	 */
	@Test
	public void testTranslation() {
		double[][] point = { { 1 }, { 2 }, { 0 }, { 1 } }; // coordonnï¿½es homogï¿½nes
		double[][] pointExpected = { { 2 }, { 2 }, { 0 }, { 1 } };
		assertTrue(Arrays.deepEquals(pointExpected, Matrix.translation(point, 1, 0, 0)));
	}

	/**
	 * Tests de la fonction de zoom de matrices
	 */
	@Test
	public void testZoom() {
		double[][] point = { { 1 }, { 2 }, { 3 }, { 1 } }; // coordonnï¿½es homogï¿½nes
		double[][] pointExpected = { { 3 }, { 6 }, { 9 }, { 1 } };
		assertTrue(Arrays.deepEquals(pointExpected, Matrix.zoom(point, 3)));
	}

	/**
	 * Test de la fonction rotation de matrice sur l'axe des X
	 */
	@Test
	public void testRotationX() {
		double rotRad = 60;
		double[][] point = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
		double[][] pointsFinaux = { { 1, 2, 3, 4 },
				{ 5 * Math.cos(rotRad) - 9 * Math.sin(rotRad), 6 * Math.cos(rotRad) - 10 * Math.sin(rotRad),
						7 * Math.cos(rotRad) - 11 * Math.sin(rotRad), 8 * Math.cos(rotRad) - 12 * Math.sin(rotRad) },
				{ 5 * Math.sin(rotRad) + 9 * Math.cos(rotRad), 6 * Math.sin(rotRad) + 10 * Math.cos(rotRad),
						7 * Math.sin(rotRad) + 11 * Math.cos(rotRad), 8 * Math.sin(rotRad) + 12 * Math.cos(rotRad) },
				{ 1, 1, 1, 1 } };
		assertArrayEquals(pointsFinaux, Matrix.rotation(point, Math.toDegrees(60), 'x'));
	}

	/**
	 * Test de la fonction rotation de matrice sur l'axe des Y
	 */
	@Test
	public void testRotationY() {
		double rotRad = 60;
		double[][] point = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
		double[][] pointsFinaux = {
				{ Math.cos(rotRad) + 9 * Math.sin(rotRad), 2 * Math.cos(rotRad) + 10 * Math.sin(rotRad),
						3 * Math.cos(rotRad) + 11 * Math.sin(rotRad), 4 * Math.cos(rotRad) + 12 * Math.sin(rotRad) },
				{ 5, 6, 7, 8 },
				{ -Math.sin(rotRad) + 9 * Math.cos(rotRad), -2 * Math.sin(rotRad) + 10 * Math.cos(rotRad),
						-3 * Math.sin(rotRad) + 11 * Math.cos(rotRad), -4 * Math.sin(rotRad) + 12 * Math.cos(rotRad) },
				{ 1, 1, 1, 1 } };
		assertArrayEquals(pointsFinaux, Matrix.rotation(point, Math.toDegrees(60), 'y'));
	}

	/**
	 * Test de la fonction rotation de matrice sur l'axe des Y
	 */
	@Test
	public void testRotationZ() {
		double rotRad = 60;
		double[][] point = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
		double[][] pointsFinaux = {
				{ Math.cos(rotRad) - 5 * Math.sin(rotRad), 2 * Math.cos(rotRad) - 6 * Math.sin(rotRad),
						3 * Math.cos(rotRad) - 7 * Math.sin(rotRad), 4 * Math.cos(rotRad) - 8 * Math.sin(rotRad) },
				{ Math.sin(rotRad) + 5 * Math.cos(rotRad), 2 * Math.sin(rotRad) + 6 * Math.cos(rotRad),
						3 * Math.sin(rotRad) + 7 * Math.cos(rotRad), 4 * Math.sin(rotRad) + 8 * Math.cos(rotRad) },
				{ 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
		assertArrayEquals(pointsFinaux, Matrix.rotation(point, Math.toDegrees(60), 'z'));
	}

	/**
	 * Test de la fonction rotation centrée de matrice sur l'axe des X
	 */
	@Test
	public void testRotationCentreX() {
		double rotRad = 60;
		double x = 1;
		double y = 1;
		double[][] point = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
		double[][] pointsFinaux = { { 1, 2, 3, 4 },
				{ 5 * Math.cos(rotRad) - 9 * Math.sin(rotRad) + y * (-Math.cos(rotRad) + 1),
						6 * Math.cos(rotRad) - 10 * Math.sin(rotRad) + y * (-Math.cos(rotRad) + 1),
						7 * Math.cos(rotRad) - 11 * Math.sin(rotRad) + y * (-Math.cos(rotRad) + 1),
						8 * Math.cos(rotRad) - 12 * Math.sin(rotRad) + y * (-Math.cos(rotRad) + 1) },
				{ 5 * Math.sin(rotRad) + 9 * Math.cos(rotRad) - y * (Math.sin(rotRad)),
						6 * Math.sin(rotRad) + 10 * Math.cos(rotRad) - y * (Math.sin(rotRad)),
						7 * Math.sin(rotRad) + 11 * Math.cos(rotRad) - y * (Math.sin(rotRad)),
						8 * Math.sin(rotRad) + 12 * Math.cos(rotRad) - y * (Math.sin(rotRad)) },
				{ 1, 1, 1, 1 } };
		assertArrayEquals(pointsFinaux, Matrix.rotationCentre(point, rotRad, x, y, 'x'));
	}

	/**
	 * Test de la fonction rotation centrée de matrice sur l'axe des Y
	 */
	@Test
	public void testRotationCentreY() {
		double rotRad = 60;
		double x = 0.1;
		double y = 1;
		double[][] point = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
		double[][] pointsFinaux = {
				{ Math.cos(rotRad) + 9 * Math.sin(rotRad) + (-x) * Math.cos(rotRad) + x,
						2 * Math.cos(rotRad) + 10 * Math.sin(rotRad) + (-x) * Math.cos(rotRad) + x,
						3 * Math.cos(rotRad) + 11 * Math.sin(rotRad) + (-x) * Math.cos(rotRad) + x,
						4 * Math.cos(rotRad) + 12 * Math.sin(rotRad) + (-x) * Math.cos(rotRad) + x },
				{ 5, 6, 7, 8 },
				{ -Math.sin(rotRad) + 9 * Math.cos(rotRad) - (x * -Math.sin(rotRad)),
						-2 * Math.sin(rotRad) + 10 * Math.cos(rotRad) - (x * -Math.sin(rotRad)),
						-3 * Math.sin(rotRad) + 11 * Math.cos(rotRad) - (x * -Math.sin(rotRad)),
						-4 * Math.sin(rotRad) + 12 * Math.cos(rotRad) - (x * -Math.sin(rotRad)) },
				{ 1, 1, 1, 1 } };
		assertArrayEquals(pointsFinaux, Matrix.rotationCentre(point, rotRad, x, y, 'y'));
	}

	/**
	 * Test de la fonction rotation centrée de matrice sur l'axe des Z
	 */
	@Test
	public void testRotationCentreZ() {
		double rotRad = 60;
		double x = 0;
		double y = 0;
		double[][] point = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
		double[][] pointsFinaux = {
				{ Math.cos(rotRad) - 5 * Math.sin(rotRad) - x * Math.cos(rotRad) + y * Math.sin(rotRad) + x,
						2 * Math.cos(rotRad) - 6 * Math.sin(rotRad) - x * Math.cos(rotRad) + y * Math.sin(rotRad) + x,
						3 * Math.cos(rotRad) - 7 * Math.sin(rotRad) - x * Math.cos(rotRad) + y * Math.sin(rotRad) + x,
						4 * Math.cos(rotRad) - 8 * Math.sin(rotRad) - x * Math.cos(rotRad) + y * Math.sin(rotRad) + x },
				{ Math.sin(rotRad) + 5 * Math.cos(rotRad) + (-x) * Math.sin(rotRad) + (-y) * Math.sin(rotRad) + y,
						2*Math.sin(rotRad) + 6 * Math.cos(rotRad) + (-x) * Math.sin(rotRad) + (-y) * Math.sin(rotRad) + y,
						3*Math.sin(rotRad) + 7 * Math.cos(rotRad) + (-x) * Math.sin(rotRad) + (-y) * Math.sin(rotRad) + y,
						4*Math.sin(rotRad) + 8 * Math.cos(rotRad) + (-x) * Math.sin(rotRad) + (-y) * Math.sin(rotRad) + y },
				{9,10,11,12}, {1,1,1,1} };
		assertArrayEquals(pointsFinaux, Matrix.rotationCentre(point, rotRad, x, y, 'z'));
	}
}
