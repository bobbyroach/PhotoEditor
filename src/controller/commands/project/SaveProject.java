package controller.commands.project;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;


/**
 * Represents the command that loads an image into the program.
 */
public class SaveProject extends AbstractCommand {

  /**
   * Constructor for a SaveProject class.
   *
   * @param name : the project file.
   */
  public SaveProject(String name) throws IllegalArgumentException {
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
      myWriter.write("fileData");
      myWriter.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("cant write to file");
    }
  }

  /**
   * Executes the save project method in the model.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    String fileData = model.saveProject();
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
