package model.layer;

import java.util.ArrayList;

import model.pixel.RGBPixel;

/**
 * Represents a layer in an image. Allows the user to use get methods like getRawPixels() and
 * getPixels() for a given layer and allows the user to set the pixels. Also allows for
 * the layer to be converted into a string that represents the current layer as an image in
 * layerContentFormat.
 */
public interface LayerInterface {
  /**
   * Gets this layer's base pixel's.
   *
   * @return : 2d arrayList of pixels
   */
  ArrayList<ArrayList<RGBPixel>> getRawPixels();


  /**
   * Sets the base image's pixels to the given pixels.
   * For adding images to the layer.
   *
   * @param pixels : 2d arrayList of pixels
   */
  void setRawPixels(ArrayList<ArrayList<RGBPixel>> pixels);


  /**
   * Gets this layer's filter.
   */
  String getFilter();


  /**
   * Sets this layers filter.
   *
   * @param filter : String
   */
  void setFilter(String filter);


  /**
   * Returns the name of the layer.
   *
   * @return : String
   */
  String getName();


  /**
   * Gets the layer's current position.
   *
   * @return int
   */
  int getPosition();


  /**
   * Sets the layer's current position one less.
   *
   */
  void decPosition();


  /**
   * Gets this filter's pixels.
   *
   * @return : 2d ArrayList of Pixels
   */
  ArrayList<ArrayList<RGBPixel>> getPixels();


  /**
   * Sets this.Pixels equal to the filtered 2d pixel array.
   */
  void setFilterPixels(ArrayList<ArrayList<RGBPixel>> other);

  /**
   * Removes this layer's filter.
   */
  void removeFilter();


  /**
   * Produces the layer-content-format of a layer.
   *
   * @param hasA : fileType contains A values in the pixel.
   * @return : String
   */
  String layerContentFormat(boolean hasA);


}
