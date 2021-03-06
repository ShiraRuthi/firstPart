/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;

/**
 * @author Ruthi
 *
 */
public class Geometries implements Intersectable{
	private List<Intersectable> _geometriesList;
	
	
	/**
	 * @param _geometriesList
	 */
	public Geometries() {
		super();
		this._geometriesList = new ArrayList<Intersectable>();
	}
	
	/**
	 * @param _geometriesList
	 */
	public Geometries(Intersectable... _geometriesList) {
		super();
		add(_geometriesList);
		
	}
	public void add(Intersectable... geometries)
	{
		for(Intersectable i: geometries)
		{
			this._geometriesList.add(i);
		}
	}
	@Override
	public List<GeoPoint> findIntersections(Ray ray,double maxDistance) {
		// TODO Auto-generated method stub
	        List<GeoPoint> intersections = null;

	        for (Intersectable geo : _geometriesList) {
	            List<GeoPoint> tempIntersections = geo.findIntersections(ray,maxDistance);
	            if (tempIntersections != null) {
	                if (intersections == null)
	                    intersections = new ArrayList<GeoPoint>();
	                  intersections.addAll(tempIntersections);
	            }
	        }
	        

	        return intersections;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_geometriesList == null) ? 0 : _geometriesList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometries other = (Geometries) obj;
		if (_geometriesList == null) {
			if (other._geometriesList != null)
				return false;
		} else if (!_geometriesList.equals(other._geometriesList))
			return false;
		return true;
	}
}
