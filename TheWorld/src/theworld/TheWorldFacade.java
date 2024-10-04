package theworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * This class provides a facade for creating and managing the model of the world.
 * It including adding items, spaces, and targets 
 * It also handles the visual representation of the world.
 */
public class TheWorldFacade {

  private TheWorld world;

  /**
   * Creates a new instance of {@code TheWorld} with the given name, rows, and columns.
   * 
   * @param name    the name of the world
   * @param row     the number of rows in the world
   * @param column  the number of columns in the world
   * @return       the current instance of the Facade
   */
  private TheWorldFacade createTheWorld(String name, int row, int column) {
    this.world = new TheWorld(name, row, column);

    return this;
  }

  /**
   * Adds a item to the current instance of TheWorldFacade.
   * 
   * @param id        the ID of the item
   * @param name      the name of the item
   * @param damage    the damage value of the item
   * @param spaceId   the ID of the space where the item is located
   * @return          the current instance of facade
   */
  private TheWorldFacade addItemToTheWorld(int id, String name, int damage, int spaceId) {
    Item it = ItemFactory.createItem(id, name, damage);
    it.setSpace((Space) (this.world.getSpaces().get(spaceId)));
    it.getSpace().setItem(it);
    this.world.addItem(it);
    return this;
  }

  /**
   * Adds a specified space to the current TheWorld.
   * 
   * @param id      the ID of the space
   * @param name    the name of the space
   * @param y1      the y-coordinate of the upper left corner
   * @param x1      the x-coordinate of the upper left corner
   * @param y2      the y-coordinate of the lower right corner
   * @param x2      the x-coordinate of the lower right corner
   * @return        the current instance of TheWorldFacade
   */
  private TheWorldFacade addSpaceToTheWorld(int id, String name, int y1, int x1, int y2, int x2) {
    Space space = SpaceFactory.createSpace(id, name, new int[] { y1, x1 }, new int[] { y2, x2 });

    this.world.addSpace(space);
    return this;
  }

  /**
   * Adds a target character to the current instance of TheWorld.
   * 
   * @param name    the name of the target
   * @param health  the health value of the target
   * @return        the current instance of TheWorldFacade
   */
  private TheWorldFacade addTargetToTheWorld(String name, int health) {
    Target target = new Target(name, health);
    this.world.addTarget(target);
    return this;
  }

  public TheWorldFacade() {
  }

  /**
   * Moves the target character in the world according to the specified mode 
   * and the number of spaces to visit.
   * 
   * @param mode                 the mode of movement (sequence, random, staystill)
   * @param howManySpaceToVisit  the number of spaces to visit
   * @return                    a string describing the movement of the target
   */
  public String moveTargetInTheWorld(Mode mode, int howManySpaceToVisit) {
    StringBuffer totalOutPut = new StringBuffer();
    if (mode == Mode.sequence) {
      int round = (int) (howManySpaceToVisit / this.world.getSpaces().size());
      int mod = howManySpaceToVisit % this.world.getSpaces().size();
      for (int i = 0; i < round; i++) {
        int j = 0;
        for (; j < this.world.getSpaces().size(); j++) {
          this.world.getTarget().move(((Space) (this.world.getSpaces().get(j))));
          totalOutPut.append(String.format("the character is in the %s\n",
              ((Space) (this.world.getSpaces().get(j))).toString()));
        }
      }
      for (int i = 0; i < mod; i++) {
        totalOutPut.append(String.format("the character is in the %s\n",
            ((Space) (this.world.getSpaces().get(i))).toString()));
      }
    }
    return totalOutPut.toString();
  }

