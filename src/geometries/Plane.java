/**
 * 
 */
package geometries;
import primitives.Vector;
import primitives.Point3D;
/**
 * @author Ruthi
 *
 */
public class Plane 
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
		this._v = _v;
		this._p = _p;
	}
	public Plane(Point3D _p1,Point3D _p2,Point3D _p3)
	{
		super();
		Vector _v1 = _p3.subtract(_p2);
		Vector _v2 = _p2.subtract(_p1);
		this._v =_v1.crossProduct(_v2);
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
		return _v;
	}
	public Vector getNormal() {
		// TODO Auto-generated method stub
		return _v;
	}
	
}
