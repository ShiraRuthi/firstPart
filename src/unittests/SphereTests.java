/**
 * 
 */
package unittests;
import geometries.*;
import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Polygon;
import primitives.Point3D;
import primitives.Vector;

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

}
