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
		this._v =null;
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
		return null;
	}
	public Vector getNormal() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
