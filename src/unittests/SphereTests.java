/**
 * 
 */
package unittests;
import geometries.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Polygon;
import primitives.Point3D;
import primitives.Vector;
import primitives.*;

/**
 * @author Ruthi
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		 // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere s = new Sphere(new Point3D(0, 0, 1),9);
        
        assertEquals("Bad normal to sphere",s.getNormal(new Point3D(1,1,1)),new Vector(1,1,0));
    
	}


	    @Test
	    public void testFindIntersections()
	    {
	        Sphere sphere = new Sphere( new Point3D(1, 0, 0), 1d);

	        // ============ Equivalence Partitions Tests ==============

	        // TC01: Ray's line is outside the sphere (0 points)
	        assertEquals("Ray's line out of sphere", null,
	                        sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

	        // TC02: Ray starts before and crosses the sphere (2 points)
	        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
	        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
	    }
	    
}
