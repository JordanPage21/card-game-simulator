package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private List<Card> hand;
    private int score;
    private String name; 

    public Player(String name) {    
         this.name = (name != null && !name.isEmpty()) ? name : "Unknown Player";
         this.score = 0;
         this.hand = new ArrayList<>();
    }

    public Player() {
        this("John Doe");
   }

    public List<Card> getHand() {
        return this.hand;
    }

    public void setHand(List<Card> hand) {
        if (hand == null) {
            throw new IllegalArgumentException("Hand cannot be null.");
        }
        this.hand = new ArrayList<>(hand);
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void decrementScore() {
        if (score > 0) {
            score--;
        }
    }

    public Card playCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException("Invalid card index: " + index);
        }
        return hand.remove(index);
    }

    public Card getCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException("Invalid card index: " + index);
        }
        return hand.get(index);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (Score: ").append(score).append(")\n");
        if (hand.isEmpty()) {
            sb.append("Hand is empty.");
        } else {
            int i = 1;
            for (Card card : hand) {
                sb.append(i).append(": ").append(card.getDisplayValue()).append("\n");
                i++;
            }
        }
        return sb.toString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
