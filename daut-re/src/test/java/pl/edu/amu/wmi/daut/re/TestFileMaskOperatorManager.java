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

        List<String> anyString = new ArrayList<String>();
        anyString.add("");
        anyString.add("*");
        assertEquals(anyString, manager.getSeparators("*"));

        List<String> anyChar = new ArrayList<String>();
        anyChar.add("?");
        assertEquals(anyChar, manager.getSeparators("?"));

        List<String> doNothing = new ArrayList<String>();
        doNothing.add("{");
        doNothing.add("}");
        assertEquals(doNothing, manager.getSeparators("{}"));

        List<String> alternative = new ArrayList<String>();
        alternative.add("");
        alternative.add(",");
        alternative.add("");
        assertEquals(alternative, manager.getSeparators(","));

        List<String> rangeEscapeSign = new ArrayList<String>();
        rangeEscapeSign.add("\"");
        rangeEscapeSign.add("\"");
        assertEquals(rangeEscapeSign, manager.getSeparators("\"\""));

    }

    /**
* Test metody getOperatorsForStringPrefix.
*/
    public final void testGetOperatorsForStringPrefix() {

        RegexpOperatorManager manager = new FileMaskOperatorManager();

        List<String> string = new ArrayList<String>();

        string = manager.getOperatorsForStringPrefix("*");
        assertEquals(Arrays.<String>asList("*", ","), string);

        string = manager.getOperatorsForStringPrefix("?");
        assertEquals(Arrays.<String>asList("?"), string);

        string = manager.getOperatorsForStringPrefix("{}");
        assertEquals(Arrays.<String>asList("{}"), string);

        string = manager.getOperatorsForStringPrefix(",");
        assertEquals(Arrays.<String>asList("*", ","), string);

        string = manager.getOperatorsForStringPrefix("");
        assertEquals(Arrays.<String>asList("*", ","), string);

    }
}
