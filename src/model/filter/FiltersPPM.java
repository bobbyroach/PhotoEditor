package model.filter;

import java.util.ArrayList;

import model.layer.Layer;
import model.pixel.RGBPixel;



/**
 * Represents a filter that can be applied to a layer's PPM image. All of this class's methods
 * take in either one layer or two layers and manipulates their RGBPixels to apply the
 * current filter.
 */
public class FiltersPPM implements Filter {


  /**
   * Constructor for the FiltersPPM class.
   */
  public FiltersPPM() {
    // nothing so far
  }

  /**
   * Modifies the ArrayList of Pixels so that they only have the red value.
   *
   * @param layer : the layer being filtered.
   * @return a new layer whose pixels are only one color.
   */
  @Override
  public Layer onlyRed(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> newPixels = new ArrayList<ArrayList<RGBPixel>>();
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.onlyRed();
        newPixel.add(temp);
      }
      newPixels.add(newPixel);
    }

    layer.setFilter("red-component");
    layer.setFilterPixels(newPixels);
    return layer;
  }

  /**
   * Modifies the ArrayList of Pixels so that they only have the green value.
   *
   * @param layer : the layer being filtered.
   * @return a new layer whose pixels are only one color.
   */
  @Override
  public Layer onlyGreen(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> newPixels = new ArrayList<ArrayList<RGBPixel>>();
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.onlyGreen();
        newPixel.add(temp);
      }
      newPixels.add(newPixel);
    }

    layer.setFilter("green-component");
    layer.setFilterPixels(newPixels);
    return layer;
  }

  /**
   * Modifies the ArrayList of Pixels so that they only have the blue value.
   *
   * @param layer : the layer being filtered.
   * @return a new layer whose pixels are only one color.
   */
  @Override
  public Layer onlyBlue(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> newPixels = new ArrayList<ArrayList<RGBPixel>>();
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.onlyBlue();
        newPixel.add(temp);
      }
      newPixels.add(newPixel);
    }

    layer.setFilter("blue-component");
    layer.setFilterPixels(newPixels);
    return layer;
  }


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, thus increasing the brightness of the image.
   *
   * @param layer : the layer object being filtered
   * @return a new layer thats been brigthened.
   */
  @Override
  public Layer brightenValue(Layer layer) {

    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();

    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.brightenValue();
        newPixel.add(temp);
      }
      tempPixels.add(newPixel);
    }

    layer.setFilter("brighten-value");
    layer.setFilterPixels(tempPixels);
    return layer;
  }



  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, based off the intensity of the max value.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are only one color.
   */
  @Override
  public Layer brightenIntensity(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();

    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.brightenIntensity();
        newPixel.add(temp);
      }
      tempPixels.add(newPixel);
    }

    layer.setFilter("brighten-intensity");
    layer.setFilterPixels(tempPixels);
    return layer;
  }


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, based on luma calculations.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are only one color.
   */
  @Override
  public Layer brightenLuma(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();

    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.brightenLuma();
        newPixel.add(temp);
      }
      tempPixels.add(newPixel);
    }

    layer.setFilter("brighten-luma");
    layer.setFilterPixels(tempPixels);
    return layer;
  }



  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255 based off the values.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are darkened.
   */
  @Override
  public Layer darkenValue(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();

    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.darkenValue();
        newPixel.add(temp);
      }
      tempPixels.add(newPixel);
    }

    layer.setFilter("darken-value");
    layer.setFilterPixels(tempPixels);
    return layer;
  }



  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been decreased
   * down to 0.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are darkened.
   */
  @Override
  public Layer darkenIntensity(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();

    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.darkenIntensity();
        newPixel.add(temp);
      }
      tempPixels.add(newPixel);
    }

    layer.setFilter("darken-intensity");
    layer.setFilterPixels(tempPixels);
    return layer;
  }



  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, based on luma calculations.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are darkened.
   */
  @Override
  public Layer darkenLuma(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();
    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.darkenLuma();
        newPixel.add(temp);
      }
      tempPixels.add(newPixel);
    }

    layer.setFilter("darken-luma");
    layer.setFilterPixels(tempPixels);
    return layer;
  }


  /**
   * Brightens the topLayer based on the pixels in the bottomLayer.
   *
   * @param topLayer    : Layer
   * @param bottomLayer : Layer
   * @return Layer
   */
  @Override
  public Layer brightenScreen(Layer topLayer, Layer bottomLayer) {
    // we want to alter the image as it, so current filters apply
    ArrayList<ArrayList<RGBPixel>> topPixels = topLayer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();
    ArrayList<ArrayList<RGBPixel>> bottomPixels = bottomLayer.getPixels();

    for (int r = 0; r < topPixels.size(); r++) {
      ArrayList<RGBPixel> temp = new ArrayList<>();  // temporary row of Pixels

      for (int c = 0; c < topPixels.get(r).size(); c++) {
        RGBPixel topPixel = topPixels.get(r).get(c);
        RGBPixel bottomPixel = bottomPixels.get(r).get(c);
        RGBPixel newPixel = topPixel.brightenScreenPixel(bottomPixel); //math in pixel class

        temp.add(newPixel);
      }
      tempPixels.add(r, temp);   // sets the new row of pixels in topPixels
    }

    topLayer.setFilter("brighten-screen");
    topLayer.setFilterPixels(tempPixels);
    return topLayer;
  }


  /**
   * Darkens the topLayer based on the pixels in the bottomLayer.
   *
   * @param topLayer    : Layer
   * @param bottomLayer : Layer
   * @return Layer
   */
  @Override
  public Layer darkenMultiply(Layer topLayer, Layer bottomLayer) {
    // we want to alter the image as it, so current filters apply
    ArrayList<ArrayList<RGBPixel>> topPixels = topLayer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();
    ArrayList<ArrayList<RGBPixel>> bottomPixels = bottomLayer.getPixels();

    for (int r = 0; r < topPixels.size(); r++) {
      ArrayList<RGBPixel> temp = new ArrayList<>();  // temporary row of Pixels

      for (int c = 0; c < topPixels.get(r).size(); c++) {
        RGBPixel topPixel = topPixels.get(r).get(c);
        RGBPixel bottomPixel = bottomPixels.get(r).get(c);
        RGBPixel newPixel = topPixel.darkenMultiplyPixel(bottomPixel); //math in pixel class

        temp.add(newPixel);
      }
      tempPixels.add(r, temp);   // sets the new row of pixels in topPixels
    }

    topLayer.setFilter("darken-multiply");
    topLayer.setFilterPixels(tempPixels);
    return topLayer;
  }


  /**
   * Inverts the color of the topLayer's filter based on the bottomLayer.
   *
   * @param topLayer    Layer
   * @param bottomLayer Layer
   * @return Layer
   */
  @Override
  public Layer difference(Layer topLayer, Layer bottomLayer) {
    // we want to alter the image as it, so current filters apply
    ArrayList<ArrayList<RGBPixel>> topPixels = topLayer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();
    ArrayList<ArrayList<RGBPixel>> bottomPixels = bottomLayer.getPixels();

    for (int r = 0; r < topPixels.size(); r++) {
      ArrayList<RGBPixel> temp = new ArrayList<>();  // temporary row of Pixels

      for (int c = 0; c < topPixels.get(r).size(); c++) {
        RGBPixel topPixel = topPixels.get(r).get(c);
        RGBPixel bottomPixel = bottomPixels.get(r).get(c);
        RGBPixel newPixel = topPixel.differencePixel(bottomPixel); //math in pixel class

        temp.add(newPixel);
      }
      tempPixels.add(r, temp);   // sets the new row of pixels in topPixels
    }

    topLayer.setFilter("difference");
    topLayer.setFilterPixels(tempPixels);
    return topLayer;
  }


  /**
   * Removes a filter on the given layer.
   *
   * @param layer : the layer object being filtered
   */
  @Override
  public Layer removeFilter(Layer layer) {
    layer.removeFilter();
    return layer;
  }

  /**
   * Resets a layer to its default pixels, ie make it look like a new layer (but at the same
   * position in the project).
   *
   * @param layer : the layer object being filtered
   * @return the layer being taken in, but just reset, and a reference to a new layer object.
   */
  @Override
  public Layer clearLayer(Layer layer) {
    ArrayList<ArrayList<RGBPixel>> pixels = layer.getRawPixels();
    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();

    for (ArrayList<RGBPixel> pixel : pixels) {
      ArrayList<RGBPixel> newPixel = new ArrayList<RGBPixel>();
      for (RGBPixel value : pixel) {
        RGBPixel temp = value.reset();
        newPixel.add(temp);
      }
      tempPixels.add(newPixel);
    }


    layer.setRawPixels(tempPixels);
    layer.setFilterPixels(tempPixels);
    layer.setFilter("none");
    return layer;
  }
}
