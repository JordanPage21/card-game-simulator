package com.jordan.page.projects.cardgamesimulator.model;

import java.util.Map;

import com.jordan.page.projects.cardgamesimulator.config.CardMap;
import com.jordan.page.projects.cardgamesimulator.enums.Suite;

public class Card {

    private int value;
    private Suite suite;
    private Map<Integer, String> cardMap;

    public Card(int value, Suite suite) {
        this.value = value;
        this.suite = suite;
        cardMap = CardMap.getInstance();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public String toString() {

        if (suite.equals(Suite.SPADE)) {
            value = value - 12;
        }

        return cardMap.get(value) + " of " + this.suite.name() + "S\n";
    }

}
