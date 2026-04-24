package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GradeCalculator.
 */
public class GradeCalculatorTest {

    private GradeCalculator gc = new GradeCalculator();

    // 1. Test for average()
    @Test
    public void testAverageCalculation() {
        double result = gc.average(90, 80, 70);
        assertEquals(80.0, result);
    }

    // 2. Test for letterGrade()
    @Test
    public void testLetterGrade() {
        assertEquals("A", gc.letterGrade(95));
        assertEquals("B", gc.letterGrade(85));
        assertEquals("C", gc.letterGrade(75));
        assertEquals("D", gc.letterGrade(65));
        assertEquals("F", gc.letterGrade(50));
    }

    // 3. Test for isPassing()
    @Test
    public void testIsPassing() {
        assertTrue(gc.isPassing(70));
        assertFalse(gc.isPassing(50));
    }

    // 4. Boundary-value tests
    @Test
    public void testBoundaryAtPassingThreshold() {
        assertTrue(gc.isPassing(60));
        assertEquals("D", gc.letterGrade(60));
    }

    @Test
    public void testBoundaryAtMaxScore() {
        double result = gc.average(100, 100, 100);
        assertEquals(100.0, result);
        assertEquals("A", gc.letterGrade(result));
    }

    // 5. Exception tests
    @Test
    public void testInvalidScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            gc.average(-1, 80, 90);
        });
    }

    @Test
    public void testInvalidScoreAboveHundred() {
        assertThrows(IllegalArgumentException.class, () -> {
            gc.average(101, 80, 90);
        });
    }
}