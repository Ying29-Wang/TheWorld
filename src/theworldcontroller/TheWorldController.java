package theworldcontroller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import theworld.TheWorldFacade;

/**
 * TheWorldController is responsible for managing the flow of the game. It
 * interacts with the user through input and output streams and controls the
 * game logic based on user inputs and game state.
 */
public class TheWorldController implements ControllerInterface {

  private final AdapterInterface adapter;
  private final Scanner scan;
  private int turnLimit;
  private int currentTurn;
  private boolean gameContinue;

  /**
   * Constructs a TheWorldController object.
   *
   * @param scan  the input source, must not be null
   * @param adapter   the output destination, must not be null
   * @param turnLimit the maximum number of turns, must be at least 1
   * @throws IllegalArgumentException if in or out is null, or if turnLimit is
   *                                  less than 1
   */
  public TheWorldController(Scanner scan, AdapterInterface adapter, int turnLimit) {
    if (scan == null || adapter == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    if (turnLimit < 1) {
      throw new IllegalArgumentException("at least play 1 turn");
    }
    this.adapter = adapter;
    this.scan = scan;
    this.turnLimit = turnLimit;
    this.currentTurn = 0;
    this.gameContinue = false;
  }

  /**
   * use this method to stop the game from user interface.
   */
  public void stopGame() {
    this.gameContinue = false;
   
  }
  /**
   * Starts and manages the game flow for TheWorld.
   *
   * @param twf TheWorldFacade instance to interact with the game world.
   * @param specification The file path to the game specification.
   * @throws IllegalStateException if there is an issue with the game output or
   *                               file reading.
   */
  
  @Override
  public void playGame(TheWorldFacade twf, String specification) throws IllegalStateException {
    gameContinue = true;
    try {
      adapter.setOutput("Welcome! The game is start \n");
      adapter.setOutput("This Game was created by Ying Wang\n");
      adapter.setOutput("------------------------------------------------------------\n");
      FileReader fr = new FileReader(specification);
      twf.parseTheWorld(fr);
      fr.close();
      adapter.setOutput(
          String.format("%s is open, let's go and take an adventure!\n", twf.getWorldName()));
      adapter.setOutput("Before that, please create at least one player to start our journey.\n");
      AddPlayer addPlayer = new AddPlayer();
      while (!addPlayer.execute(twf, adapter, scan)) {
        adapter.setOutput("Add player failure, please try again.\n");
      }
      while (twf.getPlayers().size() < 10) {
        adapter.setOutput("Press Y to add more player, press any key to continue the game.\n");
        if (scan.nextLine().trim().toUpperCase().equals("Y")) {
          if (!addPlayer.execute(twf, adapter, scan)) {
            adapter.setOutput("Add player failure, please try again.\n");
          }
        } else {
          break;
        }
      }
      adapter.setOutput("Now, we can enter the creepy castle to begin our trip...\n");
      adapter.drawGraphics(true);
      while ((currentTurn <= turnLimit) && (twf.getTarget().getHealth() > 0) && gameContinue) {
        adapter.setOutput(
            "=====================================================" + "======================\n");
        adapter.setOutput("It is turn ");
        adapter.setOutput(Integer.toString(currentTurn));
        adapter.setOutput("\n");
        adapter.setOutput(String.format("Now is %s turn:\n", twf.getTurnOfGame().getName()));
        adapter.setMovable(false);
        if (twf.getTurnOfGame().getSpace() == null) {
          adapter.setOutput(String.format("you are not in any space right now\n"));

          Move m = new Move();
          while (!m.execute(twf, adapter, scan)) {
            adapter.setOutput("The input data is wrong, please check and reenter space"
                + " number and try again.\n");
          }
          currentTurn++;
        } else if (twf.getTurnOfGame().isAutomatic()) {
          try {
            Thread.sleep(300);
          } catch (InterruptedException e) {
            return;
          }
          adapter.setOutput(String.format("you are in the space %d %s\n",
              twf.getTurnOfGame().getSpace().getId(), twf.getTurnOfGame().getSpace().getName()));

          if (twf.getTurnOfGame().getSpace().getId() == twf.getTarget().getSpace().getId()) {
            adapter.setOutput(
                String.format("%s decide to attack target\n", twf.getTurnOfGame().getName()));
            if (!new Attempt().execute(twf, adapter, scan)) {
              adapter.setOutput("something wrong occurred, please try something else.\n");
            } else {
              if (twf.getTarget().getHealth() > 0) {
                twf.nextTurn();
                twf.moveTargetToNext();
                adapter.setOutput(
                    String.format("%s has already moved to No. %d %s\n", twf.getTarget().getName(),
                        twf.getTarget().getSpace().getId(), twf.getTarget().getSpace().getName()));
                if (twf.getPet() != null) {
                  twf.movePetToNext();
                  adapter.setOutput(
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
                adapter.setOutput(
                    String.format("%s decide to move to a space\n", twf.getTurnOfGame().getName()));
                if (new Move().execute(twf, adapter, scan)) {
                  this.currentTurn++;
                }
                break;
              }
              case 1: {
                adapter.setOutput(
                    String.format("%s decide to pickup an item\n", twf.getTurnOfGame().getName()));
                if (new Pickup().execute(twf, adapter, scan)) {
                  this.currentTurn++;
                }
                break;
              }
              case 2: {
                adapter.setOutput(String.format("%s decide to drop off an item to the space\n",
                    twf.getTurnOfGame().getName()));
                if (new DropOff().execute(twf, adapter, scan)) {
                  this.currentTurn++;
                }
                break;
              }
              case 3: {
                adapter.setOutput(
                    String.format("%s decide to look up around\n", twf.getTurnOfGame().getName()));
                if (new LookAround().execute(twf, adapter, scan)) {
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
          adapter.setOutput(String.format("you are in the space %d %s\n",
              twf.getTurnOfGame().getSpace().getId(), twf.getTurnOfGame().getSpace().getName()));
          while (true) {
            adapter.setOutput(String.format("Choose an action for %s, only press 1-10: "
                + "\n1.move to another space \n2.pickup an item in the space "
                + "\n3.dropoff an item to the space \n4.look around \n5.add a player "
                + "\n6.draw the map \n7.show a player \n8.show a space"
                + "\n9.move pet \n10.attempt to target \n", twf.getTurnOfGame().getName()));
            String temp = null;
            temp = scan.nextLine();
            if ((temp != null) && (!temp.toUpperCase().equals("Q")) && (temp.matches("^[0-9]+$"))) {
              try {
                switch (Integer.parseInt(temp)) {
                  case 1: {
                    Move m = new Move();
                    if (!m.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 2: {
                    Pickup p = new Pickup();
                    if (!p.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 3: {
                    DropOff drop = new DropOff();
                    if (!drop.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 4: {
                    LookAround l = new LookAround();
                    if (!l.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 5: {
                    AddPlayer ad = new AddPlayer();
                    if (!ad.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 6: {
                    DrawMap dm = new DrawMap();
                    if (!dm.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 7: {
                    ShowPlayer sp = new ShowPlayer();
                    if (!sp.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 8: {
                    ShowSpace ss = new ShowSpace();
                    if (!ss.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    }
                    break;
                  }
                  case 9: {
                    MovePet mp = new MovePet();
                    if (!mp.execute(twf, adapter, scan)) {
                      adapter.setOutput("something wrong occurred, please try something else.\n");
                    } else {
                      this.currentTurn++;
                    }
                    break;
                  }
                  case 10: {
                    if (twf.getTurnOfGame().getSpace().getId() == twf.getTarget().getSpace()
                        .getId()) {
                      Attempt at = new Attempt();
                      if (!at.execute(twf, adapter, scan)) {
                        adapter.setOutput(
                            "something wrong occurred, " + "please try something else.\n");
                      } else {
                        if (twf.getTarget().getHealth() != 0) {
                          twf.nextTurn();
                          twf.moveTargetToNext();
                          adapter.setOutput(String.format("%s has already moved to No. %d %s\n",
                              twf.getTarget().getName(), twf.getTarget().getSpace().getId(),
                              twf.getTarget().getSpace().getName()));
                          if (twf.getPet() != null) {
                            twf.movePetToNext();
                            adapter.setOutput(String.format("%s has already moved to No. %d %s\n",
                                twf.getPet().getName(), twf.getPet().getSpace().getId(),
                                twf.getPet().getSpace().getName()));
                          }
                          this.currentTurn++;
                        }
                      }
                      break;
                    } else {
                      adapter.setOutput("target is not in the space, try other options.\n");
                      break;
                    }
                  }
                  default: {
                    adapter.setOutput("wrong input, please try again.\n");
                    break;
                  }
                }
              } catch (IllegalStateException e) {
                adapter.setOutput("");
              }
              break;
            } else {
              adapter.setOutput("something wrong for input.\n");
            }
          }
        }
        adapter.drawGraphics(false);
      }
      if (!gameContinue) {
        return;
      }
      if (twf.getTarget().getHealth() == 0) {
        adapter.setOutput(
            String.format("%s win this game, game is over, Bye!\n", twf.getTurnOfGame().getName()));
        adapter.showResult(String.format("%s win this game, "
            + "game is over, Bye!\n", twf.getTurnOfGame().getName()));
      } else {
        adapter.setOutput("You are running out of turns, game is over, Bye!\n");
        adapter.showResult(String.format("Running out of turns,"
            + " game is over, Bye!\n", twf.getTurnOfGame().getName()));
      }
    } catch (IllegalStateException nse) {
      return;
    } catch (NoSuchElementException nse) {
      return;
    } catch (FileNotFoundException ffe) {
      adapter.setOutput(
          "There is a problem occurred during output, please restart game and try again.\n");
    } catch (IOException ioe) {
      adapter.setOutput(
          "There is a problem occurred during output, please restart game and try again.\n");
    }
  }
}
