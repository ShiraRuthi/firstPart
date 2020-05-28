/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author Ruthi
 *
 */
public interface LightSource {
	
	/**
	 * @param p-point
	 *
	 */
	public Color getIntensity(Point3D p);
	/**
	 * @param p-point
	 *
	 */
	public Vector getL(Point3D p);


}
