package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Component;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "rgb-split". This uses split method of model.
 */

public class RGBSplit implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the RGBSplit object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public RGBSplit(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String sourceImageName = input.get(1);
    Map<Component, String> destinationImageNames = new LinkedHashMap<>();
    destinationImageNames.put(Component.RED, input.get(2));
    destinationImageNames.put(Component.GREEN, input.get(3));
    destinationImageNames.put(Component.BLUE, input.get(4));
    try {
      model.split(sourceImageName, destinationImageNames);
    } catch (Exception e) {
      throw new Exception("Could not split Image. Please try again or check input command.\n");
    }
  }
}
