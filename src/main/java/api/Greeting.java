package api;

import en.Hello;
import fr.Bonjour;

public interface Greeting {

    enum Language  {
        French, English
    }

    String getGreeting();

    static Greeting byLanguage(Language language) {
        return switch (language) {
            case French -> new Bonjour();
            case English -> new Hello();
        };
    }
}
