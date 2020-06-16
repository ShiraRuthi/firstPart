/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * @author Ruthi
 *
 */
public interface Intersectable {
	
	
	/**
	 * class GeoPoint
	 *
	 */
	public static class GeoPoint {
		public Geometry geometry;
	    public Point3D point;
		
		/**
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			super();
			this.geometry = geometry;
			this.point = point;
		}

	

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}
		
	    
	}
	
	default List<GeoPoint> findIntersections(Ray ray) { 
		return findIntersections(ray, Double.POSITIVE_INFINITY); 
		}
	List<GeoPoint> findIntersections(Ray ray, double maxDistance);


}
