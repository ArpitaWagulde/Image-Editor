package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "sharpen". This uses sharpen method of model. If split parameter and
 * a percentage is passed as input parameters with the "sharpen" command then the operation preview
 * is performed.
 */

public class Sharpen implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the Sharpen object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public Sharpen(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String sourceImageName = input.get(1);
    String destinationImageName = input.get(2);
    try {
      if (input.contains("split")) {
        int percentage = Integer.parseInt(input.get(4));
        model.sharpen(sourceImageName, destinationImageName, percentage);
      } else {
        model.sharpen(sourceImageName, destinationImageName);
      }
    } catch (Exception e) {
      throw new Exception("Could not sharpen Image. Please try again or check input command.\n");
    }
  }
}
