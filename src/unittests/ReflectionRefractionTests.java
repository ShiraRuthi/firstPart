/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.*;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author Ruthi
 *
 */
public class ReflectionRefractionTests {

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
						new Point3D(0, 0, 50)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

		scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
				0.0004, 0.0000006));

		ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	/**
	 * our incredibe picture
	 */
	@Test
	public void ourIncrediblePicture()
	{
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)),
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 30, new Point3D(0, 0, 115)),
				new Triangle(new Color(java.awt.Color.MAGENTA), new Material(0.8, 0.2, 300),
						new Point3D(-100, 100, 100), new Point3D(100, 100, 100), new Point3D(75, -75, 150)),
				new Polygon( new Material(0.8, 0.2, 300),new Color(java.awt.Color.green),
						new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150),new Point3D(-75, -75, 150)));

        scene.addLights(new SpotLight(new Color(450, 250, 0), new Point3D(-80, 90, -50),new Vector(3, -2, 5), 1, 0.001, 0.0001));
        scene.addLights(new PointLight(new Color(450, 250, 0), new Point3D(100, -250, -100), 1, 0.00001, 0.000001));
       scene.addLights(new DirectionalLight(new Color(450, 250, 0), new Vector(8, -8, 1)));


		ImageWriter imageWriter = new ImageWriter("incrediblePicture", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	/**
	 * our stunning picture
	 */
	@Test
	public void ourStunningPicture()
	{
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(new Color(java.awt.Color.BLACK));
		scene.set_ambientLight(new AmbientLight(new Color(255, 255, 255), 0.1));


        scene.addGeometries(	
		new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				10, new Point3D(-80, -80, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
        					5, new Point3D(-90, -70, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				7, new Point3D(-85, -60, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
    					4, new Point3D(-90, -51, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
            					9, new Point3D(-75, -50, -30)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				7, new Point3D(-90, -40, -50)),
        /////////////////////////////////
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				10, new Point3D(-80, -35, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
        					5, new Point3D(-90, -25, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				7, new Point3D(-85, -10, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
    					4, new Point3D(-90, -1, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
            					15, new Point3D(-70, 8, -30)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				8.5, new Point3D(-70, -20, -20)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				20, new Point3D(-80, 40, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				15, new Point3D(-48, 30, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				25, new Point3D(-60, 75, -100)), 
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				20, new Point3D(-25, 75, -20)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				15, new Point3D(12, 80, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				9, new Point3D(-40, 45, -150)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				14, new Point3D(35,90 , 0)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0, 0), // )
				8, new Point3D(-10, 90, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				8, new Point3D(-90, 10, -50)),
        new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
				8, new Point3D(-95, -90, -50))        );

scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 
		   new Vector(-1, 1, 4), 1, 0.00001, 0.000005));
    
       scene.addLights(new SpotLight(new Color(450, 250, 0), new Point3D(-80, 90, -50),new Vector(10, -2, 5), 1, 0.001, 0.0001));
       scene.addLights(new PointLight(new Color(450, 250, 0), new Point3D(100, 0,-250), 1, 0.00001, 0.000001));
       scene.addLights(new DirectionalLight(new Color(new Color(450,250,0)), new Vector(-80, 85, 100000)));


       scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 
		   new Vector(-1, 1, 4), 1, 0.00001, 0.000005));
		ImageWriter imageWriter = new ImageWriter("stunningPicture", 200, 200, 600, 600);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}


	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(10000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.addGeometries(
				new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
				new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

		scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 
				   new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
						30, new Point3D(60, -50, 50)));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
}
