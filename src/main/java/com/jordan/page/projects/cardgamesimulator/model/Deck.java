package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Deck {

    private List<Card> cards;

    public Deck() {
        load();
    }

    public void load() {

        cards = new ArrayList<>();

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
            log.warn("Deck is empty, reloading...");
            load();
        }

        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void reset() {
        cards.clear();
        load();
    }

    public String toString() {

        if (cards.isEmpty()) {
            return "Deck is empty";
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Card card : cards) {
            sb.append(i).append(": ").append(card.getDisplayValue()).append("\n");
            i++;
        }

        return sb.toString();
    }

    public void deal(List<Player> players) {
        
        if (cards.isEmpty()) {
            log.warn("Deck is empty, loading and shuffling deck...");
            load();
            shuffle();
        }
    
        int playerCount = players.size();
        if (playerCount == 0) {
            throw new IllegalArgumentException("No players to deal to!");
        }
    
        List<ArrayList<Card>> hands = players.stream()
                                        .map(player -> new ArrayList<Card>())
                                        .toList();
    
        for (int i = 0; i < cards.size(); i++) {
            hands.get(i % playerCount).add(cards.get(i));
        }
    
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setHand(hands.get(i));
        }
    }
}
