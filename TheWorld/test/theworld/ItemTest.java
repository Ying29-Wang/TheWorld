package theworld;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {

  /**
   * test normal case of constructor
   */
  @Test
  public void testItem() {
    Item it = new Item(0, "frank", 10);
    assertEquals(it.toString(), "0, frank, 10");
  }

  /**
   * test abnormal case of constructor,damage<0
   */
  @Test(expected = IllegalArgumentException.class)
  public void testItemDamageNotGreaterThan0() {
    Item it = new Item(0, "frank", 0);
    // assertEquals(it.toString(),"0,frank,10");
  }

  @Test
  public void testGetName() {
    Item it = new Item(0, "frank", 10);
    assertEquals(it.getName(), "frank");
  }

  @Test
  public void testGetID() {
    Item it = new Item(0, "frank", 10);
    assertEquals(it.getID(), 0);
  }

  @Test
  public void testGetSpace() {
    Item it = new Item(0, "frank", 10);
    it.setSpace(new Space(0, "kitchen", new int[] { 2, 5 }, new int[] { 10, 8 }));
    // System.out.println(it.getSpace().toString());
    assertEquals(it.getSpace().toString(),
        "Space No.0 kitchen ; upleft:2,5; downright：10,8;\nincludes 0 items\nit has 0 neighbors\n");
  }

  @Test
  public void testGetDamage() {
    Item it = new Item(0, "frank", 10);
    assertEquals(it.getDamage(), 10);

  }

  @Test
  public void testSetSpace() {
    Item it = new Item(0, "frank", 10);
    it.setSpace(new Space(0, "kitchen", new int[] { 2, 5 }, new int[] { 10, 8 }));
    // System.out.println(it.getSpace().toString());
    assertEquals(it.getSpace().toString(),
        "Space No.0 kitchen ; upleft:2,5; downright：10,8;\nincludes 0 items\nit has 0 neighbors\n");
  }

}
