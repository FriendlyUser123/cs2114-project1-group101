package calculator;

/**
 * One term of a polynomial: a coefficient multiplied by x raised to a
 * non-negative whole-number degree. For example, 5x^3 has coefficient 5.0 and
 * degree 3. A collection of Terms makes up a {@link Polynomial}.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class Term {

    // ~ Fields ................................................................

    /** The number multiplying x^degree. */
    private double coefficient;

    /** The power x is raised to. Must be 0 or greater. */
    private int degree;

    // ~ Constructors ..........................................................

    /**
     * Creates a term such as 5x^3.
     *
     * @param coefficient
     *            the number multiplying x^degree
     * @param degree
     *            the power x is raised to; must be 0 or greater
     * @throws IllegalArgumentException
     *             if the degree is negative or the coefficient is NaN or
     *             infinite
     */
    public Term(double coefficient, int degree) {
        // TODO: route through the setters so the validation lives in one place.
        this.coefficient = coefficient;
        this.degree = degree;
    }

    // ~ Public Methods ........................................................

    /**
     * Sets this term's coefficient.
     *
     * Spec test plan: normal -4.5 -> coefficient becomes -4.5;
     * bad NaN -> exception.
     *
     * @param coefficient
     *            the new coefficient
     * @throws IllegalArgumentException
     *             if the coefficient is NaN or infinite
     */
    public void setCoefficient(double coefficient) {
        // TODO: reject Double.isNaN(...) / Double.isInfinite(...) before
        // assigning.
        this.coefficient = coefficient;
    }


    /**
     * Sets this term's degree.
     *
     * Spec test plan: normal 4 -> degree becomes 4; bad -2 -> exception.
     *
     * @param degree
     *            the new degree; must be 0 or greater
     * @throws IllegalArgumentException
     *             if the degree is negative
     */
    public void setDegree(int degree) {
        // TODO: reject degree < 0 before assigning.
        this.degree = degree;
    }


    /**
     * Returns this term's coefficient.
     *
     * @return the coefficient
     */
    public double getCoefficient() {
        return coefficient;
    }


    /**
     * Returns this term's degree.
     *
     * @return the degree
     */
    public int getDegree() {
        return degree;
    }
}
