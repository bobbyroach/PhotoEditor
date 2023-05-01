package controller.commands.project;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;

/**
 * Represents the command for Clearing a Layer to an ImageInterface.
 */
public class ClearLayer extends AbstractCommand {

  /**
   * Constructor for a new ClearLayer command.
   *
   * @param name : the name of the layer.
   */
  public ClearLayer(String name) {
    super(name);
  }


  /**
   * Runs clearLayer method on the given model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.clearLayer(name);
  }
}
