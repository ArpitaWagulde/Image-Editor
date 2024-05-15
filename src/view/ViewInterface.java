package view;

import controller.ImageProcessingGRIMEController;
import java.awt.image.BufferedImage;

/**
 * This interface represents all the methods a view should for Graphical Image Manipulation and
 * Enhancement.
 */

public interface ViewInterface {

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error String error message to transmit to the view
   */
  void showErrorMessage(String error);

  /**
   * A method to add the commands of the controller as action listeners.
   *
   * @param controller Controller of type ImageProcessingGRIMEController to call its commands
   */
  void addCommands(ImageProcessingGRIMEController controller);

  /**
   * A method to refresh the images to be displayed on the view.
   *
   * @param image     image to be displayed
   * @param histogram histogram of the image being displayed
   */
  void refreshImage(BufferedImage image, BufferedImage histogram);
}
