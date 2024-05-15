package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "horizontal-flip". This uses flipHorizontally method of model.
 */

public class HorizontalFlip implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the HorizontalFlip object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public HorizontalFlip(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String sourceImageName = input.get(1);
    String destinationImageName = input.get(2);
    try {
      model.flipHorizontally(sourceImageName, destinationImageName);
    } catch (Exception e) {
      throw new Exception("Could not flip Image. Please try again or check input command.\n");
    }
  }
}

