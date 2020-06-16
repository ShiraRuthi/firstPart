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
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	
	
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
		int nX = _imageWriter.getNx();
		int nY=_imageWriter.getNy();
		
		// i is pixel row number and j is pixel in the row number
		for(int i=0;i<nX;i++)
		{
			for(int j=0;j<nY;j++)
			{
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, _scene.get_distance(),_imageWriter.getWidth(), _imageWriter.getHeight());
				List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
				if( intersectionPoints==null)
				{
					_imageWriter.writePixel(j, i, background);
				}
				else
				{
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint,ray).getColor());
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
    
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
    	Color color =new  Color( calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0));
        color = color.add(_scene.get_ambientLight().get_intensity());
        return color;
    }
//
   private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
       if (level == 0 || k < MIN_CALC_COLOR_K) {
           return Color.BLACK;
       }

        Color result = geoPoint.geometry.get_emission();
        Point3D pointGeo = geoPoint.point;

        Vector v = pointGeo.subtract(_scene.get_camera().get_position()).normalize();
        Vector n = geoPoint.geometry.getNormal(pointGeo);

        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            //ray parallel to geometry surface ??
            //and orthogonal to normal
            return result;
        }

        Material material = geoPoint.geometry.get_material();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        double kr = geoPoint.geometry.get_material().get_kR();
        double kt = geoPoint.geometry.get_material().get_kT();
        double kkr = k * kr;
        double kkt = k * kt;

        result = result.add(getLightSourcesColors(geoPoint, k, result, v, n, nv, nShininess, kd, ks));

        if (kkr > MIN_CALC_COLOR_K) {
        	Ray reflectedRay = constructReflectedRay(pointGeo, inRay, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                result = result.add(new Color(calcColor(reflectedPoint, reflectedRay, level - 1, kkr)).scale(kr));
            }
        }
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(pointGeo, inRay, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                result = result.add(new primitives.Color( calcColor(refractedPoint, refractedRay, level - 1, kkt)).scale(kt));
            }
       }
       return result;
    }
    

