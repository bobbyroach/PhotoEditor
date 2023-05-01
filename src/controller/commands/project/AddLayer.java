package controller.commands.project;

import java.util.ArrayList;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;


/**
 * Represents the command for adding a layer to an ImageInterface.
 */
public class AddLayer extends AbstractCommand {

  private final ImageViewInterface view;

  /**
   * Constructor for a new AddLayer command.
   * @param view : the view interfave being updated wit new radio buttons
   * @param name : the name of the layer.
   */
  public AddLayer(String name, ImageViewInterface view) {
    super(name);
    this.view = view;
  }


  /**
   * Runs addLayer method on the given model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.addLayer(name);
    ArrayList<String> names = model.getLayerNames();
    this.view.updateLayerRadioButton(names);
    this.view.updateButtonOptions("layer-added");
  }
}
