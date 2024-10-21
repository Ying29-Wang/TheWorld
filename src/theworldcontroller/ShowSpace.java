package theworldcontroller;

import java.io.IOException;
import java.util.List;
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
  public boolean execute(TheWorldFacade twf, Appendable out, Scanner scan) {
    try {
      out.append("please pick a space to show description\n");
      List<SpaceInterface> pl = twf.getSpaces();
      if ((pl != null) && (pl.size() > 0)) {
        for (SpaceInterface p : pl) {
          out.append(String.format("%d. %s ", p.getId(), p.getName()));
        }
        out.append("\n");
        while (true) {
          String temp = scan.nextLine();
          if ((temp.matches("^[0-9]+$")) && (pl.stream().map(SpaceInterface::getId)
              .collect(Collectors.toList()).contains(Integer.parseInt(temp)))) {
            try {
              out.append((pl.get(Integer.parseInt(temp))).toString());
              if (twf.getTarget().getSpace().getId() == Integer.parseInt(temp)) {
                out.append(String.format("%s is in the space.\n", twf.getTarget().getName()));
              }
              return true;
            } catch (IOException e) {
              out.append("Something goes wrong, please try again\n ");
            }
          } else {
            out.append("Wrong input, please re-enter the space number.\n");
          }
        }
      } else {
        out.append("There is not enough spaces in the game.");
        return false;
      }
    } catch (IOException e) {
      return false;
    }
  }

}
