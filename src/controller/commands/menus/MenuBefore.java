package controller.commands.menus;

import java.io.IOException;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;


/**
 * Im tired and wanna go to sleep.
 */
public class MenuBefore extends AbstractCommand implements Menu {


  public MenuBefore(String name) {
    super(name);
  }


  /**
   * Prints the menu before a project is present.
   *
   * @throws IllegalStateException : if unable to display menu.
   */
  @Override
  public void execute(ImageViewInterface view) throws IllegalStateException {
    try {
      view.renderMessage("Commands:" + System.lineSeparator());
      view.renderMessage("One of the following commands must be called before calling any others" +
              "." + System.lineSeparator());
      view.renderMessage("new-project project-height project-width project-name (Example: " +
              "new-project " +
              "400 200 P1)"
              + System.lineSeparator());
      view.renderMessage("load-project file-path (Example: load-project projects/P1.collage)"
              + System.lineSeparator());
      view.renderMessage("menu" + System.lineSeparator());
      view.renderMessage("quit" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Executes a command on the given model.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    // do nothing
  }
}
