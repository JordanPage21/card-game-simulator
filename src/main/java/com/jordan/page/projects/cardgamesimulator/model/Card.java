package com.jordan.page.projects.cardgamesimulator.model;

import java.util.Map;

import com.jordan.page.projects.cardgamesimulator.config.CardMap;
import com.jordan.page.projects.cardgamesimulator.enums.Suite;

public class Card {

    private final int value;
    private final Suite suite;
    private static final Map<Integer, String> cardMap = CardMap.getInstance();

    public Card(int value, Suite suite) {
        this.value = value;
        this.suite = suite;
    }

    public int getValue() {
        return value;
    }

    public Suite getSuite() {
        return suite;
    }

    public String getDisplayValue() {
        int adjustedValue = (suite == Suite.SPADE) ? value - 12 : value;
        return cardMap.get(adjustedValue) + " of " + suite.name() + "S";
    }

}
