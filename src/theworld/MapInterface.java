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
   * Returns the name of the world.
   * 
   * @return the name of the world
   */
  String getName();
  
  /**
   * Returns the target within the world.
   * 
   * @return the target in the world
   */
  Target getTarget();
  
  /**
   * Returns the pet within the world.
   * 
   * @return the pet in the world
   */
  Pet getPet();
  
  /**
   * Add a target to the world.
   * 
   * @param target the target to be added to the world
   * @return True if added successfully
   */
  boolean addTarget(Target target);
  
  /**
   * Add a pet to the world.
   * 
   * @param pet the pet to be added to the world
   * @return True if added successfully
   */
  boolean addPet(PetInterface pet);
  
  /**
   * Adds an item to the world. If the item already exists, it will be replaced.
   * 
   * @param item the item to be added to the world
   * @return true if add item successfully
   */
  boolean addItem(ItemInterface item);
  
  /**
   * Adds an item to the evidence list. If the item already exists, it will be replaced.
   * 
   * @param item the item to be added to the evidence list
   */
  void addItemToEvidence(ItemInterface item);
  
  /**
   * Adds a space to the world and calculates its neighbors based on the existing
   * spaces.
   * 
   * @param space the space to be added to the world
   * 
   * @return true if add space successfully
   */
  boolean addSpace(SpaceInterface space);
  
  /**
   * Returns a list of all items in the world.
   * 
   * @return the list of items
   */
  List<ItemInterface> getItems();

  /**
   * Get all the spaces in a list in the map.
   * @return the collection of spaces that compose of the map
   */
  List<SpaceInterface> getSpaces();
  
  /**
   * Returns a list of all players in the world.
   * 
   * @return the list of players
   */
  List<Player> getPlayers();
  
  /**
   * Adds a player to the list of players if the player is not null.
   *
   * @param p the player to be added
   * @return true if the player was successfully added, false if the player is
   *         null
   */
  boolean addPlayer(Player p);
  
  /**
   * Returns current player in the world.
   * 
   * @return the current turn of player
   */
  Player getTurn();
  
  /**
   * Retrieves the list of evidences.
   *
   * @return a list of ItemInterface objects representing the evidences if they exist,
   *         otherwise returns null.
   */
  List<ItemInterface> getEvidences();
  
  /**
   * Returns next player in the world.
   * 
   * @return the next turn of player
   */
  Player nextTurn();
}
