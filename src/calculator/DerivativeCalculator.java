package calculator;

/**
 * Evaluates a polynomial and its derivative at a specific x value
 * 
 * @author
 * @version Sep 25, 2026
 */

public class DerivativeCalculator
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * Solves a polynomial at a given x value
     * 
     * @param poly
     *            the polynomial to solve
     * @param x
     *            the double value to solve at
     * @return double the solution
     * @throw IllegalArgumentException when the input is bad
     * @throw ArithmeticException when the polynomial couldn't evaluate
     */
    public double evaluatePolynomial(Polynomial poly, double x)
    {
        if (poly == null)
        {
            throw new IllegalArgumentException("Polynomial can't be null");
        }
        if (Double.isNaN(x) || Double.isInfinite(x))
        {
            throw new IllegalArgumentException("Invalid x value.");
        }

        double[] coefficients = poly.toCoefficientArray();
        double result = 0.0;

        for (int i = 0; i < coefficients.length; i++)
        {
            result += coefficients[i] * Math.pow(x, i);
        }

        if (Double.isNaN(result) || Double.isInfinite(result))
        {
            throw new ArithmeticException(
                "Evaluation resulted in an invalid value.");
        }
        return result;
    }


    /**
    Calculates the derivative of the polynomial using power rules

    @param poly the polynomial to solve

    @param x the double value to solve at

    @return double the solution

    @throw IllegalArgumentException when the input is bad
    @throw ArithmeticException when the polynomial couldn't evaluate
    */
    public double evaluateDerivative(Polynomial poly, double x)
    {
    if (poly == null)
    {
    throw new IllegalArgumentException(
    "Polynomial cannot be null.");
    }
    
    if (Double.isNaN(x) || Double.isInfinite(x))
    {
    throw new IllegalArgumentException(
    "Invalid x value.");
    }
    
    double[] coefficients = poly.toCoefficientArray();
    double result = 0.0;
    
    for (int i = 1; i < coefficients.length; i++)
    {
    result += i * coefficients[i];
    Math.pow(x, i - 1);
    }
    
    if (Double.isNaN(result) || Double.isInfinite(result))
    {
    throw new ArithmeticException(
    "Derivative resulted in an invalid value.");
    }
    return result;
    }

}
