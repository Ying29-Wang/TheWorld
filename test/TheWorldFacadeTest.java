import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import theworld.Item;
import theworld.Player;
import theworld.Space;
import theworld.TheWorld;
import theworld.TheWorldFacade;


/**
 * This test class is designed to verify the functionality of the
 * {@code TheWorldFacade} class. It includes tests for parsing the world from a
 * file and moving targets within the world.
 */
public class TheWorldFacadeTest {

  @Test
  public void testParseTheWorld() {
    TheWorldFacade twf = new TheWorldFacade();
    try {
      TheWorld tw = twf.parseTheWorld(new FileReader("res/sampleInput1.txt"));
      assertEquals(tw.getName().trim(), "Doctor Lucky's Mansion");
      assertEquals(tw.getPet().getName(),"Fortune the Cat");
      assertEquals(tw.getRow(), 36);
      assertEquals(tw.getColumn(), 30);
      assertEquals(tw.getTarget().getName().trim(), "Doctor Lucky");
      assertEquals(tw.getTarget().getHealth(), 50);
      assertEquals(tw.getSpaces().size(), 21);
      assertEquals(tw.getItems().size(), 20);
      assertEquals(tw.getSpaces().get(0).getName().trim(), "Armory");
      assertEquals(((Space) (tw.getSpaces().get(0))).getItems().get(0).getName().trim(),
          "Revolver");
      assertEquals(((Space) (tw.getSpaces().get(0))).getNeighbors().size(), 3);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAddPlayerToTheWorld() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    assertTrue(twf.addPlayerToTheWorld(player));
    assertEquals(twf.getPlayers().size(), 1);
  }

  @Test
  public void testMovePlayer() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    assertTrue(twf.movePlayer(player, 0));
    assertTrue(player.getSpace().getId() == 0);
  }

  @Test
  public void testPickUpAction() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    twf.movePlayer(player, 0);
    assertTrue(twf.pickUpAction(player, 4));
  }

  @Test
  public void testDropOffAction() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    twf.movePlayer(player, 2);
    twf.pickUpAction(player, 6);
    twf.movePlayer(player, 0);
    assertTrue(twf.dropOffAction(player, (Item) twf.getItems().get(6)));
    assertTrue(((Space) twf.getSpaces().get(0)).getItems().size() == 2);
  }

  @Test
  public void testLookAroundFromSpace() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    twf.movePlayer(player, 2);
    assertNotNull(twf.lookAroundFromSpace(player));
    assertTrue(twf.lookAroundFromSpace(player).contains("he/she is in the space No.2 "));
  }

  @Test
  public void testMoveTargetToNext() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    twf.moveTargetToNext();
    assertEquals(twf.getTarget().getSpace().getId(), 0);
    twf.moveTargetToNext();
    assertEquals(twf.getTarget().getSpace().getId(), 1);
    for (int i = 0; i < 20; i++) {
      twf.moveTargetToNext();
    }
    assertEquals(twf.getTarget().getSpace().getId(), 0);
  }

  @Test
  public void testDrawTheWorld() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    try {
      twf.drawTheWorld("testWorld.png");
      File file = new File("testWorld.png");
      assertTrue(file.exists());
      file.delete(); // Clean up the file after test
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMovePlayerInvalid() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    assertThrows(IllegalArgumentException.class, () -> {
      twf.movePlayer(null, 0);
    });
  }

  @Test
  public void testPickUpActionInvalid() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    assertThrows(IllegalArgumentException.class, () -> {
      twf.pickUpAction(null, 0);
    });
  }

  @Test
  public void testDropOffActionInvalid() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    assertThrows(IllegalArgumentException.class, () -> {
      twf.dropOffAction(null, null);
    });
  }

  @Test
  public void testLookAroundFromSpaceInvalid() throws FileNotFoundException {
    Player player = new Player(1, "Player1", 1, false);
    TheWorldFacade twf = new TheWorldFacade();
    twf.parseTheWorld(new FileReader(
        "/Users/yingwang/eclipse-workspace/Local-TheWorld/TheWorld/res/sampleInput1.txt"));
    twf.addPlayerToTheWorld(player);
    assertThrows(IllegalArgumentException.class, () -> {
      twf.lookAroundFromSpace(null);
    });
  }
}
