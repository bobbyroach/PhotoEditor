package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import controller.ImageController;


/**
 * Prints out messages as the user is using an Image Model.
 */
public class ImageView implements ImageViewInterface {
  private final Appendable out;


  /**
   * Default constructor, which initializes the view WITHOUT a controller, to allow for the
   * controller to be initialized without circular logic.
   *
   * @param out   : an Appendable
   */
  public ImageView(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Model or appendable is null");
    }
    this.out = out;
  }


  /**
   * Appends a message to this output.
   *
   * @param message the message to be printed
   * @throws IOException : if message fails to append
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.out.append(message);
    } catch (IOException ignore) {
      throw new IOException("Append failed");
    }
  }




  /**
   * Does nothing in this view implementation.
   * @param image : image
   */
  @Override
  public void refresh(BufferedImage image) {
    // Not needed in this implementation
  }


  /**
   * Does nothing in this view implementation.
   * @param layerNames : the names of all layers.
   */
  @Override
  public void updateLayerRadioButton(ArrayList<String> layerNames) {
    // Not needed in this implementation

  }





  /**
   * Does nothing in this view implementation.
   * @param state : current state
   */
  @Override
  public void updateButtonOptions(String state) {
    // Not needed in this implementation
  }










  /**
   * Does nothing in this view implementation.
   * @param e : image controller
   */
  public final void setController(ImageController e) {
  // does northing
  }


}
