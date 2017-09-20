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
         * TODO (add in number of base case) base cases
         */

        // case Variable
        // phi = p
        if (phi.isVariable()) {
            return phi;
        }

        // case Neg
        // phi = ~p, then ~replaceImplications(p)
        else if (phi.isNotProposition()) {
            return Proposition.neg(
                    replaceImplications(phi.getFirst())
            );
        }

        // case BinOp: If
        // phi := ( p => q)
        // throw new UnsupportedOperationException("implement me!");
        else  if (phi.isIfProposition()) {
            return Proposition.disj(
                    Proposition.neg(replaceImplications(phi.getFirst())),
                    replaceImplications(phi.getSecond())
            );
        }

        // case BinOp: And
        // phi := (p & q)
        else  if (phi.isAndProposition()) {
            return Proposition.conj(
                    replaceImplications(phi.getFirst()),
                    replaceImplications(phi.getSecond())
            );
        }

        // case BinOp: Or
        // phi := (p | q)
        else {
            return Proposition.disj(
                    replaceImplications(phi.getFirst()),
                    replaceImplications(phi.getSecond())
            );
        }
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
     * TODO
     * @throws IllegalPropException if phi contains a connective that is not in the set {&, |, ~}.
     */
    public static Proposition toNNF(Proposition phi) {
        /**
         * 2 scenarios: (1) phi is not a BinOp and (2) phi is a BinOp
         * TODO fix this logic again
         * (1): phi is not a BinOp
         *      (a) phi is a non BinOp (without not) == a variable
         *         return: phi
         *      (b) phi is a not + non BinOp == can have multiple not within the non BinOp
         *          (b.1) non BinOp == variable
         *              base case: return phi
         *          (b.2) non BinOp is another not + variable
         *              base case: return phi.getFirst() (eliminate the 2 nots)
         *          (b.3) non BinOp is not + non Bin
         *              recursion
        // (2): phi is a BinOp
        //      (a) phi is a BinOp (a connective b)
        //      (b) phi is a ~(a connective b)
        */

        // case (1): phi is not a Not Proposition (does not have ~ in the outest layer)
        if (!phi.isNotProposition()) {
            if (phi.isIfProposition())
                return toNNF(replaceImplications(phi));
            else if (phi.isAndProposition())
                return Proposition.conj(
                        toNNF(phi.getFirst()),
                        toNNF(phi.getSecond())
                );
            else if (phi.isOrProposition())
                return Proposition.disj(
                        toNNF(phi.getFirst()),
                        toNNF(phi.getSecond())
                );
            else if (phi.isVariable())
                return phi;
            else
                throw new IllegalPropException("Proposition contains a connective that is not in the set {&, |, ~}");
        }

        // case (2): phi is a Not Proposition (has a ~ in the outer layer)
        else {
            if (phi.getFirst().isNotProposition())
                return toNNF(phi.getFirst().getFirst());
            else if (phi.getFirst().isIfProposition())
                return toNNF(replaceImplications(phi));
            else if (phi.getFirst().isAndProposition())
                return Proposition.disj(
                        toNNF(Proposition.neg(
                                phi.getFirst().getFirst())
                        ),
                        toNNF(Proposition.neg(
                                phi.getFirst().getSecond())
                        )
                );
            else if (phi.getFirst().isOrProposition())
                return Proposition.conj(
                        toNNF(Proposition.neg(
                                phi.getFirst().getFirst())
                        ),
                        toNNF(Proposition.neg(
                                phi.getFirst().getSecond())
                        )
                );
            else if (phi.getFirst().isVariable()) // (phi.getFirst().isVariable())
                return phi;
            else
                throw new IllegalPropException("Proposition contains a connective that is not in the set {&, |, ~}");
        }
        //throw new UnsupportedOperationException("implement me!");
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
        //throw new UnsupportedOperationException("implement me!");
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
