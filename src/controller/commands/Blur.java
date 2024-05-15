package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "blur". This uses blur method of model. If split parameter and a
 * percentage is passed as input parameters with the "blur" command then the operation preview is
 * performed.
 */

public class Blur implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the Blur object by initializing the class variable with the given parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public Blur(List<String> input) {
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
        model.blur(sourceImageName, destinationImageName, percentage);
      } else {
        model.blur(sourceImageName, destinationImageName);
      }
    } catch (Exception e) {
      throw new Exception("Could not blur Image. Please try again or check input command.\n");
    }
  }
}
