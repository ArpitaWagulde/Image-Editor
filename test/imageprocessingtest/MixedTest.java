package imageprocessingtest;

import controller.ImageIOController;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImp;
import model.Image;
import model.ImageProcessingModel;
import model.ImageProcessingModelImp;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * This class will test a few mixed operation on images of various types.
 */

public class MixedTest {

  Reader in;
  ImageProcessingModel model;
  StringBuffer out;
  ImageIOController imageio;

  @Before
  public void setUp() {
    out = new StringBuffer();
    model = new ImageProcessingModelImp();
    imageio = new ImageIOController();
  }

  @Test
  public void testRunScript() throws Exception {
    in = new StringReader("run test/testScript1");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blueTintKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blueTintKoala.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Test
  public void testRunScript2() throws Exception {
    in = new StringReader(
        "run test/testScript3\nsave images/testMixed.jpg koala-vertical-horizontal\nquit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/brightKoala.ppm"));
    Image actImage = new Image(imageio.load("images/koala-brighter.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/png/valueKoala.png"));
    actImage = new Image(imageio.load("images/koala-gs.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/jpg/testMixed.jpg"));
    actImage = new Image(imageio.load("images/testMixed.jpg"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Test
  public void testJPG() throws Exception {
    in = new StringReader("load images/Koala.jpg koala\n"
        + "blur koala blurKoala\n"
        + "save images/blurKoala.jpg blurKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/jpg/blurKoala.jpg"));
    Image actImage = new Image(imageio.load("images/blurKoala.jpg"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }
}
