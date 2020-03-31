/**
 * 
 */
package unittests;
import primitives .*;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Vector;

/**
 * @author Ruthi
 *
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		
		// ============ Equivalence Partitions Tests ==============
		assertEquals("wrong result -subtract function",new Point3D(2, 4, 6).subtract(new Point3D(1, 2, 3)),new Vector(1, 2, 3));
       
	}

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Point3D)}.
	 */
	@Test
	public void testAdd() {
		
		// ============ Equivalence Partitions Tests ==============
		assertEquals("wrong result -add function",new Point3D(1, 2, 3).add(new Vector(1, 2, 3)),new Point3D(2, 4, 6));
	}

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {
		
		// ============ Equivalence Partitions Tests ==============
		assertEquals("wrong result -distance function",new Point3D(1, 2, 3).distance(new Point3D(4, 5, 6)),5.196152423,0.00001);
       
	}

	/**
	 * Test method for {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
	 */
	@Test
	public void testDistanceSquared() {
		
		// ============ Equivalence Partitions Tests ==============
		assertEquals("wrong result -DistanceSquared function",new Point3D(1, 2, 3).distanceSquared(new Point3D(4,5,6)),27,0.00001);
      
	}

}
