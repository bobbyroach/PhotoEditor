package model;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.layer.Layer;
import model.pixel.RGBPixel;


/**
 * This class represents the model of an Image and handles its modifications. It stores
 * the model's width, height, the project's current name, a ppm file's max value,
 * a LinkedHashMap containing strings and layers used to name each layer and store them, and a
 * Hashmap that takes in Integers and layers to keep track of the order of the layer's created.
 */
public abstract class ImageModel implements ImageModelInterface {

  // flag for checking if project has been started.
  protected int width;
  protected int height;
  protected LinkedHashMap<String, Layer> layers;
  protected String projectName;

  protected HashMap<Integer, Layer> numberedLayers;
  protected ArrayList<ArrayList<RGBPixel>> pixels;

  protected int maxValue;


  /**
   * Constructor for an Image Model. Must take in NO PARAMETERS to function correctly in the main
   * and controller.
   */
  public ImageModel() {
    this.layers = new LinkedHashMap<>();
    this.numberedLayers = new HashMap<>();
  }


  /**
   * Returns the current project's height.
   *
   * @return the height.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Sets the height of the project.
   *
   * @param takeIntInputs : the new height.
   */
  @Override
  public void setHeight(int takeIntInputs) {
    this.height = takeIntInputs;
  }

  /**
   * Returns the current project's width.
   *
   * @return the width.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Sets the width of the project.
   *
   * @param takeIntInputs : the new width.
   * @throws IllegalStateException : when the project has not yet been loaded in.
   */
  @Override
  public void setWidth(int takeIntInputs) {
    this.width = takeIntInputs;
  }





  /**
   * Blank word to fill the void.
   *
   * @return : integer
   */
  public int getLayerCount() {
    return this.layers.size();
  }


  /**
   * Determines if the project has been started.
   *
   * @return : true if the project has started.
   */
  @Override
  public boolean hasProjectStarted() {
    return (this.height > 0 && this.width > 0);
  }


  /**
   * Determines if the project has been closed.
   *
   * @return : true if the project has ended.
   */
  @Override
  public boolean hasProjectClosed() {
    return (this.height == -1 && this.width == -1);
  }


  /**
   * State checker for if a project has started, but expected not to.
   * @throws IllegalStateException if a project is attempted to be made more than once.
   */
  @Override
  public void stateNotExpected() throws IllegalStateException {
    if (this.hasProjectStarted()) {
      throw new IllegalStateException("cant load new project after you already loaded one");
    }
  }

  /**
   * State checker for if a project hasn't started, but was expected to.
   * @throws IllegalStateException if a project is attempted to be called on before it was made.
   */
  @Override
  public void stateExpected() throws IllegalStateException {
    if (!this.hasProjectStarted()) {
      throw new IllegalStateException("cant edit before project has been started");
    }
  }

  /**
   * Layer check, for when a layer is expected to exist.
   * @param layerName the name of the layer expected to exist.
   * @throws IllegalArgumentException if a layer was expected to exist but doesn't.
   */
  @Override
  public void layerExpceted(String layerName) throws IllegalArgumentException {
    if (!(this.layers.containsKey(layerName))) {
      throw new IllegalArgumentException("Layer name DOESNT exist and was expected to");
    }
  }

  /**
   * Layer check, for when a layer is expected to not exist.
   * @param layerName the name of the layer expected to not exist.
   * @throws IllegalArgumentException if a layer was expected to not exist but does.
   */
  @Override
  public void layerNotExpected(String layerName) throws IllegalArgumentException {
    if ((this.layers.containsKey(layerName))) {
      throw new IllegalArgumentException("Layer name DOES exist and was expected NOT to");
    }
  }




  /**
   * This method takes in a 2d arrayList of RGBPixels and converts
   * it to a buffered image for use by the graphical interface.
   *
   * @param image : 2d arrayList of pixels that make up an image
   * @return a BufferedImage
   */
  protected static BufferedImage getBufferedImage(ArrayList<ArrayList<RGBPixel>> image) {
    BufferedImage output = new BufferedImage(image.get(0).size(), image.size(),
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < image.size(); i++) {
      for (int j = 0; j < image.get(0).size(); j++) {

        int color = image.get(i).get(j).getBufferedPixel();

        output.setRGB(j, i, color);
      }
    }
    return output;
  }

}