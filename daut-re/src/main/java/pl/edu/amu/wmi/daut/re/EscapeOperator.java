package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 * 
 * Obsługa wyjątków
 */
class InvalidEscapeOperatorException extends RuntimeException {
    public InvalidEscapeOperatorException(String message) {
        super(message);
    }
}

/**
 *
 * Klasa EscapeOperator reprezentująca znak poprzedzony znakiem ucieczki
 * (w wyrażeniach regularnych POSIX - odwrócony ukośnik).
 */
public class EscapeOperator extends NullaryRegexpOperator {

    private char znak;

    @Override
    public AutomatonSpecification createFixedAutomaton() {
        switch (znak) {
            case 'n':
                return new NaiveAutomatonSpecification().makeOneTransitionAutomaton('\n');
            case 't':
                return new NaiveAutomatonSpecification().makeOneTransitionAutomaton('\t');
            case 'a':
                return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(('\7'));
            case 'f':
                return new NaiveAutomatonSpecification().makeOneTransitionAutomaton('\f');
            case 'r':
                return new NaiveAutomatonSpecification().makeOneTransitionAutomaton('\r');
            case 'v':
                return new NaiveAutomatonSpecification().makeOneTransitionAutomaton('\13');
            default:
                return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(znak);

        }

    }
    /**
     * Konstruktor klasy.
     * @param a znak poprzedzony symbolem ucieczki
     */
    public EscapeOperator(char a) {
        this.setChar(a);

    }

    private void setChar(char b) {
        this.znak = b;
    }
    /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            if (params.get(0).length() == 1)
                return new EscapeOperator(params.get(0).charAt(0));
            else 
                throw new InvalidEscapeOperatorException("Argument was supposed to be a char, not a string");
        }
    }
}

