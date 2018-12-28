package modele.structure;

import java.util.Comparator;

public class ComparatorZ implements Comparator<Face> {

	private Modele3D modele;

	public ComparatorZ(Modele3D modele) {
		this.modele = modele;
	}

	@Override
	public int compare(Face o1, Face o2) {
		double[] coordZ1 = modele.getCoordsFromFace(o1, Coordonnee.Z);
		double[] coordZ2 = modele.getCoordsFromFace(o2, Coordonnee.Z);
		double minZ1 = coordZ1[0];
		double minZ2 = coordZ2[0];
		for (int i = 1; i < coordZ1.length; i++) {
			if (minZ1 > coordZ1[i])
				minZ1 = coordZ1[i];
		}
		for (int i = 1; i < coordZ2.length; i++) {
			if (minZ2 > coordZ2[i])
				minZ2 = coordZ2[i];
		}
		if (minZ1 < minZ2)
			return -1;
		else if (minZ2 < minZ1)
			return 1;
		else
			return 0;
	}

}
