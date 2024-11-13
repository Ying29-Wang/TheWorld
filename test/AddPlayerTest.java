import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.Player;
import theworld.TheWorldFacade;
import theworldcontroller.AddPlayer;

/**
 * Unit tests for the AddPlayer class.
 */
public class AddPlayerTest {

  private AddPlayer addPlayer;
  private TestTheWorldFacade twf;
  private Appendable out;
  private Scanner scan;

  /**
   * Sets up the test environment before each test.
   * Initializes the AddPlayer instance, TestTheWorldFacade instance, and
   * StringBuilder for output.
   */
  @Before
  public void setUp() {
    addPlayer = new AddPlayer();
    twf = new TestTheWorldFacade();
    out = new StringBuilder();
  }

  @Test
  public void testExecuteSuccess() {
    scan = new Scanner("Y\nRobotPlayer\n");
    boolean result = addPlayer.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("New player RobotPlayer has been added"));
  }

  @Test
  public void testExecuteFailure() {
    scan = new Scanner("Y\nInvalid Name\n");
    boolean result = addPlayer.execute(twf, out, scan);
    assertFalse(result);
    assertTrue(out.toString().contains("Wrong input, please enter the name:"));
  }

  @Test
  public void testExecuteAddHumanPlayer() {
    scan = new Scanner("N\nHumanPlayer\n");
    boolean result = addPlayer.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("New player HumanPlayer has been added"));
  }

  @Test
  public void testExecuteAddPlayerFailure() {
    twf.setAddPlayerResult(false);
    scan = new Scanner("Y\nRobotPlayer\n");
    boolean result = addPlayer.execute(twf, out, scan);
    assertFalse(result);
    assertTrue(out.toString().contains(
        "Something went wrong, player could not be added! Try again"));
  }

  private class TestTheWorldFacade extends TheWorldFacade {
    private List<Player> players = new ArrayList<>();
    private boolean addPlayerResult = true;

    @Override
    public boolean addPlayerToTheWorld(Player player) {
      if (addPlayerResult) {
        players.add(player);
        return true;
      }
      return false;
    }

    @Override
    public List<Player> getPlayers() {
      return players;
    }

    public void setAddPlayerResult(boolean result) {
      this.addPlayerResult = result;
    }
  }
}