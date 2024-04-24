package language.api;

import language.en.Hello;
import language.fr.Bonjour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GreetingTest {

    @Test
    void bonjourShouldSayBonjour() {
        Bonjour bonjour = new Bonjour();
        assertTrue(bonjour.getGreeting().startsWith("Bonjour"));
    }

    @Test
    void helloShouldSayHello() {
        Hello hello = new Hello();
        assertTrue(hello.getGreeting().startsWith("Hello"));
    }
}
