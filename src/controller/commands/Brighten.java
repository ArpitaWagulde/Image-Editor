package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "brighten". This uses brighten method of model.
 */

public class Brighten implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the Brighten object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public Brighten(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    float intensity = Float.parseFloat(input.get(1));
    String sourceImageName = input.get(2);
    String destinationImageName = input.get(3);
    try {
      model.brighten(sourceImageName, destinationImageName, intensity);
    } catch (Exception e) {
      throw new Exception("Could not brighten Image. "
          + "Please try again or check input command.\n");
    }
  }
}
