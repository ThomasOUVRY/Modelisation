package tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import modele.structure.Vecteur;

public class TestVecteur {
	
	double x = 4.0;
	double y = 2.0;
	double z = 4.0;
	Vecteur v = new Vecteur(x, y, z);
	
	double a = 3.0;
	double b = 4.0;
	double c = 0.0;
	Vecteur w = new Vecteur(a, b, c);
	
	double d = 1.0;
	double e = 0.0;
	double f = 1.0;
	Vecteur u = new Vecteur(d, e, f);
	
	double g = 2.0;
	double h = 0.0;
	double i = 1.0;
	Vecteur n = new Vecteur(g, h, i);
	
	@Test
	public void testNormal() {
		Vecteur expected = new Vecteur(-8.0, -12.0, 10.0);
		Vecteur actual = Vecteur.normal(v, w);
		assertEquals(expected.getCoordonneeX(), actual.getCoordonneeX(), 0.0);
		assertEquals(expected.getCoordonneeY(), actual.getCoordonneeY(), 0.0);
		assertEquals(expected.getCoordonneeZ(), actual.getCoordonneeZ(), 0.0);
	}
	
	@Test
	public void testCosTheta() {
		double expected = 1.81;
		double actual = Vecteur.cosTheta(v, w);
		assertEquals(expected, actual, 0.1);
	}
	
	@Test
	public void testArcCosTheta() {
		double expected = 34.73;
		double actual = Vecteur.arcCosTheta(Vecteur.cosTheta(u, n));
		assertEquals(expected, actual, 0.1);
	}
}