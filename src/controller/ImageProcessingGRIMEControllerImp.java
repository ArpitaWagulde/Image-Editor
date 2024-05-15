package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import controller.commands.LumaComponent;
import model.ImageProcessingGRIMEModel;
import view.ViewInterface;

/**
 * This class belongs to the controller for the ImageProcessingGRIME. It will handle starting the
 * session, calling methods from model to perform functionalities and handle the exceptions and how
 * to display it to the user.
 */

public class ImageProcessingGRIMEControllerImp extends ImageProcessingMIMEControllerImp implements
    ImageProcessingGRIMEController {

  private final ImageProcessingGRIMEModel model;
  private final ViewInterface view;
  Map<String, Function<List<String>, ImageProcessingCommand>> knownCommands;

  private String currentImageName;
  private String previousImageName;

  Function<List<String>, ImageProcessingCommand> cmd;
  ImageProcessingCommand c;

  /**
   * This method constructs an ImageProcessingGIMEController object with the given parameters. Which
   * enables this controller to handle the input and output with the given model and view.
   *
   * @param model Represents ImageProcessingGRIMEModel
   * @param view  Represents ViewInterface
   */
  public ImageProcessingGRIMEControllerImp(ImageProcessingGRIMEModel model, ViewInterface view) {
    super(model, null, null);
    knownCommands = ImageProcessingMIMEControllerImp.addCommands();
    knownCommands.put("greyscale", LumaComponent::new);

    this.model = model;
    this.view = view;
    view.addCommands(this);
    currentImageName = "sample";
  }

  /**
   * This method constructs an ImageProcessingGIMEController object with the given parameters. Which
   * enables this controller to handle the input and output with the given model required for the
   * interactive command line interface.
   *
   * @param model Represents ImageProcessingGRIMEModel
   * @param in    Represents the input of type Readable
   * @param out   Represents the output of type Appendable
   */
  public ImageProcessingGRIMEControllerImp(ImageProcessingGRIMEModel model, Readable in,
      Appendable out) {
    super(model, in, out);
    this.model = model;
    this.view = null;
  }

  private void commonMethodToUseCommands(String command, List<String> input) throws Exception {
    cmd = knownCommands.getOrDefault(command, null);
    try {
      c = cmd.apply(input);
      c.goCommand(model);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public void loadImage(String filePath) {
    List<String> input = new ArrayList<>();
    String command = "load";
    input.add(command);
    input.add(filePath);
    input.add(currentImageName);
    previousImageName = currentImageName;
    try {
      commonMethodToUseCommands(command, input);
      view.refreshImage(model.getImage(currentImageName), model.getHistogram(currentImageName));
    } catch (Exception ex) {
      view.showErrorMessage(ex.getMessage());
    }
  }

  @Override
  public void saveImage(String filePath) {
    List<String> input = new ArrayList<>();
    String command = "save";
    input.add(command);
    input.add(filePath);
    input.add(currentImageName);
    try {
      commonMethodToUseCommands(command, input);
    } catch (Exception ex) {
      view.showErrorMessage(
          "Could not save image. Please check file format provide and try again.");
    }
  }

  @Override
  public void preview(int previewPercentage) {
    view.refreshImage(model.preview(previousImageName, currentImageName, previewPercentage),
        model.getHistogram(currentImageName));
  }

  @Override
  public void confirmOperation() {
    view.refreshImage(model.getImage(currentImageName), model.getHistogram(currentImageName));
  }

  @Override
  public void cancelOperation() {
    view.refreshImage(model.getImage(previousImageName), model.getHistogram(previousImageName));
    currentImageName = previousImageName;
  }

  private void commonMethodToUsePreviewForCommandsWithNoExtraParameters(String command,
      int previewPercentage) {
    List<String> input = new ArrayList<>();
    input.add(command);
    previousImageName = currentImageName;
    currentImageName = currentImageName + command;
    input.add(previousImageName);
    input.add(currentImageName);
    try {
      commonMethodToUseCommands(command, input);
      preview(previewPercentage);
    } catch (Exception ex) {
      currentImageName = previousImageName;
      view.showErrorMessage(ex.getMessage());
    }
  }

  @Override
  public void visualizeRed(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("red-component", previewPercentage);
  }

  @Override
  public void visualizeGreen(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("green-component", previewPercentage);
  }

  @Override
  public void visualizeBlue(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("blue-component", previewPercentage);
  }

  @Override
  public void flipImageVertically(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("vertical-flip", previewPercentage);
  }

  @Override
  public void flipImageHorizontally(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("horizontal-flip", previewPercentage);
  }

  @Override
  public void blurImage(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("blur", previewPercentage);
  }

  @Override
  public void sharpenImage(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("sharpen", previewPercentage);
  }

  @Override
  public void convertToGreyscale(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("greyscale", previewPercentage);
  }

  @Override
  public void convertToSepia(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("sepia", previewPercentage);
  }

  @Override
  public void compressImage(int compressPercentage, int previewPercentage) {
    List<String> input = new ArrayList<>();
    String command = "compress";
    input.add(command);
    input.add(compressPercentage + "");
    previousImageName = currentImageName;
    currentImageName = currentImageName + command;
    input.add(previousImageName);
    input.add(currentImageName);
    try {
      commonMethodToUseCommands(command, input);
      preview(previewPercentage);
    } catch (Exception ex) {
      currentImageName = previousImageName;
      view.showErrorMessage(ex.getMessage());
    }
  }

  @Override
  public void colorCorrectImage(int previewPercentage) {
    commonMethodToUsePreviewForCommandsWithNoExtraParameters("color-correct", previewPercentage);
  }

  @Override
  public void levelsAdjustImage(String blackValue, String midValue, String whiteValue,
      int previewPercentage) {
    List<String> input = new ArrayList<>();
    String command = "levels-adjust";
    input.add(command);
    input.add(blackValue);
    input.add(midValue);
    input.add(whiteValue);
    previousImageName = currentImageName;
    currentImageName = currentImageName + command;
    input.add(previousImageName);
    input.add(currentImageName);
    try {
      commonMethodToUseCommands(command, input);
      preview(previewPercentage);
    } catch (Exception ex) {
      currentImageName = previousImageName;
      view.showErrorMessage(ex.getMessage());
    }
  }
}
