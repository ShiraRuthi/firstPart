/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Color;
import primitives.Material;
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
	/**
	 * 
	 * @param _material
	 * @param emmissionLight
	 * @param radius
	 * @param ray
	 * @param _height
	 */
	public Cylinder(Material _material,Color _color, double radius, Ray ray, double _height) {
        super(_material,_color,radius,ray);
        this._height = _height;
    }

	
	/**
	 * 
	 * @param emmissionLight
	 * @param radius
	 * @param ray
	 * @param _height
	 */
	public Cylinder(Color _color, double radius, Ray ray, double _height) {
        super(_color,radius,ray);
        this._height = _height;
    }

   
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
	public List<GeoPoint> findIntersections(Ray ray) {
		return this.findIntersections(ray);
	}
}
