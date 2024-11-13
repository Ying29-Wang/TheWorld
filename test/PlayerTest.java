import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import theworld.Item;
import theworld.ItemInterface;
import theworld.Player;
import theworld.Space;

/**
 * Unit tests for the Player class.
 */
public class PlayerTest {

  private Player player;
  private Space space;
  private ItemInterface item;

  /**
   * Sets up the test environment before each test.
   */
  @Before
  public void setUp() {
    space = new Space(1, "Kitchen", new int[] { 10, 10 }, new int[] { 20, 20 });
    item = new Item(1, "frank", 10);
    player = new Player(1, "Player1", 5, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidName() {
    new Player(1, null, 5, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidItemLimit() {
    new Player(1, "Player1", 0, false);
  }

  @Test
  public void testGetName() {
    assertEquals("Player1", player.getName());
  }

  @Test
  public void testGetId() {
    assertEquals(1, player.getId());
  }

  @Test
  public void testGetSpace() {
    assertNull(player.getSpace());
    player.move(space);
    assertEquals(space, player.getSpace());
  }

  @Test
  public void testMove() {
    assertTrue(player.move(space));
    assertEquals(space, player.getSpace());
  }

  @Test
  public void testMoveToNullSpace() {
    assertFalse(player.move(null));
  }

  @Test
  public void testIsAutomatic() {
    assertFalse(player.isAutomatic());
  }

  @Test
  public void testGetItemLimit() {
    assertEquals(5, player.getItemLimit());
  }

  @Test
  public void testGetItems() {
    List<ItemInterface> items = player.getItems();
    assertNotNull(items);
    assertTrue(items.isEmpty());
  }

  @Test
  public void testPickup() {
    player.move(space);
    space.setItem(item);
    assertTrue(player.pickup(item));
    assertTrue(player.getItems().contains(item));
    assertFalse(space.getItems().contains(item));
  }

  @Test
  public void testPickupExceedingLimit() {
    player.move(space);
    for (int i = 0; i < 5; i++) {
      space.setItem(new Item(i, "Item " + i, i + 1));
      player.pickup(new Item(i, "Item " + i, i + 1));
    }
    assertFalse(player.pickup(item));
  }

  @Test
  public void testLeaveItemToTheSpace() {
    player.move(space);
    player.pickup(item);
    assertTrue(player.leaveItemToTheSpace(item));
    assertFalse(player.getItems().contains(item));
    assertTrue(space.getItems().contains(item));
  }

  @Test
  public void testLookAround() {
    player.move(space);
    String lookAround = player.lookAround();
    assertTrue(lookAround.contains("this is the player Player1"));
    assertTrue(lookAround.contains("he/she is in the space No.1 Kitchen"));
  }

  @Test
  public void testToString() {
    player.move(space);
    player.pickup(item);
    String playerString = player.toString();
    assertTrue(playerString.contains("this is the player Player1"));
    assertTrue(playerString.contains("he/she is in the space No.1 Kitchen"));
    assertTrue(playerString.contains("he/she is carrying the item No.1 frank"));
  }
}