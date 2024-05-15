package imageprocessingtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.ImageIOController;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImp;
import model.Image;
import model.ImageProcessingModel;
import model.ImageProcessingModelImp;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Before;

/**
 * This class tests various operation on png images.
 */
public class PNGImageTest extends AbstractImageTest {

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

  @Override
  public void testLoadInvalidImagePath() throws IOException {
    in = new StringReader("load image/manhattan-small.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not load Image. Please try again or check input command."
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testLoadInvalidImageName() throws IOException {
    in = new StringReader("load image/manhattan-smal.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();

    assertEquals("Could not load Image. Please try again or check input command."
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testSaveInvalidImagePath() throws IOException {
    in = new StringReader(
        "load images/manhattan-small.png manhattan\n"
            + "save image/manhattan-small.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    assertEquals(
        "Loaded Image: images/manhattan-small.png into manhattan.\n"
            + "Could not save Image. Please try again or check input command."
            + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testOperationOnNonExistingImage() throws IOException {
    in = new StringReader("save images/manhattan-small.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not save Image. Please try again or check input command."
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testLoadSave() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "save images/new-manhattan-small.png manhattan-small\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/new-manhattan-small.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeRed() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "red-component manhattan-small manhattan-small-red\n"
            + "save images/manhattan-small-red.png manhattan-small-red\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-red.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-red.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeGreen() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "green-component manhattan-small manhattan-small-green\n"
            + "save images/manhattan-small-green.png manhattan-small-green\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-green.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-green.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeBlue() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blue-component manhattan-small manhattan-small-blue\n"
            + "save images/manhattan-small-blue.png manhattan-small-blue\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-blue.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-blue.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeValue() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "value-component manhattan-small manhattan-small-value\n"
            + "save images/manhattan-small-value.png manhattan-small-value\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-value-greyscale.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-value.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeIntensity() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "intensity-component manhattan-small manhattan-small-intensity\n"
            + "save images/manhattan-small-intensity.png manhattan-small-intensity\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-intensity-greyscale.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-intensity.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeLuma() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "luma-component manhattan-small manhattan-small-luma\n"
            + "save images/manhattan-small-luma.png manhattan-small-luma\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-luma-greyscale.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-luma.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void flipHorizontally() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "horizontal-flip manhattan-small manhattan-small-horizontal\n"
            + "save images/manhattan-small-horizontal.png manhattan-small-horizontal\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-horizontal.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-horizontal.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void flipVertically() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "vertical-flip manhattan-small manhattan-small-vertical\n"
            + "save images/manhattan-small-vertical.png manhattan-small-vertical\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-vertical.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-vertical.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBrighten() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "brighten 50 manhattan-small manhattan-small-bright\n"
            + "save images/manhattan-small-bright.png manhattan-small-bright\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-brighter-by-50.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-bright.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testDarken() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "brighten -50 manhattan-small manhattan-small-dark\n"
            + "save images/manhattan-small-dark.png manhattan-small-dark\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-darker-by-50.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-dark.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSplit() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "rgb-split manhattan-small "
        + "red-manhattan-small green-manhattan-small blue-manhattan-small\n"
        + "save images/red-manhattan-small.png red-manhattan-small\n"
        + "save images/green-manhattan-small.png green-manhattan-small\n"
        + "save images/blue-manhattan-small.png blue-manhattan-small\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-red.png"));
    Image actImage = new Image(imageio.load("images/red-manhattan-small.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/png/manhattan-small-green.png"));
    actImage = new Image(imageio.load("images/green-manhattan-small.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/png/manhattan-small-blue.png"));
    actImage = new Image(imageio.load("images/blue-manhattan-small.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCombine() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "rgb-split manhattan-small "
        + "red-manhattan-small green-manhattan-small blue-manhattan-small\n"
        + "rgb-combine manhattan-small-combined "
        + "red-manhattan-small green-manhattan-small blue-manhattan-small \n"
        + "save images/manhattan-small-combined.png manhattan-small-combined\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-combined.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlur() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur\n"
            + "save images/manhattan-small-blur.png manhattan-small-blur\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-blur.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-blur.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSharpen() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "sharpen manhattan-small manhattan-small-sharp\n"
            + "save images/manhattan-small-sharp.png manhattan-small-sharp\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-sharpen.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-sharp.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testConvertToSepia() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "sepia manhattan-small manhattan-small-sepia\n"
            + "save images/manhattan-small-sepia.png manhattan-small-sepia\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-sepia.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-sepia.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testOverwriteWithSameType() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "load images/expected/png/manhattan-small-blue.png manhattan-small\n"
            + "save images/new-manhattan-small.png manhattan-small\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/new-manhattan-small.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testOverwriteWithDifferentType() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "load images/expected/ppm/Koala.ppm manhattan-small\n"
            + "save images/new-Koala.ppm manhattan-small\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/new-Koala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSaveAsPPM() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "save images/new-manhattan-small.ppm manhattan-small\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/manhattan-small.ppm"));
    Image actImage = new Image(imageio.load("images/new-manhattan-small.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSaveAsJPG() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "save images/new-manhattan-small.jpg manhattan-small\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/jpg/manhattan-small.jpg"));
    Image actImage = new Image(imageio.load("images/new-manhattan-small.jpg"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSaveAsPNG() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "save images/new-manhattan-small.png manhattan-small\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/new-manhattan-small.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVerticalFlipTwice() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "vertical-flip manhattan-small manhattan-small-vertical\n"
            + "vertical-flip manhattan-small-vertical manhattan-small-vertical-vertical\n"
            + "save images/manhattan-small-vertical-vertical.png "
            + "manhattan-small-vertical-vertical\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-vertical-vertical.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testHorizontalFlipTwice() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "horizontal-flip manhattan-small manhattan-small-horizontal\n"
            + "horizontal-flip manhattan-small-horizontal manhattan-small-horizontal-horizontal\n"
            + "save images/manhattan-small-horizontal-horizontal.png "
            + "manhattan-small-horizontal-horizontal\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-horizontal-horizontal.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlurTwice() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur\n"
            + "blur manhattan-small-blur manhattan-small-blurred\n"
            + "save images/manhattan-small-blurred.png manhattan-small-blurred\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-blurred.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-blurred.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBrightenDarken() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "brighten 50 manhattan-small manhattan-small-bright\n"
            + "brighten -50 manhattan-small-bright manhattan-small-bright-dark\n"
            + "save images/manhattan-small-bright-dark.png manhattan-small-bright-dark\nquit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-bright-dark.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlurSharpen() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur\n"
            + "sharpen manhattan-small-blur manhattan-small-blur-sharp\n"
            + "save images/manhattan-small-blur-sharp.png manhattan-small-blur-sharp\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-blur-sharp.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-blur-sharp.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSharpenBlur() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "sharpen manhattan-small manhattan-small-sharp\n"
            + "blur manhattan-small-sharp manhattan-small-sharp-blur\n"
            + "save images/manhattan-small-sharp-blur.png manhattan-small-sharp-blur\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-sharp-blur.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-sharp-blur.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVerticalHorizontalFlip() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "vertical-flip manhattan-small manhattan-small-vertical\n"
            + "horizontal-flip manhattan-small-vertical manhattan-small-vertical-horizontal\n"
            + "save images/manhattan-small-vertical-horizontal.png "
            + "manhattan-small-vertical-horizontal\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-vertical-horizontal.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-vertical-horizontal.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testHorizontalVerticalFlip() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "horizontal-flip manhattan-small manhattan-small-horizontal\n"
            + "vertical-flip manhattan-small-horizontal manhattan-small-horizontal-vertical\n"
            + "save images/manhattan-small-horizontal-vertical.png "
            + "manhattan-small-horizontal-vertical\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-horizontal-vertical.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-horizontal-vertical.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testConsecutiveOperations() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "rgb-split manhattan-small "
        + "red-manhattan-small green-manhattan-small blue-manhattan-small\n"
        + "brighten 25 red-manhattan-small red-bright-manhattan-small\n"
        + "rgb-combine manhattan-small-red-tint "
        + "red-bright-manhattan-small green-manhattan-small blue-manhattan-small\n"
        + "save images/manhattan-small-red-tint.png manhattan-small-red-tint\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-red-tint.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-red-tint.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }
}
