package theworld;

import java.util.List;

public interface MapInterface {

  /**
   * return the total columns of the map
   */
  int getColumn();

  /**
   * get the total rows of the map
   */
  int getRow();

  /**
   * get the collection of spaces that compose of the map
   */
  List<SpaceInterface> getSpaces();
}
