package model.pixel;


/**
 * Represents a pixel whose values are stored as HSL. These pixels can be modified in many
 * ways, including applying different filters and converting to different types of pixels.
 */
public interface HSLPixelInterface extends Pixel {

  /**
   * Does the necessary math to brighten and then converts the hsl pixel to rgb.
   *
   * @param other : hsl pixel
   * @return rgb pixel
   */
  RGBPixel brightenScreenPixel(HSLPixel other);

  /**
   * Does the necessary math to darken and then converts the hsl pixel to rgb.
   *
   * @param other : hsl pixel
   * @return rgb pixel
   */
  RGBPixel darkenMultiplyPixel(HSLPixel other);


  /**
   * Convers an HSL representation where stuff is done.
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   * into an RGB representation where each component is in the range 0-1
   *
   * @param hue        hue of the HSL representation
   * @param saturation saturation of the HSL representation
   * @param lightness  lightness of the HSL representation
   */
  RGBPixel convertHSLtoRGB(double hue, double saturation, double lightness);
}
