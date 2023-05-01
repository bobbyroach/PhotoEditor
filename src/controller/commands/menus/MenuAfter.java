package controller.commands.menus;

import java.io.IOException;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;

/**
 * Represents a menu of the commands after a project has been loaded.
 */
public class MenuAfter extends AbstractCommand implements Menu {


  public MenuAfter(String name) {
    super(name);
  }

  /**
   * Prints the menu after a project is present.
   *
   * @throws IllegalStateException : if unable to displau menu.
   */
  @Override
  public void execute(ImageViewInterface view) throws IllegalStateException {
    try {
      view.renderMessage("Commands:" + System.lineSeparator());
      view.renderMessage("add-layer layer-name (Example: add-layer layerOne" +
              System.lineSeparator());
      view.renderMessage("save-image path (Example: save-image image/subDirName/image.ppm)"
              + System.lineSeparator());
      view.renderMessage("save-project project-path (Example: save-project projects/cat.collage)" +
              System.lineSeparator());
      view.renderMessage("add-image-to-layer layer-name file-path offsetX offsetY (Example: " +
              "add-image-to-layer layer1 image/a4image.ppm 0 3)" + System.lineSeparator());
      view.renderMessage("set-filter [darken-value] [darken-intensity] [darken-luma] " +
              "[brighten-value] [brighten-intensity] [brighten-luma] [red-component] " +
              "[green-component] [blue-component] [darken-multiply] [brighten-screen] " +
              "[difference]" +
              "layer-name (Example: set-filter darken-value layer34" +
              System.lineSeparator());
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
