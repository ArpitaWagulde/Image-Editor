package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "compress". This uses compress method of model.
 */

public class Compress implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the Compress object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public Compress(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    int compression = Integer.parseInt(input.get(1));
    String sourceImageName = input.get(2);
    String destinationImageName = input.get(3);
    try {
      model.compress(sourceImageName, destinationImageName, compression);
    } catch (Exception e) {
      throw new Exception("Could not compress. "
          + "Please try again or check input command.\n");
    }
  }
}
