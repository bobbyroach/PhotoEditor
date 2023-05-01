package controller.commands.menus;

import view.ImageViewInterface;


/**
 * Represents a menu which is displayed to the view. This menu contains infomration that clarifys
 * what the user can execute at different stages throughout thier usage of the program.
 */
public interface Menu {

  /**
   * Displays a menu on the given view.
   *
   * @param view : an Image view
   * @throws IllegalStateException : if the message cant be transmitted to the view.
   */
  void execute(ImageViewInterface view) throws IllegalStateException;
}
