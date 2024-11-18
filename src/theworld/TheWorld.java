package theworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a world consisting of multiple spaces and items. It
 * manages the layout of the spaces, items, and a target within the world. Each
 * world is defined by its name, number of rows, and number of columns.
 */
public class TheWorld implements MapInterface {
  private String name;
  private int row;
  private int column;
  private List<SpaceInterface> spaces;
  private List<ItemInterface> items;
  private List<ItemInterface> evidences;
  private List<Player> players;
  private Player turn;
  private Target target;
  private Pet pet;

  /**
   * Constructs a new world with the specified name, number of rows and columns.
   * 
   * @param name   the name of the world
   * @param row    the number of rows in the world
   * @param column the number of columns in the world
   * @throws IllegalArgumentException if row or column are non-positive or name is
   *                                  null
   */
  public TheWorld(String name, int row, int column) throws IllegalArgumentException {
    if (row > 0 && column > 0 && name != null) {
      this.name = name;
      this.row = row;
      this.column = column;
      this.spaces = new ArrayList<>();
      this.items = new ArrayList<>();
      this.players = new ArrayList<>();
      this.evidences = new ArrayList<>();
      this.turn = null;
      this.pet = null;
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

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Target getTarget() {
    return this.target;
  }

  @Override
  public Pet getPet() {
    return this.pet;
  }

  @Override
  public boolean addTarget(Target target) {
    if (target != null) {
      this.target = target;
      return true;
    }
    return false;
  }

  @Override
  public boolean addPet(PetInterface pet) {
    if (pet != null) {
      this.pet = (Pet) pet;
      return true;
    }
    return false;
  }

  @Override
  public boolean addItem(ItemInterface item) {
    if (item != null) {
      if (this.items.contains(item)) {
        this.getItems().remove(item);
      }
      this.items.add(item);
      return true;
    }
    return false;
  }

  @Override
  public void addItemToEvidence(ItemInterface item) {
    if (item != null) {
      this.getItems().remove(item);
      this.evidences.add(item);
    }
  }

  @Override
  public boolean addSpace(SpaceInterface space) {
    if (space != null) {
      if (this.getSpaces().size() > 0) {
        ((Space) space).calculateNeighbors(this.getSpaces());
        this.spaces.remove(space);
      }
      this.spaces.add(space);
      return true;
    }
    return false;
  }

  @Override
  public List<ItemInterface> getItems() {
    return this.items;
  }

  @Override
  public List<SpaceInterface> getSpaces() {
    return this.spaces;
  }

  @Override
  public List<Player> getPlayers() {
    return this.players;
  }

  @Override
  public boolean addPlayer(Player p) {
    if (p != null) {
      this.players.add(p);
      return true;
    }
    return false;
  }

  @Override
  public Player getTurn() {
    if (this.turn == null) {
      this.turn = this.players.get(0);
      return this.turn;
    }
    return this.turn;
  }

  @Override
  public List<ItemInterface> getEvidences() {
    if (this.evidences != null) {
      return this.evidences;
    }
    return null;
  }

  @Override
  public Player nextTurn() {
    if (this.players.get(this.players.size() - 1).getId() == this.turn.getId()) {
      this.turn = this.players.get(0);
    } else {
      this.turn = this.players.get(this.turn.getId() + 1);
    }
    return this.turn;
  }

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
            space.getUpLeft()[1], space.getLowRight()[0], space.getLowRight()[1], space.getName()));
      }
    }
    if (this.items.size() > 0) {
      basicInfo.append(String.format("has %d items\n", this.items.size()));
      for (ItemInterface it : this.items) {
        Item item = (Item) it;
        basicInfo.append(String.format("%s %d damage\n", item.getName(), item.getDamage()));
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
    return this.row == that.row && this.column == that.column
        && Objects.equals(this.name, that.name) && Objects.equals(this.target, that.target)
        && Objects.equals(this.spaces, that.spaces) && Objects.equals(this.items, that.items);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.column, this.row, this.name);
  }

}
