package controller;

import java.io.IOException;
import model.ImageProcessingMIMEModel;

/**
 * This is an interface which represents all the commands used by Image Processor.
 */
public interface ImageProcessingCommand {

  /**
   * A method to run the command.
   *
   * @param model An ImageProcessingMIMEModel whose methods will be used by the commands
   * @throws IOException If input/output error occurs
   */
  void goCommand(ImageProcessingMIMEModel model) throws Exception;

}
