package controller.commands.project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;


/**
 * Represents the command that saves an image into the program.
 */
public class SaveImage extends AbstractCommand {


  /**
   * Constructor for a save image class.
   *
   * @param name : the project file.
   */
  public SaveImage(String name) throws IllegalArgumentException {
    super(name);
    File newFileS;
    try {
      newFileS = new File(name);
      if (!newFileS.createNewFile()) {
        throw new IOException("File wasn't created.");
      }
    } catch (IOException t) {
      throw new IllegalArgumentException("File wasn't created.");
    }
    try {
      FileWriter myWriter;
      myWriter = new FileWriter(name);
      myWriter.write("");
      myWriter.close();

    } catch (IOException e) {
      throw new IllegalArgumentException("cant write to file");
    }
  }



  /**
   * Executes the save image method in the model.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    String type = name.substring(name.length() - 3);
    // C:\Users\Danth\IdeaProjects\CS3500groupwork\image\res
    if (type.equals("png") || type.equals("jpg")) {
      BufferedImage img = model.getWholeImage();
      File file = new File(name);
      try {
        ImageIO.write(img, type, file);
      } catch (IOException exce) {
        // do nothing
      }
    } else if (type.equals("ppm")) {
      String fileData = model.saveImage();
      try {
        FileWriter myWriter;
        myWriter = new FileWriter(name, false);
        myWriter.write(fileData);
        myWriter.close();
      } catch (IOException e) {
        // do nothing, already checking if file can be written in constructor.
      }
    }
  }
}
