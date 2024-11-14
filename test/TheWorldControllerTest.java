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
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
   * Add 1 player and move to room 0, then pick up item 0 and dropOff item 0 and
   * quit.
   */
  @Test
  public void testPlayGame1PlayerAndPickupAndDropoffAndQuit() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n2\n0\n3\n0\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("No item carried by mac."));
    assertTrue(output.contains("is mac turn"));
    assertTrue(output.contains("mac has already moved to Throne Room"));
    assertTrue(twf.getPlayers().get(0).getItems().size() == 0);
  }

  /**
   * Add 1 player and move to room 0, and check player description and q expect no
   * turn changes during checking player.
   */
  @Test
  public void testPlayGame1PlayerAndShowPlayer1AndNoChangeTurn() {

    StringReader sri = new StringReader("n\nmac\nn\n0\n7\n0\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 3).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 2).playGame(twf, "res/sample3.txt");

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
    new TheWorldController(sri, out, 5).playGame(twf, "res/sample3.txt");

    String output = out.toString();
    assertTrue(output.contains("It is turn 5"));
    assertTrue(output.contains("Now is mac turn"));
    assertTrue(output.contains("Now is messi turn"));
    assertTrue(output.contains("running out of turns"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Throne Room"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Grand Ballroom"));
    assertTrue(twf.getTarget().getSpace().getId() == 1);
  }

  /**
   * 1 player move pet and lookaround expected the space of pet is invisible.
   */
  @Test
  public void test1PlayerandMovePetAndLookAround() {

    StringReader sri = new StringReader("n\nmessi\nn\n0\n9\n2\n4\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    new TheWorldController(sri, out, 10).playGame(twf, "res/sample31.txt");

    String output = out.toString();
    assertTrue(output.contains("Move the pet to an space showing below"));
    assertTrue(output.contains("Fortune the Cat has already moved to No. 2 Feeding Hall"));
    assertTrue(output.contains("It is turn 2"));
    assertTrue(output.contains("this is the player messi, he/she is in the space No.0 Crypt"));
    assertTrue(output.contains("The neighbor space Feeding Hall is invisible"));
  }

  /**
   * 1 robot finally kill the target.
   */
  @Test
  public void test1RobotAndKillTheTarget() {

    StringReader sri = new StringReader("y\nmessi\nn\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 100);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    assertTrue(output.contains("messi is trying to attack target"));
    assertTrue(output.contains("Health number is left for 0"));
    assertTrue(output.contains("The Target has been attacked"));
    assertTrue(output.contains("messi win this game"));
  }

  /**
   * 2 robot finally kill the target.
   */
  @Test
  public void test2RobotAndKillTheTarget() {

    StringReader sri = new StringReader("y\nmessi\nn\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 200);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();

    assertTrue(output.contains("messi is trying to attack target"));
    assertTrue(output.contains("Health number is left for 0"));
    assertTrue(output.contains("The Target has been attacked"));
    assertTrue(output.contains("win this game"));
  }

  /**
   * 1 Player try to kill the target but was seen by the other one who is in the
   * neighbor space attack false.
   */
  @Test
  public void test2PlayerAndKillTheTarget() {

    StringReader sri = new StringReader("n\nmessi\ny\nn\nmac\nn\n0\n2\n1\n3\n10\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 200);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    assertTrue(output.contains("you are in the space 2 Feeding Hall"));
    assertTrue(output.contains("messi has already moved to Dark Library"));

    assertTrue(output.contains("attacked false beacause somebody is watching you"));
  }

  /**
   * 1 Player try to kill the target but was seen by the other one who is in the
   * same space attack false.
   */
  @Test
  public void test2PlayerAndKillTheTargetIntheSameSpaceFalse() {

    StringReader sri = new StringReader(
        "n\nmessi\ny\nn\nmac\nn\n0\n1\n1\n3\n1\n2\n1\n0\n1\n0\n10\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 200);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    assertTrue(output.contains("mac has already moved to Crypt"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Crypt" + ""));
    assertTrue(output.contains("you are in the space 0 Crypt"));
    assertTrue(output.contains("attacked false beacause somebody is watching you"));
  }

  /**
   * 1 Player try to kill the target and not seen by other. attack complete
   */
  @Test
  public void test2PlayerAttackComplete() {

    StringReader sri = new StringReader("n\nmessi\ny\nn\nmac\nn\n1\n3\n10\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 200);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    assertTrue(output.contains("messi has already moved to Coffin Chamber"));
    assertTrue(output.contains("mac has already moved to Dark Library"));
    assertTrue(output.contains("The Earl Decuras has already moved to No. 1 Coffin Chamber" + ""));
    assertTrue(output.contains("messi is poking him in the eye and hurt 1 "));

  }

  /**
   * 2 Robot run out of turn attack false.
   */
  @Test
  public void test2RobotRunOutOfTurn() {

    StringReader sri = new StringReader("y\nmessi\ny\ny\nmac\nn\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 10);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    assertTrue(output.contains("You are running out of turns"));
  }

  /**
   * 1 Player attack using an item item was became evidence.
   */
  @Test
  public void test1playerAttackByItem() {

    StringReader sri = new StringReader("n\nmac\nn\n1\n2\n1\n10\n1\n7\n0\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 10);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    assertTrue(output.contains("The Target has been attacked by Vampire's Fang Dagger"));
    assertTrue(twf.getPlayers().get(0).getItems().size() == 0);
    assertTrue(twf.getEvidences().size() == 1);
    assertTrue(twf.getItems().size() == 4);
  }

  /**
   * pet start at same place with the target.
   */
  @Test
  public void testPetStart() {

    StringReader sri = new StringReader("n\nmac\nn\n1\nq\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 10);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    assertTrue(output.contains("The Earl Decuras has already moved to No. 0 Crypt\n"
        + "Fortune the Cat has already moved to No. 0 Crypt"));
  }

  /**
   * pet move every turns and using depth-first traversal method space stack =
   * [02134] so it should move by the order [0431204312].
   */
  @Test
  public void testPetAutomaticMovingDepthFirst() {

    StringReader sri = new StringReader("y\nmac\nn\n");
    TheWorldFacade twf = new TheWorldFacade();
    TheWorldController twc = new TheWorldController(sri, out, 10);
    twc.playGame(twf, "res/sample31.txt");
    String output = out.toString();
    StringBuffer sb = new StringBuffer();
    String[] outputs = output.split("Fortune the Cat has already moved to No. ");
    for (int i = 0; i < outputs.length; i++) {
      String temp = outputs[i].substring(0, 1);
      if (temp.matches("^[0-9]+$")) {
        sb.append(temp);
      }
    }
    assertTrue(sb.toString().equals("04312043120"));
  }

}
