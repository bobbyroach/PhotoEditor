import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

import model.ImageModelPPM;
import model.pixel.Pixel;
import model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;


/**
 * Testing class for an ImageModelPPM.
 */
public class ImageModelPPMTest {
  ImageModelPPM model;


  /**
   * Stuff to do at the prs.
   */
  private void setUp() {
    model = new ImageModelPPM();
    model.startProject("P", 600, 800);
    StringBuilder ppmFile = setUpExamplePPM();
  }


  /**
   * Stuff for the stuff.
   * @return the string off file.
   */
  public StringBuilder setUpExamplePPM() {
    StringBuilder ppmFile = new StringBuilder();

    for (int i = 0; i < 100; i++) {

      for (int c = 0; c < 100; c++) {

        ppmFile.append(100 + " " + 100 + " " + 100 + " " + 255 + " ");
      }
      ppmFile.append("\r");
    }
    return ppmFile;
  }


  /**
   * String of tfile the are apples.
   * @param loadProject : yes.
   * @return the after math of this.
   */
  public StringBuilder getTako(boolean loadProject) {
    Path currentRelativePath = Path.of("").toAbsolutePath();
    String initalizedPath = currentRelativePath + "\\groupwork\\src\\";

    if (!loadProject) {
      Scanner sc;
      try {
        String filePath = (initalizedPath + "groupwork/image/a4Image.ppm").replace("/", "\\");
        sc = new Scanner(new FileInputStream(filePath));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found");
      }

      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string.
      // This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }

      return builder;
    } else {
      Scanner sc;

      try {
        String filePath = (initalizedPath + "projects/p1FORTESTS.collage").replace("/", "\\");
        sc = new Scanner(new FileInputStream(filePath));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found");
      }

      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string.
      // This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }
      return builder;
    }
  }


