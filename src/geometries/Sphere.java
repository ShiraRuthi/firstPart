/**
 * 
 */
package geometries;
import java.util.List;
import static primitives.Util.alignZero;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 * @author Ruthi
 *
 */
public class Sphere extends RadialGeometry {
	
	private Point3D _center;
	
	/**
	 * 
	 * @param _color
	 * @param material
	 * @param radius
	 * @param center
	 */
	 public Sphere(Color _color, Material material, double radius, Point3D center) {
	        super(material, _color, radius);
	        _center = new Point3D(center);

	    }


	 /**
	  * 
	  * @param _color
	  * @param radius
	  * @param center
	  */
	public Sphere(Color _color, double radius, Point3D center) {
	       this(_color,new Material(0,0,0),radius,center);
	    }
	/**
	 * 
	 * @param _center1
	 * @param _r
	 */
	public Sphere(Point3D _center1,double _r) {
        this(Color.BLACK,new Material(0,0,0),_r,_center1);
	}

	
	
	public Vector getNormal(Point3D _p)
	{
		return new Vector(_p.subtract(_center).normalized());
	}



	@Override
	public List<GeoPoint> findIntersections(Ray ray,double maxDistance) {

		Point3D p0 = ray.get_p();
        Vector v = ray.get_v();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(this._radius))));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this._radius * this._radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        double t1dist = alignZero(maxDistance - t1);
        double t2dist = alignZero(maxDistance - t2);

        if (t1 <= 0 && t2 <= 0) {
            return null;
        }

        if (t1 > 0 && t2 > 0) {
            if (t1dist > 0 && t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getTargetPoint(t1))),
                        new GeoPoint(this, (ray.getTargetPoint(t2)))); //P1 , P2
            } else if (t1dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getTargetPoint(t1))));
            } else if (t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getTargetPoint(t2))));
            }
        }

        if ((t1 > 0) && (t1dist > 0))
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t1))));
        else if ((t2 > 0) && (t2dist > 0))
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t2))));
        return null;
	}

}
