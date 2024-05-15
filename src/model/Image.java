package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class represents an image and is part of the model. It also has getter for width and height
 * of the image and to get the pixels of the image. The image is represented as a map where each key
 * is a component and the value 2D List matrix represent the pixel data of the respective component.
 * Operations that are performed on a single image are implemented here.
 */
public class Image {

  private final Map<Component, List<List<Integer>>> imageData;
  private final int height;
  private final int width;
  Map<Component, int[]> histogram;

  /**
   * This method constructs an image based on the pixel data sent.
   *
   * @param imageData An Image Data that is to be saved which is a map where each key is a component
   *                  and the value 2D List matrix represent the pixel data of the respective
   *                  component.
   */

  public Image(Map<Component, List<List<Integer>>> imageData) {
    this.imageData = imageData;
    height = imageData.getOrDefault(Component.RED, null).size();
    width = imageData.getOrDefault(Component.RED, null).get(0).size();

    histogram = new LinkedHashMap<>();
    for (Component component : imageData.keySet()) {
      int[] pixel_frequencies = new int[256];
      for (int i = 0; i < imageData.get(component).size(); i++) {
        for (int j = 0; j < imageData.get(component).get(i).size(); j++) {
          pixel_frequencies[imageData.get(component).get(i).get(j)] += 1;
        }
      }
      histogram.put(component, pixel_frequencies);
    }
  }

