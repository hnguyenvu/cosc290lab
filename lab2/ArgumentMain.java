/**
 * Colgate University COSC 290 Lab 2
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class ArgumentMain {

    private static Variable p = new Variable("p");
    private static Variable q = new Variable("q");
    private static Variable r = new Variable("r");
    private static Variable t = new Variable("t");
    private static Variable a = new Variable("a");

    public static void main(String[] args) {

        // you should test your code here by writing small helper methods that test specific cases and then calling
        // them here.  feel free to modify this file however you wish.

        // here are some examples ORIGINAL:
        //displayComplexProposition();
        //checkSimpleArgument();

        // SELFCHECK:
        testDisplay_01();
        testDisplay_02();
        testDisplay_03();

        System.out.println("Starting test case to replace Implications");
        testReplaceIf_01();
        testReplaceIf_02();
        testReplaceIf_03();
<<<<<<< HEAD
        testReplaceIf_04();
=======

        System.out.println("Starting test case for toNNF");
        testNNF_01();
        testNNF_02();
        testNNF_03();

>>>>>>> 0829a3feec49a882606d53e42328ab1b4aa79f7b
    }

    private static void displayComplexProposition() {
        // Construct a complex proposition
        // alpha := ((p | q) & r) => (r & ~t)
        Proposition alpha = Proposition.implies(
                Proposition.conj(
                        Proposition.disj(p, q),
                        r
                ),
                Proposition.conj(r,
                        Proposition.neg(t))
        );
        System.out.println("A complex proposition");
        System.out.println("alpha = " + alpha);
        System.out.println();
    }

    private static void checkSimpleArgument() {
        // Check an argument
        Proposition[] premises = new Proposition[]{
                Proposition.implies(p, q),        // psi1
                p                                 // psi2
        };
        Proposition conclusion = q;  // phi
        // Construct proposition:  (psi1 & psi2) => phi
        System.out.println("Checking an argument: (psi1 & psi2) => phi");
        Proposition psiImpliesPhi = ArgumentChecker.fromArgument(premises, conclusion);
        System.out.println("psiImpliesPhi = " + psiImpliesPhi);
        psiImpliesPhi = NormalForms.replaceImplications(psiImpliesPhi);
        System.out.println("psiImpliesPhi = " + psiImpliesPhi);
        psiImpliesPhi = NormalForms.toNNF(psiImpliesPhi);
        System.out.println("psiImpliesPhi = " + psiImpliesPhi);
        psiImpliesPhi = NormalForms.toCNF(psiImpliesPhi);
        System.out.println("psiImpliesPhi = " + psiImpliesPhi);

        boolean isTautology = ArgumentChecker.checkArgument(premises, conclusion);
        System.out.println("This argument is " + (isTautology? "valid." : "not valid."));
    }

    private static void testDisplay_01() {
        // Construct a complex proposition
        // prop2 := (((p | q) & ~r) & ((p | q) | r))
        Proposition prop1 = Proposition.conj(
                Proposition.conj(
                        Proposition.disj(p, q),
                        Proposition.neg(r)
                ),
                Proposition.disj(
                        Proposition.disj(p, q),
                        r
                )
        );

        System.out.println("prop1 = " + prop1);
        System.out.println();
    }

    private static void testDisplay_02() {
        // Construct a complex proposition
        // prop2 := (~((p | q) & r) & ((p | q) | r))
        Proposition prop2 = Proposition.conj(
                Proposition.neg(
                        Proposition.conj(
                                Proposition.disj(p, q),
                                Proposition.neg(r)
                        )
                ),
                Proposition.disj(
                        Proposition.disj(p, q),
                        r
                )
        );

        System.out.println("prop2 = " + prop2);
        System.out.println();
    }

    private static void testDisplay_03() {
        // Construct a complex proposition
        // prop3 := ~(r | ~(p & q))
        Proposition prop3 = Proposition.disj(
                Proposition.neg(r),
                Proposition.neg(
                        Proposition.conj(p, q)
                )
        );

        System.out.println("prop3 = " + prop3);
        System.out.println();
    }

    private static void testReplaceIf_01() {
        // Construct a complex proposition
        // prop1 := (p => q)
        Proposition prop1 = Proposition.implies(p,q);

        System.out.println("prop1 = " + prop1);
        Proposition prop1_ed = NormalForms.replaceImplications(prop1);
        System.out.println("prop1 edited = " + prop1_ed);
        System.out.println();
    }

    private static void testReplaceIf_02() {
        // Construct a complex proposition
        // prop2 := (~r => (p & q))
        Proposition prop2 = Proposition.implies(
                Proposition.neg(r),
                Proposition.conj(p,q)
        );

        Proposition prop2_ed = NormalForms.replaceImplications(prop2);
        System.out.println("prop2 = " + prop2);
        System.out.println("prop2 edited = " + prop2_ed);
        System.out.println();
    }

    private static void testReplaceIf_03() {
        // Construct a complex proposition
        // prop3 := ((r => q) => (~p & (t => q)))
        Proposition prop3 = Proposition.implies(
                Proposition.implies(r,q),
                Proposition.conj(
                        Proposition.neg(p),
                        Proposition.implies(t, q)
                )
        );

        Proposition prop3_ed = NormalForms.replaceImplications(prop3);
        System.out.println("prop3 = " + prop3);
        System.out.println("prop3 edited = " + prop3_ed);
        System.out.println();
    }

<<<<<<< HEAD
    private static void testReplaceIf_04() {
        // Construct a complex proposition
        // prop1 := ((r => (a => q))) & ((a => p) => (t => q)))
        Proposition prop4 = Proposition.conj(
                Proposition.implies(
                        r,
                        Proposition.implies(a,q)
                ),
                Proposition.implies(
                        Proposition.implies(a, p),
=======
    private static void testNNF_01() {
        // Construct a complex proposition
        // prop1 := (p => q)
        Proposition prop1 = Proposition.implies(p,q);

        System.out.println("prop1 = " + prop1);
        Proposition prop1_ed = NormalForms.toNNF(prop1);
        System.out.println("prop1 toNNF = " + prop1_ed);
        System.out.println();
    }

    private static void testNNF_02() {
        // Construct a complex proposition
        // prop2 := (~r => (p & q))
        Proposition prop2 = Proposition.implies(
                Proposition.neg(r),
                Proposition.conj(p,q)
        );

        Proposition prop2_ed = NormalForms.toNNF(prop2);
        System.out.println("prop2 = " + prop2);
        System.out.println("prop2 toNNF = " + prop2_ed);
        System.out.println();
    }

    private static void testNNF_03() {
        // Construct a complex proposition
        // prop3 := ((r => q) => (~p & (t => q)))
        Proposition prop3 = Proposition.implies(
                Proposition.implies(r,q),
                Proposition.conj(
                        Proposition.neg(p),
>>>>>>> 0829a3feec49a882606d53e42328ab1b4aa79f7b
                        Proposition.implies(t, q)
                )
        );

<<<<<<< HEAD
        Proposition prop4_ed = NormalForms.replaceImplications(prop4);
        System.out.println("prop4 = " + prop4);
        System.out.println("prop4 edited = " + prop4_ed);
        System.out.println();
    }
=======
        Proposition prop3_ed = NormalForms.toNNF(prop3);
        System.out.println("prop3 = " + prop3);
        System.out.println("prop3 toNNF = " + prop3_ed);
        System.out.println();
    }

>>>>>>> 0829a3feec49a882606d53e42328ab1b4aa79f7b
}
