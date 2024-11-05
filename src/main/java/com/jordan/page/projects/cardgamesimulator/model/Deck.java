package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

public class Deck {

    private final Map<Integer, String> cardMap;
    private List<Card> cards = new ArrayList<>();

    // Constructor to pass cardMap as a parameter
    public Deck(Map<Integer, String> cardMap) {
        this.cardMap = cardMap;
        load();
    }

    public void load() {

        if (!cards.isEmpty()) {
            return;
        }

        for (Suite suite : Suite.values()) {

            // spades have higher priority values
            if (suite.equals(Suite.SPADE)) {
                for (int value = 15; value <= 29; value++) {
                    cards.add(new Card(value, suite));
                }
            } else {
                for (int value = 3; value <= 14; value++) {
                    cards.add(new Card(value, suite));
                }
            }
        }
        // 2 of Clubs
        cards.add(new Card(2, Suite.CLUB));
    }

    public List<Card> getCards() {
        return cards;
    }

    public void reset() {
        cards.clear();
        load();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Card card : cards) {

            Suite suite = card.getSuite();

            if (suite.equals(Suite.SPADE)) {
                card.setValue(card.getValue() - 12);
            }

            sb.append(i + ": " + cardMap.get(card.getValue()) + " of " + card.getSuite()+"S\n");
            i++;
        }

        return sb.toString();
    }

}
