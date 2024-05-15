package controller.commands;

import controller.ImageIOController;
import controller.ImageProcessingCommand;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Component;
import model.ImageProcessingMIMEModel;

/**
 * This command implements the "rgb-combine". This uses combine method of model.
 */

public class RGBCombine implements ImageProcessingCommand {

  private final List<String> input;
  ImageIOController imageio;

  /**
   * This constructs the RGBCombine object by initializing the class variable with the given
   * parameters.
   *
   * @param input List of String which are parameters input by user
   */

  public RGBCombine(List<String> input) {
    this.input = input;
    this.imageio = new ImageIOController();
  }

  @Override
  public void goCommand(ImageProcessingMIMEModel model) throws Exception {
    String destinationImageName = input.get(1);
    Map<Component, String> sourceImageNames = new LinkedHashMap<>();
    sourceImageNames.put(Component.RED, input.get(2));
    sourceImageNames.put(Component.GREEN, input.get(3));
    sourceImageNames.put(Component.BLUE, input.get(4));
    try {
      model.combine(destinationImageName, sourceImageNames);
    } catch (Exception e) {
      throw new Exception("Could not combine Channels. "
          + "Please try again or check input command.\n");
    }
  }
}
