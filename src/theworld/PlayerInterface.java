package theworld;

import java.util.List;

/**
 * This interface represents a player in the world game, a player has a name,
 * an ID. Also, it has ability to move to a particular space, pick up or drop
 * items, and to lookaround.
 */
public interface PlayerInterface {
  /**
   * Get the Player's name.
   * 
   * @return player's name
   */
  String getName();

  /**
   * Get the id of the player.
   * 
   * @return id number of the player
   */
  int getId();

  /**
   * Show the current space of the player's position.
   * 
   * @return the space that the player is current situated
   */
  Space getSpace();

  /**
   * Moves the player to the specified space.
   *
   * @param space the space to move the player to
   * @return true if the move was successful, false otherwise
   */
  boolean move(SpaceInterface space);

  /**
   * This method represents that the player pick up an item.
   * 
   * @param item the item that to be picked up by the player.
   * @return true if item is successfully picked up, otherwise false
   */
  boolean pickup(ItemInterface item);

  /**
   * This method represents that the player drops the item.
   * 
   * @param item that the player drops
   * @return true if item is successfully dropped, otherwise false
   */
  boolean leaveItemToTheSpace(ItemInterface item);
  
  /**
   * This method represents that remove the item from the player
   *  and prepare to move item to the evidence list.
   * 
   * @param item that the player drops
   * 
   */
  void removeItem(ItemInterface item);
  

  /**
   * The look around activity of the player.
   * 
   * @return provide the result of what the player see
   */
  String lookAround();
  
  /**
   * Retrieves the list of items in the space.
   * 
   * @return a list of items in the space
   */
  List<ItemInterface> getItems();
  
  /**
   * get the maximum items that the player can carry.
   * 
   * @return number of itemLimit
   */
  int getItemLimit();
  
  /**
   * check if the player is a robot.
   * 
   * @return true if it is a robot, otherwise false
   */
  boolean isAutomatic();
}
