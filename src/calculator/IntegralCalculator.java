package calculator;

/**
 * Solves integrals numerically with the power rule and Simpson's rule.
 * 
 * @author
 * @version Sep 25, 2026
 */

public class IntegralCalculator
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
    Approximates the definite integral of a polynomial
    between two x values using Simpson's Rule.

    @param poly polynomial to integrate
    @param lowerBound lower x bound
    @param upperBound upper x bound
    @param intervals number of subintervals
    @return approximate definite integral
    */
    public double simpsonsRule(Polynomial poly, double lowerBound,
    double upperBound, int intervals)
    {
        if (poly == null)
        {
            throw new IllegalArgumentException("Polynomial cannot be null");
        }
        if (Double.isNaN(lowerBound) || Double.isInfinite(lowerBound)
        || Double.isNaN(upperBound) || Double.isInfinite(upperBound))
        {
            throw new IllegalArgumentException("Bounds must be finite");
        }
        if (intervals <= 0 || intervals % 2 != 0)
        {
            throw new IllegalArgumentException(
            "Number of intervals must be positive and even");
        }
        double h = (upperBound - lowerBound) / intervals;
        double result = evaluatePolynomial(poly, lowerBound) + evaluatePolynomial(poly, upperBound);
        for (int i = 1; i < intervals; i++)
        {
            double x = lowerBound + i * h;
            double y = evaluatePolynomial(poly, x);
            if (i % 2 == 0)
            {
                result += 2.0 * y;
            }
            else
            {
                result += 4.0 * y;
            }
        }
        result *= h / 3.0;
        if (Double.isNaN(result) || Double.isInfinite(result))
        {
            throw new ArithmeticException(
            "Integral resulted in an invalid value.");
        }
        return result;
    }
    
    /**
    Evaluates the polynomial at a given x value.

    @param poly the polynomial to evaluate

    @param x the x value to evaluate at
    */
    public double evaluatePolynomial(Polynomial poly, double x)
    {
        double[] coefficients = poly.toCoefficientArray();
        double result = 0.0;
        for (int i = 0; i < coefficients.length; i++)
        {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

}
