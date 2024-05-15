package imageprocessinggrimetest;

import static org.junit.Assert.assertEquals;

import controller.ImageProcessingGRIMEController;
import controller.ImageProcessingGRIMEControllerImp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import model.Component;
import model.ImageProcessingGRIMEModel;
import org.junit.Before;
import org.junit.Test;

import view.ViewInterface;

/**
 * A class to test ImageProcessingGRIMEController.
 */
public class ImageProcessingGRIMEControllerTest {

  /**
   * An inner class that mock the model to test the controller.
   */
  public static class MockImageProcessingGRIMEModel implements ImageProcessingGRIMEModel {

    StringBuilder logger;

    public MockImageProcessingGRIMEModel(StringBuilder log) {
      this.logger = log;
    }

    @Override
    public void compress(String sourceImageName, String destinationImageName, Integer percentage)
        throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName +
          " percentage: " + percentage);

    }

    @Override
    public void histogram(String sourceImageName, String destinationImageName) {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName);
    }

    @Override
    public void colorCorrect(String sourceImageName, String destinationImageName) {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName);
    }

    @Override
    public void colorCorrect(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    @Override
    public void levelAdjustment(String sourceImageName, String destinationImageName,
        Integer blackValue, Integer midValue, Integer whiteValue)
        throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " blackValue: " + blackValue + " midValue: " + midValue + " whiteValue: " + whiteValue);
    }

    @Override
    public void levelAdjustment(String sourceImageName, String destinationImageName,
        Integer blackValue, Integer midValue, Integer whiteValue,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " blackValue: " + blackValue + " midValue: " + midValue + " whiteValue: " + whiteValue
          + " percentage: " + percentage);
    }

    @Override
    public void blur(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    @Override
    public void blur(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void sharpen(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    @Override
    public void sharpen(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void convertToSepia(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    @Override
    public void convertToSepia(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void visualizeValue(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    @Override
    public void visualizeValue(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void visualizeIntensity(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    @Override
    public void visualizeIntensity(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void visualizeLuma(String sourceImageName, String destinationImageName,
        Integer percentage) throws IllegalArgumentException {
      logger.append(" source: " + sourceImageName + " destination: " + destinationImageName
          + " percentage: " + percentage);
    }

    @Override
    public void visualizeLuma(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void loadImage(Map<Component, List<List<Integer>>> imageData,
        String imageName) throws Exception {
      logger.append("LoadImageName: " + imageName);
    }

    @Override
    public Map<Component, List<List<Integer>>> saveImage(String imageName)
        throws Exception {
      logger.append("SaveImageName: " + imageName);
      return null;
    }

    @Override
    public void visualizeComponent(String sourceImageName, String destinationImageName,
        Component comp) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName
          + " Component: " + comp);
    }

    @Override
    public void flipHorizontally(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void flipVertically(String sourceImageName, String destinationImageName) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName);
    }

    @Override
    public void brighten(String sourceImageName, String destinationImageName, float intensity) {
      logger.append("Source: " + sourceImageName + " Destination: " + destinationImageName
          + " Intensity: " + intensity);
    }

    @Override
    public void split(String sourceImageName, Map<Component, String> destinationImageNames) {
      logger.append("Source: " + sourceImageName);
      for (Component c : destinationImageNames.keySet()) {
        logger.append("Component: " + c + " destinationImage: " + destinationImageNames.get(c));
      }
    }

    @Override
    public void combine(String destinationImageName, Map<Component, String> sourceSplitImageNames)
        throws IllegalArgumentException {
      logger.append("Destination: " + destinationImageName);
      for (Component c : sourceSplitImageNames.keySet()) {
        logger.append("Component: " + c + " sourceImage: " + sourceSplitImageNames.get(c));
      }
    }

    @Override
    public BufferedImage preview(String sourceImageName, String newImageName, Integer percentage) {
      logger.append(
          "Previewed: " + sourceImageName + " " + newImageName + " with percentage " + percentage);
      return null;
    }

    @Override
    public BufferedImage getImage(String imageName) {
      logger.append("Buffered Image of image: " + imageName);
      return null;
    }

    @Override
    public BufferedImage getHistogram(String imageName) {
      logger.append("Buffered Image of histogram: " + imageName);
      return null;
    }
  }

  /**
   * An inner class that mock the view to test the controller.
   */
  public static class MockGUIView extends JFrame implements ViewInterface {

    StringBuilder logger;

    public MockGUIView(StringBuilder log) {
      this.logger = log;
    }

    @Override
    public void showErrorMessage(String error) {
      logger.append(error);
    }

    @Override
    public void addCommands(ImageProcessingGRIMEController controller) {
      //This method does not do anything as this is used to implement command callback design
      // which cannot be tested as this method adds listeners.
    }

    @Override
    public void refreshImage(BufferedImage image, BufferedImage histogram) {
      logger.append("Images refreshed");
    }
  }

  ImageProcessingGRIMEController controller;
  ImageProcessingGRIMEModel model;
  StringBuilder log;

  Reader in;
  StringBuffer out;

  @Before
  public void setUp() {
    log = new StringBuilder();
    model = new ImageProcessingGRIMEControllerTest.
        MockImageProcessingGRIMEModel(log);
    ViewInterface view = new ImageProcessingGRIMEControllerTest.MockGUIView(log);
    out = new StringBuffer();
    controller = new ImageProcessingGRIMEControllerImp(model, view);
  }

  @Test
  public void testMockLoadImage() {
    controller.loadImage("images/manhattan-small.png");
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testSaveImage() {
    controller.loadImage("images/manhattan-small.png");
    controller.saveImage("images/manhattan-small.png");
    assertEquals("LoadImageName: sample"
            + "Buffered Image of image: sample"
            + "Buffered Image of histogram: sample"
            + "Images refreshed"
            + "SaveImageName: sample"
            + "Could not save image. Please check file format provide and try again.",
        log.toString());
  }

  @Test
  public void testMockVisualizeComponentRed() {
    controller.loadImage("images/manhattan-small.png");
    controller.visualizeRed(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "red-component Component: RED"
        + "Previewed: sample samplered-component with percentage 50"
        + "Buffered Image of histogram: samplered-component"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockVisualizeComponentGreen() {
    controller.loadImage("images/manhattan-small.png");
    controller.visualizeGreen(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "green-component Component: GREEN"
        + "Previewed: sample samplegreen-component with percentage 50"
        + "Buffered Image of histogram: samplegreen-component"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockVisualizeComponentBlue() {
    controller.loadImage("images/manhattan-small.png");
    controller.visualizeBlue(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "blue-component Component: BLUE"
        + "Previewed: sample sampleblue-component with percentage 50"
        + "Buffered Image of histogram: sampleblue-component"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockFlipHorizontally() {
    controller.loadImage("images/manhattan-small.png");
    controller.flipImageHorizontally(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "horizontal-flip"
        + "Previewed: sample samplehorizontal-flip with percentage 50"
        + "Buffered Image of histogram: samplehorizontal-flip"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockFlipVertically() {
    controller.loadImage("images/manhattan-small.png");
    controller.flipImageVertically(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "vertical-flip"
        + "Previewed: sample samplevertical-flip with percentage 50"
        + "Buffered Image of histogram: samplevertical-flip"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockBlur() {
    controller.loadImage("images/manhattan-small.png");
    controller.blurImage(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "blur"
        + "Previewed: sample sampleblur with percentage 50"
        + "Buffered Image of histogram: sampleblur"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockSharpen() {
    controller.loadImage("images/manhattan-small.png");
    controller.sharpenImage(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "sharpen"
        + "Previewed: sample samplesharpen with percentage 50"
        + "Buffered Image of histogram: samplesharpen"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockSepia() {
    controller.loadImage("images/manhattan-small.png");
    controller.convertToSepia(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "sepia"
        + "Previewed: sample samplesepia with percentage 50"
        + "Buffered Image of histogram: samplesepia"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockCompress() {
    controller.loadImage("images/manhattan-small.png");
    controller.compressImage(50, 50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed "
        + "source: sample destination: sample"
        + "compress percentage: 50"
        + "Previewed: sample samplecompress with percentage 50"
        + "Buffered Image of histogram: samplecompress"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockColorCorrect() {
    controller.loadImage("images/manhattan-small.png");
    controller.colorCorrectImage(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed "
        + "source: sample destination: sample"
        + "color-correct"
        + "Previewed: sample samplecolor-correct with percentage 50"
        + "Buffered Image of histogram: samplecolor-correct"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockColorLevelAdjustment() {
    controller.loadImage("images/manhattan-small.png");
    controller.levelsAdjustImage("10", "20", "100", 50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed "
        + "source: sample destination: sample"
        + "levels-adjust blackValue: 10 midValue: 20 whiteValue: 100"
        + "Previewed: sample samplelevels-adjust with percentage 50"
        + "Buffered Image of histogram: samplelevels-adjust"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testGreyscale() {
    controller.loadImage("images/manhattan-small.png");
    controller.convertToGreyscale(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Source: sample Destination: sample"
        + "greyscale"
        + "Previewed: sample samplegreyscale with percentage 50"
        + "Buffered Image of histogram: samplegreyscale"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testPreview() {
    controller.loadImage("images/manhattan-small.png");
    controller.preview(50);
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Previewed: sample sample with percentage 50"
        + "Buffered Image of histogram: sample"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testConfirmOperation() {
    controller.loadImage("images/manhattan-small.png");
    controller.confirmOperation();
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testCancelOperation() {
    controller.loadImage("images/manhattan-small.png");
    controller.cancelOperation();
    assertEquals("LoadImageName: sample"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed"
        + "Buffered Image of image: sample"
        + "Buffered Image of histogram: sample"
        + "Images refreshed", log.toString());
  }

  @Test
  public void testMockLoadAndQuit() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("LoadImageName: koala", log.toString());
  }

  @Test
  public void testMockSaveImage() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nsave "
        + "images/koalanew.jpg koala\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("LoadImageName: koala" + "SaveImageName: koala", log.toString());
  }

  @Test
  public void testMockVisualizeComponentRedCLI() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nred-component "
        + "koala koala-red\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals(
        "LoadImageName: koala" + "Source: koala" + " Destination: koala-red Component: RED",
        log.toString());
  }

  @Test
  public void testMockVisualizeComponentGreenCLI() throws IOException {
    in = new StringReader("load images/koala.ppm koala\ngreen-component "
        + "koala koala-green\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals(
        "LoadImageName: koala" + "Source: koala" + " Destination: koala-green Component: GREEN",
        log.toString());
  }

  @Test
  public void testMockVisualizeComponentBlueCLI() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nblue-component "
        + "koala koala-blue\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals(
        "LoadImageName: koala" + "Source: koala" + " Destination: koala-blue Component: BLUE",
        log.toString());
  }

  @Test
  public void testMockVisualizeValue() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nvalue-component "
        + "koala koala-value\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("LoadImageName: koala" + "Source: koala" + " Destination: koala-value",
        log.toString());
  }

  @Test
  public void testMockVisualizeIntensity() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nintensity-component "
        + "koala koala-intensity\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("LoadImageName: koala" + "Source: koala" + " Destination: koala-intensity",
        log.toString());
  }

  @Test
  public void testMockVisualizeLuma() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nluma-component "
        + "koala koala-luma\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("LoadImageName: koala" + "Source: koala" + " Destination: koala-luma",
        log.toString());
  }

  @Test
  public void testMockFlipHorizontallyCLI() throws IOException {
    in = new StringReader("load images/koala.ppm koala\nhorizontal-flip "
        + "koala koala-flippedH\nquit");
    controller = new ImageProcessingGRIMEControllerImp(model, in, out);
    controller.startSession();
    assertEquals("LoadImageName: koala" + "Source: koala" + " Destination: koala-flippedH",
        log.toString());
  }

}
