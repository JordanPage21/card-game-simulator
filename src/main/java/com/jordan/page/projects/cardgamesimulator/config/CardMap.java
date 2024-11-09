package com.jordan.page.projects.cardgamesimulator.config;

import java.util.HashMap;
import java.util.Map;

public class CardMap {
    private static final Map<Integer, String> cardMap = new HashMap<>();

    // Static block to initialize the map
    static {
        cardMap.put(2, "2");
        cardMap.put(3,"3");
        cardMap.put(4,"4");
        cardMap.put(5,"5");
        cardMap.put(6,"6");
        cardMap.put(7,"7");
        cardMap.put(8,"8");
        cardMap.put(9,"9");
        cardMap.put(10,"10");
        cardMap.put(11,"Jack");
        cardMap.put(12,"Queen");
        cardMap.put(13,"King");
        cardMap.put(14,"Ace");
        cardMap.put(15,"Deuce");
        cardMap.put(16,"Little Joker");
        cardMap.put(17,"Big Joker");
    }

    // Private constructor to prevent instantiation
    private CardMap() {}

    // Public method to access the singleton map
    public static Map<Integer, String> getInstance() {
        return cardMap;
    }
}
