package modele.structure;

public class Vecteur {

	private double coordonneeX;
	private double coordonneeY;
	private double coordonneeZ;
	
	public Vecteur(double cx, double cy, double cz) {
		coordonneeX = cx;
		coordonneeY = cy;
		coordonneeZ = cz;
	}
	
	public Vecteur(double x1, double y1, double z1, double x2, double y2, double z2) {
		coordonneeX = x2 - x1;
		coordonneeY = y2 - y1;
		coordonneeZ = z2 - z1;
	}
	
	public double getCoordonneeX() {
		return coordonneeX;
	}
	
	public double getCoordonneeY() {
		return coordonneeY;
	}
	
	public double getCoordonneeZ() {
		return coordonneeZ;
	}
	
	private double norme() {
		return Math.sqrt(coordonneeX * coordonneeX + coordonneeY * coordonneeY + coordonneeZ * coordonneeZ);
	}
	
	
	public static Vecteur normal(Vecteur v1, Vecteur v2) {
		return new Vecteur(v1.getCoordonneeY() * v2.getCoordonneeZ() - v1.getCoordonneeY() * v2.getCoordonneeY(), 
				v1.getCoordonneeX() * v2.getCoordonneeZ() - v1.getCoordonneeZ() * v2.getCoordonneeX(), 
				v1.getCoordonneeX() * v2.getCoordonneeY() - v1.getCoordonneeY() * v2.getCoordonneeX());
	}
	
	public static double cosTheta(Vecteur v1, Vecteur v2) {
		return (v1.getCoordonneeX() * v2.getCoordonneeX() + v1.getCoordonneeY() * v2.getCoordonneeY() + 
				v1.getCoordonneeZ() * v2.getCoordonneeZ())/(v1.norme() + v2.norme());
	}
	
	public static double arcCosTheta(double cosTheta) {
		return Math.toDegrees(Math.acos(cosTheta));
	}
	
}
