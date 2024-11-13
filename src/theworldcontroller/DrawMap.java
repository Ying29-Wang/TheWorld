package theworldcontroller;

import java.io.IOException;
import java.util.Scanner;
import theworld.TheWorldFacade;

/**
 * This class represents the command to draw an instance map of the world.
 */
public class DrawMap implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, Appendable out, Scanner scan) {
    try {
      out.append("Please input the file name to store the picture:\n");
      String tempFile;
      do {
        tempFile = scan.nextLine();
        if (tempFile.endsWith(".png")) {
          twf.drawTheWorld(tempFile);
          out.append(String.format("The map has been stored to the file %s\n", tempFile));
          return true;
        } else {
          out.append("File must be a png, must end with .png\n");
        }
      } while (true);
    } catch (IOException e) {
      try {
        out.append("An error occurred: ").append(e.getMessage()).append("\n");
      } catch (IOException ignored) {
        return false;
      }
      return false;
    }
  }

}