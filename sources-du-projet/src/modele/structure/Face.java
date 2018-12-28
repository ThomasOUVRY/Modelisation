package modele.structure;

/**
 * La classe "Face" définie une face à partir de ponts
 * 
 * @author poulyt
 *
 */
public class Face {

	/**
	 * Un tableau pour les ID des points par rappot au num�ro de colonne de la matrice principale situ�e dans le mod�le
	 */
	private int[] tabIDPoint;

	public Face(int nbPoints) {
		super();
		tabIDPoint = new int[nbPoints];
		for (int i = 0; i < tabIDPoint.length; i++) {
			tabIDPoint[i] = -1;
		}
	}
	

	/**
	 * Getter de l'attribut "tabPoint"
	 * 
	 * @return
	 */
	public int[] getTabPoint() {
		return tabIDPoint;
	}
	

	/**
	 * Ajoute un point au paramètre "tabPoint" pour ajouter un pont à la face
	 * 
	 * @param idPoint
	 * @return tableau d'id de points
	 */
	public int[] addPointID(int idPoint) {
		int i;
		for (i = 0; i < tabIDPoint.length && tabIDPoint[i] != -1; i++) {
		}
		tabIDPoint[i] = idPoint;
		return tabIDPoint;
	}
	

	/**
	 * Retourne un string de tout les points de la face
	 */
	public String toString() {
		String res = "";
		for (Integer i : tabIDPoint) {
			res += i + " ";
		}
		return res;
	}
}
