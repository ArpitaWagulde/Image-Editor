package controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import model.ImageProcessingMIMEModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.commands.Load;
import controller.commands.Save;
import controller.commands.BlueComponent;
import controller.commands.GreenComponent;
import controller.commands.RedComponent;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.ColorCorrect;
import controller.commands.Compress;
import controller.commands.Histogram;
import controller.commands.HorizontalFlip;
import controller.commands.IntensityComponent;
import controller.commands.LevelsAdjust;
import controller.commands.LumaComponent;
import controller.commands.RGBCombine;
import controller.commands.RGBSplit;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueComponent;
import controller.commands.VerticalFlip;

/**
 * This class belongs to the controller for the ImageProcessingMIME. It will handle starting the
 * session, calling methods from model to perform functionalities and handle the exceptions part and
 * how to display it to the user.
 */

public class ImageProcessingMIMEControllerImp implements ImageProcessingController {

  private final ImageProcessingMIMEModel model;
  private final Readable in;
  private final Appendable out;
  private boolean runFlag;

  Map<String, Function<List<String>, ImageProcessingCommand>> knownCommands;

  static Map<String, Function<List<String>, ImageProcessingCommand>> addCommands() {
    Map<String, Function<List<String>, ImageProcessingCommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", Load::new);
    knownCommands.put("save", Save::new);
    knownCommands.put("red-component", RedComponent::new);
    knownCommands.put("green-component", GreenComponent::new);
    knownCommands.put("blue-component", BlueComponent::new);
    knownCommands.put("value-component", ValueComponent::new);
    knownCommands.put("intensity-component", IntensityComponent::new);
    knownCommands.put("luma-component", LumaComponent::new);
    knownCommands.put("horizontal-flip", HorizontalFlip::new);
    knownCommands.put("vertical-flip", VerticalFlip::new);
    knownCommands.put("brighten", Brighten::new);
    knownCommands.put("rgb-split", RGBSplit::new);
    knownCommands.put("rgb-combine", RGBCombine::new);
    knownCommands.put("blur", Blur::new);
    knownCommands.put("sharpen", Sharpen::new);
    knownCommands.put("sepia", Sepia::new);
    knownCommands.put("compress", Compress::new);
    knownCommands.put("histogram", Histogram::new);
    knownCommands.put("color-correct", ColorCorrect::new);
    knownCommands.put("levels-adjust", LevelsAdjust::new);
    return knownCommands;
  }

  /**
   * This method constructs an ImageProcessingMIMEController object with the given parameters. Which
   * enables this controller to handle the input and output with the given model.
   *
   * @param model Represents ImageProcessingMIMEModel
   * @param in    Represents the input of type Readable
   * @param out   Represents the output of type Appendable
   */

  public ImageProcessingMIMEControllerImp(ImageProcessingMIMEModel model, Readable in,
      Appendable out) {
    this.model = model;
    this.in = in;
    this.out = out;
    this.runFlag = true;
    knownCommands = addCommands();
  }

  /**
   * A private helper method to start and run the session.
   *
   * @param scan Scanner required to start the session
   */

  private void startSession(Scanner scan) {
    Function<List<String>, ImageProcessingCommand> cmd;
    ImageProcessingCommand c;
    while (runFlag) {
      try {
        String commandLine = scan.nextLine();
        if (!commandLine.startsWith("#")) {
          List<String> input = new ArrayList<>(Arrays.asList(commandLine.split(" ")));
          cmd = knownCommands.getOrDefault(input.get(0), null);
          if (input.get(0).equals("run")) {
            String scriptFile = input.get(1);
            File script = new File(scriptFile);
            if (script.exists() && script.isFile()) {
              try {
                Scanner fileScanner = new Scanner(script);
                this.out.append(String.format("Running Script File: %s.\n", input.get(1)));
                startSession(fileScanner);
              } catch (FileNotFoundException e) {
                this.out.append(String.format("File Not Found: %s.\n", input.get(1)));
              }
            } else {
              this.out.append(String.format("File Not Found: %s.\n", input.get(1)));
            }
          } else if (input.get(0).equals("quit")) {
            runFlag = false;
            this.out.append("Thank you for using this Image processor.");
            return;
          } else if (cmd != null) {
            try {
              c = cmd.apply(input);
              c.goCommand(model);
            } catch (Exception e) {
              this.out.append(e.getMessage());
            }
          } else {
            this.out.append(String.format("%s\n",
                "Invalid command. Please enter valid commands"));
          }
        }
      } catch (Exception e) {
        return;
      }
    }
  }

  @Override
  public void startSession() {
    Scanner scan = new Scanner(this.in);
    startSession(scan);
  }

}
