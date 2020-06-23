/**
 * 
 */
package elements;

import primitives.Point3D;
import static primitives.Util.isZero;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import primitives.*;

/**
 * @author Ruthi
 *
 */
public class Camera {
	private Point3D _position;
	private Vector _vTo,_vUp,_vRight;
	private static final Random rnd = new Random();
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
	public List<Ray> constructNineRaysThroughPixel(int nX, int nY, double i, double j, double screenDist, double screenWidth, double screenHeight) {

        double Rx = screenWidth / nX;//the length of pixel in X axis
        double Ry = screenHeight / nY;//the length of pixel in Y axis

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);


        Point3D Pc = new Point3D(_position.add(_vTo.scale(screenDist)));//new point from the camera to the screen

        //pc is the center of the view plane
        Point3D P = Pc.add(_vRight.scale(xj).subtract(_vUp.scale(yi)));

        //finding the intersection point with the view plane according the formula in the moodle

        //-----SuperSampling-----
        List<Ray> rays = new LinkedList<>();//the return list, construct Rays Through Pixels

        rays.add(new Ray(P, _position.subtract(P)));//the first ray(we already find it)
        //the next 8 rays we gonna add is the same thing, but there's difference in the variation on
        // x and y arguments, some times we will change one of them and some times both of them

        // x coord middle of pixel/2 downwards
        Point3D tmp = new Point3D(P.get_x().get() - Rx / 2, P.get_y().get(), P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        // y coord middle of pixel/2 downward
        tmp = new Point3D(P.get_x().get(), P.get_y().get() - Ry / 2, P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 upwards
        tmp = new Point3D(P.get_x().get() + Rx / 2, P.get_y().get(), P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        // y coord middle of pixel/2 upward
        tmp = new Point3D(P.get_x().get(), P.get_y().get() + Ry / 2, P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 downwards  y coord middle of pixel/2 downward
        tmp = new Point3D(P.get_x().get() - Rx / 2, P.get_y().get() - Ry / 2, P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 upwards  y coord middle of pixel/2 downward
        tmp = new Point3D(P.get_x().get() + Rx / 2, P.get_y().get() - Ry / 2, P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 downwards    y coord middle of pixel/2 upward
        tmp = new Point3D(P.get_x().get() - Ry / 2, P.get_y().get() + Ry / 2, P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 upwards   y coord middle of pixel/2 upward
        tmp = new Point3D(P.get_x().get() + Ry / 2, P.get_y().get() + Ry / 2, P.get_z().get());
        rays.add(new Ray(tmp, new Vector(_position.subtract(tmp)).normalized()));

        return rays;

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
public List<Ray> constructRayBeamThroughPixel(int nX, int nY, int j, int i,
            double screenDistance, double screenWidth, double screenHeight,
            double density, int amount) {
if (isZero(screenDistance)) {
throw new IllegalArgumentException("distance cannot be 0");
}

List<Ray> rays = new LinkedList<>();

Point3D Pc = _position.add(_vTo.scale(screenDistance));

double Ry = screenHeight / nY;
double Rx = screenWidth / nX;

double yi = ((i - nY / 2d) * Ry + Ry / 2d);
double xj = ((j - nX / 2d) * Rx + Rx / 2d);

Point3D Pij = Pc;

if (!isZero(xj)) {
Pij = Pij.add(_vRight.scale(xj));
}
if (!isZero(yi)) {
Pij = Pij.add(_vUp.scale(-yi));
}

//antialiasing density >= 1
double radius = (Rx + Ry) / 2d * density;


for (int counter = 0; counter < amount; counter++) {
Point3D point = new Point3D(Pij);
double cosTheta = 2 * rnd.nextDouble() - 1;
double sinTheta = Math.sqrt(1d - cosTheta * cosTheta);

double d = radius * (2 * rnd.nextDouble() - 1);
double x = d * cosTheta;
double y = d * sinTheta;

if (!isZero(x)) {
point = point.add(_vRight.scale(x));
}
if (!isZero(y)) {
point = point.add(_vUp.scale(y));
}
rays.add(new Ray(_position, point.subtract(_position)));
}
return rays;
}

}
