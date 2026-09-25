package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

/**
 * Tests for {@link OverallCalculator}. Cases come from the Deliverable 2 test
 * plan. The run methods are tested by passing a Scanner built from a String,
 * which stands in for someone typing at the keyboard.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class OverallCalculatorTest {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /** A calculator with no pending input, for the parse-method tests. */
    private OverallCalculator calc;

    /**
     * Runs before every test.
     */
    @Before
    public void setUp() {
        calc = new OverallCalculator(new Scanner(""));
    }


    /** Normal: "5" gives 5. */
    @Test
    public void testParseDegree() {
        fail("TODO: assertEquals(5, calc.parseDegree(\"5\"))");
    }


    /** Bad: "-2" throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseDegreeNegative() {
        fail("TODO: calc.parseDegree(\"-2\")");
    }


    /** Bad: "3.5" throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseDegreeDecimal() {
        fail("TODO: calc.parseDegree(\"3.5\")");
    }


    /** Normal: "5.25" gives 5.25. */
    @Test
    public void testParseValidDouble() {
        fail("TODO: assertEquals(5.25, calc.parseValidDouble(\"5.25\"), DELTA)");
    }


    /** Bad: "2..5" throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseValidDoubleMalformed() {
        fail("TODO: calc.parseValidDouble(\"2..5\")");
    }


    /** Normal: "+" gives '+'. */
    @Test
    public void testParseBasicOperator() {
        fail("TODO: assertEquals('+', calc.parseBasicOperator(\"+\"))");
    }


    /** Bad: "^" throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseBasicOperatorInvalid() {
        fail("TODO: calc.parseBasicOperator(\"^\")");
    }


    /** Normal: "ANS" with a previous result of 8 gives 8. */
    @Test
    public void testParseBasicValueAns() {
        fail("TODO: produce a last answer of 8 first (e.g. through runFourFunctionCalculator with a Scanner), then parseBasicValue(\"ANS\")");
    }


    /** Bad: "ANS" with no previous result throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseBasicValueAnsWithoutResult() {
        fail("TODO: calc.parseBasicValue(\"ANS\")");
    }


    /** Normal: "*" gives '*'. */
    @Test
    public void testParsePolynomialOperator() {
        fail("TODO: assertEquals('*', calc.parsePolynomialOperator(\"*\"))");
    }


    /** Bad: "/" throws (no polynomial division). */
    @Test(expected = IllegalArgumentException.class)
    public void testParsePolynomialOperatorDivide() {
        fail("TODO: calc.parsePolynomialOperator(\"/\")");
    }


    /** Normal: "2" gives 2. */
    @Test
    public void testParseMenuChoice() {
        fail("TODO: assertEquals(2, calc.parseMenuChoice(\"2\"))");
    }


    /** Bad: "4" throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseMenuChoiceOutOfRange() {
        fail("TODO: calc.parseMenuChoice(\"4\")");
    }


    /** Bad: "abc" throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseMenuChoiceNotNumber() {
        fail("TODO: calc.parseMenuChoice(\"abc\")");
    }


    /** Normal: typing 5, +, 8 returns 13. */
    @Test
    public void testRunFourFunctionCalculator() {
        fail("TODO: new OverallCalculator(new Scanner(\"5\\n+\\n8\\n\")).runFourFunctionCalculator() == 13");
    }


    /** Bad: an invalid value is rejected and asked for again. */
    @Test
    public void testRunFourFunctionCalculatorBadThenGood() {
        fail("TODO: Scanner(\"abc\\n5\\n+\\n8\\n\") should still return 13");
    }


    /** Normal: two valid polynomials and + returns their sum. */
    @Test
    public void testRunPolynomialCalculator() {
        fail("TODO: script the degree/coefficient prompts in a Scanner and assert toString()");
    }


    /** Normal: valid degrees and coefficients build the Polynomial. */
    @Test
    public void testGetPolynomialFromUser() {
        fail("TODO: script the prompts and assert toString()");
    }

}
