package theworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents player which implements the PlayerInterface.
 */
public class Player implements PlayerInterface {
  private int id;
  private String name;
  private int itemLimit;
  private Space space;
  private List<ItemInterface> items;
  private boolean isAutomatic;

  /**
   * Constructor of the Player.
   * 
   * @param id the player's id number
   * @param name player's name
   * @param itemLimit the maximum of items that a player can have
   * @param isAutomatic represents that if the player is a computer controlled or not
   * @throws IllegalArgumentException illegal input exception
   */
  public Player(int id, String name, int itemLimit, boolean isAutomatic)
      throws IllegalArgumentException {
    if ((name != null) && (itemLimit > 0)) {
      this.id = id;
      this.name = name;
      this.itemLimit = itemLimit;
      this.isAutomatic = isAutomatic;
      this.items = new ArrayList<ItemInterface>();
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  public int getId() {
    return this.id;
  }

  @Override
  public Space getSpace() {
    return this.space;
  }

  @Override
  public boolean move(SpaceInterface space) {
    if (space != null) {
      if (this.space != null) {
        this.space.getPlayers().remove(this);
      }
      ((Space) space).addPlayer(this);
      this.space = (Space) space;
      return true;
    }
    return false;
  }

  /**
   * check if the player is a robot.
   * 
   * @return true if it is a robot, otherwise false
   */
  public boolean isAutomatic() {
    return isAutomatic;
  }

  /**
   * get the maximum items that the player can carry.
   * 
   * @return number of itemLimit
   */
  public int getItemLimit() {
    return itemLimit;
  }

  /**
   * get the list of items that taken by the player.
   * 
   * @return Item List
   */
  public List<ItemInterface> getItems() {
    return this.items;
  }

  @Override
  public boolean pickup(ItemInterface item) {
    if ((this.items.size() < this.itemLimit) && (item != null)) {
      this.items.add(item);
      this.space.removeItem(item);
      return true;
    }
    return false;
  }

  @Override
  public boolean leaveItemToTheSpace(ItemInterface item) {
    if ((item != null) && (this.items.remove(item))) {
      this.space.getItems().add(item);
      return true;
    }
    return false;
  }

  @Override
  public String lookAround() {
    StringBuffer sb = new StringBuffer();
    sb.append(String.format("this is the player %s, he/she is in the space No.%d %s\n", this.name,
        this.space.getId(), this.space.getName()));
    for (SpaceInterface s : this.space.getNeighbors()) {
      sb.append("now he/she is watching the space:\n");
      sb.append(((Space) s).toString());
    }
    return sb.toString();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(String.format("this is the player %s, he/she is in the space No.%d %s\n", this.name,
        this.space.getId(), this.space.getName()));
    for (ItemInterface i : this.items) {
      sb.append(String.format("he/she is carrying the item No.%d %s\n", ((Item) i).getId(),
          ((Item) i).getName()));
    }
    return sb.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof PlayerInterface)) {
      return false;
    }

    Player that = (Player) o;
    return this.id == that.id
        && Objects.equals(this.name, that.name)
        && Objects.equals(this.itemLimit, that.itemLimit)
        && Objects.equals(this.isAutomatic, that.isAutomatic);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.itemLimit, this.isAutomatic);
  }
}

