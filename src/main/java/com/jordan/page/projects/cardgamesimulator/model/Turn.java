package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

public class Turn {
    private List<Map<Player,Card>> previousTurns = new ArrayList<>();
    private Map<Player, Card> currentTurn = new HashMap<>();
    private Player previousWinner;
    private Player currentWinner;
    private Suite priority;

    public Turn(){}

    public void playCard(Player player, int cardIndex) {
        Card card = player.playCard(cardIndex);
        if (card != null) {
            currentTurn.put(player, card); // Store the card with the player

            // Set priority if this is the first card played
            if (currentTurn.size() == 1) {
                priority = card.getSuite();
            }
        }
    }

    public void determineWinner() {
        currentWinner = calculateWinner();
        if (currentWinner != null) {
            currentWinner.incrementScore(); // Increment winner's score
        }
        Map<Player, Card> previousTurnMap = new HashMap<>(currentTurn); 
        previousTurns.add(previousTurnMap); //save the previous turn 
        currentTurn.clear(); // Clear current turn for the next round
    }

    private Player calculateWinner() {
        // Logic to determine the winning player based on the priority suite and card values
        return null; // Placeholder return
    }

    public List<Map<Player, Card>> getPreviousTurns() {
        return previousTurns;
    }

    public void setPreviousTurns(List<Map<Player, Card>> previousTurns) {
        this.previousTurns = previousTurns;
    }

    public Map<Player, Card> getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Map<Player, Card> currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Player getPreviousWinner() {
        return previousWinner;
    }

    public void setPreviousWinner(Player previousWinner) {
        this.previousWinner = previousWinner;
    }

    public Player getCurrentWinner() {
        return currentWinner;
    }

    public void setCurrentWinner(Player currentWinner) {
        this.currentWinner = currentWinner;
    }

    public Suite getPriority() {
        return priority;
    }

    public void setPriority(Suite priority) {
        this.priority = priority;
    }

    
}
