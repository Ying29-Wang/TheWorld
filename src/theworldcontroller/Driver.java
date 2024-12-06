package theworldcontroller;

import java.io.IOException;
import theworldviewer.IView;
import theworldviewer.Viewer;

/**
 * This class serves as the entry point for the application. It initializes the
 * {@code TheWorldFacade}, pass a specification file, and executes actions on
 * the {@code TheWorld} instance based on command-line arguments.
 */
public class Driver {

  /**
   * Default constructor for the Driver class.
   */
  public Driver() {
  }

  /**
   * The main method serves as the entry point for the application.
   * It initializes the viewer and adapter, and starts a new game based on the provided arguments.
   *
   * @param args Command line arguments where:
   *             args[0] is the specification file path,
   *             args[1] is the game level (must be a positive integer).
   * @throws IOException if the specification file cannot be found.
   */
  public static void main(String[] args) {
    IView viewer = new Viewer();   
    try {
      AdapterInterface adapter = new IOAdapter(viewer);
      viewer.setAdapter(adapter);
      if ((args[0] != null) && (args[1].trim().matches("^[0-9]+$"))) {
        adapter.startNewGame(args[0].trim(), Integer.parseInt(args[1].trim()));
      } else {
        System.out.print(" Wrong input , please close and retry");
      }
    } catch (IOException e) {
      System.out.print(" can not find specification file , please close and retry");
    }
  }
}