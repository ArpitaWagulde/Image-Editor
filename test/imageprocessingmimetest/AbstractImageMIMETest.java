package imageprocessingmimetest;

import imageprocessingtest.AbstractImageTest;
import java.io.IOException;
import org.junit.Test;

/**
 * This class represents an abstract class that acts as a fixed structure for the tests that should
 * be run for each image type on More Image Manipulation and Enhancements model.
 */

public abstract class AbstractImageMIMETest extends AbstractImageTest {

  @Test
  public abstract void testNegativeCompressPercent() throws IOException;

  @Test
  public abstract void testInvalidCompressPercent() throws IOException;

  @Test
  public abstract void testCompress0() throws Exception;

  @Test
  public abstract void testCompress100() throws Exception;

  @Test
  public abstract void testCompress20() throws Exception;

  @Test
  public abstract void testCompress50() throws Exception;

  @Test
  public abstract void testCompress90() throws Exception;

  @Test
  public abstract void testHistogram() throws Exception;

  @Test
  public abstract void testColorCorrect() throws Exception;

  @Test
  public abstract void testColorCorrectPreview() throws Exception;

  @Test
  public abstract void testLevelsAdjust() throws Exception;

  @Test
  public abstract void testInvalidValuesLevelsAdjust() throws Exception;

  @Test
  public abstract void testOutOfRangeValuesLevelsAdjust() throws Exception;

  @Test
  public abstract void testLevelsAdjustPreview() throws Exception;

  @Test
  public abstract void testColorCorrectHistogram() throws Exception;

  @Test
  public abstract void testInvalidPreview() throws Exception;

  @Test
  public abstract void testPreview0() throws Exception;

  @Test
  public abstract void testPreview100() throws Exception;

  @Test
  public abstract void testBlurPreview() throws Exception;

  @Test
  public abstract void testSharpenPreview() throws Exception;

  @Test
  public abstract void testSepiaPreview() throws Exception;

  @Test
  public abstract void testVisualizeValuePreview() throws Exception;

  @Test
  public abstract void testVisualizeIntensityPreview() throws Exception;

  @Test
  public abstract void testVisualizeLumaPreview() throws Exception;
}
