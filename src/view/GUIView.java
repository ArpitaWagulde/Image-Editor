package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;
import java.io.File;

import controller.ImageProcessingGRIMEController;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class represents a Graphical User Interface for an ImageProcessing application. This class
 * uses Java Swing to create and display the UI.
 */

public class GUIView extends JFrame implements ViewInterface {

  private final JLabel image;
  private final JLabel histogram;

  private boolean isPreviewOn;
  private boolean isSaved;
  private boolean isOpInProgress;

  private final JSlider previewPercent;
  private final JPanel previewPanel;
  private final JLabel previewValue;
  private final JButton confirmOpButton;
  private final JButton cancelOpButton;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton visualizeRedButton;
  private final JButton visualizeGreenButton;
  private final JButton visualizeBlueButton;
  private final JButton flipVerticallyButton;
  private final JButton flipHorizontallyButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;
  private final JButton compressButton;
  private final JButton colorCorrectButton;
  private final JButton levelsAdjustButton;

  private final JPanel levelAdjParams;
  private final JTextField bVal;
  private final JTextField mVal;
  private final JTextField wVal;
  private final JButton levelAdjSubmit;

  private String compressionRatio;
  private final JPanel compressParam;
  private final JLabel compressValue;
  private final JButton compressSubmit;

