/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public enum Connective {
    NOT("~"),
    AND("&"),
    OR("|"),
    IF("=>");

    private String connectiveSymbol;

    Connective(String connectiveSymbol) {
        this.connectiveSymbol = connectiveSymbol;
    }

    @Override
    public String toString() {
        return connectiveSymbol;
    }
}