  /**
   * Draws the image of {@code TheWorld} and saves it as a PNG file 
   * with the specified filename.
   * 
   * @param fw  the filename to save the drawn representation
   */
  public void drawTheWorld(String fw) {
    if (this.world != null) {
      int height = this.world.getRow() * 20 + 100;
      int width = this.world.getColumn() * 20 + 100;

      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      // Get the graphics context to draw on the image
      Graphics g = image.getGraphics();
      // Fill with a solid color
      for (SpaceInterface sp : this.world.getSpaces()) {
        int y1 = ((Space) sp).getUpLeft()[0] * 20 - 9 + 50;
        int x1 = ((Space) sp).getUpLeft()[1] * 20 - 9 + 50;
        int y2 = ((Space) sp).getLowRight()[0] * 20 + 9 + 50;
        int x2 = ((Space) sp).getLowRight()[1] * 20 + 9 + 50;
        // drawing the space
        g.setColor(Color.BLACK);
        g.drawRect(x1, y1, x2 - x1, y2 - y1);
        g.setColor(new Color(199, 203, 205));
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
        // drawing the name on the image
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 10));
        g.setColor(Color.BLACK);
        String text = ((Space) sp).getName();
        // Get the metrics for the current font
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // Calculate the x and y coordinates to up left the text in the rectangle
        int textX = x1 + 2;
        int textY = y1 + metrics.getAscent();
        // Draw the text inside the rectangle
        g.drawString(text, textX, textY);
      }
      // Save the image to a file
      try {
        ImageIO.write(image, "png", new File(fw));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Reads a specification file from a Reader and parse it to create 
   * an instance of TheWorld.
   * 
   * @param fr  the {@code Reader} from which to read the specifications
   * @return    the created instance of TheWorld
   */
  public TheWorld parseTheWorld(Readable fr) {
    BufferedReader br;
    if (fr instanceof FileReader) {
      br = new BufferedReader((FileReader) fr);
    } else {
      br = new BufferedReader((StringReader) fr);
    }
    try {
      String firstline;
      String spaceline;
      String mymap = new String();
      String character = new String();
      int column = 0;
      int row = 0;
      int health = 0;
      int spaceNumber = 0;
      String[] word;
      // parse first line to create the world
      if ((firstline = br.readLine().trim()) != null) {
        word = firstline.split("\\s+");
        if ((!word[0].isEmpty()) && (word[0].matches("\\d+"))) {
          row = Integer.parseInt(word[0]);
        }
        if ((!word[1].isEmpty()) && (word[1].matches("\\d+"))) {
          column = Integer.parseInt(word[1]);
        }
        if (!word[2].isEmpty()) {
          for (int i = 2; i < word.length; i++) {
            mymap += word[i] + " ";
          }
        }
        createTheWorld(mymap.trim(), row, column);
      }
      
      // parse second line to add target into the world
      String secondline;
      if ((secondline = br.readLine().trim()) != null) {
        word = secondline.split("\\s+");
        if ((!word[0].isEmpty()) && (word[0].matches("\\d+"))) {
          health = Integer.parseInt(word[0]);
        }
        if (!word[1].isEmpty()) {
          for (int i = 1; i < word.length; i++) {
            character += word[i] + " ";
          }
        }
        addTargetToTheWorld(character.trim(), health);
      }
      // parse third line to add Spaces into the world
      String thirdline;
      if ((thirdline = br.readLine().trim()) != null) {
        word = thirdline.split("\\s+");
        if ((!word[0].isEmpty()) && (word[0].matches("\\d+"))) {
          spaceNumber = Integer.parseInt(word[0]);
          // read every line below to create the space in the map
          // System.out.println(spaceNumber);
          for (int i = 0; i < spaceNumber; i++) {
            spaceline = br.readLine().trim();
            int x1 = 0;
            int y1 = 0;

            word = spaceline.split("\\s+");
            if ((!word[0].isEmpty()) && (word[0].matches("\\d+"))) {
              x1 = Integer.parseInt(word[0]);
            }
            if ((!word[1].isEmpty()) && (word[1].matches("\\d+"))) {
              y1 = Integer.parseInt(word[1]);
            }

            int x2 = 0;
            int y2 = 0;
            String spaceName = new String();
            if ((!word[2].isEmpty()) && (word[2].matches("\\d+"))) {
              x2 = Integer.parseInt(word[2]);
            }
            if ((!word[3].isEmpty()) && (word[3].matches("\\d+"))) {
              y2 = Integer.parseInt(word[3]);
            }
            if (!word[4].isEmpty()) {
              for (int j = 4; j < word.length; j++) {
                spaceName += word[j] + " ";
              }
            }
            addSpaceToTheWorld(i, spaceName.trim(), x1, y1, x2, y2);
          }
        }
      }

      String fourthline;
      String itemline;
      int itemNumber = 0;

      if ((fourthline = br.readLine().trim()) != null) {
        word = fourthline.split("\\s+");
        if ((!word[0].isEmpty()) && (word[0].matches("\\d+"))) {
          itemNumber = Integer.parseInt(word[0]);
          for (int i = 0; i < itemNumber; i++) {
            itemline = br.readLine().trim();
            int spaceId = 0;
            int damage = 0;
            word = itemline.split("\\s+");
            String itemName = new String();
            if ((!word[0].isEmpty()) && (word[0].matches("\\d+"))) {
              spaceId = Integer.parseInt(word[0]);
            }
            if ((!word[1].isEmpty()) && (word[1].matches("\\d+"))) {
              damage = Integer.parseInt(word[1]);
            }
            if (!word[2].isEmpty()) {
              for (int j = 2; j < word.length; j++) {
                itemName += word[j] + " ";
              }
            }
            addItemToTheWorld(i, itemName.trim(), damage, spaceId);
          }
        }
      }
      /*
       * calculate neighbors for every space in the map if
       * (this.world.getSpaces().size()>0) {
       * //System.out.println(this.world.getSpaces().size()); for(SpaceInterface sp :
       * this.world.getSpaces()) {
       * ((Space)sp).calculateNeighbors(this.world.getSpaces()); } }
       */
    } catch (IOException e) {
      e.printStackTrace(); // Handle exceptions
      return null;
    }
    return this.world;
  }
}
