package theworldcontroller;

import theworld.TheWorldFacade;

/**
 * The ControllerInterface defines the contract for a controller that manages
 * the game flow and interactions with the game world.
 */
public interface ControllerInterface {

  /**
   * Initiates and plays the game using the provided world facade and
   * specification.
   *
   * @param twf the facade representing the game world
   * @param specification the specification string to configure the game
   * @throws IllegalStateException if the game cannot be played due to an illegal
   *                               state
   */
  void playGame(TheWorldFacade twf, String specification) throws IllegalStateException;

  /**
   * stop the game by mandatory.
   */
  void stopGame();
}
