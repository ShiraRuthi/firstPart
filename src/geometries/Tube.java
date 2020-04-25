/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;

/**
 * @author Ruthi
 *
 */
public class Tube extends RadialGeometry 
{
	Ray _axisRay;

	public Tube(double _r,Ray _ray) {
		super(_r);
		// TODO Auto-generated constructor stub
		_axisRay=_ray;
	}
	
	public Vector getNormal(Point3D _p) {
		
		double t=(_axisRay.get_v().dotProduct(_p.subtract(_axisRay.get_p())));
		Point3D _O=_axisRay.get_p().add(_axisRay.get_v().scale(t));
		return new Vector(_p.subtract(_O));
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
