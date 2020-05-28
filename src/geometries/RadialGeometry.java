/**
 * 
 */
package geometries;

import primitives.Color;
import primitives.Material;

import static primitives.Util.isZero;
/**
 * @author Ruthi
 *
 */
public abstract class RadialGeometry extends Geometry 
{
 
	protected double _radius;
	
	
	 
	/**
	 * 
	 * @param _material
	 * @param emissionLight
	 * @param radius
	 */
	public RadialGeometry( Material _material,Color emissionLight, double radius) {
	       super(emissionLight,_material);
	       this._radius = radius;
	   }

	 /**
    *
    * @param emissionLight
    * @param radius
    */
   public RadialGeometry(Color emissionLight, double radius) {
       super(emissionLight);
       this._radius = radius;
   }

	/**
	 * @param _radius
	 */
	public RadialGeometry(double _radius) {
		super();
		this._radius = _radius;
	}
	public RadialGeometry(RadialGeometry _r) {
		super();
		this._radius = _r._radius;
	}

	/**
	 * @return the _radius
	 */
	public double get_radius() {
		return _radius;
	}

	@Override
	public String toString() {
		return "RadialGeometry [_radius=" + _radius + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RadialGeometry other = (RadialGeometry) obj;
		if (Double.doubleToLongBits(_radius) != Double.doubleToLongBits(other._radius))
			return false;
		return true;
	}

	 
	
}
