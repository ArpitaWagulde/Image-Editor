package model;

import java.awt.image.BufferedImage;

/**
 * This interface represents all the methods for Graphical Image Manipulation and Enhancement.
 */

public interface ImageProcessingGRIMEModel extends ImageProcessingMIMEModel {

  /**
   * A method to preview the new image with another source image with the given percentage. The
   * image names are the names with which the image data is stored in session memory.
   *
   * @param sourceImageName A source image name to preview with
   * @param newImageName    A new image name to preview
   * @param percentage      A percentage of type integer to preview with
   * @return A buffered image of preview
   */
  BufferedImage preview(String sourceImageName, String newImageName, Integer percentage);

  /**
   * A method to get the buffered image of the given image name from the session memory.
   *
   * @param imageName An image name to fetch the image
   * @return A buffered image representation of the image data
   */
  BufferedImage getImage(String imageName);

  /**
   * A method to get the buffered image of histogram of the given image name from the session
   * memory.
   *
   * @param imageName An image name to fetch the histogram
   * @return A buffered image representation of the histogram data
   */
  BufferedImage getHistogram(String imageName);
}
