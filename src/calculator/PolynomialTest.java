package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Polynomial}. Cases come from the Deliverable 2 test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class PolynomialTest {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /** An empty polynomial created fresh before every test. */
    private Polynomial poly;

    /**
     * Runs before every test.
     */
    @Before
    public void setUp() {
        poly = new Polynomial();
    }


    /** Normal: a new polynomial is empty (prints "0"). */
    @Test
    public void testConstructorEmpty() {
        fail("TODO: assertEquals(\"0\", poly.toString())");
    }


    /** Normal: addTerm(5, 3) adds 5x^3. */
    @Test
    public void testAddTerm() {
        fail("TODO: poly.addTerm(5, 3); check toCoefficientArray()[3] == 5");
    }


    /** Duplicate degrees combine: 2x^2 + 3x^2 is 5x^2. */
    @Test
    public void testAddTermCombinesDuplicateDegree() {
        fail("TODO: addTerm(2, 2); addTerm(3, 2); expect [0, 0, 5]");
    }


    /** Bad: degree -1 throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testAddTermNegativeDegree() {
        fail("TODO: poly.addTerm(5, -1)");
    }


    /** Normal: 5x^3 - 2x + 7 becomes [7, -2, 0, 5]. */
    @Test
    public void testToCoefficientArray() {
        fail("TODO: build 5x^3 - 2x + 7, compare with assertArrayEquals");
    }


    /** Bad: an empty polynomial gives the zero-polynomial array. */
    @Test
    public void testToCoefficientArrayEmpty() {
        fail("TODO: decide the zero representation (e.g. {0.0}) and assert it");
    }


    /** Normal: [7, -2, 0, 5] becomes 5x^3 - 2x + 7. */
    @Test
    public void testFromCoefficientArray() {
        fail("TODO: fromCoefficientArray(new double[] {7, -2, 0, 5}); "
            + "assert toString()");
    }


    /** Bad: an array containing NaN throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testFromCoefficientArrayNaN() {
        fail("TODO: fromCoefficientArray(new double[] {1, Double.NaN})");
    }


    /** Normal: 5x^3 - 2x + 7 prints as "5x^3 - 2x + 7". */
    @Test
    public void testToString() {
        fail("TODO: assertEquals(\"5x^3 - 2x + 7\", poly.toString())");
    }


    /** Bad: the empty polynomial prints as "0". */
    @Test
    public void testToStringEmpty() {
        fail("TODO: assertEquals(\"0\", new Polynomial().toString())");
    }
}
