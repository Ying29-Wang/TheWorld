package theworldcontroller;

import java.io.IOException;
import java.util.Scanner;

/**
 * The AdapterInterface defines the methods required for controlling the game,
 * interacting with the input and output streams, and managing game state and player information.
 */
public interface AdapterInterface {
  /**
   * Provide the control signals to the input scanner.
   *
   * @throws IOException  
   */
  void setInput(String s) throws IOException;

  /**
   * set output stream of the controller , pass msg to viewer.
   *
   */
  void setOutput(String s);

  /**
   * Retrieves the specified player information. 
   * 
   * @param player id
   * @return string data for the player 
   */
  String getPlayerInfo(int playerId);

  /**
   * Retrieves target information. 
   * 
   * @return string data for the target
   */
  String getTargetInfo();

  /**
   * draw the map in the viewer.
   *
   */
  void drawGraphics(boolean redrawMap);

  /**
   * start a new game by passing a specification.
   * 
   * @throws IOException
   *
   */
  void startNewGame(String specification, int turnLimit) throws IOException;

  /**
   * shut off the game by mandatory.
   * @throws IOException 
   *
   */
  void shutOff() throws IOException;
  
  /**
   * restart the game by mandatory.
   * @throws IOException 
   *
   */
  void restartGame(String spec, int turnLimits) throws IOException;
  
  /**
   * move a player to a space when set moveable true.
   *
   */
  void setMovable(boolean moveable);

  /**
   * check if can a player move right now.
   *
   */
  boolean getMovable();
  
  /**
   * pass result message to the viewer.
   *
   */
  void showResult(String s);
  

}
