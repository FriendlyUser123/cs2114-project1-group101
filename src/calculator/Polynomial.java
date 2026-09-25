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
        Term newTerm = new Term(coefficient, degree);
        boolean degreeAlreadyExists = false;

        for (Term i : terms) {
            if (i.getDegree() == newTerm.getDegree()) {
                i.setCoefficient(i.getCoefficient() + newTerm.getCoefficient());
                degreeAlreadyExists = true;
                break;
            }
        }

        if (!degreeAlreadyExists) {
            terms.add(newTerm);
        }
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
        int maxDegree = 0;

        for (Term i : terms) {
            if (i.getDegree() >= maxDegree) {
                maxDegree = i.getDegree();
            }
        }

        double[] CoeffArray = new double[maxDegree + 1];

        for (int i = 0; i < CoeffArray.length; i++) {
            for (Term j : terms) {
                if (j.getDegree() == i) {
                    CoeffArray[i] += j.getCoefficient();
                }
            }
        }

        return CoeffArray;
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
        ArrayList<Term> tempArray = new ArrayList<Term>();

        if (polyArray == null) {
            throw new IllegalArgumentException("Array is null");
        }

        for (double i : polyArray) {
            if (Double.isNaN(i) || Double.isInfinite(i)) {
                throw new IllegalArgumentException(
                    "A coefficient is too large to calculate.");
            }
        }

        for (int i = 0; i < polyArray.length; i++) {
            if (polyArray[i] != 0.0) {
                tempArray.add(new Term(polyArray[i], i));
            }
        }

        this.terms = tempArray;
    }


    /**
     * Returns this polynomial as text, highest degree first, such as
     * "5x^3 - 2x + 7". The empty polynomial is "0".
     *
     * @return the formatted polynomial
     */
    @Override
    public String toString() {
        String polyString = "";
        double[] coeffArray = this.toCoefficientArray();
        boolean firstTerm = true;

        for (int i = coeffArray.length - 1; i >= 0; i--) {
            double coeff = coeffArray[i];

            if (coeff == 0.0) {
                continue;
            }
            if (!firstTerm) {
                if (coeff > 0) {
                    polyString += " + ";
                }
                else {
                    polyString += " - ";
                }
            }
            else if (coeff < 0) {
                polyString += "-";
            }

            double absCoeff = Math.abs(coeff);

            if (absCoeff != 1.0 || i == 0) {

                if (absCoeff == (long)absCoeff) {
                    polyString += Long.toString((long)absCoeff);
                }
                else {
                    polyString += Double.toString(absCoeff);
                }
            }

            if (i > 1) {
                polyString += "x^" + Integer.toString(i);
            }
            else if (i == 1) {
                polyString += "x";
            }

            firstTerm = false;
        }
        if (firstTerm) {
            return "0";
        }

        return polyString;
    }
}
