/**
 * 
 */
package geometries;
import primitives.Point3D;
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
}
