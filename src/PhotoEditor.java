import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import javax.swing.JFrame;

import controller.ImageController;
import controller.ImageControllerGUI;
import controller.ImageControllerImpl;
import model.ImageModelInterface;
import model.ImageModelPPM;
import view.ImageView;
import view.ImageViewInterface;
import view.SwingGuiView;


/**
 * Represents a photo editor application and runs the main method for it.
 */
public class PhotoEditor {


  /**
   * The main class for a photoEditors GUI.-
   *
   * @param args :
   */
  public static void main(String[] args) {

    if (args.length > 0) {
      if (args[0].equals("-file")) {
        String filePath = args[1];
        StringBuilder builder = new StringBuilder();
        StringReader in;
        FileReader reader;
        try {
          reader = new FileReader(filePath);
          Scanner scan = new Scanner(reader);

          while (scan.hasNextLine()) {
            //read the file line by line, and populate a string.
            // This will throw away any comment lines
            String s = scan.nextLine();
            builder.append(s + System.lineSeparator());
          }
          in = new StringReader(builder.toString());
        } catch (FileNotFoundException ex) {
          throw new IllegalArgumentException("File not found");
        }

        ImageModelInterface m = new ImageModelPPM();
        Appendable out = System.out;
        ImageControllerImpl controller = new ImageControllerImpl(m, new ImageView(out),
                in);
        controller.runImage();
      } else if (args[0].equals("-text")) {
        Readable in = new InputStreamReader(System.in);
        Appendable out = System.out;
        ImageModelInterface model = new ImageModelPPM();
        ImageViewInterface view = new ImageView(out);
        ImageController controller = new ImageControllerImpl(model, view, in);
        controller.runImage();

      }
    } else {
      SwingGuiView.setDefaultLookAndFeelDecorated(false);
      ImageModelInterface model = new ImageModelPPM();
      SwingGuiView frame = new SwingGuiView(null);
      ImageControllerGUI cont = new ImageControllerGUI(model, frame);
      frame.setController(cont);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }

  }
}