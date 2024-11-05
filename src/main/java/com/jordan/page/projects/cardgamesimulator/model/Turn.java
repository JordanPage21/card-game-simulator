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
}
