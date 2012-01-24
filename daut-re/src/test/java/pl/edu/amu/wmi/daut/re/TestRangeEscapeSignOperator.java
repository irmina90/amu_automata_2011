package pl.edu.amu.wmi.daut.re;
import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import junit.framework.TestCase;

/**
 *
 * @author Irminka
 */
public class TestRangeEscapeSignOperator extends TestCase {

       public void testRangeEscapeSignOperator() {

        RangeEscapeSignOperator operator = new RangeEscapeSignOperator("\"\"");
        RangeEscapeSignOperator operator2 = new RangeEscapeSignOperator("''");
        assertNotNull(operator);
        assertNotNull(operator2);

    }

     public void TestCreateFixedAutomaton() {

         RangeEscapeSignOperator operator = new RangeEscapeSignOperator("\"\"");
         AutomatonSpecification automaton = operator.createFixedAutomaton();
         NondeterministicAutomatonByThompsonApproach result =
          new NondeterministicAutomatonByThompsonApproach(automaton);

        assertTrue(result.accepts("?"));
        assertTrue(result.accepts("*"));
        assertTrue(result.accepts(","));
        assertTrue(result.accepts("???"));
        assertTrue(result.accepts(" , , "));
        assertTrue(result.accepts("plik.???"));

         RangeEscapeSignOperator operator2 = new RangeEscapeSignOperator("\'\'");
         AutomatonSpecification automaton2 = operator2.createFixedAutomaton();
         NondeterministicAutomatonByThompsonApproach result2 =
          new NondeterministicAutomatonByThompsonApproach(automaton2);

        assertTrue(result2.accepts("?"));
        assertTrue(result2.accepts("*"));
        assertTrue(result2.accepts(","));
        assertTrue(result2.accepts("???"));
        assertTrue(result2.accepts(" , , "));

    }

       public final void testFactory() {

        RegexpOperatorFactory factory = new RangeEscapeSignOperator.Factory();
        ArrayList<String> params = new ArrayList<String>();
        assertEquals(1, factory.arity());
        assertEquals(factory.createOperator(params).getClass(),
            new RangeEscapeSignOperator(params.get(0)));

    }

}
