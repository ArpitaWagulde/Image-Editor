package imageprocessingmimetest;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import controller.ImageProcessingController;
import controller.ImageProcessingMIMEControllerImp;
import model.Component;
import model.Image;
import model.ImageProcessingMIMEModel;

import static org.junit.Assert.assertEquals;

/**
 * This class will test the updated controller of this MVC using mock model.
 */
public class ImageProcessingMIMEControllerTest {

  /**
   * An inner class that mock the model to test the controller.
   */
  public static class MockImageProcessingMIMEModel implements ImageProcessingMIMEModel {

    Map<String, Image> imageProcessingSession;
    StringBuilder logger;

    public MockImageProcessingMIMEModel(StringBuilder log, Map<String, Image> session) {
      this.imageProcessingSession = session;
      this.logger = log;
    }

    /**
     * A method to create a compressed version of an image.
     *
     * @param sourceImageName      image to be compressed
     * @param destinationImageName destination image name to store the result
     * @param percentage           compress percentage of type Integer
     * @throws IllegalArgumentException if compress percentage is not in range of 0 to 100
     */
    @Override
    public void compress(String sourceImageName, String destinationImageName, Integer percentage)
        throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName +
          " percentage: " + percentage);

    }

    /**
     * A method to create a histogram of an image.
     *
     * @param sourceImageName      image whose histogram is to be created
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void histogram(String sourceImageName, String destinationImageName) {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName);
    }

    /**
     * A method to color-correct an image by aligning the meaningful peaks of its histogram.
     *
     * @param sourceImageName      image which is to be color corrected
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void colorCorrect(String sourceImageName, String destinationImageName) {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName);
    }

    /**
     * A method to color-correct an image by aligning the meaningful peaks of its histogram.
     *
     * @param sourceImageName      image which is to be color corrected
     * @param destinationImageName destination image name to store the result
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void colorCorrect(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    /**
     * A method to adjust the levels of an image. The black, mid and white values should be in
     * ascending order and within 0 and 255 for this function to work correctly.
     *
     * @param sourceImageName      image whose levels are to be adjusted
     * @param destinationImageName destination image name to store the result
     * @param blackValue           shadow reference point of type Integer
     * @param midValue             middle reference point of type Integer
     * @param whiteValue           highlight reference point of type Integer
     * @throws IllegalArgumentException if the blackValur, midValue and whiteValue should be in
     *                                  ascending order and in range of 0 to 255
     */
    @Override
    public void levelAdjustment(String sourceImageName, String destinationImageName,
        Integer blackValue, Integer midValue, Integer whiteValue)
        throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " blackValue: " + blackValue + " midValue: " + midValue + " whiteValue: " + whiteValue);
    }

    /**
     * A method to adjust the levels of an image. The black, mid and white values should be in
     * ascending order and within 0 and 255 for this function to work correctly.
     *
     * @param sourceImageName      image whose levels are to be adjusted
     * @param destinationImageName destination image name to store the result
     * @param blackValue           shadow reference point of type Integer
     * @param midValue             middle reference point of type Integer
     * @param whiteValue           highlight reference point of type Integer
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void levelAdjustment(String sourceImageName, String destinationImageName,
        Integer blackValue, Integer midValue, Integer whiteValue,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " blackValue: " + blackValue + " midValue: " + midValue + " whiteValue: " + whiteValue
          + " percentage: " + percentage);
    }

    /**
     * A method to blur the image.
     *
     * @param sourceImageName      image to be blurred
     * @param destinationImageName destination image name to store the result
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void blur(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    /**
     * A method to blur the image.
     *
     * @param sourceImageName      image to be blurred
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void blur(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to sharpen the image.
     *
     * @param sourceImageName      image to be sharpened
     * @param destinationImageName destination image name to store the result
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void sharpen(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    /**
     * A method to sharpen the image.
     *
     * @param sourceImageName      image to be sharpened
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void sharpen(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to apply sepia filter on the given image.
     *
     * @param sourceImageName      image to be applied filter on
     * @param destinationImageName destination image name to store the result
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void convertToSepia(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    /**
     * A method to apply sepia filter on the given image.
     *
     * @param sourceImageName      image to be applied filter on
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void convertToSepia(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to visualize the value. A value is maximum of all the components of a pixel. This
     * results in a greyscale image.
     *
     * @param sourceImageName      image whose value is to be visualized.
     * @param destinationImageName destination image name to store the result
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void visualizeValue(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    /**
     * A method to visualize the value. A value is maximum of all the components of a pixel. This
     * results in a greyscale image.
     *
     * @param sourceImageName      image whose value is to be visualized.
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void visualizeValue(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to visualize the intensity. An intensity is average of all the components of a
     * pixel. This results in a greyscale image.
     *
     * @param sourceImageName      image whose intensity is to be visualized.
     * @param destinationImageName destination image name to store the result
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void visualizeIntensity(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    /**
     * A method to visualize the intensity. An intensity is average of all the components of a
     * pixel. This results in a greyscale image.
     *
     * @param sourceImageName      image whose intensity is to be visualized.
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void visualizeIntensity(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to visualize the luma. It is the weighted sum of all the components of a pixel. This
     * results in a greyscale image.
     *
     * @param sourceImageName      image whose luma is to be visualized.
     * @param destinationImageName destination image name to store the result
     * @param percentage           percentage of the width to show preview
     * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
     */
    @Override
    public void visualizeLuma(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    /**
     * A method to visualize the luma. It is the weighted sum of all the components of a pixel. This
     * results in a greyscale image.
     *
     * @param sourceImageName      image whose luma is to be visualized.
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void visualizeLuma(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to load the image of any type.
     *
     * @param imageData ImageData to be saved of type Map whose key is Component and Value is 2D
     *                  List of pixels
     * @param imageName ImageName to load the image into in memory and then further use it for
     *                  operations
     * @throws Exception if there was an issue in loading an image
     */
    @Override
    public void loadImage(Map<Component, List<List<Integer>>> imageData,
        String imageName) throws Exception {
      logger.append("ImageName: " + imageName);
    }

    /**
     * A method to save the image of any type.
     *
     * @param imageName ImageName to be saved
     * @return Map whose key is Component and Value is 2D List of pixels
     * @throws IllegalArgumentException if the path provided does not exist
     * @throws Exception                if there was an issue in saving the image
     */
    @Override
    public Map<Component, List<List<Integer>>> saveImage(String imageName)
        throws IllegalArgumentException, Exception {
      logger.append("ImageName: " + imageName);
      return null;
    }

    /**
     * A method to visualize the components.
     *
     * @param sourceImageName      image whose components are to be visualized
     * @param destinationImageName destination image name to store the result
     * @param comp                 component which is to be visualized
     */
    @Override
    public void visualizeComponent(String sourceImageName, String destinationImageName,
        Component comp) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName
          + " Component: " + comp);
    }

    /**
     * A method to flip the image horizontally.
     *
     * @param sourceImageName      image to be flipped
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void flipHorizontally(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to flip the image vertically.
     *
     * @param sourceImageName      image to be flipped
     * @param destinationImageName destination image name to store the result
     */
    @Override
    public void flipVertically(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    /**
     * A method to brighten or darken the image based on the intensity passed.
     *
     * @param sourceImageName      image to operated on
     * @param destinationImageName destination image name to store the result
     * @param intensity            A float value by which the image will be brightened or darkened
     */
    @Override
    public void brighten(String sourceImageName, String destinationImageName, float intensity) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName
          + " Intensity: " + intensity);
    }

    /**
     * A method split the image into its components.
     *
     * @param sourceImageName       image to be split
     * @param destinationImageNames A map of components and destination image names to store the
     *                              result after split
     */
    @Override
    public void split(String sourceImageName, Map<Component, String> destinationImageNames) {
      logger.append("Source: " + sourceImageName);
      for (Component c : destinationImageNames.keySet()) {
        logger.append("Component: " + c + " destinationImage: " + destinationImageNames.get(c));
      }
    }

    /**
     * A method to combine the components from multiple images.
     *
     * @param destinationImageName  destination image name to store the result
     * @param sourceSplitImageNames A map of components and images from whom the new image will be
     *                              created
     */
    @Override
    public void combine(String destinationImageName, Map<Component, String> sourceSplitImageNames)
        throws IllegalArgumentException {
      logger.append("Destination: " + destinationImageName);
      for (Component c : sourceSplitImageNames.keySet()) {
        logger.append("Component: " + c + " sourceImage: " + sourceSplitImageNames.get(c));
      }
    }
  }

  @Test
  public void testMockLoadAndQuit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala", log.toString());
  }

  @Test
  public void testMockSaveImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nsave "
        + "images/koalanew.jpg koala\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "ImageName: koala", log.toString());
  }

  @Test
  public void testMockVisualizeComponentRed() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nred-component "
        + "koala koala-red\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-red Component: RED",
        log.toString());
  }

  @Test
  public void testMockVisualizeComponentGreen() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\ngreen-component "
        + "koala koala-green\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals(
        "ImageName: koala" + "Source: koala" + " Destination: koala-green Component: GREEN",
        log.toString());
  }

  @Test
  public void testMockVisualizeComponentBlue() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nblue-component "
        + "koala koala-blue\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-blue Component: BLUE",
        log.toString());
  }

  @Test
  public void testMockVisualizeValue() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nvalue-component "
        + "koala koala-value\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-value",
        log.toString());
  }

  @Test
  public void testMockVisualizeIntensity() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nintensity-component "
        + "koala koala-intensity\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-intensity",
        log.toString());
  }

  @Test
  public void testMockVisualizeLuma() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nluma-component "
        + "koala koala-luma\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-luma", log.toString());
  }

  @Test
  public void testMockFlipHorizontally() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nhorizontal-flip "
        + "koala koala-flippedH\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-flippedH",
        log.toString());
  }

  @Test
  public void testMockFlipVertically() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nvertical-flip "
        + "koala koala-flippedV\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-flippedV",
        log.toString());
  }

  @Test
  public void testMockBrighten() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nbrighten 10 "
        + "koala koala-brightened\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals(
        "ImageName: koala" + "Source: koala" + " Destination: koala-brightened Intensity: 10.0",
        log.toString());
  }

  @Test
  public void testMockDarken() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nbrighten -10 "
        + "koala koala-darkened\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals(
        "ImageName: koala" + "Source: koala" + " Destination: koala-darkened Intensity: -10.0",
        log.toString());
  }

  @Test
  public void testMockSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nrgb-split "
        + "koala koala-red koala-green koala-blue\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala"
        + "Source: koala" + "Component: RED destinationImage: koala-red"
        + "Component: GREEN destinationImage: koala-green"
        + "Component: BLUE destinationImage: koala-blue", log.toString());
  }

  @Test
  public void testMockCombine() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nrgb-combine "
        + "koala koala-red koala-green koala-blue\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Destination: koala"
            + "Component: RED sourceImage: koala-red"
            + "Component: GREEN sourceImage: koala-green"
            + "Component: BLUE sourceImage: koala-blue",
        log.toString());
  }

  @Test
  public void testMockBlur() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nblur "
        + "koala koala-blurred\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-blurred",
        log.toString());
  }

  @Test
  public void testMockSharpen() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nsharpen "
        + "koala koala-sharp\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-sharp",
        log.toString());
  }

  @Test
  public void testMockSepia() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nsepia "
        + "koala koala-sepia\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-sepia",
        log.toString());
  }

  @Test
  public void testMockCompress() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\ncompress "
        + "20 koala koala-compress\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-compress " +
                    "percentage: 20",
        log.toString());
  }

  @Test
  public void testMockHistogram() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nhistogram "
            + "koala koala-hist\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-hist",
            log.toString());
  }

  @Test
  public void testMockColorCorrect() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\ncolor-correct "
            + "koala koala-cc\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-cc",
            log.toString());
  }

  @Test
  public void testMockColorCorrectWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\ncolor-correct "
            + "koala koala-cc split 30\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-cc percentage: 30",
            log.toString());
  }

  @Test
  public void testMockColorLevelAdjustment() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nlevels-adjust "
            + "40 120 220 koala koala-la\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-la blackValue: 40 " +
                    "midValue: 120 whiteValue: 220",
            log.toString());
  }

  @Test
  public void testMockColorLevelAdjustmentWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nlevels-adjust "
            + "40 120 220 koala koala-la split 40\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-la blackValue: 40 " +
                    "midValue: 120 whiteValue: 220 percentage: 40",
            log.toString());
  }

  @Test
  public void testMockBlurWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nblur "
            + "koala koala-blur split 20\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-blur percentage: 20",
            log.toString());
  }

  @Test
  public void testMockSharpenWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nsharpen "
            + "koala koala-sharp split 60\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-sharp percentage: 60",
            log.toString());
  }

  @Test
  public void testMockSepiaWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nsepia "
            + "koala koala-sepia split 60\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-sepia percentage: 60",
            log.toString());
  }

  @Test
  public void testMockVisualizeValueWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nvalue-component "
            + "koala koala-value split 60\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-value percentage: 60",
            log.toString());
  }

  @Test
  public void testMockVisualizeIntensityWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nintensity-component "
            + "koala koala-intensity split 60\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-intensity percentage: 60",
            log.toString());
  }

  @Test
  public void testMockVisualizeLumaWithSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nluma-component "
            + "koala koala-luma split 20\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
            new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala source: koala destination: koala-luma percentage: 20",
            log.toString());
  }

  @Test
  public void testMockRun() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("run test/testScript1");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingMIMEModel model =
        new MockImageProcessingMIMEModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingMIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala"
            + "Source: koala"
            + "Component: RED destinationImage: red-koala"
            + "Component: GREEN destinationImage: green-koala"
            + "Component: BLUE destinationImage: blue-koala"
            + "Source: blue-koala Destination: blue-bright-koala Intensity: 25.0"
            + "Destination: blueTintKoala"
            + "Component: RED sourceImage: red-koala"
            + "Component: GREEN sourceImage: green-koala"
            + "Component: BLUE sourceImage: blue-bright-koala"
            + "ImageName: blueTintKoala",
        log.toString());
  }
}