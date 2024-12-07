import static org.junit.Assert.assertEquals;
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
  private MockAdapter adapter;

  /**
   * Sets up the test environment before each test.
   * Initializes the AddPlayer instance, TestTheWorldFacade instance, and
   * StringBuilder for output.
   */
  @Before
  public void setUp() {
    addPlayer = new AddPlayer();
    twf = new TestTheWorldFacade();
   
  }

  @Test
  public void testExecuteSuccess() {
    Scanner scan = new Scanner("Y\nRobotPlayer\n");
    adapter = new MockAdapter(new StringBuffer());
    boolean result = addPlayer.execute(twf, adapter, scan);
    assertTrue(result);
    assertTrue(adapter.getOutput().contains("New player RobotPlayer has been added"));
  }

  @Test
  public void testExecuteFailure() {
    Scanner scan = new Scanner("Y\n12 3\nabc\n");
    adapter = new MockAdapter(new StringBuffer());
    boolean result = addPlayer.execute(twf, adapter, scan);
    assertTrue(result);
    assertTrue(adapter.getOutput().contains("Wrong input, please enter the name:"));
  }

  @Test
  public void testExecuteAddHumanPlayer() {
    Scanner scan = new Scanner("N\nHumanPlayer\n");
    adapter = new MockAdapter(new StringBuffer());
    boolean result = addPlayer.execute(twf, adapter, scan);
    assertTrue(result);
    assertTrue(adapter.getOutput().contains("New player HumanPlayer has been added"));
  }

  @Test
  public void testExecuteAddPlayerFailure() {
    twf.setAddPlayerResult(false);
    Scanner scan = new Scanner("Y\nRobotPlayer\n");
    adapter = new MockAdapter(new StringBuffer());
    boolean result = addPlayer.execute(twf, adapter, scan);
    assertFalse(result);
    assertTrue(adapter.getOutput().contains(
        "Something went wrong, player could not be added! Try again"));
  }
  
  /**
   * test add 10 players.
   */
  @Test
  public void testExecuteAddPlayer10() {
    twf.setAddPlayerResult(true);
    
    adapter = new MockAdapter(new StringBuffer());
    for (int i = 0; i < 10; i++) {
      Scanner scan = new Scanner("Y\nRobotPlayer\n");
      boolean result = addPlayer.execute(twf, adapter, scan);
      
    }
    
    assertEquals(twf.getPlayers().size(), 10);
  }


  /**
   * test add 11 players, could not succeed because it exceed the limit 10.
   */
  @Test
  public void testExecuteAddPlayer11() {
    twf.setAddPlayerResult(true);
    
    adapter = new MockAdapter(new StringBuffer());
    for (int i = 0; i < 11; i++) {
      Scanner scan = new Scanner("Y\nRobotPlayer\n");
      boolean result = addPlayer.execute(twf, adapter, scan);
      
    }
    
    assertEquals(twf.getPlayers().size(), 10);
    assertTrue(adapter.getOutput().contains(
        "There are too many players in the game"));
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