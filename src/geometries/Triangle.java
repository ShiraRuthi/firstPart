/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.*;
import static primitives.Util.isZero;

import java.util.List;
/**
 * @author Ruthi
 *
 */
public class Triangle extends Polygon {

	/**
	 * 
	 * @param _color
	 * @param material
	 * @param vertices
	 */
	public Triangle(Color _color, Material material, Point3D...vertices) {
        super(material, _color, vertices);
    }

    
	/**
	 * @param vertices
	 * @param _color
	 */
	public Triangle(Color _color,Point3D... vertices) {
		super(_color,vertices);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param vertices
	 * @param _color
	 */
	public Triangle(Point3D... vertices) {
		super( vertices);
		// TODO Auto-generated constructor stub
	}
	@Override
	 public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray, maxDistance);
        if (planeIntersections == null) return null;

        Point3D p0 = ray.get_p();
        Vector v = ray.get_v();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2).normalized());
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3).normalized());
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1).normalized());
        if (isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            //for GeoPoint
            for (GeoPoint geo : planeIntersections) {
                geo.geometry = this;
            }
            return planeIntersections;
        }

        return null;

    }
   

	
}
