package imageprocessingmimetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.ImageIOController;
import controller.ImageProcessingController;
import controller.ImageProcessingMIMEControllerImp;
import imageprocessingtest.AbstractImageTest;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import model.Image;
import model.ImageProcessingMIMEModel;
import model.ImageProcessingMIMEModelImp;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests various operations offered in MIME model on png images.
 */

public class PNGImageMIMETest extends AbstractImageMIMETest {

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

  @Override
  public void testLoadInvalidImagePath() throws IOException {
    in = new StringReader("load image/manhattan-small.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not load Image. Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testLoadInvalidImageName() throws IOException {
    in = new StringReader("load image/manhattan-smal.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();

    assertEquals("Could not load Image. Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testSaveInvalidImagePath() throws IOException {
    in = new StringReader(
        "load images/manhattan-small.png manhattan\n"
            + "save image/manhattan-small.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not save Image manhattan to image/manhattan-small.png. "
        + "Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testOperationOnNonExistingImage() throws IOException {
    in = new StringReader("save images/manhattan-small.png manhattan\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not save Image manhattan to images/manhattan-small.png. "
        + "Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testLoadSave() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "save images/new-manhattan-small.png manhattan-small\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
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
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-red-tint.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-red-tint.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testNegativeCompressPercent() throws IOException {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "compress -1 manhattan-small manhattan-small-compress\n"
            + "save images/manhattan-small-compress-0.png manhattan-small-compress\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not compress. Please try again or check input command.\n"
        + "Could not save Image manhattan-small-compress to images/manhattan-small-compress-0.png. "
        + "Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testInvalidCompressPercent() throws IOException {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "compress 150 manhattan-small manhattan-small-compress\n"
            + "save images/manhattan-small-compress-0.png manhattan-small-compress\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not compress. Please try again or check input command.\n"
        + "Could not save Image manhattan-small-compress to images/manhattan-small-compress-0.png. "
        + "Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testCompress0() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "compress 0 manhattan-small manhattan-small-compress\n"
            + "save images/manhattan-small-compress-0.png manhattan-small-compress\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-compress-0.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress100() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "compress 100 manhattan-small manhattan-small-compress\n"
            + "save images/manhattan-small-compress-100.png manhattan-small-compress\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-black.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-compress-100.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress20() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "compress 20 manhattan-small manhattan-small-compress\n"
            + "save images/manhattan-small-compress-20.png manhattan-small-compress\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-compress-20.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-compress-20.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress50() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "compress 50 manhattan-small manhattan-small-compress\n"
            + "save images/manhattan-small-compress-50.png manhattan-small-compress\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-compress-50.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-compress-50.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress90() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "compress 90 manhattan-small manhattan-small-compress\n"
            + "save images/manhattan-small-compress-90.png manhattan-small-compress\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-compress-90.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-compress-90.png"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testHistogram() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "histogram manhattan-small manhattan-hist\n"
        + "save images/manhattan-small-hist.png manhattan-hist\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-hist.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-hist.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testColorCorrect() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "color-correct manhattan-small manhattan-cc\n"
        + "save images/manhattan-small-colorCorrect.png manhattan-cc\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-colorCorrect.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-colorCorrect.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testColorCorrectPreview() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "color-correct manhattan-small manhattan-cc split 50\n"
        + "save images/manhattan-small-colorCorrect-preview-50.png manhattan-cc\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-colorCorrect-preview-50.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-colorCorrect-preview-50.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testLevelsAdjust() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "levels-adjust 65 132 225 manhattan-small manhattan-la\n"
        + "save images/manhattan-small-levelsAdjust.png manhattan-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-levelsAdjust.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-levelsAdjust.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testInvalidValuesLevelsAdjust() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "levels-adjust 132 225 132 manhattan-small manhattan-la\n"
        + "save images/manhattan-la.png manhattan-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not level adjust. Please check input command.\n"
            + "Could not save Image manhattan-la to images/manhattan-la.png. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.",
        out.toString());
  }

  @Override
  public void testOutOfRangeValuesLevelsAdjust() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "levels-adjust -25 132 255 manhattan-small manhattan-la\n"
        + "save images/manhattan-la.png manhattan-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not level adjust. Please check input command.\n"
            + "Could not save Image manhattan-la to images/manhattan-la.png. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.",
        out.toString());
  }

  @Override
  public void testLevelsAdjustPreview() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "levels-adjust 65 132 225 manhattan-small manhattan-la split 50\n"
        + "save images/manhattan-small-levelsAdjust-preview-50.png manhattan-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-levelsAdjust-preview-50.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-levelsAdjust-preview-50.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testColorCorrectHistogram() throws Exception {
    in = new StringReader("load images/manhattan-small.png manhattan-small\n"
        + "color-correct manhattan-small manhattan-cc\n"
        + "histogram manhattan-cc manhattan-cc-hist\n"
        + "save images/manhattan-small-colorCorrect-hist.png manhattan-cc-hist\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-colorCorrect-hist.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-colorCorrect-hist.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testInvalidPreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur split 120\n"
            + "save images/manhattan-small-blur-preview.png manhattan-small-blur\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not blur Image. Please try again or check input command.\n"
            + "Could not save Image manhattan-small-blur "
            + "to images/manhattan-small-blur-preview.png. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.",
        out.toString());
  }

  @Test
  public void testNegativePreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur split -10\n"
            + "save images/manhattan-small-blur-preview.png manhattan-small-blur\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not blur Image. Please try again or check input command.\n"
            + "Could not save Image manhattan-small-blur "
            + "to images/manhattan-small-blur-preview.png. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.",
        out.toString());
  }

