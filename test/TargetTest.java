
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import theworld.Space;
import theworld.Target;

/**
 * Test class for {@code Target}, including methods for creating targets,
 * moving them into spaces, and checking health and other attributes.
 */
public class TargetTest {

  private Space sp;

  @Before
  public void createSpace() {
    sp = new Space(0, "kitchen", new int[] { 10, 10 }, new int[] { 20, 20 });
  }

  /**
   * test normal target in a Space.
   */
  @Test
  public void testTarget() {
    Target t = new Target("Allen", 100);
    t.move(sp);
    assertEquals(t.toString(), "Allen with 100 health, and is staying at room kitchen");
  }

  /**
   * test health not greater than 0,expected exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTargetHealthNotGreater0() {
    Target t = new Target("Allen", 0);
  }

  /**
   * test getName function.
   */
  @Test
  public void testGetName() {
    Target t = new Target("Allen", 100);
    assertEquals(t.getName(), "Allen");
  }

  /**
   * test getSpace function.
   */
  @Test
  public void testGetSpace() {
    Target t = new Target("Allen", 100);
    t.move(sp);
    assertEquals(t.getSpace().toString(),
        "Space No.0 kitchen; upleft:10,10; downright:20,20;\n"
        + "includes 0 items\nit has 0 neighbors\n");

  }

  /**
   * test getSpace function, don't move into any space.
   */
  @Test
  public void testGetSpaceNoMove() {
    Target t = new Target("Allen", 100);
    assertEquals(t.getSpace(), null);
  }

  /**
   * test move function, set a space to the target.
   */
  @Test
  public void testMove() {
    Target t = new Target("Allen", 100);
    t.move(sp);
    assertEquals(t.getSpace().toString(),
        "Space No.0 kitchen; upleft:10,10; downright:20,20;\n"
        + "includes 0 items\nit has 0 neighbors\n");

  }

  /**
   * test getHealth function, set a space to the target.
   */
  @Test
  public void testGetHealth() {
    Target t = new Target("Allen", 100);
    assertEquals(t.getHealth(), 100);

  }

}
