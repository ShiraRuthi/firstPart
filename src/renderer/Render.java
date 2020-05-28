/**
 * 
 */
package renderer;
import static  primitives.Util.alignZero;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * @author Ruthi
 *
 */
public class Render {

	ImageWriter _imageWriter;
	Scene _scene;
	
	public Render(ImageWriter imageWriter, Scene scene) {
		_imageWriter = imageWriter;
		_scene = scene;
	}
	/**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
    	Camera camera = _scene.get_camera();
        Intersectable geometries = _scene.get_geometries();
        java.awt.Color background = _scene.get_background().getColor();
        AmbientLight ambientLight = _scene.get_ambientLight();
        double distance = _scene.get_distance();

        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();

        for (int row = 0; row < Ny; row++) {
            for (int collumn = 0; collumn < Nx; collumn++) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, collumn, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(collumn, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(collumn, row, calcColor(closestPoint).getColor());
                }
            }
         }
    }
    /**
     * 
     *
     * @param intersectionPoints list of points, the function should find from
     *                           this list the closet point of the camera in the scene.
     * @return the closest point to the camera
     */

    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
    	 GeoPoint result = null;
         double mindist = Double.MAX_VALUE;

         Point3D p0 = this._scene.get_camera().get_position();

         for (GeoPoint geo : intersectionPoints) {
             Point3D pt = geo.point;
             double distance = p0.distance(pt);
             if (distance < mindist) {
                 mindist = distance;
                 result = geo;
             }
         }
         return result;
    }

     
    
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    private Color calcColor(GeoPoint intersection) { 

    	        List<LightSource> lightSources = _scene.getLightsSources();
    	        Color result = _scene.get_ambientLight().get_intensity();
    	        result = result.add(intersection.geometry.get_emission());

    	        Vector v = intersection.point.subtract(_scene.get_camera().get_position()).normalize();
    	        Vector n = intersection.geometry.getNormal(intersection.point);

    	        Material material = intersection.geometry.get_material();
    	        int nShininess = material.getnShininess();
    	        double kd = material.getKd();
    	        double ks = material.getKs();

    	        if (lightSources != null) {
    	            for (LightSource lightSource : lightSources) {
    	                Vector l = lightSource.getL(intersection.point);
    	                double nl = alignZero(n.dotProduct(l));
    	                double nv = alignZero(n.dotProduct(v));

    	                if (sign(nl) == sign(nv)) {
    	                    Color ip = lightSource.getIntensity(intersection.point);
    	                    result = result.add(
    	                            calcDiffusive(kd, nl, ip),
    	                            calcSpecular(ks, l, n, nl, v, nShininess, ip));
    	                }
    	            }
    	        }

    	        return result;
    	    }
/**
 * 
 * @param ks
 * @param l
 * @param n
 * @param nl
 * @param v
 * @param nShininess
 * @param ip
 * @return
 */
private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
	double p = nShininess;

    Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
    double minusVR = -alignZero(R.dotProduct(v));
    if (minusVR <= 0) {
        return Color.BLACK; // view from direction opposite to r vector
    }
    // [rs,gs,bs](-V.R)^p
    return ip.scale(ks * Math.pow(minusVR, p));
}

/**
 * 
 * @param kd
 * @param nl
 * @param ip
 * @return
 */


  private Color calcDiffusive(double kd, double nl, Color ip) 
  {
    if (nl < 0) {
        nl = -nl;
    }
    return ip.scale(nl * kd);
      }
  
private boolean sign(double val) {
    return (val > 0d);
}  
    
    /**
     * 
     * @param interval
     * @param colorsep
     */
    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNy();
        double collumns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int collumn = 0; collumn < collumns; ++collumn) {
                if (collumn % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(collumn, row, colorsep);
                }
            }
        }
    }
}