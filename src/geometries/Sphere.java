/**
 * 
 */
package geometries;
import java.util.List;
import static primitives.Util.alignZero;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 * @author Ruthi
 *
 */
public class Sphere extends RadialGeometry {
	
	private Point3D _center;
	
	
	public Sphere(Point3D _center1,double _r) {
		super(_r);
		_center= _center1;
		// TODO Auto-generated constructor stub
	}

	
	
	public Vector getNormal(Point3D _p)
	{
		return new Vector(_p.subtract(_center));
	}



	@Override
	public List<Point3D> findIntersections(Ray ray) {
		Point3D p0 = ray.get_p();
        Vector v = ray.get_v();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
	}

}
