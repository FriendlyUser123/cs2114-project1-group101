package calculator;

/**
 * A basic four-function calculator (+, -, *, /) on decimal numbers, with a
 * one-slot memory that remembers the last answer so the user can type "ANS".
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class FourFunctionCalculator {

    // ~ Fields ................................................................

    /** The answer to the most recent operation. */
    private double lastResult;

    /**
     * Whether lastResult holds a real answer yet. Needed because 0.0 is a
     * valid answer, so lastResult alone can't tell "no answer yet" apart from
     * "the answer was 0".
     */
    private boolean hasLastResult;

    // ~ Constructors ..........................................................

    /**
     * Creates a calculator with an empty memory.
     */
    public FourFunctionCalculator() {
        lastResult = 0.0;
        hasLastResult = false;
    }

    // ~ Public Methods ........................................................

    /**
     * Returns whether a previous answer is stored.
     *
     * Spec test plan: normal (previous result exists) -> true;
     * (no previous result) -> false.
     *
     * @return true if there is a last answer to recall
     */
    public boolean hasLast() {
        return hasLastResult;
    }


    /**
     * Stores an answer in memory.
     *
     * Spec test plan: normal 8.5 -> stores 8.5; bad Infinity -> exception
     * and the previous result is preserved.
     *
     * @param last
     *            the answer to remember
     * @throws IllegalArgumentException
     *             if last is NaN or infinite
     */
    public void setLast(double last) {
        if (Double.isNaN(last) || Double.isInfinite(last))
        {
            throw new IllegalArgumentException("Invalid entry, try again");
        }
        this.lastResult = last;
    }


    /**
     * Returns the stored answer.
     *
     * Spec test plan: normal (last result 8.5) -> 8.5;
     * bad (nothing stored) -> exception.
     *
     * @return the last answer
     * @throws IllegalStateException
     *             if no answer has been stored yet
     */
    public double getLast() {
        if (!hasLastResult)
        {
            throw new IllegalStateException("There is no last entry");
        }
        return lastResult;
    }
    
    /**
     * Checks whether a set of doubles are valid
     * 
     * @param check1 the first number
     * 
     * @param check2 the second number
     * 
     * @throws IllegalArgumentException if bad input
     * 
     * If either are invalid an exception is thrown
     */
    public void checkDouble(double check1, double check2)
    {
        if (Double.isNaN(check1) || Double.isInfinite(check1) || 
            Double.isNaN(check2) || Double.isInfinite(check2))
        {
            throw new IllegalArgumentException("Invalid entry, try again");
        }
    }


    /**
     * Adds two numbers.
     *
     * Spec test plan: normal 5, 8 -> 13; bad (result overflows) -> exception.
     *
     * @param num1
     *            the first number
     * @param num2
     *            the second number
     * @return num1 + num2
     * @throws ArithmeticException
     *             if the result overflows to infinity
     */
    public double add(double num1, double num2) {
        
        this.checkDouble(num1, num2);
        
        double result = num1 + num2;
        
        if (Double.isNaN(result) || Double.isInfinite(result))
            {
                throw new ArithmeticException("Operation reuslts "
                    + "in infinite value");
            }
        
        this.hasLastResult = true;
        this.setLast(result);
        return result;
        
    }


    /**
     * Subtracts the second number from the first.
     *
     * Spec test plan: normal 10, 4 -> 6; bad (result overflows) -> exception.
     *
     * @param num1
     *            the number to subtract from
     * @param num2
     *            the number to subtract
     * @return num1 - num2
     * @throws ArithmeticException
     *             if the result overflows to infinity
     */
    public double subtract(double num1, double num2) {
        this.checkDouble(num1, num2);
        double result = num1 - num2;
        if (Double.isNaN(result) || Double.isInfinite(result))
        {
            throw new ArithmeticException("Operation reuslts "
                + "in infinite value");
        }
        this.hasLastResult = true;
        this.setLast(result);
        return result;
    }


    /**
     * Multiplies two numbers.
     *
     * Spec test plan: normal 4, 5 -> 20; bad (result overflows) -> exception.
     *
     * @param num1
     *            the first number
     * @param num2
     *            the second number
     * @return num1 * num2
     * @throws ArithmeticException
     *             if the result overflows to infinity
     */
    public double multiply(double num1, double num2) {
        this.checkDouble(num1, num2);
        double result = num1 * num2;
        if (Double.isNaN(result) || Double.isInfinite(result))
        {
            throw new ArithmeticException("Operation reuslts "
                + "in infinite value");
        }
        this.hasLastResult = true;
        this.setLast(result);
        return result;
    }


    /**
     * Divides the first number by the second.
     *
     * Spec test plan: normal 10, 2 -> 5; bad 10, 0 -> exception.
     *
     * @param num1
     *            the dividend
     * @param num2
     *            the divisor; must not be 0
     * @return num1 / num2
     * @throws IllegalArgumentException
     *             if num2 is 0
     */
    public double divide(double num1, double num2) {
        this.checkDouble(num1, num2);
        if (num2 == 0)
        {
            throw new IllegalArgumentException("Divide by 0");
        }
        double result = num1 / num2;
        if (Double.isNaN(result) || Double.isInfinite(result))
        {
            throw new IllegalArgumentException("Operation reuslts "
                + "in infinite value");
        }
        this.hasLastResult = true;
        this.setLast(result);
        return result;
    }
}
