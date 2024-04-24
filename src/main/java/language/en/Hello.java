package language.en;

import language.api.Greeting;

public class Hello implements Greeting {

    @Override
    public String getGreeting() {
        return "Hello " + Character.toString(0x1F1EC) + Character.toString(0x1F1E7);
    }
}
