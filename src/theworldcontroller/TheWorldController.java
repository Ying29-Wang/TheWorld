package theworldcontroller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import theworld.TheWorldFacade;

/**
 * TheWorldController is responsible for managing the flow of the game. It
 * interacts with the user through input and output streams and controls the
 * game logic based on user inputs and game state.
 */
public class TheWorldController implements ControllerInterface {

  private final Appendable out;
  private final Scanner scan;
  private int turnLimit;
  private int currentTurn;


  /**
   * Constructs a TheWorldController object.
   *
   * @param in        the input source, must not be null
   * @param out       the output destination, must not be null
   * @param turnLimit the maximum number of turns, must be at least 1
   * @throws IllegalArgumentException if in or out is null, or if turnLimit is
   *                                  less than 1
   */
  public TheWorldController(Readable in, Appendable out, int turnLimit) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    if (turnLimit < 1) {
      throw new IllegalArgumentException("at least play 1 turn");
    }
    this.out = out;
    scan = new Scanner(in);
    this.turnLimit = turnLimit;
    this.currentTurn = 0;
  }

  @Override
  public int getTurn() {
    return this.currentTurn;
  }

  /**
   * Starts and manages the game flow for TheWorld.
   *
   * @param twf           TheWorldFacade instance to interact with the game world.
   * @param specification The file path to the game specification.
   * @throws IllegalStateException if there is an issue with the game output or
   *                               file reading.
   */
  @Override
  public void playGame(TheWorldFacade twf, String specification) throws IllegalStateException {
    try {
      out.append("Welcome! The game is start\n");
      out.append("------------------------------------------------------------\n");
      FileReader fr = new FileReader(specification);
      twf.parseTheWorld(fr);
      fr.close();
      out.append(
          String.format("%s is open, let's go and take an adventure!\n", twf.getWorldName()));
      out.append("Before that, please create at least one player to start our journey.\n");
      AddPlayer addPlayer = new AddPlayer();
      while (!addPlayer.execute(twf, out, scan)) {
        out.append("Add player failure, please try again.\n");
      }
      while (true) {
        out.append("Press Y to add more player, press any key to continue the game.\n");
        if (scan.nextLine().trim().toUpperCase().equals("Y")) {
          if (!addPlayer.execute(twf, out, scan)) {
            out.append("Add player failure, please try again.\n");
          }
        } else {
          break;
        }
      }
      out.append("Now, we can enter the creepy castle to begin our trip...\n");
      while ((currentTurn <= turnLimit) && (twf.getTarget().getHealth() > 0)) {
        out.append("===========================================================================\n");
        out.append("It is turn ");
        out.append(Integer.toString(currentTurn));
        out.append("\n");
        out.append(String.format("Now is %s turn:\n", twf.getTurnOfGame().getName()));
        if (twf.getTurnOfGame().getSpace() == null) {
          out.append(String.format("you are not in any space right now\n"));

          Move m = new Move();
          while (!m.execute(twf, out, scan)) {
            out.append(
                "The input data is wrong, please check and reenter space number and try again.\n");
          }
          currentTurn++;
        } else if (twf.getTurnOfGame().isAutomatic()) {
          out.append(String.format("you are in the space %d %s\n",
              twf.getTurnOfGame().getSpace().getId(), twf.getTurnOfGame().getSpace().getName()));

          if (twf.getTurnOfGame().getSpace().getId() == twf.getTarget().getSpace().getId()) {
            out.append(
                String.format("%s decide to attack target\n", twf.getTurnOfGame().getName()));
            if (!new Attempt().execute(twf, out, scan)) {
              out.append("something wrong occurred, please try something else.\n");
            } else {
              if (twf.getTarget().getHealth() > 0) {
                twf.nextTurn();
                twf.moveTargetToNext();
                out.append(
                    String.format("%s has already moved to No. %d %s\n", twf.getTarget().getName(),
                        twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));
                if (twf.getPet() != null) {
                  twf.movePetToNext();
                  out.append(
                      String.format("%s has already moved to No. %d %s\n", twf.getPet().getName(),
                          twf.getPet().getSpace().getId(), twf.getPet().getSpace().getName()));
                }
                this.currentTurn++;
              }
            }
          } else {
            int random = (int) (Math.random() * 4);
            switch (random) {
              case 0: {
                out.append(
                    String.format("%s decide to move to a space\n", twf.getTurnOfGame().getName()));
                if (new Move().execute(twf, out, scan)) {
                  this.currentTurn++;
                }
                break;
              }
              case 1: {
                out.append(
                    String.format("%s decide to pickup an item\n", twf.getTurnOfGame().getName()));
                if (new Pickup().execute(twf, out, scan)) {
                  this.currentTurn++;
                }
                break;
              }
              case 2: {
                out.append(String.format("%s decide to drop off an item to the space\n",
                    twf.getTurnOfGame().getName()));
                if (new DropOff().execute(twf, out, scan)) {
                  this.currentTurn++;
                }
                break;
              }
              case 3: {
                out.append(
                    String.format("%s decide to look up around\n", twf.getTurnOfGame().getName()));
                if (new LookAround().execute(twf, out, scan)) {
                  this.currentTurn++;
                }
                break;
              }
              default: {
                break;
              }
            }
          }
        } else {
          out.append(String.format("you are in the space %d %s\n",
              twf.getTurnOfGame().getSpace().getId(), twf.getTurnOfGame().getSpace().getName()));
          while (true) {
            out.append(String.format("Choose an action for %s, only press 1-10: "
                + "\n1.move to another space \n2.pickup an item in the space "
                + "\n3.dropoff an item to the space \n4.look around \n5.add a player "
                + "\n6.draw the map \n7.show a player \n8.show a space"
                + "\n9.move pet \n10.attempt to target \n"
                + "quit game press q.\n", twf.getTurnOfGame().getName()));
            String temp = null;
            temp = scan.nextLine();
            if ((temp != null) && (!temp.toUpperCase().equals("Q")) && (temp.matches("^[0-9]+$"))) {
              try {
                switch (Integer.parseInt(temp)) {
                  case 1: {
                    Move m = new Move();
                    if (!m.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 2: {
                    Pickup p = new Pickup();
                    if (!p.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 3: {
                    DropOff drop = new DropOff();
                    if (!drop.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 4: {
                    LookAround l = new LookAround();
                    if (!l.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 5: {
                    AddPlayer ad = new AddPlayer();
                    if (!ad.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 6: {
                    DrawMap dm = new DrawMap();
                    if (!dm.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 7: {
                    ShowPlayer sp = new ShowPlayer();
                    if (!sp.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 8: {
                    ShowSpace ss = new ShowSpace();
                    if (!ss.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 9: {
                    MovePet mp = new MovePet();
                    if (!mp.execute(twf, out, scan)) {
                      out.append("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 10: {
                    if (twf.getTurnOfGame().getSpace().getId() == twf.getTarget().getSpace()
                        .getId()) {
                      Attempt at = new Attempt();
                      if (!at.execute(twf, out, scan)) {
                        out.append("something wrong occurred, please try something else.\n");
                      } else {
                        if (twf.getTarget().getHealth() != 0) {
                          twf.nextTurn();
                          twf.moveTargetToNext();
                          out.append(String.format("%s has already moved to No. %d %s\n",
                              twf.getTarget().getName(), twf.getTarget().getSpace().getId(),
                              twf.getTarget().getSpace().getName()));
                          if (twf.getPet() != null) {
                            twf.movePetToNext();
                            out.append(String.format("%s has already moved to No. %d %s\n",
                                twf.getPet().getName(), twf.getPet().getSpace().getId(),
                                twf.getPet().getSpace().getName()));
                          }
                          this.currentTurn++;
                        }
                      }
                      break;
                    } else {
                      out.append("target is not in the space, try other options.\n");
                      break;
                    }
                  }
                  default: {
                    out.append("wrong input, please try again.\n");
                    break;
                  }
                }
              } catch (IllegalStateException e) {
                out.append(e.getMessage());
              }
              break;
            } else if (temp.toUpperCase().equals("Q")) {
              out.append("User quit game, Bye!\n");
              return;
            } else {
              out.append("something wrong for input.\n");
            }
          }
        }
      }
      if (twf.getTarget().getHealth() == 0) {
        out.append(
            String.format("%s win this game, game is over, Bye!\n", twf.getTurnOfGame().getName()));
      } else {
        out.append("You are running out of turns, game is over, Bye!\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalStateException(
          "There is a problem occurred during output, please restart game and try again.\n");
    }
  }
}
