import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.TheWorldFacade;
import theworldcontroller.DrawMap;

/**
 * Test class for the DrawMap class.
 */
public class DrawMapTest {

  private DrawMap drawMap;
  private TestTheWorldFacade twf;
  private Appendable out;
  private Scanner scan;

  /**
   * Set up the test environment before each test method is executed.
   * Initializes the DrawMap instance, TestTheWorldFacade instance, and StringBuilder for output.
   */
  @Before
  public void setUp() {
    drawMap = new DrawMap();
    twf = new TestTheWorldFacade();
    out = new StringBuilder();
  }

  @Test
  public void testExecuteValidPngFile() throws IOException {
    scan = new Scanner("validfile.png\n");
    boolean result = drawMap.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(out.toString().contains("The map has been stored to the file validfile.png"));
  }

  @Test
  public void testExecuteInvalidFileExtension() throws IOException {
    scan = new Scanner("invalidfile.txt\nvalidfile.png\n");
    boolean result = drawMap.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(out.toString().contains("File must be a png, must end with .png"));
    assertTrue(out.toString().contains("The map has been stored to the file validfile.png"));
  }

  @Test
  public void testExecuteIoException() throws IOException {
    scan = new Scanner("validfile.png\n");
    twf.setThrowIoException(true);
    boolean result = drawMap.execute(twf, out, scan);
    assertFalse(result);
  }

  @Test
  public void testExecuteEmptyFileName() throws IOException {
    scan = new Scanner("\nvalidfile.png\n");
    boolean result = drawMap.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(out.toString().contains("File must be a png, must end with .png"));
    assertTrue(out.toString().contains("The map has been stored to the file validfile.png"));
  }

  @Test
  public void testExecuteNullFileName() throws IOException {
    scan = new Scanner("null\nvalidfile.png\n");
    boolean result = drawMap.execute(twf, out, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(out.toString().contains("File must be a png, must end with .png"));
    assertTrue(out.toString().contains("The map has been stored to the file validfile.png"));
  }

  private class TestTheWorldFacade extends TheWorldFacade {
    private boolean drawn = false;
    private boolean throwIoException = false;

    @Override
    public void drawTheWorld(String fileName) throws IOException {
      if (throwIoException) {
        throw new IOException();
      }
      drawn = true;
    }

    public boolean isDrawn() {
      return drawn;
    }

    public void setThrowIoException(boolean throwIoException) {
      this.throwIoException = throwIoException;
    }
  }
}