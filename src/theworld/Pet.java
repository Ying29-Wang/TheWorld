package theworld;

/**
 * The Pet class represents a pet with a name and a space it occupies.
 * It implements the PetInterface.
 */
public class Pet implements PetInterface {
  private String name;
  private SpaceInterface space;

  /**
   * Constructs a new Pet with the specified name.
   *
   * @param name the name of the pet
   * @throws IllegalArgumentException if the name is null
   */
  public Pet(String name) throws IllegalArgumentException {
    if (name != null) {
      this.name = name;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public SpaceInterface getSpace() {
    return this.space;
  }

  @Override
  public boolean move(SpaceInterface space) {
    if ((space != null) && (this.space != null)) {
      this.space.setVisible(true);
      this.space = space;
      this.space.setVisible(false);
      return true;
    } else if (space != null) {
      this.space = space;
      this.space.setVisible(false);
      return true;
    }
    return false;
  }

}
