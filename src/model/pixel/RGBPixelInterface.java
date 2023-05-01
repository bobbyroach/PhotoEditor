package model.pixel;


/**
 * Represents a pixel whose values are stored as RGB. These pixels can be modified in many
 * ways, including the application of different filters, returning the combination
 * of two pixels, and converting to different types of pixels.
 */
public interface RGBPixelInterface extends Pixel {



  /**
   * Resets a pixel.
   */
  RGBPixel reset();


  /**
   * Alters a pixel to only have the red component.
   * @return a new pixel with only the red component.
   */
  RGBPixel onlyRed();

  /**
   * Alters a pixel to only have the green component.
   * @return a new pixel with only the green component.
   */
  RGBPixel onlyGreen();

  /**
   * Alters a pixel to only have the blue component.
   * @return a new pixel with only the blue component.
   */
  RGBPixel onlyBlue();


  /**
   * brightens a pixel by value.
   */
  RGBPixel brightenValue();


  /**
   * Brightens a pixel by intensity.
   */
  RGBPixel brightenIntensity();


  /**
   * Brightens a pixel by luma.
   */
  RGBPixel brightenLuma();

  /**
   * Darkens a pixel by value.
   */
  RGBPixel darkenValue();

  /**
   * Darkens a pixel by intensity.
   */
  RGBPixel darkenIntensity();

  /**
   * Darkens a pixel by Luma.
   */
  RGBPixel darkenLuma();

  /**
   * Makes a pixel brighter according to values from the other pixel provided.
   *
   * @param other RGBPixel
   * @return RGBPixel
   */
  RGBPixel brightenScreenPixel(RGBPixel other);


  /**
   * Makes a pixel darker according to values from the other pixel provided.
   *
   * @param other RGBPixel
   * @return RGBPixel
   */
  RGBPixel darkenMultiplyPixel(RGBPixel other);


  /**
   * Inverts the color of this pixel based on the other pixel.
   *
   * @param other : Pixel
   * @return : Pixel
   */
  RGBPixel differencePixel(RGBPixel other);


  /**
   * Returns the combination of two RGB Pixels.
   *
   * @param other : Pixel
   * @return : Pixel
   */
  RGBPixel combinePixels(RGBPixel other);


  /**
   * Converts an RGB pixel to an HSL pixel.
   *
   * @param r :
   * @param g :
   * @param b :
   * @return HSLPixel
   */
  HSLPixel convertRGBtoHSL(double r, double g, double b);
}
