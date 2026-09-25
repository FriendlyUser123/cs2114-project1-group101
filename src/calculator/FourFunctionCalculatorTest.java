package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link FourFunctionCalculator}. Cases come from the Deliverable 2
 * test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class FourFunctionCalculatorTest {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /** A calculator created fresh before every test. */
    private FourFunctionCalculator calc;

    /**
     * Runs before every test.
     */
    @Before
    public void setUp() {
        calc = new FourFunctionCalculator();
    }


    /** Normal: a new calculator has no stored answer. */
    @Test
    public void testConstructor() {
        fail("TODO: assertFalse(calc.hasLast())");
    }


    /** Normal: after a calculation, hasLast() is true. */
    @Test
    public void testHasLastTrue() {
        fail("TODO: calc.add(1, 2); assertTrue(calc.hasLast())");
    }


    /** No previous result: hasLast() is false. */
    @Test
    public void testHasLastFalse() {
        fail("TODO: assertFalse(new FourFunctionCalculator().hasLast())");
    }


    /** Normal: setLast(8.5) stores 8.5. */
    @Test
    public void testSetLast() {
        fail("TODO: calc.setLast(8.5); assertEquals(8.5, calc.getLast(), DELTA)");
    }


    /** Bad: setLast(Infinity) throws AND keeps the old value. */
    @Test
    public void testSetLastInfinityPreservesPrevious() {
        fail("TODO: setLast(8.5); try setLast(Infinity) expecting IAE; then assert getLast() is still 8.5");
    }


    /** Normal: last result 8.5 returns 8.5. */
    @Test
    public void testGetLast() {
        fail("TODO: calc.setLast(8.5); assertEquals(8.5, calc.getLast(), DELTA)");
    }


    /** Bad: getLast() with nothing stored throws. */
    @Test(expected = IllegalStateException.class)
    public void testGetLastWhenEmpty() {
        fail("TODO: calc.getLast()");
    }


    /** Normal: 5 + 8 is 13. */
    @Test
    public void testAdd() {
        fail("TODO: assertEquals(13.0, calc.add(5, 8), DELTA)");
    }


    /** Bad: an overflowing sum throws. */
    @Test(expected = ArithmeticException.class)
    public void testAddOverflow() {
        fail("TODO: calc.add(Double.MAX_VALUE, Double.MAX_VALUE)");
    }


    /** Normal: 10 - 4 is 6. */
    @Test
    public void testSubtract() {
        fail("TODO: assertEquals(6.0, calc.subtract(10, 4), DELTA)");
    }


    /** Bad: an overflowing difference throws. */
    @Test(expected = ArithmeticException.class)
    public void testSubtractOverflow() {
        fail("TODO: calc.subtract(-Double.MAX_VALUE, Double.MAX_VALUE)");
    }


    /** Normal: 4 * 5 is 20. */
    @Test
    public void testMultiply() {
        fail("TODO: assertEquals(20.0, calc.multiply(4, 5), DELTA)");
    }


    /** Bad: an overflowing product throws. */
    @Test(expected = ArithmeticException.class)
    public void testMultiplyOverflow() {
        fail("TODO: calc.multiply(Double.MAX_VALUE, 2)");
    }


    /** Normal: 10 / 2 is 5. */
    @Test
    public void testDivide() {
        fail("TODO: assertEquals(5.0, calc.divide(10, 2), DELTA)");
    }


    /** Bad: dividing by 0 throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        fail("TODO: calc.divide(10, 0)");
    }

}
