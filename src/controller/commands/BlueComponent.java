package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.Component;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "blue-component". This uses visualize component method of model on
 * Blue component.
 */

public class BlueComponent implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the BlueComponent object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public BlueComponent(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String sourceImageName = input.get(1);
    String destinationImageName = input.get(2);
    try {
      model.visualizeComponent(sourceImageName, destinationImageName, Component.BLUE);
    } catch (Exception e) {
      throw new Exception("Could not retrieve Blue Component. "
          + "Please try again or check input command.\n");
    }
  }
}
