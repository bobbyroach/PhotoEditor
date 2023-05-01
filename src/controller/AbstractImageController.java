package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.AbstractCommand;
import controller.commands.filters.AbstractFilter;
import controller.commands.filters.brighten.BrightenIntensity;
import controller.commands.filters.brighten.BrightenLuma;
import controller.commands.filters.brighten.BrightenScreen;
import controller.commands.filters.brighten.BrightenValue;
import controller.commands.filters.colors.ChangeBlue;
import controller.commands.filters.colors.ChangeGreen;
import controller.commands.filters.colors.ChangeRed;
import controller.commands.filters.darken.DarkenIntensity;
import controller.commands.filters.darken.DarkenLuma;
import controller.commands.filters.darken.DarkenMultiply;
import controller.commands.filters.darken.DarkenValue;
import controller.commands.filters.darken.Difference;
import controller.commands.project.RemoveFilter;

import model.ImageModelInterface;
import view.ImageViewInterface;


/**
 * Abstract class for an ImageController. This takes in a model and introduces the runImage
 * and accept().
 */
public abstract class AbstractImageController implements ImageController {


  // The model to be used by this controller.
  protected ImageModelInterface model;
  // Command to be used on the model
  protected AbstractCommand command;

  protected boolean hasJustSaved;
  /*
Stores all the independent filter commands inside the set-filter command. Brighten-value,
red-component, difference are all examples.
*/
  protected Map<String, Function<Scanner, AbstractFilter>> setFilterCommands;
  /*
    Stores all the higher level commands that can only be done once:
    Such as: load-project, new-project
    */
  protected Map<String, Function<Scanner, AbstractCommand>> primaryCommands;
  /*
   Stores all the higher level commands that can only be done multiple times:
   Such as: add-layer, save-image, save-project, add-image-to-layer, set-filter, menu (unique to
   after project has been made)
   */
  protected Map<String, Function<Scanner, AbstractCommand>> secondaryCommands;
  // A readable
  Readable in;
  // GUI view to be used by this controller.
  protected final ImageViewInterface view;

  /**
   * Constructor for an AbstractImageController. Initializes the model, view, a bunch of other
   * fields and the command hashmaps, while putting some preset commands in the hashmaps.
   *
   * @param model : The model
   * @throws IllegalArgumentException : when provided null inputs
   */
  public AbstractImageController(ImageModelInterface model, ImageViewInterface view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Null inputs provided! 1");
    }
    this.model = model;
    this.view = view;
    this.command = null;
    this.hasJustSaved = false;
    setFilterCommands = new HashMap<>();
    primaryCommands = new HashMap<>();
    secondaryCommands = new HashMap<>();

    setFilterCommands.put("darken-value", (Scanner s) -> {
      return new DarkenValue(s.next());
    });
    setFilterCommands.put("darken-luma", (Scanner s) -> {
      return new DarkenLuma(s.next());
    });
    setFilterCommands.put("darken-intensity", (Scanner s) -> {
      return new DarkenIntensity(s.next());
    });
    setFilterCommands.put("darken-multiply", (Scanner s) -> {
      return new DarkenMultiply(s.next());
    });
    setFilterCommands.put("difference", (Scanner s) -> {
      return new Difference(s.next());
    });
    setFilterCommands.put("brighten-value", (Scanner s) -> {
      return new BrightenValue(s.next());
    });
    setFilterCommands.put("brighten-luma", (Scanner s) -> {
      return new BrightenLuma(s.next());
    });
    setFilterCommands.put("brighten-intensity", (Scanner s) -> {
      return new BrightenIntensity(s.next());
    });
    setFilterCommands.put("brighten-screen", (Scanner s) -> {
      return new BrightenScreen(s.next());
    });
    setFilterCommands.put("red-component", (Scanner s) -> {
      return new ChangeRed(s.next());
    });
    setFilterCommands.put("green-component", (Scanner s) -> {
      return new ChangeGreen(s.next());
    });
    setFilterCommands.put("blue-component", (Scanner s) -> {
      return new ChangeBlue(s.next());
    });
    setFilterCommands.put("remove-filter", (Scanner s) -> {
      return new RemoveFilter(s.next());
    });
    secondaryCommands.put("set-filter", (Scanner s) -> {
      String in = s.next();
      Function<Scanner, AbstractFilter> cmd = setFilterCommands.getOrDefault(in,
              null);
      return cmd.apply(s);
    });
  }

  /**
   * Handles all command input when the controller is operating on an image model.
   */
  @Override
  public abstract void runImage();

  /**
   * This method accepts input and calls the runCommand method with
   * the given input to make the appropriate changes to the model.
   * If there's an error processing the input, the graphical interface
   * will display that error to the user.
   *
   * @param input : A string input from the user.
   */
  @Override
  public abstract void accept(String input);
}
