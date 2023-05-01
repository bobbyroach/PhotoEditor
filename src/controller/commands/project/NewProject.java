package controller.commands.project;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;


/**
 * Represents the new-project command.
 */
public class NewProject extends AbstractCommand {

  private final int width;
  private final int height;

  private final ImageViewInterface view;

  /**
   * The constructor.
   *
   * @param name   : the new project name
   * @param width  : width of project
   * @param height : height of project.
   * @param view : the view itnerfaec being minipilated to display approarite options based on
   *               the state.
   */
  public NewProject(String name, int width, int height, ImageViewInterface view)
          throws IllegalArgumentException {
    super(name);
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("bad inputs");
    }
    this.view = view;
    this.width = width;
    this.height = height;
  }


  /**
   * Executes a command on the given model.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    model.startProject(name, width, height);
    view.updateButtonOptions("started");
  }
}
