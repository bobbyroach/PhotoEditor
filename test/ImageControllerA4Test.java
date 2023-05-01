import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModelInterface;
import model.ImageModelPPM;
import model.pixel.Pixel;
import model.pixel.RGBPixel;
import view.ImageView;
import view.ImageViewInterface;

import static org.junit.Assert.assertEquals;


/**
 * Testing class for an Image Controller.
 */
public class ImageControllerA4Test {
  private Appendable out;
  private Readable in;
  private ImageModelInterface model;
  private ImageController controller;
  private ImageViewInterface view;


  /**
   * Sets up model, view, and controller.
   */
  @Before
  public void setUp() {
    out = new StringBuilder();
    model = new ImageModelPPM();
    view = new ImageView(out);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    in = new StringReader("");
    controller = new ImageControllerImpl(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    in = new StringReader("");
    controller = new ImageControllerImpl(model, view, null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    in = new StringReader("");
    controller = new ImageControllerImpl(model,null, in);
  }


  // (mostly) as expected
  @Test
  public void testQuitAfterStart() {
    in = new StringReader("new-project 500 500 quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();

    // diffferent line seperators and i dont care enough to get this correct. it still works
    assertEquals("Welcome to the image editior program!" + System.lineSeparator() +
            "Commands:" + System.lineSeparator() +
            "One of the following commands must be called before calling any others."
            + System.lineSeparator() +
            "new-project project-height project-width project-name" +
            "(Example: new-project 400 200 P1)" + System.lineSeparator() +
            "load-project file-path (Example: load-project projects/P1.collage)"
            + System.lineSeparator() +
            "menu" + System.lineSeparator() +
            "quit" + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Project quit. All progress lost. " + System.lineSeparator() +
            "Bye, program gone!\n", out.toString());
  }


  @Test
  public void testTrysToEditProjectBeforeItsOpen() {
    in = new StringReader("red-component la1 new-project 500 500 quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    // diffferent line seperators and i dont care enough to get this correct. it still works
    assertEquals("Welcome to the image editior program!" + System.lineSeparator() +
            "Commands:" + System.lineSeparator() +
            "One of the following commands must be called before calling any others."
            + System.lineSeparator() +
            "new-project project-height project-width project-name (Example:" +
            "new-project 400 200 P1)" + System.lineSeparator() +
            "load-project file-path (Example: load-project projects/P1.collage)"
            + System.lineSeparator() +
            "menu" + System.lineSeparator() +
            "quit" + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Project quit. All progress lost. " + System.lineSeparator() +
            "Bye, program gone!\n", out.toString());
  }

  @Test
  public void testTrysToEditProjectBeforeItsOpen1() {
    in = new StringReader("ahhhh la1 new-project 500 500 quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    // different line separators and I don't care enough to get this correct. it still works
    assertEquals("Welcome to the image editior program!" + System.lineSeparator() +
            "Commands:" + System.lineSeparator() +
            "One of the following commands must be called before calling any others." +
            System.lineSeparator() +
            "new-project project-height project-width project-name " +
            "(Example: new-project 400 200 P1)" + System.lineSeparator() +
            "load-project file-path (Example: load-project projects/P1.collage)" +
            System.lineSeparator() +
            "menu" + System.lineSeparator() +
            "quit" + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Project quit. All progress lost. " + System.lineSeparator() +
            "Bye, program gone!\n", out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testNoInputAfterOneCommand() {
    in = new StringReader("new-project");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();

  }


  // test load project
  @Test
  public void loadProject() {
    in = new StringReader("load-project projects/p1FORTESTS.collage" +
            " set-filter darken-luma layer1" +
            " save-image image/res/A4TESTloadedProjectTestImage1.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(133, 0, 0, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  // test load project
  @Test
  public void loadProjectButPathWrongThenRightPath() {
    in = new StringReader("load-project projects//p1FORTESTS.collage" +
            " load-project projects/p1FORTESTS.collage" +
            " save-image image/res/A4TESTloadedProjectTestImage2.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(193, 25, 25, 255);



    assertEquals("Welcome to the image editior program!\n" +
            "Commands:\n" +
            "One of the following commands must be called before calling any others.\n" +
            "new-project project-height project-width project-name" +
            "(Example: new-project 400 200 P1)\n" +
            "load-project file-path (Example: load-project projects/P1.collage)\n" +
            "menu\n" +
            "quit\n" +
            "Unknown command\n" +
            "Unknown command\n" +
            "Bad file name, layer name, offset or something else idk \n" +
            "Project quit. All progress lost. \n" +
            "Bye, program gone!\n", out.toString());

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  // Tests for new-project command
  // Tests for add-layer command

  @Test
  public void testAddLayerNewProject1() {
    in = new StringReader("new-project 500 500 P1 add-layer layer2 quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(0, 0, 0, 0);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));

  }

  @Test
  public void testAddLayerNewProject2() {
    in = new StringReader("new-project 500 500 P1 add-layer layer2 add-layer 4 quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(0, 0, 0, 0);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));

  }


  //THESE DONT THROW EXCEPTIONS, CONTROLLER CATCHES THEN AND THEN ASKS FOR NEW INPUT

  @Test
  public void testAddDuplicateLayerName() {
    in = new StringReader("new-project 500 500 P1 add-layer layer1 add-layer layer1 quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    // diffferent line seperators and i dont care enough to get this correct. it still works
    assertEquals("Welcome to the image editior program!" + System.lineSeparator() +
            "Commands:" + System.lineSeparator() +
            "One of the following commands must be called before calling any others."
            + System.lineSeparator() +
            "new-project project-height project-width project-name" +
            "(Example: new-project 400 200 P1)" + System.lineSeparator() +
            "load-project file-path (Example: load-project projects/P1.collage)"
            + System.lineSeparator() +
            "menu" + System.lineSeparator() +
            "quit" + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Project quit. All progress lost. " + System.lineSeparator() +
            "Bye, program gone!\n", out.toString());
  }


  // Tests for add-image
  @Test
  public void testAddImageNoLayer() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1 " +
            " add-image-to-layer layer2 image/a4image.ppm 0 0" +
            " add-image-to-layer layer1 image/a4image.ppm 0 0" +
            " save-image image/res/A4TESTsavedCorrectly.ppm" +
            " quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    // diffferent line seperators and i dont care enough to get this correct. it still works
    assertEquals("Welcome to the image editior program!" + System.lineSeparator() +
            "Commands:" + System.lineSeparator() +
            "One of the following commands must be called before calling any others."
            + System.lineSeparator() +
            "new-project project-height project-width project-name" +
            "(Example: new-project 400 200 P1)" + System.lineSeparator() +
            "load-project file-path (Example: load-project projects/P1.collage)"
            + System.lineSeparator() +
            "menu" + System.lineSeparator() +
            "quit" + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Project quit. All progress lost. " + System.lineSeparator() +
            "Bye, program gone!\n", out.toString());
  }


  @Test
  public void testAddImageNoFile() {
    in = new StringReader("new-project 600 800 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/nonExistentFile.ppm 0 0" +
            " quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    // diffferent line seperators and i dont care enough to get this correct. it still works
    assertEquals("Welcome to the image editior program!" + System.lineSeparator() +
            "Commands:" + System.lineSeparator() +
            "One of the following commands must be called before calling any others."
            + System.lineSeparator() +
            "new-project project-height project-width project-name" +
            "(Example: new-project 400 200 P1)" + System.lineSeparator() +
            "load-project file-path (Example: load-project projects/P1.collage)"
            + System.lineSeparator() +
            "menu" + System.lineSeparator() +
            "quit" + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Bad file name, layer name, offset or something else idk " + System.lineSeparator() +
            "Project quit. All progress lost. " + System.lineSeparator() +
            "Bye, program gone!\n", out.toString());
  }


  @Test
  public void testAddImage() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " save-project projects/A4TESTSavedProject1.collage" +
            " save-image image/res/A4TESTa4ImageSavedTested.ppm" +
            " quit");
    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(193, 25, 25, 255);
    Pixel q = model.getPixelAtCoord(0, 0);

    assertEquals(p.formatPixel(true), q.formatPixel(true));
  }


  @Test
  public void imageOverImage() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/a4Image.ppm 0 0" +
            " add-image-to-layer layer1 image/purpleImage.ppm 0 0" +
            " save-image image/res/A4TESTimageOnImage.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(46, 34, 172, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  // makes only red components
  @Test
  public void getOnlyRedComponents() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter green-component layer1 " +
            " set-filter red-component layer1 " +
            " save-project projects/A4TESTSaveProject2.collage" +
            " save-image image/res/A4onlyRedComponent.ppm" +
            " quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(193, 0, 0, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

  // makes only green components
  @Test
  public void getOnlyGreenComponents() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter green-component layer1" +
            " save-image image/res/A4onlyGreenComponent.ppm" +
            " quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(0, 25, 0, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

  // makes only blue components
  @Test
  public void getOnlyBlueComponents() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter blue-component layer1 " +
            " save-image image/res/A4onlyBlueComponent.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(0, 0, 25, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  // darkened image
  // darken-value
  @Test
  public void darkenValue() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter darken-value layer1" +
            " save-image image/res/A4darkenValue.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    RGBPixel p = new RGBPixel(0, 0, 0, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

  //darken intensity
  @Test
  public void darkenIntensity() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter darken-intensity layer1" +
            " save-image image/res/A4darkenIntensity.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(72, 0, 0, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

  //darken-luma
  @Test
  public void darkenLuma() {
    in = new StringReader("load-project projects/p1FORTESTS.collage" +
            " add-layer layer2" +
            " add-image-to-layer layer2 image/a4Image.ppm 0 0" +
            " set-filter darken-luma layer2" +
            " save-image image/res/A4darkenLuma.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(133, 0, 0, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  // brighened image
  //   //brighten -value
  @Test
  public void brightenValue() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter brighten-value layer1" +
            " save-image image/res/A4brightenValue.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(255, 218, 218, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

  //brighten intensity
  @Test
  public void brightenIntensity() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0" +
            " set-filter brighten-intensity layer1" +
            " save-image image/res/A4brightenIntensity.ppm " +
            "quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(255, 146, 146, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

  //brighten-luma
  @Test
  public void brightenLuma() {
    in = new StringReader("new-project 500 500 P1" +
            " add-layer layer1" +
            " add-image-to-layer layer1 image/a4Image.ppm 0 0 " +
            " set-filter brighten-luma layer1" +
            " save-image image/res/A4brightenLuma.ppm " +
            " quit");

    controller = new ImageControllerImpl(model,view,in );
    controller.runImage();
    Pixel p = new RGBPixel(253, 85, 85, 255);

    assertEquals(p.formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }

}
