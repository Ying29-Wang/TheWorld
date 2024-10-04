package theworld;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TargetTest {

  private Space s;

  @Before
  public void createSpace() {
    s = new Space(0, "kitchen", new int[] { 10, 10 }, new int[] { 20, 20 });
  }

  /**
   * test normal target in a Space
   */
  @Test
  public void testTarget() {
    Target t = new Target("Allen", 100);
    t.move(s);
    assertEquals(t.toString(), "Allen with 100 health, and is staying at room kitchen");
  }

  /**
   * test health not greater than 0,expected exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTargetHealthNotGreater0() {
    Target t = new Target("Allen", 0);
    // t.move(s);
    // assertEquals(t.toString(),"Allen with 100 health,and is staying at kitchen");
  }

  /**
   * test getName function
   */
  @Test
  public void testGetName() {
    Target t = new Target("Allen", 100);
    assertEquals(t.getName(), "Allen");
  }

  /**
   * test getSpace function
   */
  @Test
  public void testGetSpace() {
    Target t = new Target("Allen", 100);
    t.move(s);
    assertEquals(t.getSpace().toString(),
        "Space No.0 kitchen ; upleft:10,10; downright：20,20;\nincludes 0 items\nit has 0 neighbors\n");

  }

  /**
   * test getSpace function, don't move into any space
   */
  @Test
  public void testGetSpaceNoMove() {
    Target t = new Target("Allen", 100);
    // t.move(s);
    assertEquals(t.getSpace(), null);
  }

  /**
   * test move function, set a space to the target
   */
  @Test
  public void testMove() {
    Target t = new Target("Allen", 100);
    t.move(s);
    // System.out.println(t.getSpace().toString());
    assertEquals(t.getSpace().toString(),
        "Space No.0 kitchen ; upleft:10,10; downright：20,20;\nincludes 0 items\nit has 0 neighbors\n");

  }

  /**
   * test getHealth function, set a space to the target
   */
  @Test
  public void testGetHealth() {
    Target t = new Target("Allen", 100);
    assertEquals(t.getHealth(), 100);

  }

}
