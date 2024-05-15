package imageprocessinggrimetest;

import static org.junit.Assert.assertTrue;

import controller.ImageIOController;
import java.awt.image.BufferedImage;
import model.ImageProcessingGRIMEModel;
import model.ImageProcessingGRIMEModelImp;
import org.junit.Test;

/**
 * A class to test the ImageProcessingGRIMEModel.
 */
public class ImageProcessingGRIMEModelTest {

  private boolean checkIfTwoBufferedImagesEqual(BufferedImage image1, BufferedImage image2) {
    if (image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
      return false;
    }
    for (int i = 0; i < image1.getHeight(); i++) {
      for (int j = 0; j < image1.getWidth(); j++) {
        if (image1.getRGB(j, i) != image2.getRGB(j, i)) {
          return false;
        }
      }
    }
    return true;
  }

  @Test
  public void testGetImage() throws Exception {
    ImageProcessingGRIMEModel model = new ImageProcessingGRIMEModelImp();
    ImageIOController imageio = new ImageIOController();
    model.loadImage(imageio.load("images/manhattan-small.png"), "sample");
    BufferedImage actImage = model.getImage("sample");
    model.loadImage(imageio.load("images/expected/png/manhattan-small.png"), "expected");
    BufferedImage expImage = model.getImage("expected");
    assertTrue(checkIfTwoBufferedImagesEqual(actImage, expImage));
  }

  @Test
  public void testGetHistogram() throws Exception {
    ImageProcessingGRIMEModel model = new ImageProcessingGRIMEModelImp();
    ImageIOController imageio = new ImageIOController();
    model.loadImage(imageio.load("images/manhattan-small.png"), "sample");
    BufferedImage actImage = model.getHistogram("sample");
    model.loadImage(imageio.load("images/expected/png/manhattan-small.png"), "expected");
    BufferedImage expImage = model.getHistogram("expected");
    assertTrue(checkIfTwoBufferedImagesEqual(actImage, expImage));
  }

  @Test
  public void testPreview() throws Exception {
    ImageProcessingGRIMEModel model = new ImageProcessingGRIMEModelImp();
    ImageIOController imageio = new ImageIOController();
    model.loadImage(imageio.load("images/manhattan-small.png"), "sample");
    model.blur("sample", "sampleBlur");
    BufferedImage actImage = model.preview("sample", "sampleBlur", 50);
    model.loadImage(imageio.load("images/expected/png/manhattan-small-blur-preview-50.png"),
        "expected");
    BufferedImage expImage = model.getImage("expected");
    assertTrue(checkIfTwoBufferedImagesEqual(actImage, expImage));
  }
}
