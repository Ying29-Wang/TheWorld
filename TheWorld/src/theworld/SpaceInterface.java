package theworld;

/**
 * Represents a general interface for spaces, providing methods 
 * to access the space's properties such as its name, ID, and boundary coordinates.
 * </p>
 * Implementations of this interface should define how to retrieve the space's name,
 * unique identifier, and the coordinates of the upper-left and lower-right corners 
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
  int getID();

  /**
   * Get the coordinates of the upper-left corner of the space.
   * The coordinates are returned as an array of two integers
   * @return An array of two integers representing the coordinates of the upper-left corner.
   */
  int[] getUpLeft();
  
  /**
   * Retrieves the coordinates of the lower-right corner of the space.
   * The coordinates are returned as an array of two integers
   *
   * @return An array of two integers representing the coordinates of the lower-right corner.
   */
  int[] getLowRight();
}
