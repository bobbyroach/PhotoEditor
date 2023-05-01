package controller.commands.filters.colors;


import controller.commands.filters.AbstractFilter;
import model.ImageModelInterface;

/**
 * Represents a command to change the image to only have green rgb values.
 */
public class ChangeGreen extends AbstractFilter {

  /**
   * Constructor for a change-red component class.
   *
   * @param name : the layer name.
   */
  public ChangeGreen(String name) {
    super(name);
  }

  /**
   * Runs the command onlyOneColor(String color) with the color green.
   *
   * @param model : an Image Model.
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.onlyGreen(name);
  }

}
