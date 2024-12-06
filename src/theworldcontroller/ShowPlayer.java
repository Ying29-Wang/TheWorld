package theworldcontroller;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import theworld.Player;
import theworld.TheWorldFacade;

/**
 * The ShowPlayer class implements the CommandInterface and is responsible for
 * displaying the description of a player in the game.
 */
public class ShowPlayer implements CommandInterface {

  @Override
  public boolean execute(TheWorldFacade twf, AdapterInterface adapter, Scanner scan)
      throws IllegalStateException  {
    adapter.setOutput("Please pick a player to show description\n");
    List<Player> pl = twf.getPlayers();
    if ((pl != null) && (pl.size() > 0)) {
      for (Player p : pl) {
        adapter.setOutput(String.format("%d. %s ", p.getId(), p.getName()));
      }
      adapter.setOutput("\n");
      while (true) {

        String temp = scan.nextLine();

        if ((temp.matches("^[0-9]+$")) && (pl.stream().map(Player::getId)
            .collect(Collectors.toList()).contains(Integer.parseInt(temp)))) {
          adapter.setOutput((pl.get(Integer.parseInt(temp))).toString());
          return true;
        } else {
          adapter.setOutput("Wrong input, please re-enter the space number.\n");
        }
      }
    } else {
      adapter.setOutput("There is not enough players in the game.");
      return false;
    }
  }
}