  @Test
  public void testNewProjectAndConstructor() {
    model = new ImageModelPPM();
    model.startProject("C1", 500, 500);
    assertEquals(this.model.getWidth(), 500);
    assertEquals(this.model.getHeight(), 500);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testStartProject() {
    model = new ImageModelPPM();
    model.startProject(null, 500, 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartProject1() {
    model = new ImageModelPPM();
    model.startProject("L1", 0, 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartProject2() {
    model = new ImageModelPPM();
    model.startProject("L1", 500, 0);
  }


  @Test(expected = IllegalStateException.class)
  public void testStartedProjectAlready() {
    model = new ImageModelPPM();
    model.startProject("C1", 500, 500);
    model.startProject("C2", 500, 500);

  }


  // Tests for add-layer
  @Test(expected = IllegalStateException.class)
  public void testAddLayerIllState() {
    model = new ImageModelPPM();
    model.addLayer("c1");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testAddingDuplicateLayer() {
    model = new ImageModelPPM();
    model.startProject("P1", 500, 500);
    model.addLayer("L1");
    model.addLayer("L1");
  }


  @Test
  public void testAddLayerGetPixels() {
    model = new ImageModelPPM();
    model.startProject("P1", 500, 500);
    model.addLayer("L1");
    RGBPixel p = new RGBPixel(0, 0, 0, 0);

    assertEquals(p.formatPixel(true), (
            new RGBPixel(0, 0, 0, 0)).formatPixel(true));
  }


  // Tests for save-project

  @Test(expected = IllegalStateException.class)
  public void testSaveProjectIllState() {
    model = new ImageModelPPM();
    model.saveProject();
  }

  @Test
  public void testSaveProject() {
    setUp();
    model.saveProject();
    Pixel p = new RGBPixel(0, 0, 0, 0);

    assertEquals(p.formatPixel(true), (
            new RGBPixel(0, 0, 0, 0)).formatPixel(true));
  }


  @Test(expected = IllegalStateException.class)
  public void testSaveProjectDuplicatePath() {
    model = new ImageModelPPM();
    model.startProject("P1", 500, 500);
    model.saveProject();

    model.startProject("P1", 800, 600);
    model.saveProject();
  }

  @Test
  public void saveProject() {
    model = new ImageModelPPM();
    model.startProject("P7", 500, 500);
    model.saveProject();
    Pixel p = new RGBPixel(0, 0, 0, 0);

    assertEquals(p.formatPixel(true), (
            new RGBPixel(0, 0, 0, 0)).formatPixel(true));
  }


  // Tests for save-image
  @Test(expected = IllegalStateException.class)
  public void testSaveImageIllState() {
    model = new ImageModelPPM();
    model.saveImage();
  }



  // Test for load-project

  @Test
  public void testLoadProject() {
    model = new ImageModelPPM();
    model.loadProject(getTako(true).toString());
    Pixel p = new RGBPixel(0, 0, 0, 0);

    assertEquals(p.formatPixel(true), (
            new RGBPixel(0, 0, 0, 0)).formatPixel(true));
  }


  @Test(expected = IllegalStateException.class)
  public void testLoadProjectAfterStartProject() {
    model = new ImageModelPPM();
    model.startProject("P", 500, 500);
    model.loadProject(getTako(true).toString());
  }


  // Tests for addImageToLayer

  @Test(expected = IllegalStateException.class)
  public void testAddImageToLayerIllState() {
    model = new ImageModelPPM();
    model.addImageToLayer("L1", getTako(false).toString(), 0, 0, ".ppm");
  }


  @Test
  public void testAddImageToLayer() {
    setUp();
    model.addLayer("L1");
    assertEquals(model.getPixelAtCoord(0, 12).formatPixel(true), (
            new RGBPixel(0, 0, 0, 0)).formatPixel(true));
    model.addImageToLayer("L1", getTako(false).toString(),
            0, 0, ".ppm");

    assertEquals(new RGBPixel(193, 25, 25, 255).formatPixel(true),
            (model.getPixelAtCoord(0, 12)).formatPixel(true));
  }


  @Test
  public void testAddImageWithOffset() {
    setUp();
    model.addLayer("L1");
    assertEquals(model.getPixelAtCoord(50, 50).formatPixel(true), (
            new RGBPixel(0, 0, 0, 0)).formatPixel(true));
    model.addImageToLayer("L1", getTako(false).toString(), 0, 0, ".ppm");

    RGBPixel p = new RGBPixel(173, 179, 151);

    assertEquals(new RGBPixel(201, 199, 195, 255).formatPixel(true),
            (model.getPixelAtCoord(50, 50)).formatPixel(true));
  }


  // Tests for makeWhiteBackground


  @Test(expected = IllegalStateException.class)
  public void testAddWhiteBackgroundIllState() {
    model = new ImageModelPPM();
    model.makeWhiteBackground(2, 20);
  }


  @Test
  public void testMakeWhiteBackground() {
    model = new ImageModelPPM();
    model.startProject("P1", 500, 500);
    model.addLayer("L1");
    Pixel p = model.getPixelAtCoord(0, 0);
    RGBPixel white = new RGBPixel(0, 0, 0, 0);

    assertEquals(white.formatPixel(true), p.formatPixel(true));
  }


  // Tests for onlyOneColor
  @Test(expected = IllegalStateException.class)
  public void testOnlyOneColorIllState() {
    model = new ImageModelPPM();
    model.onlyRed("L1");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testOnlyOneColorBadInput() {
    model = new ImageModelPPM();
    model.startProject("P", 500, 500);
    model.addLayer("L1");

    model.onlyRed("L2");

  }

  @Test
  public void testRedComponentThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(), 0, 0, ".ppm");
    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.onlyRed("L1");

    assertEquals(new RGBPixel(193, 0, 0).formatPixel(true),
            (model.getPixelAtCoord(0, 0).formatPixel(true)));

    model.removeFilter("L1");
    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
  }


  @Test
  public void testBlueComponentThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(), 0, 0, ".ppm");
    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.onlyBlue("L1");

    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(0, 0, 25, 255)).formatPixel(true));

    model.removeFilter("L1");

    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
  }


  @Test
  public void testGreenComponentThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(), 0, 0, ".ppm");
    assertEquals(new RGBPixel(193, 25, 25, 255).formatPixel(true),
            (model.getPixelAtCoord(0, 0)).formatPixel(true));
    model.onlyGreen("L1");

