/**
 * 
 */
package geometries;

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

	public Tube(RadialGeometry _r) {
		super(_r);
		// TODO Auto-generated constructor stub
	}
	
	public Vector getNormal(Point3D _p)
	{
		return null;
	}

}
