/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Ruthi
 *
 */
public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		//----------------------------------Boundary Value-----------------------------
		
		//The list is empty
		Geometries g1=new Geometries();
		assertEquals("The list is empty",g1,new Geometries());
		
		//There are no intersections
		g1.add(new Triangle(new Point3D(1,-2,3),new Point3D(-1,-2,3),new Point3D(2,4,3)));
		assertEquals("There are no intersections",g1.findIntersections(new Ray(new Point3D(5,6,7),new Vector(5,8,3))),null);
		
		//There is one intersection
		assertEquals("There is one intersection",(g1.findIntersections(new Ray(new Point3D(0,-1,0),new Vector(0,0,5)))).size(),1);
		
		//all the geometries have an intersection
		g1.add(new Sphere(new Point3D(0,0,7),7));//,new Plane(new Point3D(5,6,7),new Point3D(5,6,7),new Point3D(5,6,7)),new Triangle(new Point3D(0,0,1),new Point3D(1,0,0), new Point3D(-1,0,0)));
		assertEquals(" all the geometries have an intersection",( g1.findIntersections(new Ray(new Point3D(0,-1,0),new Vector(0,0,12)))).size(),3);
		
		//-----------------------------------Equivalent Partitions-------------------------------------------
		
		//not all the geometries have an intersection
		g1.add(new Sphere(new Point3D(-8,11,-8),1));

		assertEquals("not all the geometries have an intersection",( g1.findIntersections(new Ray(new Point3D(0,-1,0),new Vector(0,0,12)))).size(),3);
	}

}
