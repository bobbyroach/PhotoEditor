package model.pixel;

/**
 * An HSLPixel representation. Represented by 3 double values; hue, saturation, and lightness,
 * where saturation and lightness are in the range of 0 <= 1 and hue is in the range of
 * 0 <= 360.
 */
public class HSLPixel implements HSLPixelInterface {
  double hue;
  double saturation;
  double lightness;


  /**
   * Constructor for an HSLPixel. Initializes hue, saturation, and lightness fields.
   *
   * @param hue        : double
   * @param saturation : double
   * @param lightness  : double
   */
  public HSLPixel(double hue, double saturation, double lightness) {
    this.hue = hue;
    this.saturation = saturation;
    this.lightness = lightness;
  }

  /*
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model
   */
  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30.0)) % 12.0;
    double a = saturation * Math.min(lightness, 1.0 - lightness);

    return lightness - a * Math.max(-1.0, Math.min(k - 3.0, Math.min(9.0 - k, 1.0)));
  }

  /**
   * Formats the pixel into String form.
   *
   * @param hasA : true if expects an A value in the output.
   * @return String
   */
  @Override
  public String formatPixel(boolean hasA) {
    return this.hue + " " + this.saturation + " " + this.lightness;
  }

  /**
   * Does the necessary math to brighten and then converts the hsl pixel to rgb.
   *
   * @param other : hsl pixel
   * @return rgb pixel
   */
  @Override
  public RGBPixel brightenScreenPixel(HSLPixel other) {
    double newLightness = (1.0 - ((1.0 - this.lightness) * (1.0 - other.lightness)));

    return convertHSLtoRGB(this.hue, this.saturation, newLightness);
  }

  /**
   * Does the necessary math to darken and then converts the hsl pixel to rgb.
   *
   * @param other : hsl pixel
   * @return rgb pixel
   */
  @Override
  public RGBPixel darkenMultiplyPixel(HSLPixel other) {
    double newLightness = this.lightness * other.lightness;
    return convertHSLtoRGB(this.hue, this.saturation, newLightness);
  }

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
  @Override
  public RGBPixel convertHSLtoRGB(double hue, double saturation, double lightness) {
    double r = convertFn(hue, saturation, lightness, 0) * 255.0;
    double g = convertFn(hue, saturation, lightness, 8) * 255.0;
    double b = convertFn(hue, saturation, lightness, 4) * 255.0;

    return new RGBPixel((int) r, (int) g, (int) b, 255);
  }


}