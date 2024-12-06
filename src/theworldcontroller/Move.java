package theworldcontroller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import theworld.Player;
import theworld.SpaceInterface;
import theworld.TheWorldFacade;

/**
 * The Move class implements the CommandInterface and provides functionality for
 * executing a move command within the game. It handles both manual and
 * automatic player movements, retrieves available spaces for movement, and
 * manages the transition to the next turn.
 */
public class Move implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan)
      throws IllegalStateException  {
    try {
      Player p = twf.getTurnOfGame();
      List<SpaceInterface> sf = getAvailableSpaces(p, twf);
      if (sf.size() == 0) {
        adapter.setOutput(" there is no available space to move\n");
        return false;
      }
      if (!p.isAutomatic()) {
        handleManualMove(p, sf, twf, adapter, scan);
      } else {
        handleAutomaticMove(p, sf, twf, adapter);
      }
      return true;
    } catch (NullPointerException e) {
      adapter.setOutput("An error occurred: " + e.getMessage() + "\n");
    }
    return false;
  }

  /**
   * Retrieves a list of available spaces for the given player to move into. If
   * the player is currently in a space, it returns the neighboring spaces of that
   * space. If the player is not in any space, it returns all spaces in the world.
   *
   * @param p   the player whose available spaces are to be retrieved
   * @param twf the facade providing access to the world and its spaces
   * @return a list of available spaces for the player
   */
  private List<SpaceInterface> getAvailableSpaces(Player p, TheWorldFacade twf) {
    
    return (p.getSpace() != null) 
        ? p.getSpace().getNeighbors().stream().filter(space -> space.isVisible())
        .collect(Collectors.toList()) : twf.getSpaces();
  }

  /**
   * Handles the manual move of a player within the game. This method prompts the
   * player to enter a move, validates the input, and attempts to move the player
   * to the specified space. If the move is successful, it proceeds to the next
   * turn. If an error occurs, it prompts the player to try again.
   * 
   * @param p the player who is making the move
   * @param sf   the list of available spaces the player can move to
   * @param twf  the facade object that provides access to the game world
   * @param adapter the AdapterInterface object to which output messages are written
   * @param scan the scanner object to read input from the player
   * @throws IOException if an I/O error occurs
   */
  private void handleManualMove(Player p, List<SpaceInterface> sf, TheWorldFacade twf,
      AdapterInterface adapter, Scanner scan) throws IllegalStateException  {
    adapter.setOutput(String.format("Enter a move for %s to ", p.getName()));
    for (SpaceInterface sp : sf) {
      adapter.setOutput(String.format("%d. %s ", sp.getId(), sp.getName()));
      
    }
    adapter.setOutput("\n");
    adapter.setMovable(true);
    while (true) {
      String temp = scan.nextLine();
      //System.out.println(temp);
      if (isValidSpaceId(temp, sf)) {
        twf.movePlayer(p, Integer.parseInt(temp));
        adapter.setOutput(
            String.format("%s has already moved to %s\n", p.getName(), p.getSpace().getName()));
        proceedToNextTurn(twf, adapter);
        break;
      } else {
        adapter.setOutput("Wrong input, please re-enter the space number.\n");
      }
    }
  }

  /**
   * Handles the automatic movement of a player to a random space from the
   * provided list of spaces.
   *
   * @param p   the player who will be moved
   * @param sf  the list of available spaces to move to
   * @param twf the facade of the game world
   * @param adapter the output Interface to log the movement
   * @throws IOException if an I/O error occurs while appending to the output
   */
  private void handleAutomaticMove(Player p, List<SpaceInterface> sf, TheWorldFacade twf,
      AdapterInterface adapter) {
    int random = (int) (Math.random() * sf.size());
    p.move(sf.get(random));
    adapter.setOutput(String.format("%s has already moved to %s\n", p.getName(),
        p.getSpace().getName()));
    proceedToNextTurn(twf, adapter);
  }

  /**
   * Checks if the given space ID is valid. A valid space ID is a string that
   * consists only of digits and exists in the provided list of space interfaces.
   *
   * @param temp the space ID to check
   * @param sf   the list of space interfaces to validate against
   * @return true if the space ID is valid, false otherwise
   */
  private boolean isValidSpaceId(String temp, List<SpaceInterface> sf) {
    return temp.matches("^[0-9]+$") && sf.stream().map(SpaceInterface::getId)
        .collect(Collectors.toList()).contains(Integer.parseInt(temp));
  }

  /**
   * Proceeds to the next turn in the game by invoking the necessary methods on
   * the TheWorldFacade instance and appending the result to the provided
   *
   * @param twf the TheWorldFacade instance that manages the game state
   * @param adapter to which the result will be exported
   */
  private void proceedToNextTurn(TheWorldFacade twf, AdapterInterface adapter) {
    twf.nextTurn();
    twf.moveTargetToNext();
    adapter.setOutput(String.format("%s has already moved to No. %d %s\n", 
        twf.getTarget().getName(),
        twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));
    if (twf.getPet() != null) {
      twf.movePetToNext();
      adapter.setOutput(String.format("%s has already moved to No. %d %s\n", twf.getPet().getName(),
          twf.getPet().getSpace().getId(), twf.getPet().getSpace().getName()));
    }

  }
}