/**
 * 
 */
package elements;

import primitives.Point3D;
import static primitives.Util.isZero;
import primitives.*;

/**
 * @author Ruthi
 *
 */
public class Camera {
	private Point3D _position;
	private Vector _vTo,_vUp,_vRight;
	/**
	 * @param _position
	 * @param _vTo
	 * @param _vUp
	 */
	public Camera(Point3D _position, Vector _Vto , Vector _Vup ) {
		super();
		if(_Vto.dotProduct(_Vup)!=0)
			throw new IllegalArgumentException("The vectors are not orthogonal");
		this._position = new Point3D(_position);
		this._vTo = _Vto.normalized();
		this._vUp = _Vup.normalized();
		this._vRight = (_Vto.crossProduct(_Vup)).normalize();
		
	}
	public Ray constructRayThroughPixel (int nX, int nY,int j, int i, double screenDistance,double screenWidth, double screenHeight)
	{

        if (isZero(screenDistance))
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = _position.add(_vTo.scale(screenDistance));

        double Ry = screenHeight/nY;
        double Rx = screenWidth/nX;

        double yi =  ((i - nY/2d)*Ry + Ry/2d);
        double xj=   ((j - nX/2d)*Rx + Rx/2d);

        Point3D Pij = Pc;

        if (! isZero(xj))
        {
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (! isZero(yi))
        {
            Pij = Pij.add(_vUp.scale(-yi));
        }

        Vector Vij = Pij.subtract(_position);

        return new Ray(_position,Vij);
	}
	/**
	 * @return the _position
	 */
	public Point3D get_position() {
		return _position;
	}
	/**
	 * @return the _Vto
	 */
	public Vector get_vTo() {
		return _vTo;
	}
	/**
	 * @return the _Vup
	 */
	public Vector get_vUp() {
		return _vUp;
	}
	/**
	 * @return the _Vright
	 */
	public Vector get_vRight() {
		return _vRight;
	}


}
