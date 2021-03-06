package tests;
import modele.structure.ComparatorZ;
import modele.structure.Face;
import modele.structure.Modele3D;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestComparator {


    @Test
    public void testComparatorZ() {
        Modele3D m = new Modele3D(4,2);
        double[][] ma = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,1,1,1}};
        m.setMatricePoint(ma);
        Face f1 = new Face(3);
        f1.addPointID(0);
        f1.addPointID(1);
        f1.addPointID(2);
        Face f2 = new Face(3);
        f2.addPointID(1);
        f2.addPointID(2);
        f2.addPointID(3);
        ComparatorZ c = new ComparatorZ(m);
        assertEquals(-1, c.compare(f1, f2));

        Modele3D m2 = new Modele3D(4,2);
        double[][] ma2 = {{10,10,10,10},{10,3,2,10},{10,10,10,10},{1,1,1,1}};
        m2.setMatricePoint(ma2);
        ComparatorZ c2 = new ComparatorZ(m2);
        assertEquals(0, c2.compare(f1, f2));

        Modele3D m3 = new Modele3D(4,2);
        double[][] ma3 = {{4,3,2,1},{4,3,2,1},{4,3,2,1},{1,1,1,1}};
        m3.setMatricePoint(ma3);
        ComparatorZ c3 = new ComparatorZ(m3);
        assertEquals(1 , c3.compare(f1, f2));


    }
}
