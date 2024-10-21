package theworldcontroller;

import java.io.IOException;
import java.util.Scanner;
import theworld.Player;
import theworld.TheWorldFacade;

/**
 * This class represents the command of adding a player to the game. Each player
 * is identified by the name. They enter the world in a space of their choice.
 */
public class AddPlayer implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, Appendable out, Scanner scan) {
    try {
      out.append("Add a player controlled by computer? Press Y to create a robot, "
          + "or any other key to create a human-controlled player\n");
      String temp = scan.nextLine();
      boolean isAutomatic = "Y".equalsIgnoreCase(temp);
      out.append("Please enter the name: \n");
      String newPerson;
      do {
        newPerson = scan.nextLine();
        if (!newPerson.matches("^[a-zA-Z0-9]+$")) {
          out.append("Wrong input, please enter the name:\n");
          newPerson = null;
        }
      } while (newPerson == null);

      if (twf.addPlayerToTheWorld(new Player(twf.getPlayers().size(), newPerson, 1, isAutomatic))) {
        out.append(String.format("New player %s has been added\n", newPerson));
        return true;
      } else {
        out.append("Something went wrong, player could not be added! Try again\n");
        return false;
      }
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
