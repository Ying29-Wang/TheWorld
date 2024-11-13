package theworld;

/**
 * The PetInterface defines the methods that any pet character must implement.
 * It includes methods to get the pet's name, the current space it is in, and to
 * move the pet to a new space.
 */
public interface PetInterface {
  /**
   * Return the character name as a string.
   * 
   * @return the name of the character
   */
  String getName();

  /**
   * Get the {@link SpaceInterface} that the character is current staying.
   * 
   * @return the {@link SpaceInterface} that the character is current staying.
   */
  SpaceInterface getSpace();

  /**
   * Move the character to the {@link SpaceInterface} and return the space.
   * 
   * @param space the target {@link SpaceInterface} that the character is going to
   * @return {@link SpaceInterface}
   */
  boolean move(SpaceInterface space);
}
