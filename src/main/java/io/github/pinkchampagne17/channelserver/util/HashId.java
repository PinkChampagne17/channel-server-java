package io.github.pinkchampagne17.channelserver.util;

import org.hashids.Hashids;

public class HashId {
    private static Hashids hashids = new Hashids("TheSaltOfChannelServer");

    public static String encodeOne(Long id) {
        return hashids.encode(id);
    }

    public static Long decodeOne(String hashId) {
        return hashids.decode(hashId)[0];
    }
}
