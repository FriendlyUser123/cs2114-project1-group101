package calculator;

/**
 * Tests for {@link DerivativeCalculator}.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class DerivativeCalculatorTest extends student.TestCase {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /** An x value large enough to overflow a squared or cubed term. */
    private static final double HUGE_X = 1e200;

    /** A derivative calculator created fresh before every test. */
    private DerivativeCalculator calc;

    /** The polynomial 3x^2 + 2x + 1, created fresh before every test. */
    private Polynomial poly;

    /**
     * Runs before every test.
     */
    public void setUp() {
        calc = new DerivativeCalculator();
        poly = new Polynomial();
        poly.addTerm(3.0, 2);
        poly.addTerm(2.0, 1);
        poly.addTerm(1.0, 0);
    }


    /**
     * Tests evaluating 3x^2 + 2x + 1 at several x values.
     */
    public void testEvaluatePolynomial() {
        assertEquals(17.0, calc.evaluatePolynomial(poly, 2.0), DELTA);
        assertEquals(1.0, calc.evaluatePolynomial(poly, 0.0), DELTA);
        assertEquals(2.0, calc.evaluatePolynomial(poly, -1.0), DELTA);
    }


    /**
     * Tests that evaluatePolynomial rejects bad input and overflow.
     */
    public void testEvaluatePolynomialExceptions() {
        IllegalArgumentException nullException = null;

        try {
            calc.evaluatePolynomial(null, 2.0);
        }
        catch (IllegalArgumentException e) {
            nullException = e;
        }

        assertNotNull(nullException);

        IllegalArgumentException nanException = null;

        try {
            calc.evaluatePolynomial(poly, Double.NaN);
        }
        catch (IllegalArgumentException e) {
            nanException = e;
        }

        assertNotNull(nanException);

        IllegalArgumentException infinityException = null;

        try {
            calc.evaluatePolynomial(poly, Double.POSITIVE_INFINITY);
        }
        catch (IllegalArgumentException e) {
            infinityException = e;
        }

        assertNotNull(infinityException);

        ArithmeticException overflowException = null;

        try {
            calc.evaluatePolynomial(poly, HUGE_X);
        }
        catch (ArithmeticException e) {
            overflowException = e;
        }

        assertNotNull(overflowException);
    }


    /**
     * Tests the derivative of 3x^2 + 2x + 1, which is 6x + 2, and the
     * derivative of a constant, which is 0.
     */
    public void testEvaluateDerivative() {
        assertEquals(14.0, calc.evaluateDerivative(poly, 2.0), DELTA);
        assertEquals(2.0, calc.evaluateDerivative(poly, 0.0), DELTA);
        assertEquals(-4.0, calc.evaluateDerivative(poly, -1.0), DELTA);

        Polynomial constant = new Polynomial();
        constant.addTerm(7.0, 0);

        assertEquals(0.0, calc.evaluateDerivative(constant, 5.0), DELTA);
    }


    /**
     * Tests that evaluateDerivative rejects bad input and overflow.
     */
    public void testEvaluateDerivativeExceptions() {
        IllegalArgumentException nullException = null;

        try {
            calc.evaluateDerivative(null, 2.0);
        }
        catch (IllegalArgumentException e) {
            nullException = e;
        }

        assertNotNull(nullException);

        IllegalArgumentException nanException = null;

        try {
            calc.evaluateDerivative(poly, Double.NaN);
        }
        catch (IllegalArgumentException e) {
            nanException = e;
        }

        assertNotNull(nanException);

        IllegalArgumentException infinityException = null;

        try {
            calc.evaluateDerivative(poly, Double.NEGATIVE_INFINITY);
        }
        catch (IllegalArgumentException e) {
            infinityException = e;
        }

        assertNotNull(infinityException);

        Polynomial cubic = new Polynomial();
        cubic.addTerm(1.0, 3);

        ArithmeticException overflowException = null;

        try {
            calc.evaluateDerivative(cubic, HUGE_X);
        }
        catch (ArithmeticException e) {
            overflowException = e;
        }

        assertNotNull(overflowException);
    }
}
