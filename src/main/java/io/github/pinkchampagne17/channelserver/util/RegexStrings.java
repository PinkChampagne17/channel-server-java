package io.github.pinkchampagne17.channelserver.util;

public class RegexStrings {
    // Printable ASCII, length between 6 and 32
    public static final String PASSWORD = "^[\\x20-\\x7E]{6,32}$";

    // Alphabet and number, length between 1 and 32
    public static final String USERNAME = "^[A-Za-z0-9]{1,32}$";
}
