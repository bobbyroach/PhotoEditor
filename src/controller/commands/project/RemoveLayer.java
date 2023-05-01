
package controller.commands.project;

import java.util.ArrayList;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;

/**
 * Represents the command for removing a layer to an ImageInterface.
 */
public class RemoveLayer extends AbstractCommand {

  private final ImageViewInterface view;

  /**
   * Constructor for a new RemoveLayer command.
   *
   * @param name : the name of the layer.
   */
  public RemoveLayer(String name, ImageViewInterface view) {
    super(name);
    this.view = view;
  }


  /**
   * Runs removeLayer method on the given model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.removeLayer(name);
    ArrayList<String> names = model.getLayerNames();
    this.view.updateLayerRadioButton(names);

  }
}

