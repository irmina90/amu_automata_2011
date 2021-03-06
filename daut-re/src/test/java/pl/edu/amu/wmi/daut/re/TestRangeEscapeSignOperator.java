package pl.edu.amu.wmi.daut.re;
import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import pl.edu.amu.wmi.daut.re.RangeEscapeSignOperator.Factory;

/**
 *
 * Test klasy RangeEscapeSignOperator.
 */
public class TestRangeEscapeSignOperator extends TestCase {
/**
 *
 * Test konstruktora TestRangeEscaoeSignOperator.
 */
     public void testRangeEscapeSignOperator() {

      RangeEscapeSignOperator operator = new RangeEscapeSignOperator("\"\"");
      RangeEscapeSignOperator operator2 = new RangeEscapeSignOperator("\'\'");
      assertNotNull(operator);
      assertNotNull(operator2);

    }

/**
 *
 * Test metody CreateFixedAutomaton.
 */
     public void testCreateFixedAutomaton() {

       RangeEscapeSignOperator operator = new RangeEscapeSignOperator("\"?a\"");
       AutomatonSpecification automaton = operator.createFixedAutomaton();
       NondeterministicAutomatonByThompsonApproach result =
        new NondeterministicAutomatonByThompsonApproach(automaton);

        assertFalse(result.accepts("\"?\""));
        assertFalse(result.accepts("\"*\""));
        assertFalse(result.accepts("\",\""));
        assertFalse(result.accepts("???"));
        assertFalse(result.accepts(" , , "));
        assertFalse(result.accepts("\"plik.???\""));
        assertFalse(result.accepts("ba"));
        assertTrue(result.accepts("\"?a\""));

       RangeEscapeSignOperator operator2 = new RangeEscapeSignOperator("\'*a\'");
       AutomatonSpecification automaton2 = operator2.createFixedAutomaton();
       NondeterministicAutomatonByThompsonApproach result2 =
        new NondeterministicAutomatonByThompsonApproach(automaton2);

        assertFalse(result2.accepts("cbacbacbaa"));
        assertTrue(result2.accepts("\'*a\'"));
        assertFalse(result2.accepts("\',\'"));
        assertFalse(result2.accepts("???"));
        assertFalse(result2.accepts(" , , "));

       RangeEscapeSignOperator operator3 = new RangeEscapeSignOperator("\'b,a\'");
       AutomatonSpecification automaton3 = operator3.createFixedAutomaton();
       NondeterministicAutomatonByThompsonApproach result3 =
        new NondeterministicAutomatonByThompsonApproach(automaton3);

        assertFalse(result3.accepts("b,a"));
        assertTrue(result3.accepts("\'b,a\'"));
        assertFalse(result3.accepts("\',\'"));
        assertFalse(result3.accepts("???"));
        assertFalse(result3.accepts(" , , "));
    }
 /**
 *
 * Test metody Factory.
 */
    public final void testFactory() {

        Factory factory = new Factory();
        ArrayList<String> params = new ArrayList<String>();
        params.add(0, "*");
        assertEquals(factory.createOperator(params).getClass(),
            new RangeEscapeSignOperator("*").getClass());

    }
}
