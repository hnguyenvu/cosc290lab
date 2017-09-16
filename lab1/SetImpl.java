/*****************************************************************************
 * For complication and execution instructions, see:  SetMain.java
 * Version 0.1,  2017
 * @author Michael Hay
 * @student Nam Nguyen, COSC 290
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the set interface that uses a fixed-size array to store elements.  As elements are added to the
 * set, the array is automatically resized.
 */
public class SetImpl<E> implements Set<E> {

    private E[] elements;                  // where elements should be stored (order does not matter)
    private int cardinality;               // the number of elements in the set

    private SetImpl(int capacity) {
        capacity = Math.max(capacity, 1);  // create storage for at least one element
        @SuppressWarnings("unchecked")     // this cast is okay because methods will ensure that only E's are added
            E[] tempItems = (E[]) new Object[capacity];
        elements = tempItems;
        cardinality = 0;
    }

    /**
     * Create an empty set.
     */
    public SetImpl() {
        this(0);
    }

    /**
     * Create a set via enumeration.  Each element in {@code elementsToAdd} should be added to the set.  Note that
     * {@code elementsToAdd} may contain duplicates but the created set should not have any duplicates.
     * @param elementsToAdd elements to add to the set
     */
    SetImpl(E[] elementsToAdd) {
        // initially allocate memory for the length to copy the length
        @SuppressWarnings("unchecked")     // this cast is okay because methods will ensure that only E's are added
            E[] tempItems = (E[]) new Object[elementsToAdd.length];
        elements = tempItems;

        // copy from 'elementsToAdd' to 'elements'
        // note: (1) don't copy 'null', (2) don't copy duplicate

        int posTempItems = 0;

        for (int posToAdd = 0; posToAdd < elementsToAdd.length; posToAdd++) {
            if (elementsToAdd[posToAdd] == null || this.in(elementsToAdd[posToAdd])) {
                continue;
            } else {
                elements[posTempItems] = elementsToAdd[posToAdd];
                posTempItems++;
                cardinality++;
            }
        }
        // throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public boolean in(E element) {
    	if (element == null) {
    		if (cardinality == 0) return true; // empty set case
    		else return false;
    	} else {
    		for (int pos = 0; pos < elements.length; pos++) {
    			if (element.equals(elements[pos])) {
    				return true;
    			}
    		}
    		return false; // after going through every element in the set without finding the element
    	}
        // throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public void add(E element) {
        /**
         * Check if: (1) element is null
         * (2) whether element is already in the set
         * if yes, make no change to the set and return the old one
         * if not: (0) check if there's still space in the list;
         * if yes: (1) create new storage +1, (2) move elements from the original set to the new set
         * (3) add new element in at the end of the list, (4) increase cardinality
         */

        if (element == null || this.in(element)) {
        	return;
        } else {
            /**
             * Check if there is extra physical sapce already assigned for the list (elements.length > cardinality)
             * checking for null element within the original set 'elements'
             * replace null elemenet with the new element\
             */
            if (elements.length > cardinality) {
                for (int posToAdd = 0; posToAdd < elements.length; posToAdd++) {
                    if (elements[posToAdd] == null) {
                        elements[posToAdd] = element;
                        cardinality++;
                        return;
                    }
                    continue;
                }
            } else {
                E[] tempItems = (E[]) new Object[cardinality*2]; // double the space for efficiency
                int posAdded = 0;

                /**
                 * copy all the elements in the original set to the new set
                 * checking for null elements within the orginal set
                 * skip null elements, only copy valid elements
                 */

                for (int posToAdd = 0; posToAdd < elements.length; posToAdd++) {
                    if (elements[posToAdd] != null) {
                        tempItems[posAdded] = elements[posToAdd];
                        posAdded++;
                    }
                    continue;
                }

                // add in the new element to the last round
                tempItems[cardinality] = element;
                elements = tempItems;
                cardinality++;
            }
        }
    	// throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public void remove(E element) {
        // assume that there's no duplicates

        if (cardinality == 0) {
            return;
        } else if (element == null || !this.in(element)) {
            throw new NoSuchElementException("element is not in set");
        } else { // ensure the element is not null and present in the elements
       	    for (int posCurrSet = 0; posCurrSet < elements.length; posCurrSet++) {
                if (element.equals(elements[posCurrSet])) {
       		        elements[posCurrSet] = null;
                    cardinality--;
                    return;
                }
       	    }
        }
        // throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public int cardinality() {
        return cardinality;
    }

