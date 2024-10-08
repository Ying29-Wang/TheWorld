package theworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a world consisting of multiple spaces and items.
 * It manages the layout of the spaces, items, and a target within the world.
 * Each world is defined by its name, number of rows, and number of columns.
 */
public class TheWorld implements MapInterface {
  private String name;
  private int row;
  private int column;
  private List<SpaceInterface> spaces = new ArrayList<>();
  private List<ItemInterface> items = new ArrayList<>();
  private Target target;

  /**
   * Constructs a new world with the specified name, number of rows and columns. 
   * 
   * @param name   the name of the world
   * @param row    the number of rows in the world
   * @param column the number of columns in the world
   * @throws IllegalArgumentException if row or column are non-positive or name is null
   */
  public TheWorld(String name, int row, int column) throws IllegalArgumentException {
    if (row > 0 && column > 0 && name != null) {
      this.name = name;
      this.row = row;
      this.column = column;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int getColumn() {
    return this.column;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  /**
   * Returns the name of the world.
   * 
   * @return the name of the world
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the target within the world.
   * 
   * @return the target in the world
   */
  public Target getTarget() {
    return this.target;
  }

  /**
   * Add a target to the world.
   * 
   * @param target the target to be added to the world
   */
  public void addTarget(Target target) {
    this.target = target;
  }

  /**
   * Adds an item to the world. If the item already exists, it will be replaced.
   * 
   * @param item the item to be added to the world
   */
  public void addItem(ItemInterface item) {
    if (this.items.contains(item)) {
      this.getItems().remove(item);
    }
    this.items.add(item);
  }

  /**
   * Adds a space to the world and calculates its neighbors based on the existing spaces.
   * 
   * @param space the space to be added to the world
   */
  public void addSpace(SpaceInterface space) {
    if (this.getSpaces().size() > 0) {
      ((Space) space).calculateNeighbors(this.getSpaces());
      this.spaces.remove(space);
    }
    this.spaces.add(space);
  }

  /**
   * Returns a list of all items in the world.
   * 
   * @return the list of items
   */
  public List<ItemInterface> getItems() {
    return this.items;
  }

  @Override
  public List<SpaceInterface> getSpaces() {
    return this.spaces;
  }

  /**
   * Returns a string representation of the world, including its name, target, spaces, and items.
   * 
   * @return a string describing the world
   */
  @Override
  public String toString() {
    StringBuilder basicInfo = new StringBuilder();
    if (this.name != null) {
      basicInfo.append(String.format("%d %d %s\n", this.row, this.column, this.name));
    }
    if (this.target != null) {
      basicInfo.append(String.format("%d %s\n", target.getHealth(), target.getName()));
    }
    if (this.spaces.size() > 0) {
      basicInfo.append(String.format("includes %d spaces\n", this.spaces.size()));
      for (SpaceInterface sp : this.spaces) {
        Space space = (Space) sp;
        basicInfo.append(String.format("%d %d %d %d %s\n", space.getUpLeft()[0],
            space.getUpLeft()[1], space.getLowRight()[0],
            space.getLowRight()[1], space.getName()));
      }
    }
    if (this.items.size() > 0) {
      basicInfo.append(String.format("has %d items\n", this.items.size()));
      for (ItemInterface it : this.items) {
        Item item = (Item) it;
        basicInfo.append(
            String.format("%s %d damage\n", item.getName(), item.getDamage()));
      }
    }
    return basicInfo.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { 
      return true;
    }

    if (!(o instanceof MapInterface)) {
      return false;
    }

    TheWorld that = (TheWorld) o; 
    return this.row == that.row 
        && this.column == that.column 
        && Objects.equals(this.name, that.name) 
        && Objects.equals(this.target, that.target) 
        && Objects.equals(this.spaces, that.spaces) 
        && Objects.equals(this.items, that.items);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.column, this.row, this.name);
  }
}
