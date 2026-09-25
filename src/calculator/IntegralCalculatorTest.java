package calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *  Tests the integral calculator class to make sure it works as intended.
 * 
 *  @author Ethan Gearhart
 *  @version Sep 25, 2026
 */

public class IntegralCalculatorTest
{
    private static final double DELTA = 0.0001;
    private IntegralCalculator calc;
    private Polynomial poly;

    /**
     * Creates a calculator and polynomial before each test.
     */
    public void setUp()
    {
        calc = new IntegralCalculator();
        poly = new Polynomial();
    }


    /**
     * Tests creation of an IntegralCalculator.
     */
    @Test
    public void testIntegralCalculator()
    {
        IntegralCalculator testCalc = new IntegralCalculator();

        assertNotNull(testCalc);
    }


    /**
     * Tests direct polynomial evaluation.
     */
    @Test
    public void testEvaluatePolynomial()
    {
        // f(x) = 3x^2 - 2x + 5
        poly.addTerm(3.0, 2);
        poly.addTerm(-2.0, 1);
        poly.addTerm(5.0, 0);

        // f(2) = 3(4) - 4 + 5 = 13
        double result = calc.evaluatePolynomial(poly, 2.0);

        assertEquals(13.0, result, DELTA);

        // Also test a constant polynomial
        Polynomial constant = new Polynomial();
        constant.addTerm(7.5, 0);

        result = calc.evaluatePolynomial(constant, -20.0);

        assertEquals(7.5, result, DELTA);
    }


    /**
     * Tests normal Simpson's Rule operation. Simpson's Rule exactly integrates
     * cubic polynomials.
     */
    @Test
    public void testSimpsonsRule()
    {
        // f(x) = x^3
        poly.addTerm(1.0, 3);

        /*
         * Integral from 0 to 2: x^4 / 4 from 0 to 2 = 16 / 4 = 4 Four intervals
         * also causes the loop to use both the 4*y and 2*y branches.
         */
        double result = calc.simpsonsRule(poly, 0.0, 2.0, 4);

        assertEquals(4.0, result, DELTA);

        // Reversed bounds should produce the negative integral.
        result = calc.simpsonsRule(poly, 2.0, 0.0, 4);

        assertEquals(-4.0, result, DELTA);

        // Equal bounds should have integral zero.
        result = calc.simpsonsRule(poly, 2.0, 2.0, 4);

        assertEquals(0.0, result, DELTA);
    }


    /**
     * Tests null polynomial input.
     */
    @Test
    public void testNullPolynomial()
    {
        IllegalArgumentException exception = null;

        try
        {
            calc.simpsonsRule(null, 0.0, 2.0, 4);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests invalid integration bounds.
     */
    @Test
    public void testInvalidBounds()
    {
        poly.addTerm(1.0, 2);

        // NaN lower bound
        IllegalArgumentException lowerNaNException = null;

        try
        {
            calc.simpsonsRule(poly, Double.NaN, 2.0, 4);
        }
        catch (IllegalArgumentException e)
        {
            lowerNaNException = e;
        }

        assertNotNull(lowerNaNException);

        // Infinite lower bound
        IllegalArgumentException lowerInfinityException = null;

        try
        {
            calc.simpsonsRule(poly, Double.POSITIVE_INFINITY, 2.0, 4);
        }
        catch (IllegalArgumentException e)
        {
            lowerInfinityException = e;
        }

        assertNotNull(lowerInfinityException);

        // NaN upper bound
        IllegalArgumentException upperNaNException = null;

        try
        {
            calc.simpsonsRule(poly, 0.0, Double.NaN, 4);
        }
        catch (IllegalArgumentException e)
        {
            upperNaNException = e;
        }

        assertNotNull(upperNaNException);

        // Infinite upper bound
        IllegalArgumentException upperInfinityException = null;

        try
        {
            calc.simpsonsRule(poly, 0.0, Double.POSITIVE_INFINITY, 4);
        }
        catch (IllegalArgumentException e)
        {
            upperInfinityException = e;
        }

        assertNotNull(upperInfinityException);
    }


    /**
     * Tests invalid numbers of intervals.
     */
    @Test
    public void testInvalidIntervals()
    {
        poly.addTerm(1.0, 2);

        // Intervals must be positive.
        IllegalArgumentException zeroException = null;

        try
        {
            calc.simpsonsRule(poly, 0.0, 2.0, 0);
        }
        catch (IllegalArgumentException e)
        {
            zeroException = e;
        }

        assertNotNull(zeroException);

        // Intervals must also be even.
        IllegalArgumentException oddException = null;

        try
        {
            calc.simpsonsRule(poly, 0.0, 2.0, 3);
        }
        catch (IllegalArgumentException e)
        {
            oddException = e;
        }

        assertNotNull(oddException);
    }


    /**
     * Tests an integral whose result overflows to infinity.
     */
    @Test
    public void testInfiniteResult()
    {
        /*
         * Double.MAX_VALUE is finite, so this is theoretically something a user
         * could enter.
         */
        poly.addTerm(Double.MAX_VALUE, 0);

        ArithmeticException exception = null;

        try
        {
            calc.simpsonsRule(poly, 0.0, 2.0, 2);
        }
        catch (ArithmeticException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests an integral calculation that results in NaN.
     */
    @Test
    public void testNaNResult()
    {
        /*
         * Both bounds themselves are finite. upperBound - lowerBound overflows
         * to infinity, making h infinite. The zero polynomial then eventually
         * produces 0 * Infinity, giving NaN.
         */
        ArithmeticException exception = null;

        try
        {
            calc.simpsonsRule(poly, -Double.MAX_VALUE, Double.MAX_VALUE, 2);
        }
        catch (ArithmeticException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }
}
