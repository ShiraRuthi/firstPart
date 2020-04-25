/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Ruthi
 *
 */
public class Cylinder extends Tube 
{
	private double _height;
	
	/** Cylinder constructor
    *
    * @param _radius
    * @param _ray
    * @param _height
    */
   public Cylinder(double _radius, Ray _ray, double _height) {
       super(_radius, _ray);
       this._height = _height;
   }
	public Vector getNormal(Point3D _p)
	{
		return null;
	}
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		return super.findIntersections(ray);
	}
}
