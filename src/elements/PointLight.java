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
public class PointLight extends Light implements LightSource {

	
	protected Point3D _position;
    protected double _kC;
    protected double _kL;
    protected double _kQ;

    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
        super(intensity);
        this._position = new Point3D(position);
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }


    //overriding LightSource getIntensity(Point3D)
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }

    // Light vector
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();
    }
}
