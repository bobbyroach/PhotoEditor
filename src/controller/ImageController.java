package controller;


/**
 * Interface for an Image Controller. This controller takes in input commands
 * and updates the model accordingly.
 */
public interface ImageController {


  /**
   * Handles all command input when the controller is operating on an image model.
   */
  void runImage();


  /**
   * This method accepts input and calls the runCommand method with
   * the given input to make the appropriate changes to the model.
   * If there's an error processing the input, the graphical interface
   * will display that error to the user.
   *
   * @param input : A string input from the user.
   */
  void accept(String input);
}
