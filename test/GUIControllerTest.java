import org.junit.Test;


import controller.ImageControllerGUI;

import model.ImageModel;
import model.ImageModelPPM;

import view.ImageViewInterface;
import view.SwingGuiView;
import static org.junit.Assert.assertEquals;


/**
 * Testing class for a GUI controller.
 */
public class GUIControllerTest {

  private ImageModel model;
  private ImageControllerGUI controller;
  Readable in;


  /**
   * Setu[ the methods.
   */
  private void setUp() {
    this.model = new ImageModelPPM();
    ImageViewInterface view = new SwingGuiView(null);
    this.controller = new ImageControllerGUI(this.model, view);
    view.setController(controller);
  }




  // Tests add-image-to-layer, add-layer, and start-project
  // Tests that the filter methods work
  @Test
  public void test1() {
    setUp();

    this.controller.runImage();
    this.controller.accept("start-project hi 500 500");

    assertEquals(this.model.getPixelAtCoord(0, 0).formatPixel(true), "0 0 0 0");

    this.controller.accept("add-layer l1");
    this.controller.accept("add-image-to-layer l1 " +
            "src\\image\\a4Image.ppm 0 0");

    assertEquals(this.model.getPixelAtCoord(0, 0).formatPixel(true),
            "193 25 25 255");

    this.controller.accept("set-filter l1 red-component");

    assertEquals(this.model.getPixelAtCoord(0, 0).formatPixel(true),
            "193 0 0 255");

    this.controller.accept("set-filter l1 remove-filter");
    assertEquals(this.model.getPixelAtCoord(0, 0).formatPixel(true),
            "193 25 25 255");

  }



  // Testing adding one image on top of another on same layer
  @Test
  public void test2() {
    setUp();

    this.controller.runImage();
    this.controller.accept("start-project hi 600 800");

    this.controller.accept("add-layer l1");
    this.controller.accept("add-image-to-layer l1 src/image/tako.ppm 0 0");

    this.controller.accept("add-image-to-layer l1 src/image/tako.ppm 150 150");
    assertEquals(this.model.getPixelAtCoord(150, 150).formatPixel(true),
            "173 179 151 255");

  }



  @Test
  public void test() {
    setUp();

    this.controller.accept("start-project hi 600 800");

    this.controller.accept("add-layer l1");
    this.controller.accept("add-image-to-layer l1 src/image/black.ppm 0 0");

    this.controller.accept("add-layer l2");
    this.controller.accept("add-image-to-layer l2 src/image/tako.ppm 0 0");
    assertEquals(this.model.getPixelAtCoord(0, 0).formatPixel(true),
            "173 179 151 255");

    this.controller.accept("set-filter l2 darken-multiply");
    assertEquals(this.model.getPixelAtCoord(0, 0).formatPixel(true),
            "7 7 5 255");


  }





}
