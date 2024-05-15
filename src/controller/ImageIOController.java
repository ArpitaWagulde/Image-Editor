package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.Component;

/**
 * This class is part of a controller and will handle the input-output operations of the image.
 */
public class ImageIOController {

  private static Map<Component, List<List<Integer>>> readPPM(Scanner sc)
      throws Exception {
    Map<Component, List<List<Integer>>> imageData = new LinkedHashMap<>();
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new Exception("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();
    List<List<Integer>> rChannel = new ArrayList<>();
    List<List<Integer>> gChannel = new ArrayList<>();
    List<List<Integer>> bChannel = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<Integer> rChannelRow = new ArrayList<>();
      List<Integer> gChannelRow = new ArrayList<>();
      List<Integer> bChannelRow = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int rValue = sc.nextInt();
        int gValue = sc.nextInt();
        int bValue = sc.nextInt();
        rChannelRow.add(rValue);
        gChannelRow.add(gValue);
        bChannelRow.add(bValue);
      }
      rChannel.add(rChannelRow);
      gChannel.add(gChannelRow);
      bChannel.add(bChannelRow);
    }
    imageData.put(Component.RED, rChannel);
    imageData.put(Component.GREEN, gChannel);
    imageData.put(Component.BLUE, bChannel);

    return imageData;
  }

  private static Map<Component, List<List<Integer>>> readJPGPNG(
      String filename) {
    Map<Component, List<List<Integer>>> imageData = new LinkedHashMap<>();
    BufferedImage img;
    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    List<List<Integer>> rChannel = new ArrayList<>();
    List<List<Integer>> gChannel = new ArrayList<>();
    List<List<Integer>> bChannel = new ArrayList<>();
    for (int i = 0; i < img.getHeight(); i++) {
      List<Integer> rChannelRow = new ArrayList<>();
      List<Integer> gChannelRow = new ArrayList<>();
      List<Integer> bChannelRow = new ArrayList<>();
      for (int j = 0; j < img.getWidth(); j++) {
        int rgb = img.getRGB(j, i);
        rChannelRow.add((rgb >> 16) & 0xFF);
        gChannelRow.add((rgb >> 8) & 0xFF);
        bChannelRow.add(rgb & 0xFF);
      }
      rChannel.add(rChannelRow);
      gChannel.add(gChannelRow);
      bChannel.add(bChannelRow);
    }
    imageData.put(Component.RED, rChannel);
    imageData.put(Component.GREEN, gChannel);
    imageData.put(Component.BLUE, bChannel);
    return imageData;
  }

  private void savePPM(Map<Component, List<List<Integer>>> image, String destination)
      throws IOException {
    FileWriter outputWriter;
    try {
      File outputImage = new File(destination);
      outputWriter = new FileWriter(outputImage);
      List<List<Integer>> redData = image.get(Component.RED);
      List<List<Integer>> greenData = image.get(Component.GREEN);
      List<List<Integer>> blueData = image.get(Component.BLUE);
      int width = redData.get(0).size();
      int height = redData.size();
      outputWriter.write(String.format("P3\n%d\n%d\n255\n", width, height));
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          outputWriter.write(
              String.format("%d\t%d\t%d\t", redData.get(i).get(j),
                  greenData.get(i).get(j),
                  blueData.get(i).get(j)));
        }
      }
      outputWriter.close();
    } catch (IOException e) {
      throw new IOException("Destination path not found");
    }
  }

  private void saveJPGPNG(Map<Component, List<List<Integer>>> image, String destination)
      throws IOException {
    String format = "jpg";
    if (destination.endsWith(".png")) {
      format = "png";
    }
    if (destination.endsWith(".jpeg")) {
      format = "jpeg";
    }
    File outputImage = new File(destination);
    List<List<Integer>> redData = image.get(Component.RED);
    List<List<Integer>> greenData = image.get(Component.GREEN);
    List<List<Integer>> blueData = image.get(Component.BLUE);
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
    try {
      if (outputImage.createNewFile()) {
        ImageIO.write(img, format, outputImage);
      }
    } catch (FileNotFoundException f) {
      throw new IOException("File Not Found");
    } catch (IOException e) {
      throw new IOException("Destination path not found");
    }
  }

  /**
   * This method will load the image from the given destination.
   *
   * @param filename the filepath to save the image from
   * @return map where each key is a component and the value 2D List matrix represent the pixel data
   *         of the respective component.
   * @throws Exception if there was an issue in loading an image
   */

  public Map<Component, List<List<Integer>>> load(String filename) throws Exception {
    File f = new File(filename);
    if (!f.exists() || !f.isFile()) {
      throw new IllegalArgumentException("Invalid Source.");
    }
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new Exception("File " + filename + " not found!");
    }
    if (filename.endsWith(".ppm")) {
      return readPPM(sc);
    } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(
        ".png")) {
      return readJPGPNG(filename);
    }
    return null;
  }

  /**
   * This method will save this image at the given destination.
   *
   * @param imageData   An Image Data that is to be saved which is a map where each key is a
   *                    component and the value 2D List matrix represent the pixel data of the
   *                    respective component.
   * @param destination the filepath to save the image at
   * @throws Exception if there was an issue in saving an image
   */

  public void save(Map<Component, List<List<Integer>>> imageData, String destination)
      throws Exception {
    if (destination.endsWith(".ppm")) {
      savePPM(imageData, destination);
    } else if (destination.endsWith(".jpg") || destination.endsWith(".jpeg")
        || destination.endsWith(".png")) {
      try {
        saveJPGPNG(imageData, destination);
      } catch (Exception e) {
        throw new Exception(e);
      }
    } else {
      throw new Exception("Not an image file format");
    }
  }
}
