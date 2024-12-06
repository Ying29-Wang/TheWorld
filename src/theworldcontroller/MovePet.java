package theworldcontroller;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import theworld.SpaceInterface;
import theworld.TheWorldFacade;

/**
 * The MovePet class implements the CommandInterface and provides the
 * functionality to move a pet to a different space in the game world.
 */
public class MovePet implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan)
      throws IllegalStateException {
    List<SpaceInterface> sf = twf.getSpaces();

    adapter.setOutput("Move the pet to an space showing below .\n");
    for (SpaceInterface si : sf) {
      adapter.setOutput(String.format("%d. %s ", si.getId(), si.getName()));
    }
    adapter.setOutput("\n");
    while (true) {
      String temp = scan.nextLine();
      if ((temp.matches("^[0-9]+$")) && (Integer.parseInt(temp) >= 0)
          && (Integer.parseInt(temp) < sf.size())) {
        if (twf.movePet(twf.getPet(), Integer.parseInt(temp))) {
          twf.nextTurn();
          twf.moveTargetToNext();
          adapter.setOutput(
              String.format("%s has already moved to No. %d %s\n", twf.getTarget().getName(),
                  twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));
          adapter.setOutput(String.format("%s has already moved to No. %d %s\n", 
              twf.getPet().getName(),
              twf.getPet().getSpace().getId(), twf.getPet().getSpace().getName()));
          return true;
        } else {
          adapter.setOutput("Wrong input, please re-enter the space number.\n");
        }
      } else {
        adapter.setOutput("Wrong input, please re-enter the space number.\n");
      }
    } 
  }

}
