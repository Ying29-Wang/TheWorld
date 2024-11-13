package theworldcontroller;

import java.io.InputStreamReader;
import theworld.TheWorldFacade;

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
   * The main method that drives the application. It takes command-line arguments
   * to specify the input specification file, the output sample file for character
   * movements, and the output map file for the visual representation of the
   * world.
   * 
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    try {
      if ((args.length > 1) && (args[1].matches("^[0-9]+$"))) {
        String specification = args[0].trim();
        int stepLimit = Integer.parseInt(args[1].trim());
        new TheWorldController(input, output, stepLimit).playGame(new TheWorldFacade(),
            specification);
      } else {
        System.out.println("Input arguments error occured. "
            + "Please check the spicification file name and set a right steps to run the game.");
      }
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}