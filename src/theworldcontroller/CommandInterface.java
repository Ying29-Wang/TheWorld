package theworldcontroller;

import java.util.Scanner;
import theworld.TheWorldFacade;

/**
 * This interface represents a command that will be executed during the game.
 * Implementations of this interface should define the specific behavior of the
 * command.
 */
public interface CommandInterface {

  /**
   * Each command is executed with access to the model, an output destination, and
   * an input scanner.
   * 
   * @param twf  The model access object {@link TheWorldFacade} to be used by the
   *             command.
   * @param out  The output destination where the command can write its results.
   * @param scan The input scanner to read any necessary input for the command.
   * @return true if the command is successfully executed, otherwise false.
   */
  boolean execute(TheWorldFacade twf, Appendable out, Scanner scan);

}
