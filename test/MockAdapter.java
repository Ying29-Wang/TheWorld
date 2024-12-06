import java.io.IOException;

import theworldcontroller.AdapterInterface;

/**
 * MockAdapter is a mock implementation of the AdapterInterface used for testing purposes.
 * It uses an Appendable object to simulate output operations.
 */
public class MockAdapter implements AdapterInterface {
 
  private Appendable out;
  
  public MockAdapter(Appendable ap) {
    this.out = ap;
  }
  
  @Override
  public void setInput(String s) throws IOException {

  }

  @Override
  public void setOutput(String s) {
    try {
      out.append(s);
    } catch (IOException e) {
      return;
    }
  }
  
  public String getOutput() {
    return this.out.toString();
  }
  

  @Override
  public String getPlayerInfo(int playerId) {
    return null;
  }

  @Override
  public String getTargetInfo() {
    return null;
  }

  @Override
  public void drawGraphics(boolean redrawMap) {
    return;
    
  }

  @Override
  public void startNewGame(String specification, int turnLimit) throws IOException {
   return;
    
  }

  @Override
  public void shutOff() throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void restartGame(String spec, int turnLimits) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setMovable(boolean moveable) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean getMovable() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void showResult(String s) {
    // TODO Auto-generated method stub
    
  }

}
