/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class BinOp extends Proposition {

    private Connective op;
    private Proposition phi_1;
    private Proposition phi_2;

    public BinOp(Connective op, Proposition p, Proposition q) {
        this.op = op;
        this.phi_1 = p;
        this.phi_2 = q;
    }

    @Override
    public Connective getConnective() {
        return op;
    }

    @Override
    public Proposition getFirst() {
        return phi_1;
    }

    @Override
    public Proposition getSecond() {
        return phi_2;
    }

    @Override
    public String toString() {
        /**
         * recursively print first proposition phi_1, then the op, then phi_2
         * Base case:
         * (1) : if one of the two
         */

        // work sample 1: parentheses for everything
        return "(" + phi_1.toString() +  " " + op.toString() + " " + phi_2.toString() + ")";
        //can use this later
        //return phi_1.toString() + op.toString() + phi_2.toString();
        //throw new UnsupportedOperationException("implement me!");
    }
}
