package calculator;

/**
 * Performs arithmetic on {@link Polynomial} objects: addition, subtraction,
 * and multiplication.
 *
 * Suggested approach for every operation: convert both inputs with
 * {@link Polynomial#toCoefficientArray()}, do the math on the arrays (index =
 * degree), then build the answer with
 * {@link Polynomial#fromCoefficientArray(double[])}.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class PolynomialCalculator {

    // ~ Constructors ..........................................................

    /**
     * Creates a polynomial calculator. It keeps no state.
     */
    public PolynomialCalculator() {
        // Nothing to initialize: every operation works only on its arguments.
    }

    // ~ Public Methods ........................................................

    /**
     * Adds two polynomials.
     *
     * Spec test plan: normal 3x^2 + 2x and 2x^2 + 1 -> 5x^2 + 2x + 1;
     * bad (null polynomial) -> exception.
     *
     * @param poly1
     *            the first polynomial
     * @param poly2
     *            the second polynomial
     * @return a new polynomial equal to poly1 + poly2
     * @throws IllegalArgumentException
     *             if either polynomial is null
     */
    public Polynomial add(Polynomial poly1, Polynomial poly2) {
        // TODO: reject nulls; result array length = the longer of the two;
        // result[i] = a[i] + b[i], treating a missing index as 0.
        throw new UnsupportedOperationException(
            "TODO: PolynomialCalculator.add");
    }


    /**
     * Subtracts the second polynomial from the first.
     *
     * Spec test plan: normal 5x^2 + 3x and 2x^2 + x -> 3x^2 + 2x;
     * bad (null polynomial) -> exception.
     *
     * @param poly1
     *            the polynomial to subtract from
     * @param poly2
     *            the polynomial to subtract
     * @return a new polynomial equal to poly1 - poly2
     * @throws IllegalArgumentException
     *             if either polynomial is null
     */
    public Polynomial subtract(Polynomial poly1, Polynomial poly2) {
        // TODO: same as add but a[i] - b[i].
        throw new UnsupportedOperationException(
            "TODO: PolynomialCalculator.subtract");
    }


    /**
     * Multiplies two polynomials.
     *
     * Spec test plan: normal x + 2 and x + 3 -> x^2 + 5x + 6;
     * bad (coefficient overflow) -> exception.
     *
     * @param poly1
     *            the first polynomial
     * @param poly2
     *            the second polynomial
     * @return a new polynomial equal to poly1 * poly2
     * @throws IllegalArgumentException
     *             if either polynomial is null
     * @throws ArithmeticException
     *             if a coefficient overflows
     */
    public Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        // TODO: result length = a.length + b.length - 1.
        // Nested loop: result[i + j] += a[i] * b[j]
        // (degrees add when you multiply terms). Check for infinity.
        throw new UnsupportedOperationException(
            "TODO: PolynomialCalculator.multiply");
    }
}
