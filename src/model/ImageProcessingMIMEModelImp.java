package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class belongs to the model for the ImageProcessingMIME. This is a child class of
 * ImageProcessingModelImp. It will handle all the functionalities a model should in MVC model like
 * implementing the functionalities and maintaining session memory. It uses Image object to
 * represent an Image.
 */

public class ImageProcessingMIMEModelImp extends ImageProcessingModelImp implements
    ImageProcessingMIMEModel {

  Image preview(Image sourceImage, Image tempImage, Integer percentage) {
    int newSourceWidth = sourceImage.getWidth() * percentage / 100;
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();
    Map<Component, List<List<Integer>>> sourceImageData = sourceImage.getDeepCopyOfImage();
    for (Map.Entry<Component, List<List<Integer>>>
        mapElement : sourceImageData.entrySet()) {
      List<List<Integer>> tempImageData = tempImage.getImageComponent(mapElement.getKey());
      List<List<Integer>> visualizeChannel = new ArrayList<>();
      for (int i = 0; i < sourceImage.getHeight(); i++) {
        List<Integer> visualizeRow = new ArrayList<>();
        for (int j = 0; j < newSourceWidth; j++) {
          visualizeRow.add(tempImageData.get(i).get(j));
        }
        for (int j = newSourceWidth; j < tempImage.getWidth(); j++) {
          visualizeRow.add(mapElement.getValue().get(i).get(j));
        }
        visualizeChannel.add(visualizeRow);
      }
      newImageData.put(mapElement.getKey(), visualizeChannel);
    }
    return new Image(newImageData);
  }

  @Override
  public void compress(String sourceImageName, String destinationImageName,
      Integer compressPercentage) throws IllegalArgumentException {
    if (compressPercentage < 0 || compressPercentage > 100) {
      throw new IllegalArgumentException("Compression percentage must be in range of 0 to 100");
    }
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).compress(compressPercentage));
  }

  @Override
  public void histogram(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).buildHistogram());
  }

  @Override
  public void colorCorrect(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).colorCorrect());
  }

  @Override
  public void colorCorrect(String sourceImageName, String destinationImageName,
      Integer percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    Image sourceImage = getSourceImage(sourceImageName);
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      imageProcessingSession.put(tempImageName, sourceImage.colorCorrect());
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName, preview(sourceImage, tempImage, percentage));
  }

  @Override
  public void levelAdjustment(String sourceImageName, String destinationImageName,
      Integer blackValue, Integer midValue, Integer whiteValue) throws IllegalArgumentException {
    if (blackValue >= midValue || blackValue >= whiteValue || whiteValue <= midValue
        || blackValue < 0 || whiteValue > 255) {
      throw new IllegalArgumentException(
          "Black-value, mid-value and White-value must be in ascending order "
              + "and in range of 0 to 255");
    }
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).levelAdjustment(blackValue, midValue, whiteValue));
  }

  @Override
  public void levelAdjustment(String sourceImageName, String destinationImageName,
      Integer blackValue, Integer midValue, Integer whiteValue, Integer percentage)
      throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    Image sourceImage = getSourceImage(sourceImageName);
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      imageProcessingSession.put(tempImageName,
          sourceImage.levelAdjustment(blackValue, midValue, whiteValue));
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName, preview(sourceImage, tempImage, percentage));
  }

  @Override
  public void blur(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      super.blur(sourceImageName, tempImageName);
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName,
        preview(getSourceImage(sourceImageName), tempImage, percentage));
  }

  @Override
  public void sharpen(String sourceImageName, String destinationImageName, Integer percentage)
      throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      super.sharpen(sourceImageName, tempImageName);
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName,
        preview(getSourceImage(sourceImageName), tempImage, percentage));
  }

  @Override
  public void convertToSepia(String sourceImageName, String destinationImageName,
      Integer percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      super.convertToSepia(sourceImageName, tempImageName);
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName,
        preview(getSourceImage(sourceImageName), tempImage, percentage));
  }

  @Override
  public void visualizeValue(String sourceImageName, String destinationImageName,
      Integer percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      super.visualizeValue(sourceImageName, tempImageName);
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName,
        preview(getSourceImage(sourceImageName), tempImage, percentage));
  }

  @Override
  public void visualizeIntensity(String sourceImageName, String destinationImageName,
      Integer percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      super.visualizeIntensity(sourceImageName, tempImageName);
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName,
        preview(getSourceImage(sourceImageName), tempImage, percentage));
  }

  @Override
  public void visualizeLuma(String sourceImageName, String destinationImageName,
      Integer percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Preview percentage must be in range of 0 to 100");
    }
    String tempImageName = "temp" + destinationImageName;
    Image tempImage;
    try {
      tempImage = getSourceImage(tempImageName);
    } catch (IllegalArgumentException e) {
      super.visualizeLuma(sourceImageName, tempImageName);
      tempImage = getSourceImage(tempImageName);
    }
    imageProcessingSession.put(destinationImageName,
        preview(getSourceImage(sourceImageName), tempImage, percentage));
  }
}
