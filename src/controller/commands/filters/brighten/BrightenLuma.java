package controller.commands.filters.brighten;


import model.ImageModelInterface;

/**
 * Represents a command to brighten an image's rbg values via luma.
 */
public class BrightenLuma extends BrightenAbstact {

  /**
   * Constructor that sets the increment value.
   *
   * @param name : the layer name.
   */
  public BrightenLuma(String name) {
    super(name);
  }


  /**
   * Runs the command brightenLuma() on an image model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.brightenLuma(this.name);
  }
}
