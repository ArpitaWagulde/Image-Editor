package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.List;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "load". This uses loadImage method of model and load method of Image
 * IO Controller.
 */

public class Load implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the Load object by initializing the class variable with the given parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public Load(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String imagePath = input.get(1);
    String imageName = input.get(2);
    try {
      model.loadImage(imageio.load(imagePath), imageName);
    } catch (Exception e) {
      throw new Exception("Could not load Image. Please try again or check input command.\n");
    }
  }
}
