package modele.structure;

import java.awt.event.ActionListener;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Observable;

import javax.swing.Timer;

/**
 * Cette classe nous permet de créer un modèle 3D à partir d'une liste de
 * faces et d'effectuer les calculs inclus dans Matrix sur le modèle
 * 
 * @author poulyt
 *
 */
public class Modele3D extends Observable {

	/**
	 * Liste des faces du modèle
	 */
	private Face[] listeFaces;
	private ArrayList<Face> listFace;
	private File fichier;
	/**
	 * Ce paramètre nous permet de récupèrer tout les calculs de matrice
	 */
	private Matrix matrice;

	public Modele3D(int nbPoints, int nbFaces) {
		listFace = new ArrayList<>();
		matrice = new Matrix(nbPoints, 4);
		listeFaces = new Face[nbFaces];
		fichier = null;
	}

	public Modele3D(int nbPoints, File f) {
		listFace = new ArrayList<>();
		matrice = new Matrix(nbPoints, 4);
		fichier = f;
	}

	/**
	 * Permet d'ajouter une face au modele
	 * 
	 * @param face
	 */
	public void addFace(Face face) {
		listFace.add(face);
	}

	/**
	 * Retourne la liste des faces qui compose le modele
	 * 
	 * @return liste des faces
	 */
	public ArrayList<Face> getListFace() {
		return listFace;
	}

	/**
	 * retourne le tableau de faces
	 * 
	 * @return Face[]
	 */
	public Face[] getListeFaces() {
		return this.listeFaces;
	}

	/**
	 * Cette fonction recherche le point avec la plus grande abcisse
	 * 
	 * @return
	 */
	public double recherchePlusGrandX() {
		double temp = 0;
		for (int i = 0; i < matrice.getMatrice()[1].length; i++) {
			if (matrice.getMatrice()[0][i] > temp) {
				temp = matrice.getMatrice()[0][i];
			}
		}
		return temp;
	}

	/**
	 * Cette fonction recherche le point avec la plus grande ordonn�e
	 * 
	 * @return
	 */
	public double recherchePlusGrandY() {
		double temp = 0;
		for (int i = 0; i < matrice.getMatrice()[1].length; i++) {
			if (matrice.getMatrice()[1][i] > temp) {
				temp = matrice.getMatrice()[1][i];
			}
		}
		return temp;

	}

	/**
	 * Retourne l'element placé dans la liste des Faces à l'indice passé en
	 * paramètre
	 *
	 * @param idx L'indice de l'objet à retourner dans la liste.
	 **/
	public Face getFace(int idx) {
		return listFace.get(idx);
	}

	/**
	 * Récupère la matrice qui contient les points
	 * 
	 * @return matrice
	 */
	public Matrix getMatrice() {
		return matrice;
	}

	/**
	 * Recup�re le fichier
	 * 
	 * @return
	 */
	public File getFichier() {
		return fichier;
	}

	/**
	 * Récupère directement la matrice sous forme d'un tableau de double
	 * 
	 * @return matrice tableau de double[][]
	 */
	public double[][] getMatricePoint() {
		return matrice.getMatrice();
	}

	/**
	 * Cette fonction permet de set le fichier fournit en param�tre
	 * 
	 * @param f
	 */
	public void setFichier(File f) {
		fichier = f;
		setChanged();
		notifyObservers(f);
	}

	/**
	 * Effectuer une translation sur la matrice
	 * 
	 * @param tX
	 * @param tY
	 * @param tZ
	 */
	public void translation(double tX, double tY, double tZ) {
		matrice.setMatrice(Matrix.translation(matrice.getMatrice(), tX, tY, tZ));
		setChanged();
		notifyObservers();
	}

	/**
	 * Effectuer une zoom sur la matrice
	 * 
	 * @param tX
	 * @param tY
	 * @param tZ
	 */
	public void zoom(double k) {
		matrice.setMatrice(Matrix.zoom(matrice.getMatrice(), k));
		setChanged();
		notifyObservers();
	}

	public void zoomCentre(double k, double x, double y) {
		matrice.setMatrice(Matrix.zoomCentre(matrice.getMatrice(), k, x, y));
		setChanged();
		notifyObservers();
	}

	/**
	 * Effectuer une rotation sur la matrice
	 * 
	 * @param tX
	 * @param tY
	 * @param tZ
	 */

	public void rotation(double angleDegre, char axe) {
		matrice.setMatrice(Matrix.rotation(matrice.getMatrice(), angleDegre, axe));
		setChanged();
		notifyObservers();
	}

	public void rotationCentre(double angleRad, double x, double y, char axe) {
		matrice.setMatrice(Matrix.rotationCentre(getMatricePoint(), angleRad, x, y, axe));
		setChanged();
		notifyObservers();
	}

	public void setMatricePoint(double[][] matrice) {
		this.matrice.setMatrice(matrice);
		setChanged();
		notifyObservers();
	}

	public double[] getCoordsFromFace(Face face, Coordonnee coordonnee) {
		int[] idxsPoints = face.getTabPoint();
		double[] coords = new double[idxsPoints.length];
		switch (coordonnee) {
		case X:
			for (int i = 0; i < idxsPoints.length; i++) {
				coords[i] = matrice.getValue(0, idxsPoints[i]);
			}
			break;
		case Y:
			for (int i = 0; i < idxsPoints.length; i++) {
				coords[i] = matrice.getValue(1, idxsPoints[i]);
			}
			break;
		case Z:
			for (int i = 0; i < idxsPoints.length; i++) {
				coords[i] = matrice.getValue(2, idxsPoints[i]);
			}
			break;
		}
		return coords;
	}
	
	@Override
	public String toString() {
		return Arrays.deepToString(getMatricePoint());
	}

}