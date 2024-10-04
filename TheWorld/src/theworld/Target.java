package theworld;
 
/**
 * This class represents the target of the game. It provide the name and health number.
 * Also, it gives the current position and target position of the target.
 */
public class Target implements CharacterInterface {
  private String name;
  private Space space;
  private int health; 

  /**
   * Construct the target with a given name, amount of health.
   * @param name of the target
   * @param health of the target
   * @throws IllegalArgumentException illegal input exception
   */
  public Target(String name, int health) throws IllegalArgumentException{
    
    if ((health > 0) && (!name.equals(null))) {
      this.name = name;
      this.health = health;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.name;
  }

  @Override
  public Space getSpace() {
    // TODO Auto-generated method stub
    return this.space;
  }

  @Override
  public Space move(SpaceInterface space) {
    // TODO Auto-generated method stub
    this.space = (Space) space;
    return this.space;
  }
  
  public int getHealth() {
    return this.health;
  }

  public String toString() {
    return String.format("%s with %d health, and is staying at room %s", 
        this.name, this.health, this.space.getName());
  }
}
