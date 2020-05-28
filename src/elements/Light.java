/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author Ruthi
 *
 */
 class Light {
	 
	protected Color _intensity;

	
	/**
	 * @param _intensity
	 */
	public Light(Color _intensity) {
		super();
		this._intensity = _intensity;
	}


	/**
	 * @return the _intensity
	 */
	public Color get_intensity() {
		return _intensity;
		}

}
