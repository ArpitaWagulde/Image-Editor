package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.Component;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "red-component". This uses visualize component method of model on Red
 * component.
 */

public class RedComponent implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the RedComponent object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public RedComponent(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String sourceImageName = input.get(1);
    String destinationImageName = input.get(2);
    try {
      model.visualizeComponent(sourceImageName, destinationImageName, Component.RED);
    } catch (Exception e) {
      throw new Exception("Could not retrieve Red Component. "
          + "Please try again or check input command.\n");
    }
  }
}
