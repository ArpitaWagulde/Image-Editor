package model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * This class belongs to the model for the ImageProcessingGRIMEModel. This is a child class of
 * ImageProcessingMIMEModelImp. It will handle all the functionalities a model should in MVC model
 * like implementing the functionalities, maintaining session memory and get image data required for
 * the graphical representation. It uses Image object to represent an Image.
 */

public class ImageProcessingGRIMEModelImp extends ImageProcessingMIMEModelImp implements
    ImageProcessingGRIMEModel {

  @Override
  public BufferedImage preview(String sourceImageName, String newImageName, Integer percentage) {
    Image image = super.preview(getSourceImage(sourceImageName), getSourceImage(newImageName),
        percentage);
    return getBufferedImage(image);
  }

  private BufferedImage getBufferedImage(Image image) {
    List<List<Integer>> redData = image.getImageComponent(Component.RED);
    List<List<Integer>> greenData = image.getImageComponent(Component.GREEN);
    List<List<Integer>> blueData = image.getImageComponent(Component.BLUE);
    int width = redData.get(0).size();
    int height = redData.size();
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    img.setRGB(0, 0, 0);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rValue = redData.get(i).get(j);
        int gValue = greenData.get(i).get(j);
        int bValue = blueData.get(i).get(j);
        int rgbValue = (rValue << 16) | (gValue << 8) | bValue;
        img.setRGB(j, i, 0xFFFFFF & rgbValue);
      }
    }
    return img;
  }

  @Override
  public BufferedImage getImage(String imageName) {
    Image image = getSourceImage(imageName);
    return getBufferedImage(image);
  }

  @Override
  public BufferedImage getHistogram(String imageName) {
    Image image = getSourceImage(imageName).buildHistogram();
    return getBufferedImage(image);
  }
}
