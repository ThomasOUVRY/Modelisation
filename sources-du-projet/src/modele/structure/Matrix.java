package modele.structure;

import java.util.Arrays;

/**
 * Une classe comportant la liste de points (X, Y, Z, 1) de la figure.
 * 
 * @author Lucas
 *
 */
public class Matrix {

	private double[][] matrice;

	/**
	 * Contructeur avec une matrice en param�tre
	 * 
	 * @param matrice
	 */
	public Matrix(double[][] matrice) {
		this.matrice = matrice;
	}

	/**
	 * Contructeur avec un mod�le de matrice en param�tre
	 * 
	 * @param matrice
	 */
	public Matrix(int nbColonnes, int nbLignes) {
		this.matrice = new double[nbLignes][nbColonnes];
	}

	/**
	 * Donne une coordonnée en fonction de la colonne(ID du point) et de la
	 * ligne(une des 3 coordonnees du point)
	 * 
	 * @param col
	 * @param ligne
	 * @param value
	 */
	public void setValue(int col, int ligne, double value) {
		matrice[ligne][col] = value;
	}

	public double getValue(int ligne, int colonne) {
		return matrice[ligne][colonne];
	}

	/**
	 * renvoie la matrice sous forme d'un tableau de double
	 * 
	 * @return matrice tableau de double
	 */
	public double[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(double[][] m) {
		matrice = m;
	}

	/**
	 * Multiplie deux matrices pour faire (zoom, dezoom, translation)
	 * 
	 * @param a matrice une
	 * @param b matrice deux
	 * @return la nouvelle matrice de double
	 */
	public static double[][] multiply(double[][] a, double[][] b) {
		int m1 = a.length;
		int n1 = a[0].length;
		int m2 = b.length;
		int n2 = b[0].length;
		if (n1 != m2)
			throw new RuntimeException("Illegal matrix dimensions.");
		double[][] c = new double[m1][n2];
		for (int i = 0; i < m1; i++)
			for (int j = 0; j < n2; j++)
				for (int k = 0; k < n1; k++)
					c[i][j] += a[i][k] * b[k][j];
		return c;
	}

	public static double[][] translation(double[][] matrice, double tX, double tY, double tZ) {
		double[][] translation = { { 1, 0, 0, tX }, { 0, 1, 0, tY }, { 0, 0, 1, tZ }, { 0, 0, 0, 1 } };
		return Matrix.multiply(translation, matrice);
	}

	public static double[][] zoom(double[][] matrice, double k) {
		double[][] zoom = { { k, 0, 0, 0 }, { 0, k, 0, 0 }, { 0, 0, k, 0 }, { 0, 0, 0, 1 } };
		return Matrix.multiply(zoom, matrice);
	}

	public static double[][] zoomCentre(double[][] matrice, double k, double x, double y) {
		double[][] zoomCentre = { { k, 0, 0, x * (1 - k) }, { 0, k, 0, y * (1 - k) }, { 0, 0, k, 0 }, { 0, 0, 0, 1 } };
		return Matrix.multiply(zoomCentre, matrice);
	}

	public static double[][] rotation(double[][] matrice, double angleDegre, char axe) {
		double angleRadian = Math.toRadians(angleDegre);
		double[][] rota;
		switch (axe) {
		case 'x':
			rota = new double[][] { { 1, 0, 0, 0 }, { 0, Math.cos(angleRadian), -Math.sin(angleRadian), 0 },
					{ 0, Math.sin(angleRadian), Math.cos(angleRadian), 0 }, { 0, 0, 0, 1 } };
			break;
		case 'y':
			rota = new double[][] { { Math.cos(angleRadian), 0, Math.sin(angleRadian), 0 }, { 0, 1, 0, 0 },
					{ -Math.sin(angleRadian), 0, Math.cos(angleRadian), 0 }, { 0, 0, 0, 1 } };
			break;
		case 'z':
			rota = new double[][] { { Math.cos(angleRadian), -Math.sin(angleRadian), 0, 0 },
					{ Math.sin(angleRadian), Math.cos(angleRadian), 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
			break;
		default:
			return null;
		}
		return Matrix.multiply(rota, matrice);
	}

	public static double[][] rotationCentre(double[][] matrice, double angleRad, double x, double y, char axe) {
		double[][] rotaCentre;
		switch (axe) {
		case 'x':
			rotaCentre = new double[][] { { 1, 0, 0, 0 },
					{ 0, Math.cos(angleRad), -Math.sin(angleRad), y * (-Math.cos(angleRad) + 1) },
					{ 0, Math.sin(angleRad), Math.cos(angleRad), -y * Math.sin(angleRad) }, { 0, 0, 0, 1 } };
			break;
		case 'y':
			rotaCentre = new double[][] { { Math.cos(angleRad), 0, Math.sin(angleRad), -x * Math.cos(angleRad) + x },
					{ 0, 1, 0, 0 }, { -Math.sin(angleRad), 0, Math.cos(angleRad), -x * -Math.sin(angleRad) },
					{ 0, 0, 0, 1 } };
			break;
		case 'z':
			rotaCentre = new double[][] {
					{ Math.cos(angleRad), -Math.sin(angleRad), 0,
							(-x * Math.cos(angleRad) + (-y * -Math.sin(angleRad) + x)) },
					{ Math.sin(angleRad), Math.cos(angleRad), 0,
							(-x * Math.sin(angleRad) + (-y * Math.cos(angleRad) + y)) },
					{ 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
			break;
		default:
			return null;
		}
		return Matrix.multiply(rotaCentre, matrice);
	}

	public String toString() {
		String rep = "";
		for (int i = 0; i < matrice.length; i++) {
			for (int y = 0; y < matrice[1].length; y++) {
				rep = rep + " " + matrice[i][y] + " ";
			}
			rep = rep + "\n";
		}
		return rep;
	}

	@Override
	public boolean equals(Object obj) {
		Matrix etranger = (Matrix) obj;
		return Arrays.deepEquals(matrice, etranger.getMatrice());
	}

}
