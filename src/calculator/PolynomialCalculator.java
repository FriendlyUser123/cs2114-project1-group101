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
     * Checks that the polynomials are not null
     *
     * @param check1
     *            the first polynomial
     *
     * @param check2
     *            the second polynomial
     *
     * @return boolean true if the polynomials are good, false if one is null
     */
    public boolean checkPolys(Polynomial check1, Polynomial check2) {
        if (check1 == null || check2 == null) {
            return false;
        }
        return true;
    }


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
        if (!this.checkPolys(poly1, poly2)) {
            throw new IllegalArgumentException("Polynomial is null.");
        }

        double[] polyArray1 = poly1.toCoefficientArray();
        double[] polyArray2 = poly2.toCoefficientArray();

        int resultLength = Math.max(polyArray1.length, polyArray2.length);
        double[] coeffResult = new double[resultLength];

        for (int i = 0; i < coeffResult.length; i++) {
            if (i < polyArray1.length) {
                coeffResult[i] += polyArray1[i];
            }
            if (i < polyArray2.length) {
                coeffResult[i] += polyArray2[i];
            }
        }

        Polynomial polyResult = new Polynomial();
        polyResult.fromCoefficientArray(coeffResult);
        return polyResult;

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
        if (!this.checkPolys(poly1, poly2)) {
            throw new IllegalArgumentException("Polynomial is null.");
        }

        double[] polyArray1 = poly1.toCoefficientArray();
        double[] polyArray2 = poly2.toCoefficientArray();

        int resultLength = Math.max(polyArray1.length, polyArray2.length);
        double[] coeffResult = new double[resultLength];

        for (int i = 0; i < coeffResult.length; i++) {
            if (i < polyArray1.length) {
                coeffResult[i] += polyArray1[i];
            }
            if (i < polyArray2.length) {
                coeffResult[i] -= polyArray2[i];
            }
        }

        Polynomial polyResult = new Polynomial();
        polyResult.fromCoefficientArray(coeffResult);
        return polyResult;
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
     */
    public Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        if (!this.checkPolys(poly1, poly2)) {
            throw new IllegalArgumentException("Polynomial is null.");
        }

        double[] polyArray1 = poly1.toCoefficientArray();
        double[] polyArray2 = poly2.toCoefficientArray();

        int resultLength = polyArray1.length + polyArray2.length - 1;
        double[] coeffResult = new double[resultLength];

        for (int i = 0; i < polyArray1.length; i++) {
            for (int j = 0; j < polyArray2.length; j++) {
                coeffResult[i + j] += polyArray1[i] * polyArray2[j];
            }
        }

        Polynomial polyResult = new Polynomial();
        polyResult.fromCoefficientArray(coeffResult);
        return polyResult;
    }
}
