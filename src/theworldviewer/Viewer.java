package theworldviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import theworld.CharacterInterface;
import theworld.Player;
import theworld.Space;
import theworld.SpaceInterface;
import theworldcontroller.AdapterInterface;

/**
 * The Viewer class implements the IView interface and provides a graphical user interface
 * for the game "Kill the doctor!!!". It uses Swing components to create the main frame,
 * output panel, info panel, and world panel. It also provides methods to interact with
 * the game through dialogs and key events.
 */
public class Viewer implements IView {
  private JFrame frame;
  private JMenuBar menu;
  private JScrollPane outputPanel;
  private JTextArea outputArea;
  private JScrollPane worldPanel;
  private JTextArea infoArea;
  private JScrollPane infoPanel;
  private JPanel panel;
  private JLabel targetRepresent;
  private List<JLabel> spaceRepresents;
  private List<JLabel> playerRepresents;
  private AdapterInterface adapter;

  /**
   * The constructor is responsible for creating and managing the main GUI components
   * of the application. It initializes the main frame, output panel, info panel,
   * world panel, and menu bar with various functionalities such as starting a new
   * game, restarting the game, and shutting off the game.
   */
  public Viewer() {
    spaceRepresents = new ArrayList<JLabel>();
    playerRepresents = new ArrayList<JLabel>();
    // create main frame
    frame = new JFrame("Kill the doctor!!!");
    frame.setSize(1800, 1600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    
    // outputPanel
    outputArea = new JTextArea();
    outputArea.setEditable(false);
    outputPanel = new JScrollPane(outputArea);
    outputPanel.setPreferredSize(new Dimension(1200, 500));
    outputPanel.setFocusable(false);
    outputArea.setFocusable(false); 
    frame.add(outputPanel, BorderLayout.WEST);

    // infoPanel
    infoArea = new JTextArea();
    infoArea.setEditable(false);
    infoPanel = new JScrollPane(infoArea);
    infoPanel.setPreferredSize(new Dimension(600, 500));
    infoPanel.setFocusable(false);
    infoArea.setFocusable(false); 
    frame.add(infoPanel, BorderLayout.EAST);

    // worldPanel
    panel = new JPanel();
    panel.setLayout(null);
    panel.setPreferredSize(new Dimension(1500, 1000));

    worldPanel = new JScrollPane(panel);
    worldPanel.setPreferredSize(new Dimension(frame.getWidth(), 800));
    worldPanel.setFocusable(false);
    panel.setFocusable(false);
    
    frame.add(worldPanel, BorderLayout.SOUTH);
    frame.addKeyListener(this);
    // menu bar
    menu = new JMenuBar();

    // Menu Item for start a new game with new specification file
    JMenuItem useNewSpec = new JMenuItem("new spec");
    useNewSpec.addActionListener(e -> showFileInputDialog());
    menu.add(useNewSpec);

    // Menu Item for start a new game with current specification file
    JMenuItem useCurrentSpec = new JMenuItem("restart game ");
    useCurrentSpec.addActionListener(e -> showConfirmationDialogToRestart());
    menu.add(useCurrentSpec);

    // Menu Item for shut off the game
    JMenuItem quit = new JMenuItem("shut off");
    quit.addActionListener(e -> showConfirmationDialogToShutoff());
    menu.add(quit);

    frame.setJMenuBar(menu);
    frame.setVisible(true);
    frame.requestFocusInWindow();
  }

  // input dialog for select specification file
  /**
   * Displays a dialog for the user to select a file and input the number of turns to play.
   */
  private void showFileInputDialog() {
    JDialog dialog = new JDialog();
    dialog.setLayout(new GridLayout(3, 1));
    dialog.setSize(400, 200);

    // select file
    JPanel filePanel = new JPanel(new BorderLayout());
    JTextField fileField = new JTextField();
    JButton fileButton = new JButton("Choose File");
    fileButton.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      if (fileChooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        fileField.setText(selectedFile.getAbsolutePath());
      }
    });
    filePanel.add(fileField, BorderLayout.CENTER);
    filePanel.add(fileButton, BorderLayout.EAST);

