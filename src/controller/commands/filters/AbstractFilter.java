package controller.commands.filters;


import controller.commands.AbstractCommand;

/**
 * Represents an abstract  filter command which can be executed on an ImageInterface, or an image.
 */
public abstract class AbstractFilter extends AbstractCommand {

  public AbstractFilter(String name) {
    super(name);
  }

}
