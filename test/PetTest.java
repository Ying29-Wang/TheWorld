import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import theworld.Pet;
import theworld.Space;

/**
 * Test class for the Pet class.
 */
public class PetTest {
  Space space;

  /**
   * Sets up the test environment before each test.
   * Initializes a Space object with specific parameters.
   */
  @Before
  public void setUp() {
    space = new Space(1, "Kitchen", new int[] { 10, 10 }, new int[] { 20, 20 });

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidName() {
    new Pet(null);
  }

  @Test
  public void testGetName() {
    Pet p = new Pet("petsname");
    assertEquals(p.getName(), "petsname");
  }

  @Test
  public void testGetSpace() {
    Pet p = new Pet("petsname");
    p.move(space);
    assertEquals(p.getSpace().getName(), "Kitchen");
  }

  @Test
  public void testMove() {
    Pet p = new Pet("petsname");
    p.move(space);
    assertEquals(p.getSpace().getName(), "Kitchen");
  }

}
