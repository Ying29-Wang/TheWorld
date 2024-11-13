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
import theworldcontroller.ShowPlayer;

/**
 * Test class for the ShowPlayer class.
 */
public class ShowPlayerTest {

  private ShowPlayer showPlayer;
  private TheWorldFacade twf;
  private Appendable out;
  private Scanner scan;

  /**
   * Sets up the test environment before each test method is executed. Initializes
   * a new instance of ShowPlayer.
   * 
   * @throws FileNotFoundException if the ShowPlayer initialization fails due to a
   *                               missing file.
   */
  @Before
  public void setUp() throws FileNotFoundException {
    showPlayer = new ShowPlayer();
  }

  @Test
  public void testExecuteWithValidPlayerSelection() throws IOException {
    Player player = new Player(0, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    scan = new Scanner("0\n");
    out = new StringBuilder();
    boolean result = showPlayer.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(out.toString().contains("Please pick a player to show description"));
    assertTrue(out.toString().contains("0. Player1"));
    assertTrue(out.toString().contains(player.toString()));
  }

  @Test
  public void testExecuteWithInvalidPlayerSelection() throws IOException {
    Player player = new Player(0, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));

    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    scan = new Scanner("2\n0\n");
    out = new StringBuilder();

    boolean result = showPlayer.execute(twf, out, scan);
    scan.close();
    assertTrue(result);
    assertTrue(out.toString().contains("Please pick a player to show description"));
    assertTrue(out.toString().contains("0. Player1"));
    assertTrue(out.toString().contains("Wrong input, please re-enter the space number."));
    // assertTrue(out.toString().contains(player1.toString()));
  }

  @Test
  public void testExecuteWithNoPlayers() throws IOException {
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));
    scan = new Scanner("");
    out = new StringBuilder();
    boolean result = showPlayer.execute(twf, out, scan);
    assertTrue(!result);
    assertTrue(out.toString().contains("There is not enough players in the game."));
  }

  @Test
  public void testExecuteWithIoException() throws IOException {
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));
    scan = new Scanner("1\n");
    Player player = new Player(0, "Player1", 1, false);
    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    Appendable outWithIoException = new Appendable() {
      @Override
      public Appendable append(CharSequence csq) throws IOException {
        throw new IOException();
      }

      @Override
      public Appendable append(CharSequence csq, int start, int end) throws IOException {
        throw new IOException();
      }

      @Override
      public Appendable append(char c) throws IOException {
        throw new IOException();
      }
    };

    boolean result = showPlayer.execute(twf, outWithIoException, scan);
    assertFalse(result);
  }

  @Test
  public void testExecuteWithNonNumericInput() throws IOException {
    Player player = new Player(0, "Player1", 1, false);
    twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput.txt"));
    twf.addPlayerToTheWorld(player);
    player.move(twf.getSpaces().get(0));
    scan = new Scanner("abc\n0\n");
    out = new StringBuilder();
    boolean result = showPlayer.execute(twf, out, scan);
    scan.close();
    assertTrue(result);
    assertTrue(out.toString().contains("Wrong input, please re-enter the space number."));
    assertTrue(out.toString().contains(player.toString()));
  }
}