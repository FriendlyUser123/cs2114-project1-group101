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
        // TODO: validate FIRST (so a bad value never overwrites a good one),
        // then store it and mark that a result exists.
        throw new UnsupportedOperationException(
            "TODO: FourFunctionCalculator.setLast");
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
        // TODO: if nothing is stored, throw; otherwise return lastResult.
        throw new UnsupportedOperationException(
            "TODO: FourFunctionCalculator.getLast");
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
        // TODO: compute, check Double.isInfinite(result), store with setLast,
        // return. The same overflow check applies to all four operations, so
        // consider one private helper.
        throw new UnsupportedOperationException(
            "TODO: FourFunctionCalculator.add");
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
        // TODO: same pattern as add.
        throw new UnsupportedOperationException(
            "TODO: FourFunctionCalculator.subtract");
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
        // TODO: same pattern as add.
        throw new UnsupportedOperationException(
            "TODO: FourFunctionCalculator.multiply");
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
        // TODO: reject num2 == 0 BEFORE dividing. Doubles don't throw on
        // divide-by-zero, they quietly return Infinity, so this check is
        // required.
        throw new UnsupportedOperationException(
            "TODO: FourFunctionCalculator.divide");
    }
}
