package com.solera.forum.services;

import java.util.List;

public class BannedWords {

    private static List<String> bannedWords =
            List.of("fuck", "fucker", "dick", "pussy", "motherfucker", "anal", "ass",
                    "bitch", "cunt", "shit", "whore", "vagina");

    public List<String> get() {
        return bannedWords;
    }
}