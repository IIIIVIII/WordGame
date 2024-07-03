/**
 * Exception to be thrown when files provided for word guess is malformed.
 *
 * @author Purdue CS
 * @version 2023-07-15
 */
public class InvalidGuessException extends Exception {
    public InvalidGuessException() {
    }

    public InvalidGuessException(String message) {
        super(message);
    }
}

