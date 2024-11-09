package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jordan.page.projects.cardgamesimulator.config.CardMap;
import com.jordan.page.projects.cardgamesimulator.enums.Suite;

public class Player {
    
    private Map<Integer, String> cardMap;
    private List<Card> hand;
    int score;

    public Player() {
         this.score = 0;
         this.hand = new ArrayList<>();
         cardMap = CardMap.getInstance();
    }

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

     public String toString() {

        if (hand.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Card card : hand) {

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

    
}
