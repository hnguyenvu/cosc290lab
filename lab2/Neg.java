/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Neg extends Proposition {

    private Proposition phi;

    public Neg(Proposition p) {
        this.phi = p;
    }

    @Override
    public Connective getConnective() {
        return Connective.NOT;
    }

    @Override
    public Proposition getFirst() {
        return phi;
    }

    @Override
    public String toString() {
        return "~" + phi.toString();
        //throw new UnsupportedOperationException("implement me!");
    }
}
