
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import theworld.Item;
import theworld.Space;
import theworld.SpaceInterface;

/**
 * Test class for {@code Space}, including methods for creating spaces, 
 * managing items, and calculating neighbor spaces.
 */
public class SpaceTest {

  private Item item;
  private List<SpaceInterface> spaces;

  @Before
  public void createItem() {
    this.item = new Item(0, "mop", 5);
  }

  /**
   * Sets up a single {@code Item} object to be used in the tests.
   */
  @Before
  public void createSpaces() {
    this.spaces = new ArrayList<SpaceInterface>();
    this.spaces.add(new Space(0, "kitchen", new int[] { 10, 10 }, new int[] { 20, 20 }));
    this.spaces.add(new Space(1, "dining room", new int[] { 21, 15 }, new int[] { 26, 35 }));
    this.spaces.add(new Space(2, "living room", new int[] { 15, 21 }, new int[] { 20, 28 }));
    this.spaces.add(new Space(1, "gym", new int[] { 10, 29 }, new int[] { 18, 50 }));
    this.spaces.add(new Space(2, "cottage", new int[] { 26, 50 }, new int[] { 33, 55 }));
  }

  /**
   * normal space create.
   */
  @Test
  public void testSpace() {
    Space s = new Space(0, "kitchen", new int[] { 2, 5 }, new int[] { 10, 8 });
    assertEquals(s.toString(),
        "Space No.0 kitchen; upleft:2,5; downright:10,8;\nincludes 0 items\nit has 0 neighbors\n");
  }

  /**
   * illegal arg upleft = downright.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSpaceUpleftEqual() {
    new Space(0, "kitchen", new int[] { 15, 5 }, new int[] { 15, 8 });
  }

  /**
   * illegal arg upleft > downright.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSpaceUpleftBigger() {
    new Space(0, "kitchen", new int[] { 10, 11 }, new int[] { 15, 8 });
  }

  /**
   * test get name.
   */
  @Test
  public void testGetName() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    assertEquals(s.getName(), "kitchen");
  }

  /**
   * test get ID.
   */
  @Test
  public void testGetId() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    assertEquals(s.getId(), 0);
  }

  /**
   * test get up left.
   */
  @Test
  public void testGetUpLeft() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    assertEquals(s.getUpLeft()[0], 10);
    assertEquals(s.getUpLeft()[1], 5);

  }

  /**
   * test get low right.
   */
  @Test
  public void testGetLowRight() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    assertEquals(s.getLowRight()[0], 15);
    assertEquals(s.getLowRight()[1], 8);
  }

  /**
   * test set Item into the space.
   */
  @Test
  public void testSetItem() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    s.setItem(item);
    assertEquals(s.getItems().get(0).getName(), "mop");
  }

  /**
   * test set Item into the space ,replicated items,only 1 successfully insert.
   */
  @Test
  public void testSetItemReplicated() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    s.setItem(item);
    s.setItem(item);
    assertEquals(s.getItems().get(0).getName(), "mop");
    assertEquals(s.getItems().size(), 1);
  }

  /**
   * test remove Item into the space.
   */
  @Test
  public void testRemoveItem() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    s.setItem(item);
    assertEquals(s.getItems().get(0).getName(), "mop");
    s.removeItem(item);
    assertEquals(s.getItems().size(), 0);
  }

  /**
   * test get Items from the space.
   */
  @Test
  public void testGetItems() {
    Space s = new Space(0, "kitchen", new int[] { 10, 5 }, new int[] { 15, 8 });
    s.setItem(item);
    assertEquals(s.getItems().get(0).getName(), "mop");
  }

  /**
   * test get Neighbors from the space.
   */
  @Test
  public void testGetNeighbors() {
    ((Space) this.spaces.get(0)).calculateNeighbors(spaces);
    assertEquals(((Space) this.spaces.get(0)).getNeighbors().size(), 2);
    assertEquals(((Space) this.spaces.get(0)).getNeighbors().get(0).getName(), "dining room");
    assertEquals(((Space) this.spaces.get(0)).getNeighbors().get(1).getName(), "living room");
  }

  /**
   * test calculate Neighbors from the space ,multi conditions,include 0 neighbor.
   * 1 neighbor 2 neighbors
   */
  @Test
  public void testCalculateNeighbors() {
    ((Space) this.spaces.get(0)).calculateNeighbors(spaces);
    ((Space) this.spaces.get(1)).calculateNeighbors(spaces);
    ((Space) this.spaces.get(2)).calculateNeighbors(spaces);
    ((Space) this.spaces.get(3)).calculateNeighbors(spaces);
    ((Space) this.spaces.get(4)).calculateNeighbors(spaces);
    assertEquals(((Space) this.spaces.get(0)).getNeighbors().size(), 2);
    assertEquals(((Space) this.spaces.get(1)).getNeighbors().size(), 2);
    assertEquals(((Space) this.spaces.get(2)).getNeighbors().size(), 3);
    assertEquals(((Space) this.spaces.get(3)).getNeighbors().size(), 1);
    assertEquals(((Space) this.spaces.get(4)).getNeighbors().size(), 0);
  }

  @Test
  public void testToString() {
    ((Space) this.spaces.get(0)).calculateNeighbors(spaces);
    ((Space) this.spaces.get(0)).setItem(item);
    System.out.print(((Space) this.spaces.get(0)).toString());
    assertEquals(((Space) this.spaces.get(0)).toString(),
        "Space No.0 kitchen; upleft:10,10; downright:20,20;\n" + "includes 1 items\n"
            + "- mop, cause 5 damage\n" + "it has 2 neighbors\n"
            + "- Space No.1 dining room is a neighbor\n"
            + "- Space No.2 living room is a neighbor\n");

  }

}
