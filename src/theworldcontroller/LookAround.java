package theworldcontroller;

import java.io.IOException;
import java.util.Scanner;
import theworld.Player;
import theworld.TheWorldFacade;

/**
 * This class represents the look around action of the player by displaying
 * information
 * about where a specific player is in the world including what spaces that can
 * been seen
 * from where they are. This will lead to a turn.
 */
public class LookAround implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, Appendable out, Scanner in) {
    try {
      Player p = twf.getTurnOfGame();
      String lookingaround = twf.lookAroundFromSpace(p);
      if (lookingaround != null) {
        out.append(lookingaround);
        twf.nextTurn();
        twf.moveTargetToNext();
        out.append(String.format("%s has already moved to No. %d %s\n", twf.getTarget().getName(),
            twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));

        return true;
      }
      return false;
    } catch (IOException e) {
      return false;
    }
  }
}
