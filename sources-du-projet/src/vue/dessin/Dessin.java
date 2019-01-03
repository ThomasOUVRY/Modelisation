package vue.dessin;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import modele.structure.ComparatorZ;
import modele.structure.Coordonnee;
import modele.structure.Face;
import modele.structure.Modele3D;
import modele.structure.Vecteur;
import vue.VueAvancee;
import vue.VueSimple;

public class Dessin {

	public static void dessinerPoints(Canvas canvas, Modele3D modele, String methode) throws Exception {
		if (methode.toLowerCase().equals("pixelwriter"))
			dessinerPointsPixelWriter(canvas, modele);
		else if (methode.toLowerCase().equals("graphicscontext"))
			dessinerPointsGraphicsContext(canvas, modele);
		else
			throw new Exception();
	}

	private static void dessinerPointsPixelWriter(Canvas canvas, Modele3D modele) {
		double[][] matricePoints = modele.getMatricePoint();
		PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
		for (int i = 0; i < matricePoints[0].length; i++) {
			int x = (int) Math.round(matricePoints[0][i]);
			int y = (int) Math.round(matricePoints[1][i]);
			pixelWriter.setColor(x, y, Color.BLACK);
		}
	}

	private static void dessinerPointsGraphicsContext(Canvas canvas, Modele3D modele) {
		double[][] matricePoints = modele.getMatricePoint();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for (int i = 0; i < matricePoints[0].length; i++) {
			int x = (int) Math.round(matricePoints[0][i]);
			int y = (int) Math.round(matricePoints[1][i]);
			gc.strokeOval(x, y, 3, 3);
		}
	}

	public static void dessinerLignes(Canvas canvas, Modele3D modele, String methode) throws Exception {
		if (methode.toLowerCase().equals("pixelwriter"))
			dessinerLignesPixelWriter(canvas, modele);
		else if (methode.toLowerCase().equals("graphicscontext"))
			dessinerLignesGraphicsContext(canvas, modele);
		else
			throw new Exception();
	}

	private static void dessinerLignesGraphicsContext(Canvas canvas, Modele3D modele) {
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		double[][] matricePoints = modele.getMatricePoint();
		for (Face face : modele.getListFace()) {
			int idxPoint1;
			int idxPoint2;
			int x1;
			int x2;
			int y1;
			int y2;
			for (int j = 0; j < face.getTabPoint().length - 1; j++) {
				idxPoint1 = face.getTabPoint()[j];
				idxPoint2 = face.getTabPoint()[j + 1];
				x1 = (int) Math.round(matricePoints[0][idxPoint1]);
				x2 = (int) Math.round(matricePoints[0][idxPoint2]);
				y1 = (int) Math.round(matricePoints[1][idxPoint1]);
				y2 = (int) Math.round(matricePoints[1][idxPoint2]);
				graphicsContext.strokeLine(x1, y1, x2, y2);
			}
			idxPoint1 = face.getTabPoint()[face.getTabPoint().length - 1];
			idxPoint2 = face.getTabPoint()[0];
			x1 = (int) Math.round(matricePoints[0][idxPoint1]);
			x2 = (int) Math.round(matricePoints[0][idxPoint2]);
			y1 = (int) Math.round(matricePoints[1][idxPoint1]);
			y2 = (int) Math.round(matricePoints[1][idxPoint2]);
			graphicsContext.strokeLine(x1, y1, x2, y2);
		}
	}

	private static void dessinerLignesPixelWriter(Canvas canvas, Modele3D modele) {
		double[][] matricePoints = modele.getMatricePoint();
		PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
		for (Face face : modele.getListFace()) {
			int idxPoint1;
			int idxPoint2;
			int x1;
			int x2;
			int y1;
			int y2;
			for (int j = 0; j < face.getTabPoint().length - 1; j++) {
				idxPoint1 = face.getTabPoint()[j];
				idxPoint2 = face.getTabPoint()[j + 1];
				x1 = (int) Math.round(matricePoints[0][idxPoint1]);
				x2 = (int) Math.round(matricePoints[0][idxPoint2]);
				y1 = (int) Math.round(matricePoints[1][idxPoint1]);
				y2 = (int) Math.round(matricePoints[1][idxPoint2]);
				if (x1 < x2) {
					dessinerLigne(pixelWriter, x1, y1, x2, y2);
				} else {
					dessinerLigne(pixelWriter, x2, y2, x1, y1);
				}
			}
			idxPoint1 = face.getTabPoint()[face.getTabPoint().length - 1];
			idxPoint2 = face.getTabPoint()[0];
			x1 = (int) Math.round(matricePoints[0][idxPoint1]);
			x2 = (int) Math.round(matricePoints[0][idxPoint2]);
			y1 = (int) Math.round(matricePoints[1][idxPoint1]);
			y2 = (int) Math.round(matricePoints[1][idxPoint2]);
			if (x1 < x2) {
				dessinerLigne(pixelWriter, x1, y1, x2, y2);
			} else {
				dessinerLigne(pixelWriter, x2, y2, x1, y1);
			}
		}
	}

