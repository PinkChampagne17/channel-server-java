package io.github.pinkchampagne17.channelserver.util;
import java.time.LocalDateTime;

public class ISO8601 {
    public static String of (LocalDateTime localDateTime) {
        return localDateTime.toString() + "Z";
    }
}
