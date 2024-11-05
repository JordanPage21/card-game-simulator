package com.jordan.page.projects.cardgamesimulator.model;

import java.util.HashMap;
import java.util.Map;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

public class Card {
    
    private int value;
    private Suite suite;
    
    public Card(int value, Suite suite) {
        this.value = value;
        this.suite = suite;
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

}
