/**
 * 
 */
package geometries;

/**
 * @author Ruthi
 *
 */
public abstract class RadialGeometry implements Geometry 
{
 
	protected double _radius;
	
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
