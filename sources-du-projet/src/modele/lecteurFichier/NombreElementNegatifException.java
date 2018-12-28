
package modele.lecteurFichier;
/**
 * Gère l'exception: "Le nombre d'elements est negatif"
 * @author thoma
 *
 */
public class NombreElementNegatifException extends Exception {

	public NombreElementNegatifException() {
		super("Le nombre d'élément ne peut pas être négatif");
	}
}
