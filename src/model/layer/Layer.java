package model.layer;

import java.util.ArrayList;

import model.pixel.Pixel;
import model.pixel.RGBPixel;


/**
 * Class representation of a Layer in an ImageModel. Firstly, it stores its name,
 * filter, and position for keeping track of the layer. It also stores a 2d ArrayList of rawPixels
 * that represent the image on the layer without any filter applied. This allows the user to
 * remove and switch to different filters easily. And finally, it stores its current pixels
 * that would be the rawPixels with or without a filter currently applied.
 */
public final class Layer implements LayerInterface {
  private final String name;
  private int position;

  public ArrayList<ArrayList<RGBPixel>> pixels;
  private ArrayList<ArrayList<RGBPixel>> rawPixels;
  private String filter = "none";


  /**
   * Constructor for a layer. Initializes the pixels and the rawPixels to the given pixels,
   * as no filter would be applied yet, and initializes the name and position.
   *
   * @param pixels : 2d arrayList of pixels
   */
  public Layer(ArrayList<ArrayList<RGBPixel>> pixels, String name, int position) {
    this.pixels = pixels;
    this.rawPixels = pixels;
    this.name = name;
    this.position = position;
  }


  /**
   * Constructor that also takes in the filter name and has the same functionality as the
   * other constructor otherwise.
   *
   * @param pixels : 2d arrayList of pixels
   * @param filter : Current filter
   */
  public Layer(ArrayList<ArrayList<RGBPixel>> pixels, String name, String filter,
               int position) {
    this.pixels = pixels;
    this.rawPixels = pixels;
    this.filter = filter;
    this.name = name;
    this.position = position;
  }


  /**
   * Gets this layer's base pixels that aren't affected by the current filter.
   *
   * @return : 2d arrayList of pixels
   */
  @Override
  public ArrayList<ArrayList<RGBPixel>> getRawPixels() {
    return this.rawPixels;
  }


  /**
   * Sets the base image's pixels to the given pixels.
   * For adding images to the layer.
   *
   * @param pixels : 2d arrayList of pixels
   */
  @Override
  public void setRawPixels(ArrayList<ArrayList<RGBPixel>> pixels) {
    this.rawPixels = pixels;
  }


  /**
   * Gets this layer's filter.
   */
  @Override
  public String getFilter() {
    return this.filter;
  }


  /**
   * Sets this layers filter.
   *
   * @param filter : String
   */
  @Override
  public void setFilter(String filter) {
    this.filter = filter;
  }


  /**
   * Returns the name of the layer.
   *
   * @return : String
   */
  @Override
  public String getName() {
    return this.name;
  }


  /**
   * Gets the layer's current position.
   *
   * @return int
   */
  @Override
  public int getPosition() {
    return this.position;
  }


  /**
   * Sets the layer's current position to one less.
   */
  @Override
  public void decPosition() {
    this.position--;
  }


  /**
   * Gets this filter's pixels.
   *
   * @return : 2d ArrayList of Pixels
   */
  @Override
  public ArrayList<ArrayList<RGBPixel>> getPixels() {
    return this.pixels;
  }


  /**
   * Sets this.Pixels equal to the filtered 2d pixel array.
   */
  @Override
  public void setFilterPixels(ArrayList<ArrayList<RGBPixel>> other) {
    this.pixels = other;
  }


  /**
   * Removes this layer's filter.
   */
  @Override
  public void removeFilter() {
    this.pixels = this.rawPixels;
    this.filter = "none";
  }


  /**
   * Produces the layer-content-format of a layer.
   *
   * @param hasA : fileType contains A values in the pixel.
   * @return : String
   */
  @Override
  public String layerContentFormat(boolean hasA) {
    StringBuilder s = new StringBuilder();
    for (ArrayList<RGBPixel> pixels : this.pixels) {
      for (int c = 0; c < pixels.size(); c++) {
        Pixel p = pixels.get(c);
        s.append(p.formatPixel(hasA) + " ");
      }
      s.append("\n");
    }
    return s.toString();
  }

}
