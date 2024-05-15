package imageprocessingtest;

import static org.junit.Assert.assertEquals;

import java.util.List;
import model.Component;
import model.Image;
import org.junit.Test;

/**
 * This class represents an abstract class that acts as a fixed structure for the tests that should
 * be run for each image type.
 */

public abstract class AbstractImageTest {

  /**
   * A method to check if two images are equal to each other.
   *
   * @param image1 an Image to check
   * @param image2 an Image to check
   * @return true if both the images are equal, false otherwise
   */
  public static boolean checkIfTwoImagesEqual(Image image1, Image image2) {
    Component[] componentList = {Component.RED, Component.GREEN, Component.BLUE};
    for (Component c : componentList) {
      assertEquals(image1.getWidth(), image2.getWidth());
      assertEquals(image1.getHeight(), image2.getHeight());
      List<List<Integer>> img1 = image1.getImageComponent(c);
      List<List<Integer>> img2 = image2.getImageComponent(c);

      for (int i = 0; i < image1.getHeight(); i++) {
        for (int j = 0; j < image1.getWidth(); j++) {
          if (Math.abs(img1.get(i).get(j) - img2.get(i).get(j)) > 50) {
            System.out.println(img1.get(i).get(j) + " " + img2.get(i).get(j));
            return false;
          }
        }
      }
    }
    return true;
  }

  @Test
  public abstract void testLoadInvalidImagePath() throws Exception;

  @Test
  public abstract void testLoadInvalidImageName() throws Exception;

  @Test
  public abstract void testSaveInvalidImagePath() throws Exception;

  @Test
  public abstract void testOperationOnNonExistingImage() throws Exception;

  @Test
  public abstract void testLoadSave() throws Exception;

  @Test
  public abstract void testVisualizeRed() throws Exception;

  @Test
  public abstract void testVisualizeGreen() throws Exception;

  @Test
  public abstract void testVisualizeBlue() throws Exception;

  @Test
  public abstract void testVisualizeValue() throws Exception;

  @Test
  public abstract void testVisualizeIntensity() throws Exception;

  @Test
  public abstract void testVisualizeLuma() throws Exception;

  @Test
  public abstract void flipHorizontally() throws Exception;

  @Test
  public abstract void flipVertically() throws Exception;

  @Test
  public abstract void testBrighten() throws Exception;

  @Test
  public abstract void testDarken() throws Exception;

  @Test
  public abstract void testSplit() throws Exception;

  @Test
  public abstract void testCombine() throws Exception;

  @Test
  public abstract void testBlur() throws Exception;

  @Test
  public abstract void testSharpen() throws Exception;

  @Test
  public abstract void testConvertToSepia() throws Exception;

  @Test
  public abstract void testOverwriteWithSameType() throws Exception;

  @Test
  public abstract void testOverwriteWithDifferentType() throws Exception;

  @Test
  public abstract void testSaveAsPPM() throws Exception;

  @Test
  public abstract void testSaveAsJPG() throws Exception;

  @Test
  public abstract void testSaveAsPNG() throws Exception;

  //Should give back the same image
  @Test
  public abstract void testVerticalFlipTwice() throws Exception;

  //Should give back the same image
  @Test
  public abstract void testHorizontalFlipTwice() throws Exception;

  @Test
  public abstract void testBlurTwice() throws Exception;

  //Should give back the same image
  @Test
  public abstract void testBrightenDarken() throws Exception;

  @Test
  public abstract void testBlurSharpen() throws Exception;

  @Test
  public abstract void testSharpenBlur() throws Exception;

  @Test
  public abstract void testVerticalHorizontalFlip() throws Exception;

  @Test
  public abstract void testHorizontalVerticalFlip() throws Exception;

  @Test
  public abstract void testConsecutiveOperations() throws Exception;
}