    assertEquals(new RGBPixel(0, 25, 0, 255).formatPixel(true),
            (model.getPixelAtCoord(0, 0)).formatPixel(true));

    model.removeFilter("L1");
    assertEquals(new RGBPixel(193, 25, 25, 255).formatPixel(true),
            (model.getPixelAtCoord(0, 0)).formatPixel(true));
  }


  // Tests for brighten

  @Test(expected = IllegalStateException.class)
  public void testBrightenIllState() {
    model = new ImageModelPPM();
    model.brightenValue("L1");
  }

  @Test
  public void testBrightenValueThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1",getTako(false).toString() , 0, 0, ".ppm");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.brightenValue("L1");

    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(255, 218, 218, 255)).formatPixel(true));

    model.removeFilter("L1");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(255, 218, 218, 255)).formatPixel(true));
  }


  @Test
  public void testBrightenValueSmallerIntPixels() {
    setUp();
    model.addLayer("L1");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(0, 0, 0, 0)).formatPixel(true));
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");

    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.brightenValue("L1");

    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(255, 218, 218, 255)).formatPixel(true));

    model.removeFilter("L1");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(255, 218, 218, 255)).formatPixel(true));


  }


  @Test
  public void testBrightenIntensityThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");
    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.brightenIntensity("L1");
    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true),
            (new RGBPixel(255, 146, 146, 255).formatPixel(true)));

    model.removeFilter("L1");
    assertEquals(new RGBPixel(255, 146, 146, 255).formatPixel(true),
            (model.getPixelAtCoord(0, 0)).formatPixel(true));
  }


  @Test
  public void testBrightenLuma() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");

    model.brightenLuma("L1");

    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(253, 85, 85, 255)).formatPixel(true));

    model.removeFilter("L1");
    assertEquals(new RGBPixel(253, 85, 85, 255).formatPixel(true),
            (model.getPixelAtCoord(0, 0)).formatPixel(true));
  }


  // Tests for darken
  @Test(expected = IllegalStateException.class)
  public void testDarkenIllState() {
    model = new ImageModelPPM();
    model.darkenValue("L1");
  }


  @Test
  public void testDarkenValueThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.darkenValue("L1");

    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(0, 0, 0, 255)).formatPixel(true));

    model.removeFilter("L1");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(0, 0, 0, 255)).formatPixel(true));
  }


  @Test
  public void testDarkenIntensityThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");
    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.removeFilter("L1");
    assertEquals(new RGBPixel(193, 25, 25, 255).formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
    model.darkenIntensity("L1");

    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true),
            (new RGBPixel(72, 0, 0, 255)).formatPixel(true));

    model.removeFilter("L1");
    assertEquals(new RGBPixel(72, 0, 0, 255).formatPixel(true),
            model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  @Test
  public void testDarkenLumaThenRemoveFilter() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(193, 25, 25, 255)).formatPixel(true));
    model.brightenLuma("L1");

    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(253, 85, 85, 255)).formatPixel(true));

    model.removeFilter("L1");
    assertEquals(model.getPixelAtCoord(0, 423).formatPixel(true), (
            new RGBPixel(253, 85, 85, 255)).formatPixel(true));

  }


  // tests for remove filter
  @Test(expected = IllegalStateException.class)
  public void testRemoveFilterIllState() {
    model = new ImageModelPPM();
    model.removeFilter("L1");
  }


  // Miscellaneous Filter Tests


  @Test
  public void testChangingFilters() {
    model = new ImageModelPPM();
    model.startProject("P1", 2, 2);
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");

    model.onlyGreen("L1");
    model.onlyBlue("L1");

    assertEquals(new RGBPixel(0, 0, 25).formatPixel(true),
            (model.getPixelAtCoord(0, 0)).formatPixel(true));

    model.brightenIntensity("L1");
    assertEquals(new RGBPixel(255, 146, 146, 255).formatPixel(true),
            (model.getPixelAtCoord(0, 0)).formatPixel(true));
  }


  // tests for get/set width/height

  @Test
  public void getHeightAndGetWidth() {
    model = new ImageModelPPM();
    model.startProject("P1", 500, 600);

    assertEquals(500, model.getWidth());
    assertEquals(600, model.getHeight());
  }

  @Test
  public void setHeightAndSetWidth() {
    model = new ImageModelPPM();
    model.startProject("P1", 500, 600);

    assertEquals(500, model.getWidth());
    assertEquals(600, model.getHeight());

    model.setHeight(40);
    model.setWidth(402);
    assertEquals(402, model.getWidth());
    assertEquals(40, model.getHeight());
  }


  // Tests for new Brighten

  @Test
  public void testNewBrightenOverBlackBackground() {
    setUp();

    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");

    model.brightenScreen("L1");
    RGBPixel p = new RGBPixel(193, 25, 25, 255);

    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true),
            p.formatPixel(true));
  }


  @Test
  public void testNewBrightenOverWhite() {
    setUp();

    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");
    model.brightenValue("L1");

    model.addLayer("L2");
    model.addImageToLayer("L2", getTako(false).toString(),0 ,0 , ".ppm");

    model.brightenScreen("L2");
    RGBPixel d = new RGBPixel(252, 236, 236, 255);

    assertEquals(model.getPixelAtCoord(0, 0).
            formatPixel(true), d.formatPixel(true));
  }


  // newDarken() tests

  @Test
  public void testNewDarkenOverWhite() {
    setUp();

    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");
    model.brightenValue("L1");

    model.addLayer("L2");
    model.addImageToLayer("L2", getTako(false).toString(),0 ,0 , ".ppm");

    model.darkenMultiply("L2");
    RGBPixel d = new RGBPixel(178, 23, 23, 255);

    assertEquals(model.getPixelAtCoord(0, 0).
            formatPixel(true), d.formatPixel(true));
  }


  @Test
  public void testNewDarkenOverBlack() {
    setUp();

    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");

    model.darkenMultiply("L1");
    RGBPixel p = new RGBPixel(193, 25, 25, 255);

    assertEquals(p.formatPixel(true), model.getPixelAtCoord(0, 0).formatPixel(true));
  }


  // Difference() tests


  @Test(expected = IllegalStateException.class)
  public void testDifferenceIllState() {
    model = new ImageModelPPM();
    model.difference("L1");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testDifferenceIllArg() {
    setUp();

    model.difference("L1");
  }


  @Test
  public void testDifference() {
    setUp();

    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");


    RGBPixel p = new RGBPixel(193, 25, 25, 255);

    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true),
            p.formatPixel(true));

    model.difference("L1");

    assertEquals(model.getPixelAtCoord(0, 0).formatPixel(true),
            p.formatPixel(true));
  }


  @Test
  public void testDifferenceWithWhiteBackground() {
    setUp();
    model.addLayer("L1");
    model.addImageToLayer("L1", getTako(false).toString(),0 ,0 , ".ppm");
    model.brightenValue("L1");

    model.addLayer("L2");
    model.addImageToLayer("L2", getTako(false).toString(),0 ,0 , ".ppm");

    model.difference("L2");
    RGBPixel p = new RGBPixel(62, 193, 193, 255);

    assertEquals(p.formatPixel(true), model.getPixelAtCoord(0, 0).formatPixel(true));
  }



}
