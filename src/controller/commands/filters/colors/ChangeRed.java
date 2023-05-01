package controller.commands.filters.colors;

import controller.commands.filters.AbstractFilter;
import model.ImageModelInterface;


/**
 * Represents a command to change the image to only have red rgb values.
 */
public class ChangeRed extends AbstractFilter {

  /**
   * Constructor for a change-red component class.
   *
   * @param name : the layer name.
   */
  public ChangeRed(String name) {
    super(name);
  }

  /**
   * Runs the command onlyOneColor(String color) with the color red.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.onlyRed(this.name);
  }

}
