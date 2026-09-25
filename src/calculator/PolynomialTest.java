package calculator;

/**
 * Tests for {@link Polynomial}. Cases come from the Deliverable 2 test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @author Ethan Gearhart (ethang06)
 * @author Nandini Duggaraju (nduggaraju)
 * @version 2026.09.25
 */
public class PolynomialTest extends student.TestCase {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /** An empty polynomial created fresh before every test. */
    private Polynomial poly;

    /**
     * Runs before every test.
     */
    public void setUp() {
        poly = new Polynomial();
    }


    /**
     * Tests the Polynomial constructor.
     */
    public void testPolynomial() {
        double[] result = poly.toCoefficientArray();

        assertEquals(1, result.length);
        assertEquals(0.0, result[0], DELTA);
        assertEquals("0", poly.toString());
    }


    /**
     * Tests adding normal terms, adding different degrees,
     * combining duplicate degrees, and rejecting an invalid degree.
     */
    public void testAddTerm() {
        poly.addTerm(5.0, 3);
        poly.addTerm(-2.0, 1);
        poly.addTerm(7.0, 0);

        double[] result = poly.toCoefficientArray();

        assertEquals(4, result.length);
        assertEquals(7.0, result[0], DELTA);
        assertEquals(-2.0, result[1], DELTA);
        assertEquals(0.0, result[2], DELTA);
        assertEquals(5.0, result[3], DELTA);

        // Same degree should combine instead of creating another term
        poly.addTerm(3.0, 3);

        result = poly.toCoefficientArray();

        assertEquals(8.0, result[3], DELTA);

        // Negative degree cannot normally reach addTerm through the terminal,
        // so call it directly to cover its exception path.
        IllegalArgumentException exception = null;

        try {
            poly.addTerm(4.0, -1);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests conversion from a polynomial to a coefficient array.
     */
    public void testToCoefficientArray() {
        poly.addTerm(5.0, 3);
        poly.addTerm(-2.0, 1);
        poly.addTerm(7.0, 0);

        double[] result = poly.toCoefficientArray();

        assertEquals(4, result.length);
        assertEquals(7.0, result[0], DELTA);
        assertEquals(-2.0, result[1], DELTA);
        assertEquals(0.0, result[2], DELTA);
        assertEquals(5.0, result[3], DELTA);

        // Also covers the empty/zero polynomial case
        Polynomial empty = new Polynomial();
        double[] zero = empty.toCoefficientArray();

        assertEquals(1, zero.length);
        assertEquals(0.0, zero[0], DELTA);
    }


    /**
     * Tests rebuilding a polynomial from a coefficient array,
     * including zero entries and invalid arrays.
     */
    public void testFromCoefficientArray() {
        double[] input = { 7.0, -2.0, 0.0, 5.0 };

        poly.fromCoefficientArray(input);

        double[] result = poly.toCoefficientArray();

        assertEquals(4, result.length);
        assertEquals(7.0, result[0], DELTA);
        assertEquals(-2.0, result[1], DELTA);
        assertEquals(0.0, result[2], DELTA);
        assertEquals(5.0, result[3], DELTA);

        // Verify that calling the method again overwrites the old polynomial
        double[] replacement = { 4.0, 3.0 };
        poly.fromCoefficientArray(replacement);

        result = poly.toCoefficientArray();

        assertEquals(2, result.length);
        assertEquals(4.0, result[0], DELTA);
        assertEquals(3.0, result[1], DELTA);

        // Null cannot occur through the terminal, but this branch exists
        IllegalArgumentException nullException = null;

        try {
            poly.fromCoefficientArray(null);
        }
        catch (IllegalArgumentException e) {
            nullException = e;
        }

        assertNotNull(nullException);

        // NaN cannot get through terminal validation either,
        // but is required to cover this validation branch.
        IllegalArgumentException nanException = null;

        try {
            poly.fromCoefficientArray(new double[] { 1.0, Double.NaN });
        }
        catch (IllegalArgumentException e) {
            nanException = e;
        }

        assertNotNull(nanException);

        // Infinity reaches the other side of the OR condition.
        IllegalArgumentException infinityException = null;

        try {
            poly.fromCoefficientArray(new double[] { 1.0,
                Double.POSITIVE_INFINITY });
        }
        catch (IllegalArgumentException e) {
            infinityException = e;
        }

        assertNotNull(infinityException);
    }


    /**
     * Tests all major polynomial formatting cases.
     */
    public void testToString() {
        // Covers:
        // positive first term
        // skipped zero degree
        // later negative term
        // later positive term
        // degree > 1
        // degree == 1
        // degree == 0
        poly.addTerm(5.0, 3);
        poly.addTerm(-2.0, 1);
        poly.addTerm(7.0, 0);

        assertEquals("5x^3 - 2x + 7", poly.toString());

        // Negative first term
        Polynomial negativeFirst = new Polynomial();
        negativeFirst.addTerm(-5.0, 2);
        negativeFirst.addTerm(2.0, 0);

        assertEquals("-5x^2 + 2", negativeFirst.toString());

        // Coefficients of +1 and -1 should not print the 1
        Polynomial unitCoefficients = new Polynomial();
        unitCoefficients.addTerm(1.0, 2);
        unitCoefficients.addTerm(-1.0, 1);

        assertEquals("x^2 - x", unitCoefficients.toString());

        // Decimal coefficient reaches the non-integer formatting path
        Polynomial decimal = new Polynomial();
        decimal.addTerm(2.5, 2);

        assertEquals("2.5x^2", decimal.toString());

        // Constant coefficient of 1 must still print as 1
        Polynomial constantOne = new Polynomial();
        constantOne.addTerm(1.0, 0);

        assertEquals("1", constantOne.toString());

        // Complete zero polynomial
        Polynomial zero = new Polynomial();

        assertEquals("0", zero.toString());
    }
}
