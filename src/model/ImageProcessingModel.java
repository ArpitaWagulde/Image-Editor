package model;

import java.util.List;
import java.util.Map;

/**
 * This interface represents all the methods an ImageProcessingModel should have. It offers various
 * functionalities like load and save an image, visualize is components, flip, brighten, darken,
 * blur, sharp, apply filters etc.
 */
public interface ImageProcessingModel {

  /**
   * A method to load the image of any type.
   *
   * @param imageData ImageData to be saved of type Map whose key is Component and Value is 2D List
   *                  of pixels
   * @param imageName ImageName to load the image into in memory and then further use it for
   *                  operations
   * @throws Exception if there was an issue in loading an image
   */

  void loadImage(Map<Component, List<List<Integer>>> imageData, String imageName) throws Exception;

  /**
   * A method to save the image of any type.
   *
   * @param imageName ImageName to be saved
   * @return Map whose key is Component and Value is 2D List of pixels
   * @throws IllegalArgumentException if the path provided does not exist
   * @throws Exception                if there was an issue in saving the image
   */

  Map<Component, List<List<Integer>>> saveImage(String imageName)
      throws IllegalArgumentException, Exception;

  /**
   * A method to visualize the components.
   *
   * @param sourceImageName      image whose components are to be visualized
   * @param destinationImageName destination image name to store the result
   * @param comp                 component which is to be visualized
   */
  void visualizeComponent(String sourceImageName, String destinationImageName,
      Component comp);

  /**
   * A method to visualize the value. A value is maximum of all the components of a pixel. This
   * results in a greyscale image.
   *
   * @param sourceImageName      image whose value is to be visualized.
   * @param destinationImageName destination image name to store the result
   */
  void visualizeValue(String sourceImageName, String destinationImageName);

  /**
   * A method to visualize the intensity. An intensity is average of all the components of a pixel.
   * This results in a greyscale image.
   *
   * @param sourceImageName      image whose intensity is to be visualized.
   * @param destinationImageName destination image name to store the result
   */
  void visualizeIntensity(String sourceImageName, String destinationImageName);

  /**
   * A method to visualize the luma. It is the weighted sum of all the components of a pixel. This
   * results in a greyscale image.
   *
   * @param sourceImageName      image whose luma is to be visualized.
   * @param destinationImageName destination image name to store the result
   */
  void visualizeLuma(String sourceImageName, String destinationImageName);

  /**
   * A method to flip the image horizontally.
   *
   * @param sourceImageName      image to be flipped
   * @param destinationImageName destination image name to store the result
   */
  void flipHorizontally(String sourceImageName, String destinationImageName);

  /**
   * A method to flip the image vertically.
   *
   * @param sourceImageName      image to be flipped
   * @param destinationImageName destination image name to store the result
   */
  void flipVertically(String sourceImageName, String destinationImageName);

  /**
   * A method to brighten or darken the image based on the intensity passed.
   *
   * @param sourceImageName      image to operated on
   * @param destinationImageName destination image name to store the result
   * @param intensity            A float value by which the image will be brightened or darkened
   */
  void brighten(String sourceImageName, String destinationImageName, float intensity);

  /**
   * A method split the image into its components.
   *
   * @param sourceImageName       image to be split
   * @param destinationImageNames A map of components and destination image names to store the
   *                              result after split
   */
  void split(String sourceImageName, Map<Component, String> destinationImageNames);

  /**
   * A method to combine the components from multiple images.
   *
   * @param destinationImageName  destination image name to store the result
   * @param sourceSplitImageNames A map of components and images from whom the new image will be
   *                              created
   */
  void combine(String destinationImageName, Map<Component, String> sourceSplitImageNames)
      throws IllegalArgumentException;

  /**
   * A method to blur the image.
   *
   * @param sourceImageName      image to be blurred
   * @param destinationImageName destination image name to store the result
   */
  void blur(String sourceImageName, String destinationImageName);

  /**
   * A method to sharpen the image.
   *
   * @param sourceImageName      image to be sharpened
   * @param destinationImageName destination image name to store the result
   */
  void sharpen(String sourceImageName, String destinationImageName);

  /**
   * A method to apply sepia filter on the given image.
   *
   * @param sourceImageName      image to be applied filter on
   * @param destinationImageName destination image name to store the result
   */
  void convertToSepia(String sourceImageName, String destinationImageName);
}
