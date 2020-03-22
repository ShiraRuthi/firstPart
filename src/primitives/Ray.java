/**
 * 
 */
package primitives;

/**
 * @author Ruthi
 *
 */
public class Ray {
	private Point3D _p;
	private Vector _v;
	/**
	 * @return the _p
	 */
	public Point3D get_p() 
	{
		return _p;
	}
	/**
	 * @return the _v
	 */
	public Vector get_v() {
		return _v;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (_p == null) {
			if (other._p != null)
				return false;
		} else if (!_p.equals(other._p))
			return false;
		if (_v == null) {
			if (other._v != null)
				return false;
		} else if (!_v.equals(other._v))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ray [_p=" + _p + ", _v=" + _v + "]";
	}
	/**
	 * @param _p
	 * @param _v
	 */
	public Ray(Point3D _p, Vector _v) {
		super();
		this._p = _p;
		this._v = _v.normalized();
	}

	

}
