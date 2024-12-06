package theworldcontroller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import theworld.SpaceInterface;
import theworld.TheWorldFacade;

/**
 * The ShowSpace class implements the CommandInterface and is responsible for
 * displaying the description of a selected space within the game.
 */
public class ShowSpace implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan) 
      throws IllegalStateException  {
    adapter.setOutput("please pick a space to show description\n");
    List<SpaceInterface> pl = twf.getSpaces();
    if ((pl != null) && (pl.size() > 0)) {
      for (SpaceInterface p : pl) {
        adapter.setOutput(String.format("%d. %s ", p.getId(), p.getName()));
      }
      adapter.setOutput("\n");
      while (true) {
        String temp = scan.nextLine();
        if ((temp.matches("^[0-9]+$")) && (pl.stream().map(SpaceInterface::getId)
            .collect(Collectors.toList()).contains(Integer.parseInt(temp)))) {
          adapter.setOutput((pl.get(Integer.parseInt(temp))).toString());
          if (twf.getTarget().getSpace().getId() == Integer.parseInt(temp)) {
            adapter.setOutput(String.format("%s is in the space.\n", twf.getTarget().getName()));
          }
          if (twf.getPet().getSpace().getId() == Integer.parseInt(temp)) {
            adapter.setOutput(String.format("The pet %s is in the space.\n", 
                twf.getPet().getName()));
          }
          return true;
        } else {
          adapter.setOutput("Wrong input, please re-enter the space number.\n");
        }
      }
    } else {
      adapter.setOutput("There is not enough spaces in the game.");
      return false;
    }
  }

}
