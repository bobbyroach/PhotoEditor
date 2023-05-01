package model.filter;

import model.layer.Layer;


/**
 * Represents a filter that can be applied to a layer's image. All filter methods either
 * take in one or two layers and manipulates their pixels to result in the correct filtering.
 */
public interface Filter {

  /**
   * Modifies the ArrayList of Pixels so that they only have the red value.
   *
   * @param layer : the layer being filtered.
   * @return a new layer whose pixels are only one color.
   */
  Layer onlyRed(Layer layer);

  /**
   * Modifies the ArrayList of Pixels so that they only have the green value.
   *
   * @param layer : the layer being filtered.
   * @return a new layer whose pixels are only one color.
   */
  Layer onlyGreen(Layer layer);

  /**
   * Modifies the ArrayList of Pixels so that they only have the blue value.
   *
   * @param layer : the layer being filtered.
   * @return a new layer whose pixels are only one color.
   */
  Layer onlyBlue(Layer layer);

  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, thus increasing the brightness of the image.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are brightened.
   */
  Layer brightenValue(Layer layer);

  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, based off the intensity of the max value.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are brightened.
   */
  Layer brightenIntensity(Layer layer);


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, based on luma calculations.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are brightened.
   */
  Layer brightenLuma(Layer layer);


  /**
   * Brightens the topLayer based on the pixels in the bottomLayer.
   *
   * @param topLayer    : Layer
   * @param bottomLayer : Layer
   * @return Layer
   */
  Layer brightenScreen(Layer topLayer, Layer bottomLayer);


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255 based off the values.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are darkened.
   */
  Layer darkenValue(Layer layer);

  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been decreased
   * down to 0.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are darkened.
   */
  Layer darkenIntensity(Layer layer);


  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been increased
   * up to 255, based on luma calculations.
   *
   * @param layer : the layer object being filtered
   * @return a new layer whose pixels are darkened.
   */
  Layer darkenLuma(Layer layer);


  /**
   * Darkens the topLayer based on the pixels in the bottomLayer.
   *
   * @param topLayer    : Layer
   * @param bottomLayer : Layer
   */
  Layer darkenMultiply(Layer topLayer, Layer bottomLayer);


  /**
   * Inverts the color of the topLayer's filter based on the bottomLayer.
   *
   * @param topLayer    Layer
   * @param bottomLayer Layer
   * @return Layer
   */
  Layer difference(Layer topLayer, Layer bottomLayer);

  /**
   * Removes a filter on the given layer.
   *
   * @param layer : the layer object being filtered
   */
  Layer removeFilter(Layer layer);


  /**
   * Resets a layer to its default pixels, ie make it look like a new layer (but at the same
   * position in the project).
   *
   * @param layer : the layer object being filtered
   * @return the layer being taken in, but just reset, and a reference to a new layer object.
   */
  Layer clearLayer(Layer layer);

}
