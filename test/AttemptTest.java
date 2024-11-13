import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.Item;
import theworld.Player;
import theworld.TheWorldFacade;
import theworldcontroller.Attempt;


/**
 * Test class for the Attempt class.
 * This class contains unit tests to verify the behavior of the Attempt class
 * when executing attacks in various scenarios.
 */
public class AttemptTest {
  private Attempt at;
  private TheWorldFacade twf;
  private Appendable out;
  private Scanner scan;
  private Player player;

  /**
   * Sets up the test environment before each test.
   * 
   * @throws Exception if an error occurs during setup
   */
  @Before
  public void setUp() throws Exception {
    at = new Attempt();
    player = new Player(0, "Player1", 2, false);

    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "res/sample3.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(1));
    twf.getTurnOfGame();
    out = new StringBuilder();
  }

  /**
   * try to attack by player without item.
   */
  @Test
  public void testExecuteByNothing() {
    scan = new Scanner("");
    boolean result = at.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("him in the eye and hurt 1"));
  }

  /**
   * try to attack by player with item.
   */
  @Test
  public void testExecuteByItem() {
    player.pickup(new Item(0, "Bloodthirst Blade", 10));
    scan = new Scanner("0\n");
    boolean result = at.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("attacked by Bloodthirst Blade and cause 10 hurt"));
  }

  /**
   * try to attack by player choosing one item in user's item list.
   */
  @Test
  public void testExecuteByChoosingItem() {
    player.pickup(new Item(0, "Bloodthirst Blade", 10));
    player.pickup(new Item(1, "Vampire's Fang Dagger", 8));
    System.out.println(player.getItems().get(0).getName());
    scan = new Scanner("1\n");
    boolean result = at.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("attacked by Vampire's Fang Dagger and cause 8 hurt"));
  }

  /**
   * try to attack by player choosing invalid id of item.
   */
  @Test
  public void testExecuteByChoosingInvalidItem() {
    player.pickup(new Item(0, "Bloodthirst Blade", 10));
    player.pickup(new Item(1, "Vampire's Fang Dagger", 8));
    scan = new Scanner("2\n1\n");
    boolean result = at.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("Wrong input, please re-enter the item number."));
    assertTrue(out.toString().contains("attacked by Vampire's Fang Dagger and cause 8 hurt"));
  }

  /**
   * try to attack by a robot without item.
   * 
   */
  @Test
  public void testExecuteByRobotWithoutItem() {
    Player robot = new Player(1, "Player2", 2, true);
    boolean result = at.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("him in the eye and hurt 1"));
  }

}
