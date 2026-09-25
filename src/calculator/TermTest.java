package calculator;

/**
 * Tests for {@link Term}. Cases come from the Deliverable 2 test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @author Ethan Gearhart (ethang06)
 * @author Nandini Duggaraju (nduggaraju)
 * @version 2026.09.25
 */
public class TermTest extends student.TestCase {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /** A term created fresh before every test. */
    private Term term;

    /**
     * Runs before every test.
     */
    public void setUp() {
        term = new Term(5.0, 3);
    }


    /**
     * Tests the Term constructor.
     */
    public void testTerm() {
        Term testTerm = new Term(-4.5, 2);

        assertEquals(-4.5, testTerm.getCoefficient(), DELTA);
        assertEquals(2, testTerm.getDegree());
    }


    /**
     * Tests setting a valid coefficient and rejecting invalid coefficients.
     */
    public void testSetCoefficient() {
        term.setCoefficient(-7.25);
        assertEquals(-7.25, term.getCoefficient(), DELTA);

        IllegalArgumentException nanException = null;

        try {
            term.setCoefficient(Double.NaN);
        }
        catch (IllegalArgumentException e) {
            nanException = e;
        }

        assertNotNull(nanException);
        assertEquals(-7.25, term.getCoefficient(), DELTA);

        IllegalArgumentException infinityException = null;

        try {
            term.setCoefficient(Double.POSITIVE_INFINITY);
        }
        catch (IllegalArgumentException e) {
            infinityException = e;
        }

        assertNotNull(infinityException);
        assertEquals(-7.25, term.getCoefficient(), DELTA);
    }


    /**
     * Tests setting valid and invalid degrees.
     */
    public void testSetDegree() {
        term.setDegree(4);
        assertEquals(4, term.getDegree());

        term.setDegree(0);
        assertEquals(0, term.getDegree());

        IllegalArgumentException exception = null;

        try {
            term.setDegree(-2);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(0, term.getDegree());
    }


    /**
     * Tests getting the coefficient.
     */
    public void testGetCoefficient() {
        assertEquals(5.0, term.getCoefficient(), DELTA);

        term.setCoefficient(-2.75);
        assertEquals(-2.75, term.getCoefficient(), DELTA);
    }


    /**
     * Tests getting the degree.
     */
    public void testGetDegree() {
        assertEquals(3, term.getDegree());

        term.setDegree(10);
        assertEquals(10, term.getDegree());
    }
}
