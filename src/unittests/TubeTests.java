/**
 * 
 */
package unittests;
import geometries.*;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Ruthi
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		
		Tube tube = new Tube(1.0, new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0)));
	       assertEquals("Bad normal to tube", new Vector(0, 0, 1), tube.getNormal(new Point3D(0, 0.5, 2)));
	}

}

