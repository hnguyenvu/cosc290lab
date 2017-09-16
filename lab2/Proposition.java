/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 * @author Michael Hay
 */
public class Proposition {

    /**
     * Returns a string representation of a proposition.  The string representation should include parentheses to
     * indicate the operator precedence.  Example:
     * (p & q) | r
     * TODO As a challenge problem, implement this method such that parens are dropped if the operators are of the same
     * type. Example:
     * (p & q & r) | (q | ~r)
     * @return a string representation of proposition
     */
    @Override
    public String toString() {
        // you should not modify this line, instead implement toString in all subclasses.
        return "This is a proposition.  Subclasses should override this method!";
    }

    // --- HELPER METHODS FOR CHECKING THE STRUCTURE OF A COMPLEX PROPOSITION ---

    /**
     * @return true is the proposition is of the form p for some variable p.
     */
    public boolean isVariable() {
        return getConnective() == null;
    }

    /**
     * @return true is the proposition is of the form ~phi for some proposition phi.
     */
    public boolean isNotProposition() {
        return hasConnective(Connective.NOT);
    }

    /**
     * @return true is the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is
     * some logical connective.
     */
    public boolean isBinaryProposition() {
        return isOrProposition() || isAndProposition() || isIfProposition();
    }

    /**
     * @return true is the proposition is of the form phi1 | phi2.
     */
    public boolean isOrProposition() {
        return hasConnective(Connective.OR);
    }

    /**
     * @return true is the proposition is of the form phi1 & phi2.
     */
    public boolean isAndProposition() {
        return hasConnective(Connective.AND);
    }

    /**
     * @return true is the proposition is of the form phi1 => phi2.
     */
    public boolean isIfProposition() {
        return hasConnective(Connective.IF);
    }

    /**
     * If the proposition is binary -- i.e., it is of the form  phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective, then OPERATOR is returned.
     * Example: if proposition is p & q, it returns &.
     *
     * @return the logical connective associated with this sentence if it has one, otherwise null
     */
    public Connective getConnective() {
        return null;
    }

    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective -- then phi1 is returned.
     *
     * Example: if proposition is (p | r) & q, it returns (p | r).
     *
     * @return the left argument if this sentence is a binary proposition, otherwise null
     */
    public Proposition getFirst() {
        return null;
    }

    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective -- then phi2 is returned.
     *
     * Example: if proposition is (p | r) & q, it returns q.
     *
     * @return the right argument if this sentence is a binary proposition, otherwise null
     */
    public Proposition getSecond() {
        return null;
    }

    /**
     * Checks whether the sentence has connective c at its root.
     *
     * Example: if proposition is (p | r) & q, then hasConnective(&) is true, but hasConnective(|) is false.
     *
     * @param c the connective type
     * @return true if the root of the sentence is equal to c
     */
    public boolean hasConnective(Connective c) {
        return getConnective() != null && getConnective().equals(c);
    }


    // --- HELPER METHODS FOR BUILDING COMPLEX PROPOSITIONS ---

    /**
     * Returns proposition that is negation of p
     * @param p
     * @return proposition that is ~p
     */
    public static Proposition neg(Proposition p) {
        return new Neg(p);
    }

    /**
     * Returns proposition that is conjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p & q
     */
    public static Proposition conj(Proposition p, Proposition q) {
        return new BinOp(Connective.AND, p, q);
    }

    /**
     * Returns proposition that is disjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p | q
     */
    public static Proposition disj(Proposition p, Proposition q) {
        return new BinOp(Connective.OR, p, q);
    }

    /**
     * Returns proposition that is p implies q
     * @param p
     * @param q
     * @return proposition that is p => q
     */
    public static Proposition implies(Proposition p, Proposition q) {
        // look at the other examples above for tips on how to write this
        throw new UnsupportedOperationException("implement me!");
    }
}
