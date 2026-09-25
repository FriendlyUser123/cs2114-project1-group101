package calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link FourFunctionCalculator}. Cases come from the Deliverable 2
 * test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class FourFunctionCalculatorTest
{
    private static final double DELTA = 0.0001;
    private FourFunctionCalculator calc;

    /**
     * Creates a fresh calculator before each test.
     */
    public void setUp()
    {
        calc = new FourFunctionCalculator();
    }


    /**
     * Tests the constructor.
     */
    @Test
    public void testFourFunctionCalculator()
    {
        FourFunctionCalculator testCalc = new FourFunctionCalculator();

        assertNotNull(testCalc);
        assertFalse(testCalc.hasLast());
    }


    /**
     * Tests whether the calculator correctly reports if a previous result
     * exists.
     */
    @Test
    public void testHasLast()
    {
        assertFalse(calc.hasLast());

        calc.setLast(8.5);

        assertTrue(calc.hasLast());
    }


    /**
     * Tests storing valid results and rejecting invalid results.
     */
    @Test
    public void testSetLast()
    {
        calc.setLast(8.5);

        assertTrue(calc.hasLast());
        assertEquals(8.5, calc.getLast(), DELTA);

        // NaN cannot reach this method through normal terminal input.
        IllegalArgumentException nanException = null;

        try
        {
            calc.setLast(Double.NaN);
        }
        catch (IllegalArgumentException e)
        {
            nanException = e;
        }

        assertNotNull(nanException);

        // Previous valid result should remain unchanged.
        assertEquals(8.5, calc.getLast(), DELTA);

        // Infinity cannot reach this method through normal terminal input.
        IllegalArgumentException infinityException = null;

        try
        {
            calc.setLast(Double.POSITIVE_INFINITY);
        }
        catch (IllegalArgumentException e)
        {
            infinityException = e;
        }

        assertNotNull(infinityException);

        // Previous valid result should still remain unchanged.
        assertEquals(8.5, calc.getLast(), DELTA);
    }


    /**
     * Tests retrieving the last result and attempting to retrieve a result
     * before one exists.
     */
    @Test
    public void testGetLast()
    {
        IllegalStateException exception = null;

        try
        {
            calc.getLast();
        }
        catch (IllegalStateException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        calc.setLast(-4.25);

        assertEquals(-4.25, calc.getLast(), DELTA);
    }


    /**
     * Tests validation of doubles.
     */
    @Test
    public void testCheckDouble()
    {
        // Normal finite values should be accepted.
        calc.checkDouble(5.0, -3.5);

        // First number is NaN.
        IllegalArgumentException firstNaNException = null;

        try
        {
            calc.checkDouble(Double.NaN, 5.0);
        }
        catch (IllegalArgumentException e)
        {
            firstNaNException = e;
        }

        assertNotNull(firstNaNException);

        // First number is infinite.
        IllegalArgumentException firstInfinityException = null;

        try
        {
            calc.checkDouble(Double.POSITIVE_INFINITY, 5.0);
        }
        catch (IllegalArgumentException e)
        {
            firstInfinityException = e;
        }

        assertNotNull(firstInfinityException);

        // Second number is NaN.
        IllegalArgumentException secondNaNException = null;

        try
        {
            calc.checkDouble(5.0, Double.NaN);
        }
        catch (IllegalArgumentException e)
        {
            secondNaNException = e;
        }

        assertNotNull(secondNaNException);

        // Second number is infinite.
        IllegalArgumentException secondInfinityException = null;

        try
        {
            calc.checkDouble(5.0, Double.POSITIVE_INFINITY);
        }
        catch (IllegalArgumentException e)
        {
            secondInfinityException = e;
        }

        assertNotNull(secondInfinityException);
    }


    /**
     * Tests normal addition and addition overflow.
     */
    @Test
    public void testAdd()
    {
        double result = calc.add(5.0, 8.0);

        assertEquals(13.0, result, DELTA);
        assertTrue(calc.hasLast());
        assertEquals(13.0, calc.getLast(), DELTA);

        ArithmeticException exception = null;

        try
        {
            calc.add(Double.MAX_VALUE, Double.MAX_VALUE);
        }
        catch (ArithmeticException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // Failed operation should not replace the valid answer.
        assertEquals(13.0, calc.getLast(), DELTA);
    }


    /**
     * Tests normal subtraction and subtraction overflow.
     */
    @Test
    public void testSubtract()
    {
        double result = calc.subtract(10.0, 4.0);

        assertEquals(6.0, result, DELTA);
        assertTrue(calc.hasLast());
        assertEquals(6.0, calc.getLast(), DELTA);

        ArithmeticException exception = null;

        try
        {
            calc.subtract(Double.MAX_VALUE, -Double.MAX_VALUE);
        }
        catch (ArithmeticException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // Failed operation should not replace the valid answer.
        assertEquals(6.0, calc.getLast(), DELTA);
    }


    /**
     * Tests normal multiplication and multiplication overflow.
     */
    @Test
    public void testMultiply()
    {
        double result = calc.multiply(4.0, 5.0);

        assertEquals(20.0, result, DELTA);
        assertTrue(calc.hasLast());
        assertEquals(20.0, calc.getLast(), DELTA);

        ArithmeticException exception = null;

        try
        {
            calc.multiply(Double.MAX_VALUE, 2.0);
        }
        catch (ArithmeticException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // Failed operation should not replace the valid answer.
        assertEquals(20.0, calc.getLast(), DELTA);
    }


    /**
     * Tests normal division, divide by zero, and division overflow.
     */
    @Test
    public void testDivide()
    {
        double result = calc.divide(10.0, 2.0);

        assertEquals(5.0, result, DELTA);
        assertTrue(calc.hasLast());
        assertEquals(5.0, calc.getLast(), DELTA);

        // Divide by zero.
        IllegalArgumentException zeroException = null;

        try
        {
            calc.divide(10.0, 0.0);
        }
        catch (IllegalArgumentException e)
        {
            zeroException = e;
        }

        assertNotNull(zeroException);

        // Failed operation should preserve the previous answer.
        assertEquals(5.0, calc.getLast(), DELTA);

        // Both operands are finite, but the resulting division
        // overflows to infinity.
        IllegalArgumentException overflowException = null;

        try
        {
            calc.divide(Double.MAX_VALUE, Double.MIN_VALUE);
        }
        catch (IllegalArgumentException e)
        {
            overflowException = e;
        }

        assertNotNull(overflowException);

        assertEquals(5.0, calc.getLast(), DELTA);
    }
}
