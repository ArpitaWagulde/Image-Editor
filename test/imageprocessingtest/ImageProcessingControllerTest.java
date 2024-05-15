package imageprocessingtest;

import static org.junit.Assert.assertEquals;

import controller.ImageProcessingControllerImp;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedHashMap;

import controller.ImageProcessingController;
import model.Component;
import model.Image;
import model.ImageProcessingModel;

/**
 * This class will test the controller of this MVC using mock model.
 */

public class ImageProcessingControllerTest {

  /**
   * An inner class that mock the model to test the controller.
   */
  public static class MockImageProcessingModel implements ImageProcessingModel {

    Map<String, Image> imageProcessingSession;
    StringBuilder logger;

    public MockImageProcessingModel(StringBuilder log, Map<String, Image> session) {
      this.imageProcessingSession = session;
      this.logger = log;
    }

    @Override
    public void loadImage(Map<Component, List<List<Integer>>> imageData, String imageName)
        throws IllegalArgumentException {
      logger.append("ImageName: " + imageName);
      imageProcessingSession.put(imageName, null);
    }

    @Override
    public Map<Component, List<List<Integer>>> saveImage(String imageName)
        throws IllegalArgumentException {
      logger.append("ImageName: " + imageName);
      return null;
    }

    @Override
    public void visualizeComponent(String sourceImageName, String destinationImageName,
        Component comp) throws IllegalArgumentException {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName
          + " Component: " + comp);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void visualizeValue(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void visualizeIntensity(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void visualizeLuma(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void flipHorizontally(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void flipVertically(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void brighten(String sourceImageName, String destinationImageName, float intensity) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName
          + " Intensity: " + intensity);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void split(String sourceImageName, Map<Component, String> destinationImageNames) {
      logger.append("Source: " + sourceImageName);
      for (Component c : destinationImageNames.keySet()) {
        logger.append("Component: " + c + " destinationImage: " + destinationImageNames.get(c));
        imageProcessingSession.put(destinationImageNames.get(c), null);
      }
    }

    @Override
    public void combine(String destinationImageName,
        Map<Component, String> sourceSplitImageNames)
        throws IllegalArgumentException {
      logger.append("Destination: " + destinationImageName);
      for (Component c : sourceSplitImageNames.keySet()) {
        logger.append("Component: " + c + " sourceImage: " + sourceSplitImageNames.get(c));
        imageProcessingSession.put(sourceSplitImageNames.get(c), null);
      }
    }

    @Override
    public void blur(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void sharpen(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }

    @Override
    public void convertToSepia(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
      imageProcessingSession.put(destinationImageName, null);
    }
  }

  @Test
  public void testMockLoadAndQuit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nquit");

    StringBuilder log = new StringBuilder();
    Map<String, Image> testSession = new LinkedHashMap<>();
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
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
    MockImageProcessingModel model = new MockImageProcessingModel(log, testSession);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, out);
    controller.startSession();
    assertEquals("ImageName: koala" + "Source: koala" + " Destination: koala-sepia",
        log.toString());
  }
}