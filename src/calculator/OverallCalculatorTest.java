package calculator;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.util.Scanner;
import static org.junit.Assert.*;

/**
 * Tests for {@link OverallCalculator}. Cases come from the Deliverable 2 test
 * plan. The run methods are tested by passing a Scanner built from a String,
 * which stands in for someone typing at the keyboard.
 *
 * @author Aditya Banerjee (adityab7)
 * @author Ethan Gearhart (ethang06)
 * @author Nandini Duggaraju (nduggaraju)
 * @version 2026.09.25
 */
public class OverallCalculatorTest extends student.TestCase
{
    private static final double DELTA = 0.0001;


    /**
     * Tests the constructor that accepts a Scanner.
     */
    public void testScannerConstructor()
    {
        Scanner input = new Scanner("");
        OverallCalculator calc = new OverallCalculator(input);

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests main() and the default constructor.
     */
    public void testMain()
    {
        InputStream originalIn = System.in;

        try
        {
            System.setIn(
                new ByteArrayInputStream("3\n".getBytes()));

            OverallCalculator.main(new String[0]);
        }
        finally
        {
            System.setIn(originalIn);
        }

        assertTrue(System.in == originalIn);
    }


    /**
     * Tests run() returning when no menu input is available.
     */
    public void testRunNoInput()
    {
        Scanner input = new Scanner("");
        OverallCalculator calc = new OverallCalculator(input);

        calc.run();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests invalid menu input and all five menu choices.
     */
    public void testRunAllMenuChoices()
    {
        Scanner input = new Scanner(
            "9\n"

            // Four-function calculator
            + "1\n"
            + "EXIT\n"

            // Polynomial calculator: 1 + 2
            + "2\n"
            + "0\n"
            + "1\n"
            + "0\n"
            + "2\n"
            + "+\n"

            // Derivative calculator: f(x) = 1 at x = 2
            + "4\n"
            + "0\n"
            + "1\n"
            + "2\n"

            // Integral calculator: integral of 1 from 0 to 2
            + "5\n"
            + "0\n"
            + "1\n"
            + "0\n"
            + "2\n"
            + "2\n"

            // Exit
            + "3\n");

        OverallCalculator calc = new OverallCalculator(input);

        calc.run();

        assertNotNull(calc);
    }


    /**
     * Tests all four successful arithmetic operations.
     * Also forces formatResult() to format a tiny negative
     * value as 0 instead of -0.
     */
    public void testRunFourFunctionOperations()
    {
        Scanner input = new Scanner(
            // Tiny negative result -> "-0" formatting branch
            "-0.0000001\n"
            + "+\n"
            + "0\n"

            // Subtraction
            + "10\n"
            + "-\n"
            + "4\n"

            // Multiplication
            + "4\n"
            + "*\n"
            + "5\n"

            // Division
            + "10\n"
            + "/\n"
            + "2\n"

            + "EXIT\n");

        OverallCalculator calc = new OverallCalculator(input);

        double result =
            calc.runFourFunctionCalculator();

        assertEquals(5.0, result, DELTA);

        input.close();
    }


    /**
     * Tests invalid first value, operator, and second value.
     */
    public void testRunFourFunctionBadInput()
    {
        Scanner input = new Scanner(
            "bad\n"
            + "5\n"
            + "^\n"
            + "+\n"
            + "bad\n"
            + "2\n"
            + "EXIT\n");

        OverallCalculator calc = new OverallCalculator(input);

        double result =
            calc.runFourFunctionCalculator();

        assertEquals(7.0, result, DELTA);

        input.close();
    }


    /**
     * Tests the exception catch for every arithmetic operator.
     */
    public void testRunFourFunctionOperationExceptions()
    {
        String max =
            Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // Addition overflow
            max + "\n"
            + "+\n"
            + max + "\n"

            // Subtraction overflow
            + max + "\n"
            + "-\n"
            + "-" + max + "\n"

            // Multiplication overflow
            + max + "\n"
            + "*\n"
            + "2\n"

            // Divide by zero
            + "1\n"
            + "/\n"
            + "0\n"

            // Successful calculation afterward
            + "1\n"
            + "+\n"
            + "1\n"

            + "EXIT\n");

        OverallCalculator calc = new OverallCalculator(input);

        double result =
            calc.runFourFunctionCalculator();

        assertEquals(2.0, result, DELTA);

        input.close();
    }


    /**
     * Tests running out of input while waiting for value 1.
     */
    public void testRunFourFunctionNoFirstValue()
    {
        Scanner input = new Scanner("");
        OverallCalculator calc =
            new OverallCalculator(input);

        double result =
            calc.runFourFunctionCalculator();

        assertEquals(0.0, result, DELTA);

        input.close();
    }


    /**
     * Tests running out of input while waiting for an operator.
     */
    public void testRunFourFunctionNoOperator()
    {
        Scanner input = new Scanner("5\n");
        OverallCalculator calc =
            new OverallCalculator(input);

        double result =
            calc.runFourFunctionCalculator();

        assertEquals(0.0, result, DELTA);

        input.close();
    }


    /**
     * Tests running out of input while waiting for value 2.
     */
    public void testRunFourFunctionNoSecondValue()
    {
        Scanner input =
            new Scanner("5\n+\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        double result =
            calc.runFourFunctionCalculator();

        assertEquals(0.0, result, DELTA);

        input.close();
    }


    /**
     * Tests polynomial addition and invalid operator re-prompting.
     */
    public void testRunPolynomialAdd()
    {
        Scanner input = new Scanner(
            "0\n"
            + "2\n"
            + "0\n"
            + "3\n"
            + "/\n"
            + "+\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.runPolynomialCalculator();

        double[] array =
            result.toCoefficientArray();

        assertEquals(1, array.length);
        assertEquals(5.0, array[0], DELTA);

        input.close();
    }


    /**
     * Tests polynomial subtraction.
     */
    public void testRunPolynomialSubtract()
    {
        Scanner input =
            new Scanner(
                "0\n5\n"
                + "0\n3\n"
                + "-\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.runPolynomialCalculator();

        assertEquals(
            2.0,
            result.toCoefficientArray()[0],
            DELTA);

        input.close();
    }


    /**
     * Tests polynomial multiplication.
     */
    public void testRunPolynomialMultiply()
    {
        Scanner input = new Scanner(
            // x + 2
            "1\n"
            + "1\n"
            + "2\n"

            // x + 3
            + "1\n"
            + "1\n"
            + "3\n"

            + "*\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.runPolynomialCalculator();

        double[] array =
            result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(6.0, array[0], DELTA);
        assertEquals(5.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        input.close();
    }


    /**
     * Tests running out of input while waiting for
     * the polynomial operator.
     */
    public void testRunPolynomialNoOperator()
    {
        Scanner input =
            new Scanner(
                "0\n1\n"
                + "0\n2\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.runPolynomialCalculator();

        assertEquals("0", result.toString());

        input.close();
    }


    /**
     * Tests the polynomial addition exception catch.
     */
    public void testRunPolynomialAddException()
    {
        String max =
            Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // Overflow
            "0\n"
            + max + "\n"
            + "0\n"
            + max + "\n"
            + "+\n"

            // Valid retry: 1 + 2
            + "0\n1\n"
            + "0\n2\n"
            + "+\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.runPolynomialCalculator();

        assertEquals(
            3.0,
            result.toCoefficientArray()[0],
            DELTA);

        input.close();
    }


    /**
     * Tests the polynomial subtraction exception catch.
     */
    public void testRunPolynomialSubtractException()
    {
        String max =
            Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // Overflow
            "0\n"
            + max + "\n"
            + "0\n"
            + "-" + max + "\n"
            + "-\n"

            // Valid retry: 3 - 1
            + "0\n3\n"
            + "0\n1\n"
            + "-\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.runPolynomialCalculator();

        assertEquals(
            2.0,
            result.toCoefficientArray()[0],
            DELTA);

        input.close();
    }


    /**
     * Tests the polynomial multiplication exception catch.
     */
    public void testRunPolynomialMultiplyException()
    {
        String max =
            Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // Overflow
            "0\n"
            + max + "\n"
            + "0\n"
            + "2\n"
            + "*\n"

            // Valid retry: 2 * 3
            + "0\n2\n"
            + "0\n3\n"
            + "*\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.runPolynomialCalculator();

        assertEquals(
            6.0,
            result.toCoefficientArray()[0],
            DELTA);

        input.close();
    }


    /**
     * Tests polynomial entry including:
     * bad degree, bad coefficient, valid coefficients,
     * and blank coefficient = 0.
     */
    public void testGetPolynomialFromUser()
    {
        Scanner input = new Scanner(
            "bad\n"
            + "2\n"
            + "1\n"
            + "bad\n"
            + "2\n"
            + "\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.getPolynomialFromUser();

        double[] array =
            result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(0.0, array[0], DELTA);
        assertEquals(2.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        input.close();
    }


    /**
     * Tests running out of input while waiting for degree.
     */
    public void testGetPolynomialNoDegree()
    {
        Scanner input = new Scanner("");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.getPolynomialFromUser();

        assertEquals("0", result.toString());

        input.close();
    }


    /**
     * Tests running out of input while waiting for a coefficient.
     */
    public void testGetPolynomialNoCoefficient()
    {
        Scanner input =
            new Scanner("2\n1\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        Polynomial result =
            calc.getPolynomialFromUser();

        assertEquals("x^2", result.toString());

        input.close();
    }


    /**
     * Tests normal derivative operation and invalid x re-prompting.
     */
    public void testRunDerivativeSolver()
    {
        Scanner input = new Scanner(
            // x^2
            "2\n"
            + "1\n"
            + "0\n"
            + "0\n"

            // Invalid then valid x
            + "bad\n"
            + "2\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runDerivativeSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests running out of input while waiting for x.
     */
    public void testRunDerivativeNoX()
    {
        Scanner input =
            new Scanner("0\n1\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runDerivativeSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests the arithmetic exception catch in the derivative solver.
     */
    public void testRunDerivativeArithmeticException()
    {
        String max =
            Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // x^2
            "2\n"
            + "1\n"
            + "0\n"
            + "0\n"

            // Evaluating x^2 here overflows
            + max + "\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runDerivativeSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests normal integral operation plus invalid
     * lower bound, upper bound, and interval re-prompting.
     */
    public void testRunIntegralSolver()
    {
        Scanner input = new Scanner(
            // f(x) = 1
            "0\n"
            + "1\n"

            // Lower bound
            + "bad\n"
            + "0\n"

            // Upper bound
            + "bad\n"
            + "2\n"

            // Intervals
            + "bad\n"
            + "2\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runIntegralSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests running out of input while waiting for lower bound.
     */
    public void testRunIntegralNoLower()
    {
        Scanner input =
            new Scanner("0\n1\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runIntegralSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests running out of input while waiting for upper bound.
     */
    public void testRunIntegralNoUpper()
    {
        Scanner input =
            new Scanner(
                "0\n1\n"
                + "0\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runIntegralSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests running out of input while waiting for interval count.
     */
    public void testRunIntegralNoInterval()
    {
        Scanner input =
            new Scanner(
                "0\n1\n"
                + "0\n"
                + "2\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runIntegralSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests the arithmetic exception catch in the integral solver.
     */
    public void testRunIntegralArithmeticException()
    {
        String max =
            Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // Very large constant polynomial
            "0\n"
            + max + "\n"

            + "0\n"
            + "2\n"
            + "2\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        calc.runIntegralSolver();

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests normal, malformed, negative, and excessive degrees.
     */
    public void testParseDegree()
    {
        OverallCalculator calc =
            new OverallCalculator(new Scanner(""));

        assertEquals(0, calc.parseDegree("0"));
        assertEquals(100, calc.parseDegree("100"));


        IllegalArgumentException formatException = null;

        try
        {
            calc.parseDegree("3.5");
        }
        catch (IllegalArgumentException e)
        {
            formatException = e;
        }

        assertNotNull(formatException);


        IllegalArgumentException negativeException = null;

        try
        {
            calc.parseDegree("-1");
        }
        catch (IllegalArgumentException e)
        {
            negativeException = e;
        }

        assertNotNull(negativeException);


        IllegalArgumentException largeException = null;

        try
        {
            calc.parseDegree("101");
        }
        catch (IllegalArgumentException e)
        {
            largeException = e;
        }

        assertNotNull(largeException);
    }


    /**
     * Tests normal, malformed, too-long, NaN,
     * and infinite numeric inputs.
     */
    public void testParseValidDouble()
    {
        OverallCalculator calc =
            new OverallCalculator(new Scanner(""));

        assertEquals(
            -2.5,
            calc.parseValidDouble("-2.5"),
            DELTA);


        StringBuilder longNumber =
            new StringBuilder();

        for (int i = 0; i < 101; i++)
        {
            longNumber.append("1");
        }


        IllegalArgumentException lengthException = null;

        try
        {
            calc.parseValidDouble(
                longNumber.toString());
        }
        catch (IllegalArgumentException e)
        {
            lengthException = e;
        }

        assertNotNull(lengthException);


        IllegalArgumentException formatException = null;

        try
        {
            calc.parseValidDouble("2..5");
        }
        catch (IllegalArgumentException e)
        {
            formatException = e;
        }

        assertNotNull(formatException);


        IllegalArgumentException nanException = null;

        try
        {
            calc.parseValidDouble("NaN");
        }
        catch (IllegalArgumentException e)
        {
            nanException = e;
        }

        assertNotNull(nanException);


        IllegalArgumentException infinityException = null;

        try
        {
            calc.parseValidDouble("Infinity");
        }
        catch (IllegalArgumentException e)
        {
            infinityException = e;
        }

        assertNotNull(infinityException);
    }


    /**
     * Tests all four valid basic operators and invalid input.
     */
    public void testParseBasicOperator()
    {
        OverallCalculator calc =
            new OverallCalculator(new Scanner(""));

        assertEquals(
            '+',
            calc.parseBasicOperator("+"));

        assertEquals(
            '-',
            calc.parseBasicOperator("-"));

        assertEquals(
            '*',
            calc.parseBasicOperator("*"));

        assertEquals(
            '/',
            calc.parseBasicOperator("/"));


        IllegalArgumentException exception = null;

        try
        {
            calc.parseBasicOperator("^");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests normal numbers, ANS without memory,
     * and ANS after an actual calculation.
     */
    public void testParseBasicValue()
    {
        Scanner input =
            new Scanner(
                "5\n"
                + "+\n"
                + "3\n"
                + "EXIT\n");

        OverallCalculator calc =
            new OverallCalculator(input);

        assertEquals(
            2.5,
            calc.parseBasicValue("2.5"),
            DELTA);


        IllegalArgumentException exception = null;

        try
        {
            calc.parseBasicValue("ANS");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);


        // Store ANS through the actual user-facing calculation path.
        double result =
            calc.runFourFunctionCalculator();

        assertEquals(8.0, result, DELTA);

        assertEquals(
            8.0,
            calc.parseBasicValue("ANS"),
            DELTA);

        input.close();
    }


    /**
     * Tests all polynomial operators and invalid input.
     */
    public void testParsePolynomialOperator()
    {
        OverallCalculator calc =
            new OverallCalculator(new Scanner(""));

        assertEquals(
            '+',
            calc.parsePolynomialOperator("+"));

        assertEquals(
            '-',
            calc.parsePolynomialOperator("-"));

        assertEquals(
            '*',
            calc.parsePolynomialOperator("*"));


        IllegalArgumentException exception = null;

        try
        {
            calc.parsePolynomialOperator("/");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests all five menu choices and invalid input.
     */
    public void testParseMenuChoice()
    {
        OverallCalculator calc =
            new OverallCalculator(new Scanner(""));

        assertEquals(
            1,
            calc.parseMenuChoice("1"));

        assertEquals(
            2,
            calc.parseMenuChoice("2"));

        assertEquals(
            3,
            calc.parseMenuChoice("3"));

        assertEquals(
            4,
            calc.parseMenuChoice("4"));

        assertEquals(
            5,
            calc.parseMenuChoice("5"));


        IllegalArgumentException exception = null;

        try
        {
            calc.parseMenuChoice("6");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }
    
    /**
     * Tests interval validation in the integral solver.
     * Covers malformed, zero, odd, and valid interval inputs.
     */
    public void testRunIntegralInvalidIntervals()
    {
        Scanner input = new Scanner(
            
            "0\n"
            + "1\n"
            + "0\n"
            + "2\n"
            + "bad\n"
            + "0\n"
            + "3\n"
            + "2\n");

        OverallCalculator calc = new OverallCalculator(input);

        calc.runIntegralSolver();

        assertNotNull(calc);

        input.close();
    }
}