  @Override
  public void testPreview0() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur split 0\n"
            + "save images/manhattan-small-blur-preview-0.png manhattan-small-blur\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-blur-preview-0.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testPreview100() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur split 100\n"
            + "save images/manhattan-small-blur-preview-100.png manhattan-small-blur\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small-blur.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-blur-preview-100.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlurPreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur split 50\n"
            + "save images/manhattan-small-blur-preview-50.png manhattan-small-blur\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-blur-preview-50.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-blur-preview-50.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSharpenPreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "sharpen manhattan-small manhattan-small-sharp split 25\n"
            + "save images/manhattan-small-sharp-preview-25.png manhattan-small-sharp\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-sharp-preview-25.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-sharp-preview-25.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSepiaPreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "sepia manhattan-small manhattan-small-sepia split 65\n"
            + "save images/manhattan-small-sepia-preview-65.png manhattan-small-sepia\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-sepia-preview-65.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-sepia-preview-65.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeValuePreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "value-component manhattan-small manhattan-small-value split 30\n"
            + "save images/manhattan-small-value-preview-30.png manhattan-small-value\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-value-preview-30.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-value-preview-30.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeIntensityPreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "intensity-component manhattan-small manhattan-small-intensity split 75\n"
            + "save images/manhattan-small-intensity-preview-75.png manhattan-small-intensity\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-intensity-preview-75.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-intensity-preview-75.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeLumaPreview() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "luma-component manhattan-small manhattan-small-luma split 15\n"
            + "save images/manhattan-small-luma-preview-15.png manhattan-small-luma\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-luma-preview-15.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-luma-preview-15.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Test
  public void testPreviewTwice() throws Exception {
    in = new StringReader(
        "load images/manhattan-small.png manhattan-small\n"
            + "luma-component manhattan-small manhattan-small-luma split 0\n"
            + "luma-component manhattan-small manhattan-small-luma split 15\n"
            + "save images/manhattan-small-luma-preview-15.png manhattan-small-luma\n"
            + "save images/manhattan-small-luma-preview-0.png manhattan-small-luma\n"
            + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/png/manhattan-small-luma-preview-15.png"));
    Image actImage = new Image(imageio.load("images/manhattan-small-luma-preview-15.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
    actImage = new Image(imageio.load("images/manhattan-small-luma-preview-0.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }
}
