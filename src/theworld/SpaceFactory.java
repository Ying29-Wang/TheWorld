package theworld;

/**
 * This class provides a factory method to create instances of space.
 * It encapsulates the object creation logic and handles 
 * exceptions during the instantiation process.
 */
public class SpaceFactory {

  /**
   * Constructs a new space.  
   * Since this class only contains static methods, no instances are required or used.
   */
  public SpaceFactory() {
  }

  /**
   * Creates a new space with the given parameters.
   * 
   * @param id        the id of the space
   * @param name      the name of the space
   * @param upleft    an array representing the upper-left corner coordinates of the space
   * @param downright an array representing the lower-right corner coordinates of the space
   * @return          a new space if the parameters are valid, or {@code null} if 
   *                  an {@code IllegalArgumentException} occurs
   */
  public static Space createSpace(int id, String name, int[] upleft, int[] downright) {
    try {
      return new Space(id, name, upleft, downright);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    return null;
  }

}
