
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;
import theworld.Mode;
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
  public void testMoveTargetInTheWorld() {
    TheWorldFacade twf = new TheWorldFacade();
    try {
      twf.parseTheWorld(new FileReader("res/sampleInput2.txt"));
      System.out.print(twf.moveTargetInTheWorld(Mode.sequence, 2));
      assertEquals(twf.moveTargetInTheWorld(Mode.sequence, 2),
          "the character is in the Space No.0 Armory; upleft:22,19; downright:23,26;\n"
              + "includes 1 items\n" + "- Revolver, cause 3 damage\n" + "it has 3 neighbors\n"
              + "- Space No.1 Billiard Room is a neighbor\n"
              + "- Space No.3 Dining Hall is a neighbor\n"
              + "- Space No.4 Drawing Room is a neighbor\n" + "\n"
              + "the character is in the Space No.1 Billiard Room; upleft:16,21; downright:21,28;\n"
              + "includes 1 items\n" + "- Billiard Cue, cause 2 damage\n" + "it has 3 neighbors\n"
              + "- Space No.0 Armory is a neighbor\n" + "- Space No.3 Dining Hall is a neighbor\n"
              + "- Space No.18 Trophy Room is a neighbor\n" + "\n" + "");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testParseTheWorld() {
    TheWorldFacade twf = new TheWorldFacade();
    try {
      TheWorld tw = twf.parseTheWorld(new FileReader("res/sampleInput2.txt"));
      assertEquals(tw.getName().trim(), "Doctor Lucky's Mansion");
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

}
