/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author Ruthi
 *
 */
public class ImageWriterTest {

	
	@Test
	public void testImageWriter() {
		 ImageWriter imageWriter = new ImageWriter("Shira and Ruthi were here", 1600, 1000, 800, 500);
	        int Nx = imageWriter.getNx();
	        int Ny = imageWriter.getNy();
	        for (int i = 0; i < Ny; i++) {
	            for (int j = 0; j < Nx; j++) {
	                if (i % 50 == 0 || j % 50 == 0) {
	                    imageWriter.writePixel(j, i, java.awt.Color.pink);
	                } else {
	                    imageWriter.writePixel(j, i, java.awt.Color.green);
	                }
	            }
	        }
	        imageWriter.writeToImage();
	    }

}
