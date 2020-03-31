/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

import primitives.*;

import org.junit.Test;

import primitives.Vector;

/**
 * @author Ruthi
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		
        // ============ Equivalence Partitions Tests ==============
		assertEquals("wrong result -subtract function",new Vector(2, 4, 6).subtract(new Vector(1, 2, 3)),new Vector(1, 2, 3));
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		
		 // ============ Equivalence Partitions Tests ==============
		assertEquals("wrong result -add function",new Vector(1, 2, 3).add(new Vector(1, 2, 3)),new Vector(2, 4, 6));
		
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		
		 // ============ Equivalence Partitions Tests ==============
		assertEquals("wrong result -scale function",new Vector(1, 2, 3).scale(2),new Vector(2, 4, 6));
		
		// =============== Boundary Values Tests ==================
        try {
        	new Vector(0, 1, 4).scale(0);
            fail("scale()  didn't throw an exception for creating zero vector");
        } catch (IllegalArgumentException e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		
		// ============ Equivalence Partitions Tests ==============
        assertEquals("wrong result -length function",new Vector(1, 2, 3).lengthSquared(),14 , 0.00001);
       
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		
		// ============ Equivalence Partitions Tests ==============
        assertEquals("wrong result -length function",new Vector(0, 3, 4).length(),5 , 0.00001);
      
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(0, 3, -2);
		
		// ============ Equivalence Partitions Tests ==============
        assertEquals("dotProduct() for orthogonal vectors is not zero", v1.dotProduct(v3), 0, 0.00001);
        assertEquals("dotProduct() wrong value", v1.dotProduct(v2) , -28, 0.00001);


	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		
		// ============ Equivalence Partitions Tests ==============
		 assertEquals("wrong result -Normalize function",new Vector(1, 2, 3).normalize().length(),1 , 0.00001);
	     
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
		Vector u = v.normalized();
		
		// ============ Equivalence Partitions Tests ==============
		assertNotSame("wrong result -Normalized function",v,u );
        
	}

}
