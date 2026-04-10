package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for IntegerSet.
 */
public class IntegerSetTest {

    private IntegerSet makeSet(int... values) {
        IntegerSet set = new IntegerSet();
        for (int v : values) {
            set.add(v);
        }
        return set;
    }

    @Test
    public void testClearNormalAndEdge() {
        IntegerSet set = makeSet(1, 2, 3);
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals("[]", set.toString());

        IntegerSet empty = new IntegerSet();
        empty.clear();
        assertTrue(empty.isEmpty());
        assertEquals("[]", empty.toString());
    }

    @Test
    public void testLengthNormalAndEdge() {
        IntegerSet set = makeSet(5, 10, 15);
        assertEquals(3, set.length());

        IntegerSet empty = new IntegerSet();
        assertEquals(0, empty.length());
    }

    @Test
    public void testEqualsSameElementsDifferentOrderAndMismatch() {
        IntegerSet set1 = makeSet(1, 2, 3);
        IntegerSet set2 = makeSet(3, 2, 1);
        IntegerSet set3 = makeSet(1, 2);

        assertTrue(set1.equals(set2));
        assertFalse(set1.equals(set3));
    }

    @Test
    public void testContainsPresentAndAbsent() {
        IntegerSet set = makeSet(1, 2, 3);

        assertTrue(set.contains(2));
        assertFalse(set.contains(99));
    }

    @Test
    public void testLargestNormalSingleElementAndEmptyException() {
        IntegerSet single = makeSet(42);
        assertEquals(42, single.largest());

        IntegerSet multi = makeSet(1, 9, 4);
        assertEquals(9, multi.largest());

        IntegerSet empty = new IntegerSet();
        assertThrows(NoSuchElementException.class, empty::largest);
    }

    @Test
    public void testSmallestNormalSingleElementAndEmptyException() {
        IntegerSet single = makeSet(42);
        assertEquals(42, single.smallest());

        IntegerSet multi = makeSet(7, 3, 10);
        assertEquals(3, multi.smallest());

        IntegerSet empty = new IntegerSet();
        assertThrows(NoSuchElementException.class, empty::smallest);
    }

    @Test
    public void testAddNormalAndDuplicate() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(7);
        set.add(5); // duplicate

        assertTrue(set.contains(5));
        assertTrue(set.contains(7));
        assertEquals(2, set.length());
        assertEquals("[5, 7]", set.toString());
    }

    @Test
    public void testRemoveNormalAndMissing() {
        IntegerSet set = makeSet(1, 2, 3);

        set.remove(2);
        assertFalse(set.contains(2));
        assertEquals(2, set.length());
        assertEquals("[1, 3]", set.toString());

        set.remove(99); // missing value should not crash
        assertEquals(2, set.length());
        assertEquals("[1, 3]", set.toString());
    }

    @Test
    public void testUnionNormalAndWithEmptySet() {
        IntegerSet set1 = makeSet(1, 2, 3);
        IntegerSet set2 = makeSet(3, 4);
        IntegerSet empty = new IntegerSet();

        IntegerSet result1 = set1.union(set2);
        assertEquals("[1, 2, 3, 4]", result1.toString());
        assertEquals("[1, 2, 3]", set1.toString());
        assertEquals("[3, 4]", set2.toString());

        IntegerSet result2 = set1.union(empty);
        assertEquals("[1, 2, 3]", result2.toString());
    }

    @Test
    public void testIntersectNormalAndNoOverlap() {
        IntegerSet set1 = makeSet(1, 2, 3);
        IntegerSet set2 = makeSet(2, 3, 4);
        IntegerSet set3 = makeSet(7, 8);

        IntegerSet result1 = set1.intersect(set2);
        assertEquals("[2, 3]", result1.toString());

        IntegerSet result2 = set1.intersect(set3);
        assertEquals("[]", result2.toString());
    }

    @Test
    public void testDiffNormalAndIdenticalSets() {
        IntegerSet set1 = makeSet(1, 2, 3);
        IntegerSet set2 = makeSet(2, 3, 4);
        IntegerSet identical = makeSet(1, 2, 3);

        IntegerSet result1 = set1.diff(set2);
        assertEquals("[1]", result1.toString());

        IntegerSet result2 = set1.diff(identical);
        assertEquals("[]", result2.toString());
    }

    @Test
    public void testComplementNormalAndDisjointSets() {
        IntegerSet set1 = makeSet(1, 2, 3);
        IntegerSet set2 = makeSet(2, 3, 4);
        IntegerSet disjoint = makeSet(7, 8);

        IntegerSet result1 = set1.complement(set2);
        assertEquals("[4]", result1.toString());

        IntegerSet result2 = set1.complement(disjoint);
        assertEquals("[7, 8]", result2.toString());
    }

    @Test
    public void testIsEmptyEmptyAndNonEmpty() {
        IntegerSet empty = new IntegerSet();
        IntegerSet nonEmpty = makeSet(1);

        assertTrue(empty.isEmpty());
        assertFalse(nonEmpty.isEmpty());
    }

    @Test
    public void testToStringNormalAndEmpty() {
        IntegerSet set = makeSet(3, 1, 2);
        IntegerSet empty = new IntegerSet();

        assertEquals("[1, 2, 3]", set.toString());
        assertEquals("[]", empty.toString());
    }
}