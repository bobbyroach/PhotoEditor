package controller.commands.filters.darken;


import model.ImageModelInterface;

/**
 * Object class for a Difference command.
 */
public class Difference extends DarkenAbstract {
  /**
   * Constructor for the difference object class.
   *
   * @param name String
   */
  public Difference(String name) {
    super(name);
  }

  /**
   * Executes the difference function on the model.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.difference(this.name);
  }
}
