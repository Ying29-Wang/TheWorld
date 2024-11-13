package theworld;

/**
 * Item interface containing the ID, name, damage amount and current position of this target.
 */
public interface ItemInterface {
  /**
   * Get the name of the item.
   * 
   * @return the item name
   */
  String getName();

  /**
   * Get the item ID.
   * 
   * @return item ID
   */
  int getId();

  /**
   * Get the space that this item is placed in.
   * 
   * @return {@link SpaceInterface}
   */
  SpaceInterface getSpace();
  
  /**
   * Get damage to the character of this item.
   * 
   * @return the damage number
   */
  int getDamage();
 
  /**
   * Set the item in a given space.
   * 
   * @param space give space that to set the item
   * @throws IllegalArgumentException Illegal space passed in
   */
  void setSpace(SpaceInterface space) throws IllegalArgumentException; 
}
