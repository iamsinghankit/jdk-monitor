package com.singhankit.jdk.core;

/**
 * @author Ankit Singh
 */
public class Color {
    private static final String E = "\u001B[";

    public static final String RESET = E + "0m";
    public static final String BLACK = E + "30m";
    public static final String RED = E + "31m";
    public static final String GREEN = E + "32m";
    public static final String YELLOW = E + "33m";
    public static final String BLUE = E + "34m";
    public static final String PURPLE = E + "35m";
    public static final String CYAN = E + "36m";
    public static final String WHITE = E + "37m";
    public static final String WHITE_BOLD = E + "37;1m";
    public static final String BOLD = E + "1m";
    public static final String WHITE_BACKGROUND = E + "107m";
}
