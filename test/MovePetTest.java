
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.Player;
import theworld.TheWorldFacade;
import theworldcontroller.MovePet;

/**
 * This class contains unit tests for the MovePet class.
 */
public class MovePetTest {

  private MovePet mp;
  private TheWorldFacade twf;
  private Appendable out;
  private Scanner scan;
  private Player player;

  /**
   * Sets up the test environment before each test case runs.
   * 
   * @throws Exception if an error occurs during setup.
   */
  @Before
  public void setUpBeforeClass() throws IOException {
    mp = new MovePet();
    player = new Player(1, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader("res/sample3.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(1));
    out = new StringBuilder();
  }

  @Test
  public void testExecute() {
    twf.getTurnOfGame();
    scan = new Scanner("1\n");
    boolean result = mp.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("has already moved to No. 1"));
    assertEquals(1, twf.getPet().getSpace().getId());
  }

  /**
   * test invisible of the space.
   */
  @Test
  public void testInvisible() {
    twf.getTurnOfGame();
    scan = new Scanner("1\n");
    boolean result = mp.execute(twf, out, scan);
    System.out.println(out.toString());
    assertTrue(result);
    assertFalse(twf.getSpaces().get(1).isVisible());
    assertTrue(twf.getSpaces().get(0).isVisible());
    assertEquals(1, twf.getPet().getSpace().getId());
  }

  /**
   * test take in null expected message for error occurred.
   */
  @Test
  public void testExecuteWithException() throws IOException {
    twf.getTurnOfGame();
    boolean result = mp.execute(null, out, scan);

    assertFalse(result);
    assertTrue(out.toString().contains("An error occurred"));
  }

  /**
   * input wrong space number expected message for wrong input.
   */
  @Test
  public void testIsValidSpaceId() {
    twf.getTurnOfGame();
    scan = new Scanner("26\n");
    boolean result = mp.execute(twf, out, scan);

    assertFalse(result);
    assertTrue(out.toString().contains("Wrong input, please re-enter the space number."));
  }

}
