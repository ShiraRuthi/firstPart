/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author Ruthi
 *
 */


public class DirectionalLight extends Light implements  LightSource {
    private Vector _direction;

    
    public DirectionalLight(Color intensityLight, Vector direction) {
        super(intensityLight);
        this._direction = direction.normalized();
    }

    @Override
    public Vector getL(Point3D point) {
        return _direction;
    }

    @Override
    public Color getIntensity(Point3D point) {
        return super.get_intensity();
    }
}