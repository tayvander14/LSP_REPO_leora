package org.howard.edu.lsp.assigment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * IntegerSet models a mathematical set of integers.
 * It does not allow duplicate values and supports common set operations.
 */
public class IntegerSet {
    private ArrayList<Integer> set;

    /**
     * Default constructor initializes an empty set.
     */
    public IntegerSet() {
        set = new ArrayList<>();
    }

    /**
     * Clears the internal representation of the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the length of the set.
     * @return number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Compares two sets for equality.
     * @param b another IntegerSet
     * @return true if both sets contain exactly the same elements
     */
    public boolean equals(IntegerSet b) {
        if (b == null) return false;
        if (this.length() != b.length()) return false;

        for (int value : set) {
            if (!b.contains(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the set contains a given value.
     * @param value integer to check
     * @return true if value is in the set
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * @return largest integer
     * @throws NoSuchElementException if set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new NoSuchElementException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest item in the set.
     * @return smallest integer
     * @throws NoSuchElementException if set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new NoSuchElementException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if not already present.
     * @param item integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if present.
     * @param item integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns the union of this set and another set.
     * @param intSetb another IntegerSet
     * @return new IntegerSet containing all unique elements
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);

        for (int value : intSetb.set) {
            if (!result.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Returns the intersection of this set and another set.
     * @param intSetb another IntegerSet
     * @return new IntegerSet containing common elements
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (int value : this.set) {
            if (intSetb.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Returns the difference of this set and another set.
     * @param intSetb another IntegerSet
     * @return elements in this set but not in intSetb
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (int value : this.set) {
            if (!intSetb.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Returns the complement of this set relative to another set.
     * @param intSetb another IntegerSet
     * @return elements in intSetb but not in this set
     */
    public IntegerSet complement(IntegerSet intSetb) {
        return intSetb.diff(this);
    }

    /**
     * Checks if the set is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in ascending order.
     * Format: [1, 2, 3]
     * @return string representation
     */
    @Override
    public String toString() {
        ArrayList<Integer> temp = new ArrayList<>(set);
        Collections.sort(temp);
        return temp.toString();
    }
}