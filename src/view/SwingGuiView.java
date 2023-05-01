package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.ImageController;


/**
 * Implementation of a graphical interface for a photo editor application. This class
 * has many JPanels, Buttons, and other things stored as fields as handles the user's
 * interaction with those components and sends them to the model.
 */
public class SwingGuiView extends JFrame implements ImageViewInterface, ActionListener {

  // Takes in commands for the purpose of sending them to the controller
  private ImageController commands;
  // Shows what the current filter is.
  private final JLabel filterDisplay;
  // The custom panel on which the images will be shown.
  private JLabel canvas;
  //Panel that holds the text area and some buttons.
  private JPanel textAreaPanel;
  // Handles input in the text area.
  private JTextArea textArea;
  // Displays and handles the scrolling.
  private JScrollPane scrollPane;
  // Displays a panel of buttons.
  private JPanel buttonPanel;
  // Represents a button associated with a command.
  private JButton commandButton;
  // Holds the current layer radioButtons.
  private JRadioButton[] layerButtons;



  private JButton newProject;
  private JButton loadProject;
  private JButton saveProject;
  private JButton addLayer;
  private JButton removeLayer;
  private JButton clearLayer;
  private JButton addImageToLayer;
  private JButton saveImage;


  /**
   * Constructor for a GUI for a photo editor. Initializes main
   * aspects of the visible photo editor.
   */
  public SwingGuiView(ImageController controller) {
    super();
    this.commands = controller;
    this.setTitle("Bobby and Dan's photo editor! :)");
    this.setBackground(Color.WHITE);
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    buildLayerView();
    buildTextArea();
    buildButtons();
    filterDisplay = new JLabel("No Filter Applied");
    buildFilterOptions();
    buildImage();
  }

  public final void setController(ImageController e) {
    this.commands = e;
  }

