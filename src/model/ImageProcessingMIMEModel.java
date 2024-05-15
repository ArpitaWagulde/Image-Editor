package model;

/**
 * This interface represents all the methods an ImageProcessingModel has and a few more image
 * manipulation and enhancements. It offers various functionalities like compress, histogram, color
 * correction, level adjustments along with the functionality of operation preview.
 */

public interface ImageProcessingMIMEModel extends ImageProcessingModel {

  /**
   * A method to create a compressed version of an image.
   *
   * @param sourceImageName      image to be compressed
   * @param destinationImageName destination image name to store the result
   * @param percentage           compress percentage of type Integer
   * @throws IllegalArgumentException if compress percentage is not in range of 0 to 100
   */
  void compress(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;

  /**
   * A method to create a histogram of an image.
   *
   * @param sourceImageName      image whose histogram is to be created
   * @param destinationImageName destination image name to store the result
   */
  void histogram(String sourceImageName, String destinationImageName);

  /**
   * A method to color-correct an image by aligning the meaningful peaks of its histogram.
   *
   * @param sourceImageName      image which is to be color corrected
   * @param destinationImageName destination image name to store the result
   */
  void colorCorrect(String sourceImageName, String destinationImageName);

  /**
   * A method to color-correct an image by aligning the meaningful peaks of its histogram.
   *
   * @param sourceImageName      image which is to be color corrected
   * @param destinationImageName destination image name to store the result
   * @param percentage           percentage of the width to show preview
   * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
   */
  void colorCorrect(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;

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
  void levelAdjustment(String sourceImageName, String destinationImageName, Integer blackValue,
      Integer midValue, Integer whiteValue) throws IllegalArgumentException;

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
  void levelAdjustment(String sourceImageName, String destinationImageName, Integer blackValue,
      Integer midValue, Integer whiteValue, Integer percentage) throws IllegalArgumentException;

  /**
   * A method to blur the image.
   *
   * @param sourceImageName      image to be blurred
   * @param destinationImageName destination image name to store the result
   * @param percentage           percentage of the width to show preview
   * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
   */
  void blur(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;

  /**
   * A method to sharpen the image.
   *
   * @param sourceImageName      image to be sharpened
   * @param destinationImageName destination image name to store the result
   * @param percentage           percentage of the width to show preview
   * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
   */
  void sharpen(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;

  /**
   * A method to apply sepia filter on the given image.
   *
   * @param sourceImageName      image to be applied filter on
   * @param destinationImageName destination image name to store the result
   * @param percentage           percentage of the width to show preview
   * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
   */
  void convertToSepia(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;

  /**
   * A method to visualize the value. A value is maximum of all the components of a pixel. This
   * results in a greyscale image.
   *
   * @param sourceImageName      image whose value is to be visualized.
   * @param destinationImageName destination image name to store the result
   * @param percentage           percentage of the width to show preview
   * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
   */
  void visualizeValue(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;

  /**
   * A method to visualize the intensity. An intensity is average of all the components of a pixel.
   * This results in a greyscale image.
   *
   * @param sourceImageName      image whose intensity is to be visualized.
   * @param destinationImageName destination image name to store the result
   * @param percentage           percentage of the width to show preview
   * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
   */
  void visualizeIntensity(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;

  /**
   * A method to visualize the luma. It is the weighted sum of all the components of a pixel. This
   * results in a greyscale image.
   *
   * @param sourceImageName      image whose luma is to be visualized.
   * @param destinationImageName destination image name to store the result
   * @param percentage           percentage of the width to show preview
   * @throws IllegalArgumentException if Preview percentage is not in range of 0 to 100
   */

  void visualizeLuma(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException;
}
