import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import theworld.TheWorldFacade;
import theworldcontroller.AdapterInterface;
import theworldcontroller.DrawMap;

/**
 * Test class for the DrawMap class.
 */
public class DrawMapTest {

  private DrawMap drawMap;
  private TestTheWorldFacade twf;
  private MockAdapter adapter;
  private Scanner scan;

  /**
   * Set up the test environment before each test method is executed.
   * Initializes the DrawMap instance, TestTheWorldFacade instance, and StringBuilder for output.
   */
  @Before
  public void setUp() {
    drawMap = new DrawMap();
    twf = new TestTheWorldFacade();
    
  }

  @Test
  public void testExecuteValidPngFile() throws IOException {
    scan = new Scanner("validfile.png\n");
    adapter = new MockAdapter(new StringBuffer());
    boolean result = drawMap.execute(twf, adapter, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(adapter.getOutput().contains("The map has been stored to the file validfile.png"));
  }

  @Test
  public void testExecuteInvalidFileExtension() throws IOException {
    scan = new Scanner("invalidfile.txt\nvalidfile.png\n");
    adapter = new MockAdapter(new StringBuffer());
    
    boolean result = drawMap.execute(twf, adapter, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(adapter.getOutput().contains("File must be a png, must end with .png"));
    assertTrue(adapter.getOutput().contains("The map has been stored to the file validfile.png"));
  }

  @Test
  public void testExecuteIoException() throws IOException {
    scan = new Scanner("validfile.png\n");
    adapter = new MockAdapter(new StringBuffer());
    
    twf.setThrowIoException(true);
    boolean result = drawMap.execute(twf, adapter, scan);
    assertFalse(result);
  }

  @Test
  public void testExecuteEmptyFileName() throws IOException {
    scan = new Scanner("\nvalidfile.png\n");
    adapter = new MockAdapter(new StringBuffer());
    
    boolean result = drawMap.execute(twf, adapter, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(adapter.getOutput().contains("File must be a png, must end with .png"));
    assertTrue(adapter.getOutput().contains("The map has been stored to the file validfile.png"));
  }

  @Test
  public void testExecuteNullFileName() throws IOException {
    scan = new Scanner("null\nvalidfile.png\n");
    adapter = new MockAdapter(new StringBuffer());
    
    boolean result = drawMap.execute(twf, adapter, scan);
    assertTrue(result);
    assertTrue(twf.isDrawn());
    assertTrue(adapter.getOutput().contains("File must be a png, must end with .png"));
    assertTrue(adapter.getOutput().contains("The map has been stored to the file validfile.png"));
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