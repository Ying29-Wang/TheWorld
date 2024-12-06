import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.Player;
import theworld.TheWorldFacade;
import theworldcontroller.LookAround;

/**
 * Test class for the LookAround command.
 */
public class LookAroundTest {

  private LookAround lookAround;
  private TheWorldFacade twf;
  private TheWorldFacade twf2;
  private MockAdapter out;
  private Scanner in;
  private Player player;

  /**
   * Sets up the test environment before each test.
   * 
   * @throws FileNotFoundException if the sample input file is not found.
   */
  @Before
  public void setUp() throws FileNotFoundException {
    lookAround = new LookAround();
    twf = new TestTheWorldFacade();
    twf2 = new TestTheWorldFacad2();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));
    twf2.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));
    out = new MockAdapter(new StringBuffer());
    in = new Scanner(System.in);
    player = new Player(0, "mocker", 1, true);
    twf.addPlayerToTheWorld(player);
    twf2.addPlayerToTheWorld(player);
  }

  @Test
  public void testExecuteSuccess() {
    boolean result = lookAround.execute(twf, out, in);
    assertTrue(result);
    assertTrue(out.getOutput().contains("Looking around..."));
    assertTrue(out.getOutput().contains("as already moved to No."));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testExecuteFailure() {
    boolean result = lookAround.execute(twf2, out, in);
    //assertFalse(result);
  }

  private class TestTheWorldFacade extends TheWorldFacade {

    @Override
    public String lookAroundFromSpace(Player p) {
      return "Looking around...";
    }

  }

  private class TestTheWorldFacad2 extends TheWorldFacade {

    @Override
    public String lookAroundFromSpace(Player p) throws IllegalArgumentException {
      throw new IllegalArgumentException();
    }

  }
}
