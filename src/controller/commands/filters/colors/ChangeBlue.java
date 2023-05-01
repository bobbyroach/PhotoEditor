package controller.commands.filters.colors;

import controller.commands.filters.AbstractFilter;
import model.ImageModelInterface;


/**
 * Represents a command to change the image to only have blue rgb values.
 */
public class ChangeBlue extends AbstractFilter {

  /**
   * Constructor for a change-red component class.
   *
   * @param name : the layer name.
   */
  public ChangeBlue(String name) {
    super(name);
  }

  /**
   * Runs the command onlyOneColor(String color) with the color blue.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.onlyBlue(this.name);
  }

}
