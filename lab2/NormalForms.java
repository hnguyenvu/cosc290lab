/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class NormalForms {

    /**
     * Constructs a proposition psi such that (1) psi does not contain any implications (=>) and (2) psi is logically
     * equivalent to phi.
     * @param phi a proposition to transform
     * @return psi, a proposition that has no => and is logically equivalent to phi
     */
    public static Proposition replaceImplications(Proposition phi) {
        /**
         * Proposition has 3 types: (1) Variable, (2) Neg, and (3) BinOps;
         * 3 base cases
         */

        // case Variable
        if (phi.isVariable()) {
            return phi;
        }

        // case Neg
        else if {
            return neg(
                    replaceImplications(phi.getFirst())
            );
        }

        // case 

        // throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Constructs a proposition psi such that (1) psi is in negation normal form  and (2) psi is logically
     * equivalent to phi.  Expects a proposition that has only &, | and ~ connectives.
     *
     * A sentence is in negation normal form if the negation connective is applied only to atomic propositions (i.e.
     * variables) and not to more complex expressions, and furthermore, the only connectives allowed are {&, |, ~}.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition that has no => and is logically equivalent to phi
     * @throws IllegalPropException if phi contains a connective that is not in the set {&, |, ~}.
     */
    public static Proposition toNNF(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Constructs a proposition psi such that (1) psi is in CNF and (2) psi is logically
     * equivalent to phi.  Expects a proposition phi that is in NNF.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition that has no => and is logically equivalent to phi
     * @throws IllegalPropException if phi is not in NNF
     */
    public static Proposition distOrOverAnd(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Constructs a proposition psi such that (1) psi is conjunctive normal form and (2) psi is logically equivalent to
     * phi.
     * @param phi a proposition to transform to CNF
     * @return psi, a proposition in CNF that is logically equivalent to phi
     */
    public static Proposition toCNF(Proposition phi) {
        // this has been implemented for you!
        return distOrOverAnd(toNNF(replaceImplications(phi)));
    }
}
