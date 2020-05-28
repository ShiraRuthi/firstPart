/**
 * 
 */
package geometries;
import primitives.Vector;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
/**
 * @author Ruthi
 *
 */
public abstract class Geometry implements Intersectable {

	protected Color _emission;
	protected Material _material;
	
	/**
	 * @return the _material
	 */
	public Material get_material() {
		return _material;
	}


	/**
	 * @param _emission
	 * @param _material
	 */
	public Geometry(Color _emission, Material _material) {
		super();
		this._emission = _emission;
		this._material = _material;
	}


	/**
	 * @param _emission
	 */
	public Geometry(Color _emission) {
		
		this(new Color(_emission),new Material(0, 0, 0));
	}


	/**
	 * 
	 */
	public Geometry() {
		this(Color.BLACK,new Material(0, 0, 0));
	}
	
	/**
	 * @return the _emission
	 */
	public Color get_emission() {
		return _emission;
	}
	
	public abstract Vector getNormal(Point3D _p);
}
