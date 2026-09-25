package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link PolynomialCalculator}. Cases come from the Deliverable 2
 * test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class PolynomialCalculatorTest {

    /** The calculator under test. */
    private PolynomialCalculator calc;

    /**
     * Runs before every test.
     */
    @Before
    public void setUp() {
        calc = new PolynomialCalculator();
    }


    /** Normal: (3x^2 + 2x) + (2x^2 + 1) is 5x^2 + 2x + 1. */
    @Test
    public void testAdd() {
        fail("TODO: build both, assert add(...).toString()");
    }


    /** Bad: a null polynomial throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        fail("TODO: calc.add(new Polynomial(), null)");
    }


    /** Normal: (5x^2 + 3x) - (2x^2 + x) is 3x^2 + 2x. */
    @Test
    public void testSubtract() {
        fail("TODO: build both, assert subtract(...).toString()");
    }


    /** Bad: a null polynomial throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtractNull() {
        fail("TODO: calc.subtract(null, new Polynomial())");
    }


    /** Normal: (x + 2)(x + 3) is x^2 + 5x + 6. */
    @Test
    public void testMultiply() {
        fail("TODO: build both, assert multiply(...).toString()");
    }


    /** Bad: a coefficient that overflows throws. */
    @Test(expected = ArithmeticException.class)
    public void testMultiplyOverflow() {
        fail("TODO: multiply two polynomials with Double.MAX_VALUE coefficients");
    }

}
