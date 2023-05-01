package controller.commands.filters.darken;

import controller.commands.filters.AbstractFilter;


/**
 * Represents commands which specfically darken the image somehow.
 */
public abstract class DarkenAbstract extends AbstractFilter {

  /**
   * Constructor that sets layer name.
   *
   * @param name : the layer name.
   */
  public DarkenAbstract(String name) {
    super(name);
  }
}