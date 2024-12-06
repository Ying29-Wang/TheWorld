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
public interface IView extends KeyListener {
  /**
   * set adapter in the viewer.
   * 
   * @param adapter need to be set into viewer
   */
  void setAdapter(AdapterInterface adapter);

  /**
   * set shown message in the output panel.
   * 
   * @param string for setting the panel
   */
  void setOutputPanel(String s);

  /**
   * set shown message in the world panel.
   * 
   * @param string for setting the panel
   * 
   */
  void setWorldPanel(List<SpaceInterface> spaces, List<Player> list, CharacterInterface target,
      boolean redrawMap);

  /**
   * set user or target message in the info panel.
   * 
   * @param string for setting the panel
   * 
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
   * show game result in worldPanel.
   * 
   */
  void showResult(String s);
}
