package controller.commands.project;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.commands.AbstractCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;


/**
 * Represents the command that loads an image into the program.
 */
public class LoadProject extends AbstractCommand {

  private final ImageViewInterface view;

  /**
   * Constructor for a LoadProject class.
   *
   * @param name : the data of a project file.
   */
  public LoadProject(String name, ImageViewInterface view) throws IllegalArgumentException {
    super(name);
    this.view = view;
    Scanner lsc = null;
    try {
      lsc = new Scanner(new FileInputStream(name));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }
  }

  /**
   * Executes the save project method in the model.
   *
   * @param model : an Image Model
   */
  @Override
  public void execute(ImageModelInterface model) {
    try {
      Scanner lsc = new Scanner(new FileInputStream(name));
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (lsc.hasNextLine()) {
        String s = lsc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }
      lsc.close();
      model.loadProject(builder.toString());
      ArrayList<String> names = model.getLayerNames();
      this.view.updateLayerRadioButton(names);
      view.updateButtonOptions("started");
    } catch (IOException e) {
      // do nothing already verified it's all good in constructor.
    }
  }
}
