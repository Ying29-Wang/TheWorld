package theworld;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a space in the world, defined by its unique ID, name,
 * upper-left and lower-right coordinates, neighboring spaces, and the items it
 * contains. This class provides methods to access and manipulate the space's
 * attributes and calculate its neighbors based on spatial proximity.
 */
public class Space implements SpaceInterface {
  private String name;
  private List<SpaceInterface> neighbors;
  private List<ItemInterface> items;
  private List<Player> players;
  private int id;
  private int[] upleft;
  private int[] downright;
  private boolean visible;

  /**
   * Constructs a new space with the given ID, name, and coordinates.
   * 
   * @param id        the id of the space
   * @param name      the name of the space
   * @param upleft    an array containing the x and y coordinates of the
   *                  upper-left corner
   * @param downright an array containing the x and y coordinates of the
   *                  lower-right corner
   * @throws IllegalArgumentException if the coordinates are invalid
   */
  public Space(int id, String name, int[] upleft, int[] downright) throws IllegalArgumentException {
    if ((upleft[0] >= 0) && (upleft[1] >= 0) && (downright[0] > upleft[0])
        && (downright[1] > upleft[1])) {
      this.id = id;
      this.name = name;
      this.upleft = upleft;
      this.downright = downright;
      this.neighbors = new ArrayList<>();
      this.items = new ArrayList<>();
      this.players = new ArrayList<>();
      this.visible = true;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public int[] getUpLeft() {
    return this.upleft;
  }

  @Override
  public int[] getLowRight() {
    return this.downright;
  }

  @Override
  public boolean isVisible() {
    return this.visible;
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public void setItem(ItemInterface item) {
    if (item != null) {
      if (this.items.size() > 0) {
        this.items.remove((Item) item);
      }
      this.items.add((Item) item);
    }
  }

  @Override
  public boolean removeItem(ItemInterface item) {
    if (item != null) {
      return this.items.remove((Item) item);
    }
    return false;
  }

  @Override
  public List<ItemInterface> getItems() {
    return this.items;
  }

  @Override
  public List<Player> getPlayers() {
    return this.players;
  }

  @Override
  public void addPlayer(Player p) {
    if (p != null) {
      this.players.remove(p);
      this.players.add(p);
    }
  }

  @Override
  public void removePlayer(Player p) {
    if (p != null) {
      this.players.remove(p);
    }
  }

  @Override
  public List<SpaceInterface> getNeighbors() {
    return this.neighbors;
  }

  /**
   * Checks if two spaces are neighbors based on their coordinates.
   * 
   * @param space1 The first space
   * @param space2 The second space
   * @return true if the spaces are adjacent, false otherwise
   */
  private boolean isNeighbor(SpaceInterface space1, SpaceInterface space2) {
    int[] upLeft1 = ((Space) space1).getUpLeft();
    int[] lowRight1 = ((Space) space1).getLowRight();
    int[] upLeft2 = ((Space) space2).getUpLeft();
    int[] lowRight2 = ((Space) space2).getLowRight();

    // Check horizontal adjacency
    boolean horizontalAdjacent = (lowRight1[0] + 1 == upLeft2[0] || lowRight2[0] + 1 == upLeft1[0])
        && (upLeft1[1] <= lowRight2[1] && lowRight1[1] >= upLeft2[1]);

    // Check vertical adjacency
    boolean verticalAdjacent = (lowRight1[1] + 1 == upLeft2[1] || lowRight2[1] + 1 == upLeft1[1])
        && (upLeft1[0] <= lowRight2[0] && lowRight1[0] >= upLeft2[0]);

    return horizontalAdjacent || verticalAdjacent;
  }

  @Override
  public void calculateNeighbors(List<SpaceInterface> spaces) {
    this.neighbors.clear(); // Clear current neighbors

    for (SpaceInterface space : spaces) {
      if (space != this && isNeighbor(this, space)) {
        this.neighbors.add(space); // Add space to this space's neighbor list
        ((Space) space).getNeighbors().remove(this); // Ensure no duplicates
        ((Space) space).getNeighbors().add(this); // Add this space as a neighbor to the other
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder basicInfo = new StringBuilder();

    // add basic information
    basicInfo.append(String.format("Space No.%d %s; upleft:%d,%d; downright:%d,%d;\n", this.id,
        this.name, this.upleft[0], this.upleft[1], this.downright[0], this.downright[1]));

    for (Player p : this.getPlayers()) {
      basicInfo.append(String.format("Player %s is in the space\n", p.getName()));
    }
    // add items information
    basicInfo.append(String.format("this space includes %d items\n", this.items.size()));
    for (ItemInterface itemElement : this.items) {
      Item item = (Item) itemElement;
      basicInfo.append(String.format("- %s, cause %d damage\n", item.getName(), item.getDamage()));
    }

    // add neighbors information
    basicInfo.append(String.format("it has %d neighbors\n", this.neighbors.size()));
    for (SpaceInterface neighborElement : this.neighbors) {
      Space neighbor = (Space) neighborElement;
      basicInfo.append(
          String.format("- Space No.%d %s is a neighbor\n", neighbor.getId(), neighbor.getName()));
    }

    return basicInfo.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof SpaceInterface)) {
      return false;
    }

    Space that = (Space) o;
    return this.id == that.id && Objects.equals(this.name, that.name)
        && Objects.equals(this.upleft, that.upleft)
        && Objects.equals(this.downright, that.downright);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.downright, this.upleft);
  }
}
