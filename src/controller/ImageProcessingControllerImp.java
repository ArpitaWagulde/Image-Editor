package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.Component;
import model.ImageProcessingModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * This class belongs to the controller for the ImageProcessing. It will handle starting the
 * session, calling methods from model to perform functionalities and handle the exceptions part and
 * how to display it to the user.
 */

public class ImageProcessingControllerImp implements ImageProcessingController {

  private final ImageProcessingModel model;
  final Readable in;
  final Appendable out;
  private boolean runFlag = true;

  /**
   * This method constructs an ImageProcessingModel object with the given parameters. Which enables
   * this controller to handle the input and output with the given model.
   *
   * @param model Represents ImageProcessingModel
   * @param in    Represents the input of type Readable
   * @param out   Represents the output of type Appendable
   */

  public ImageProcessingControllerImp(ImageProcessingModel model, Readable in, Appendable out) {
    this.model = model;
    this.in = in;
    this.out = out;
  }

  /**
   * A private helper method to start and run the session.
   *
   * @param scan Scanner required to start the session
   */

  private void startSession(Scanner scan) {
    ImageIOController imageio = new ImageIOController();
    while (runFlag) {
      try {
        String commandLine = scan.nextLine();
        if (!commandLine.startsWith("#")) {
          List<String> commands = new ArrayList<>(Arrays.asList(commandLine.split(" ")));
          switch (commands.get(0)) {
            case "run": {
              String scriptFile = commands.get(1);
              File script = new File(scriptFile);
              if (script.exists() && script.isFile()) {
                try {
                  Scanner fileScanner = new Scanner(script);
                  this.out.append(String.format("Running Script File: %s.", commands.get(1)));
                  startSession(fileScanner);
                  break;
                } catch (FileNotFoundException e) {
                  this.out.append(String.format("File Not Found: %s.", commands.get(1)));
                  break;
                }
              } else {
                this.out.append(String.format("File Not Found: %s", commands.get(1)));
                break;
              }
            }

            case "load": {
              String imagePath = commands.get(1);
              String imageName = commands.get(2);
              try {
                model.loadImage(imageio.load(imagePath), imageName);
                this.out.append(String.format("Loaded Image: %s into %s.\n",
                    imagePath, imageName));
              } catch (Exception e) {
                this.out.append("Could not load Image. Please try again or check input command.");
              } finally {
                break;
              }
            }
            case "save": {
              String destinationPath = commands.get(1);
              String imageName = commands.get(2);
              try {
                imageio.save(model.saveImage(imageName), destinationPath);
                this.out.append(String.format("Saved %s to %s.\n", imageName, destinationPath));
              } catch (Exception e) {
                this.out.append("Could not save Image. Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "red-component": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.visualizeComponent(sourceImageName, destinationImageName, Component.RED);
                this.out.append(String.format("Saved Red Component into %s.\n",
                    destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not retrieve Red Component. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "green-component": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.visualizeComponent(sourceImageName, destinationImageName, Component.GREEN);
                this.out.append(String.format("Saved Green Component into %s.\n",
                    destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not retrieve Green Component. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "blue-component": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.visualizeComponent(sourceImageName, destinationImageName, Component.BLUE);
                this.out.append(String.format("Saved Blue Component into %s.\n",
                    destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not retrieve Blue Component. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "value-component": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.visualizeValue(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved Value Component into %s.\n",
                    destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not retrieve Value Component. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "intensity-component": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.visualizeIntensity(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved Intensity Component into %s.\n",
                    destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not retrieve Intensity Component. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "luma-component": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.visualizeLuma(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved Luma Component into %s.\n",
                    destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not retrieve Luma Component. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "horizontal-flip": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.flipHorizontally(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved Horizontally Flipped %s image into %s.\n",
                    sourceImageName, destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not flip Image. Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "vertical-flip": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.flipVertically(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved Vertically Flipped %s image into %s.\n",
                    sourceImageName, destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not flip Image. Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "brighten": {
              float intensity = Float.parseFloat(commands.get(1));
              String sourceImageName = commands.get(2);
              String destinationImageName = commands.get(3);
              try {
                model.brighten(sourceImageName, destinationImageName, intensity);
                this.out.append(String.format("Brightened %s image by %f.\n",
                    sourceImageName, intensity));
              } catch (Exception e) {
                this.out.append("Could not brighten Image. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "rgb-split": {
              String sourceImageName = commands.get(1);
              Map<Component, String> destinationImageNames = new LinkedHashMap<>();
              destinationImageNames.put(Component.RED, commands.get(2));
              destinationImageNames.put(Component.GREEN, commands.get(3));
              destinationImageNames.put(Component.BLUE, commands.get(4));
              try {
                model.split(sourceImageName, destinationImageNames);
                this.out.append(String.format("Split %s image into Red(%s), Green(%s),"
                        + " and Blue(%s) Channels.\n",
                    sourceImageName, commands.get(2), commands.get(3), commands.get(4)));
              } catch (Exception e) {
                this.out.append("Could not split Image. Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "rgb-combine": {
              String destinationImageName = commands.get(1);
              Map<Component, String> sourceImageNames = new LinkedHashMap<>();
              sourceImageNames.put(Component.RED, commands.get(2));
              sourceImageNames.put(Component.GREEN, commands.get(3));
              sourceImageNames.put(Component.BLUE, commands.get(4));
              try {
                model.combine(destinationImageName, sourceImageNames);
                this.out.append(String.format("Combined Red(%s), Green(%s),"
                        + " and Blue(%s) Channels into %s.\n",
                    commands.get(2), commands.get(3), commands.get(4), destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not combine Channels. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "blur": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.blur(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved blurred %s as %s.\n",
                    sourceImageName, destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not blur Image. Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "sharpen": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.sharpen(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved sharpened %s as %s.\n",
                    sourceImageName, destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not sharpen Image. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "sepia": {
              String sourceImageName = commands.get(1);
              String destinationImageName = commands.get(2);
              try {
                model.convertToSepia(sourceImageName, destinationImageName);
                this.out.append(String.format("Saved Sepia filtered %s as %s.\n",
                    sourceImageName, destinationImageName));
              } catch (Exception e) {
                this.out.append("Could not convert to Sepia. "
                    + "Please try again or check input command.");
              } finally {
                break;
              }
            }

            case "quit": {
              runFlag = false;
              this.out.append("Thank you for using this Image processor.");
              return;
            }

            default: {
              this.out.append(String.format("%s\n",
                  "Invalid command. Please enter valid commands"));
            }

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
