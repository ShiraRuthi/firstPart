/**
 * 
 */
package geometries;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
/**
 * @author Ruthi
 *
 */
public class Plane implements Geometry
{
	private Vector _v;
	private Point3D _p;
	/**
	 * @param _v
	 * @param _p
	 */
	public Plane(Vector _v, Point3D _p) 
	{
		super();
		this._v = _v.normalize();
		this._p = _p;
	}
	public Plane(Point3D _p1,Point3D _p2,Point3D _p3)
	{
		super();
		Vector _v1 = _p1.subtract(_p2);
		Vector _v2 = _p1.subtract(_p3);
		this._v =_v1.crossProduct(_v2).normalize();
		this._p = _p1;
	}
	@Override
	public String toString() {
		return "Plane [_v=" + _v + ", _p=" + _p + "]";
	}
	/**
	 * @return the _v
	 */
	public Vector get_v() {
		return _v;
	}
	/**
	 * @return the _p
	 */
	public Point3D get_p() {
		return _p;
	}
	
	public Vector getNormal(Point3D _p)
	{
		return ( _v);
	}
	public Vector getNormal() {
		// TODO Auto-generated method stub
		return ( _v);
	}
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		 Vector p0Q;
	        try {
	            p0Q = _p.subtract(ray.get_p());
	        } catch (IllegalArgumentException e) {
	            return null; // ray starts from point Q - no intersections
	        }

	        double nv = _v.dotProduct(ray.get_v());
	        if (isZero(nv)) // ray is parallel to the plane - no intersections
	            return null;

	        double t = alignZero(_v.dotProduct(p0Q) / nv);

	        return t <= 0 ? null : List.of(ray.getTargetPoint(t));
	}
    
	
}
