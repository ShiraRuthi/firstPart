/**
 * 
 */
package primitives;

/**
 * @author Ruthi
 *
 */
public class Point3D {
	
	
	
	
	private Coordinate _x;
	private Coordinate _y;
	private Coordinate _z;
	public static final Point3D ZERO = new Point3D (0.0,0.0,0.0);
	
	/**
	 * @param _x
	 * @param _y
	 * @param _z
	 */
	public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
		super();
		this._x = _x;
		this._y = _y;
		this._z = _z;
	}
	/**
	 * @param _x
	 * @param _y
	 * @param _z
	 */
	public Point3D(double _x, double _y,double _z) {
		super();
		this._x = new Coordinate (_x);
		this._y = new Coordinate (_y);
		this._z = new Coordinate (_z);
	}
	public Point3D( Point3D _p)
	{
		super();
		this._x = _p._x;
		this._y = _p._y;
		this._z = _p._z;
	}
	
	public Vector subtract(Point3D _p2)
	{
		return new Vector(_x._coord-_p2._x._coord,_y._coord-_p2._y._coord,_z._coord-_p2._z._coord);
	}
	
	public Point3D add(Vector _v)
	{
		return new Point3D(_v.get_head()._x._coord+_x._coord,_v.get_head()._y._coord+_y._coord,_v.get_head()._z._coord+_z._coord);
	}
	
	public double distance(Point3D _p)
	{
		return Math.sqrt(distanceSquared(_p));
	}

	public double 	distanceSquared(Point3D _p)
	{
		double _a = (_p._x.get() - _x.get())*(_p._x.get() - _x.get());
		double _b = (_p._y.get() - _y.get())*(_p._y.get() - _y.get());
		double _c = (_p._z.get() - _z.get())*(_p._z.get() - _z.get());
		return (_a+_b+_c);
	}
	
	/**
	 * @return the _x
	 */
	public Coordinate get_x() {
		return _x;
	}
	/**
	 * @return the _y
	 */
	public Coordinate get_y() {
		return _y;
	}
	/**
	 * @return the _z
	 */
	public Coordinate get_z() {
		return _z;
	}
	
	@Override
	public String toString() {
		return "Point3D [_x=" + _x + ", _y=" + _y + ", _z=" + _z + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (_x == null) {
			if (other._x != null)
				return false;
		} else if (!_x.equals(other._x))
			return false;
		if (_y == null) {
			if (other._y != null)
				return false;
		} else if (!_y.equals(other._y))
			return false;
		if (_z == null) {
			if (other._z != null)
				return false;
		} else if (!_z.equals(other._z))
			return false;
		return true;
	}
	
}
