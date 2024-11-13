package theworldcontroller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import theworld.ItemInterface;
import theworld.Player;
import theworld.TheWorldFacade;

/**
 * The Attempt class implements the CommandInterface and represents an attempt
 * by a player to attack a target in the game. The class provides methods to
 * execute the attempt, either automatically or manually, based on the player's
 * state and available items.
 */
public class Attempt implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, Appendable out, Scanner scan) {
    Player p = twf.getTurnOfGame();
    try {
      if (p.getItems().size() > 0) {
        if (p.isAutomatic()) {
          ItemInterface attackItem = getMaxHurtItem(p.getItems());
          out.append(String.format("%s is trying to attack target by using %s \n", p.getName(),
              attackItem.getName()));
          if (!twf.beSeen(p)) {
            int hurt = Math.min(attackItem.getDamage(), twf.getTarget().getHealth());
            twf.attempt(hurt);
            out.append(String.format(
                "The Target has been attacked by %s and cause %d hurt. "
                    + "Health number is left for %d\n",
                attackItem.getName(), hurt, twf.getTarget().getHealth()));
          } else {
            out.append("attacked false beacause somebody is watching you\n");
          }
          p.removeItem(attackItem);
          twf.moveItemToEvidences(attackItem);
          return true;
        } else {
          out.append("Please pick an item showing below.\n");
          for (ItemInterface i : p.getItems()) {
            out.append(String.format("%d. %s ", i.getId(), i.getName()));
          }
          out.append("\n");

          handleManualAttempt(p, twf, out, scan);
          return true;
        }
      } else {
        if (!twf.beSeen(p)) {
          twf.attempt(1);
          out.append(String.format(
              "%s is poking him in the eye and hurt %d Health number is left for %d\n", p.getName(),
              1, twf.getTarget().getHealth()));

          return true;
        } else {
          out.append("attacked false beacause somebody is watching you\n");
          return true;
        }
      }
    } catch (IOException e) {
      return false;
    }
  }

  private ItemInterface getMaxHurtItem(List<ItemInterface> items) {
    int maxHurt = 0;
    ItemInterface maxHurtItem = null;
    for (ItemInterface i : items) {
      if (i.getDamage() >= maxHurt) {
        maxHurt = i.getDamage();
        maxHurtItem = i;
      }
    }
    return maxHurtItem;
  }

  /**
   * Handles the manual pickup of an item by a player in the game. This method
   * continuously prompts the player to enter an item number until a valid item is
   * picked up.
   * 
   * @param p    the player attempting to pick up the item
   * @param twf  the facade providing access to the game's world and items
   * @param out  the appendable output to which messages are written
   * @param scan the scanner used to read input from the player
   * @throws IOException if an I/O error occurs while writing to the output
   */
  private void handleManualAttempt(Player p, TheWorldFacade twf, Appendable out, Scanner scan)
      throws IOException {
    while (true) {
      String temp = scan.nextLine();
      if (isValidItemId(temp, p)) {
        ItemInterface it = twf.getItemById(Integer.parseInt(temp));
        if (!twf.beSeen(p)) {
          int hurt = Math.min(twf.getTarget().getHealth(), it.getDamage());
          boolean result = twf.attempt(hurt);
          if (result) {
            out.append(String.format(
                "The Target has been attacked by %s and cause %d hurt. "
                    + "Health number is left for %d\n",
                it.getName(), hurt, twf.getTarget().getHealth()));

            p.removeItem(it);
            twf.moveItemToEvidences(it);
            return;
          } else {
            out.append("Wrong input, please re-enter the item number.\n");
          }
        } else {
          out.append("attacked false beacause somebody is watching you\n");
          p.removeItem(it);
          twf.moveItemToEvidences(it);
          return;
        }
      } else {
        out.append("Wrong input, please re-enter the item number.\n");
      }
    }
  }

  /**
   * Checks if the given item ID is valid for the specified player. An item ID is
   * considered valid if it is a numeric string and exists in the list of item IDs
   * available in the player's current space.
   *
   * @param temp the item ID to validate as a string
   * @param p    the player whose current space's items are to be checked
   * @return true if the item ID is valid, false otherwise
   */
  private boolean isValidItemId(String temp, Player p) {
    return temp.matches("^[0-9]+$") && p.getItems().stream().map(ItemInterface::getId)
        .collect(Collectors.toList()).contains(Integer.parseInt(temp));
  }
}
