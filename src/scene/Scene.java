/**
 * 
 */
package scene;

import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * @author Ruthi
 *
 */
public class Scene 
{
	
	private final String _name;
	private Color _background;
    private final Geometries _geometries;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;
    
    /**
	 * @param _name
	 */
	public Scene(String _name) {
		super();
		this._name = _name;
		_geometries = new Geometries();
	}

	
	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}
	/**
	 * @return the _background
	 */
	public Color get_background() {
		return _background;
	}
	/**
	 * @return the _geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}
	/**
	 * @return the _camera
	 */
	public Camera get_camera() {
		return _camera;
	}
	/**
	 * @return the _distance
	 */
	public double get_distance() {
		return _distance;
	}
	/**
	 * @return the _ambientLight
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}
	/**
	 * @param _background the _background to set
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}
	/**
	 * @param _camera the _camera to set
	 */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}
	/**
	 * @param _distance the _distance to set
	 */
	public void set_distance(double _distance) {
		this._distance = _distance;
	}
	/**
	 * @param _ambientLight the _ambientLight to set
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}

	public void addGeometries(Intersectable... geometries) 
	{
		 for (Intersectable i : geometries) {
	            _geometries.add(i);
	            }
		
	}



   
}
