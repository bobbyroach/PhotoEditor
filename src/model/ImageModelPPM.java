package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import model.filter.FiltersPPM;
import model.layer.Layer;
import model.pixel.RGBPixel;


/**
 * This class represents the model of a PPM Image and handles its modifications. It stores
 * the model's width, height, the project's current name, a ppm file's max value,
 * a LinkedHashMap containing strings and layers used to name each layer and store them, and a
 * Hashmap that takes in Integers and layers to keep track of the order of the layer's created.
 * This extension of the ImageModel class also contains a filter class object that
 * is currently in use.
 */
public class ImageModelPPM extends ImageModel {

  private static FiltersPPM filter;



  /**
   * Constructor for an Image Model. Must take in NO PARAMETERS to function correctly in the main
   * and controller
   */
  public ImageModelPPM() {
    super();
    filter = new FiltersPPM();
  }


  /**
   * Starts a project and initializes its height and width.
   *
   * @param projectName : the name of the project
   * @param height      : the height of the project in pixels.
   * @param width       : the width of the project in pixels.
   * @throws IllegalStateException    : when starting a project after its already been started.
   * @throws IllegalArgumentException : given bad or null inputs.
   */
  @Override
  public void startProject(String projectName, int width, int height)
          throws IllegalStateException, IllegalArgumentException {
    if (this.hasProjectStarted()) {
      throw new IllegalStateException("cant start another project, one is already open");
    }
    if (projectName == null || width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Inputted numbers can't be negative");
    }

    super.projectName = projectName;
    super.setWidth(width);
    super.setHeight(height);
    addLayer("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
  }




  /**
   * Creates a new layer with the given name and places it on top of the project.
   *
   * @param layerName : name of the new layer
   * @throws IllegalStateException    : if you try to add a lyer before a project has been started
   * @throws IllegalArgumentException : bad layer name.
   */
  @Override
  public void addLayer(String layerName) throws IllegalStateException, IllegalArgumentException {
    super.stateExpected();
    super.layerNotExpected(layerName);
    super.pixels = makeWhiteBackground(super.height, super.width);    // makes all white new
    // layer
    super.layers.put(layerName, new Layer(super.pixels, layerName, super.layers.size()));
    super.numberedLayers.put(super.layers.size() - 1, super.layers.get(layerName));
  }


  /**
   * Creates a 2d arrayList with the specified height and width filled with
   * only white, transparent pixels.
   *
   * @param height : initial height of the project
   * @param width  : initial width of the project
   * @return a 2d arrayList of pixels
   * @throws IllegalStateException : if trying to make a background to a project that hasnt been
   *                               started.
   */
  @Override
  public ArrayList<ArrayList<RGBPixel>> makeWhiteBackground(int height, int width)
          throws IllegalStateException {
    super.stateExpected();
    ArrayList<ArrayList<RGBPixel>> outerArray = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      ArrayList<RGBPixel> innerArray = new ArrayList<>();

      for (int k = 0; k < width; k++) {
        innerArray.add(new RGBPixel());
      }
      outerArray.add(innerArray);
    }
    return outerArray;
  }

  /**
   * Saves the current image in a specified path.
   *
   * @throws IllegalStateException : when saving a project before ones been made or after it's
   *                               been closed.
   */
  @Override
  public String saveImage() throws IllegalStateException {
    super.stateExpected();
    boolean flag = true;
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    for (Map.Entry<String, Layer> entry : super.layers.entrySet()) {
      Layer layer = entry.getValue();
      // first layer (background transparent layer)
      if (flag) {
        image = layer.getPixels();
        flag = false;
      }
      // every other layer
      else {
        for (int row = 0; row < super.height; row++) {
          ArrayList<RGBPixel> tempRow = new ArrayList<>();
          for (int col = 0; col < super.width; col++) {
            RGBPixel currentPixel = layer.getPixels().get(row).get(col);
            RGBPixel oldPixel = image.get(row).get(col);
            RGBPixel newPixel = currentPixel.combinePixels(oldPixel);
            tempRow.add(newPixel);
          }
          image.set(row, tempRow);
        }
      }
    }
    Layer imageLayer = new Layer(image, "Name doesn't matter here", 0);
    String builder = "P3 \n" + super.width + " " + super.height + " \n" + super.maxValue + " \n" +
            imageLayer.layerContentFormat(false);
    return builder;
  }

