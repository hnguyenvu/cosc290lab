/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Variable extends Proposition {
    private String symbol;

    public Variable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol; // TODO
        //throw new UnsupportedOperationException("implement me!");
    }

    // ---- implementations of equals and hashcode in case you want to put variables into hash sets/maps/etc.
    // ---- hint: you will probably want to do this at some point!

    @Override
    public boolean equals(Object other) {
        return (other instanceof Variable) && symbol.equals(((Variable)other).symbol);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }
}
