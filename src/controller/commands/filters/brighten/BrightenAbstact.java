package controller.commands.filters.brighten;

import controller.commands.filters.AbstractFilter;


/**
 * Represents commands which specfically brighten the image somehow.
 */
public abstract class BrightenAbstact extends AbstractFilter {

  /**
   * Constructor that sets layer name.
   *
   * @param name : the layer name.
   */
  public BrightenAbstact(String name) {
    super(name);
  }
}