  /**
   * Returns the pixel at the given coordinate.
   *
   * @param x : x coordinate
   * @param y : y coordinate
   * @return : a Pixel
   */
  @Override
  public RGBPixel getPixelAtCoord(int x, int y) {
    return super.pixels.get(y).get(x);
  }


  /**
   * Saves the current project to the given path.
   *
   * @throws IllegalStateException : when saving a project before ones been made or after its
   *                               been closed.
   */
  @Override
  public String saveProject() throws IllegalStateException {
    super.stateExpected();
    StringBuilder output = new StringBuilder();
    output.append(super.projectName + "\n" + super.width + " " + super.height + "\n"
            + super.maxValue +
            "\n");
    // ADD SPACE BETWEEN OUTPUT AND INFO
    Set<String> kets = layers.keySet();
    for (String layerName : kets) {
      Layer layer = super.layers.get(layerName);
      String name = layer.getName();

      output.append(name + " " + layer.getFilter() + "\n" +
              // no new line after below call, bc last line in layerConentFormat - "\n"
              layer.layerContentFormat(true));
    }
    return output.toString();
  }


  /**
   * Loads a project from the given file path and initializes all of its layers.
   *
   * @param projectData : the info of a project as a string.
   * @throws IllegalStateException : when loading a project after its already been loaded.
   */
  @Override
  public void loadProject(String projectData) throws IllegalStateException {
    super.stateNotExpected();
    super.layers = new LinkedHashMap<>();
    super.numberedLayers = new HashMap<>();

    ArrayList<ArrayList<RGBPixel>> tempPixels = new ArrayList<>();
    String[] lines = projectData.split("\\r\\n|\\s|\r|\n| |\r\r|\r\\s");

    super.projectName = lines[0];

    super.width = Integer.parseInt(lines[1]);

    super.height = Integer.parseInt(lines[2]);

    super.maxValue = Integer.parseInt(lines[3]);

    int count = 4;
    int numOfLayers = (lines.length - 4) / ((super.width * super.height * 4) + 2);
    String layerName;
    String filterName;
    for (int l = 0; l < numOfLayers; l++) {
      layerName = lines[count];
      count++;
      filterName = lines[count];
      count++;

      for (int r = 0; r < super.height; r++) {
        ArrayList<RGBPixel> innerPixels = new ArrayList<>();

        // gets the next n items, where n is the row length * 3 + count
        for (int w = 0; w < super.width; w++) {
          int rVal = Integer.parseInt(lines[count]);
          int gVal = Integer.parseInt(lines[count + 1]);
          int bVal = Integer.parseInt(lines[count + 2]);
          int aVal = Integer.parseInt(lines[count + 3]);
          count += 4;
          innerPixels.add(new RGBPixel(rVal, gVal, bVal, aVal));
        }
        tempPixels.add(innerPixels);
      }
      super.pixels = tempPixels;
      Layer layer = new Layer(tempPixels, layerName, filterName, super.layers.size());
      super.layers.put(layerName, layer);                        // linked hashmap with strings
      super.numberedLayers.put(super.layers.size() - 1, layer);       // hashmap with integers
      tempPixels = new ArrayList<>();                               // reset tempPixels
    }
  }

