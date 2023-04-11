package com.wag;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * This is a dummy starter class and the application class where the startup logic can be encoded
 */
@QuarkusMain
public class LiveCodingRunner {
    public static void main(String... args) {
        Quarkus.run(LiveCodingApp.class, args);
    }

    public static class LiveCodingApp implements QuarkusApplication {
        public int run(String... args) {
            Quarkus.waitForExit();
            return 0;
        }
    }
}
