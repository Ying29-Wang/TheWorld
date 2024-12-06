package theworldcontroller;


import java.util.NoSuchElementException;
import java.util.Scanner;
import theworld.Player;
import theworld.TheWorldFacade;

/**
 * This class represents the look around action of the player by displaying
 * information about where a specific player is in the world including what
 * spaces that can been seen from where they are. This will lead to a turn.
 */
public class LookAround implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner in)
      throws IllegalStateException  {
    Player p = twf.getTurnOfGame();
    String lookingaround = twf.lookAroundFromSpace(p);
    if (lookingaround != null) {
      adapter.setOutput(lookingaround);
      twf.nextTurn();
      twf.moveTargetToNext();
      adapter.setOutput(String.format("%s has already moved to No. %d %s\n", 
          twf.getTarget().getName(),
          twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));
      if (twf.getPet() != null) {
        twf.movePetToNext();
        adapter.setOutput(String.format("%s has already moved to No. %d %s\n", 
            twf.getPet().getName(),
            twf.getPet().getSpace().getId(), twf.getPet().getSpace().getName()));
      }
      return true;
    }
    return false;
   
  }
}
