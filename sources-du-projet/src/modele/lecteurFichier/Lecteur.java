package modele.lecteurFichier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modele.structure.Face;
import modele.structure.Modele3D;

/** S'occupe du parsing d'un fichier .ply. **/
public class Lecteur {

	private File fichierPLY;

	public Lecteur(File fichier) {
		fichierPLY = fichier;
	}

	public Lecteur() {
	}

	// DEBUGAGE
	/** Affichage du fichier en attribut. **/
	public void affichageFichier() {
		Scanner sc;
		try {
			sc = new Scanner(fichierPLY);
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** Mute le fichier vers celui pass� en param�tre **/
	public void setFichierPLY(File fichierPLY) {
		this.fichierPLY = fichierPLY;
	}

	// CONTROLES ET INFORMATIONS POUR MODELE
	/** @return La premiere ligne du fichier. **/
	public String getPremiereLigne() {
		Scanner sc;
		try {
			sc = new Scanner(fichierPLY);
			if (sc.hasNextLine()) {
				return sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return Le nombre de points identifi� � la ligne de d�claration si le
	 *         fichier est correct, -1 sinon.
	 **/
	public int getNbVertex() throws NombreElementNegatifException {
		Scanner sc;
		String line = "";
		String[] parsedLine;
		try {
			sc = new Scanner(fichierPLY);
			while (sc.hasNextLine() && !line.contains("element vertex")) {
				line = sc.nextLine();
			}
			sc.close();
			parsedLine = line.split(" ");
			if (parsedLine.length == 3) {
				if (Integer.valueOf(parsedLine[2]) < 0) {
					throw new NombreElementNegatifException();
				} else {
					return Integer.parseInt(line.split(" ")[2]);
				}
			} else {
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		return -1;
	}

	/**
	 * @return Le nombre de faces identifi� � la ligne de d�claration si le
	 *         fichier est correct, -1 sinon.
	 **/
	public int getNbFaces() throws NombreElementNegatifException {
		Scanner sc;
		String line = "";
		String[] parsedLine;
		try {
			sc = new Scanner(fichierPLY);
			while (sc.hasNextLine() && !line.contains("element face")) {
				line = sc.nextLine();
			}
			sc.close();
			parsedLine = line.split(" ");
			if (parsedLine.length == 3) {
				if (Integer.valueOf(parsedLine[2]) < 0)
					throw new NombreElementNegatifException();
				else
					return Integer.parseInt(line.split(" ")[2]);
			} else
				System.out.println("Erreur syntaxique dans la longueur de la ligne contenant [ element face ]");
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		return -1;
	}

	// CREATION MODELE
	/** @return Un mod�le contenant la liste des points et la liste des faces **/
	public Modele3D creerModele3D() throws FileNotFoundException, NombreElementNegatifException {
		int nbVertex = getNbVertex();
		int nbFaces = getNbFaces();
		Modele3D modele = new Modele3D(nbVertex, nbFaces);
		modele.setFichier(fichierPLY);
		String line = "";
		Scanner sc = new Scanner(fichierPLY);
		String parsedLineVertex[];
		while (sc.hasNextLine() && !line.equals("end_header")) {
			line = sc.nextLine();
		}
		for (int i = 0; i < nbVertex; i++) {
			line = sc.nextLine();
			parsedLineVertex = line.split(" ");
			modele.getMatrice().setValue(i, 0, Double.valueOf(parsedLineVertex[0]));
			modele.getMatrice().setValue(i, 1, Double.valueOf(parsedLineVertex[1]));
			modele.getMatrice().setValue(i, 2, Double.valueOf(parsedLineVertex[2]));
			modele.getMatrice().setValue(i, 3, 1.0);
		}
		Face face;
		double[][] m = modele.getMatricePoint();
		ArrayList<Integer> listIdPoint;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			parsedLineVertex = line.split(" ");
			face = new Face(Integer.valueOf(parsedLineVertex[0]));
			for (int i = 1; i < parsedLineVertex.length; i++) {
				int col = Integer.valueOf(parsedLineVertex[i]);
				face.addPointID(Integer.valueOf(parsedLineVertex[i]));
			}
			modele.addFace(face);
		}

		sc.close();
		return modele;
	}
}