  /**
   * A method to construct the GUIView. This will essentially create the entire user interface with
   * all the required components.
   */
  public GUIView() {
    this.setTitle("GRIME-Flow");
    this.setSize(1366, 768);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    isPreviewOn = false;
    isSaved = true;
    isOpInProgress = false;

    JPanel mainPanel = new JPanel();
    mainPanel.setPreferredSize(new Dimension(1366, 768));
    mainPanel.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3; // Spans 2 columns
    gbc.gridheight = 3; // Spans 2 rows
    gbc.weightx = 2;
    gbc.weighty = 2;
    gbc.fill = GridBagConstraints.BOTH;

    // Top Left Panel
    image = new JLabel();
    JScrollPane imagePanel = new JScrollPane(image);
    imagePanel.setPreferredSize(new Dimension(1024, 768));
    imagePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imagePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imagePanel.setBorder(BorderFactory.createTitledBorder("Working Image"));

    // Top Right Panel
    JPanel operationsPanel = new JPanel();
    operationsPanel.setPreferredSize(new Dimension(300, 600));
    operationsPanel.setLayout(new GridLayout(3, 1));
    operationsPanel.setBorder(BorderFactory.createTitledBorder("Operations"));

    // Create Operation Buttons
    JPanel operationButtons = new JPanel();
    operationButtons.setLayout(new GridLayout(6, 2));
    operationButtons.setPreferredSize(new Dimension(300, 400));
    blurButton = new JButton("Blur Image");
    //blurButton.addActionListener(new BlurButtonListener());
    sharpenButton = new JButton("Sharpen Image");
    visualizeRedButton = new JButton("Red Component");
    visualizeGreenButton = new JButton("Green Component");
    visualizeBlueButton = new JButton("Blue Component");
    flipVerticallyButton = new JButton("Flip Vertically");
    flipHorizontallyButton = new JButton("Flip Horizontally");
    greyscaleButton = new JButton("GreyScale");
    sepiaButton = new JButton("Sepia");
    compressButton = new JButton("Compress Image");

    colorCorrectButton = new JButton("Color Correct");
    levelsAdjustButton = new JButton("Levels Adjust");

    levelAdjParams = new JPanel();
    levelAdjParams.setBorder(BorderFactory.createTitledBorder("Level Adjust Parameters"));
    JLabel black = new JLabel("Black Value: ");
    JLabel mid = new JLabel("Mid Value: ");
    JLabel white = new JLabel("White Value: ");
    bVal = new JTextField(5);
    mVal = new JTextField(5);
    wVal = new JTextField(5);
    levelAdjSubmit = new JButton("Adjust Levels");

    levelAdjParams.add(black);
    levelAdjParams.add(bVal);
    levelAdjParams.add(mid);
    levelAdjParams.add(mVal);
    levelAdjParams.add(white);
    levelAdjParams.add(wVal);
    levelAdjParams.add(levelAdjSubmit);
    operationsPanel.add(levelAdjParams);
    levelAdjParams.setVisible(false);

    compressParam = new JPanel();
    compressParam.setBorder(BorderFactory.createTitledBorder("Compression Parameters"));
    JSlider compressPercent = new JSlider(0, 100);
    compressPercent.setMajorTickSpacing(20);
    compressPercent.setMinorTickSpacing(5);
    compressPercent.setPaintTicks(true);
    compressPercent.setPaintLabels(true);
    compressionRatio = "50";

    compressPercent.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        int value = source.getValue();
        compressionRatio = value + "";
        compressValue.setText("Compression Percentage: " + value + "%");
      }
    });

    compressValue = new JLabel("Compression Percentage: 50%");
    compressSubmit = new JButton("Compress Image");

    compressParam.add(compressPercent);
    compressParam.add(compressValue);
    compressParam.add(compressSubmit);
    compressParam.setVisible(false);

    // Add Operation Buttons
    operationButtons.add(blurButton);
    operationButtons.add(sharpenButton);
    operationButtons.add(visualizeRedButton);
    operationButtons.add(visualizeGreenButton);
    operationButtons.add(visualizeBlueButton);
    operationButtons.add(flipVerticallyButton);
    operationButtons.add(flipHorizontallyButton);
    operationButtons.add(greyscaleButton);
    operationButtons.add(sepiaButton);
    operationButtons.add(compressButton);
    operationButtons.add(colorCorrectButton);
    operationButtons.add(levelsAdjustButton);

    operationsPanel.add(operationButtons);
    operationsPanel.add(levelAdjParams);
    operationsPanel.add(compressParam);

    // Bottom Left Panel
    JPanel ioPanel = new JPanel();
    ioPanel.setLayout(new GridLayout(3, 1));
    ioPanel.setPreferredSize(new Dimension(768, 150));
    ioPanel.setBorder(BorderFactory.createTitledBorder("IO Options"));

    // Create Buttons and Panels
    JPanel loadSavePanel = new JPanel(new FlowLayout());
    loadButton = new JButton("Load Image");
    loadButton.setPreferredSize(new Dimension(200, 30));
    saveButton = new JButton("Save Image");
    saveButton.setPreferredSize(new Dimension(200, 30));
    loadSavePanel.add(loadButton);
    loadSavePanel.add(saveButton);
    confirmOpButton = new JButton("Confirm Operation");
    confirmOpButton.setPreferredSize(new Dimension(200, 30));
    cancelOpButton = new JButton("Cancel Operation");
    cancelOpButton.setPreferredSize(new Dimension(200, 30));
    previewPanel = new JPanel();
    previewPanel.setLayout(new GridLayout(1, 4));
    previewPanel.setPreferredSize(new Dimension(768, 80));
    previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));
    previewPercent = new JSlider(0, 100);
    previewPercent.setMajorTickSpacing(20);
    previewPercent.setMinorTickSpacing(5);
    previewPercent.setPaintTicks(true);
    previewPercent.setPaintLabels(true);

    previewValue = new JLabel("Preview Percentage: 50%");
    //previewValue.setPreferredSize(new Dimension(50, 10));

    previewPanel.add(previewPercent);
    previewPanel.add(previewValue);
    previewPanel.add(confirmOpButton);
    previewPanel.add(cancelOpButton);
    previewPanel.setVisible(false);

    // Add Buttons and Panels
    ioPanel.add(loadSavePanel);
    ioPanel.add(previewPanel);

    JPanel histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));

    histogram = new JLabel();
    histogramPanel.add(histogram);
    histogramPanel.setPreferredSize(new Dimension(356, 356));

    mainPanel.add(imagePanel, gbc);

    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 2;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;

    mainPanel.add(operationsPanel, gbc);

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 3;
    gbc.gridheight = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;

    mainPanel.add(ioPanel, gbc);

    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 2;
    gbc.weightx = 1;
    gbc.weighty = 2;
    gbc.fill = GridBagConstraints.BOTH;

    mainPanel.add(histogramPanel, gbc);

    JScrollPane mainPane = new JScrollPane(mainPanel);
    mainPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    mainPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Disable All Buttons before Load.

    saveButton.setEnabled(false);
    blurButton.setEnabled(false);
    sharpenButton.setEnabled(false);
    sepiaButton.setEnabled(false);
    compressButton.setEnabled(false);
    visualizeRedButton.setEnabled(false);
    visualizeBlueButton.setEnabled(false);
    visualizeGreenButton.setEnabled(false);
    flipHorizontallyButton.setEnabled(false);
    flipVerticallyButton.setEnabled(false);
    greyscaleButton.setEnabled(false);
    colorCorrectButton.setEnabled(false);
    levelsAdjustButton.setEnabled(false);

    this.add(mainPane);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void refreshImage(BufferedImage image, BufferedImage histogram) {
    ImageIcon img = new ImageIcon(image);
    GUIView.this.image.setIcon(img);
    GUIView.this.image.revalidate();
    GUIView.this.image.repaint();

    ImageIcon hist = new ImageIcon(histogram.getScaledInstance(256, -1, Image.SCALE_SMOOTH));
    GUIView.this.histogram.setIcon(hist);
    GUIView.this.histogram.revalidate();
    GUIView.this.histogram.repaint();
  }

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error String error message to transmit to the view
   */
  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }


  @Override
  public void addCommands(ImageProcessingGRIMEController controller) {
    loadButton.addActionListener(new LoadButtonListener(controller));
    saveButton.addActionListener(new SaveButtonListener(controller));
    blurButton.addActionListener(new BlurButtonListener(controller));
    sharpenButton.addActionListener(new SharpenButtonListener(controller));
    sepiaButton.addActionListener(new SepiaButtonListener(controller));
    previewPercent.addChangeListener(new PreviewChange(controller));
    confirmOpButton.addActionListener(new ConfirmOperationListener(controller));
    cancelOpButton.addActionListener(new CancelOperationListener(controller));
    visualizeRedButton.addActionListener(new RedComponentListener(controller));
    visualizeGreenButton.addActionListener(new GreenComponentListener(controller));
    visualizeBlueButton.addActionListener(new BlueComponentListener(controller));
    greyscaleButton.addActionListener(new GreyScaleListener(controller));
    flipHorizontallyButton.addActionListener(new FlipHorizontalListener(controller));
    flipVerticallyButton.addActionListener(new FlipVerticalListener(controller));
    compressButton.addActionListener(new CompressStartListener(controller));
    compressSubmit.addActionListener(new CompressSubmitListener(controller));
    colorCorrectButton.addActionListener(new ColorCorrectListener(controller));
    levelsAdjustButton.addActionListener(new LevelAdjustStartListener(controller));
    levelAdjSubmit.addActionListener(new LevelAdjustListener(controller));
  }

  private class PreviewChange implements ChangeListener {

    ImageProcessingGRIMEController controller;

    PreviewChange(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
      JSlider source = (JSlider) e.getSource();
      int value = source.getValue();
      previewValue.setText("Preview Percentage: " + value + "%");
      controller.preview(value);
    }
  }

  private class LoadButtonListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private LoadButtonListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (isSaved) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files",
            "jpg", "png", "ppm", "jpeg");
        fileChooser.setFileFilter(filter);
        // Set current Working Directory for FileChooser.
        fileChooser.setCurrentDirectory(new File("."));
        int option = fileChooser.showOpenDialog(GUIView.this);
        if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          controller.loadImage(selectedFile.getAbsolutePath());
          // enable All Operations.
          GUIView.this.saveButton.setEnabled(true);
          GUIView.this.blurButton.setEnabled(true);
          GUIView.this.sharpenButton.setEnabled(true);
          GUIView.this.sepiaButton.setEnabled(true);
          GUIView.this.compressButton.setEnabled(true);
          GUIView.this.visualizeRedButton.setEnabled(true);
          GUIView.this.visualizeBlueButton.setEnabled(true);
          GUIView.this.visualizeGreenButton.setEnabled(true);
          GUIView.this.flipHorizontallyButton.setEnabled(true);
          GUIView.this.flipVerticallyButton.setEnabled(true);
          GUIView.this.greyscaleButton.setEnabled(true);
          GUIView.this.colorCorrectButton.setEnabled(true);
          GUIView.this.levelsAdjustButton.setEnabled(true);

          isSaved = false;
        }
      } else {
        int option = JOptionPane.showConfirmDialog(
            GUIView.this,
            "Are you sure you want to load a new image? You will lose all edits on " +
                "the current image. Click 'No' to go back to editing.",
            "Load New Image",
            JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
          isSaved = true;
          this.actionPerformed(e);
        }
      }
    }
  }

  private class SaveButtonListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private SaveButtonListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isOpInProgress) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files",
            "jpg", "png", "ppm", "jpeg");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File("."));
        int option = fileChooser.showSaveDialog(GUIView.this);
        if (option == JFileChooser.APPROVE_OPTION) {
          File newFile = fileChooser.getSelectedFile();
          controller.saveImage(newFile.getAbsolutePath());
        }
      } else {
        int option = JOptionPane.showConfirmDialog(
            GUIView.this,
            "You have unconfirmed changes. Do you want to save? You will lose the " +
                "unconfirmed changes. Click 'No' to go back to editing.",
            "Save Image",
            JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
          controller.cancelOperation();
          isOpInProgress = false;
          this.actionPerformed(e);
        }
      }
    }
  }

  private class BlurButtonListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private BlurButtonListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(50);
        GUIView.this.previewValue.setText("Preview Percentage: 50%");
        GUIView.this.previewPercent.setEnabled(true);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();

        controller.blurImage(50);
        isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class SharpenButtonListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private SharpenButtonListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(50);
        GUIView.this.previewValue.setText("Preview Percentage: 50%");
        GUIView.this.previewPercent.setEnabled(true);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.sharpenImage(50);
        GUIView.this.isPreviewOn = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class CancelOperationListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private CancelOperationListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      this.controller.cancelOperation();
      GUIView.this.previewPanel.setVisible(false);
      GUIView.this.previewPanel.revalidate();
      GUIView.this.previewPanel.repaint();

      GUIView.this.compressParam.setVisible(false);
      GUIView.this.compressParam.revalidate();
      GUIView.this.compressParam.repaint();

      GUIView.this.levelAdjParams.setVisible(false);
      GUIView.this.levelAdjParams.revalidate();
      GUIView.this.levelAdjParams.repaint();

      GUIView.this.isPreviewOn = false;
      isOpInProgress = false;
    }
  }

  private class ConfirmOperationListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private ConfirmOperationListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      this.controller.confirmOperation();
      GUIView.this.previewPanel.setVisible(false);
      GUIView.this.previewPanel.revalidate();
      GUIView.this.previewPanel.repaint();

      GUIView.this.compressParam.setVisible(false);
      GUIView.this.compressParam.revalidate();
      GUIView.this.compressParam.repaint();

      GUIView.this.levelAdjParams.setVisible(false);
      GUIView.this.levelAdjParams.revalidate();
      GUIView.this.levelAdjParams.repaint();

      GUIView.this.isPreviewOn = false;
      isOpInProgress = false;
    }
  }

  private class SepiaButtonListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private SepiaButtonListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(50);
        GUIView.this.previewValue.setText("Preview Percentage: 50%");
        GUIView.this.previewPercent.setEnabled(true);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.convertToSepia(50);
        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class RedComponentListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private RedComponentListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(100);
        GUIView.this.previewValue.setText("Preview Percentage: 100%");
        GUIView.this.previewPercent.setEnabled(false);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.visualizeRed(100);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class GreenComponentListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private GreenComponentListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(100);
        GUIView.this.previewValue.setText("Preview Percentage: 100%");
        GUIView.this.previewPercent.setEnabled(false);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.visualizeGreen(100);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class BlueComponentListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private BlueComponentListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(100);
        GUIView.this.previewValue.setText("Preview Percentage: 100%");
        GUIView.this.previewPercent.setEnabled(false);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.visualizeBlue(100);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class GreyScaleListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private GreyScaleListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(50);
        GUIView.this.previewValue.setText("Preview Percentage: 50%");
        GUIView.this.previewPercent.setEnabled(true);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.convertToGreyscale(50);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class FlipHorizontalListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private FlipHorizontalListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(100);
        GUIView.this.previewValue.setText("Preview Percentage: 100%");
        GUIView.this.previewPercent.setEnabled(false);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.flipImageHorizontally(100);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class FlipVerticalListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private FlipVerticalListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(100);
        GUIView.this.previewValue.setText("Preview Percentage: 100%");
        GUIView.this.previewPercent.setEnabled(false);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.flipImageVertically(100);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class CompressSubmitListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private CompressSubmitListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(50);
        GUIView.this.previewValue.setText("Preview Percentage: 50%");
        GUIView.this.previewPercent.setEnabled(true);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.compressImage(Integer.parseInt(GUIView.this.compressionRatio), 50);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class ColorCorrectListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private ColorCorrectListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(50);
        GUIView.this.previewValue.setText("Preview Percentage: 50%");
        GUIView.this.previewPercent.setEnabled(true);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.colorCorrectImage(50);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class LevelAdjustListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private LevelAdjustListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!isPreviewOn) {
        GUIView.this.previewPercent.setValue(50);
        GUIView.this.previewValue.setText("Preview Percentage: 50%");
        GUIView.this.previewPercent.setEnabled(true);
        GUIView.this.previewPanel.setVisible(true);
        GUIView.this.previewPanel.revalidate();
        GUIView.this.previewPanel.repaint();

        GUIView.this.compressParam.setVisible(false);
        GUIView.this.compressParam.revalidate();
        GUIView.this.compressParam.repaint();

        GUIView.this.levelAdjParams.setVisible(false);
        GUIView.this.levelAdjParams.revalidate();
        GUIView.this.levelAdjParams.repaint();
        controller.levelsAdjustImage(GUIView.this.bVal.getText(), GUIView.this.mVal.getText(),
            GUIView.this.wVal.getText(), 50);

        GUIView.this.isPreviewOn = true;
        isOpInProgress = true;
      } else {
        if (isOpInProgress) {
          controller.cancelOperation();
        }
        GUIView.this.isPreviewOn = false;
        this.actionPerformed(e);
      }
    }
  }

  private class CompressStartListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private CompressStartListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (isOpInProgress) {
        controller.cancelOperation();
      }

      GUIView.this.previewPanel.setVisible(false);
      GUIView.this.previewPanel.revalidate();
      GUIView.this.previewPanel.repaint();

      GUIView.this.compressParam.setVisible(true);
      GUIView.this.compressParam.revalidate();
      GUIView.this.compressParam.repaint();

      GUIView.this.levelAdjParams.setVisible(false);
      GUIView.this.levelAdjParams.revalidate();
      GUIView.this.levelAdjParams.repaint();
    }
  }

  private class LevelAdjustStartListener implements ActionListener {

    ImageProcessingGRIMEController controller;

    private LevelAdjustStartListener(ImageProcessingGRIMEController controller) {
      this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (isOpInProgress) {
        controller.cancelOperation();
      }

      GUIView.this.previewPanel.setVisible(false);
      GUIView.this.previewPanel.revalidate();
      GUIView.this.previewPanel.repaint();

      compressParam.setVisible(false);
      compressParam.revalidate();
      compressParam.repaint();

      levelAdjParams.setVisible(true);
      levelAdjParams.revalidate();
      levelAdjParams.repaint();
    }
  }
}
