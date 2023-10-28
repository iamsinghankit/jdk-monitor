package com.singhankit.jdk.core;

import static com.singhankit.jdk.core.Color.RED;
import static com.singhankit.jdk.core.Color.RESET;
import static java.lang.System.out;

/**
 * @author Ankit Singh
 */
public class Log {

    public static void info(String message) {
        out.println(message);
    }

    public static void error(String message) {
        out.println(RED + message + RESET);
    }
}
