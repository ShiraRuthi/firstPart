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
		
		Tube _t = new Tube(9,new Ray(new Point3D(1,1,1),new Vector(2,3,2)));
		assertEquals("Bad normal to tube",_t.getNormal(new Point3D(2,2,2)),new Vector(0.17647058823529393,-0.23529411764705888,0.17647058823529393));
		
	}

}

