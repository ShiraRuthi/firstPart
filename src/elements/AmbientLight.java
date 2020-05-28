/**
 * 
 */
package elements;
import primitives.*;
/**
 * @author Ruthi
 *
 */
public class AmbientLight extends Light {

	

	/**
	 * @param Ia
	 * @param Ka
	 */
	public AmbientLight(Color Ia,double Ka) {
		super(Ia.scale(Ka));
	}

	

	
	
}
