package theworld;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a space in the world, defined by its unique ID, name, 
 * upper-left and lower-right coordinates, neighboring spaces, and the items it contains. 
 * This class provides methods to access and manipulate the space's attributes and calculate 
 * its neighbors based on spatial proximity.
 */
public class Space implements SpaceInterface {
  private String name;
  private List<SpaceInterface> neighbors;
  private List<ItemInterface> items;
  private int id;
  private int[] upleft;
  private int[] downright;

  /**
   * Constructs a new space with the given ID, name, and coordinates.
   * 
   * @param id        the id of the space
   * @param name      the name of the space
   * @param upleft    an array containing the x and y coordinates of the upper-left corner
   * @param downright an array containing the x and y coordinates of the lower-right corner
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
  public int[] getUpLeft() {
    return this.upleft;
  }

  @Override
  public int[] getLowRight() {
    return this.downright;
  }

  /**
   * Add an item to the space. If the item already exists, it will be replaced.
   * 
   * @param the item to be added to the space
   */
  public void setItem(ItemInterface item) {
    if (item != null) {
      if (this.items.size() > 0) {
        //delete the same item if it already exists in this space, to avoid duplicate
        this.items.remove((Item) item); 
      }
      this.items.add((Item) item);
    }
  }

  /**
   * Removes the specified item from the space.
   * 
   * @param the item to be removed
   * @return true if the item was successfully removed
   */
  public boolean removeItem(ItemInterface item) {
    if (item != null) {
      return this.items.remove((Item) item);
    }
    return false;
  }

  /**
   * Retrieves the list of items in the space.
   * 
   * @return a list of items in the space
   */
  public List<ItemInterface> getItems() {
    return this.items;
  }

  /**
   * Retrieves the list of neighboring spaces.
   * 
   * @return a list of the neighbors of the space
   */
  public List<SpaceInterface> getNeighbors() {
    return this.neighbors;
  }

  /**
   * Calculates and sets the neighboring spaces. 
   * If a space is adjacent to this space horizontally or vertically, it is considered a neighbor.
   * 
   * @param spaces the list of all possible spaces to check for adjacency
   */
  public void calculateNeighbors(List<SpaceInterface> spaces) {
    // to calculate neighbors of the space
    if (this.neighbors.size() > 0) {
      this.neighbors.clear();
    }
    for (SpaceInterface space : spaces) {
      if ((this.upleft[0] == ((Space) space).getLowRight()[0] + 1)
          && (((this.upleft[1] <= ((Space) space).getLowRight()[1])
              && (this.upleft[1] >= ((Space) space).getUpLeft()[1]))
              || ((this.downright[1] <= ((Space) space).getLowRight()[1])
                  && (this.downright[1] >= ((Space) space).getUpLeft()[1]))
              || ((((Space) space).getUpLeft()[1] >= this.upleft[1])
                  && (((Space) space).getUpLeft()[1] <= this.downright[1]))
              || ((((Space) space).getLowRight()[1] >= this.upleft[1])
                  && (((Space) space).getUpLeft()[1] <= this.downright[1])))) {
        this.neighbors.add(space);
        // System.out.print(1);
        ((Space) space).getNeighbors().remove(this);
        ((Space) space).getNeighbors().add(this);
      }
      if ((this.downright[0] == ((Space) space).getUpLeft()[0] - 1)
          && (((this.upleft[1] <= ((Space) space).getLowRight()[1])
              && (this.upleft[1] >= ((Space) space).getUpLeft()[1]))
              || ((this.downright[1] <= ((Space) space).getLowRight()[1])
                  && (this.downright[1] >= ((Space) space).getUpLeft()[1]))
              || ((((Space) space).getUpLeft()[1] >= this.upleft[1])
                  && (((Space) space).getUpLeft()[1] <= this.downright[1]))
              || ((((Space) space).getLowRight()[1] >= this.upleft[1])
                  && (((Space) space).getUpLeft()[1] <= this.downright[1])))) {
        this.neighbors.add(space);
        // System.out.print(2);
        ((Space) space).getNeighbors().remove(this);
        ((Space) space).getNeighbors().add(this);
      }
      if ((this.upleft[1] == ((Space) space).getLowRight()[1] + 1)
          && (((this.upleft[0] >= ((Space) space).getUpLeft()[0])
              && (this.upleft[0] <= ((Space) space).getLowRight()[0]))
              || ((this.downright[0] >= ((Space) space).getUpLeft()[0])
                  && (this.downright[0] <= ((Space) space).getLowRight()[0]))
              || ((((Space) space).getUpLeft()[0] >= this.upleft[0])
                  && (((Space) space).getUpLeft()[0] <= this.downright[0]))
              || ((((Space) space).getLowRight()[0] >= this.upleft[0])
                  && (((Space) space).getLowRight()[0] <= this.downright[0])))) {
        this.neighbors.add(space);
        // System.out.print(3);
        ((Space) space).getNeighbors().remove(this);
        ((Space) space).getNeighbors().add(this);
      }
      if ((this.downright[1] == ((Space) space).getUpLeft()[1] - 1)
          && (((this.upleft[0] >= ((Space) space).getUpLeft()[0])
              && (this.upleft[0] <= ((Space) space).getLowRight()[0]))
              || ((this.downright[0] >= ((Space) space).getUpLeft()[0])
                  && (this.downright[0] <= ((Space) space).getLowRight()[0]))
              || ((((Space) space).getUpLeft()[0] >= this.upleft[0])
                  && (((Space) space).getUpLeft()[0] <= this.downright[0]))
              || ((((Space) space).getLowRight()[0] >= this.upleft[0])
                  && (((Space) space).getLowRight()[0] <= this.downright[0])))) {
        this.neighbors.add(space);
        // System.out.print(4);
        ((Space) space).getNeighbors().remove(this);
        ((Space) space).getNeighbors().add(this);
      }
    }
  }


  /**
   * Returns a string representation of the space, including its ID, name, coordinates, 
   * the items it contains, and its neighboring spaces.
   * 
   * @return a string describing the space
   */
  @Override
  public String toString() {
    StringBuffer basicInfo = new StringBuffer();
    basicInfo.append(String.format("Space No.%d %s ; upleft:%d,%d; downright：%d,%d;\n", this.id,
        this.name, this.upleft[0], this.upleft[1], this.downright[0], this.downright[1]));
    basicInfo.append(String.format("includes %d items\n", this.items.size()));
    // basicInfo+=new String().format(, null)
    for (int i = 0; i < this.items.size(); i++) {
      basicInfo.append(String.format("includes %s ,cause %d hurt\n",
          ((Item) this.items.get(i)).getName(), ((Item) this.items.get(i)).getDamage()));
    }
    basicInfo.append(String.format("it has %d neighbors\n", this.neighbors.size()));
    // basicInfo+=new String().format(, null)
    for (int j = 0; j < this.neighbors.size(); j++) {
      basicInfo.append(String.format(" Space No.%d %s is a neighbor\n",
          ((Space) this.neighbors.get(j)).getID(), ((Space) this.neighbors.get(j)).getName()));
    }
    return basicInfo.toString();
  }

}
