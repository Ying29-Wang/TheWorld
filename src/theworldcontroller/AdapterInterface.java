package theworldcontroller;

import java.io.IOException;
import java.util.Scanner;

/**
 * The AdapterInterface defines the methods required for controlling the game,
 * interacting with the input and output streams, and managing game state and player information.
 */
public interface AdapterInterface {
  /**
   * Sets the input with the specified string.
   *
   * @param s the input string to be set
   * @throws IOException if an I/O error occurs
   */
  void setInput(String s) throws IOException;

  /**
   * Sets the output with the specified string.
   *
   * @param s the string to be set as output
   */
  void setOutput(String s);

  /**
   * Retrieves the information of a player based on the provided player ID.
   *
   * @param playerId the unique identifier of the player whose information is to be retrieved
   * @return a String containing the player's information
   */
  String getPlayerInfo(int playerId);

  /**
   * Retrieves target information. 
   * 
   * @return string data for the target
   */
  String getTargetInfo();

  /**
   * Draws the graphics on the screen.
   *
   * @param redrawMap a boolean indicating whether the map should be redrawn.
   */
  void drawGraphics(boolean redrawMap);

  /**
   * Starts a new game with the given specification and turn limit.
   *
   * @param specification the game specification as a String
   * @param turnLimit the maximum number of turns allowed in the game
   * @throws IOException if an I/O error occurs during the game setup
   */
  void startNewGame(String specification, int turnLimit) throws IOException;

  /**
   * shut off the game by mandatory.
   * @throws IOException 
   */
  void shutOff() throws IOException;
  
  /**
   * Restarts the game with the given specifications and turn limits.
   *
   * @param spec the specifications for the game restart
   * @param turnLimits the maximum number of turns allowed in the game
   * @throws IOException if an I/O error occurs during the game restart
   */
  void restartGame(String spec, int turnLimits) throws IOException;
  
  /**
   * move a player to a space when set moveable true.
   *
   */
  void setMovable(boolean moveable);

  /**
   * Determines if the object is movable.
   *
   * @return true if the object can be moved, false otherwise.
   */
  boolean getMovable();
  
  /**
   * Displays the result.
   *
   * @param s the result string to be displayed
   */
  void showResult(String s);
}
