
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModelInterface;
import model.ImageModelPPM;
import model.pixel.RGBPixel;
import view.ImageView;
import view.ImageViewInterface;


/**
 * Testing class for an Image Controller.
 */
public class ImageControllerA5Tests {
  private Readable in;
  private ImageModelInterface model;
  private ImageController controller;
  private ImageViewInterface view;



  /**
   * Sets up model, view, and controller.
   *
   */
  @Before
  public void setUp() {
    Appendable out = new StringBuilder();
    model = new ImageModelPPM();
    view = new ImageView(out);
  }


  // brighten screen
  @Test
  public void brightenScreenA4Image() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/purpleImage.ppm 0 0" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter brighten-screen layer1" +
            " save-project projects/A5TESTmultipleImages.collage" +
            " save-image image/res/A5brightenScreen.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(235, 100, 100, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  // brighten screeen
  @Test
  public void brightenScreenTako() {
    in = new StringReader("new-project 800 800 P1" +
            " add-layer layer4" +
            " add-image-to-layer layer4 image/black.ppm 0 700" +
            " add-layer layer3" +
            " add-image-to-layer layer3 image/white.ppm 400 300" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/purpleImage.ppm 0 50" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/tako.ppm 0 0" +
            " set-filter brighten-screen layer1" +
            " save-project projects/A5TESTmultipleImages.collage" +
            " save-image image/res/A5TESTbrightenScreenTakoPEPEP.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(173, 179, 151, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }





  // difference
  @Test
  public void differenceA4Image() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/purpleImage.ppm 0 0" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter difference layer1" +
            " save-image image/res/A5difference.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(147, 9, 147, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }



  // difference with tako
  @Test
  public void differenceTako() {
    in = new StringReader("new-project 800 800 P1" +
            " add-layer layer4" +
            " add-image-to-layer layer4 image/black.ppm 0 700" +
            " add-layer layer3" +
            " add-image-to-layer layer3 image/white.ppm 400 300" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/purpleImage.ppm 0 50" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/tako.ppm 0 0" +
            " set-filter difference layer1" +
            " save-image image/res/A5TESTdifferenceTako.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(173, 179, 151, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true) );
  }



  // darken-multiply
  @Test
  public void darkenMultiply() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/purpleImage.ppm 0 50" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter darken-multiply layer1" +
            " save-project projects/A5TESTdarkenMultiply.collage" +
            " save-image image/res/A5darkenMultiply.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(193, 25, 25, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }



  // darken-multiply
  @Test
  public void darkenMultiplyTako() {
    in = new StringReader("new-project 800 800 P1" +
            " add-layer layer4" +
            " add-image-to-layer layer4 image/black.ppm 0 700" +
            " add-layer layer3" +
            " add-image-to-layer layer3 image/white.ppm 400 300" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/purpleImage.ppm 0 50" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/tako.ppm 0 0" +
            " set-filter darken-multiply layer1" +
            " save-project projects/A5TESTdarkenMultiply1.collage" +
            " save-image image/res/A5TESTdarkenMultiply1.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(173, 179, 151, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

  @Test
  public void darkenMultiply1() {
    in = new StringReader("load-project projects/A5TESTdarkenMultiply1.collage" +

            " save-image image/res/A5darkenMultiply123.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(193, 25, 25, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }




}
