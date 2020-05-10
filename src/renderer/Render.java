/**
 * 
 */
package renderer;

import java.util.List;

import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * @author Ruthi
 *
 */
public class Render {

	ImageWriter _imageWriter;
	Scene _scene;
	
	public Render(ImageWriter imageWriter, Scene scene) {
		_imageWriter = imageWriter;
		_scene = scene;
	}
	/**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        java.awt.Color background = _scene.get_background().getColor();
        Camera camera = _scene.get_camera();
        Intersectable geometries = _scene.get_geometries();
        double distance = _scene.get_distance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    _imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }
    /**
     * 
     *
     * @param intersectionPoints list of points, the function should find from
     *                           this list the closet point of the camera in the scene.
     * @return the closest point to the camera
     */

    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
    	Point3D result = null;
        double mindist = Double.MAX_VALUE;

        return this._scene.get_camera().get_position();

     
    }
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    private Color calcColor(Point3D point) 
    { return _scene.get_ambientLight().get_intensity(); } 
    
    
    /**
     * Printing the grid with a fixed interval between lines
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNy();
        double collumns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int collumn = 0; collumn < collumns; ++collumn) {
                if (collumn % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(collumn, row, colorsep);
                }
            }
        }
    }
}
