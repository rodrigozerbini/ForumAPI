package com.solera.forum.services;

import java.util.List;

public class BannedWords {

    private static List<String> bannedWords =
            List.of("f***", "d***", "p****",
                    "b****", "c***", "s***");

    public List<String> get() {
        return bannedWords;
    }
}
