package controller.commands.filters.darken;


import model.ImageModelInterface;

/**
 * Represents a command to darken an image's rbg values.
 */
public class DarkenValue extends DarkenAbstract {

  /**
   * Constructor that sets darken value command.
   *
   * @param name : the layer name.
   */
  public DarkenValue(String name) {
    super(name);
  }

  /**
   * Runs the command Darken() on an image model.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.darkenValue(this.name);
  }
}
