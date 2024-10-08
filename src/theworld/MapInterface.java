package theworld;

import java.util.List;

/**
 * This interface represents a blueprint for a map. 
 * It defines the methods that provide its dimensions and the collection of spaces
 */
public interface MapInterface {

  /**
   * Get the total column number of the map.
   * @return the total columns of the map
   */
  int getColumn();

  /**
   * Get the total row number of the map.
   * @return the total rows of the map
   */
  int getRow();

  /**
   * Get all the spaces in a list in the map.
   * @return the collection of spaces that compose of the map
   */
  List<SpaceInterface> getSpaces();
}
