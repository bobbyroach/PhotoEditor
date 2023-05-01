package controller.commands.project;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;

/**
 * Represents the command that can add an image to a layer inside a project.
 */
public class AddImageToLayer extends AbstractCommand {
  private final String layerName;
  private final int xOffset;
  private final int yOffset;

  private String data;
  private String type;


  private final ImageViewInterface view;

  /**
   * The constructor.
   *
   * @param xOffset : x offset where image is added onto.
   * @param yOffset : y offset where image is added onto.
   */
  public AddImageToLayer(String layerName, String name, int xOffset, int yOffset,
                         ImageViewInterface view)
          throws IllegalArgumentException {
    super(name);
    if (xOffset < 0 || yOffset < 0 || layerName == null) {
      throw new IllegalArgumentException("bad inputs");
    }
    this.view = view;
    this.layerName = layerName;
    this.xOffset = xOffset;
    this.yOffset = yOffset;
    Scanner sc;
    try {
      File newImageFile;
      newImageFile = new File(name);
      sc = new Scanner(newImageFile);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
  }


  /**
   * Returns a string representation of the given image file type.
   *
   * @param fileName : String
   */
  private void getFileType(String fileName) {
    String imageType = fileName.substring(fileName.length() - 4);
    type = imageType;
    try {
      if (imageType.equals(".png") || imageType.equals(".jpg")) {
        pngJpgHelper();

      } else if (imageType.equals(".ppm")) {
        ppmHelper();
      } else {
        throw new IllegalArgumentException();
      }
    } catch (IllegalStateException excc) {
      throw new IllegalArgumentException("bad stuff 8=D");
    } catch (IllegalArgumentException es) {
      throw new IllegalArgumentException("File type not supported");
    }


  }


  private void ppmHelper() throws IllegalStateException {
    Scanner sc;
    File file = new File(name);
    try {
      sc = new Scanner(file);
      StringBuilder builder = new StringBuilder();
      while (sc.hasNextLine()) {
        String sheee = sc.nextLine();
        if (sheee.charAt(0) != '#') {
          builder.append(sheee + System.lineSeparator());
        }
      }
      sc.close();
      data = builder.toString();
    } catch (IOException e) {
      throw new IllegalStateException("Couldnt read file");
    }
  }


  private void pngJpgHelper() {
    // Load image from file
    BufferedImage image = null;
    int width = 0;
    int height = 0;
    try {
      image = ImageIO.read(new File(name));
      // Get image dimensions
      width = image.getWidth();
      height = image.getHeight();
    } catch (IOException e) {
      throw new IllegalStateException("Couldnt read file");
    }

    // Convert image to string
    StringBuilder sb = new StringBuilder();
    sb.append("P3" + System.lineSeparator());
    sb.append(width).append(" ").append(height).append(System.lineSeparator());
    sb.append("255" + System.lineSeparator());
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = image.getRGB(x, y);
        int r = (pixel >> 16) & 0xff;
        int g = (pixel >> 8) & 0xff;
        int b = pixel & 0xff;
        int a = (pixel >> 24) & 0xff;
        sb.append(r).append(" ").append(g).append(" ").append(b).append(" ")
                .append(a).append(" ");
      }
      sb.append(System.lineSeparator());
    }
    data = sb.toString();
  }


  /**
   * Adds an image to the given layer.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    this.getFileType(name);
    model.addImageToLayer(layerName, data, xOffset, yOffset, type);
    view.updateButtonOptions("image-added");
  }
}
