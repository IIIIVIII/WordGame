/**
 * Exception to be thrown when files provided for word guess is malformed.
 *
 * @author Purdue CS
 * @version 2023-07-15
 */
public class InvalidWordException extends Exception {
    public InvalidWordException(String message) {
        super(message);
    }
}