/*	private Color calcColor(GeoPoint gp, Ray ray, int level, double k) {
		Color color = gp.geometry.get_emission();

		Vector v = gp.point.subtract(_scene.get_camera().get_position()).normalize();
		Vector n = gp.geometry.getNormal(gp.point);

		// check that view to point line is not tangent to the surface
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return color;

		Material material = gp.geometry.get_material();
		int nShininess = material.getnShininess();
		double kd = material.getKd();
		double ks = material.getKs();
		for (LightSource lightSource : _scene.getLightsSources()) {
			Vector l = lightSource.getL(gp.point);
			double nl = alignZero(n.dotProduct(l));
			if ((nl > 0 && nv > 0) || (nl < 0 && nv < 0)) {
				double ktr =// shadowWithTransparency ? transparency(lightSource, l, n, gp) :
					(unshaded(lightSource, l, n, gp) ? 1d : 0d);
				if (ktr > MIN_CALC_COLOR_K) {
					Color ip = lightSource.getIntensity(gp.point).scale(ktr);
					color = color.add(calcDiffusive(kd, nl, ip),
							calcSpecular(ks, l, n, nl, v, nShininess, ip));
				}
			}
		}

		if (level == 1)
			return color;

		double kr = material.get_kR(), kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K)
			color = color.add(calcColorRecursion(constructReflectedRay(n, nv, gp.point, ray), n, level, k, kr));
		double kt = material.get_kT(), kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K)
			color = color.add(calcColorRecursion(constructRefractedRay(n, gp.point, ray), n, level, k, kt));
		return color;
	}
*/


	/**
	 * Function to construct reflected ray from the given point in reflected from
	 * the surface direction of the original ray. The generated ray head will be
	 * moved across the normal line in ray's direction
	 * 
	 * @param n     normal to the geometry surface at the point
	 * @param point the desired point
	 * @param ray   the original ray
	 * @return new reflected ray
	 */
	private Ray constructReflectedRay(Vector n, double nv, Point3D point, Ray ray) {
		Vector v = ray.get_v();
		Vector r = v.add(n.scale(-2 * nv)); // nv must not be zero!
		return new Ray(point, r, n);
	}

	/**
	 * Bring some code which is common for refraction and reflection to a separate
	 * function here.
	 * 
	 * @param ray   refracted or reflected ray
	 * @param n     normal to the surface at the point
	 * @param level recursion level
	 * @param k     accumulated attenuation factor
	 * @param krt   given transparency or reflection attenuation factor
	 * @return the color effect from the reflection/refraction in the point
	 */
	private Color calcColorRecursion(Ray ray, Vector n, int level, double k, double krt) {
		double kkrt = k * krt;
		GeoPoint gp = findClosestIntersection(ray);
		return gp == null ? Color.BLACK : calcColor(gp, ray, level - 1, kkrt).scale(krt);
	}


    private Color getLightSourcesColors(GeoPoint geoPoint, double k, Color result, Vector v, Vector n, double nv, int nShininess, double kd, double ks) 
    {
        Point3D pointGeo = geoPoint.point;
        List<LightSource> lightSources = _scene.getLightsSources();
         if(lightSources != null)
         {
            for (LightSource lightSource : lightSources) {
                Vector l = lightSource.getL(pointGeo);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) {
               if (sign(nl) == sign(nv) && nl != 0 && nv != 0) {
                    if (unshaded(lightSource, l, n, geoPoint)) {
                    double ktr = transparency(lightSource, l, n, geoPoint);
                    if (ktr * k > MIN_CALC_COLOR_K) {
                        Color ip = lightSource.getIntensity(pointGeo).scale(ktr);
                        result = result.add(
                                calcDiffusive(kd, nl, ip),
                                calcSpecular(ks, l, n, nl, v, nShininess, ip));
              
            }}}
              
        }
        }
       
      }  
        return result;
    }
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        Point3D pointGeo = geopoint.point;

        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
        if (intersections == null) {
            return 1d;
        }
        double lightDistance = light.getDistance(pointGeo);
        double ktr = 1d;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(pointGeo) - lightDistance) <= 0) {
                ktr *= gp.geometry.get_material().get_kT();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0.0;
                }
            }
        }
        return ktr;
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
    /**
     * 
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    /*private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
    { 
    Vector lightDirection = l.scale(-1); // from point to light source
    Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA); 
    Point3D point = geopoint.point.add(delta); 
    Ray lightRay = new Ray(point, lightDirection);
    List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
    if (intersections==null) return true;
    double lightDistance = light.getDistance(geopoint.point);
    for (GeoPoint gp : intersections) 
    { if (alignZero(gp.point.distance(geopoint.point)- lightDistance) <= 0) 
    	return false;
    } 
    return true;

    }*/
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        Point3D pointGeo = geopoint.point;

        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        double lightDistance = light.getDistance(pointGeo);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(pointGeo) - lightDistance) <= 0
                    && gp.geometry.get_material().get_kT() == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @param ray
     * @return
     */

    	private GeoPoint findClosestIntersection(Ray ray) {

            if (ray == null) {
                return null;
            }

            GeoPoint closestPoint = null;
            double closestDistance = Double.MAX_VALUE;
            Point3D ray_p0 = ray.get_p();

            List<GeoPoint> intersections = _scene.get_geometries().findIntersections(ray);
            if (intersections == null)
                return null;

            for (GeoPoint geoPoint : intersections) {
                double distance = ray_p0.distance(geoPoint.point);
                if (distance < closestDistance) {
                    closestPoint = geoPoint;
                    closestDistance = distance;
                }
            }
            return closestPoint;
        }
    
    /**
     * 
     * @param pointGeo
     * @param inRay
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.get_v(), n);

    
    }

    /**
     * 
     * @param pointGeo
     * @param inRay
     * @param n
     * @return
     */
    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
     
    	  Vector v = inRay.get_v();
          double vn = v.dotProduct(n);

          if (vn == 0) {
              return null;
          }

          Vector r = v.subtract(n.scale(2 * vn));
          return new Ray(pointGeo, r, n);
        
    }

}