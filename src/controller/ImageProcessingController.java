package controller;

import java.io.IOException;

/**
 * This class represents controller interface for the ImageProcessing. An ImageProcessing Controller
 * will handle all the functionalities a controller should in MVC model.
 */

public interface ImageProcessingController {

  /**
   * A method that will start the session.
   *
   * @throws IOException if the file or folder is not found
   */
  void startSession() throws IOException;
}
