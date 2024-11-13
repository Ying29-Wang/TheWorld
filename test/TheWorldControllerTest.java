import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Before;
import org.junit.Test;
import theworld.TheWorldFacade;
import theworldcontroller.TheWorldController;

/**
 * TheWorldControllerTest is a test class for the TheWorldController class.
 */
public class TheWorldControllerTest {

  private StringWriter out;

  /**
   * Sets up the test environment before each test method is executed. Initializes
   * the StringWriter object to capture output.
   *
   * @throws Exception if an error occurs during setup
   */
  @Before
  public void setUp() throws IOException {
    out = new StringWriter();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullReadable() {
    new TheWorldController(null, out, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAppendable() {
    new TheWorldController(new StringReader(""), null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidTurnLimit() {
    new TheWorldController(new StringReader(""), out, 0);
  }

  /**
   * Add 1 human-controlled player and move to space 0, and quit.
   */
  @Test
  public void testPlayGame1PlayerMoveAndQuit() {

    StringReader sri = new StringReader("n\nmac\nn\n0\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("Welcome! The game is start"));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("mac has already moved to Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("User quit game"));
  }

  /**
   * Add 1 robot player and run to the end turn 3.
   */
  @Test
  public void testPlayGame1Robot() {

    StringReader sri = new StringReader("y\nmessi\nn\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("Welcome! The game is start"));
    assertTrue(output.contains("is messi turn"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(output.contains("It is turn 3"));
    assertTrue(output.contains("running out of turns"));
  }

  /**
   * Add 2 player and 1 is a robot, separately move to space 0 and space 1
   * Expected :Stop at turn 2.
   */
  @Test
  public void testPlayGame1Player1RobotManualQuit() {

    StringReader sri = new StringReader("n\nmac\ny\ny\nmessi\nn\n1\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("Welcome! The game is start"));
    assertTrue(output.contains("is messi turn"));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(output.contains("User quit"));
  }

  /**
   * Add 1 player and move to room 0, then pick up item and quit.
   */
  @Test
  public void testPlayGame1PlayerAndPickupAndQuit() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n2\n0\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("Welcome! The game is start"));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("Please pick an item showing below"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(output.contains("The item has been picked up by mac."));
    assertTrue(twf.getPlayers().get(0).getItems().get(0).getId() == 0);
  }

  /**
   * Add 1 player and move to room 0, then pick up item 0 and try to pick up
   * another(but there is no more in the space)and quit.
   */
  @Test
  public void testPlayGame1PlayerAndPickupNoMoreItemAndQuit() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n2\n0\n2\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("No item list in the room."));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("Please pick an item showing below"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(output.contains("The item has been picked up by mac."));
    assertTrue(twf.getPlayers().get(0).getItems().get(0).getId() == 0);
  }

  /**
   * Add 1 player and move to room 0, then pick up item 0 and move to room 1 and
   * pick up another but over limit of a player.
   */
  @Test
  public void testPlayGame1PlayerAndPickupToLimitAndQuit() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n2\n0\n1\n1\n2\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("mac can't have more items"));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("Please pick an item showing below"));
    assertTrue(output.contains("mac has already moved to Throne Room"));
    assertTrue(output.contains("mac has already moved to Grand Ballroom"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(output.contains("The item has been picked up by mac."));
    assertTrue(twf.getPlayers().get(0).getItems().get(0).getId() == 0);
  }

  /**
   * Add 1 player and move to room 0, then pick up item 0 and dropOff item 0 and
   * quit.
   */
  @Test
  public void testPlayGame1PlayerAndPickupAndDropoffAndQuit() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n2\n0\n3\n0\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("The item has been dropped off by mac"));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("Please pick an item showing below"));
    assertTrue(output.contains("mac has already moved to Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(output.contains("The item has been picked up by mac."));
    assertTrue(twf.getPlayers().get(0).getItems().size() == 0);
  }

  /**
   * Try to drop off item but there is no item carried by the player.
   */
  @Test
  public void testPlayGame1PlayerAndDropOffWithoutAnything() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n3\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("No item carried by mac."));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("mac has already moved to Throne Room"));
    assertTrue(twf.getPlayers().get(0).getItems().size() == 0);
  }

  /**
   * Add 1 player and move to room 0, and check space1 description and q expect no
   * turn changes during show space 1.
   */
  @Test
  public void testPlayGame1PlayerAndShowSpace1AndNoChangeTurn() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n8\n1\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains(twf.getSpaces().get(1).toString()));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
  }

  /**
   * Add 1 player and move to room 0, and check player description and q expect no
   * turn changes during checking player.
   */
  @Test
  public void testPlayGame1PlayerAndShowPlayer1AndNoChangeTurn() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n7\n0\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains(twf.getPlayers().get(0).toString()));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
  }

  /**
   * Add 1 player and move to room 0 and look around and quit expect turn changes
   * after looking around.
   */
  @Test
  public void testPlayGame1PlayerAndLookAround() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n4\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("he/she is in the space No.0 Throne Room"));
    assertTrue(output.contains("now he/she is watching the space:"));
    assertTrue(output.contains("Space No.1 Grand Ballroom;"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
  }

  /**
   * play 3 times but only allows 2 times as limit , game stopped by excess the
   * turn limits expect: target move to space 0(round back).
   */
  @Test
  public void testPlayGame1PlayerAndPlayTooManyTurns() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n1\n1\n2\n1" + "\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 2).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("It is turn 2"));
    assertTrue(output.contains("running out of turns"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(twf.getTarget().getSpace().getId() == 0);
  }

  /**
   * 1 player and 1 robot play to the end expect: target move to space 0(round
   * back).
   */
  @Test
  public void testPlayGame1PlayerAnd1RobotPlayToTheEnd() {

    StringReader sri = new StringReader("y\nmessi\ny\nn\nmac\nn\n1\n1\n0\n1\n1\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 5).playGame(twf,
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("It is turn 5"));
    assertTrue(output.contains("Now is mac turn"));
    assertTrue(output.contains("Now is messi turn"));
    assertTrue(output.contains("running out of turns"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(twf.getTarget().getSpace().getId() == 1);
  }
}
