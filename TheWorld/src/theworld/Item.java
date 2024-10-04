package theworld;

/**
 * This class represent an item that contains a name, an id, its damage value.
 * Also it provide the current space.
 */
public class Item implements ItemInterface {
  private String name;
  private Space space;
  private int damage;
  private int id;

  /**
   * Construct an item with a given id, name and its damage value.
   * @param id Item id
   * @param name Item name
   * @param damage the damage value of the item
   * @throws IllegalArgumentException throw illegal input exception
   */
  public Item(int id, String name, int damage) throws IllegalArgumentException {
    if  ((damage > 0) && (!name.equals(null))) {
      this.id = id;
      this.name = name;
      this.damage = damage;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getID() {
    return this.id;
  }

  @Override
  public Space getSpace() {
    return this.space;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
  
  /**
   * Set the item in a given space.
   * @param space give space that to set the item
   * @throws IllegalArgumentException Illegal space passed in
   */
  public void setSpace(SpaceInterface space) throws IllegalArgumentException {
    if ((space != null) && (space instanceof Space)) {
      this.space = (Space) space;
    } else {
      throw new IllegalArgumentException();
    }
  }
  
  public String toString() {
    return String.format("%d, %s, %d", this.id, this.name, this.damage);
  }

}
