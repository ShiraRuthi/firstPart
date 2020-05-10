/**
 * 
 */
package elements;
import primitives.*;
/**
 * @author Ruthi
 *
 */
public class AmbientLight {

	Color  _intensity;

	/**
	 * @param _intensity
	 */
	public AmbientLight(Color Ia,double Ka) {
		super();
		this._intensity = Ia.scale(Ka);
	}

	/**
	 * @return the _intensity
	 */
	public Color get_intensity() {
		return _intensity;
	}

	
	
}
