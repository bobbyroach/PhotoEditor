package controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.AbstractCommand;
import controller.commands.project.AddImageToLayer;
import controller.commands.project.AddLayer;
import controller.commands.project.ClearLayer;
import controller.commands.project.LoadProject;
import controller.commands.project.NewProject;
import controller.commands.project.RemoveLayer;
import controller.commands.project.SaveImage;
import controller.commands.project.SaveProject;

import model.ImageModelInterface;
import view.ImageViewInterface;


/**
 * Class for a GUI controller that takes in a view and a model and is responsible for
 * controlling the model and its changes based on the user's input from the view's GUI.
 */
public class ImageControllerGUI extends AbstractImageController {



  // Keeps track of whether the project has been started or not
  private boolean hasStartedEditing;


  /**
   * Constructor for an ImageControllerGUI object. This takes in a
   * graphical interface view that acts as a visual representation
   * of the model. This controller then manipulates the model and
   * changes the view accordingly.
   *
   * @param view  : an ImageGUIView
   * @param model : An image model
   */
  public ImageControllerGUI(ImageModelInterface model, ImageViewInterface view) {
    super(model, view);
    this.hasStartedEditing = false;
  }


  /**
   * Handles the initialization of
   * the method that will accept input from the GUI.
   */
  @Override
  public void runImage() {
    // not used in this implementation
  }


  /**
   * This method takes input from the graphical interface and
   * uses that input to change the model appropriately. First,
   * it populates an arrayList with strings of commands and then
   * uses those values to change the model.
   *
   * @param input : String of inputs from the user
   */
  private void runCommand(String input) throws IllegalArgumentException {


    primaryCommands.put("load-project", (Scanner s) -> {
      String filePath = s.next().replace("/", "\\");
      LoadProject loadProject;
      loadProject = new LoadProject(filePath, view);
      this.hasStartedEditing = true;
      loadProject.execute(model);
      return null;
    });

    primaryCommands.put("new-project", (Scanner s) -> {
      String projectName = s.next();
      int widthProj = s.nextInt();
      int heightProj = s.nextInt();
      this.hasStartedEditing = true;
      return new NewProject(projectName, widthProj, heightProj, this.view);
    });

    secondaryCommands.put("add-layer", (Scanner s) -> {
      String entry = s.next();
      return new AddLayer(entry, this.view);
    });

    secondaryCommands.put("remove-layer", (Scanner s) -> {
      String entry = s.next();
      return new RemoveLayer(entry, this.view);
    });

    secondaryCommands.put("clear-layer", (Scanner s) -> {
      String entry = s.next();
      return new ClearLayer(entry);
    });


    secondaryCommands.put("add-image-to-layer", (Scanner s) -> {
      String layerName = s.next();
      String filepath = s.next().replace("/", "\\");
      int x = s.nextInt();
      int y = s.nextInt();
      AddImageToLayer addedImage;
      addedImage = new AddImageToLayer(layerName, filepath, x, y, this.view);
      return addedImage;
    });


    secondaryCommands.put("save-project", (Scanner s) -> {
      String projectPath = s.next().replace("/", "\\");
      SaveProject savedProj;
      savedProj = new SaveProject(projectPath);
      hasJustSaved = true;
      return savedProj;
    });


    secondaryCommands.put("save-image", (Scanner s) -> {
      String filePath = s.next().replace("/", "\\");
      SaveImage saveImages;
      saveImages = new SaveImage(filePath);
      hasJustSaved = true;
      return saveImages;
    });


    Scanner scanner = new Scanner(input);
    while (scanner.hasNext()) {
      hasJustSaved = false;
      String in = scanner.next();
      // Commands that can be done ONLY if a project is not currently open
      if (!this.hasStartedEditing) {
        Function<Scanner, AbstractCommand> cmd = primaryCommands.getOrDefault(in,
                null);
        command = cmd.apply(scanner);
      }
      // AFTER PROJECT WAS STARTED
      else {
        Function<Scanner, AbstractCommand> cmd = secondaryCommands.getOrDefault(in,
                null);
        command = cmd.apply(scanner);
      }
      if (this.command != null) {
        command.execute(this.model);
        command = null;
      } else {
        try {
          this.view.renderMessage("Unknown Command");
        } catch (IOException ex) {
          // do nothing
        }
      }
    }

    if (this.model.getWholeImage() != null) {
      this.view.refresh(model.getWholeImage());
    }
  }


  /**
   * This method accepts input and calls the runCommand method with
   * the given input to make the appropriate changes to the model.
   * If there's an error processing the input, the graphical interface
   * will display that error to the user.
   *
   * @param input : A string input from the user.
   */
  @Override
  public void accept(String input) {

    try {
      runCommand(input);

    } catch (Exception ex) {
      try {
        this.view.renderMessage("Error: " + ex.getMessage());
      } catch (IOException ioex) {
        // Do nothing
      }
    }
  }

}


