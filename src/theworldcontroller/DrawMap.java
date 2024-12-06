package theworldcontroller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import theworld.TheWorldFacade;

/**
 * This class represents the command to draw an instance map of the world.
 */
public class DrawMap implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan) 
      throws IllegalStateException  {
    try {
      adapter.setOutput("Please input the file name to store the picture:\n");
      String tempFile;
      do {
        tempFile = scan.nextLine();
        if (tempFile.endsWith(".png")) {
          twf.drawTheWorld(tempFile);
          adapter.setOutput(String.format("The map has been stored to the file %s\n", tempFile));
          return true;
        } else {
          adapter.setOutput("File must be a png, must end with .png\n");
        }
      } while (true);
    } catch (IOException e) {
      adapter.setOutput("An error occurred: " + e.getMessage() + "\n");
    }
    return false;
  }
}