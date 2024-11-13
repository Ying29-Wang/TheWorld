package theworldcontroller;

import java.io.IOException;
import java.util.List;
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
  public boolean execute(TheWorldFacade twf, Appendable out, Scanner scan) {
    try {
      out.append("Please pick a player to show description\n");
      List<Player> pl = twf.getPlayers();
      if ((pl != null) && (pl.size() > 0)) {
        for (Player p : pl) {
          out.append(String.format("%d. %s ", p.getId(), p.getName()));
        }
        out.append("\n");
        while (true) {

          String temp = scan.nextLine();

          if ((temp.matches("^[0-9]+$")) && (pl.stream().map(Player::getId)
              .collect(Collectors.toList()).contains(Integer.parseInt(temp)))) {
            try {
              out.append((pl.get(Integer.parseInt(temp))).toString());
              return true;
            } catch (IOException e) {
              out.append("Something goes wrong, please try again\n ");
            }
          } else {
            out.append("Wrong input, please re-enter the space number.\n");
          }
        }
      } else {
        out.append("There is not enough players in the game.");
        return false;
      }
    } catch (IOException e) {
      return false;
    }
  }
}
