package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "save". This uses saveImage method of model and save method of Image
 * IO Controller.
 */

public class Save implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the Save object by initializing the class variable with the given parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public Save(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String destinationPath = input.get(1);
    String imageName = input.get(2);
    try {
      imageio.save(model.saveImage(imageName), destinationPath);
    } catch (Exception e) {
      throw new Exception(
          String.format("Could not save Image %s to %s. Please try again or check input command.\n",
              imageName, destinationPath));
    }
  }
}
