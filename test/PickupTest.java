
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.Player;
import theworld.Space;
import theworld.TheWorldFacade;
import theworldcontroller.Pickup;

/**
 * Test class for the Pickup functionality in TheWorld application.
 */
public class PickupTest {

  private Pickup pickup;
  private TheWorldFacade twf;
  private MockAdapter out;
  private Scanner scan;

  /**
   * Sets up the test environment before each test method is executed. Initializes
   * a new instance of the Pickup class and a StringBuilder for output.
   *
   * @throws FileNotFoundException if the file is not found during setup
   */
  @Before
  public void setUp() throws FileNotFoundException {
    pickup = new Pickup();
    out = new MockAdapter(new StringBuilder());

  }

  @Test
  public void testExecute_NoItemsInRoom() throws IOException {
    Player player = new Player(1, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(3));
    // scan = new Scanner("4\n");
    boolean result = pickup.execute(twf, out, scan);

    assertFalse(result);
    assertEquals("No item list in the room.\n", out.getOutput());
  }

  @Test
  public void testExecute_PlayerItemLimitReached() throws IOException {
    Player player = new Player(1, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(8));
    scan = new Scanner("0\n3\n");
    pickup.execute(twf, out, scan);
    boolean result = pickup.execute(twf, out, scan);

    assertFalse(result);
    assertTrue(
        out.getOutput().contains(String.format("%s can't have more items.\n", player.getName())));
  }

  @Test
  public void testExecute_ManualPickup() throws IOException {
    Player player = new Player(1, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(8));
    scan = new Scanner("3\n");

    boolean result = pickup.execute(twf, out, scan);

    assertTrue(result);
    assertEquals(((Space) twf.getSpaces().get(8)).getItems().size(), 1);
    assertTrue(out.getOutput()
        .contains(String.format("The item has been picked up by %s.\n", player.getName())));
  }

  @Test
  public void testExecute_AutomaticPickup() throws IOException {
    Player player = new Player(1, "Player1", 1, true);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(8));
    scan = new Scanner("");

    boolean result = pickup.execute(twf, out, scan);

    assertTrue(result);
    assertEquals(((Space) twf.getSpaces().get(8)).getItems().size(), 1);
    assertTrue(out.getOutput()
        .contains(String.format("The item had been picked up by %s.\n", player.getName())));
  }

  
}