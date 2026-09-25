package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Term}. Cases come from the Deliverable 2 test plan.
 *
 * @author Aditya Banerjee (adityab7)
 * @version 2026.09.25
 */
public class TermTest {

    /** Tolerance for comparing doubles. */
    private static final double DELTA = 1e-9;

    /** A term created fresh before every test. */
    private Term term;

    /**
     * Runs before every test.
     */
    @Before
    public void setUp() {
        term = new Term(5.0, 3);
    }


    /** Normal: new Term(5, 3) is 5x^3. */
    @Test
    public void testConstructor() {
        fail("TODO: assert getCoefficient() == 5.0 and getDegree() == 3");
    }


    /** Bad: degree -1 throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeDegree() {
        fail("TODO: new Term(5.0, -1)");
    }


    /** Normal: setCoefficient(-4.5) makes the coefficient -4.5. */
    @Test
    public void testSetCoefficient() {
        fail("TODO: term.setCoefficient(-4.5); assert -4.5");
    }


    /** Bad: setCoefficient(NaN) throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testSetCoefficientNaN() {
        fail("TODO: term.setCoefficient(Double.NaN)");
    }


    /** Normal: setDegree(4) makes the degree 4. */
    @Test
    public void testSetDegree() {
        fail("TODO: term.setDegree(4); assert 4");
    }


    /** Bad: setDegree(-2) throws. */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDegreeNegative() {
        fail("TODO: term.setDegree(-2)");
    }


    /** Normal: a coefficient of 7.5 is returned as 7.5. */
    @Test
    public void testGetCoefficient() {
        fail("TODO: new Term(7.5, 1).getCoefficient() == 7.5");
    }


    /** Normal: a degree of 4 is returned as 4. */
    @Test
    public void testGetDegree() {
        fail("TODO: new Term(1.0, 4).getDegree() == 4");
    }
}
