package calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *  Tests the integral calculator class to make sure it works as intended.
 * 
 *  @author Aditya Banerjee (adityab7)
 * @author Ethan Gearhart (ethang06)
 * @author Nandini Duggaraju (nduggaraju)
 *  @version Sep 25, 2026
 */

public class IntegralCalculatorTest extends student.TestCase
{
    private static final double DELTA = 0.0001;
    private IntegralCalculator calc;
    private Polynomial poly;


    /**
     * Creates a calculator and polynomial before each test.
     */
    @Before
    public void setUp()
    {
        calc = new IntegralCalculator();
        poly = new Polynomial();
    }


    /**
     * Tests construction of an IntegralCalculator.
     */
    @Test
    public void testIntegralCalculator()
    {
        IntegralCalculator testCalc =
            new IntegralCalculator();

        assertNotNull(testCalc);
    }


    /**
     * Tests evaluating a polynomial at an x value.
     */
    @Test
    public void testEvaluatePolynomial()
    {
        // f(x) = 3x^2 - 2x + 5
        poly.addTerm(3.0, 2);
        poly.addTerm(-2.0, 1);
        poly.addTerm(5.0, 0);

        // f(2) = 12 - 4 + 5 = 13
        double result =
            calc.evaluatePolynomial(poly, 2.0);

        assertEquals(13.0, result, DELTA);

        // Check a negative x as well:
        // f(-2) = 12 + 4 + 5 = 21
        result =
            calc.evaluatePolynomial(poly, -2.0);

        assertEquals(21.0, result, DELTA);

        // Zero polynomial
        Polynomial zero = new Polynomial();

        result =
            calc.evaluatePolynomial(zero, 7.0);

        assertEquals(0.0, result, DELTA);
    }


    /**
     * Tests normal Simpson's Rule behavior.
     */
    @Test
    public void testSimpsonsRuleNormal()
    {
        // f(x) = x^2
        poly.addTerm(1.0, 2);

        /*
         * Integral from 0 to 3:
         *
         * x^3 / 3 from 0 to 3 = 9
         *
         * Four intervals also causes the loop to use
         * BOTH Simpson weighting branches:
         *
         * i = 1 -> weight 4
         * i = 2 -> weight 2
         * i = 3 -> weight 4
         */
        double result =
            calc.simpsonsRule(
                poly,
                0.0,
                3.0,
                4);

        assertEquals(9.0, result, DELTA);


        /*
         * Reversing the bounds should reverse the sign.
         */
        result =
            calc.simpsonsRule(
                poly,
                3.0,
                0.0,
                4);

        assertEquals(-9.0, result, DELTA);


        /*
         * Identical bounds should produce zero area.
         */
        result =
            calc.simpsonsRule(
                poly,
                2.0,
                2.0,
                4);

        assertEquals(0.0, result, DELTA);
    }


    /**
     * Tests rejection of a null polynomial.
     */
    @Test
    public void testNullPolynomial()
    {
        IllegalArgumentException exception = null;

        try
        {
            calc.simpsonsRule(
                null,
                0.0,
                2.0,
                4);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests every invalid-bound condition.
     */
    @Test
    public void testInvalidBounds()
    {
        poly.addTerm(1.0, 2);


        // Lower bound is NaN
        IllegalArgumentException lowerNaNException = null;

        try
        {
            calc.simpsonsRule(
                poly,
                Double.NaN,
                2.0,
                4);
        }
        catch (IllegalArgumentException e)
        {
            lowerNaNException = e;
        }

        assertNotNull(lowerNaNException);


        // Lower bound is infinite
        IllegalArgumentException lowerInfinityException = null;

        try
        {
            calc.simpsonsRule(
                poly,
                Double.POSITIVE_INFINITY,
                2.0,
                4);
        }
        catch (IllegalArgumentException e)
        {
            lowerInfinityException = e;
        }

        assertNotNull(lowerInfinityException);


        // Upper bound is NaN
        IllegalArgumentException upperNaNException = null;

        try
        {
            calc.simpsonsRule(
                poly,
                0.0,
                Double.NaN,
                4);
        }
        catch (IllegalArgumentException e)
        {
            upperNaNException = e;
        }

        assertNotNull(upperNaNException);


        // Upper bound is infinite
        IllegalArgumentException upperInfinityException = null;

        try
        {
            calc.simpsonsRule(
                poly,
                0.0,
                Double.NEGATIVE_INFINITY,
                4);
        }
        catch (IllegalArgumentException e)
        {
            upperInfinityException = e;
        }

        assertNotNull(upperInfinityException);
    }


    /**
     * Tests invalid numbers of Simpson intervals.
     */
    @Test
    public void testInvalidIntervals()
    {
        poly.addTerm(1.0, 2);


        // Must be greater than zero
        IllegalArgumentException zeroException = null;

        try
        {
            calc.simpsonsRule(
                poly,
                0.0,
                2.0,
                0);
        }
        catch (IllegalArgumentException e)
        {
            zeroException = e;
        }

        assertNotNull(zeroException);


        // Must be even
        IllegalArgumentException oddException = null;

        try
        {
            calc.simpsonsRule(
                poly,
                0.0,
                2.0,
                3);
        }
        catch (IllegalArgumentException e)
        {
            oddException = e;
        }

        assertNotNull(oddException);
    }


    /**
     * Tests an integral whose result becomes positive infinity.
     */
    @Test
    public void testInfiniteResult()
    {
        /*
         * This is still a finite coefficient.
         *
         * f(x) = Double.MAX_VALUE
         *
         * Adding multiple Simpson samples causes the
         * accumulated result to overflow.
         */
        poly.addTerm(
            Double.MAX_VALUE,
            0);

        ArithmeticException exception = null;

        try
        {
            calc.simpsonsRule(
                poly,
                0.0,
                2.0,
                2);
        }
        catch (ArithmeticException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests an integral whose intermediate result becomes NaN.
     */
    @Test
    public void testNaNResult()
    {
        /*
         * f(x) = Double.MAX_VALUE * x
         *
         * At x = -2:
         * result becomes negative infinity.
         *
         * At x = 2:
         * result becomes positive infinity.
         *
         * -Infinity + Infinity = NaN.
         *
         * All values supplied to the calculator itself are finite.
         */
        poly.addTerm(
            Double.MAX_VALUE,
            1);

        ArithmeticException exception = null;

        try
        {
            calc.simpsonsRule(
                poly,
                -2.0,
                2.0,
                2);
        }
        catch (ArithmeticException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }
}