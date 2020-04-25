/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * @author Ruthi
 *
 */
public interface Intersectable {
	List<Point3D> findIntersections(Ray ray);

}