    // input turn limits
    JPanel numberPanel = new JPanel(new BorderLayout());
    JLabel numberLabel = new JLabel("Enter how many turns do you want to play:");
    JTextField numberField = new JTextField();
    numberPanel.add(numberLabel, BorderLayout.WEST);
    numberPanel.add(numberField, BorderLayout.CENTER);

    // submit to the controller
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(e -> {
      String filePath = fileField.getText();
      String limit = numberField.getText();
      try {
        dialog.dispose();
        frame.requestFocusInWindow();
        adapter.restartGame(filePath, Integer.parseInt(limit));
      } catch (NumberFormatException e1) {
        System.out.print("turns must be a number larger than 0");
      } catch (IOException e1) {
        System.out.print("no suck file found: " + filePath);
      }
    });

    dialog.add(filePanel);
    dialog.add(numberPanel);
    dialog.add(submitButton);
    dialog.setVisible(true);
  }

  // dialog to confirm restart this game
  /**
   * Displays a confirmation dialog to restart the game.
   * If an IOException occurs during the game restart, an error message is printed to the console.
   */
  private void showConfirmationDialogToRestart() {
    JDialog dialog = new JDialog();
    dialog.setLayout(new FlowLayout());
    dialog.setSize(300, 150);

    JLabel message = new JLabel("Confirm to restart this game.");
    JButton confirmButton = new JButton("Confirm");
    JButton closeButton = new JButton("Close");

    confirmButton.addActionListener(e -> {
      try {
        dialog.dispose();
        frame.requestFocusInWindow();
        adapter.restartGame(null, 0);
        
      } catch (IOException e1) {
        System.out.print("could not restart the game, please try later");
      }      
    });
    closeButton.addActionListener(e -> dialog.dispose());

    dialog.add(message);
    dialog.add(confirmButton);
    dialog.add(closeButton);
    dialog.setVisible(true);
  }

  // dialog to confirm shut off the game
  /**
   * Displays a confirmation dialog to shut off the game.
   * The method handles any IOException that may occur during the shut off process
   * and prints an error message to the console if the shut off fails.
   */
  private void showConfirmationDialogToShutoff() {
    JDialog dialog = new JDialog();
    dialog.setLayout(new FlowLayout());
    dialog.setSize(300, 150);

    JLabel message = new JLabel("Confirm to shut off.");
    JButton confirmButton = new JButton("Confirm");
    JButton closeButton = new JButton("Close");

    confirmButton.addActionListener(e -> {
      try {
        dialog.dispose();
        adapter.shutOff();
      } catch (IOException e1) {
        System.out.print("could not shut off the game, please try later");
      }
    });
    closeButton.addActionListener(e -> dialog.dispose());

    dialog.add(message);
    dialog.add(confirmButton);
    dialog.add(closeButton);
    dialog.setVisible(true);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    try {
      char key = e.getKeyChar();
      if (key == '\n') {
        adapter.setInput(Character.toString('\n')); // End the current line
      } else {
        adapter.setInput(Character.toString(key)); // Write character to stream
      }
      frame.requestFocusInWindow();
    } catch (IOException e1) {
      System.out.print("could not input at this time, please retry");
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void setAdapter(AdapterInterface adapter) {
    this.adapter = adapter;
  }
  
  @Override
  public void showResult(String s) {
    JLabel label = new JLabel(s);
    label.setFont(new Font("Arial", Font.PLAIN, 60)); 
    label.setForeground(Color.RED);
    FontMetrics metrics = label.getFontMetrics(label.getFont());
    int textWidth = metrics.stringWidth(s);
    int textHeight = metrics.getHeight();
    label.setBounds(500, 500, textWidth + 200, textHeight);
    label.setOpaque(true);
    label.setBackground(Color.LIGHT_GRAY);
    label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    label.setHorizontalAlignment(SwingConstants.LEFT); 
    label.setVerticalAlignment(SwingConstants.TOP); 
    this.panel.add(label);
    this.panel.setComponentZOrder(label, 0);
    this.panel.revalidate();
    this.panel.repaint();
  }

  @Override
  public void setOutputPanel(String s) {
    this.outputArea.append(s);
    //this.outputArea.append("\n");
    this.outputPanel.revalidate();
    this.outputPanel.repaint();
    JScrollBar verticalBar = this.outputPanel.getVerticalScrollBar();
    verticalBar.setValue(verticalBar.getMaximum());
  }

  @Override
  public void setWorldPanel(List<SpaceInterface> spaces, List<Player> players,
      CharacterInterface target, boolean redrawMap) {
    int scaleFactor = 50;
    //panel.setPreferredSize(new Dimension(1500, 1000));

    if (redrawMap) {
      //draw map on the world panel
      int maxX = 0;
      int maxY = 0;
      for (SpaceInterface sp : spaces) {
        int[] upLeft = sp.getUpLeft();
        int[] lowRight = sp.getLowRight();
        int y1 = upLeft[0] * scaleFactor - (scaleFactor / 2 - 1) + scaleFactor;
        int x1 = upLeft[1] * scaleFactor - (scaleFactor / 2 - 1) + scaleFactor;
        int y2 = lowRight[0] * scaleFactor 
            + (scaleFactor / 2 - 1) + scaleFactor;
        int x2 = lowRight[1] * scaleFactor 
            + (scaleFactor / 2 - 1) + scaleFactor;
        int width = x2 - x1;
        int height = y2 - y1;
        if (x2 > maxX) {
          maxX = x2;
        }
        if (y2 > maxY) {
          maxY = y2;
        }
        // create JLabel
        JLabel label = new JLabel(sp.getName());
        
        label.setBounds(x1, y1, width, height);
        label.setOpaque(true);
        label.setBackground(Color.GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // add mouse listener to 
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (adapter.getMovable()) {
                  try {
                    adapter.setInput(String.valueOf(sp.getId()) + "\n");
                  } catch (IOException e1) {
                    setOutputPanel("something wrong happens, please restart the game " 
                        + e1.getMessage());
                  } 
                }
                frame.requestFocusInWindow();
            }
        });
        label.setHorizontalAlignment(SwingConstants.LEFT); 
        label.setVerticalAlignment(SwingConstants.TOP); 
        this.panel.add(label);
        this.panel.setComponentZOrder(label, 0);
        this.spaceRepresents.add(label);
      }
      this.panel.setPreferredSize(new Dimension(Math.max(panel.getWidth(),
          maxX + 200), Math.max(panel.getHeight(), maxY + 200)));
     
      this.panel.revalidate();
      this.panel.repaint();
    } else { // only redraw players and target
      Iterator<JLabel> iterator = playerRepresents.iterator();
      while (iterator.hasNext()) {
        
        JLabel ele = iterator.next();
        this.panel.remove(ele);
        iterator.remove();
        this.panel.revalidate();
        this.panel.repaint();
      }
      
      if (targetRepresent != null) {
        this.panel.remove(targetRepresent);
        this.panel.revalidate();
        this.panel.repaint();
        targetRepresent = null;
      }
      int currentX = 0;
      int currentY = 0;
      for (SpaceInterface sp : spaces) {
     
        currentY = scaleFactor + sp.getUpLeft()[0] * scaleFactor - (scaleFactor / 2 - 1);
        currentX = scaleFactor + sp.getUpLeft()[1] * scaleFactor - (scaleFactor / 2 - 1);
        for (Player p : ((Space) sp).getPlayers()) {
           
          if (p != null) {
            System.out.println(sp.getName());
            addPlayerToWorldPanel(currentX + 5, currentY + 15, p);
            currentY = currentY + 15;
          }
        }
        if ((target.getSpace() != null) && (target.getSpace().getId() == sp.getId())) {
          addTargetToWorldPanel(currentX + 5, currentY + 15, target);
        } 
      }
    }
    
  }
  
  // draw player on the map
  /**
   * Adds a player representation to the world panel at the specified coordinates.
   *
   * @param x1 The x-coordinate where the player label should be placed.
   * @param y1 The y-coordinate where the player label should be placed.
   * @param p The player object containing the player's information.
   */
  private void addPlayerToWorldPanel(int x1, int y1, Player p) {
    JLabel label = new JLabel(p.getName());
    FontMetrics metrics = label.getFontMetrics(label.getFont());
    int textWidth = metrics.stringWidth(p.getName());
    int textHeight = metrics.getHeight();
    
    label.setBounds(x1, y1, textWidth + 10, textHeight + 2);
    label.setForeground(Color.BLUE);
    label.setOpaque(true);
    label.setBackground(Color.RED);
    label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    
    label.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        String playerInfo = adapter.getPlayerInfo(p.getId());
        if (playerInfo != null) {
          clearInfoPanel();
          setInfoPanel(playerInfo);
        }
        frame.requestFocusInWindow();
      }
    });
    System.out.println(label.getText() + " " + label.getX() + " " + label.getY());
    
    this.panel.add(label);
    this.panel.setComponentZOrder(label, 0);
    this.panel.revalidate();
    this.panel.repaint();
    this.playerRepresents.add(label);
  }
  
  // draw target on the map
  /**
   * Adds a target to the world panel at the specified coordinates.
   *
   * @param x1 the x-coordinate where the target label should be placed
   * @param y1 the y-coordinate where the target label should be placed
   * @param t the character interface representing the target
   */
  private void addTargetToWorldPanel(int x1, int y1, CharacterInterface t) {
    JLabel label = new JLabel(t.getName());
    FontMetrics metrics = label.getFontMetrics(label.getFont());
    int textWidth = metrics.stringWidth(t.getName());
    int textHeight = metrics.getHeight();
    label.setBounds(x1, y1, textWidth + 10, textHeight + 2);
    label.setForeground(Color.RED);
    label.setOpaque(true);
    label.setBackground(Color.BLUE);
    label.setBorder(BorderFactory.createLineBorder(Color.RED));
    label.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        
        String targetInfo = adapter.getTargetInfo();
        if (targetInfo != null) {
          clearInfoPanel();
          setInfoPanel(targetInfo);
        }
        frame.requestFocusInWindow();
      }
    });
    this.panel.add(label);
    this.panel.setComponentZOrder(label, 0);
    this.panel.revalidate();
    this.panel.repaint();
    this.targetRepresent = label;
  }
  
  /*@Override
  public void popGameStart() {
    this.outputArea.setText("");
    this.outputPanel.revalidate();
    this.outputPanel.repaint();
  }*/

  @Override
  public void setInfoPanel(String s) {
    this.infoArea.append(s);
    this.infoArea.append("\n");
    this.infoPanel.revalidate();
    this.infoPanel.repaint();
  }

  @Override
  public void clearInfoPanel() {
    this.infoArea.setText("");
    this.infoPanel.revalidate();
    this.infoPanel.repaint();
  }

  @Override
  public void clearWorldPanel() {
    this.panel.removeAll();
    this.playerRepresents.removeAll(playerRepresents);
    this.targetRepresent = null;
    this.spaceRepresents.removeAll(spaceRepresents);
    this.panel.revalidate();
    this.panel.repaint();
    
  }
  
  @Override
  public void clearOutputPanel() {
    this.outputArea.setText("");
    this.outputPanel.revalidate();
    this.outputPanel.repaint();
  }
}
