package controller.commands.filters.darken;


import model.ImageModelInterface;

/**
 * Represents a command to darken an image's rbg values.
 */
public class DarkenMultiply extends DarkenAbstract {

  /**
   * Constructor that sets darken intensity command.
   *
   * @param name : the layer name.
   */
  public DarkenMultiply(String name) {
    super(name);
  }

  /**
   * Runs the command Darken() on an image model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.darkenMultiply(this.name);
  }
}