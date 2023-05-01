package controller.commands.filters.brighten;


import model.ImageModelInterface;

/**
 * Represents a command to brighten an image's rbg values via intensity.
 */
public class BrightenIntensity extends BrightenAbstact {


  /**
   * Constructor that sets the increment value.
   *
   * @param name : the layer name.
   */
  public BrightenIntensity(String name) {
    super(name);

  }


  /**
   * Runs the command brightenIntensity() on an image model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.brightenIntensity(this.name);
  }
}
