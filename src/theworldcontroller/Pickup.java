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
 * The Pickup class implements the CommandInterface and provides the
 * functionality for a player to pick up an item in the game. The class handles
 * both manual and automatic item pickups, ensuring that the player can only
 * pick up valid items and that the game proceeds to the next turn after an item
 * is picked up.
 */
public class Pickup implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan)
      throws IllegalStateException  {
    Player p = twf.getTurnOfGame();
    if (p.getSpace().getItems().isEmpty()) {
      adapter.setOutput("No item list in the room.\n");
      return false;
    }
    if (p.getItems().size() == p.getItemLimit()) {
      adapter.setOutput(String.format("%s can't have more items.\n", p.getName()));
      return false;
    }
    adapter.setOutput("Please pick an item showing below.\n");
    for (ItemInterface i : p.getSpace().getItems()) {
      adapter.setOutput(String.format("%d. %s ", i.getId(), i.getName()));
    }
    adapter.setOutput("\n");

    if (!p.isAutomatic()) {
      handleManualPickup(p, twf, adapter, scan);
    } else {
      handleAutomaticPickup(p, twf, adapter);
    }
    return true;
  }

  /**
   * Handles the manual pickup of an item by a player in the game. This method
   * continuously prompts the player to enter an item number until a valid item is
   * picked up.
   * 
   * @param p the player attempting to pick up the item
   * @param twf  the facade providing access to the game's world and items
   * @param adapter the output interface to which messages are written
   * @param scan the scanner used to read input from the player
   * @throws IOException if an I/O error occurs while writing to the output
   */
  private void handleManualPickup(Player p, TheWorldFacade twf, 
      AdapterInterface adapter, Scanner scan)
      throws IllegalStateException  {
    while (true) {
      String temp = scan.nextLine();
      if (isValidItemId(temp, p)) {
        boolean result = p.pickup((Item) twf.getItemById(Integer.parseInt(temp)));
        if (result) {
          adapter.setOutput(String.format("The item has been picked up by %s.\n", p.getName()));
          proceedToNextTurn(twf, adapter);
          return;
        } else {
          adapter.setOutput("Wrong input, please re-enter the item number.\n");
        }
      } else {
        adapter.setOutput("Wrong input, please re-enter the item number.\n");
      }
    }
  }

  /**
   * Handles the automatic pickup of an item by the player in the current space.
   * If there are no items in the space, it notifies the player. Otherwise, it
   * randomly selects an item from the space and has the player pick it up. After
   * picking up the item, it proceeds to the next turn.
   *
   * @param p   the player who will pick up the item
   * @param twf the facade of the game world
   * @param out the appendable output to write messages 
   */
  private void handleAutomaticPickup(Player p, TheWorldFacade twf, AdapterInterface adapter) {
    if (p.getSpace().getItems().isEmpty()) {
      adapter.setOutput("No item list in the room.\n");
    } else {
      int random = (int) (Math.random() * p.getSpace().getItems().size());
      p.pickup(p.getSpace().getItems().get(random));
      adapter.setOutput(String.format("The item had been picked up by %s.\n", p.getName()));
      proceedToNextTurn(twf, adapter);
    }
  }

  /**
   * Checks if the given item ID is valid for the specified player. An item ID is
   * considered valid if it is a numeric string and exists in the list of item IDs
   * available in the player's current space.
   *
   * @param temp the item ID to validate as a string
   * @param p the player whose current space's items are to be checked
   * @return true if the item ID is valid, false otherwise
   */
  private boolean isValidItemId(String temp, Player p) {
    return temp.matches("^[0-9]+$") && p.getSpace().getItems().stream().map(ItemInterface::getId)
        .collect(Collectors.toList()).contains(Integer.parseInt(temp));
  }

  /**
   * Proceeds to the next turn in the game by invoking the nextTurn and
   * moveTargetToNext methods on the provided TheWorldFacade instance. It then
   * appends a message to the provided AdapterInterface indicating the target's new
   * location.
   *
   * @param twf the TheWorldFacade instance representing the game state
   * @param adapter the output interface to which the message will be appended
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