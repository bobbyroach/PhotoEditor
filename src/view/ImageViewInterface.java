package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import controller.ImageController;


/**
 * Interface for an ImageView, which controls the image output and displays
 * any kind of messages to the user.
 */
public interface ImageViewInterface {


  /**
   * Appends a message to this output.
   *
   * @param message the message to be printed
   * @throws IOException : if message fails to append
   */
  void renderMessage(String message) throws IOException;


  /**
   * Refreshes the view and updates the current image displayed.
   */
  void refresh(BufferedImage image);


  /**
   * Updates the layer buttons to reflect the layers in the model.
   *
   * @param layerNames : the names of all layers.
   */
  void updateLayerRadioButton(ArrayList<String> layerNames);


  /**
   * Updates the command buttons to reflect the state in the model, and the possible commands
   * allowed.
   *
   * @param state : the state that indicates what buttons should be displayed
   */
  void updateButtonOptions(String state);


  /**
   * Sets the controller for a view, with the goal of decoupling MVC.
   *
   * @param e : the controller being set for the view.
   */
  void setController(ImageController e);

}
