package model;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.pixel.RGBPixel;

/**
 * Interface for an image model. Represents an image in an image-editor that can be edited.
 * Allows for the setting of height and width, and many get methods that contain information
 * about the current project.
 */
public interface ImageModelInterface extends Image {


  /**
   * Returns the current project's height.
   *
   * @return the height.
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  int getHeight() throws IllegalStateException;

  /**
   * Sets the height of the project.
   *
   * @param takeIntInputs : the new height.
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void setHeight(int takeIntInputs) throws IllegalStateException;

  /**
   * Returns the current project's width.
   *
   * @return the width.
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  int getWidth() throws IllegalStateException;

  /**
   * Sets the width of the project.
   *
   * @param takeIntInputs : the new width.
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void setWidth(int takeIntInputs) throws IllegalStateException;




  /**
   * State checker for if a project has started, but expected not to.
   *
   * @throws IllegalStateException if a project is attempted to be made more than once.
   */
  void stateNotExpected() throws IllegalStateException;


  /**
   * State checker for if a project hasn't started, but was expected to.
   *
   * @throws IllegalStateException if a project is attempted to be called on before it was made.
   */
  void stateExpected() throws IllegalStateException;


  /**
   * Layer check, for when a layer is expected to exist.
   *
   * @param layerName the name of the layer expected to exist.
   * @throws IllegalArgumentException if a layer was expected to exist but doesn't.
   */
  void layerExpceted(String layerName) throws IllegalArgumentException;


  /**
   * Layer check, for when a layer is expected to not exist.
   *
   * @param layerName the name of the layer expected to not exist.
   * @throws IllegalArgumentException if a layer was expected to not exist but does.
   */
  void layerNotExpected(String layerName) throws IllegalArgumentException;



  /**
   * Creates an arrayList of all the layers currently stores in this class's
   * Linked HashMap of layers.
   * @return ArrayList of Layers
   */
  ArrayList<String> getLayerNames();


  /**
   * Determines if the project has been started.
   *
   * @return : true if the project has started.
   */
  boolean hasProjectStarted();


  /**
   * Determines if the project has been closed or not.
   *
   * @return true if the project has been closed.
   */
  boolean hasProjectClosed();


  /**
   * This helper method returns the image of all layers below a specified layer.
   * Used in brighten and darken methods.
   *
   * @param topLayerPosition : int
   * @return 2d ArrayList of Pixels
   */
  ArrayList<ArrayList<RGBPixel>> getWholeImageHelp(int topLayerPosition);


  /**
   * The method returns the amount of layers currently stored
   * in the model.
   *
   * @return The integer.
   */
  int getLayerCount();


  /**
   * This returns the entire composite image to be used by
   * the graphical interface.
   *
   * @return a buffered image.
   */
  BufferedImage getWholeImage();
}
