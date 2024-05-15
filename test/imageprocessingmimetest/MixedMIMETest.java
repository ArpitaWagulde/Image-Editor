package imageprocessingmimetest;

import controller.ImageIOController;
import controller.ImageProcessingController;
import controller.ImageProcessingMIMEControllerImp;
import imageprocessingtest.AbstractImageTest;
import model.Image;
import model.ImageProcessingMIMEModel;
import model.ImageProcessingMIMEModelImp;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * This class will test a few mixed operations offered in MIME model on images of various types.
 */

public class MixedMIMETest {

  Reader in;
  ImageProcessingMIMEModel model;
  StringBuffer out;
  ImageIOController imageio;

  @Before
  public void setUp() {
    out = new StringBuffer();
    model = new ImageProcessingMIMEModelImp();
    imageio = new ImageIOController();
  }

  @Test
  public void testRunScript() throws Exception {
    in = new StringReader("run test/testScript1");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blueTintKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blueTintKoala.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Test
  public void testRunScript2() throws Exception {
    in = new StringReader(
        "run test/testScript3\n"
            + "save images/testMixed.jpg koala-vertical-horizontal\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/jpg/blurKoala.jpg"));
    Image actImage = new Image(imageio.load("images/blurKoala.jpg"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Test
  public void testRunMIMEScript() throws Exception {
    in = new StringReader(
            "run test/testMIME\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/koala-compressed.png"));
    Image actImage = new Image(imageio.load("images/koala-compressed.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/png/koala-hist.png"));
    actImage = new Image(imageio.load("images/koala-hist.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/png/koala-partialblur.png"));
    actImage = new Image(imageio.load("images/koala-partialblur.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/png/koala-partialblursepia.png"));
    actImage = new Image(imageio.load("images/koala-partialblursepia.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }
}