  private Image convolute(Image image, List<List<Float>> filter) {
    List<List<Integer>> existingComponent;
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();
    int mid = filter.size() / 2;
    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : image.imageData.entrySet()) {
      existingComponent = mapElement.getValue();
      List<List<Integer>> newComponent = new ArrayList<>();
      for (int i = 0; i < image.getHeight(); i++) {
        newComponent.add(new ArrayList<>());
        for (int j = 0; j < image.getWidth(); j++) {
          float sum = 0;
          for (int k = 0; k < filter.size(); k++) {
            int m = i - mid + k;
            for (int l = 0; l < filter.get(k).size(); l++) {
              int n = j - mid + l;
              if (m >= 0 && m < existingComponent.size()
                  && n >= 0 && n < existingComponent.get(i).size()) {
                sum += (existingComponent.get(m).get(n) * filter.get(k).get(l));
              }
            }
          }
          newComponent.get(i).add(Math.min(Math.max(0, (int) (sum)), 255));
        }
      }
      newImageData.put(mapElement.getKey(), newComponent);
    }
    return new Image(newImageData);
  }

  private List<List<Integer>> matrixMultiplication(
      List<List<Integer>> componentMat, List<List<Float>> filterMat) {
    if (filterMat.get(0).size() != componentMat.size()) {
      throw new IllegalArgumentException(
          "The filter and the component sizes do not match "
              + "and hence cannot be multiplied with each other.");
    }
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < filterMat.get(0).size(); i++) {
      result.add(new ArrayList<>());
      float product = 0;
      for (int j = 0; j < componentMat.size(); j++) {
        product += filterMat.get(i).get(j) * componentMat.get(j).get(0);
      }
      result.get(i).add(Math.min(Math.max(0, (int) (product)), 255));
    }
    return result;
  }

  private List<List<Float>> convertFloatToList(float[][] arr) {
    List<List<Float>> arrList = new ArrayList<>();

    for (float[] rowArray : arr) {
      List<Float> rowList = new ArrayList<>();
      for (float val : rowArray) {
        rowList.add(val);
      }
      arrList.add(rowList);
    }

    return arrList;
  }

  private List<List<Integer>> resetToZero(List<List<Integer>> source) {
    List<List<Integer>> blankChannel = new ArrayList<>();
    for (int i = 0; i < source.size(); i++) {
      List<Integer> sourceRow = new ArrayList<>();
      for (int j = 0; j < source.get(0).size(); j++) {
        sourceRow.add(0);
      }
      blankChannel.add(sourceRow);
    }
    return blankChannel;
  }

  private void transformForHaar(List<Double> data) {
    List<Double> avg = new ArrayList<>();
    List<Double> diff = new ArrayList<>();

    for (int i = 0; i < data.size(); i += 2) {
      avg.add((data.get(i) + data.get(i + 1)) / Math.sqrt(2));
      diff.add((data.get(i) - data.get(i + 1)) / Math.sqrt(2));
    }

    for (int i = 0; i < avg.size(); i++) {
      data.set(i, avg.get(i));
      data.set(i + avg.size(), diff.get(i));
    }
  }

  private void inverseForInvHaar(List<Double> data) {
    List<Double> avg = new ArrayList<>();
    List<Double> diff = new ArrayList<>();
    int mid = data.size() / 2;
    for (int i = 0; i < mid; i++) {
      avg.add((data.get(i) + data.get(i + mid)) / Math.sqrt(2));
      diff.add((data.get(i) - data.get(i + mid)) / Math.sqrt(2));
    }
    for (int i = 0; i < avg.size(); i++) {
      data.set(i * 2, avg.get(i));
      data.set(i * 2 + 1, diff.get(i));
    }
  }

  private void haar(List<List<Double>> data, int size) {
    int c = size;
    while (c > 1) {
      //for each row i of the first c rows
      for (int i = 0; i < c; i++) {
        List<Double> transform = new ArrayList<>();
        for (int j = 0; j < c; j++) {
          transform.add(data.get(i).get(j));
        }
        transformForHaar(transform);
        for (int k = 0; k < transform.size(); k++) {
          data.get(i).set(k, transform.get(k));
        }
      }

      //for each col i of the first c columns
      for (int i = 0; i < c; i++) {
        ArrayList<Double> transform = new ArrayList<>();
        for (int j = 0; j < c; j++) {
          transform.add(data.get(j).get(i));
        }
        transformForHaar(transform);
        for (int k = 0; k < transform.size(); k++) {
          data.get(k).set(i, transform.get(k));
        }
      }
      c = c / 2;
    }
  }

  private void invHaar(List<List<Double>> data, int size) {
    int c = 2;
    while (c <= size) {
      //for each col i of the first c columns
      for (int i = 0; i < c; i++) {
        List<Double> transform = new ArrayList<>();
        for (int j = 0; j < c; j++) {
          transform.add(data.get(j).get(i));
        }
        inverseForInvHaar(transform);
        for (int k = 0; k < transform.size(); k++) {
          data.get(k).set(i, transform.get(k));
        }
      }

      //for each row i of the first c rows
      for (int i = 0; i < c; i++) {
        List<Double> transform = new ArrayList<>();
        for (int j = 0; j < c; j++) {
          transform.add(data.get(i).get(j));
        }
        inverseForInvHaar(transform);
        for (int k = 0; k < transform.size(); k++) {
          data.get(i).set(k, transform.get(k));
        }
      }

      c = c * 2;
    }
  }

  private int getNearestPowerOfTwo(int num) {
    int result = 1;
    while (result < num) {
      result = result * 2;
    }
    return result;
  }

  private List<List<Integer>> convert2DDoubleArrayToInt(
      List<List<Double>> data) {
    List<List<Integer>> newData = new ArrayList<>();
    for (List<Double> datum : data) {
      List<Integer> newRow = new ArrayList<>();
      for (Double dd : datum) {
        //Cap the pixel values on 0 to 255
        newRow.add(Math.max(0, Math.min(255, (int) Math.round(dd))));
      }
      newData.add(newRow);
    }
    return newData;
  }

  private int solveQuadratic(double[] coefficients, int x) {
    return (int) ((coefficients[0] * (x * x)) + (coefficients[1] * x) + coefficients[2]);
  }

  private double[] levelAdjustHelper(int b, int m, int w) {
    double[] coefficients = new double[3];
    int a = (int) ((Math.pow(b, 2) * (m - w)) - (b * (Math.pow(m, 2) - Math.pow(w, 2)))
        + (w * Math.pow(m, 2)) - (m * Math.pow(w, 2)));
    int aA = (b * 127) + (128 * w) - (255 * m);
    int aB = (int) ((Math.pow(b, 2) * (-127)) + (255 * Math.pow(m, 2)) - (128 * Math.pow(w, 2)));
    int aC = (int) (Math.pow(b, 2) * ((255 * m) - (128 * w))
        - (b * (255 * Math.pow(m, 2) - 128 * Math.pow(w, 2))));
    coefficients[0] = (aA * 1.0) / a;
    coefficients[1] = (aB * 1.0) / a;
    coefficients[2] = (aC * 1.0) / a;

    return coefficients;
  }

  /**
   * A common method to implement visualizations.
   *
   * @param combiner BiFunction which takes two integers as inputs and produces a result of type
   *                 integer
   * @param divider  Function which takes a String as input and produces a result of type String
   * @return a new Image after visualization
   */

  Image commonVisualize(BiFunction<Integer, Integer, Integer> combiner,
      Function<String, String> divider) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();

    List<List<Integer>> visualizeChannel = new ArrayList<>();

    for (int i = 0; i < this.getHeight(); i++) {
      List<Integer> visualizeRow = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        int toInsert = 0;
        for (Map.Entry<Component, List<List<Integer>>>
            mapElement : this.imageData.entrySet()) {
          toInsert =
              combiner.apply(toInsert, mapElement.getValue().get(i).get(j));
        }
        int div = 1;
        if (divider.apply("getDivider").equals("avg")) {
          div = this.imageData.size();
        }
        visualizeRow.add(toInsert / div);
      }
      visualizeChannel.add(visualizeRow);
    }

    // Add the same Channel to the New Image Data.
    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : this.imageData.entrySet()) {
      newImageData.put(mapElement.getKey(), visualizeChannel);
    }

    return new Image(newImageData);
  }

  /**
   * A common method to implement functionalities that use matrix multiplication. This is commonly
   * applicable when a filter is to be applied on an image.
   *
   * @param filter      represents a filter which is a 2D array of type float
   * @param incremental a function that takes in an integer and produces an integer
   * @return a new Image after a filter is applied on it
   */
  Image commonFunctionToUseMatMul(float[][] filter, Function<Integer, Integer> incremental) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();

    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : this.imageData.entrySet()) {
      newImageData.put(mapElement.getKey(), new ArrayList<>());
      for (int i = 0; i < this.getHeight(); i++) {
        newImageData.get(mapElement.getKey()).add(new ArrayList<>());
      }
    }

    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        List<List<Integer>> sourceComponents = new ArrayList<>();
        for (Map.Entry<Component, List<List<Integer>>>
            mapElement : this.imageData.entrySet()) {
          List<Integer> comp = new ArrayList<>();
          comp.add(mapElement.getValue().get(i).get(j));
          sourceComponents.add(comp);
        }
        List<List<Integer>> newComponents = matrixMultiplication(sourceComponents,
            convertFloatToList(filter));
        int k = 0;
        for (Map.Entry<Component, List<List<Integer>>>
            mapElement : newImageData.entrySet()) {
          mapElement.getValue().get(i).add(newComponents.get(k).get(0));
          k = incremental.apply(k);
        }
      }
    }

    return new Image(newImageData);
  }

  /**
   * A common method to implement functionalities that use convolution.
   *
   * @param filter represents a filter which is a 2D array of type float
   * @return a new Image after convolution is applied on it
   */
  Image commonFunctionToUseConvolute(float[][] filter) {
    return convolute(this, convertFloatToList(filter));
  }

  /**
   * A common method to flip the image.
   *
   * @param getRow BiFunction which takes two integers as inputs and produces a result of type
   *               integer
   * @param getCol BiFunction which takes two integers as inputs and produces a result of type
   *               integer
   * @return a new flipped Image
   */

  Image commonFlip(BiFunction<Integer, Integer, Integer> getRow,
      BiFunction<Integer, Integer, Integer> getCol) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();

    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : this.imageData.entrySet()) {
      List<List<Integer>> visualizeChannel = new ArrayList<>();
      int noRow = this.getHeight();
      int noCol = this.getWidth();
      for (int i = 0; i < noRow; i++) {
        List<Integer> visualizeRow = new ArrayList<>();
        for (int j = 0; j < noCol; j++) {
          visualizeRow.add(
              mapElement.getValue().get(getRow.apply(i, noRow)).get(getCol.apply(j, noCol)));
        }
        visualizeChannel.add(visualizeRow);
      }
      newImageData.put(mapElement.getKey(), visualizeChannel);
    }

    return new Image(newImageData);
  }

  /**
   * A method to visualize a component of an image.
   *
   * @param comp Component to be visualized
   * @return a new Image after its component is visualized
   */
  Image visualizeComponent(Component comp) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();

    // Need to see how we can set all values to zero efficiently.
    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : this.imageData.entrySet()) {
      if (mapElement.getKey() != comp) {
        newImageData.put(mapElement.getKey(), resetToZero(mapElement.getValue()));
      } else {
        newImageData.put(mapElement.getKey(), mapElement.getValue());
      }
    }
    return new Image(newImageData);
  }

  /**
   * A method to brighten or darken an Image. If intensity is positive, the image will be brightened
   * and darkened if it is negative.
   *
   * @param intensity Intensity with which image will be brightened or darkened
   * @return a new brightened or darkened Image
   */
  Image brighten(float intensity) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();

    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : this.imageData.entrySet()) {
      List<List<Integer>> visualizeChannel = new ArrayList<>();
      for (int i = 0; i < this.getHeight(); i++) {
        List<Integer> visualizeRow = new ArrayList<>();
        for (int j = 0; j < this.getWidth(); j++) {
          visualizeRow.add(
              Math.min(Math.max(0, (int) (mapElement.getValue().get(i).get(j) + intensity)), 255));
        }
        visualizeChannel.add(visualizeRow);
      }
      newImageData.put(mapElement.getKey(), visualizeChannel);
    }
    return new Image(newImageData);
  }

  /**
   * A method to compress the image.
   *
   * @param compressPercentage Compression percentage
   * @return a new compressed image
   */
  Image compress(Integer compressPercentage) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();
    Map<Component, List<List<Double>>> tempImageData = new LinkedHashMap<>();
    Set<Double> valueSet = new HashSet<>();
    //Find the new size of nearest power of 2 for square matrix
    int newSize = getNearestPowerOfTwo(Math.max(this.getHeight(), this.getWidth()));
    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : this.imageData.entrySet()) {
      List<List<Double>> deepCopyImage = this.getImageComponentOfTypeDouble(
          mapElement.getKey());
      //Pad with 0 to the new size
      while (deepCopyImage.size() < newSize) {
        deepCopyImage.add(new ArrayList<>(Collections.nCopies(newSize, 0.0)));
      }
      for (List<Double> row : deepCopyImage) {
        while (row.size() < newSize) {
          row.add(0.0);
        }
      }
      haar(deepCopyImage, newSize);
      tempImageData.put(mapElement.getKey(), deepCopyImage);
      //Find the number of unique and absolute values of all channels.
      for (List<Double> row : deepCopyImage) {
        for (Double rowData : row) {
          valueSet.add(Math.abs(rowData));
        }
      }
    }
    //Find the new size with the compress percentage.
    int initialSize = valueSet.size();
    int finalSize = (100 - compressPercentage) * initialSize / 100;
    //Remove the k smallest numbers.
    List<Double> sortedList = new ArrayList<>(valueSet);
    sortedList.sort(Collections.reverseOrder());
    sortedList.subList(finalSize, initialSize).clear();
    //Get the threshold.
    double threshold = 0;
    if (!sortedList.isEmpty()) {
      threshold = sortedList.get(finalSize - 1);
    }

    //Apply the threshold
    for (Map.Entry<Component, List<List<Double>>>
        mapElement : tempImageData.entrySet()) {
      List<List<Double>> deepCopyImage = mapElement.getValue();
      for (List<Double> row : deepCopyImage) {
        for (int j = 0; j < row.size(); j++) {
          double rowData = Math.abs(row.get(j));
          if ((rowData != 0 && rowData < threshold) || sortedList.isEmpty()) {
            row.set(j, 0.0);
          }
        }
      }
      invHaar(deepCopyImage, newSize);
      //Restore the original size.
      if (deepCopyImage.size() > this.getHeight()) {
        deepCopyImage.subList(this.getHeight(), deepCopyImage.size()).clear();
      }
      for (List<Double> row : deepCopyImage) {
        if (row.size() > this.getWidth()) {
          row.subList(this.getWidth(), row.size()).clear();
        }
      }
      newImageData.put(mapElement.getKey(), convert2DDoubleArrayToInt(deepCopyImage));
    }
    return new Image(newImageData);
  }

  private Map<Component, int[]> getImageHistogram() {
    Map<Component, int[]> deepHistogramCopy = new LinkedHashMap<>();
    for (Component comp : histogram.keySet()) {
      int[] newChannelValues = new int[256];
      for (int i = 0; i < 256; i++) {
        newChannelValues[i] = this.histogram.get(comp)[i];
      }
      deepHistogramCopy.put(comp, newChannelValues);
    }
    return deepHistogramCopy;
  }

  private Map<Component, List<List<Integer>>> convertBufferedToImage(BufferedImage img) {
    Map<Component, List<List<Integer>>> newImage = new LinkedHashMap<>();
    List<List<Integer>> rChannel = new ArrayList<>();
    List<List<Integer>> gChannel = new ArrayList<>();
    List<List<Integer>> bChannel = new ArrayList<>();
    for (int i = 0; i < img.getHeight(); i++) {
      List<Integer> rChannelRow = new ArrayList<>();
      List<Integer> gChannelRow = new ArrayList<>();
      List<Integer> bChannelRow = new ArrayList<>();
      for (int j = 0; j < img.getWidth(); j++) {
        int rgb = img.getRGB(j, i);
        rChannelRow.add((rgb >> 16) & 0xFF);
        gChannelRow.add((rgb >> 8) & 0xFF);
        bChannelRow.add(rgb & 0xFF);
      }
      rChannel.add(rChannelRow);
      gChannel.add(gChannelRow);
      bChannel.add(bChannelRow);
    }
    newImage.put(Component.RED, rChannel);
    newImage.put(Component.GREEN, gChannel);
    newImage.put(Component.BLUE, bChannel);
    return newImage;

  }

  /**
   * A method to build a histogram.
   *
   * @return a histogram image
   */

  Image buildHistogram() {
    BufferedImage newImage = new BufferedImage(256, 256,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D grid = newImage.createGraphics();
    grid.setColor(Color.white);
    grid.fillRect(0, 0, 256, 256);
    grid.setStroke(new BasicStroke(0.1f));
    Map<Component, int[]> histogram = this.getImageHistogram();
    int max = 0;
    for (Component c : histogram.keySet()) {
      // Decide Paint Brush Color
      switch (c) {
        case RED: {
          grid.setColor(Color.red);
          break;
        }
        case GREEN: {
          grid.setColor(Color.green);
          break;
        }
        case BLUE: {
          grid.setColor(Color.blue);
          break;
        }
        default: {
          grid.setColor(Color.black);
          break;
        }
      }

      // Get x and y points for drawing Line.
      for (int val = 0; val < histogram.get(c).length; val++) {
        if (histogram.get(c)[val] > max) {
          max = histogram.get(c)[val];
        }
      }
      int previous_val = 0;

      for (int row = 0; row < histogram.get(c).length; row++) {
        int current_val = histogram.get(c)[row];
        int pointTo = 255 - (int) (((current_val * 1.0) / max) * 255);
        int pointFrom = 255 - (int) (((previous_val * 1.0) / max) * 255);
        previous_val = current_val;
        grid.drawLine(row, pointFrom, row + 1, pointTo);
      }
    }
    return new Image(convertBufferedToImage(newImage));
  }

  Image colorCorrect() {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();
    int sum = 0;
    int count = 0;
    // Hashmap to store peak values.
    Map<Component, Integer> peakValues = new HashMap<>();

    // For every Component Find the pixel value with the highest frequency.
    for (Component c : this.histogram.keySet()) {
      int tempMax = 0;
      int index = 0;
      // Consider 10 - 245 Only.
      for (int i = 9; i < 245; i++) {
        if (tempMax < this.histogram.get(c)[i]) {
          tempMax = this.histogram.get(c)[i];
          index = i;
        }
      }
      // Storing the pixel value with the highest frequency.
      peakValues.put(c, index);
      sum += index;
      count += 1;
    }

    // Get the Average of all the peaks.
    int meanPeak = (int) ((sum * 1.0) / count);

    // Calculate the Offset for each channel and add it to all the values.
    for (Component c : this.imageData.keySet()) {
      int offset = meanPeak - peakValues.get(c);
      List<List<Integer>> channelData = this.getImageComponent(c);
      for (int i = 0; i < channelData.size(); i++) {
        for (int j = 0; j < channelData.get(0).size(); j++) {
          int currValue = channelData.get(i).get(j);
          // Perform 0 - 255 Clamping.
          int newValue = Math.min((Math.max(currValue + offset, 0)), 255);
          channelData.get(i).set(j, newValue);
        }
      }
      newImageData.put(c, channelData);
    }
    return new Image(newImageData);
  }

  Image levelAdjustment(Integer blackValue, Integer midValue, Integer whiteValue) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();

    double[] coefficients = levelAdjustHelper(blackValue, midValue, whiteValue);
    for (Component c : this.imageData.keySet()) {
      List<List<Integer>> channelData = this.getImageComponent(c);
      for (int i = 0; i < channelData.size(); i++) {
        for (int j = 0; j < channelData.get(0).size(); j++) {
          int currValue = channelData.get(i).get(j);
          int newValue = Math.min((Math.max(solveQuadratic(coefficients, currValue), 0)), 255);
          channelData.get(i).set(j, newValue);
        }
      }
      newImageData.put(c, channelData);
    }
    return new Image(newImageData);
  }

  /**
   * This method will send a deep copy of the pixel data of an image based on the component.
   *
   * @param comp Component whose pixel data is to be fetched
   * @return A 2D List Matrix as pixel data of type Integer
   */

  public List<List<Integer>> getImageComponent(Component comp) {
    List<List<Integer>> deepCopyImage = new ArrayList<>();
    for (int i = 0; i < imageData.get(comp).size(); i++) {
      List<Integer> deepCopyImageRow = new ArrayList<>(imageData.get(comp).get(i));
      deepCopyImage.add(deepCopyImageRow);
    }
    return deepCopyImage;
  }

  /**
   * This method will send a deep copy of the pixel data of an image based on the component as type
   * Double.
   *
   * @param comp Component whose pixel data is to be fetched
   * @return A 2D List Matrix as pixel data of type Double
   */

  private List<List<Double>> getImageComponentOfTypeDouble(Component comp) {
    List<List<Double>> deepCopyImage = new ArrayList<>();
    for (int i = 0; i < imageData.get(comp).size(); i++) {
      List<Double> deepCopyImageRow = new ArrayList<>();
      for (int j = 0; j < imageData.get(comp).get(i).size(); j++) {
        deepCopyImageRow.add((double) imageData.get(comp).get(i).get(j));
      }
      deepCopyImage.add(deepCopyImageRow);
    }
    return deepCopyImage;
  }

  /**
   * This method will send a deep copy of the pixel data of an image.
   *
   * @return a map where each key is a component and the value 2D List matrix represent the pixel
   *         data of the respective component.
   */
  public Map<Component, List<List<Integer>>> getDeepCopyOfImage() {
    Map<Component, List<List<Integer>>> deepCopyImage = new LinkedHashMap<>();
    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : this.imageData.entrySet()) {
      deepCopyImage.put(mapElement.getKey(), getImageComponent(mapElement.getKey()));
    }
    return deepCopyImage;
  }

  /**
   * A getter for the height of an image.
   *
   * @return Height of type integer
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * A getter for the width of an image.
   *
   * @return Width of type integer
   */
  public int getWidth() {
    return this.width;
  }
}
