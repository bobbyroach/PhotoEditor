package controller.commands.project;

import controller.commands.filters.AbstractFilter;
import model.ImageModelInterface;


/**
 * Command class to remove a filter from a model.
 */
public class RemoveFilter extends AbstractFilter {

  /**
   * Constructor for a new RemoveFilter object.
   *
   * @param name : the layer name
   */
  public RemoveFilter(String name) {
    super(name);
  }


  /**
   * Executes the command on the model.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.removeFilter(name);
  }

}