  /**
   * Adds the specified image to the given layer and places it according to the
   * coordinates given.
   *
   * @param layerName    : Name of layer
   * @param fileContents : The info of an image file, stored as a stringBuilder.
   * @param xOffset      : y coordinate
   * @param yOffset      : x coordinate
   * @param fileType     : the type of file, .ppm, .png, ,jpg are suppooted
   * @throws IllegalStateException    : when adding an image to a layer before project is made.
   * @throws IllegalArgumentException : when given an invalid layerName or file type, inst a P3
   *                                  ppm file.
   */
  @Override
  public void addImageToLayer(String layerName, String fileContents,
                              int xOffset, int yOffset, String fileType)
          throws IllegalStateException, IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);

    super.pixels = super.layers.get(layerName).getRawPixels();  // access 2d pixel array


    // turns the fileContents into a 2d array of pixels
    ArrayList<ArrayList<RGBPixel>> outerArray;

    String[] lines =
            fileContents.split(
                    "[\\n\\r\\s]+");


    if (!lines[0].equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    outerArray = new ArrayList<>();

    int width = Integer.parseInt(lines[1]);

    int height = Integer.parseInt(lines[2]);

    super.maxValue = Integer.parseInt(lines[3]);
    // initially offset by 4
    int count = 4;
    if (fileType.equals(".ppm")) {
      for (int i = 0; i < height; i++) {
        ArrayList<RGBPixel> innerArray = new ArrayList<>();

        for (int j = 0; j < width; j++) {
          int r = Integer.parseInt(lines[count]);
          int g = Integer.parseInt(lines[1 + count]);
          int b = Integer.parseInt(lines[2 + count]);
          count += 3;
          innerArray.add(new RGBPixel(r, g, b, 255));
        }
        outerArray.add(innerArray);
      }
    } else {
      for (int i = 0; i < height; i++) {
        ArrayList<RGBPixel> innerArray = new ArrayList<>();

        for (int j = 0; j < width; j++) {
          int r = Integer.parseInt(lines[count]);
          int g = Integer.parseInt(lines[1 + count]);
          int b = Integer.parseInt(lines[2 + count]);
          int a = Integer.parseInt(lines[3 + count]);
          count += 4;
          innerArray.add(new RGBPixel(r, g, b, a));
        }
        outerArray.add(innerArray);
      }
    }

    // adds the image to layer
    for (int r = yOffset; (r < super.height && r - yOffset < height); r++) {
      ArrayList<RGBPixel> temp = super.pixels.get(r);
      for (int c = xOffset; (c < super.width && c - xOffset < width); c++) {
        RGBPixel p = outerArray.get(r - yOffset).get(c - xOffset);
        temp.set(c, p);
      }
      super.pixels.set(r, temp);

    }
    // this layer object also points to the numbered layer hashmap
    super.layers.get(layerName).setRawPixels(super.pixels);
    super.layers.get(layerName).setFilterPixels(super.pixels);
  }

  /**
   * Brightens the current layer based off of what the bottom layers would look like compiled.
   * Does not actually compile the bottom layers into a final image, and still allows for
   * mutation of bottom layers after method call.
   *
   * @param layerName : Name of the layer being modified.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  @Override
  public void brightenScreen(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    if (super.layers.size() >= 2) {

      Layer topLayer = super.layers.get(layerName);
      int topLayerPosition = topLayer.getPosition();

      // gets the whole image below the top layer's position
      ArrayList<ArrayList<RGBPixel>> wholeImage = getWholeImageHelp(topLayerPosition);

      // whole image below top layer as a layer
      Layer bottomLayer = new Layer(wholeImage, "bottomLayer", 0);
      Layer temp = filter.brightenScreen(topLayer, bottomLayer);

      // replace in both hashmaps
      super.pixels = temp.getPixels();
      super.layers.replace(layerName, temp);
      super.numberedLayers.replace(topLayerPosition, temp);

    }
  }

  /**
   * Modifies the ArrayList of pixels where all the pixel's rbg values have been decreased
   * down to 0, thus decreasing the brightness of the image.
   *
   * @param layerName : Name of the layer being modified.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  @Override
  public void darkenMultiply(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    if (super.layers.size() >= 2) {
      Layer topLayer = super.layers.get(layerName);
      int topLayerPosition = topLayer.getPosition();

      // gets the whole image below the top layer's position
      ArrayList<ArrayList<RGBPixel>> wholeImage = getWholeImageHelp(topLayerPosition);

      // whole image below top layer as a layer
      Layer bottomLayer = new Layer(wholeImage, "bottomLayer", 0);
      Layer temp = filter.darkenMultiply(topLayer, bottomLayer);

      // replace in both hashmaps
      super.pixels = temp.getPixels();
      super.layers.replace(layerName, temp);
      super.numberedLayers.replace(topLayerPosition, temp);


    }
  }

  /**
   * Inverts the current layer's colors based on the composite image below it.
   *
   * @param layerName : String
   */
  @Override
  public void difference(String layerName) {
    super.stateExpected();
    super.layerExpceted(layerName);

    if (super.layers.size() >= 2) {
      Layer topLayer = super.layers.get(layerName);
      int topLayerPosition = topLayer.getPosition();
      // gets the whole image below the top layer's position
      ArrayList<ArrayList<RGBPixel>> wholeImage = getWholeImageHelp(topLayerPosition);
      // whole image below top layer as a layer
      Layer bottomLayer = new Layer(wholeImage, "bottomLayer", 0);
      changeLayer(filter.difference(topLayer, bottomLayer));


    }
  }


  /**
   * Brightens the given layer by every pixel's value.
   * @param layerName : Name of layer being brightened
   * @throws IllegalStateException : If project hasn't started
   * @throws IllegalArgumentException : If layer doesn't exist
   */
  @Override
  public void brightenValue(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.brightenValue(super.layers.get(layerName)));
  }


  /**
   * Brightens the given layer by the luma value for every pixel.
   * @param layerName : Name of layer being brightened
   * @throws IllegalStateException : if project hasn't started
   * @throws IllegalArgumentException : if layer doesn't exist
   */
  @Override
  public void brightenLuma(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.brightenLuma(super.layers.get(layerName)));
  }


  /**
   * Brightens the given layer by the intensity value of individual pixels.
   * @param layerName : Name of layer being brightened
   * @throws IllegalStateException : if project isn't started
   * @throws IllegalArgumentException : if layer doesn't exist
   */
  @Override
  public void brightenIntensity(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.brightenIntensity(super.layers.get(layerName)));
  }


  /**
   * Darkens the given layer by the individual pixel's values.
   * @param layerName : Name of layer being darkened
   * @throws IllegalStateException : if project isn't started
   * @throws IllegalArgumentException : if layer doesn't exist
   */
  @Override
  public void darkenValue(String layerName) throws IllegalStateException, IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.darkenValue(super.layers.get(layerName)));
  }


  /**
   * Darkens the given layer by the luma value of every pixel.
   * @param layerName : Name of layer being darkened
   * @throws IllegalStateException : if project isn't started
   * @throws IllegalArgumentException : if layer doesn't exist
   */
  @Override
  public void darkenLuma(String layerName) throws IllegalStateException, IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.darkenLuma(super.layers.get(layerName)));
  }


  /**
   * Darkens the given layer by the intensity value of every pixel.
   * @param layerName : Name of layer being darkened
   * @throws IllegalStateException : if project isn't started
   * @throws IllegalArgumentException : layer doesn't exist
   */
  @Override
  public void darkenIntensity(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.darkenIntensity(super.layers.get(layerName)));
  }

  /**
   * Modifies the ArrayList of Pixels so that they only have red value.
   *
   * @param layerName : the name of the layer whihc is being changed.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  @Override
  public void onlyRed(String layerName) throws IllegalStateException, IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.onlyRed(super.layers.get(layerName)));
  }

  /**
   * Modifies the ArrayList of Pixels so that they only have green value.
   *
   * @param layerName : the name of the layer whihc is being changed.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  @Override
  public void onlyGreen(String layerName) throws IllegalStateException, IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.onlyGreen(super.layers.get(layerName)));
  }


  /**
   * Modifies the ArrayList of Pixels so that they only have blue value.
   *
   * @param layerName : the name of the layer whihc is being changed.
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  @Override
  public void onlyBlue(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.onlyBlue(super.layers.get(layerName)));
  }

  /**
   * Removes a filter on the given layer.
   *
   * @param layerName : layerName
   * @throws IllegalStateException    : when the project has not yet been loaded in.
   * @throws IllegalArgumentException : if layer name doesn't exist
   */
  @Override
  public void removeFilter(String layerName) throws IllegalStateException,
          IllegalArgumentException {
    super.stateExpected();
    super.layerExpceted(layerName);
    changeLayer(filter.removeFilter(super.layers.get(layerName)));
  }


  /**
   * Changes the given layer and updates its position in the hashmap fields accordingly.
   * @param newLayer : Layer
   */
  private void changeLayer(Layer newLayer) {
    super.pixels = newLayer.getPixels();
    // for remove filter it used to be newLayer.getPosition() so not sure if that chnages it
    int position = super.layers.get(newLayer.getName()).getPosition();
    super.layers.replace(newLayer.getName(), newLayer);
    super.numberedLayers.replace(position, newLayer);

  }




  /**
   * Creates an arrayList of all the layer's names currently stored in this class's
   * Linked HashMap of layers' names.
   *
   * @return ArrayList of Layers's names
   */
  @Override
  public ArrayList<String> getLayerNames() {
    ArrayList<String> layerArray = new ArrayList<>();
    for (String name : layers.keySet()) {
      // effectivly hides the base layer and any null named layers not caught
      if (!name.equals("AAAAAAAAAAAAAAAAAAAAAAAAAAA") && !name.equals("null")) {
        layerArray.add(name);
      }
    }
    return layerArray;
  }


  /**
   * Clears an existing layer with the given name.
   *
   * @param layerName : name of the current layer
   * @throws IllegalStateException    : if you try to clear a lyer before a project started
   * @throws IllegalArgumentException : bad layer name.
   */
  @Override
  public void clearLayer(String layerName) throws IllegalStateException, IllegalArgumentException {
    this.stateExpected();
    this.layerExpceted(layerName);
    changeLayer(filter.clearLayer(super.layers.get(layerName)));
    changeLayer(filter.clearLayer(super.layers.get("AAAAAAAAAAAAAAAAAAAAAAAAAAA")));
  }




  /**
   * Removes an existing layer with the given name.
   *
   * @param layerName : name of the current layer
   * @throws IllegalStateException    : if you try to remove a lyer before a project started
   * @throws IllegalArgumentException : bad layer name.
   */
  @Override
  public void removeLayer(String layerName) throws IllegalStateException, IllegalArgumentException {
    this.stateExpected();
    this.layerExpceted(layerName);
    clearLayer(layerName);
    updateLayers(layers.get(layerName));
  }


  /**
   * The actual behavior needed to remove a layer. Deals with removing the layer and updating
   * eveyr other layer's position to accurately reflect the change in ordering.
   * @param removedLayer the layer being removed.
   */
  private void updateLayers(Layer removedLayer) {
    // the position of the removed item
    int posOfRemovedItem = super.layers.get(removedLayer.getName()).getPosition();
    layers.remove(removedLayer.getName());
    numberedLayers.remove(posOfRemovedItem);
    for (int i = 0; i < numberedLayers.size() + 1; i++) {
      if (i > posOfRemovedItem) {
        Layer a = numberedLayers.remove(i);
        numberedLayers.put(i - 1, a);
        a.decPosition();
      }
    }

  }


  /**
   * This helper method returns the image of all layers below a specified layer.
   * Used in brighten and darken methods.
   *
   * @param topLayerPosition : int
   * @return 2d ArrayList of Pixels
   */
  @Override
  public ArrayList<ArrayList<RGBPixel>> getWholeImageHelp(int topLayerPosition) {
    ArrayList<ArrayList<RGBPixel>> wholeImage = new ArrayList<>();   // whole image below
    boolean flag = false;

    // iterate over every layer below the topLayer and combine them
    for (int i = 0; i < topLayerPosition; i++) {
      Layer currentLayer = super.numberedLayers.get(i);

      if (!flag) {
        wholeImage = currentLayer.getPixels();
        flag = true;
      } else {

        for (int row = 0; row < super.height; row++) {

          ArrayList<RGBPixel> tempRow = new ArrayList<>();

          for (int col = 0; col < super.width; col++) {
            RGBPixel currentPixel = currentLayer.getPixels().get(row).get(col);
            RGBPixel oldPixel = wholeImage.get(row).get(col);

            RGBPixel newPixel = currentPixel.combinePixels(oldPixel);

            tempRow.add(newPixel);
          }

          wholeImage.set(row, tempRow);
        }

      }
    }
    return wholeImage;
  }


  /**
   * Returns a buffered image of the whole composite image of the project.
   *
   * @return : a buffered image
   */
  @Override
  public BufferedImage getWholeImage() {

    int layerCount = getLayerCount();
    ArrayList<ArrayList<RGBPixel>> image;


    updateLayerStatus();
    image = getWholeImageHelp(layerCount);

    if (image.size() == 0) {
      return null;
    } else {
      return getBufferedImage(image);
    }

  }




  /**
   * Runs through all the layers that have been stored in this.newLayers
   * and reruns the filter method on them.
   */
  private void updateLayerStatus() {
    for (Map.Entry<String, Layer> entry : super.layers.entrySet()) {
      String layerName = entry.getKey();
      Layer value = entry.getValue();

      switch (value.getFilter()) {
        case "difference":
          difference(layerName);
          break;
        case "brighten-screen":
          brightenScreen(layerName);
          break;
        case "darken-multiply":
          darkenMultiply(layerName);
          break;
        case "brighten-value":
          brightenValue(layerName);
          break;
        case "brighten-luma":
          brightenLuma(layerName);
          break;
        case "brighten-intensity":
          brightenIntensity(layerName);
          break;
        case "darken-value":
          darkenValue(layerName);
          break;
        case "darken-luma":
          darkenLuma(layerName);
          break;
        case "darken-intensity":
          darkenIntensity(layerName);
          break;
        case "red-component":
          onlyRed(layerName);
          break;
        case "green-component":
          onlyGreen(layerName);
          break;
        case "blue-component":
          onlyBlue(layerName);
          break;
        default:
          break;
      }
    }
  }
}
