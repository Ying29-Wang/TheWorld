
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import theworld.Item;
import theworld.ItemFactory;
import theworld.Space;
import theworld.SpaceInterface;
import theworld.Target;
import theworld.TheWorld;

/**
 * This test class is designed to verify the functionality of the 
 * {@code TheWorld} class. It includes tests for various aspects such as 
 * constructing the world, adding items and spaces, retrieving information, 
 * and validating target-related functions.
 */
public class TheWorldTest {
  private Item item1;
  private Item item2;
  private List<SpaceInterface> spaces;
  private Target target;

  /**
   * Setup method that initializes reusable objects such as items, spaces, and the target. 
   */
  @Before
  public void createUtils() {
    this.item1 = ItemFactory.createItem(0, "mop", 2);
    this.item2 = ItemFactory.createItem(1, "rifle", 50);
    this.target = new Target("Bryan", 100);
    this.spaces = new ArrayList<SpaceInterface>();
    this.spaces.add(new Space(0, "kitchen", new int[] { 10, 10 }, new int[] { 20, 20 }));
    this.spaces.add(new Space(1, "dining room", new int[] { 21, 15 }, new int[] { 26, 35 }));
    this.spaces.add(new Space(2, "living room", new int[] { 15, 21 }, new int[] { 20, 28 }));
    this.spaces.add(new Space(1, "gym", new int[] { 10, 29 }, new int[] { 18, 50 }));
    this.spaces.add(new Space(2, "cottage", new int[] { 26, 50 }, new int[] { 33, 55 }));

  }

  /**
   * normal case constructor.
   */
  @Test
  public void testTheWorld() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    assertEquals(tw.toString(), "100 100 Bryan's farmland\n");
  }

  /**
   * abnormal parameters row<=0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTheWorldLowerRow() {
    TheWorld tw = new TheWorld("Bryan's farmland", 0, 100);
  }

  /**
   * test getColumn function.
   */
  @Test
  public void testGetColumn() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    assertEquals(tw.getColumn(), 100);
  }

  /**
   * test getRow function.
   */
  @Test
  public void testGetRow() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    assertEquals(tw.getRow(), 100);
  }

  /**
   * test getName function.
   */
  @Test
  public void testGetName() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    assertEquals(tw.getName(), "Bryan's farmland");
  }

  /**
   * test getTarget function, add a target first and then call getTarget.
   */
  @Test
  public void testGetTarget() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addTarget(target);
    assertEquals(tw.getTarget().getName(), "Bryan");
  }

  /**
   * test addTarget function, add a target.
   */
  @Test
  public void testAddTarget() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addTarget(target);
    assertEquals(tw.getTarget().getName(), "Bryan");
  }

  /**
   * test addItem function, add an item.
   */
  @Test
  public void testAddItem() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addItem(item1);
    assertEquals(tw.getItems().get(0).getName(), "mop");
  }

  /**
   * test addItem function, add an item repeatly, expect only one insert successfully.
   */
  @Test
  public void testAddItemReplicated() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addItem(item1);
    tw.addItem(item1);
    assertEquals(tw.getItems().get(0).getName(), "mop");
    assertEquals(tw.getItems().size(), 1);
  }

  /**
   * test addSpace function, add 1 space then getSpace list.
   */
  @Test
  public void testAddSpace() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addSpace(this.spaces.get(0));
    assertEquals(tw.getSpaces().get(0).getName(), "kitchen");
  }

  /**
   * test addSpace function, add replicated space into the World,expect only one.
   * insert successfully
   */
  @Test
  public void testAddSpaceReplicated() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addSpace(this.spaces.get(0));
    tw.addSpace(this.spaces.get(0));
    assertEquals(tw.getSpaces().get(0).getName(), "kitchen");
    assertEquals(tw.getSpaces().size(), 1);

  }

  /**
   * test getItem function, add 2 items then getItem list.
   */
  @Test
  public void testGetItems() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addItem(item1);
    tw.addItem(item2);
    assertEquals(tw.getItems().get(0).getName(), "mop");
    assertEquals(tw.getItems().get(1).getName(), "rifle");
  }

  /**
   * test getSpaces function, add 2 spaces then getSpace list.
   */
  @Test
  public void testGetSpaces() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);
    tw.addSpace(this.spaces.get(0));
    tw.addSpace(this.spaces.get(1));
    assertEquals(tw.getSpaces().get(0).getName(), "kitchen");
    assertEquals(tw.getSpaces().get(1).getName(), "dining room");
  }

  /**
   * test toString function, add 2 spaces and 1 item and the target in the World.
   */
  @Test
  public void testToString() {
    TheWorld tw = new TheWorld("Bryan's farmland", 100, 100);

    tw.addTarget(target);
    tw.addItem(item1);

    tw.addSpace(this.spaces.get(2));
    tw.addSpace(this.spaces.get(3));
    assertEquals(tw.toString(), "100 100 Bryan's farmland\n" + "100 Bryan\n" + "includes 2 spaces\n"
        + "15 21 20 28 living room\n" + "10 29 18 50 gym\n" + "has 1 items\n" + "mop 2 damage\n");

  }

}
