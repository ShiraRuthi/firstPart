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
public interface Geometry extends Intersectable {

	public Vector getNormal(Point3D _p);
	
}
