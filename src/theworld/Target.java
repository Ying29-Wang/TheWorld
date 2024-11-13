package theworld;

import java.util.Objects;

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
  public Target(String name, int health) throws IllegalArgumentException {

    if ((health > 0) && (!name.equals(null))) {
      this.name = name;
      this.health = health;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Space getSpace() {
    return this.space;
  }

  @Override
  public Space move(SpaceInterface space) {
    this.space = (Space) space;
    return this.space;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public void setHealth(int health) {
    this.health = health;
  }

  @Override
  public String toString() {
    return String.format("%s with %d health, and is staying at room %s", 
        this.name, this.health, this.space.getName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { 
      return true;
    }

    if (!(o instanceof CharacterInterface)) {
      return false;
    }

    Target that = (Target) o; 
    return this.health == that.health 
        && Objects.equals(this.name, that.name) 
        && Objects.equals(this.space, that.space);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.health, this.space);
  }
}
