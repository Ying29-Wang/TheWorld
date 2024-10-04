package theworld;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class serves as the entry point for the application.
 * It initializes the {@code TheWorldFacade}, pass a specification file,
 * and executes actions on the {@code TheWorld} instance based on command-line 
 * arguments.
 */
public class Driver {

  public Driver() {
  }

  /**
   * The main method that drives the application.
   * It takes command-line arguments to specify the input specification file, 
   * the output sample file for character movements, and the output map file 
   * for the visual representation of the world.
   * 
   * @param args  the command-line arguments
   */
  public static void main(String[] args) {
    String specification;
    String sampleFile;
    String mapFile;
    TheWorld tw;
    
    if (args.length > 0) {
      specification = args[0].trim();
      TheWorldFacade twf = new TheWorldFacade();
      try {
        // create TheWorld instance

        if ((args.length > 1) && (twf.parseTheWorld(new FileReader(specification)) != null)) {
          sampleFile = args[1].trim();
          try {
            // run the model and print to the sample file;
            FileWriter fw = new FileWriter(sampleFile);
            fw.write(twf.moveTargetInTheWorld(Mode.sequence, 23));
            fw.flush();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        // draw the map as picture to a file
        if (args.length > 2) {
          mapFile = args[2].trim();
          twf.drawTheWorld(mapFile);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

}
