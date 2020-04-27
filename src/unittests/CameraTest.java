/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Ruthi
 *
 */
public class CameraTest {

	@Test
	public void testCamera()
	{
		Vector v1,v2,v3;
		v1 = (new Vector(1,0,0)).normalize();
		v2 =( new Vector(0,1,0)).normalize();
		assertEquals("Vetors are not orthogonal",v1.dotProduct(v2),0,0.0001);
		
		v3= (new Vector(v1.crossProduct(v2))).normalize();
		assertEquals("Vetor vUp is not normalized",v1.length(),1,0.0001);
		assertEquals("Vetor vTo is not normalized",v2.length(),1,0.0001);
		assertEquals("Vetor vRight is not normalized",v3.length(),1,0.0001);

		
	}
	
	 /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    @Test
    public void testConstructRayThroughPixel() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        
        // ============ Equivalence Partitions Tests ==============
        // TC01: 3X3 Corner (0,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
                camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6));
        
        // TC02: 4X4 Corner (0,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-3, -3, 10)),
                camera.constructRayThroughPixel(4, 4, 0, 0, 10, 8, 8));

        // TC03: 4X4 Side (0,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -3, 10)),
                camera.constructRayThroughPixel(4, 4, 1, 0, 10, 8, 8));

        // TC04: 4X4 Inside (1,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -1, 10)),
                camera.constructRayThroughPixel(4, 4, 1, 1, 10, 8, 8));
        
        // =============== Boundary Values Tests ==================
        // TC11: 3X3 Center (1,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, 0, 10)),
                camera.constructRayThroughPixel(3, 3, 1, 1, 10, 6, 6));

        // TC12: 3X3 Center of Upper Side (0,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -2, 10)),
                camera.constructRayThroughPixel(3, 3, 1, 0, 10, 6, 6));

        // TC13: 3X3 Center of Left Side (1,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, 0, 10)),
                camera.constructRayThroughPixel(3, 3, 0, 1, 10, 6, 6));
        
    }

}
