
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
import theworld.TheWorldFacade;
import theworldcontroller.Move;

/**
 * The MoveTest class contains unit tests for the Move class.
 */
public class MoveTest {

  private Move move;
  private TheWorldFacade twf;
  private Appendable out;
  private Scanner scan;

  /**
   * Sets up the test environment before each test method is executed. Initializes
   * a new instance of the Move class and a StringBuilder for capturing output.
   *
   * @throws FileNotFoundException if the file is not found during setup
   */
  @Before
  public void setUp() throws FileNotFoundException {
    move = new Move();
    out = new StringBuilder();

  }

  @Test
  public void testExecuteManualMove() throws IOException {
    Player player = new Player(1, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/westz/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    scan = new Scanner("1\n");
    boolean result = move.execute(twf, out, scan);

    assertTrue(result);
    assertTrue(out.toString().contains("Enter a move for"));
    assertEquals(1, player.getSpace().getId());
  }

  @Test
  public void testExecuteAutomaticMove() throws IOException {
    Player player = new Player(1, "Player1", 1, true);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    scan = new Scanner("");
    boolean result = move.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("Player1 has already moved to"));
  }

  @Test
  public void testExecuteWithException() throws IOException {
    boolean result = move.execute(null, out, scan);

    assertFalse(result);
    assertTrue(out.toString().contains("An error occurred"));
  }

  @Test
  public void testIsValidSpaceId() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    scan = new Scanner("26\n");
    boolean result = move.execute(twf, out, scan);

    assertFalse(result);
    assertTrue(out.toString().contains("Wrong input, please re-enter the space number."));
  }
}
