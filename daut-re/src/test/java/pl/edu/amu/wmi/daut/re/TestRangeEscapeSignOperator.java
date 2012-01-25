package pl.edu.amu.wmi.daut.re;
import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
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
public void testRangeEscapeSignOperatorA() {
        RangeEscapeSignOperator operator = new RangeEscapeSignOperator("\"\"");
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        assertFalse(automaton.isEmpty());
        assertTrue(result.accepts("?"));
        assertTrue(result.accepts("*"));
        assertTrue(result.accepts(","));
        assertTrue(result.accepts("???"));
        assertTrue(result.accepts(" , , "));
        assertTrue(result.accepts("plik.???"));
        assertTrue(result.accepts("*.txt???"));
}



		public void testRangeEscapeSignOperatorQ() {
        RangeEscapeSignOperator operator2 = new RangeEscapeSignOperator("\'\'");
        AutomatonSpecification automaton2 = operator2.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result2 =
                new NondeterministicAutomatonByThompsonApproach(automaton2);

        assertFalse(automaton2.isEmpty());
        assertTrue(result2.accepts("'?'"));
        assertTrue(result2.accepts("'*'"));
        assertTrue(result2.accepts("','"));
        assertTrue(result2.accepts("???"));
        assertTrue(result2.accepts("' , , '"));
        assertTrue(result2.accepts("'plik.???'"));
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
