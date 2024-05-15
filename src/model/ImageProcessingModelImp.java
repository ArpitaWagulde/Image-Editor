package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class belongs to the model for the ImageProcessing. It will handle all the functionalities a
 * model should in MVC model like implementing the functionalities and maintaining session memory.
 * It uses Image object to represent an Image.
 */

public class ImageProcessingModelImp implements ImageProcessingModel {

  private static final float[][] blurFilter =
      {{1 / 16f, 1 / 8f, 1 / 16f},
          {1 / 8f, 1 / 4f, 1 / 8f},
          {1 / 16f, 1 / 8f, 1 / 16f}};

  private static final float[][] sharpeningFilter =
      {{-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f},
          {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
          {-1 / 8f, 1 / 4f, 1, 1 / 4f, -1 / 8f},
          {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
          {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}};

  private static final float[][] lumaMatrix =
      {{0.2126f, 0.7152f, 0.0722f},
          {0.2126f, 0.7152f, 0.0722f},
          {0.2126f, 0.7152f, 0.0722f}};
  private static final float[][] sepiaFilter =
      {{0.393f, 0.769f, 0.189f},
          {0.349f, 0.686f, 0.168f},
          {0.272f, 0.534f, 0.131f}};

  Map<String, Image> imageProcessingSession;

  /**
   * This method constructs ImageProcessingModelImp by initializing the session memory of type Map.
   */
  public ImageProcessingModelImp() {
    imageProcessingSession = new LinkedHashMap<>();
  }

  Image getSourceImage(String srcImageName) throws IllegalArgumentException {
    Image sourceImage = imageProcessingSession.getOrDefault(srcImageName, null);
    if (sourceImage == null) {
      throw new IllegalArgumentException("Source Image '" + srcImageName + "' not found");
    }
    return sourceImage;
  }

  @Override
  public void loadImage(Map<Component, List<List<Integer>>> imageData, String imageName) {
    imageProcessingSession.put(imageName, new Image(imageData));
  }

  @Override
  public Map<Component, List<List<Integer>>> saveImage(String imageName) throws Exception {
    Image image;
    try {
      image = getSourceImage(imageName);
      return image.getDeepCopyOfImage();
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  @Override
  public void visualizeComponent(String sourceImageName, String destinationImageName,
      Component comp) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).visualizeComponent(comp));
  }

  @Override
  public void visualizeValue(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonVisualize((a, b) -> a > b ? a : b, a -> a));
  }

  @Override
  public void visualizeIntensity(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonVisualize(Integer::sum, a -> "avg"));

  }

  @Override
  public void visualizeLuma(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonFunctionToUseMatMul(lumaMatrix, a -> 0));
  }

  @Override
  public void flipHorizontally(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonFlip((a, b) -> a, (a, b) -> b - a - 1));
  }

  @Override
  public void flipVertically(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonFlip((a, b) -> b - a - 1, (a, b) -> a));
  }

  @Override
  public void brighten(String sourceImageName, String destinationImageName, float intensity) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).brighten(intensity));
  }

  @Override
  public void split(String sourceImageName, Map<Component, String> destinationImageNames) {
    for (Map.Entry<Component, String> mapElement : destinationImageNames.entrySet()) {
      visualizeComponent(sourceImageName, mapElement.getValue(), mapElement.getKey());
    }
  }

  @Override
  public void combine(String destinationImageName,
      Map<Component, String> sourceSplitImageNames) {
    Map<Component, List<List<Integer>>> newImageData = new LinkedHashMap<>();

    for (Map.Entry<Component, String> mapElement : sourceSplitImageNames.entrySet()) {
      newImageData.put(mapElement.getKey(),
          getSourceImage(mapElement.getValue()).getImageComponent(mapElement.getKey()));
    }

    imageProcessingSession.put(destinationImageName, new Image(newImageData));
  }

  @Override
  public void blur(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonFunctionToUseConvolute(blurFilter));
  }

  @Override
  public void sharpen(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonFunctionToUseConvolute(sharpeningFilter));
  }

  @Override
  public void convertToSepia(String sourceImageName, String destinationImageName) {
    imageProcessingSession.put(destinationImageName,
        getSourceImage(sourceImageName).commonFunctionToUseMatMul(sepiaFilter, a -> a + 1));
  }
}
