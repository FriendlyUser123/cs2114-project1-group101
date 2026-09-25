package calculator;

/**
 * One term of a polynomial: a coefficient multiplied by x raised to a
 * non-negative whole-number degree. For example, 5x^3 has coefficient 5.0 and
 * degree 3. A collection of Terms makes up a {@link Polynomial}.
 *
 * @author Aditya Banerjee (adityab7)
 * @author Ethan Gearhart (ethang06)
 * @author Nandini Duggaraju (nduggaraju)
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
        this.setCoefficient(coefficient);
        this.setDegree(degree);
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
        if (Double.isNaN(coefficient) || Double.isInfinite(coefficient)) {
            throw new IllegalArgumentException("Invalid entry, try again");
        }
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
        if (degree < 0) {
            throw new IllegalArgumentException("Degree must "
                + "not be negative");
        }
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
