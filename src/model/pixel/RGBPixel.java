package model.pixel;


/**
 * Represents an individual RGB pixel. Represents a color that has 4 integers,
 * a red, green, blue, and transparency value.
 */
public class RGBPixel implements RGBPixelInterface {
  private final int a;
  private int r;
  private int b;
  private int g;


  /**
   * Constructor for a pixel without an "a" value. A value is assigned 255 and
   * assumed to be completely opaque.
   *
   * @param r : red value
   * @param b : blue value
   * @param g : green value
   */
  public RGBPixel(int r, int g, int b) {
    this.r = r;
    this.b = b;
    this.g = g;
    this.a = 255;
  }


  /**
   * Constructor for a background pixel. This pixel is default, meaning this is the pixel added
   * to a background or new layer.
   *
   */
  public RGBPixel() {
    this.r = 0;
    this.b = 0;
    this.g = 0;
    this.a = 0;
  }


  /**
   * Constructor for a pixel with an "a" value.
   *
   * @param r : red value
   * @param b : blue value
   * @param g : green value
   * @param a : transparency value
   */
  public RGBPixel(int r, int g, int b, int a) {
    this.r = r;
    this.b = b;
    this.g = g;
    this.a = a;
  }


  /**
   * Alters a pixel to only have the red component.
   * @return a new pixel with only the red component.
   */
  @Override
  public RGBPixel onlyRed() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    temp.g = 0;
    temp.b = 0;
    return temp;
  }

  /**
   * Alters a pixel to only have the green component.
   * @return a new pixel with only the green component.
   */
  @Override
  public RGBPixel onlyGreen() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    temp.r = 0;
    temp.b = 0;
    return temp;
  }

  /**
   * Alters a pixel to only have the blue component.
   * @return a new pixel with only the blue component.
   */
  @Override
  public RGBPixel onlyBlue() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    temp.r = 0;
    temp.g = 0;
    return temp;
  }

  /**
   * Brightens a pixel by value.
   */
  @Override
  public RGBPixel brightenValue() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    int max1 = Math.max(temp.r, temp.b);
    int max = Math.max(temp.g, max1);
    temp.r += max;
    if (temp.r > 255) {
      temp.r = 255;
    }
    temp.b += max;
    if (temp.b > 255) {
      temp.b = 255;
    }
    temp.g += max;
    if (temp.g > 255) {
      temp.g = 255;
    }
    return temp;
  }



  /**
   * Resets a pixel.
   */
  @Override
  public RGBPixel reset() {
    return new RGBPixel();
  }



  /**
   * Brightens a pixel by intensity.
   */
  @Override
  public RGBPixel brightenIntensity() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    int avg = (temp.r + temp.b + temp.g) / 2;
    temp.r += avg;
    if (temp.r > 255) {
      temp.r = 255;
    }
    temp.b += avg;
    if (temp.b > 255) {
      temp.b = 255;
    }
    temp.g += avg;
    if (temp.g > 255) {
      temp.g = 255;
    }
    return temp;
  }


  /**
   * Brightens a pixel by luma.
   */
  @Override
  public RGBPixel brightenLuma() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    int luma = (int) ((0.2126 * temp.r) + (0.7152 * temp.g) + (0.0722 * temp.b));
    temp.r += luma;
    if (temp.r > 255) {
      temp.r = 255;
    }
    temp.b += luma;
    if (temp.b > 255) {
      temp.b = 255;
    }
    temp.g += luma;
    if (temp.g > 255) {
      temp.g = 255;
    }
    return temp;
  }


  /**
   * Darkens a pixel by value.
   */
  @Override
  public RGBPixel darkenValue() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    int max1 = Math.max(temp.r, temp.b);
    int max = Math.max(temp.g, max1);
    temp.r -= max;
    if (temp.r < 0) {
      temp.r = 0;
    }
    temp.b -= max;
    if (temp.b < 0) {
      temp.b = 0;
    }
    temp.g -= max;
    if (temp.g < 0) {
      temp.g = 0;
    }
    return temp;
  }


  /**
   * Darkens a pixel by intensity.
   */
  @Override
  public RGBPixel darkenIntensity() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    int avg = (temp.r + temp.b + temp.g) / 2;
    temp.r -= avg;
    if (temp.r < 0) {
      temp.r = 0;
    }
    temp.b -= avg;
    if (temp.b < 0) {
      temp.b = 0;
    }
    temp.g -= avg;
    if (temp.g < 0) {
      temp.g = 0;
    }
    return temp;
  }


  /**
   * Darkens a pixel by Luma.
   */
  @Override
  public RGBPixel darkenLuma() {
    RGBPixel temp = new RGBPixel(this.r, this.g, this.b, this.a);
    int luma = (int) ((0.2126 * temp.r) + (0.7152 * temp.g) + (0.0722 * temp.b));
    temp.r -= luma;
    if (temp.r < 0) {
      temp.r = 0;
    }
    temp.b -= luma;
    if (temp.b < 0) {
      temp.b = 0;
    }
    temp.g -= luma;
    if (temp.g < 0) {
      temp.g = 0;
    }
    return temp;
  }


  /**
   * Makes a pixel brighter according to values from the other pixel provided.
   *
   * @param other RGBPixel
   * @return RGBPixel
   */
  @Override
  public RGBPixel brightenScreenPixel(RGBPixel other) {

    // background fully transparent
    if (other.a == 0) {
      return this;
      // current layer is transparent, ie invisible
    } else if (this.a == 0 ) {
      return new RGBPixel(other.r, other.g, other.b, other.a);
    } else {

      HSLPixel thisPixel = convertRGBtoHSL(((double) this.r) / 255.0,
              ((double) this.g) / 255.0, ((double) this.b) / 255.0);
      HSLPixel otherPixel = convertRGBtoHSL(((double) other.r) / 255.0,
              ((double) other.g) / 255.0, ((double) other.b) / 255.0);

      return thisPixel.brightenScreenPixel(otherPixel);
    }
  }


  /**
   * Makes a pixel darker according to values from the other pixel provided.
   *
   * @param other RGBPixel
   * @return RGBPixel
   */
  @Override
  public RGBPixel darkenMultiplyPixel(RGBPixel other) {
    // background fully transparent
    if (other.a == 0) {
      return this;
      // current layer is transparent, ie invisible
    } else if (this.a == 0) {
      return new RGBPixel(other.r, other.g, other.b, other.a);
    } else {
      HSLPixel thisPixel = convertRGBtoHSL(((double) this.r) / 255.0,
              ((double) this.g) / 255.0, ((double) this.b) / 255.0);
      HSLPixel otherPixel = convertRGBtoHSL(((double) other.r) / 255.0,
              ((double) other.g) / 255.0, ((double) other.b) / 255.0);
      return thisPixel.darkenMultiplyPixel(otherPixel);
    }
  }


  /**
   * Inverts the color of this pixel based on the other pixel.
   *
   * @param other : Pixel
   * @return : Pixel
   */
  @Override
  public RGBPixel differencePixel(RGBPixel other) {
    // background fully transparent
    if (other.a == 0) {
      return this;
      // current layer is transparent, ie invisible
    } else if (this.a == 0) {
      return new RGBPixel(other.r, other.g, other.b, other.a);
    } else {
      int r = Math.abs(this.r - other.r);
      int g = Math.abs(this.g - other.g);
      int b = Math.abs(this.b - other.b);

      return new RGBPixel(r, g, b, this.a);
    }
  }


  /**
   * Checks if two pixels are equal to each other.
   *
   * @param other : Pixel
   * @return : boolean
   */
  private Boolean isEqual(RGBPixel other) {
    return this.r == other.r && this.b == other.b && this.g == other.g &&
            this.a == other.a;
  }


  /**
   * Formats the pixel into String form.
   *
   * @param hasA : true if expects an A value in the output.
   * @return String
   */
  @Override
  public String formatPixel(boolean hasA) {
    if (hasA) {
      return this.r + " " + this.g + " " + this.b + " " + this.a;
    } else {
      return this.r + " " + this.g + " " + this.b;
    }
  }


  /**
   * Returns the combination of two RGB Pixels.
   *
   * @param other : Pixel
   * @return : Pixel
   */
  @Override
  public RGBPixel combinePixels(RGBPixel other) {


    // background image
    int dr = other.r;
    int db = other.b;
    int dg = other.g;
    int da = other.a;

    // background fully transparent, or durrent layer is fully opaque
    if (da == 0 || this.a == 255) {
      return new RGBPixel(this.r, this.g, this.b, this.a);
      // current layer is transparent, ie invisible
    } else if (this.a == 0) {
      return new RGBPixel(dr, dg, db, da);

    } else {
      double aPrimePrime = ((this.a / 255.0) + (da / 255.0) * (1.0 - (this.a / 255.0)));
      int newR;
      int newG;
      int newB;
      int newA;

      newR = (int) (((this.a / 255.0) * this.r + dr * (da / 255.0))
              * ((1 - this.a / 255.0)) * (1.0 / aPrimePrime));
      newG = (int) (((this.a / 255.0) * this.g + dg * (da / 255.0))
              * ((1 - this.a / 255.0)) * (1.0 / aPrimePrime));
      newB = (int) (((this.a / 255.0) * this.b + db * (da / 255.0))
              * ((1 - this.a / 255.0)) * (1.0 / aPrimePrime));
      newA = (int) aPrimePrime * 255;

      return new RGBPixel(newR, newG, newB, newA);

    }

  }


  /**
   * Converts an RGB pixel to an HSL pixel.
   *
   * @param r : yes.
   * @param g : no.
   * @param b : maybe.
   * @return HSLPixel so.
   */
  @Override
  public HSLPixel convertRGBtoHSL(double r, double g, double b) {
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2.0;
    double hue;
    double saturation;
    if (delta == 0.0) {
      hue = 0.0;
      saturation = 0.0;
    } else {
      saturation = delta / (1.0 - Math.abs(2.0 * lightness - 1.0));
      hue = 0.0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2.0;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4.0;
      }

      hue = hue * 60.0;
    }

    return new HSLPixel(hue, saturation, lightness);
  }


  /**
   * Something here to void the fill.
   *
   * @return integer that does stuff.
   */
  public int getBufferedPixel() {

    int r = this.r;
    int g = this.g;
    int b = this.b;

    return (r << 16) + (g << 8) + b;
  }

}




