package theworldviewer;

import java.awt.event.KeyListener;
import java.util.List;
import theworld.CharacterInterface;
import theworld.Player;
import theworld.SpaceInterface;
import theworldcontroller.AdapterInterface;

/**
 * The IView interface provides methods for setting and clearing messages in
 * different panels of the viewer, as well as showing game results. It extends
 * the KeyListener interface.
 */
public interface MyView extends KeyListener {
  /**
   * set adapter in the viewer.
   * 
   * @param adapter need to be set into viewer
   */
  void setAdapter(AdapterInterface adapter);


  /**
   * Sets the output panel with the specified string.
   *
   * @param s the string to be displayed in the output panel
   */
  void setOutputPanel(String s);


  /**
   * Sets the world panel with the given parameters.
   *
   * @param spaces a list of SpaceInterface objects representing the spaces in the world
   * @param list a list of Player objects representing the players in the world
   * @param target a CharacterInterface object representing the target character
   * @param redrawMap a boolean indicating whether the map should be redrawn
   */
  void setWorldPanel(List<SpaceInterface> spaces, List<Player> list, CharacterInterface target,
      boolean redrawMap);


  /**
   * Sets the information panel with the provided string.
   *
   * @param s the string to be displayed in the information panel
   */
  void setInfoPanel(String s);

  /**
   * clear the message shown in the info panel.
   * 
   */
  void clearInfoPanel();

  /**
   * clear the message shown in the world panel.
   * 
   */
  void clearWorldPanel();

  /**
   * clear the message shown in the output panel.
   * 
   */
  void clearOutputPanel();


  /**
   * Displays the result in the view.
   *
   * @param s the result string to be displayed
   */
  void showResult(String s);
}
