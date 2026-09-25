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
        boolean menuLoop = true;
        while (menuLoop)
        {
            System.out.println("Welcome to myFieldCalc! Press 1 for 4-function"
                + " calculator, 2 for polynomial calculator, and 3 to exit: ");
            int menuChoice = 0;
            
            try
            {
                menuChoice = this.parseMenuChoice(scanner.nextLine());
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Try again");
            }
            
            if (menuChoice == 1)
            {
                System.out.println("Starting 4-Function Calculator...");
                this.runFourFunctionCalculator();
                System.out.println("Operation complete, you are being "
                    + "directed back to the main menu...");
            }
            else if (menuChoice == 2)
            {
                System.out.println("Starting Polynomial Calculator...");
                this.runPolynomialCalculator();
                System.out.println("Operation complete, you are being "
                    + "directed back to the main menu...");
            }
            else if (menuChoice == 3)
            {
                System.out.println("Goodbye!");
                scanner.close();
                menuLoop = false;
            }
        }
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
        double lastResult = 0.0;
        boolean calcRunning = true;
        fourFunctionCalc = new FourFunctionCalculator();
        while (calcRunning)
        {
            boolean gettingValue1 = true;
            boolean gettingValue2 = true;
            boolean gettingOperator = true;
            
            double value1 = 0.0;
            double value2 = 0.0;
            char operator = ' ';
            double result = 0.0;
            
            while (gettingValue1)
            {
                System.out.println("Enter value 1 (Enter ANS for "
                    + "last result, Enter EXIT to go back to main menu): ");
                
                if (!scanner.hasNextLine())
                {
                    return lastResult;
                }
                
                String in = scanner.nextLine();
                if (in.equalsIgnoreCase("EXIT"))
                {
                    calcRunning = false;
                    break;
                }
                try
                {
                    value1 = this.parseBasicValue(in);
                    gettingValue1 = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Try again");
                }
            }
            if (!calcRunning)
            {
                break;
            }
            while (gettingOperator)
            {
                System.out.println("Enter operator: ");
                try
                {
                    operator = this.parseBasicOperator(scanner.nextLine());
                    gettingOperator = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Try again");
                }
                
            }
            while (gettingValue2)
            {
                System.out.println("Enter value 2 (Enter ANS for "
                    + "last result): ");
                try
                {
                    value2 = this.parseBasicValue(scanner.nextLine());
                    gettingValue2 = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Try again");
                }
                
            }
            
            if (operator == '+')
            {
                try
                {
                    result = fourFunctionCalc.add(value1, value2);
                }
                catch (Exception e)
                {
                    System.out.println("try again");
                    continue;
                }
            }
            else if (operator == '-')
            {
                try
                {
                    result = fourFunctionCalc.subtract(value1, value2);
                }
                catch (Exception e)
                {
                    System.out.println("try again");
                    continue;
                }
            }
            else if (operator == '*')
            {
                try
                {
                    result = fourFunctionCalc.multiply(value1, value2);
                }
                catch (Exception e)
                {
                    System.out.println("try again");
                    continue;
                }
            }
            else if (operator == '/')
            {
                try
                {
                    result = fourFunctionCalc.divide(value1, value2);
                }
                catch (Exception e)
                {
                    System.out.println("try again");
                    continue;
                }
            }
            
            System.out.println("Result: " + Double.toString(result));
            lastResult = result;
            
        }
        
        return lastResult;
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
        polyCalc = new PolynomialCalculator();
        Polynomial result = new Polynomial();
        boolean operating = true;
        
        while (operating)
        {
            boolean gettingOperator = true;
            char operator = ' ';
            
            
            System.out.println("Follow the steps for polynomial 1: ");
            Polynomial poly1 = getPolynomialFromUser();
            System.out.println("Polynomial1: " + poly1.toString());
            System.out.println("Follow the steps for polynomial 2: ");
            Polynomial poly2 = getPolynomialFromUser();
            System.out.println("Polynomial2: " + poly2.toString());
            
            while (gettingOperator)
            {
                System.out.println("Enter polynomial operation: ");
                try
                {
                    operator = this.parsePolynomialOperator(scanner.nextLine());
                    gettingOperator = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Try again");
                }   
            }
            
            if (operator == '+')
            {
                try
                {
                    result = polyCalc.add(poly1, poly2);
                }
                catch (Exception e)
                {
                    System.out.println("try again");
                    continue;
                }
            }
            else if (operator == '-')
            {
                try
                {
                    result = polyCalc.subtract(poly1, poly2);
                }
                catch (Exception e)
                {
                    System.out.println("try again");
                    continue;
                }
            }
            else if (operator == '*')
            {
                try
                {
                    result = polyCalc.multiply(poly1, poly2);
                }
                catch (Exception e)
                {
                    System.out.println("try again");
                    continue;
                }
            }
            
            operating = false;
        }
        System.out.println("The resulting polynomial is: " + result.toString());
        return result;
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
        boolean gettingPoly = true;
        Polynomial userPoly = new Polynomial();
        while (gettingPoly)
        {
            boolean gettingDegree = true;
            int degree = 0;
            
            while (gettingDegree)
            {
                System.out.println("Enter the order of polynomial: ");
                try
                {
                    degree = this.parseDegree(scanner.nextLine());
                    gettingDegree = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("try again");
                }
            }
            
            for (int i = degree; i >= 0; i--)
            {
                boolean gettingTerm = true;
                double coeff = 0.0;
                while (gettingTerm)
                {
                    System.out.println("Enter the coefficient of term with "
                        + "degree " + Integer.toString(i) + ": ");
                    
                    String in = scanner.nextLine();
                    
                    if (in.isEmpty())
                    {
                        coeff = 0.0;
                        gettingTerm = false;
                    }
                    else
                    {
                        try
                        {
                            coeff = parseValidDouble(in);
                            gettingTerm = false;
                        }
                        catch (IllegalArgumentException e)
                        {
                            System.out.println("try again");
                        }
                    }
                }
                userPoly.addTerm(coeff, i);
            }
            
            gettingPoly = false;
        }
        return userPoly;
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
        int degree;
        try
        {
            degree = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Entry is invalid, try again");
        }
        if (degree < 0 || degree > 100)
        {
            throw new IllegalArgumentException("Degree is out of bounds");
        }
        
        return degree;
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
        double validDouble;
        if (input.length() > 100)
        {
            throw new IllegalArgumentException("Over character limit");
        }
        try
        {
            validDouble = Double.parseDouble(input);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Entry is invalid, try again");
        }
        if (Double.isNaN(validDouble) || Double.isInfinite(validDouble) )
        {
            throw new IllegalArgumentException("Entry is invalid, try again");
        }
        
        return validDouble;
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
        if (!(input.equals("+") || input.equals("-") || input.equals("*") 
            || input.equals("/")))
        {
            throw new IllegalArgumentException("invalid operator input");
        }
        
        char operator = input.charAt(0);
        return operator;
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
        if (input.equalsIgnoreCase("ANS"))
        {
            if (fourFunctionCalc.hasLast())
            {
                return fourFunctionCalc.getLast();
            }
            throw new IllegalArgumentException("No previous answer");
        }
        
        return this.parseValidDouble(input);
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
        if (!(input.equals("+") || input.equals("-") || input.equals("*")))
        {
            throw new IllegalArgumentException("invalid operator input");
        }
        
        char operator = input.charAt(0);
        return operator;
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
        if (!(input.equals("1") || input.equals("2") || input.equals("3")))
        {
            throw new IllegalArgumentException("invalid menu choice input");
        }
        
        int choice = Integer.parseInt(input);
        return choice;
    }
}
