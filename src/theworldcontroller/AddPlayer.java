package theworldcontroller;

import java.util.NoSuchElementException;
import java.util.Scanner;
import theworld.Player;
import theworld.TheWorldFacade;

/**
 * This class represents the command of adding a player to the game. Each player
 * is identified by the name. They enter the world in a space of their choice.
 */
public class AddPlayer implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan)
      throws IllegalStateException {
    if (twf.getPlayers().size() < 10) {
      adapter.setOutput("Add a player controlled by computer? Press Y to create a robot, "
          + "or any other key to create a human-controlled player\n");
      String temp = scan.nextLine();
      boolean isAutomatic = "Y".equalsIgnoreCase(temp);
      adapter.setOutput("Please enter the name: \n");
      String newPerson;
      do {
        newPerson = scan.nextLine();
        if (!newPerson.matches("^[a-zA-Z0-9]+$")) {
          adapter.setOutput("Wrong input, please enter the name:\n");
          newPerson = null;
        }
      } while (newPerson == null);

      if (twf.addPlayerToTheWorld(new Player(twf.getPlayers().size(), newPerson, 5, isAutomatic))) {
        adapter.setOutput(String.format("New player %s has been added\n", newPerson));
        return true;
      } else {
        adapter.setOutput("Something went wrong, player could not be added! Try again\n");
        return false;
      }
    } else {
      adapter.setOutput("There are too many players in the game, can't add any more players.\n");
      return false;
    }
  }

}