    @Override
    public Set<E> union(Set<E> otherSet) {
        /**
         * (1) Loop through the otherSet and copy the element to tempItems (iterator alr check null items)
         *   (a) still need to check if the otherSet has duplicate as otherSet may not be in the same class SetImpl
         * (2) Loop through our current set to
         *   (a) check for duplicate in the tempItems
         *   (b) if not duplicate, add in to our set
         */
        // new SetImpl<>(new String[] { });
        Iterator<E> currSetIterator = otherSet.iterator();
        int capacity = cardinality + otherSet.cardinality();
        E next;

        Set<E> tempItems = new SetImpl<>((E[]) new Object[capacity]);

        // copy otherSet to tempItems
        for (int numElement = 0; numElement < otherSet.cardinality() && currSetIterator.hasNext(); numElement++) {
            // System.out.println(currSetIterator.hasNext());
            next = currSetIterator.next();

            if (!tempItems.in(next)) {
                tempItems.add(next);
            } else continue;
        }

        // copy this current set to tempItems
        for (int posCurrSet = 0; posCurrSet < elements.length; posCurrSet++) {
            if (elements[posCurrSet] != null && !tempItems.in(elements[posCurrSet])) {
                tempItems.add(elements[posCurrSet]);
            }
        }
        return tempItems;
        // throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public Set<E> intersection(Set<E> otherSet) {
        // (1) allocate memory, using the cardinality of the smaller set to be
        // the maximum size of the intersection set
        int capacity = Math.min(otherSet.cardinality(), cardinality);

        Set<E> tempItems = new SetImpl<>((E[]) new Object[capacity]);

        // doesn't really matter which set to check, as the in() method will have to go through the other set as well
        // so the run time will be roughly the same
        for (int posCurrSet = 0; posCurrSet < elements.length; posCurrSet++) {
            if (elements[posCurrSet] != null) {
                if (otherSet.in(elements[posCurrSet])) {
                    tempItems.add(elements[posCurrSet]);
                }
            }
        }
        return tempItems;
        // throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public Set<E> difference(Set<E> otherSet) {
        // allocate memory, using cardinality of the bigger set
        // the maximum size of the intersection set
        int capacity = Math.max(otherSet.cardinality(), cardinality);
        Set<E> tempItems = new SetImpl<>((E[]) new Object[capacity]);

        for (int posCurrSet = 0; posCurrSet < elements.length; posCurrSet++) {
            if (elements[posCurrSet] != null) {
                if (!otherSet.in(elements[posCurrSet])) {
                    tempItems.add(elements[posCurrSet]);
                }
            }
        }
        return tempItems;
        // throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public Set<Set<E>> powerSet() {
        // use recursion
        /**
         * 3 base cases for element set
         * (1): |set| == 0; powerSet has 1 empty set
         * (2): |set| == 1; powerSet has 1 empty set and 1 set with 1 element
         * (3): |set| == 2; powerSet has 1 set with 1 element, x, union with elements (which are sets)
         * in powerSet(currentSet - {x})
         */

        if (cardinality == 0) {
            //System.out.println("Base Case 0");
            return new SetImpl<Set<E>>(); // empty powerSet
        } else if (cardinality == 1) {
            //System.out.println("Base Case 1");
            Set<Set<E>> tempPowerSet = new SetImpl<Set<E>>();
            Set<E> emptySet = new SetImpl<E>(); // initialize setToAdd to become emptySet
            tempPowerSet.add(emptySet); // add empty set
            //System.out.println(setToAdd.cardinality());
            //System.out.println(tempPowerSet.cardinality());

            E element = iterator().next();
            System.out.println(element);
            Set<E> setToAdd = new SetImpl<E>();
            setToAdd.add(element);
            System.out.println(setToAdd.cardinality());

            tempPowerSet.add(setToAdd); // should have next as cardinality == 1 // TODO there is some problem with this method
            System.out.println(tempPowerSet.cardinality());
            return tempPowerSet;
        } else {
            E elementToRemove = this.iterator().next();
            //System.out.println(elementToRemove);

            Set<E> setToRemove = new SetImpl<E>();
            setToRemove.add(elementToRemove);

            Set<Set<E>> tempPowerSet = this.difference(setToRemove).powerSet();
            Iterator<Set<E>> tempPSIter = tempPowerSet.iterator();

            Set<Set<E>> result = tempPowerSet;
            int powerSerCard = tempPowerSet.cardinality();
            // System.out.println(tempPowerSet.cardinality());
            //System.out.println("tempPSIter.hasNext(): " + tempPSIter.hasNext());

            for (int numElement = 0; numElement < powerSerCard; numElement++) {
                //System.out.println(numElement);
                //System.out.println(tempPowerSet.cardinality());
                Set<E> setElFromPS = tempPSIter.next();
                result.add(setElFromPS.union(setToRemove));
            }
            //System.out.println("finish loop");
            return result;
        }
        // throw new UnsupportedOperationException("implement me!");
    }

    /**
     * A helper method to check whether a cell is occupied.
     * @param idx the index to check
     * @return true if there is an element at this index of the array, false otherwise.
     */
    private boolean isOccupied(int idx) {
        return elements[idx] != null;            // if you implement the challenge problem, you may wish to change
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator();
    }

    /**
     * An iterator over array of elements.  Automatically skips over empty cells, by using the
     * {@link SetImpl#isOccupied} method.
     */
    class SetIterator implements Iterator<E> {

        private int idx;
        E next = null;

        SetIterator() {
            idx = 0;
            fetchNext();
        }

        /**
         * Skips ahead to find next occupied array index.
         */
        private void fetchNext() {
            boolean found = false;
            for (; idx < elements.length && !found; idx++) {
                if (isOccupied(idx)) {
                    next = elements[idx];
                    found = true;
                }
            }
        }

        @Override
        public boolean hasNext() {
            if (next == null) {
                fetchNext();
            }
            return next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E elt = next;
            next = null;
            return elt;
        }
    }

    /**
     * @return the current storage consumed by this implementation
     */
    public int capacity() {
         // you don't need to edit this method
        return elements.length;
    }
}
