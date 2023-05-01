package controller.commands;

import model.ImageModelInterface;

/**
 * Represents a command that the controller exectures. This is a something that the suer enters
 * to alter an image.
 */
public interface ControllerCommand {

  /**
   * Executes a command on the given model.
   *
   * @param model : an Image Model
   */
  void execute(ImageModelInterface model);
}
