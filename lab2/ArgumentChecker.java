import java.util.HashSet;

import java.util.Set;
/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class ArgumentChecker {

    /**
     * Given a collection of premises and a conclusion, check whether conclusion follows
     * logically from premises.
     * @param premises propositions psi1, psi2, ..., psiN
     * @param conclusion the conclusion phi
     * @return true if the argument is valid, i.e., whether (psi1 & psi2 & ... & psiN) => phi is a tautology
     */
    public static boolean checkArgument(Proposition[] premises, Proposition conclusion) {
        Proposition psiImpliesPhi = fromArgument(premises, conclusion);
        return isTautology(psiImpliesPhi);
        //throw new UnsupportedOperationException("implement me!");
        // hint: how do you check whether conclusion phi follows logically from premises (psi1 & psi2 & ... & psiN)?
    }

    /**
     * Makes a single proposition from all the premises and the conclusion.  This proposition
     * should be of the form (psi1 & psi2 & ... & psiN) => phi.
     * @param premises propositions psi1, psi2, ..., psiN where each pi is a premise.
     * @param conclusion the conclusion phi
     * @return a proposition of the form (psi1 & psi2 & ... & psiN) => phi
     */
    public static Proposition fromArgument(Proposition[] premises, Proposition conclusion) {
        Proposition psi = premises[0];

        for (int i = 1; i < premises.length(); i++) {
            psi = Proposition.conj(psi, premises[i]);
        }

        return Proposition.implies(psi, conclusion);
        //throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Checks whether proposition phi is a tautology (true under all possible truth assignments)
     * @param phi the proposition to check
     * @return true if phi is a tautology and false otherwise
     */
    public static boolean isTautology(Proposition phi) {
        Proposition phi_CNF = NormalForms.toCNF(phi);

        

        //throw new UnsupportedOperationException("implement me!");
    }
}
