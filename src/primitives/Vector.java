/**
 * 
 */
package primitives;

/**
 * @author Ruthi
 *
 */
public class Vector {

	private Point3D _head;
	
	/**
	 * @return the _head
	 */
	public Point3D get_head() {
		return _head;
	}
	/**
	 * @param _x
	 * @param _y
	 * @param _z
	 */
	public Vector(Coordinate _x, Coordinate _y, Coordinate _z) {
		super();
		if(new Point3D(_x, _y, _z).equals(Point3D.ZERO))
			throw new IllegalArgumentException("you cant define the vector 0"); 
		_head = new Point3D(_x, _y, _z);
		
	}
	/**
	 * @param _x
	 * @param _y
	 * @param _z
	 */
	public Vector(double _x, double _y,double _z) {
		super();
		if(new Point3D(_x, _y, _z).equals(Point3D.ZERO))
			throw new IllegalArgumentException("you cant define the vector 0"); 
		_head = new Point3D(_x, _y, _z);
	}
	
	public Vector( Point3D _p)
	{
		super();
		if( new Point3D(_p.get_x(), _p.get_y(), _p.get_z()).equals(Point3D.ZERO))
			throw new IllegalArgumentException("you cant define the vector 0"); 
		_head = new Point3D(_p.get_x(), _p.get_y(), _p.get_z());
	}
	public Vector( Vector  _v)
	{
		super();
		_head = new Point3D(_v._head.get_x(),_v._head.get_y(),_v._head.get_z());
	}
	
	public Vector subtract(Vector _v)
	{
		return new Vector(_head.subtract(_v._head));
	}
	public Vector add(Vector _v)
	{
		return new Vector(_head.add(_v));
	}
	public Vector scale(double l)
	{
		return new Vector(l*_head.get_x().get(),l*_head.get_y().get(),l*_head.get_z().get());
	}
	@Override
	public String toString() {
		return "Vector [_head=" + _head + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (_head == null) {
			if (other._head != null)
				return false;
		} else if (!_head.equals(other._head))
			return false;
		return true;
	}
	public double 	lengthSquared()
	{
		return ((_head.get_x().get())*(_head.get_x().get())+(_head.get_y().get())*(_head.get_y().get())+(_head.get_z().get())*(_head.get_z().get()));
	}
	public double 	length()
	{
		return Math.sqrt(lengthSquared());
	}
	public double dotProduct(Vector _other)
	{
	    return this.get_head().get_x().get()*_other.get_head().get_x().get()+this.get_head().get_y().get()*_other.get_head().get_y().get()+this.get_head().get_z().get()*_other.get_head().get_z().get();
	}
	public Vector crossProduct(Vector v) 
	{	
		return new Vector(this._head.get_y().get()*v._head.get_z().get()-this._head.get_z().get()*v._head.get_y().get(),
				 this._head.get_z().get()*v._head.get_x().get()-this._head.get_x().get()*v._head.get_z().get(),
				 this._head.get_x().get()*v._head.get_y().get()-this._head.get_y().get()*v._head.get_x().get());

	}
	public Vector normalize()
	{
		this._head = this.scale(1/length())._head;
		return this;
		
	}
	public Vector normalized()
	{
		return new Vector(this.normalize());
		
	}
	
}
