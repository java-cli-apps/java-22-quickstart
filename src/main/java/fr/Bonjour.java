package fr;

import api.Greeting;

public class Bonjour implements Greeting {

    @Override
    public String getGreeting() {
        return "Bonjour " + Character.toString(0x1F1EB) + Character.toString(0x1F1F7);
    }
}
