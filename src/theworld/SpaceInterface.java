package theworld;

import java.util.List;

/**
 * Represents a general interface for spaces, providing methods
 * to access the space's properties such as its name, ID, and boundary
 * coordinates.
 * </p>
 * Implementations of this interface should define how to retrieve the space's
 * name,
 * unique identifier, and the coordinates of the upper-left and lower-right
 * corners
 * that define the boundaries of the space.
 * 
 */
public interface SpaceInterface {
  /**
   * Provide the name of the space.
   *
   * @return the name of the space.
   */
  String getName();

  /**
   * Provide the space ID.
   *
   * @return the space's ID.
   */
  int getId();

  /**
   * Get the coordinates of the upper-left corner of the space.
   * The coordinates are returned as an array of two integers
   * 
   * @return An array of two integers representing the coordinates of the
   *         upper-left corner.
   */
  int[] getUpLeft();

  /**
   * Retrieves the coordinates of the lower-right corner of the space.
   * The coordinates are returned as an array of two integers
   *
   * @return An array of two integers representing the coordinates of the
   *         lower-right corner.
   */
  int[] getLowRight();

  /**
   * Retrieves if the room is visible.
   * 
   * @return if visible true else false
   */
  boolean isVisible();

  /**
   * Sets the visibility of the space.
   *
   * @param visible a boolean indicating whether the space should be visible
   *                (true) or not (false)
   */
  void setVisible(boolean visible);

  /**
   * Retrieves a list of players currently in the space.
   *
   * @return a list of Player objects representing the players in the space.
   */
  public List<Player> getPlayers();

  /**
   * Adds a player to the space.
   *
   * @param p the player to be added
   */
  public void addPlayer(Player p);

  /**
   * Removes the specified player from the list of players if the player is not
   * null.
   *
   * @param p the player to be removed
   */
  public void removePlayer(Player p);

  /**
   * Add an item to the space. If the item already exists, it will be replaced.
   * 
   * @param item to be added to the space
   */
  public void setItem(ItemInterface item);

  /**
   * Removes the specified item from the space.
   * 
   * @param item to be removed
   * @return true if the item was successfully removed
   */
  public boolean removeItem(ItemInterface item);

  /**
   * Retrieves the list of items in the space.
   * 
   * @return a list of items in the space
   */
  public List<ItemInterface> getItems();

  /**
   * Retrieves the list of neighboring spaces.
   * 
   * @return a list of the neighbors of the space
   */
  public List<SpaceInterface> getNeighbors();

  /**
   * Calculates and sets the neighboring spaces. If a space is adjacent to this
   * space horizontally or vertically, it is considered a neighbor.
   * 
   * @param spaces the list of all possible spaces to check for adjacency
   */
  public void calculateNeighbors(List<SpaceInterface> spaces);
}
