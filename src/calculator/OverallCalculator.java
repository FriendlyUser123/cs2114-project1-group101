package calculator;

import java.util.Scanner;

/**
 * The entry point of the program. Shows a menu, reads the user's input with a
 * Scanner, and sends it to either the {@link FourFunctionCalculator} or the
 * {@link PolynomialCalculator}. All bad-input checking happens in the parse
 * methods, which throw IllegalArgumentException so the run methods can show an
 * error and re-prompt.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class OverallCalculator {

    // ~ Fields ................................................................

    /** Handles +, -, *, / on decimal numbers and remembers the last answer. */
    private FourFunctionCalculator fourFunctionCalc;

    /** Handles +, -, * on polynomials. */
    private PolynomialCalculator polyCalc;

    /** Reads everything the user types. */
    private Scanner scanner;

    // ~ Constructors ..........................................................

    /**
     * Creates a calculator that reads from the keyboard.
     */
    public OverallCalculator() {
        this(new Scanner(System.in));
    }


    /**
     * Creates a calculator that reads from the given Scanner. Tests use this
     * to feed in pretend user input, for example
     * new Scanner("5\n+\n8\n").
     *
     * @param scanner
     *            where user input comes from
     */
    public OverallCalculator(Scanner scanner) {
        this.fourFunctionCalc = new FourFunctionCalculator();
        this.polyCalc = new PolynomialCalculator();
        this.scanner = scanner;
    }

    // ~ Program entry point ...................................................

    /**
     * Starts the calculator.
     *
     * @param args
     *            not used
     */
    public static void main(String[] args) {
        new OverallCalculator().run();
    }

    // ~ Menu flow .............................................................

    /**
     * Runs the main menu loop: 1 = four-function, 2 = polynomial, 3 = exit.
     * Bad menu input shows an error and asks again.
     */
    public void run() {
        // TODO: loop until the user picks 3.
        // print menu -> read a line -> parseMenuChoice in a try/catch ->
        // call runFourFunctionCalculator / runPolynomialCalculator, print the
        // result -> on IllegalArgumentException print the message and loop.
        throw new UnsupportedOperationException("TODO: OverallCalculator.run");
    }


    /**
     * Walks the user through one four-function calculation, such as 5 + 8.
     * Bad input shows an error and asks for that value again.
     *
     * Spec test plan: normal 5 + 8 -> 13; bad (invalid value) -> error and
     * re-prompt.
     *
     * @return the answer
     */
    public double runFourFunctionCalculator() {
        // TODO: read value 1 (parseBasicValue, so "ANS" works), operator
        // (parseBasicOperator), value 2, then call the matching method on
        // fourFunctionCalc. Re-prompt each piece on IllegalArgumentException.
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.runFourFunctionCalculator");
    }


    /**
     * Reads two polynomials and an operator from the user and returns the
     * result.
     *
     * Spec test plan: normal two valid polynomials and + -> their sum;
     * bad (invalid polynomial or operator) -> error and re-prompt.
     *
     * @return the resulting polynomial
     */
    public Polynomial runPolynomialCalculator() {
        // TODO: p1 = getPolynomialFromUser(); read operator with
        // parsePolynomialOperator; p2 = getPolynomialFromUser();
        // call polyCalc.add / subtract / multiply.
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.runPolynomialCalculator");
    }


    /**
     * Builds one polynomial from user input, one term at a time: the degree,
     * then the coefficient for each term.
     *
     * Spec test plan: normal valid degrees/coefficients -> the Polynomial;
     * bad (invalid degree or coefficient) -> error and re-prompt.
     *
     * Per the spec, a blank coefficient counts as 0.
     *
     * @return the polynomial the user entered
     */
    public Polynomial getPolynomialFromUser() {
        // TODO: ask for the highest degree (parseDegree), then for each degree
        // from highest down to 0 ask for a coefficient (parseValidDouble;
        // a blank line means 0) and call polynomial.addTerm(coef, degree).
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.getPolynomialFromUser");
    }

    // ~ Input validation (parse methods) ......................................

    /**
     * Turns text into a polynomial degree.
     *
     * Spec test plan: normal "5" -> 5; bad "-2" or "3.5" -> exception.
     *
     * @param input
     *            the text the user typed
     * @return the degree
     * @throws IllegalArgumentException
     *             if the text is not a whole number from 0 to a sensible limit
     */
    public int parseDegree(String input) {
        // TODO: trim; Integer.parseInt inside try/catch (NumberFormatException
        // -> IllegalArgumentException); reject negatives and anything above
        // your max degree (pick a limit, e.g. 20, and state it in the README).
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.parseDegree");
    }


    /**
     * Turns text into a finite decimal number.
     *
     * Spec test plan: normal "5.25" -> 5.25; bad "2..5" -> exception.
     *
     * @param input
     *            the text the user typed
     * @return the number
     * @throws IllegalArgumentException
     *             if the text is blank, not a number, or infinite/NaN
     */
    public double parseValidDouble(String input) {
        // TODO: null/blank check; Double.parseDouble in try/catch; reject
        // NaN and Infinity (parseDouble accepts the strings "NaN" and
        // "Infinity"!). Scope doc also limits input to 20 characters.
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.parseValidDouble");
    }


    /**
     * Turns text into one of the four basic operators.
     *
     * Spec test plan: normal "+" -> '+'; bad "^" -> exception.
     *
     * @param input
     *            the text the user typed
     * @return '+', '-', '*', or '/'
     * @throws IllegalArgumentException
     *             if the text is not exactly one of those operators
     */
    public char parseBasicOperator(String input) {
        // TODO: trim; must be exactly one character and one of "+-*/".
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.parseBasicOperator");
    }


    /**
     * Turns text into a number, where "ANS" means the last answer.
     *
     * Spec test plan: normal "ANS" with previous result 8 -> 8;
     * bad "ANS" with no previous result -> exception.
     *
     * @param input
     *            the text the user typed
     * @return the number, or the last answer if the input was "ANS"
     * @throws IllegalArgumentException
     *             if the text is invalid, or is "ANS" with nothing stored
     */
    public double parseBasicValue(String input) {
        // TODO: if input equalsIgnoreCase("ANS"): check fourFunctionCalc
        // .hasLast() and return getLast(); otherwise parseValidDouble(input).
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.parseBasicValue");
    }


    /**
     * Turns text into a polynomial operator. Division is not supported for
     * polynomials.
     *
     * Spec test plan: normal "*" -> '*'; bad "/" -> exception.
     *
     * @param input
     *            the text the user typed
     * @return '+', '-', or '*'
     * @throws IllegalArgumentException
     *             if the text is not one of those operators
     */
    public char parsePolynomialOperator(String input) {
        // TODO: same as parseBasicOperator but only "+-*".
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.parsePolynomialOperator");
    }


    /**
     * Turns text into a menu choice.
     *
     * Spec test plan: normal "2" -> 2; bad "4" or "abc" -> exception.
     *
     * @param input
     *            the text the user typed
     * @return 1 (four-function), 2 (polynomial), or 3 (exit)
     * @throws IllegalArgumentException
     *             if the text is not 1, 2, or 3
     */
    public int parseMenuChoice(String input) {
        // TODO: trim; Integer.parseInt in try/catch; must be 1, 2, or 3.
        throw new UnsupportedOperationException(
            "TODO: OverallCalculator.parseMenuChoice");
    }
}
