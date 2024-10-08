package theworld;

/**
 * This is a factory class for creating instances of {@link Item}.
 * This class provides a method to create items with given values like
 * ID, name, and damage value. If the parameters provided are invalid, the method 
 * will catch an {@link IllegalArgumentException} and return {@code null}.
 */
public class ItemFactory {

  /**
   * Default constructor for {@code ItemFactory}.
   * This constructor is not intended to be called as {@code ItemFactory} only 
   * contains static methods.
   */
  public ItemFactory() {
  }

  /**
   * Creates an {@link Item} with the given ID, name, and damage value.
   * If the provided parameters are invalid, this method catches an 
   * {@link IllegalArgumentException} and returns {@code null}.
   *
   * @param id     The unique id for the item.
   * @param name   The name of the item.
   * @param damage The damage value of the item.
   * @return A new instance of {@link Item}, or {@code null} if an exception occurs.
   * @throws IllegalArgumentException if the {@link Item} constructor throws an exception.
   */
  public static Item createItem(int id, String name, int damage) {
    try {
      return new Item(id, name, damage);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    return null;
  }

}
