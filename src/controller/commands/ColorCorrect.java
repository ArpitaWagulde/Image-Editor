package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "color-correct". This uses colorCorrect method of model. If split
 * parameter and a percentage is passed as input parameters with the "color-correct" command then
 * the operation preview is performed.
 */

public class ColorCorrect implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the ColorCorrect object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public ColorCorrect(List<String> input) {
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
        model.colorCorrect(sourceImageName, destinationImageName, percentage);
      } else {
        model.colorCorrect(sourceImageName, destinationImageName);
      }
    } catch (Exception e) {
      throw new Exception("Could not color correct. "
          + "Please try again or check input command.\n");
    }
  }
}
