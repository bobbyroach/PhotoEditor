package controller.commands.filters.brighten;

import model.ImageModelInterface;

/**
 * Represents a command to brighten an image's rbg values via its values.
 */
public class BrightenValue extends BrightenAbstact {


  /**
   * Constructor that sets the increment value.
   *
   * @param name : the layer name.
   */
  public BrightenValue(String name) {
    super(name);
  }


  /**
   * Runs the command brightenValue() on an image model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.brightenValue(this.name);
  }
}
