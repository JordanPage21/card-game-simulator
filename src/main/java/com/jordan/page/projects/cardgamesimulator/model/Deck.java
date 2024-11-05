package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.Collections;
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

    public void shuffle(){

        if (cards.isEmpty()) {
            return;
        }

        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void reset() {
        cards.clear();
        load();
    }

    public String toString() {

        if (cards.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Card card : cards) {

            Suite suite = card.getSuite();
            int value = card.getValue();

            if (suite.equals(Suite.SPADE)) {
               value = value - 12;
            }

            sb.append(i + ": " + cardMap.get(value) + " of " + card.getSuite()+"S\n");
            i++;
        }

        return sb.toString();
    }

    public void deal(Player one, Player two, Player three, Player four){

        if (cards.isEmpty()) {
            return;
        }

        List<Card> oneHand = new ArrayList<>();
        List<Card> twoHand = new ArrayList<>();
        List<Card> threeHand = new ArrayList<>();
        List<Card> fourHand = new ArrayList<>();
        
        List<List<Card>> hands = List.of(oneHand, twoHand, threeHand, fourHand);

        // Distribute cards to players
        for (int i = 0; i < cards.size(); i++) {
            hands.get(i % 4).add(cards.get(i));
        }

        one.setHand(oneHand);
        two.setHand(twoHand);
        three.setHand(threeHand);
        four.setHand(fourHand);
    }
}
