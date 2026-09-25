package calculator;

/**
 * Tests for {@link PolynomialCalculator}. Cases come from the Deliverable 2
 * test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class PolynomialCalculatorTest extends student.TestCase {
    private static final double DELTA = 0.0001;
    private PolynomialCalculator calc;
    private Polynomial poly1;
    private Polynomial poly2;

    /**
     * Sets up normal calculator and polynomial objects.
     */
    public void setUp() {
        calc = new PolynomialCalculator();

        poly1 = new Polynomial();
        poly2 = new Polynomial();
    }


    /**
     * Tests the constructor.
     */
    public void testPolynomialCalculator() {
        PolynomialCalculator testCalc = new PolynomialCalculator();

        assertNotNull(testCalc);
    }


    /**
     * Tests checking valid and null polynomials.
     */
    public void testCheckPolys() {
        assertTrue(calc.checkPolys(poly1, poly2));

        // First polynomial null
        assertFalse(calc.checkPolys(null, poly2));

        // Second polynomial null
        assertFalse(calc.checkPolys(poly1, null));

        // Both null
        assertFalse(calc.checkPolys(null, null));
    }


    /**
     * Tests polynomial addition.
     */
    public void testAdd() {
        // poly1 = 3x^2 + 2x
        poly1.addTerm(3.0, 2);
        poly1.addTerm(2.0, 1);

        // poly2 = 2x^2 + 1
        poly2.addTerm(2.0, 2);
        poly2.addTerm(1.0, 0);

        Polynomial result = calc.add(poly1, poly2);

        double[] array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(1.0, array[0], DELTA);
        assertEquals(2.0, array[1], DELTA);
        assertEquals(5.0, array[2], DELTA);

        /*
         * Different-length polynomials cover the branches where one coefficient
         * array runs out before the other.
         */

        Polynomial shortPoly = new Polynomial();
        shortPoly.addTerm(2.0, 0);

        Polynomial longPoly = new Polynomial();
        longPoly.addTerm(1.0, 2);
        longPoly.addTerm(3.0, 0);

        // First polynomial shorter
        result = calc.add(shortPoly, longPoly);
        array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(5.0, array[0], DELTA);
        assertEquals(0.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        // Second polynomial shorter
        result = calc.add(longPoly, shortPoly);
        array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(5.0, array[0], DELTA);
        assertEquals(0.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        // Null polynomial cannot come from terminal input,
        // but is necessary to cover the validation branch.
        IllegalArgumentException exception = null;

        try {
            calc.add(null, poly2);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests polynomial subtraction.
     */
    public void testSubtract() {
        // poly1 = 5x^2 + 3x
        poly1.addTerm(5.0, 2);
        poly1.addTerm(3.0, 1);

        // poly2 = 2x^2 + x
        poly2.addTerm(2.0, 2);
        poly2.addTerm(1.0, 1);

        Polynomial result = calc.subtract(poly1, poly2);

        double[] array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(0.0, array[0], DELTA);
        assertEquals(2.0, array[1], DELTA);
        assertEquals(3.0, array[2], DELTA);

        /*
         * Unequal lengths cover both conditional branches inside the
         * subtraction loop.
         */

        Polynomial shortPoly = new Polynomial();
        shortPoly.addTerm(2.0, 0);

        Polynomial longPoly = new Polynomial();
        longPoly.addTerm(1.0, 2);
        longPoly.addTerm(3.0, 0);

        // First polynomial shorter:
        // 2 - (x^2 + 3) = -x^2 - 1
        result = calc.subtract(shortPoly, longPoly);
        array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(-1.0, array[0], DELTA);
        assertEquals(0.0, array[1], DELTA);
        assertEquals(-1.0, array[2], DELTA);

        // Second polynomial shorter:
        // (x^2 + 3) - 2 = x^2 + 1
        result = calc.subtract(longPoly, shortPoly);
        array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(1.0, array[0], DELTA);
        assertEquals(0.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        IllegalArgumentException exception = null;

        try {
            calc.subtract(poly1, null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests polynomial multiplication.
     */
    public void testMultiply() {
        // poly1 = x + 2
        poly1.addTerm(1.0, 1);
        poly1.addTerm(2.0, 0);

        // poly2 = x + 3
        poly2.addTerm(1.0, 1);
        poly2.addTerm(3.0, 0);

        Polynomial result = calc.multiply(poly1, poly2);

        // Expected: x^2 + 5x + 6
        double[] array = result.toCoefficientArray();

        assertEquals(3, array.length);
        assertEquals(6.0, array[0], DELTA);
        assertEquals(5.0, array[1], DELTA);
        assertEquals(1.0, array[2], DELTA);

        // Also test multiplication by the zero polynomial
        Polynomial zero = new Polynomial();

        result = calc.multiply(poly1, zero);
        array = result.toCoefficientArray();

        assertEquals(1, array.length);
        assertEquals(0.0, array[0], DELTA);

        IllegalArgumentException exception = null;

        try {
            calc.multiply(null, poly2);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }
}
