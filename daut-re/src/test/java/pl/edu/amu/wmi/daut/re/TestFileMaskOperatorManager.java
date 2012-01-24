package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Irminka
 */
public class TestFileMaskOperatorManager extends TestCase {

        /**
     * Test metody GetFactory.
     */
    public final void testGetFactory() {

        RegexpOperatorManager manager = new FileMaskOperatorManager();

        assertNotNull(manager);
        assertNotNull(manager.getFactory("*"));
        assertNotNull(manager.getFactory("?"));
        assertNotNull(manager.getFactory("{}"));
        assertNotNull(manager.getFactory(","));
        assertNotNull(manager.getFactory("\"\""));
        assertNotNull(manager.getFactory("\'\'"));
    }

    /**
     * Test metody addOperator z priorytetami.
     */
    public final void testAddOperatorWithPiority() {

        RegexpOperatorManager manager = new FileMaskOperatorManager();

        assertEquals(3, manager.getPriority("*"));
        assertEquals(3, manager.getPriority("?"));
        assertEquals(5, manager.getPriority("{}"));
        assertEquals(1, manager.getPriority(","));
        assertEquals(5, manager.getPriority("\"\""));
        assertEquals(5, manager.getPriority("\'\'"));
    }

    /**
     * Test metody getSeparators.
     */
    public final void testGetSeparators() {

        RegexpOperatorManager manager = new FileMaskOperatorManager();

        List<String> AnyString = new ArrayList<String>();
        AnyString.add("");
        AnyString.add("*");
        assertEquals(AnyString, manager.getSeparators("*"));

        List<String> AnyChar = new ArrayList<String>();
        AnyChar.add("?");
        assertEquals(AnyChar, manager.getSeparators("?"));

        List<String> DoNothing = new ArrayList<String>();
        DoNothing.add("{");
        DoNothing.add("}");
        assertEquals(DoNothing, manager.getSeparators("{}"));

        List<String> Alternative = new ArrayList<String>();
        Alternative.add("");
        Alternative.add(",");
        Alternative.add("");
        assertEquals(Alternative, manager.getSeparators(","));

        List<String> RangeEscapeSign = new ArrayList<String>();
        RangeEscapeSign.add("\"");
        RangeEscapeSign.add("\"");
        RangeEscapeSign.add("\'");
        RangeEscapeSign.add("\'");
        assertEquals(RangeEscapeSign, manager.getSeparators("\"\"\'\'"));

    }

    /**
     * Test metody getOperatorsForStringPrefix.
     */
    public final void testGetOperatorsForStringPrefix() {

        RegexpOperatorManager manager = new FileMaskOperatorManager();

        List<String> string = new ArrayList<String>();

        string = manager.getOperatorsForStringPrefix("*");
        assertEquals(Arrays.<String>asList("*", "*"), string);

        string = manager.getOperatorsForStringPrefix("?");
        assertEquals(Arrays.<String>asList("?", "?"), string);

        string = manager.getOperatorsForStringPrefix("{}");
        assertEquals(Arrays.<String>asList("{}"), string);

        string = manager.getOperatorsForStringPrefix(",");
        assertEquals(Arrays.<String>asList("*", "{}", ",", ".", "?"), string);

        string = manager.getOperatorsForStringPrefix("");
        assertEquals(Arrays.<String>asList("*", "", ","), string);

    }
}

