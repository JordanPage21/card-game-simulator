package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private List<Card> hand = new ArrayList<>();
    int score = 0;

    public Player() {}

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public Card playCard(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.remove(index); // Remove the card from the hand
        }
        return null; 
    }
    
}
