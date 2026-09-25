package calculator;

import java.util.ArrayList;

/**
 * A polynomial stored as a list of {@link Term} objects, such as
 * 5x^3 - 2x + 7. It can also be converted to and from a coefficient array,
 * where the index is the degree, which makes the math in
 * {@link PolynomialCalculator} easier.
 *
 * An ArrayList was chosen because the number of terms changes and new terms
 * are easy to add.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class Polynomial {

    // ~ Fields ................................................................

    /** The terms of this polynomial. Each degree should appear at most once. */
    private ArrayList<Term> terms;

    // ~ Constructors ..........................................................

    /**
     * Creates an empty polynomial (the zero polynomial).
     */
    public Polynomial() {
        terms = new ArrayList<Term>();
    }

    // ~ Public Methods ........................................................

    /**
     * Adds a term to this polynomial. If a term with the same degree already
     * exists, the coefficients are combined instead of adding a duplicate.
     *
     * Spec test plan: normal 5, 3 -> adds 5x^3; bad degree -1 -> exception.
     *
     * @param coefficient
     *            the term's coefficient
     * @param degree
     *            the term's degree; must be 0 or greater
     * @throws IllegalArgumentException
     *             if the degree is negative or the coefficient is invalid
     */
    public void addTerm(double coefficient, int degree) {
        // TODO: 1) validate (let the Term constructor do it)
        // 2) loop over terms; if a Term has the same degree, add to its
        //    coefficient and return
        // 3) otherwise add a new Term
        throw new UnsupportedOperationException("TODO: Polynomial.addTerm");
    }


    /**
     * Returns this polynomial as an array of coefficients where the index is
     * the degree. For 5x^3 - 2x + 7 this is [7.0, -2.0, 0.0, 5.0].
     *
     * Spec test plan: bad (empty polynomial) -> the zero-polynomial
     * representation.
     *
     * @return the coefficient array
     */
    public double[] toCoefficientArray() {
        // TODO: 1) find the highest degree among the terms
        // 2) make a double[] of length (highest degree + 1)
        // 3) for each Term, put its coefficient at index = its degree
        // Decide what an empty polynomial returns (e.g. new double[] {0.0}).
        throw new UnsupportedOperationException(
            "TODO: Polynomial.toCoefficientArray");
    }


    /**
     * Replaces this polynomial's terms using a coefficient array where the
     * index is the degree. [7, -2, 0, 5] becomes 5x^3 - 2x + 7.
     *
     * Spec test plan: bad (array contains NaN) -> exception.
     *
     * @param polyArray
     *            the coefficients, indexed by degree
     * @throws IllegalArgumentException
     *             if the array is null or contains NaN
     */
    public void fromCoefficientArray(double[] polyArray) {
        // TODO: 1) validate the array
        // 2) clear the current terms
        // 3) for each index with a non-zero coefficient, add a Term
        throw new UnsupportedOperationException(
            "TODO: Polynomial.fromCoefficientArray");
    }


    /**
     * Returns this polynomial as text, highest degree first, such as
     * "5x^3 - 2x + 7". The empty polynomial is "0".
     *
     * @return the formatted polynomial
     */
    @Override
    public String toString() {
        // TODO: build the string from the highest degree down.
        // Things to decide: how x^1 and x^0 print, how negative coefficients
        // turn into " - ", and whether 1.0 prints as "1" or "1.0".
        return "TODO: Polynomial.toString";
    }
}
