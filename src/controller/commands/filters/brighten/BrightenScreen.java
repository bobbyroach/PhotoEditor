package controller.commands.filters.brighten;


import model.ImageModelInterface;

/**
 * Represents a command to brighten an image's rbg values based on the layer below it.
 */
public class BrightenScreen extends BrightenAbstact {


  /**
   * Constructor that sets the increment value.
   *
   * @param name : the layer name.
   */
  public BrightenScreen(String name) {
    super(name);
  }


  /**
   * Runs the command brightenScreen() on an image model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.brightenScreen(this.name);
  }
}
