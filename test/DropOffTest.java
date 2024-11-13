import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.Player;
import theworld.TheWorldFacade;
import theworldcontroller.DropOff;
import theworldcontroller.Pickup;

/**
 * Test class for the DropOff command.
 */
public class DropOffTest {

  private DropOff dropOff;
  private Pickup pickup;
  private TheWorldFacade twf;
  private Appendable out;
  private Scanner scan;

  /**
   * Sets up the test environment before each test.
   * 
   * @throws FileNotFoundException if the sample input file is not found.
   */
  @Before
  public void setUp() throws FileNotFoundException {
    dropOff = new DropOff();
    pickup = new Pickup();
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));
    out = new StringBuilder();
    // out = System.out;
  }

  @Test
  public void testExecuteSuccessManual() {
    Player player = new Player(1, "Player1", 1, false);
    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    scan = new Scanner("4\n4\n");
    pickup.execute(twf, out, scan);
    // scan = new Scanner("4\n");
    boolean result = dropOff.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("The item has been dropped off by Player1."));
  }

  @Test
  public void testExecuteSuccessAutomatic() {
    Player player = new Player(1, "Player1", 1, true);
    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    scan = new Scanner("");
    pickup.execute(twf, out, scan);

    boolean result = dropOff.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("The item had been left to the space"));
  }

  @Test
  public void testExecuteNoItems() {
    Player player = new Player(1, "Player1", 1, false);
    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    scan = new Scanner("");
    boolean result = dropOff.execute(twf, out, scan);

    assertFalse(result);
    assertTrue(out.toString().contains("No item carried by Player1."));
  }

  @Test
  public void testExecuteInvalidItemId() {
    Player player = new Player(1, "Player1", 1, false);
    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    scan = new Scanner("4\n");
    pickup.execute(twf, out, scan);
    scan = new Scanner("10\n");
    boolean result = dropOff.execute(twf, out, scan);
    assertFalse(result);
    assertTrue(out.toString().contains("Wrong input, please re-enter space number."));
  }
}