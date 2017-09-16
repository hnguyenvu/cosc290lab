/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */

/**
 * This exception is thrown whenever a proposition processor encounters
 * an unexpected propositional form.
 */
public class IllegalPropException extends RuntimeException {
    public IllegalPropException() {
        super();
    }
    public IllegalPropException(String message) {
        super(message);
    }
}
