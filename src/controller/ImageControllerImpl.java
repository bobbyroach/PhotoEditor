package controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.AbstractCommand;
import controller.commands.menus.MenuAfter;
import controller.commands.menus.MenuBefore;
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
 * Class for an Image Controller that runs on the ImageModel class and handles its commands.
 * It takes in a view, a model, and a readable and controls the model.
 */
public class ImageControllerImpl extends AbstractImageController {

  private final String initalizedPath;

  // Keeps track of whether the project has been started or not
  private boolean hasStartedEditing;


  /**
   * Constructor for a new ImageControllerImpl object.
   *
   * @param model : an image model
   * @param in    : a readable for taking inputs
   * @param view  : an appendable for outputting result
   */
  public ImageControllerImpl(ImageModelInterface model, ImageViewInterface view, Readable in) {
    super(model, view);
    this.in = in;
    this.hasStartedEditing = false;
    Path currentRelativePath = Path.of("").toAbsolutePath();
    this.initalizedPath = currentRelativePath + "\\";

  }

  /**
   * Handles all command input when the controller is operating on an image model.
   * <p>
   * Handles two input cases: when an image isnt currently in the project and when an image is
   * already loaded.
   * </p>
   *
   * <p>
   * The [quit] command isn't a command like others like [save-project] etc. It gets caught as
   * a custom exception, which when throw, triggers quitDaPorgram().
   * </p>
   *
   * @throws IllegalStateException - If the controller isn't able to re ad the input, or transmit
   *                               the output, or doesn't have an input to read from.
   */
  @Override
  public void runImage() throws IllegalStateException {
    Scanner scanner = new Scanner(this.in);


    primaryCommands.put("load-project", (Scanner s) -> {
      String filePath = (this.initalizedPath + s.next()).replace("/", "\\");
      LoadProject loadProject;
      loadProject = new LoadProject(filePath, view);
      this.hasStartedEditing = true;
      return loadProject;
    });

    // why is this a different order than in the gui Controller? BECUASE WE CAN AND IF WE CAN WE
    // DO AND IF WE DO WE DONE DID 
    primaryCommands.put("new-project", (Scanner s) -> {
      int heightProj = s.nextInt();
      int widthProj = s.nextInt();
      String projectName = s.next();
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
      String filepath = (this.initalizedPath + s.next()).replace("/", "\\");
      int x = s.nextInt();
      int y = s.nextInt();
      AddImageToLayer addedImage;
      addedImage = new AddImageToLayer(layerName, filepath, x, y, this.view);
      return addedImage;
    });

    secondaryCommands.put("menu", (Scanner s) -> {
      return new MenuAfter("doesnt matter");
    });

    secondaryCommands.put("save-project", (Scanner s) -> {
      String projectPath = (this.initalizedPath + s.next()).replace("/", "\\");
      SaveProject savedProj;
      savedProj = new SaveProject(projectPath);
      this.hasJustSaved = true;
      return savedProj;
    });


    secondaryCommands.put("save-image", (Scanner s) -> {
      String filePath = (this.initalizedPath + s.next()).replace("/", "\\");
      SaveImage saveImages;
      saveImages = new SaveImage(filePath);
      this.hasJustSaved = true;
      return saveImages;
    });

    // entry messages
    this.welcomeMessage();
    MenuBefore me = new MenuBefore("");
    me.execute(this.view);

    // actual running stuff
    while (!this.model.hasProjectClosed()) {

      // resets the flag to recognize the command is no longer save-image/project
      hasJustSaved = false;

      if (scanner.hasNext()) {
        String in = scanner.next();
        if (in.equals("quit")) {
          this.quitDaProgram();
          this.farewellMessage();
          scanner.close();
          return;
        }
        try {
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
            throw new IllegalArgumentException("bad command");
          }
        } catch (NullPointerException ne) {
          // do nothing, feature not a bug <3 :)
        } catch (NoSuchElementException nsee) {
          // dpp tjomhaa
        } catch (IllegalArgumentException eeee) {
          try {
            view.renderMessage("Bad file name, layer name, offset or something else idk " +
                    "\r");
          } catch (IOException ex) {
            throw new IllegalStateException("Failed to transmit to the view. 5 \n");
          }
        } catch (IllegalStateException se) {
          try {
            view.renderMessage("Invalid behavior, what you did isn't possible at this time, try " +
                    "again 6 " +
                    "\r");
          } catch (IOException ex) {
            throw new IllegalStateException(ex);
          }
        }
      } else {
        break;
      }
    }
    // no more inputs
    this.quitDaProgram();
    this.farewellMessage();
    scanner.close();
  }


  /**
   * The greeting message to users.
   *
   * @throws IllegalStateException : if unable to display welcome method.
   */
  private void welcomeMessage() throws IllegalStateException {
    try {
      view.renderMessage("Welcome to the image editior program!" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * The fareweell message to users.
   *
   * @throws IllegalStateException : if unable to dispaly farewell message.
   */
  private void farewellMessage() throws IllegalStateException {
    try {
      view.renderMessage("Bye, program gone!" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Handles the quiting behavior.
   */
  private void quitDaProgram() {
    if (this.hasJustSaved) {
      try {
        this.view.renderMessage("Project quit. \r");
      } catch (IOException exc) {
        throw new IllegalStateException(exc);
      }
    } else {
      try {
        this.view.renderMessage("Project quit. All progress lost. \r");
      } catch (IOException exc) {
        throw new IllegalStateException(exc);
      }
    }
    this.model.setHeight(-1);
    this.model.setWidth(-1);
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
    // This method is not used in this controller implementation.
  }
}
