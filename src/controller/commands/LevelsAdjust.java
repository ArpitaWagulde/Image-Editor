package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "levels-adjust". This uses levelAdjustment method of model. If split
 * parameter and a percentage is passed as input parameters with the "levels-adjust" command then
 * the operation preview is performed.
 */

public class LevelsAdjust implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the LevelsAdjust object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */
  public LevelsAdjust(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    try {
      int black = Integer.parseInt(input.get(1));
      int mid = Integer.parseInt(input.get(2));
      int white = Integer.parseInt(input.get(3));
      String sourceImageName = input.get(4);
      String destinationImageName = input.get(5);
      if (input.contains("split")) {
        int percentage = Integer.parseInt(input.get(7));
        model.levelAdjustment(sourceImageName, destinationImageName, black, mid, white, percentage);
      } else {
        model.levelAdjustment(sourceImageName, destinationImageName, black, mid, white);
      }
    } catch (Exception e) {
      throw new Exception("Could not level adjust. "
          + "Please check input command.\n");
    }
  }
}
