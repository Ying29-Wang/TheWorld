package theworld;

/**
 * Character Interface, provide the name, the current position and the move destination.
 */
public interface CharacterInterface {
  /**
   * Return the character name as a string.
   * @return the name of the character
   */
  String getName();

  /**
   * Get the {@link SpaceInterface} that the character is current staying.
   * @return the {@link SpaceInterface} that the character is current staying.
   */
  SpaceInterface getSpace();

  /**
   * Move the character to the {@link SpaceInterface} and return the space. 
   * @param space the target {@link SpaceInterface} that the character is going to
   * @return {@link SpaceInterface} 
   */
  SpaceInterface move(SpaceInterface space);
  
  /**
   * Get the health number of the character.
   * @return the integer number of the character
   */
  int getHealth();
  
  /**
   * set the health number of the character.
   * @return the integer number of the character
   */
  void setHealth(int health);
  
}
