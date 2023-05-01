import org.junit.Test;

import model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;


/**
 * Testing class for pixels and layers.
 */
public class PixelAndLayerTest {


  @Test
  public void testPixelFormat() {
    RGBPixel p = new RGBPixel(0, 0, 0);
    assertEquals("0 0 0 255", p.formatPixel(true));
  }


  @Test
  public void difference() {
    RGBPixel p1 = new RGBPixel(173, 179, 151, 255);
    RGBPixel p2 = new RGBPixel(0, 0, 0, 0);

    assertEquals(p1.formatPixel(true), p1.differencePixel(p2).formatPixel(true));
  }


  @Test
  public void testNewBrightenpixel() {
    RGBPixel p = new RGBPixel(0, 0, 0, 255);
    RGBPixel b = new RGBPixel(173, 178, 255, 255);

    assertEquals(b.brightenScreenPixel(p).formatPixel(true), b.formatPixel(true));
  }


  @Test
  public void testNewDarkenPixel() {
    RGBPixel p = new RGBPixel(0, 0, 0, 255);
    RGBPixel b = new RGBPixel(173, 179, 151, 255);
    assertEquals(b.darkenMultiplyPixel(p).formatPixel(true), p.formatPixel(true));
  }


  @Test
  public void testNewDarkenPixelLight() {
    RGBPixel a = new RGBPixel(255, 255, 255, 255);
    RGBPixel b = new RGBPixel(173, 179, 150, 255);
    RGBPixel c = new RGBPixel(172, 179, 149, 255);
    assertEquals(b.darkenMultiplyPixel(a).formatPixel(true), c.formatPixel(true));
  }

}



