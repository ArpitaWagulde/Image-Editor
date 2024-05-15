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

/**
 * This class tests various operations offered in MIME model on ppm images.
 */

public class PPMImageMIMETest extends AbstractImageMIMETest {

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
    in = new StringReader("load image/Koala.ppm koala\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not load Image. Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testLoadInvalidImageName() throws IOException {
    in = new StringReader("load images/Koal.ppm koala\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not load Image. Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testSaveInvalidImagePath() throws IOException {
    in = new StringReader("load images/Koala.ppm koala\nsave image/NewKoala.ppm koala\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not save Image koala to image/NewKoala.ppm. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testOperationOnNonExistingImage() throws IOException {
    in = new StringReader("save images/NewKoala.ppm koala\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not save Image koala to images/NewKoala.ppm. "
        + "Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testLoadSave() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\nsave images/NewKoala.ppm koala\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/newKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeRed() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "red-component koala redKoala\n"
        + "save images/redKoala.ppm redKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/redKoala.ppm"));
    Image actImage = new Image(imageio.load("images/redKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeGreen() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "green-component koala greenKoala\n"
        + "save images/greenKoala.ppm greenKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/greenKoala.ppm"));
    Image actImage = new Image(imageio.load("images/greenKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeBlue() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blue-component koala blueKoala\n"
        + "save images/blueKoala.ppm blueKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blueKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blueKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeValue() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "value-component koala valueKoala\n"
        + "save images/valueKoala.ppm valueKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/valueKoala.ppm"));
    Image actImage = new Image(imageio.load("images/valueKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeIntensity() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "intensity-component koala intensityKoala\n"
        + "save images/intensityKoala.ppm intensityKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/intensityKoala.ppm"));
    Image actImage = new Image(imageio.load("images/intensityKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeLuma() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "luma-component koala lumaKoala\n"
        + "save images/lumaKoala.ppm lumaKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/lumaKoala.ppm"));
    Image actImage = new Image(imageio.load("images/lumaKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void flipHorizontally() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "horizontal-flip koala flipHKoala\n"
        + "save images/flipHKoala.ppm flipHKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/flipHKoala.ppm"));
    Image actImage = new Image(imageio.load("images/flipHKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void flipVertically() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "vertical-flip koala flipVKoala\nsave images/flipVKoala.ppm flipVKoala\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/flipVKoala.ppm"));
    Image actImage = new Image(imageio.load("images/flipVKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBrighten() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "brighten 50 koala brightKoala\n"
        + "save images/brightKoala.ppm brightKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/brightKoala.ppm"));
    Image actImage = new Image(imageio.load("images/brightKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testDarken() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "brighten -50 koala darkKoala\n"
        + "save images/darkKoala.ppm darkKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/darkKoala.ppm"));
    Image actImage = new Image(imageio.load("images/darkKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSplit() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "rgb-split koala red-koala green-koala blue-koala\n"
        + "save images/red-Koala.ppm red-koala\n"
        + "save images/green-Koala.ppm green-koala\n"
        + "save images/blue-Koala.ppm blue-koala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/redKoala.ppm"));
    Image actImage = new Image(imageio.load("images/red-Koala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/ppm/greenKoala.ppm"));
    actImage = new Image(imageio.load("images/green-Koala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
    expImage = new Image(imageio.load("images/expected/ppm/blueKoala.ppm"));
    actImage = new Image(imageio.load("images/blue-Koala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCombine() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "rgb-split koala red-koala green-koala blue-koala\n"
        + "rgb-combine combinedKoala red-koala green-koala blue-koala\n"
        + "save images/combinedKoala.ppm combinedKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/combinedKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlur() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blur koala blurKoala\n"
        + "save images/blurKoala.ppm blurKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blurKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blurKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSharpen() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "sharpen koala sharpKoala\n"
        + "save images/sharpKoala.ppm sharpKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/sharpKoala.ppm"));
    Image actImage = new Image(imageio.load("images/sharpKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testConvertToSepia() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "sepia koala sepiaKoala\n"
        + "save images/sepiaKoala.ppm sepiaKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/sepiaKoala.ppm"));
    Image actImage = new Image(imageio.load("images/sepiaKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testOverwriteWithSameType() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "load images/expected/ppm/redKoala.ppm koala\n"
        + "save images/NewKoala.ppm koala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/redKoala.ppm"));
    Image actImage = new Image(imageio.load("images/newKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testOverwriteWithDifferentType() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "load images/expected/png/manhattan-small.png koala\n"
        + "save images/NewImage.png koala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/manhattan-small.png"));
    Image actImage = new Image(imageio.load("images/NewImage.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSaveAsPPM() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "save images/NewKoala.ppm koala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/newKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSaveAsJPG() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "save images/Koala.jpg koala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/jpg/Koala.jpg"));
    Image actImage = new Image(imageio.load("images/Koala.jpg"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSaveAsPNG() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "save images/Koala.png koala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/png/Koala.png"));
    Image actImage = new Image(imageio.load("images/Koala.png"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVerticalFlipTwice() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "vertical-flip koala flipVKoala\n"
        + "vertical-flip flipVKoala flipVVKoala\n"
        + "save images/flipVVKoala.ppm flipVVKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/flipVVKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testHorizontalFlipTwice() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "horizontal-flip koala flipHKoala\n"
        + "horizontal-flip flipHKoala flipHHKoala\n"
        + "save images/flipHHKoala.ppm flipHHKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/flipHHKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlurTwice() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blur koala blurKoala\n"
        + "blur blurKoala blurredKoala\n"
        + "save images/blurredKoala.ppm blurredKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blurredKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blurredKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBrightenDarken() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "brighten 50 koala brightKoala\n"
        + "brighten -50 brightKoala brightDarkKoala\n"
        + "save images/brightDarkKoala.ppm brightDarkKoala\nquit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/brightDarkKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlurSharpen() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blur koala blurKoala\n"
        + "sharpen blurKoala blurSharpKoala\n"
        + "save images/blurSharpKoala.ppm blurSharpKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blurSharpKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blurSharpKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSharpenBlur() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "sharpen koala sharpKoala\n"
        + "blur sharpKoala sharpBlurKoala\n"
        + "save images/sharpBlurKoala.ppm sharpBlurKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/sharpBlurKoala.ppm"));
    Image actImage = new Image(imageio.load("images/sharpBlurKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVerticalHorizontalFlip() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "vertical-flip koala flipVKoala\n"
        + "horizontal-flip flipVKoala flipVHKoala\n"
        + "save images/flipVHKoala.ppm flipVHKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/flipVHKoala.ppm"));
    Image actImage = new Image(imageio.load("images/flipVHKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testHorizontalVerticalFlip() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "horizontal-flip koala flipHKoala\n"
        + "vertical-flip flipHKoala flipHVKoala\n"
        + "save images/flipHVKoala.ppm flipHVKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/flipHVKoala.ppm"));
    Image actImage = new Image(imageio.load("images/flipHVKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testConsecutiveOperations() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "rgb-split koala red-koala green-koala blue-koala\n"
        + "brighten 25 blue-koala blue-bright-koala\n"
        + "rgb-combine blueTintKoala red-koala green-koala blue-bright-koala\n"
        + "save images/blueTintKoala.ppm blueTintKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blueTintKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blueTintKoala.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testNegativeCompressPercent() throws IOException {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "compress -20 koala koala-compress\n"
        + "save images/koala-compress-90.ppm koala-compress\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not compress. Please try again or check input command.\n"
        + "Could not save Image koala-compress to images/koala-compress-90.ppm. "
        + "Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testInvalidCompressPercent() throws IOException {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "compress 120 koala koala-compress\n"
        + "save images/koala-compress-90.ppm koala-compress\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not compress. Please try again or check input command.\n"
        + "Could not save Image koala-compress to images/koala-compress-90.ppm. "
        + "Please try again or check input command.\n"
        + "Thank you for using this Image processor.", out.toString());
  }

  @Override
  public void testCompress0() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "compress 0 koala koala-compress\n"
        + "save images/koala-compress-0.ppm koala-compress\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/koala.ppm"));
    Image actImage = new Image(imageio.load("images/koala-compress-0.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress100() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "compress 100 koala koala-compress\n"
        + "save images/koala-compress-100.ppm koala-compress\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/koala-black.ppm"));
    Image actImage = new Image(imageio.load("images/koala-compress-100.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress20() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "compress 20 koala koala-compress\n"
        + "save images/koala-compress-20.ppm koala-compress\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/koala-compress-20.ppm"));
    Image actImage = new Image(imageio.load("images/koala-compress-20.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress50() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "compress 50 koala koala-compress\n"
        + "save images/koala-compress-50.ppm koala-compress\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/koala-compress-50.ppm"));
    Image actImage = new Image(imageio.load("images/koala-compress-50.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testCompress90() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "compress 90 koala koala-compress\n"
        + "save images/koala-compress-90.ppm koala-compress\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/koala-compress-90.ppm"));
    Image actImage = new Image(imageio.load("images/koala-compress-90.ppm"));
    assertTrue(AbstractImageTest.checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testHistogram() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "histogram koala koala-hist\n"
        + "save images/koala-hist.ppm koala-hist\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/koala-hist.ppm"));
    Image actImage = new Image(imageio.load("images/koala-hist.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testColorCorrect() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "color-correct koala koala-cc\n"
        + "save images/koala-colorCorrect.ppm koala-cc\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/ppm/koala-colorCorrect.ppm"));
    Image actImage = new Image(imageio.load("images/koala-colorCorrect.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testColorCorrectPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "color-correct koala koala-cc split 50\n"
        + "save images/koala-colorCorrect-preview-50.ppm koala-cc\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/ppm/koala-colorCorrect-preview-50.ppm"));
    Image actImage = new Image(imageio.load("images/koala-colorCorrect-preview-50.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testLevelsAdjust() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "levels-adjust 65 132 225 koala koala-la\n"
        + "save images/koala-levelsAdjust.ppm koala-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/ppm/koala-levelsAdjust.ppm"));
    Image actImage = new Image(imageio.load("images/koala-levelsAdjust.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testInvalidValuesLevelsAdjust() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "levels-adjust 132 225 132 koala koala-la\n"
        + "save images/koala-levelsAdjust-preview-50.ppm koala-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not level adjust. Please check input command.\n"
            + "Could not save Image koala-la to images/koala-levelsAdjust-preview-50.ppm. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.",
        out.toString());
  }

  @Override
  public void testOutOfRangeValuesLevelsAdjust() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "levels-adjust 0 132 267 koala koala-la\n"
        + "save images/koala-levelsAdjust-preview-50.ppm koala-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not level adjust. Please check input command.\n"
            + "Could not save Image koala-la to images/koala-levelsAdjust-preview-50.ppm. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.",
        out.toString());
  }

  @Override
  public void testLevelsAdjustPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "levels-adjust 65 132 225 koala koala-la split 50\n"
        + "save images/koala-levelsAdjust-preview-50.ppm koala-la\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/ppm/koala-levelsAdjust-preview-50.ppm"));
    Image actImage = new Image(imageio.load("images/koala-levelsAdjust-preview-50.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testColorCorrectHistogram() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "color-correct koala koala-cc\n"
        + "histogram koala-cc koala-cc-hist\n"
        + "save images/koala-colorCorrect-hist.ppm koala-cc-hist\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(
        imageio.load("images/expected/ppm/koala-colorCorrect-hist.ppm"));
    Image actImage = new Image(imageio.load("images/koala-colorCorrect-hist.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testInvalidPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blur koala blurKoala split -20\n"
        + "save images/blurKoala-preview-0.ppm blurKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("Could not blur Image. Please try again or check input command.\n"
            + "Could not save Image blurKoala to images/blurKoala-preview-0.ppm. "
            + "Please try again or check input command.\n"
            + "Thank you for using this Image processor.",
        out.toString());
  }

  @Override
  public void testPreview0() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blur koala blurKoala split 0\n"
        + "save images/blurKoala-preview-0.ppm blurKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/Koala.ppm"));
    Image actImage = new Image(imageio.load("images/blurKoala-preview-0.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testPreview100() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blur koala blurKoala split 100\n"
        + "save images/blurKoala-preview-100.ppm blurKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blurKoala.ppm"));
    Image actImage = new Image(imageio.load("images/blurKoala-preview-100.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testBlurPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "blur koala blurKoala split 50\n"
        + "save images/blurKoala-preview-50.ppm blurKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/blurKoala-preview-50.ppm"));
    Image actImage = new Image(imageio.load("images/blurKoala-preview-50.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSharpenPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "sharpen koala sharpKoala split 25\n"
        + "save images/sharpKoala-preview-25.ppm sharpKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/sharpKoala-preview-25.ppm"));
    Image actImage = new Image(imageio.load("images/sharpKoala-preview-25.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testSepiaPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "sepia koala sepiaKoala split 65\n"
        + "save images/sepiaKoala-preview-65.ppm sepiaKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/sepiaKoala-preview-65.ppm"));
    Image actImage = new Image(imageio.load("images/sepiaKoala-preview-65.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeValuePreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "value-component koala valueKoala split 30\n"
        + "save images/valueKoala-preview-30.ppm valueKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/valueKoala-preview-30.ppm"));
    Image actImage = new Image(imageio.load("images/valueKoala-preview-30.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeIntensityPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "intensity-component koala intensityKoala split 75\n"
        + "save images/intensityKoala-preview-75.ppm intensityKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/intensityKoala-preview-75.ppm"));
    Image actImage = new Image(imageio.load("images/intensityKoala-preview-75.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }

  @Override
  public void testVisualizeLumaPreview() throws Exception {
    in = new StringReader("load images/Koala.ppm koala\n"
        + "luma-component koala lumaKoala split 15\n"
        + "save images/lumaKoala-preview-15.ppm lumaKoala\n"
        + "quit");
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    Image expImage = new Image(imageio.load("images/expected/ppm/lumaKoala-preview-15.ppm"));
    Image actImage = new Image(imageio.load("images/lumaKoala-preview-15.ppm"));
    assertTrue(checkIfTwoImagesEqual(expImage, actImage));
  }
}

