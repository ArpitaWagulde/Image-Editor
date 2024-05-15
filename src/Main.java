import controller.ImageProcessingGRIMEController;
import controller.ImageProcessingGRIMEControllerImp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import model.ImageProcessingGRIMEModelImp;
import model.ImageProcessingGRIMEModel;
import view.GUIView;
import view.ViewInterface;

/**
 * Main class that will initialize the model and controller for this MVC.
 */

public class Main {

  /**
   * Main method that will initialize and run the MVC.
   *
   * @param args A string array of arguments expected
   */
  public static void main(String[] args) {
    ImageProcessingGRIMEModel imageprocessor;
    ImageProcessingGRIMEController controller = null;
    if (args.length > 0) {
      // Check if user has passed a Valid Script File.
      boolean exeFlag = false;

      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-file")) {
          File script = new File(args[i + 1]);
          if (script.exists() && script.isFile()) {
            try {
              exeFlag = true;
              Readable fileScanner = new InputStreamReader(new FileInputStream(script));
              imageprocessor = new ImageProcessingGRIMEModelImp();
              controller = new ImageProcessingGRIMEControllerImp(
                  imageprocessor,
                  fileScanner,
                  System.out);
            } catch (FileNotFoundException e) {
              System.out.println("Invalid Script File. Terminating.");
            }
          }
        } else if (args[i].equals("-text")) {
          exeFlag = true;
          imageprocessor = new ImageProcessingGRIMEModelImp();
          controller = new ImageProcessingGRIMEControllerImp(imageprocessor,
              new InputStreamReader(System.in),
              System.out);
        }
        if (!exeFlag) {
          System.out.println("No valid Parameters passed. Terminating program. Please try again"
              + " with Valid parameters.");
        }
      }
      try {
        controller.startSession();
      } catch (Exception e) {
        System.out.println("Exception. Terminating");
      }
    } else {
      //Launch GUI here
      imageprocessor = new ImageProcessingGRIMEModelImp();
      ViewInterface view = new GUIView();
      controller = new ImageProcessingGRIMEControllerImp(imageprocessor, view);
    }
  }
}