	private static void dessinerLigne(PixelWriter pixelWriter, int x1, int y1, int x2, int y2) {
		// delta of exact value and rounded value of the dependent variable
		int d = 0;

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);

		int dx2 = 2 * dx; // slope scaling factors to
		int dy2 = 2 * dy; // avoid floating point

		int ix = x1 < x2 ? 1 : -1; // increment direction
		int iy = y1 < y2 ? 1 : -1;

		int x = x1;
		int y = y1;

		if (dx >= dy) {
			while (true) {
				pixelWriter.setColor(x, y, Color.BLACK);
				if (x == x2)
					break;
				x += ix;
				d += dy2;
				if (d > dx) {
					y += iy;
					d -= dx2;
				}
			}
		} else {
			while (true) {
				pixelWriter.setColor(x, y, Color.BLACK);
				if (y == y2)
					break;
				y += iy;
				d += dx2;
				if (d > dy) {
					x += ix;
					d -= dy2;
				}
			}
		}
	}

	public static void dessinerFaces(Canvas canvas, Modele3D modele, String methode, String vue) throws Exception {
		if (methode.toLowerCase().equals("graphicscontext")) {
			dessinerFacesGraphicsContext(canvas, modele, false, vue);
		} else {
			throw new Exception();
		}
	}

	private static void dessinerFacesGraphicsContext(Canvas canvas, Modele3D modele, boolean segment, String vue) {
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		ComparatorZ comparator = new ComparatorZ(modele);
		List<Face> listFaces = modele.getListFace();
		listFaces.sort(comparator);
		for (int i = 0; i < listFaces.size(); i++) {
			double couleur;
			if (vue.toLowerCase().equals("avance")) {
				couleur = (eclairage(modele, i)) / 255.0;
			} else {
				couleur = 0.5;
			}

			if (!Double.isNaN(couleur)) {
				Color c = Color.color(couleur, couleur, couleur);
				graphicsContext.setFill(c);
			} else {
				graphicsContext.setFill(Color.RED);
				
			}
			Face currentFace = listFaces.get(i);
			int[] idxsPoints = currentFace.getTabPoint();
			double[] xPos = modele.getCoordsFromFace(currentFace, Coordonnee.X);
			double[] yPos = modele.getCoordsFromFace(currentFace, Coordonnee.Y);
			graphicsContext.fillPolygon(xPos, yPos, idxsPoints.length);
			if (segment) {
				graphicsContext.strokePolygon(xPos, yPos, idxsPoints.length);
			}
		}

	}

	public static void dessinerSegmentsFaces(Canvas canvas, Modele3D modele, String methode, String vue) throws Exception {
		if (methode.toLowerCase().equals("graphicscontext")) {
			dessinerFacesGraphicsContext(canvas, modele, true, vue);
		}

	}

	private static double eclairage(Modele3D modele, int numFace) {
		// coordonn�es vecteur lumi�re
		Vecteur lumiere = new Vecteur(0, 0.9, 0);

		double[][] matrice = modele.getMatricePoint();
		int[] pointsFace = modele.getListFace().get(numFace).getTabPoint();
		int ptA = pointsFace[0];
		int ptB = pointsFace[1];
		int ptC = pointsFace[2];

		// Les deux vecteurs du plan ABC
		Vecteur AB = new Vecteur(matrice[0][ptA], matrice[1][ptA], matrice[2][ptA], matrice[0][ptB], matrice[1][ptB],
				matrice[2][ptB]);
		Vecteur AC = new Vecteur(matrice[0][ptA], matrice[1][ptA], matrice[2][ptA], matrice[0][ptC], matrice[1][ptC],
				matrice[2][ptC]);

		// vecteur normal (vecteur perpendiculaire au plan)
		Vecteur normal = Vecteur.normal(AB, AC);

		// calcul cos de th�ta
		double cosTheta = Vecteur.cosTheta(normal, lumiere);
		// calcul de th�ta en degr�

		double arcCosTheta = Vecteur.arcCosTheta(cosTheta);

		// calcul permettant de convertir un degr� en couleur (sachant que couleur va
		// de 1 � 254 et degr� de 0 � 360)
		double coeffCouleurDegre = 255.0 / 360.0;
		return arcCosTheta * coeffCouleurDegre;
	}
}