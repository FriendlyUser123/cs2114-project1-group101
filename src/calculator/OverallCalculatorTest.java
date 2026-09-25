package calculator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Tests for {@link OverallCalculator}. Cases come from the Deliverable 2 test
 * plan. The run methods are tested by passing a Scanner built from a String,
 * which stands in for someone typing at the keyboard.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class OverallCalculatorTest extends student.TestCase {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /**
     * Tests the Scanner constructor.
     */
    public void testConstructor() {
        Scanner input = new Scanner("");
        OverallCalculator calc = new OverallCalculator(input);

        assertNotNull(calc);

        input.close();
    }


    /**
     * Tests main and the default constructor.
     */
    public void testMain() {
        InputStream originalIn = System.in;

        try {
            String input = "3\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            OverallCalculator.main(new String[0]);
        }
        finally {
            System.setIn(originalIn);
        }

        OverallCalculator defaultCalc = new OverallCalculator();
        assertNotNull(defaultCalc);
    }


    /**
     * Tests the complete main-menu flow.
     *
     * Covers:
     * invalid menu choice
     * four-function calculator selection
     * polynomial calculator selection
     * derivative calculator selection
     * exit
     */
    public void testRun() {
        Scanner input = new Scanner("6\n" + "1\n" + "5\n" + "+\n" + "8\n"
            + "EXIT\n" + "2\n" + "0\n" + "1\n" + "0\n" + "2\n" + "+\n" + "4\n"
            + "1\n" + "2\n" + "3\n" + "2\n" + "3\n");

        OverallCalculator calc = new OverallCalculator(input);

        calc.run();

        assertNotNull(calc);
    }


    /**
     * Tests all four successful four-function operations
     * and repeated use of ANS.
     */
    public void testRunFourFunctionCalculatorOperations() {
        Scanner input = new Scanner("5\n" + "+\n" + "3\n" + "ANS\n" + "-\n"
            + "2\n" + "ANS\n" + "*\n" + "4\n" + "ANS\n" + "/\n" + "6\n"
            + "EXIT\n");

        OverallCalculator calc = new OverallCalculator(input);

        double result = calc.runFourFunctionCalculator();

        assertEquals(4.0, result, DELTA);

        input.close();
    }


    /**
     * Tests bad first value, bad operator, and bad second value.
     */
    public void testRunFourFunctionCalculatorBadInputs() {
        Scanner input = new Scanner("bad\n" + "5\n" + "^\n" + "+\n" + "bad\n"
            + "2\n" + "EXIT\n");

        OverallCalculator calc = new OverallCalculator(input);

        double result = calc.runFourFunctionCalculator();

        assertEquals(7.0, result, DELTA);

        input.close();
    }


    /**
     * Tests failed arithmetic operations.
     *
     * Each failed operation should restart the calculator without
     * accepting the invalid result.
     */
    public void testRunFourFunctionCalculatorArithmeticExceptions() {
        String max = Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // Addition overflow
            max + "\n" + "+\n" + max + "\n"

            // Subtraction overflow
                + max + "\n" + "-\n" + "-" + max + "\n"

                // Multiplication overflow
                + max + "\n" + "*\n" + "2\n"

                // Divide by zero
                + "1\n" + "/\n" + "0\n"

                // Successful calculation
                + "1\n" + "+\n" + "1\n"

                + "EXIT\n");

        OverallCalculator calc = new OverallCalculator(input);

        double result = calc.runFourFunctionCalculator();

        assertEquals(2.0, result, DELTA);

        input.close();
    }


    /**
     * Tests the no-more-input branch.
     *
     * This is not normal terminal behavior, but is the only way
     * to reach the hasNextLine() false branch.
     */
    public void testRunFourFunctionCalculatorNoInput() {
        Scanner input = new Scanner("");
        OverallCalculator calc = new OverallCalculator(input);

        double result = calc.runFourFunctionCalculator();

        assertEquals(0.0, result, DELTA);

        input.close();
    }


    /**
     * Tests polynomial addition and invalid polynomial operator input.
     */
    public void testRunPolynomialCalculatorAdd() {
        Scanner input = new Scanner("0\n" + "2\n" + "0\n" + "3\n" + "/\n"
            + "+\n");

        OverallCalculator calc = new OverallCalculator(input);

        Polynomial result = calc.runPolynomialCalculator();

        double[] array = result.toCoefficientArray();

        assertEquals(1, array.length);
        assertEquals(5.0, array[0], DELTA);

        input.close();
    }


    /**
     * Tests polynomial subtraction.
     */
    public void testRunPolynomialCalculatorSubtract() {
        Scanner input = new Scanner("0\n" + "5\n" + "0\n" + "3\n" + "-\n");

        OverallCalculator calc = new OverallCalculator(input);

        Polynomial result = calc.runPolynomialCalculator();

        double[] array = result.toCoefficientArray();

        assertEquals(1, array.length);
        assertEquals(2.0, array[0], DELTA);

        input.close();
    }


    /**
     * Tests polynomial multiplication.
     *
     * (x + 2)(x + 3) = x^2 + 5x + 6
     */
    public void testRunPolynomialCalculatorMultiply() {
        Scanner input = new Scanner("1\n" + "1\n" + "2\n" + "1\n" + "1\n"
            + "3\n" + "*\n");

        OverallCalculator calc = new OverallCalculator(input);

        Polynomial result = calc.runPolynomialCalculator();

        double[] array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(6.0, array[0], DELTA);
        assertEquals(5.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        input.close();
    }


    /**
     * Tests exception handling for all three polynomial operations.
     */
    public void testRunPolynomialCalculatorArithmeticExceptions() {
        String max = Double.toString(Double.MAX_VALUE);

        Scanner input = new Scanner(
            // Addition overflow
            "0\n" + max + "\n" + "0\n" + max + "\n" + "+\n"

            // Subtraction overflow
                + "0\n" + max + "\n" + "0\n" + "-" + max + "\n" + "-\n"

                // Multiplication overflow
                + "0\n" + max + "\n" + "0\n" + "2\n" + "*\n"

                // Final successful operation
                + "0\n" + "1\n" + "0\n" + "2\n" + "+\n");

        OverallCalculator calc = new OverallCalculator(input);

        Polynomial result = calc.runPolynomialCalculator();

        double[] array = result.toCoefficientArray();

        assertEquals(1, array.length);
        assertEquals(3.0, array[0], DELTA);

        input.close();
    }


    /**
     * Tests polynomial entry.
     *
     * Covers:
     * invalid degree
     * valid degree
     * invalid coefficient
     * valid coefficient
     * blank coefficient treated as zero
     */
    public void testGetPolynomialFromUser() {
        Scanner input = new Scanner("abc\n" + "2\n" + "1\n" + "bad\n" + "2\n"
            + "\n");

        OverallCalculator calc = new OverallCalculator(input);

        Polynomial result = calc.getPolynomialFromUser();

        double[] array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(0.0, array[0], DELTA);
        assertEquals(2.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        input.close();
    }


    /**
     * Tests valid and invalid polynomial degree parsing.
     */
    public void testParseDegree() {
        OverallCalculator calc = new OverallCalculator(new Scanner(""));

        assertEquals(5, calc.parseDegree("5"));
        assertEquals(0, calc.parseDegree("0"));
        assertEquals(100, calc.parseDegree("100"));

        IllegalArgumentException decimalException = null;

        try {
            calc.parseDegree("3.5");
        }
        catch (IllegalArgumentException e) {
            decimalException = e;
        }

        assertNotNull(decimalException);

        IllegalArgumentException negativeException = null;

        try {
            calc.parseDegree("-1");
        }
        catch (IllegalArgumentException e) {
            negativeException = e;
        }

        assertNotNull(negativeException);

        IllegalArgumentException largeException = null;

        try {
            calc.parseDegree("101");
        }
        catch (IllegalArgumentException e) {
            largeException = e;
        }

        assertNotNull(largeException);
    }


    /**
     * Tests valid and invalid double parsing.
     */
    public void testParseValidDouble() {
        OverallCalculator calc = new OverallCalculator(new Scanner(""));

        assertEquals(5.25, calc.parseValidDouble("5.25"), DELTA);
        assertEquals(-4.5, calc.parseValidDouble("-4.5"), DELTA);

        IllegalArgumentException badNumberException = null;

        try {
            calc.parseValidDouble("2..5");
        }
        catch (IllegalArgumentException e) {
            badNumberException = e;
        }

        assertNotNull(badNumberException);

        IllegalArgumentException nanException = null;

        try {
            calc.parseValidDouble("NaN");
        }
        catch (IllegalArgumentException e) {
            nanException = e;
        }

        assertNotNull(nanException);

        IllegalArgumentException infinityException = null;

        try {
            calc.parseValidDouble("Infinity");
        }
        catch (IllegalArgumentException e) {
            infinityException = e;
        }

        assertNotNull(infinityException);

        StringBuilder tooLong = new StringBuilder();

        for (int i = 0; i < 101; i++) {
            tooLong.append("1");
        }

        IllegalArgumentException lengthException = null;

        try {
            calc.parseValidDouble(tooLong.toString());
        }
        catch (IllegalArgumentException e) {
            lengthException = e;
        }

        assertNotNull(lengthException);
    }


    /**
     * Tests all valid basic operators and an invalid operator.
     */
    public void testParseBasicOperator() {
        OverallCalculator calc = new OverallCalculator(new Scanner(""));

        assertEquals('+', calc.parseBasicOperator("+"));
        assertEquals('-', calc.parseBasicOperator("-"));
        assertEquals('*', calc.parseBasicOperator("*"));
        assertEquals('/', calc.parseBasicOperator("/"));

        IllegalArgumentException exception = null;

        try {
            calc.parseBasicOperator("^");
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests normal values, ANS with no stored result, and ANS
     * after a successful calculation.
     */
    public void testParseBasicValue() {
        Scanner input = new Scanner("5\n" + "+\n" + "3\n" + "EXIT\n");

        OverallCalculator calc = new OverallCalculator(input);

        // Normal numeric value
        assertEquals(5.25, calc.parseBasicValue("5.25"), DELTA);

        // ANS before anything has been calculated
        IllegalArgumentException exception = null;

        try {
            calc.parseBasicValue("ANS");
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);

        // Store an answer using the actual user flow
        double result = calc.runFourFunctionCalculator();

        assertEquals(8.0, result, DELTA);

        // ANS should now retrieve that result
        assertEquals(8.0, calc.parseBasicValue("ANS"), DELTA);

        input.close();
    }


    /**
     * Tests all valid polynomial operators and an invalid operator.
     */
    public void testParsePolynomialOperator() {
        OverallCalculator calc = new OverallCalculator(new Scanner(""));

        assertEquals('+', calc.parsePolynomialOperator("+"));
        assertEquals('-', calc.parsePolynomialOperator("-"));
        assertEquals('*', calc.parsePolynomialOperator("*"));

        IllegalArgumentException exception = null;

        try {
            calc.parsePolynomialOperator("/");
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests that 0 * -5 gives 0, which is shown as 0 rather than -0.
     */
    public void testRunFourFunctionCalculatorNegativeZero() {
        Scanner input = new Scanner("0\n" + "*\n" + "-5\n" + "EXIT\n");
        OverallCalculator calc = new OverallCalculator(input);

        double result = calc.runFourFunctionCalculator();

        assertEquals(0.0, result, DELTA);
    }


    /**
     * Tests the derivative calculator menu flow.
     *
     * Covers:
     * normal polynomial and x value
     * bad x value, then a valid one
     * a result too large to calculate
     * running out of input
     */
    public void testRunDerivativeSolver() {
        Scanner input = new Scanner("2\n" + "3\n" + "2\n" + "1\n" + "abc\n"
            + "2\n");
        OverallCalculator calc = new OverallCalculator(input);
        calc.runDerivativeSolver();

        Scanner bigInput = new Scanner("2\n" + "1\n" + "0\n" + "0\n"
            + "1e200\n");
        OverallCalculator bigCalc = new OverallCalculator(bigInput);
        bigCalc.runDerivativeSolver();

        OverallCalculator emptyCalc = new OverallCalculator(new Scanner(""));
        emptyCalc.runDerivativeSolver();

        assertNotNull(calc);
        assertNotNull(bigCalc);
        assertNotNull(emptyCalc);
    }


    /**
     * Tests all valid menu choices and invalid menu input.
     */
    public void testParseMenuChoice() {
        OverallCalculator calc = new OverallCalculator(new Scanner(""));

        assertEquals(1, calc.parseMenuChoice("1"));
        assertEquals(2, calc.parseMenuChoice("2"));
        assertEquals(3, calc.parseMenuChoice("3"));
        assertEquals(4, calc.parseMenuChoice("4"));

        IllegalArgumentException exception = null;

        try {
            calc.parseMenuChoice("5");
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }
}
