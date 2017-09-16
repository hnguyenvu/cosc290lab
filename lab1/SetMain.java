/******************************************************************************
 * Compilation:  javac SetMain.java
 * Execution:    java SetMain
 * Dependencies: Set.java, SetImpl.java
 *
 * Version 0.1,  2017
 * @author Michael Hay
 * @student Nam Nguyen, COSC 290
 */

/**
 * A simple program to demonstrate the functionality of sets.
 */
public class SetMain {

    /**
     * Demonstrates that the set implementation works correctly.
     * @param args should be empty
     */
    public static void main(String[] args) {
        /** ORIGINAL
        // original
        System.out.println("Initialize S = { a, b, c }");
        Set<String> S = new SetImpl<>(new String[] {"a", "b", "c", "a"});
        System.out.println("|S| = " + S.cardinality());
        System.out.println("a in S = " + S.in("a"));
        System.out.println("\n");
        System.out.println("Initialize T = { c, d }");
        Set<String> T = new SetImpl<>(new String[] {"c", "d"});
        System.out.println("|S union T| = " + S.union(T).cardinality());
        */

        /**
        // first testing case: 1 elementsToAdd
        System.out.println("Initialize A = { a }");
        Set<String> A = new SetImpl<>(new String[] {"a"});
        System.out.println("|A| = " + A.cardinality());
        System.out.println("a in A = " + A.in("a"));
        System.out.println("b in A = " + A.in("b"));
        System.out.println("\n");

        // 1 elementsToAdd, 1 duplicates
        System.out.println("Initialize B = { a }, B input has duplicates { a, a }");
        Set<String> B = new SetImpl<>(new String[] {"a", "a"});
        System.out.println("|B| = " + B.cardinality());
        System.out.println("a in B = " + B.in("a"));
        System.out.println("b in B = " + B.in("b"));
        System.out.println("\n");
        //System.out.println("c in B = " + B.in("c"));

        // 3 elementsToAdd, 2 duplicates
        System.out.println("Initialize C = { a, b, c, d }");
        Set<String> C = new SetImpl<>(new String[] {"a","b","c","d","a","b","c"});
        System.out.println("|C| = " + C.cardinality());
        System.out.println("d in C = " + C.in("d"));
        System.out.println("\n");

        // implement add() test case
        System.out.println("Test case for add() method");
        System.out.println("A add b");
        A.add("b");
        System.out.println("|A add b| = " + A.cardinality());
        System.out.println("a in A = " + A.in("a"));
        System.out.println("b in A = " + A.in("b"));
        System.out.println("c in A = " + A.in("c"));
        System.out.println("\n");

        // add() more data
        System.out.println("A add c");
        A.add("c");
        System.out.println("|A +b +c| = " + A.cardinality());
        System.out.println("a in A = " + A.in("a"));
        System.out.println("b in A = " + A.in("b"));
        System.out.println("c in A = " + A.in("c"));
        System.out.println("\n");

        //add multiple duplicate elements
        System.out.println("B = { a }");
        B.add("z");
        B.add("z");
        B.add("z");
        System.out.println("|B +z (+z +z)| = " + B.cardinality());
        System.out.println("z in B = " + B.in("z"));
        System.out.println("\n");

        //add multiple elements
        System.out.println("B = { a, z }");
        B.add("x");
        B.add("y");
        B.add("b");
        B.add("c");
        B.add("c");
        System.out.println("|B +x +y +b +c (+c)| = " + B.cardinality());
        System.out.println("c in B = " + B.in("c"));
        System.out.println("|B| = " + B.cardinality());
        System.out.println("\n");

        // implement remove() test case
        System.out.println("Test case for remove() method");
        System.out.println("A = { a, b, c }");
        A.remove("b");
        System.out.println("|A -b| = " + A.cardinality());
        System.out.println("a in A = " + A.in("a"));
        System.out.println("b in A = " + A.in("b"));
        System.out.println("c in A = " + A.in("c"));
        System.out.println("\n");

        // remove() more data
        System.out.println("A -c");
        A.add("b");
        A.remove("c");
        System.out.println("|A +b -c| = " + A.cardinality());
        System.out.println("a in A = " + A.in("a"));
        System.out.println("b in A = " + A.in("b"));
        System.out.println("c in A = " + A.in("c"));
        System.out.println("\n");

        // remove multiple duplicate elements
        System.out.println("B = { a, b, c, x, y, z }");
        System.out.println("|B| = " + B.cardinality());
        B.add("z");
        B.add("b");
        B.add("c");
        B.add("d");
        B.add("e");
        B.add("f");
        B.add("g");
        B.remove("d");
        B.remove("b");
        System.out.println("|B (+z +b +c) +d +e +f +g -d -b| = " + B.cardinality());
        System.out.println("z in B = " + B.in("z"));
        System.out.println("e in B = " + B.in("e"));
        System.out.println("b in B = " + B.in("b"));
        System.out.println("d in B = " + B.in("d"));
        System.out.println("\n");
        */

        // implement intersection() test case
        System.out.println("Test case for intersection() method");
        System.out.println("Z = { a, b, c, e }");
        Set<String> Z = new SetImpl<>(new String[] {"a", "b", "c", "e"});
        System.out.println("Y = {x, y, z}");
        Set<String> Y = new SetImpl<>(new String[] {"x", "y", "z"});
        System.out.println("X = {a, b, c, x, d, e}");
        Set<String> X = new SetImpl<>(new String[] {"a", "b", "c", "x", "d", "e"});
        //System.out.println("E = { }");
        //Set<String> E = new SetImpl<>(new String[] { });
        System.out.println("\n");

        //Set<String> XY = X.union(Y);

        /**
        Set<String> YnZ = Y.intersection(Z);
        System.out.println("|YnZ = Y intersect Z| = " + (YnZ.cardinality()));
        System.out.println("a in YnZ = " + YnZ.in("a"));
        System.out.println("b in YnZ = " + YnZ.in("b"));
        System.out.println("c in YnZ = " + YnZ.in("c"));
        System.out.println("x in YnZ = " + YnZ.in("x"));
        System.out.println("y in YnZ = " + YnZ.in("y"));
        System.out.println("z in YnZ = " + YnZ.in("z"));
        System.out.println("e in YnZ = " + YnZ.in("e"));
        System.out.println("\n");

        Set<String> XnZ= X.intersection(Z);
        System.out.println("|XnZ = X intersect Z| = " + (XnZ.cardinality()));
        System.out.println("a in XnZ = " + XnZ.in("a"));
        System.out.println("b in XnZ = " + XnZ.in("b"));
        System.out.println("c in XnZ = " + XnZ.in("c"));
        System.out.println("y in XnZ = " + XnZ.in("y"));
        System.out.println("z in XnZ = " + XnZ.in("z"));
        System.out.println("e in XnZ = " + XnZ.in("e"));
        System.out.println("\n");
        */

        //System.out.println("|E| = " + (E.cardinality()));

        System.out.println("Test case for union() method");
        System.out.println("Z = { a, b, c, e }");
        System.out.println("Y = { x, y, z }");
        System.out.println("X = { a, b, c, x, d, e }");
        System.out.println("E = { }");
        Set<String> E = new SetImpl<>(new String[] { });

        System.out.println("|Z| = " + Z.cardinality());
        System.out.println("|Y| = " + Y.cardinality());
        System.out.println("|X| = " + X.cardinality());
        System.out.println("\n");

        Set<String> XuZ= X.union(Z);
        Set<String> XuY= X.union(Y);
        System.out.println("|XuY| = " + XuY.cardinality());
        System.out.println("|X| = " + X.cardinality());
        System.out.println("|Y| = " + Y.cardinality());

        System.out.println("b in XuY = " + XuY.in("b"));
        System.out.println("x in XuY = " + XuY.in("x"));
        System.out.println("z in XuY = " + XuY.in("z"));
        System.out.println("\n");

        System.out.println("|XuZ| = " + XuZ.cardinality());
        System.out.println("a in XuZ = " + XuZ.in("a"));
        System.out.println("e in XuZ = " + XuZ.in("e"));
        System.out.println("x in XuZ = " + XuZ.in("x"));
        System.out.println("z in XuZ = " + XuZ.in("z"));
        System.out.println("\n");

        System.out.println("Test case for powerSet() method");
        System.out.println("Z = { a, b, c, e }");
        System.out.println("Y = { x, y, z }");
        System.out.println("X = { a, b, c, x, d, e }");
        System.out.println("E = { }");
        System.out.println("N = { a }");
        Set<String> N = new SetImpl<>(new String[] { "a" });
        System.out.println("\n");

        Set<Set<String>> E_PS = E.powerSet();
        //System.out.println("Here");
        System.out.println("|P(E)| = " + E_PS.cardinality());
        //System.out.println("\nMoving to X");
        Set<Set<String>> X_PS = X.powerSet();
        System.out.println("|P(X)| = " + X_PS.cardinality());
        Set<Set<String>> N_PS = N.powerSet();
        System.out.println("|P(N)| = " + N_PS.cardinality());
        Set<Set<String>> Y_PS = Y.powerSet();
        System.out.println("|P(Y)| = " + Y_PS.cardinality());


         // you are expected to add more code here
         // this main program should provide clear and convincing evidence that your set implementation
         // works correctly.
         // the code here will count for a significant part of your grade on this lab
    }

}
