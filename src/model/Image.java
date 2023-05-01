package model;


import java.util.ArrayList;

import model.pixel.RGBPixel;

/**
 * Represents an image that can handle modifications to it. Allows for the user to apply different
 * filters to an image, start the project, add a layer, save the project, save the current image,
 * and other getter methods such as getting a pixel at a given coordinate.
 */
public interface Image {


  /**
   * Returns the pixel at the given coordinate.
   *
   * @param x : x coordinate
   * @param y : y coordinate
   * @return : a Pixel
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  RGBPixel getPixelAtCoord(int x, int y) throws IllegalStateException;


  /**
   * Creates a new layer with the given name and places it on top of the project.
   *
   * @param layerName : name of the new layer
   * @throws IllegalStateException    : if you try to add a lyer before a project has been started
   * @throws IllegalArgumentException : bad layer name.
   */
  void addLayer(String layerName) throws IllegalStateException, IllegalArgumentException;


  /**
   * Removes an existing layer with the given name.
   *
   * @param layerName : name of the current layer
   * @throws IllegalStateException    : if you try to remove a lyer before a project started
   * @throws IllegalArgumentException : bad layer name.
   */
  void removeLayer(String layerName) throws IllegalStateException, IllegalArgumentException;


  /**
   * Clears an existing layer with the given name.
   *
   * @param layerName : name of the current layer
   * @throws IllegalStateException    : if you try to clear a lyer before a project started
   * @throws IllegalArgumentException : bad layer name.
   */
  void clearLayer(String layerName) throws IllegalStateException, IllegalArgumentException;


  /**
   * Starts a project and initializes its height and width.
   *
   * @param projectName : the name of the project
   * @param height      : the height of the project in pixels.
   * @param width       : the width of the project in pixels.
   * @throws IllegalStateException    : when starting a project after its already been started.
   * @throws IllegalArgumentException : given bad or null inputs.
   */
  void startProject(String projectName, int width, int height) throws IllegalStateException,
          IllegalArgumentException;


  /**
   * Creates a 2d arrayList with the specified height and width filled with
   * only white, transparent pixels.
   *
   * @param height : initial height of the project
   * @param width  : initial width of the project
   * @return a 2d arrayList of pixels
   * @throws IllegalStateException : if trying to make a background to a project that hasnt been
   *                               started.
   */

  ArrayList<ArrayList<RGBPixel>> makeWhiteBackground(int height, int width)
          throws IllegalStateException;

  /**
   * Saves the current image in a specified path.
   *
   * @throws IllegalStateException    : when saving a project before ones been made or after it's
   *                                  been closed.
   * @throws IllegalArgumentException : Bad filename.
   */
  String saveImage() throws IllegalStateException, IllegalArgumentException;


  /**
   * Saves the current project to the given path.
   *
   * @throws IllegalStateException    : when saving a project before ones been made or after its
   *                                  been closed.
   * @throws IllegalArgumentException : when given a bad file path.
   */
  String saveProject() throws IllegalStateException, IllegalArgumentException;


  /**
   * Loads a project from the given file path.
   *
   * @param projectData : the info of a project as a string.
   * @throws IllegalArgumentException : if path doesn't exist
   * @throws IllegalStateException    : when loading a project after its already been loaded.
   */
  void loadProject(String projectData) throws IllegalStateException,
          IllegalArgumentException;


  /**
   * Adds the specified image to the given layer and places it according to the
   * coordinates given.
   *
   * @param layerName    : Name of layer
   * @param fileContents : The info of an image file, stored as a stringBuilder.
   * @param xOffset      : y coordinate
   * @param yOffset      : x coordinate
   * @param fileType     : the type of file, .ppm, .png, ,jpg are suppooted
   * @throws IllegalStateException    : when adding an image to a layer before project is made.
   * @throws IllegalArgumentException : when given an invalid layerName or fileName.
   */
  void addImageToLayer(String layerName, String fileContents, int xOffset, int yOffset,
                       String fileType)
          throws IllegalStateException, IllegalArgumentException;


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, thus increasing the brightness of the image.
   *
   * @param layerName : Name of layer being brightened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void brightenValue(String layerName) throws IllegalStateException, IllegalArgumentException;

  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, thus increasing the brightness of the image.
   *
   * @param layerName : Name of layer being brightened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void brightenLuma(String layerName) throws IllegalStateException, IllegalArgumentException;

  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, thus increasing the brightness of the image.
   *
   * @param layerName : Name of layer being brightened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void brightenIntensity(String layerName) throws IllegalStateException, IllegalArgumentException;


  /**
   * Brightens the current layer based off of what the bottom layers would look like compiled.
   * Does not actually compile the bottom layers into a final image, and still allows for
   * mutation of bottom layers after method call.
   *
   * @param layerName : Name of layer being brightened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void brightenScreen(String layerName) throws IllegalStateException, IllegalArgumentException;


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been decreased
   * down to 0, thus decreasing the brightness of the image.
   *
   * @param layerName : Name of layer being darkened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void darkenValue(String layerName) throws IllegalStateException,
          IllegalArgumentException;


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been decreased
   * down to 0, thus decreasing the brightness of the image.
   *
   * @param layerName : Name of layer being darkened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void darkenLuma(String layerName) throws IllegalStateException,
          IllegalArgumentException;

  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been decreased
   * down to 0, thus decreasing the brightness of the image.
   *
   * @param layerName : Name of layer being darkened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void darkenIntensity(String layerName) throws IllegalStateException,
          IllegalArgumentException;

  /**
   * Darkens the current layer based off of what the bottom layers would look like compiled.
   * Does not actually compile the bottom layers into a final image, and still allows for
   * mutation of bottom layers after method call.
   *
   * @param layerName : Name of layer being darkened
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  void darkenMultiply(String layerName) throws IllegalStateException,
          IllegalArgumentException;


  /**
   * Inverts the current layer's colors based on the composite image below it.
   *
   * @param layerName : the layername being differenced
   */
  void difference(String layerName);


  /**
   * Removes a filter on the given layer.
   *
   * @param layerName : layerName
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */

  void removeFilter(String layerName) throws IllegalStateException, IllegalArgumentException;


  /**
   * Modifies the ArrayList of Pixels so that they only have the red value.
   *
   * @param layerName : the name of the layer whihc is being changed.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  void onlyRed(String layerName) throws IllegalStateException,
          IllegalArgumentException;

  /**
   * Modifies the ArrayList of Pixels so that they only have the green value.
   *
   * @param layerName : the name of the layer whihc is being changed.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  void onlyGreen(String layerName) throws IllegalStateException,
          IllegalArgumentException;


  /**
   * Modifies the ArrayList of Pixels so that they only have the blue value.
   *
   * @param layerName : the name of the layer whihc is being changed.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  void onlyBlue(String layerName) throws IllegalStateException,
          IllegalArgumentException;


}
