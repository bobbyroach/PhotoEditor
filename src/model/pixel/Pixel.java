package model.pixel;


/**
 * Represents a pixel of an image. Allows for the pixel to be formatted and
 * represented as a string.
 */
public interface Pixel {


  /**
   * Formats the pixel into String form.
   *
   * @param hasA : if Pixel has an A value
   * @return String
   */
  String formatPixel(boolean hasA);


}
