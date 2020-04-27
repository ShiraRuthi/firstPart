/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.Camera;
import primitives.*;
import geometries.*;

/**
 * @author Ruthi
 *
 */
public class IntegrationCameraTest {

	 Camera cam1 = new Camera(Point3D.ZERO,new Vector(0, 0, 1), new Vector(0, -1, 0));
	 Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
	 Camera cam3 = new Camera(new Point3D(0, 0,3), new Vector(0, 0, 1), new Vector(0, -1, 0));

	 //The sphere is parallel to the camera - 2 intersections points.
	  @Test
	  public  void constructRayThroughPixelWithSphere1() {
	        Sphere sph =  new Sphere(new Point3D(0, 0, 3), 1);
	       List<Point3D> results;
	        int count = 0;
	        int Nx =3;
	        int Ny =3;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",2,count);
	        System.out.println("count: "+count);

	    }
	//The view plane of the camera is inside the sphere - 2 intersections points.
	 @Test
	 public   void constructRayThroughPixelWithSphere2() {
	        Sphere sph =  new Sphere(new Point3D(0, 0, 2.5), 2.5);

	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",18,count);
	        System.out.println("count: "+count);
	    }
	//The view plane of the camera is half inside the sphere-10 intersections points.
	 @Test
	 public   void constructRayThroughPixelWithSphere3() {
	        Sphere sph =  new Sphere(new Point3D(0, 0, 2), 2);

	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",10,count);
	        System.out.println("count: "+count);
	   }
	//The view plane of the camera is totally inside the sphere-9 intersections points.
	 @Test
	 public   void constructRayThroughPixelWithSphere4() {
	        Sphere sph =  new Sphere(new Point3D(0, 0, 4), 4);

	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = sph.findIntersections(cam3.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",9,count);
	        System.out.println("count: "+count);
	   }
	//The sphere is behind the view plane of the camera -0 intersections points.
	 @Test
	 public   void constructRayThroughPixelWithSphere5() {
	        Sphere sph =  new Sphere(new Point3D(0, 0, -1), 0.5);

	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",0,count);
	        System.out.println("count: "+count);
	   }
	 //The plane is parallel to the view plane of the camera - 9 intersections points.
	 @Test
	 public   void constructRayThroughPixelWithPlane1() {
		 Plane plane = new Plane(new Point3D(0,0,3),new Point3D(1,-1,3),new Point3D(0,2,3));
	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = plane.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",9,count);
	        System.out.println("count: "+count);
	   }
	 //The plane is slant to the view plane of the camera(big incline)  - 9 intersections points.
	 @Test
	 public   void constructRayThroughPixelWithPlane2() {
		 Plane plane = new Plane(new Point3D(5,0,3),new Point3D(1,1,1),new Point3D(0,9,0));
	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = plane.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",9,count);
	        System.out.println("count: "+count);
	   }
	 //The plane is slant to the view plane of the camera(small incline) - 6 intersections points.
	 @Test
	 public   void constructRayThroughPixelWithPlane3() {
		 Plane plane = new Plane(new Point3D(0,0,3),new Point3D(1,1,1),new Point3D(0,3,0));
	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = plane.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",6,count);
	        System.out.println("count: "+count);
	   }
	 //The triangle is parallel to the view plane of the camera and shorter - 1 intersections points
	 @Test
	 public   void constructRayThroughPixelWithTriangle1() {
		 Triangle triangle = new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = triangle.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",1,count);
	        System.out.println("count: "+count);
	   }
	 //The triangle is parallel to the view plane of the camera and longer - 1 intersections points
	 @Test
	 public   void constructRayThroughPixelWithTriangle2() {
		 Triangle triangle = new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
	        List<Point3D> results;
	        int count = 0;
	        // TODO explanations
	        int Nx =3;
	        int Ny =3;

	        // TODO explanations
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                results = triangle.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
	                if (results != null)
	                    count += results.size();
	            }
	        }

	        assertEquals("too bad",2,count);
	        System.out.println("count: "+count);
	   }
}
