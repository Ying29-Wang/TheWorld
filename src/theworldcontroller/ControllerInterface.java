package theworldcontroller;

import theworld.TheWorldFacade;

/**
 * The ControllerInterface defines the contract for starting and managing the
 * game flow for TheWorld.
 * Implementations of this interface will handle the interaction with the game
 * world through the provided
 * TheWorldFacade instance and manage the game based on the given specification
 * file.
 */
/**
 * The ControllerInterface defines the contract for a controller that manages
 * the game flow and interactions with the game world.
 */
public interface ControllerInterface {

  /**
   * Initiates and plays the game using the provided world facade and
   * specification.
   *
   * @param twf           the facade representing the game world
   * @param specification the specification string to configure the game
   * @throws IllegalStateException if the game cannot be played due to an illegal
   *                               state
   */
  void playGame(TheWorldFacade twf, String specification) throws IllegalStateException;
}
