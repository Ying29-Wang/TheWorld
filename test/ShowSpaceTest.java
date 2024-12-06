
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
import theworldcontroller.ShowSpace;

/**
 * Test class for the ShowSpace class.
 */
public class ShowSpaceTest {

  private ShowSpace showSpace;
  private TheWorldFacade twf;
  private MockAdapter out;
  private Scanner scan;
  private Move move;

  /**
   * Sets up the test environment before each test is run. Initializes the
   * ShowSpace and Move objects, and prepares a StringBuilder for output.
   *
   * @throws FileNotFoundException if the file is not found during setup
   */
  @Before
  public void setUp() throws FileNotFoundException {
    showSpace = new ShowSpace();
    move = new Move();
    out = new MockAdapter(new StringBuilder());
  }

  @Test
  public void testExecuteWithValidInput() throws IOException {
    Player player = new Player(0, "Player1", 1, true);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    move.execute(twf, out, scan);
    scan = new Scanner("1\n");
    boolean result = showSpace.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.getOutput().contains("please pick a space to show description"));
    assertTrue(out.getOutput().contains(twf.getSpaces().get(1).toString()));
  }

  @Test
  public void testExecuteWithInvalidInput() throws IOException {
    Player player = new Player(0, "Player1", 1, true);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    move.execute(twf, out, scan);
    scan = new Scanner("invalid\n1\n");

    boolean result = showSpace.execute(twf, out, scan);

    assertTrue(result);
    assertTrue(out.getOutput().contains("please pick a space to show description"));
    assertTrue(out.getOutput().contains("Wrong input, please re-enter the space number."));
  }

  @Test
  public void testExecuteWithNoSpaces() throws IOException {
    Player player = new Player(0, "Player1", 1, true);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput2.txt"));

    twf.addPlayerToTheWorld(player);
    move.execute(twf, out, scan);

    boolean result = showSpace.execute(twf, out, scan);

    assertFalse(result);
    assertTrue(out.getOutput().contains("There is not enough spaces in the game."));
  }


  @Test
  public void testExecuteWithTargetInSpace() throws IOException {
    Player player = new Player(0, "Player1", 1, true);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));
    twf.addPlayerToTheWorld(player);
    move.execute(twf, out, scan);
    scan = new Scanner("0\n1\n");
    boolean result = showSpace.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(
        out.getOutput().contains(String.format("%s is in the space.", twf.getTarget().getName())));
    assertTrue(out.getOutput().contains(twf.getSpaces().get(0).toString()));
    move.execute(twf, out, scan);
    result = showSpace.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(
        out.getOutput().contains(String.format("%s is in the space.", twf.getTarget().getName())));
    assertTrue(out.getOutput().contains(twf.getSpaces().get(1).toString()));
  }
}