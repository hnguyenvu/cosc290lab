/******************************************************************************
 * For complication and execution instructions, see:  SetMain.java
 * Version 0.1,  2017
 * @author Michael Hay
 */
import java.util.Iterator;

/**
 * The interface to a set.
 */
public interface Set<E> extends Iterable<E> {

    /**
     * Check for membership in set.
     * @param element element to be checked
     * @return true if element is in the set, false otherwise
     */
    boolean in(E element);

    /**
     * Adds one element to set, modifying the set.
     * @param element the element to add
     */
    void add(E element);

    /**
     * Removes one element from the set, modifying the set.
     * @param element the element to add
     * @throws java.util.NoSuchElementException if element is not in set
     */
    void remove(E element);

    /**
     * Size (aka cardinality) of the set.
     * @return the size of the set (number of distinct elements)
     */
    int cardinality();

    /**
     * Perform set union, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the union of this set with otherSet
     */
    Set<E> union(Set<E> otherSet);

    /**
     * Perform set intersection, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the intersection of this set with otherSet
     */
    Set<E> intersection(Set<E> otherSet);

    /**
     * Perform set difference, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the difference of this set with otherSet
     */
    Set<E> difference(Set<E> otherSet);

    /**
     * The power set of this set, which is the set of all subsets.
     * @return a set of sets, equal to the power set of this set
     */
    Set<Set<E>> powerSet();

    /**
     * Iterator over set elements
     * @return an iterator of the elements of this set
     */
    @Override
    Iterator<E> iterator();
}