  /**
   * Builds the text area for inputting commands.
   */
  private void buildTextArea() {
    textAreaPanel = new JPanel();
    textArea = new JTextArea(25, 20);
    scrollPane = new JScrollPane(textArea);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Input batch scripts here:"));
    textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.PAGE_AXIS));
    textArea.setLineWrap(true);
    textAreaPanel.add(scrollPane);
    buildRunButton();
    this.add(textAreaPanel, BorderLayout.WEST);
  }


  /**
   * Builds a button under the text area that runs the batch script given.
   */
  private void buildRunButton() {
    JButton runText = new JButton("Run Text");
    runText.setActionCommand("run");
    runText.addActionListener(this);
    runText.setMargin(new Insets(7, 4, 7, 4));
    textAreaPanel.add(runText, BorderLayout.SOUTH);
  }


  /**
   * Builds the panel for buttons.
   */
  private void buildButtons() {
    buttonPanel = new JPanel();
    BoxLayout boxlayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
    buttonPanel.setLayout(boxlayout);
    buttonPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);
    buildStartProjectButton();
    buildLoadProjectButton();
    buildAddLayerButton();
    buildAddImageToLayerButton();
    buildRemoveLayerButton();
    buildClearLayerButton();
    buildSaveProjectButton();
    buildSaveImageButton();
  }


  /**
   * Builds the button for start project.
   */
  private void buildStartProjectButton() {
    newProject = new JButton("New Project");
    newProject.setActionCommand("new-project");
    newProject.addActionListener(this);
    newProject.setMargin(new Insets(7, 4, 7, 4));
    buttonPanel.add(newProject);
  }


  /**
   * Builds the button for save project.
   */
  private void buildSaveProjectButton() {
    saveProject = new JButton("Save Project");
    saveProject.setActionCommand("save-project");
    saveProject.addActionListener(this);
    saveProject.setMargin(new Insets(7, 4, 7, 4));
    saveProject.setVisible(false);
    buttonPanel.add(saveProject);
  }


  /**
   * Builds the button for load project.
   */
  private void buildLoadProjectButton() {
    loadProject = new JButton("Load Project");
    loadProject.setActionCommand("load-project");
    loadProject.addActionListener(this);
    loadProject.setMargin(new Insets(7, 4, 7, 4));
    buttonPanel.add(loadProject);
  }


  /**
   * Builds the button for add layer.
   */
  private void buildAddLayerButton() {
    addLayer = new JButton("Add Layer");
    addLayer.setActionCommand("add-layer");
    addLayer.addActionListener(this);
    addLayer.setMargin(new Insets(7, 4, 7, 4));
    addLayer.setVisible(false);
    buttonPanel.add(addLayer);
  }

  /**
   * Builds the button for clear layer.
   */
  private void buildClearLayerButton() {
    clearLayer = new JButton("Clear Layer");
    clearLayer.setActionCommand("clear-layer");
    clearLayer.addActionListener(this);
    clearLayer.setMargin(new Insets(7, 4, 7, 4));
    clearLayer.setVisible(false);
    buttonPanel.add(clearLayer);
  }

  /**
   * Builds the button for remove layer.
   */
  private void buildRemoveLayerButton() {
    removeLayer = new JButton("Remove Layer");
    removeLayer.setActionCommand("remove-layer");
    removeLayer.addActionListener(this);
    removeLayer.setMargin(new Insets(7, 4, 7, 4));
    removeLayer.setVisible(false);
    buttonPanel.add(removeLayer);
  }


  /**
   * Builds the button for save image.
   */
  private void buildSaveImageButton() {
    saveImage = new JButton("Save Image");
    saveImage.setActionCommand("save-image");
    saveImage.addActionListener(this);
    saveImage.setMargin(new Insets(7, 4, 7, 4));
    saveImage.setVisible(false);
    buttonPanel.add(saveImage);
  }


  /**
   * Builds the button for add-image to layer.
   */
  private void buildAddImageToLayerButton() {
    addImageToLayer = new JButton("Add Image");
    addImageToLayer.setActionCommand("add-image");
    addImageToLayer.addActionListener(this);
    addImageToLayer.setMargin(new Insets(7, 4, 7, 4));
    addImageToLayer.setVisible(false);
    buttonPanel.add(addImageToLayer);
  }


  // Filter options on right side of GUI:


  /**
   * Builds a radio button list of filter options.
   */
  private void buildFilterOptions() {
    // Handles the filter selection.
    JPanel filterSelection = new JPanel();
    filterSelection.setBorder(BorderFactory.createTitledBorder("Apply Filter:"));
    filterSelection.setLayout(new BoxLayout(filterSelection, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[14];
    ButtonGroup group1 = new ButtonGroup();
    radioButtons[0] = new JRadioButton("only-red");
    radioButtons[1] = new JRadioButton("only-green");
    radioButtons[2] = new JRadioButton("only-blue");
    radioButtons[3] = new JRadioButton("brighten-value");
    radioButtons[4] = new JRadioButton("brighten-intensity");
    radioButtons[5] = new JRadioButton("brighten-luma");
    radioButtons[6] = new JRadioButton("brighten-screen");
    radioButtons[7] = new JRadioButton("darken-value");
    radioButtons[8] = new JRadioButton("darken-intensity");
    radioButtons[9] = new JRadioButton("darken-luma");
    radioButtons[10] = new JRadioButton("darken-multiply");
    radioButtons[11] = new JRadioButton("difference");
    radioButtons[12] = new JRadioButton("remove-filter");
    radioButtons[0].setActionCommand("red-component");
    radioButtons[1].setActionCommand("green-component");
    radioButtons[2].setActionCommand("blue-component");
    radioButtons[3].setActionCommand("brighten-value");
    radioButtons[4].setActionCommand("brighten-intensity");
    radioButtons[5].setActionCommand("brighten-luma");
    radioButtons[6].setActionCommand("brighten-screen");
    radioButtons[7].setActionCommand("darken-value");
    radioButtons[8].setActionCommand("darken-intensity");
    radioButtons[9].setActionCommand("darken-luma");
    radioButtons[10].setActionCommand("darken-multiply");
    radioButtons[11].setActionCommand("difference");
    radioButtons[12].setActionCommand("remove-filter");

    for (int i = 0; i < 13; i++) {
      group1.add(radioButtons[i]);
      radioButtons[i].addActionListener(this);
      filterSelection.add(radioButtons[i]);
    }
    filterSelection.add(filterDisplay);
    this.add(filterSelection, BorderLayout.EAST);
  }


  // Image Panel:


  /**
   * Creates the panel that displays the images.
   */
  private void buildImage() {
    this.canvas = new JLabel();
    // Displays the current project's name
    String projectNameDisplay = "Canvas";
    scrollPane = new JScrollPane(this.canvas);
    scrollPane.setBorder(BorderFactory.createTitledBorder(projectNameDisplay));
    this.add(scrollPane, BorderLayout.CENTER);
  }


  // Layer view on top of GUI:


  /**
   * Handles the display of all the project's current layers. There are a set amount of 20 layers
   * available, and they are all set to empty to begin with. When the user presses add-layer and
   * gives a name, the next empty layer is set to that new layer and can be accessed by pressing
   * its new radio button.
   */
  private void buildLayerView() {
    // Displays all the layers in the project.
    JPanel layerView = new JPanel();
    layerView.setBorder(BorderFactory.createTitledBorder("Current Layers:"));
    BoxLayout boxlayout = new BoxLayout(layerView, BoxLayout.X_AXIS);
    layerView.setLayout(boxlayout);
    this.add(layerView, BorderLayout.NORTH);
    layerButtons = new JRadioButton[20];
    ButtonGroup group1 = new ButtonGroup();
    for (int i = 0; i < 20; i++) {
      JRadioButton layerButton = new JRadioButton("");
      layerButton.setVisible(false);
      layerButtons[i] = layerButton;
      group1.add(layerButton);
      //   layerButton.setActionCommand("layer " + i);
      //   layerButtons[i].addActionListener(this);
      layerView.add(layerButton);
    }
  }


  /**
   * Updates the layer buttons to reflect the layers in the model.
   *
   * @param layerNames : the names of all layers.
   */
  @Override
  public void updateLayerRadioButton(ArrayList<String> layerNames) {
    for (int i = 0; i < layerButtons.length; i++) {
      if (i < layerNames.size()) {
        layerButtons[i].setText(layerNames.get(i));
        layerButtons[i].setVisible(true);
      } else {
        layerButtons[i].setText("de");
        layerButtons[i].setVisible(false);
      }
    }
  }


  /**
   * Updates the command buttons to reflect the state in the model, and the possible commands
   * allowed.
   *
   * @param state : the state that indicates what buttons should be displayed
   */
  public void updateButtonOptions(String state) {
    // project has been started
    if (state.equals("started")) {
      loadProject.setVisible(false);
      newProject.setVisible(false);
      addLayer.setVisible(true);
    } // first image has been added
    else if (state.equals("image-added")) {
      saveImage.setVisible(true);
      saveProject.setVisible(true);
      clearLayer.setVisible(true);

    } // first layer has been added
    else if (state.equals("layer-added")) {
      addImageToLayer.setVisible(true);
      removeLayer.setVisible(true);
    }
  }


  /**
   * Shows any error messages.
   *
   * @param err : error message to be shown
   */
  @Override
  public void renderMessage(String err) {
    JOptionPane.showMessageDialog(this, err, "Error",
            JOptionPane.ERROR_MESSAGE);
  }


  /**
   * Refreshes the view.
   */
  @Override
  public void refresh(BufferedImage image) {
    this.canvas.setIcon(new ImageIcon(image));
    this.repaint();
    this.validate();
  }


  /**
   * The action performed and sent to this method to process input.
   *
   * @param e the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    JLabel addFilterDisplay = new JLabel();
    String l = "";
    String actCom = e.getActionCommand();
    switch (actCom) {
      case "":
        break;
      case "null":
        break;
      case "red-component":
        l = "set-filter " + actCom + " ";
        filterDisplay.setText("Red-component Applied");
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "green-component":
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        filterDisplay.setText("Green-component Applied");
        break;
      case "blue-component":
        filterDisplay.setText("Blue-component Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "brighten-value":
        filterDisplay.setText("Brighten-value Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "brighten-intensity":
        filterDisplay.setText("Brighten-intensity Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "brighten-luma":
        filterDisplay.setText("Brighten-luma Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "brighten-screen":
        filterDisplay.setText("Brighten-screen Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "darken-value":
        filterDisplay.setText("Darken-value Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "darken-intensity":
        filterDisplay.setText("Darken-intensity Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "darken-luma":
        filterDisplay.setText("Darken-luma Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "darken-multiply":
        filterDisplay.setText("Darken-multiply Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "difference":
        filterDisplay.setText("Difference Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;
      case "remove-filter":
        filterDisplay.setText("No Filter Applied");
        l = "set-filter " + actCom + " ";
        addFilterDisplay.setText("Please enter the name of the layer you want to modify: ");
        l = l + (JOptionPane.showInputDialog(addFilterDisplay) + " ");
        break;

      case "run":
        l = this.textArea.getText();
        this.textArea.setText("");
        break;
      case "new-project":
        try {
          l = actCom + " ";
          //Displays the input field when asking for user input.
          JLabel startProjectInputDisplay = new JLabel();
          startProjectInputDisplay.setText("Please enter a name for this new project: ");
          String projectName = JOptionPane.showInputDialog(startProjectInputDisplay);
          l = l + projectName + " ";
          startProjectInputDisplay.setText("Please enter a width for this new project: ");
          l = l + (JOptionPane.showInputDialog(startProjectInputDisplay) + " ");
          startProjectInputDisplay.setText("Please enter a height for this new project: ");
          l = l + (JOptionPane.showInputDialog(startProjectInputDisplay) + " ");
        } catch (NullPointerException ex) {
          // Do nothing, user quits input field
        }
        break;
      case "add-layer":
        l = actCom + " ";
        try {
          //Displays the input field when asking for user input.
          JLabel addLayerInputDisplay = new JLabel();
          addLayerInputDisplay.setText("Please enter a name for this new layer:");
          l += JOptionPane.showInputDialog(addLayerInputDisplay) + " ";

        } catch (NullPointerException ex) {
          // Do nothing, user quits input field
        }
        break;
      case "remove-layer":
        l = actCom + " ";
        try {
          //Displays the input field when asking for user input.
          JLabel addLayerInputDisplay = new JLabel();
          addLayerInputDisplay.setText("Please enter a name for the layer you wish to delete:");
          l += JOptionPane.showInputDialog(addLayerInputDisplay) + " ";

        } catch (NullPointerException ex) {
          // Do nothing, user quits input field
        }
        break;
      case "clear-layer":
        l = actCom + " ";
        try {
          //Displays the input field when asking for user input.
          JLabel addLayerInputDisplay = new JLabel();
          addLayerInputDisplay.setText("Please enter a name for the layer you wish to clear:");
          l += JOptionPane.showInputDialog(addLayerInputDisplay) + " ";

        } catch (NullPointerException ex) {
          // Do nothing, user quits input field
        }
        break;
      case "add-image":
        l = "add-image-to-layer ";
        JLabel addImageInputDisplay = new JLabel();
        addImageInputDisplay.setText("Please enter the name of the layer you want to modify: ");
        l += (JOptionPane.showInputDialog(addImageInputDisplay) + " ");
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
          File f = chooser.getSelectedFile();
          String path = f.getAbsolutePath();
          l += (path + " ");
          addImageInputDisplay.setText("Please enter the xOffset of the image: ");
          l += (JOptionPane.showInputDialog(addImageInputDisplay) + " ");
          addImageInputDisplay.setText("Please enter the yOffset of the image: ");
          l += (JOptionPane.showInputDialog(addImageInputDisplay)) + " ";
        } else {
          return;
          // user changed their mind, exits file explorer
        }
        break;
      case "save-image":
        l = actCom + " ";
        JLabel addImageInputDisplayj = new JLabel();
        addImageInputDisplayj.setText("Please enter file path to save image:");
        l += JOptionPane.showInputDialog(addImageInputDisplayj) + " ";
        break;
      case "save-project":
        l = actCom + " ";
        JLabel addImageInputDisplay2 = new JLabel();
        addImageInputDisplay2.setText("Please enter file path to save project:");
        l += JOptionPane.showInputDialog(addImageInputDisplay2) + " ";
        break;
      case "load-project":
        l = actCom + " ";
        JFileChooser loadFileChooser = new JFileChooser();
        if (loadFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
          File f = loadFileChooser.getSelectedFile();
          String path = f.getAbsolutePath();
          l += path + " ";
        } else {
          return;
          // user changed their mind, exits file explorer
        }
        break;
      default:
        break;


    }
    if (commands != null) {
      commands.accept(l);
    }
  }
}
