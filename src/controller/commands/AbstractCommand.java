package controller.commands;


/**
 * Represents an abstract  command which can be executed on an ImageInterface, or an image,
 * specificallty these commands alter the state of the project.
 */
public abstract class AbstractCommand implements ControllerCommand {

  protected final String name;

  /**
   * Constructor for a command.
   *
   * @param name String
   */
  public AbstractCommand(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("e");
    }
    this.name = name;
  }


}
