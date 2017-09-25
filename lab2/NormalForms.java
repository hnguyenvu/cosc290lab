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
         * 2 scenarios: (1) phi is not a NOT Proposition (meaning a normal prop w/o a NOT in the outer layer)
         * and (2) phi is a Not Proposition
         * TODO fix this logic again
         * (1): phi is not a Not Proposition
         *      (a) phi is an IF
         *          remove If and then convert it to NNF forms
         *      (b) phi is an AND
         *          take all propositions within phi (getFirst and getSecond) and convert them to NNF form
         *          return a CONJUNCTION of the two compoments
         *      (c) phi is an OR
         *          take all propositions within phi (getFirst and getSecond) and convert them to NNF form
         *          return a DISJUNCTION of the two compoments
         *      (d) phi is a variable
         *          just return phi
         *      (e) not one of the above -> contains some other connectives not OR, AND, NOT, IF
         *          throw errors
         *
         * (2): phi is a Not Proposition. Proposition p = phi.getFirst()
         *      (a) p is an NOT
         *          remove the two not at Phi and p level, convert p.getFirst() to NNF
         *      (b) p is an IF
         *          remove If and then convert it to NNF forms
         *      (c) p is an AND
         *          take all propositions within phi (getFirst and getSecond) and convert them to NNF form
         *          return a DISJUNCTION of the two compoments
         *      (d) p is an OR
         *          take all propositions within phi (getFirst and getSecond) and convert them to NNF form
         *          return a CONJUNCTION of the two compoments
         *      (e) p is a variable
         *          just return p
         *      (f) not one of the above -> contains some other connectives not OR, AND, NOT, IF
         *          throw errors
         */

        // case (1): phi is not a Not Proposition (does not have ~ in the outest layer)
        if (!phi.isNotProposition()) {
            //if (phi.isIfProposition())
                //return toNNF(replaceImplications(phi));
            if (phi.isAndProposition())
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
            //else if (phi.getFirst().isIfProposition())
                //return toNNF(replaceImplications(phi));
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
     * @param phi a proposition to transform
     * @return psi, a proposition that has no => and is logically equivalent to phi
     * @throws IllegalPropException if phi is not in NNF
     */
    public static Proposition distOrOverAnd(Proposition phi) {

        System.out.println(phi);

        //
        if (phi.isVariable())
            return phi;

        else if (phi.isNotProposition()) {
            if (!phi.getFirst().isVariable())
                throw new IllegalPropException("Proposition is not in NNF");
            else
                return phi;
        }

        // TODO >> the case where there's trouble
        else if (phi.isOrProposition()) {

            //System.out.println("isOr");

            Proposition p1 = phi.getFirst();
            Proposition p2 = phi.getSecond();
            // try to keep p1 the same, break down p2 until it's variable/NotProp

            /** Unnecessary as the first two base cases should take care of this
            if (p2.isVariable())
                return //distOrOverAnd(
                        Proposition.disj(
                                distOrOverAnd(p1),
                                p2
                        //)
                );

            else if (p2.isNotProposition()) {
                if (p2.getFirst().isVariable())
                    return //distOrOverAnd(
                            Proposition.disj(
                                    distOrOverAnd(p1),
                                    p2

                            //)
                    );
                else throw new IllegalPropException("Proposition is not in NNF");
            }

            */

            // case p1 || (p2.getFirst || p2.getSecond)
            // ==  p1 || p2.getFirst || p2.getSecond
            /** TODO
            else if (p2.isOrProposition())
                return //distOrOverAnd(
                        Proposition.disj(
                                Proposition.disj(
                                        distOrOverAnd(p1),
                                        distOrOverAnd(p2.getFirst())
                                ),
                                Proposition.disj(
                                        distOrOverAnd(p1),
                                        distOrOverAnd(p2.getSecond())
                                )
                        //)
                );
            */

            if (p2.isAndProposition()) {
                /** Unnecessary
                if (p2.isVariable())
                    return //distOrOverAnd(
                            Proposition.disj(
                                    distOrOverAnd(p1),
                                    p2
                                //)
                    );

                else if (p2.isNotProposition()) {
                    if (p2.getFirst().isVariable())
                        return //distOrOverAnd(
                                Proposition.disj(
                                        distOrOverAnd(p1),
                                        p2
                                    //)
                        );
                    else throw new IllegalPropException("Proposition is not in NNF");
                }

                if (p2.isAndProposition()) {
                    return Proposition.conj(
                            Proposition.disj(
                                    distOrOverAnd(p1),
                                    p2.getFirst()
                            ),
                            Proposition.disj(
                                    distOrOverAnd(p1),
                                    p2.getSecond()
                            )
                    );
                }

                else if (p2.isOrProposition()) {
                    return Proposition.disj(
                            Proposition.disj(
                                    distOrOverAnd(p1),
                                    p2.getFirst()
                            ),
                            Proposition.disj(
                                    distOrOverAnd(p1),
                                    p2.getSecond()
                            )
                    );
                }
                else throw new IllegalPropException("Proposition is not in NNF");
                */
                return Proposition.conj(
                        distOrOverAnd(Proposition.disj(
                                p1,
                                p2.getFirst()
                        )),
                        distOrOverAnd(Proposition.disj(
                                p1,
                                p2.getSecond()
                        ))
                );
            }

            //else throw new IllegalPropException("Proposition is not in NNF");
            // consider the case where p1 is AND and not p2 - have exhausted p2
            else if (p1.isAndProposition()) {
                return Proposition.conj(
                        distOrOverAnd(Proposition.disj(
                                p1.getFirst(),
                                p2
                        )),
                        distOrOverAnd(Proposition.disj(
                                p1.getSecond(),
                                p2
                        ))
                );
            }

            else return Proposition.disj(
                    p1,
                    p2
            );
        }

        else if (phi.isAndProposition()) {
            //throw new UnsupportedOperationException("implement me!");
            System.out.println("isAnd");


            Proposition p1 = phi.getFirst();
            Proposition p2 = phi.getSecond();

            if (p2.isVariable())
                return Proposition.conj(
                        p2,
                        distOrOverAnd(p1)
                );

            else if (p2.isNotProposition()) {
                if (p2.getFirst().isVariable())
                    return Proposition.conj(
                            p2,
                            distOrOverAnd(p1)
                    );
                else throw new IllegalPropException("Proposition is not in NNF");
            }

            else if (p2.isOrProposition() || p2.isAndProposition())
                return Proposition.conj(
                        //distOrOverAnd(p1),
                        distOrOverAnd(p1),
                        distOrOverAnd(p2)
                );
            else throw new IllegalPropException("Proposition is not in NNF");
        }

        else throw new IllegalPropException("Proposition is not in NNF");
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
