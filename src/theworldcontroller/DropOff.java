package theworldcontroller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import theworld.Item;
import theworld.ItemInterface;
import theworld.Player;
import theworld.TheWorldFacade;

/**
 * The DropOff class implements the CommandInterface and provides the
 * functionality for a player to drop off an item in the game world. The class
 * handles both manual and automatic drop-off scenarios.
 */
public class DropOff implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan)
      throws IllegalStateException  {
    try {
      Player p = twf.getTurnOfGame();
      if (p.getItems().isEmpty()) {
        adapter.setOutput(String.format("No item carried by %s.\n", p.getName()));
        return false;
      }
      adapter.setOutput("Please leave an item in the space, items are shown below.\n");
      for (ItemInterface i : p.getItems()) {
        adapter.setOutput(String.format("%d. %s ", i.getId(), i.getName()));
      }
      adapter.setOutput("\n");

      if (!p.isAutomatic()) {
        handleManualDropOff(p, twf, adapter, scan);
      } else {
        handleAutomaticDropOff(p, twf, adapter);
      }
      return true;
    } catch (IOException e) {
      adapter.setOutput("An error occurred: " + e.getMessage() + "\n");
    }
    return false;
  }

  /**
   * Handles the manual drop-off of an item by a player in the game. This method
   * continuously prompts the player for input until a valid item ID is provided
   * and the item is successfully dropped off in the space.
   *
   * @param p    the player who is dropping off the item
   * @param twf  the facade object representing the game world
   * @param out  the output stream to which messages are written
   * @param scan the scanner object used to read player input
   * @throws IOException if an I/O error occurs
   */
  private void handleManualDropOff(Player p, TheWorldFacade twf, AdapterInterface adapter,
      Scanner scan) throws IllegalStateException  {
    while (true) {
      String temp = scan.nextLine();
      if (isValidItemId(temp, p)) {
        boolean result = p.leaveItemToTheSpace((Item) twf.getItemById(Integer.parseInt(temp)));
        if (result) {
          adapter.setOutput(String.format("The item has been dropped off by %s.\n", p.getName()));
          proceedToNextTurn(twf, adapter);
          return;
        } else {
          adapter.setOutput("Wrong input, please re-enter space number.\n");
        }
      } else {
        adapter.setOutput("Wrong input, please re-enter space number.\n");
      }
    }
  }

  /**
   * Handles the automatic drop-off of an item by the player. If the player is
   * carrying items, a random item is selected and left in the current space. If
   * the player is not carrying any items, a message is appended indicating no
   * items are carried.
   * 
   * @param p the player who is performing the drop-off
   * @param twf the facade of the world where the game is taking place
   * @param out the output Interface to which messages are written
   * @throws IOException if an I/O error occurs while appending to the output
   */
  private void handleAutomaticDropOff(Player p, TheWorldFacade twf, AdapterInterface adapter)
      throws IOException {
    if (p.getItems().isEmpty()) {
      adapter.setOutput("No item carried by the player.\n");
    } else {
      int random = (int) (Math.random() * p.getItems().size());
      p.leaveItemToTheSpace((Item) p.getItems().get(random));
      adapter.setOutput(String.format("The item had been left to the space %s by %s.\n",
          p.getSpace().getName(), p.getName()));
      proceedToNextTurn(twf, adapter);
    }
  }

  /**
   * Checks if the given item ID is valid for the specified player. An item ID is
   * considered valid if it is a numeric string and it exists in the player's list
   * of items.
   *
   * @param temp the item ID to validate as a string
   * @param p the player whose items are to be checked
   * @return true if the item ID is valid, false otherwise
   */
  private boolean isValidItemId(String temp, Player p) {
    return temp.matches("^[0-9]+$") && p.getItems().stream().map(ItemInterface::getId)
        .collect(Collectors.toList()).contains(Integer.parseInt(temp));
  }

  /**
   * Proceeds to the next turn in the game by invoking the nextTurn and
   * moveTargetToNext methods on the provided TheWorldFacade instance. It then
   * appends a message to the provided IOAdapter, indicating the target's
   * new location.
   *
   * @param twf the TheWorldFacade instance that manages the game state and
   *            operations.
   * @param adapter the Interface of output to which the status message will be
   *            appended.
   */
  private void proceedToNextTurn(TheWorldFacade twf, AdapterInterface adapter) {
    twf.nextTurn();
    twf.moveTargetToNext();
    adapter
        .setOutput(String.format("%s has already moved to No. %d %s\n", twf.getTarget().getName(),
            twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));
    if (twf.getPet() != null) {
      twf.movePetToNext();
      adapter.setOutput(String.format("%s has already moved to No. %d %s\n", twf.getPet().getName(),
          twf.getPet().getSpace().getId(), twf.getPet().getSpace().getName()));
    }

  }
}