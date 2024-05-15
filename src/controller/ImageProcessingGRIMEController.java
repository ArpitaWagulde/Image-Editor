package controller;

/**
 * This class represents controller interface for the ImageProcessing required for the graphical
 * representation. An ImageProcessingGRIME Controller will handle all the functionalities a
 * controller should in MVC model with Graphical user interface. This interface mainly deals with
 * command callbacks. The view calls methods of this interface and the controller then calls methods
 * of the model in its implementation.
 */
public interface ImageProcessingGRIMEController extends ImageProcessingController {

  /**
   * A method to load the image.
   *
   * @param filePath FilePath of type String to load the image from
   */
  void loadImage(String filePath);

  /**
   * A method to save the image.
   *
   * @param filePath FilePath of type String to save the image at
   */
  void saveImage(String filePath);

  /**
   * A method to preview the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the images
   */
  void preview(int previewPercentage);

  /**
   * A method to confirm the operation after the preview.
   */
  void confirmOperation();

  /**
   * A method to cancel the operation after the preview.
   */
  void cancelOperation();

  /**
   * A method to visualize the red component of the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void visualizeRed(int previewPercentage);

  /**
   * A method to visualize the green component of the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void visualizeGreen(int previewPercentage);

  /**
   * A method to visualize the blue component of the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void visualizeBlue(int previewPercentage);

  /**
   * A method to flip the image horizontally.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void flipImageVertically(int previewPercentage);

  /**
   * A method to flip the image vertically.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void flipImageHorizontally(int previewPercentage);

  /**
   * A method to blur the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void blurImage(int previewPercentage);

  /**
   * A method to sharpen the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void sharpenImage(int previewPercentage);

  /**
   * A method to convert the image to greyscale.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void convertToGreyscale(int previewPercentage);

  /**
   * A method to apply the sepia filter to the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void convertToSepia(int previewPercentage);

  /**
   * A method to compress the image.
   *
   * @param compressPercentage CompressPercentage of type integer to compress the image
   * @param previewPercentage  PreviewPercentage of type integer to preview the operation
   */
  void compressImage(int compressPercentage, int previewPercentage);

  /**
   * A method to color correct the image.
   *
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void colorCorrectImage(int previewPercentage);

  /**
   * A method to adjust the levels of the image.
   *
   * @param blackValue        shadow reference point of type Integer
   * @param midValue          middle reference point of type Integer
   * @param whiteValue        highlight reference point of type Integer
   * @param previewPercentage PreviewPercentage of type integer to preview the operation
   */
  void levelsAdjustImage(String blackValue, String midValue, String whiteValue,
      int previewPercentage);
}
