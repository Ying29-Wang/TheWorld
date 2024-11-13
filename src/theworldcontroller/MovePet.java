package theworldcontroller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import theworld.SpaceInterface;
import theworld.TheWorldFacade;

/**
 * The MovePet class implements the CommandInterface and provides the
 * functionality to move a pet to a different space within the world.
 */
public class MovePet implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, Appendable out, Scanner scan) {
    try {
      List<SpaceInterface> sf = twf.getSpaces();

      out.append("Move the pet to an space showing below .\n");
      for (SpaceInterface si : sf) {
        out.append(String.format("%d. %s ", si.getId(), si.getName()));
      }
      out.append("\n");
      while (true) {
        String temp = scan.nextLine();
        if ((temp.matches("^[0-9]+$")) && (Integer.parseInt(temp) >= 0)
            && (Integer.parseInt(temp) < sf.size())) {
          if (twf.movePet(twf.getPet(), Integer.parseInt(temp))) {
            twf.nextTurn();
            twf.moveTargetToNext();
            out.append(
                String.format("%s has already moved to No. %d %s\n", twf.getTarget().getName(),
                    twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));
            out.append(String.format("%s has already moved to No. %d %s\n", twf.getPet().getName(),
                twf.getPet().getSpace().getId(), twf.getPet().getSpace().getName()));
            return true;
          } else {
            out.append("Wrong input, please re-enter the space number.\n");
          }
        } else {
          out.append("Wrong input, please re-enter the space number.\n");
        }
      }
    } catch (Exception e) {
      try {
        out.append("An error occurred: ").append(e.getMessage()).append("\n");
      } catch (IOException ignored) {
        return false;
      }
      return false;
    }
  }

}
