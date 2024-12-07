package theworldcontroller;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;
import theworld.TheWorldFacade;
import theworldviewer.MyView;

/**
 * The IoAdapter class implements the AdapterInterface and serves as an adapter
 * between the view and the controller in the application. It handles input and
 * output operations, manages game state, and facilitates communication between
 * different components.
 */
public class IoAdapter implements AdapterInterface {

  private PipedInputStream pipeInput;
  private PipedOutputStream pipeOutput;
  private Scanner scan;
  private TheWorldController twc;
  private TheWorldFacade twf;
  private MyView viewer;
  private String specification;
  private int turnLimit;
  private boolean moveable;
  private boolean restart;

  /**
   * Constructs an IoAdapter with the specified viewer.
   *
   * @param viewer the IView instance to be associated with this IOAdapter
   * @throws IllegalArgumentException if the viewer is null
   */
  public IoAdapter(MyView viewer) throws IllegalArgumentException {
    if (viewer != null) {
      this.viewer = viewer;
      this.restart = false;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void setInput(String s) throws IOException {
    pipeOutput.write((s).getBytes());
    pipeOutput.flush();
    this.viewer.setOutputPanel(s);
  }

  @Override
  public void setOutput(String s) {
    this.viewer.setOutputPanel(s);
  }

  @Override
  public void setMovable(boolean moveable) {
    this.moveable = moveable;
  }
  
  @Override
  public boolean getMovable() {
    return this.moveable;
  }
  
  @Override
  public String getPlayerInfo(int playerId) {
    return twf.getPlayers().get(playerId) != null ? twf.getPlayers().get(playerId).toString()
        : null;
  }

  @Override
  public String getTargetInfo() {
    return twf.getTarget().toString();
  }

  @Override
  public void drawGraphics(boolean redrawMap) {
    this.viewer.setWorldPanel(twf.getSpaces(), twf.getPlayers(), twf.getTarget(), redrawMap);

  }

  @Override
  public void showResult(String s) {
    this.viewer.showResult(s);
  }
  
  @Override
  public void startNewGame(String specification, int turnLimit) throws IOException {
    this.specification = specification;
    this.turnLimit = turnLimit;
    boolean firstRound = true;
    while (true) {
      while ((!this.restart) && (!firstRound)) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          return;
        }
      }
      if (this.restart) {
        this.restart = false;
        clearAll();
        setOutput("<<<===========================================================>>\n");
        setOutput("<<<================ user starts a new game. =================>>>\n");
        setOutput("<<<==========================================================>>>\n");
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          return;
        }
      }  
      pipeInput = new PipedInputStream();
      pipeOutput = new PipedOutputStream(pipeInput);
      scan = new Scanner(pipeInput);
      twc = new TheWorldController(this.scan, this, this.turnLimit);
      twf = new TheWorldFacade();
      twc.playGame(twf, this.specification);
      firstRound = false;
      
    } 
  }

  @Override
  public void restartGame(String spec, int turnLimits) throws IOException {
    if (spec != null) {
      this.specification = spec;
      this.turnLimit = turnLimits;
    }
    this.restart = true;
    twc.stopGame();
    pipeOutput.close();
    scan.close();
  }

  @Override
  public void shutOff() throws IOException {
    if (twc != null) {
      twc.stopGame();
      pipeOutput.close();
      scan.close();
      clearAll();
      
    }
    System.exit(0);  
  }

  /**
   * Clears all panels in the viewer.
   * This method clears the information panel, output panel, and world panel
   * in the viewer. It returns true upon successful completion.
   * 
   * @return true if all panels are successfully cleared.
   */
  private boolean clearAll() {
    
    viewer.clearInfoPanel();
    viewer.clearOutputPanel();
    viewer.clearWorldPanel();
    
    return true;
  }
  
}
