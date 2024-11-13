package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

public class Turn {
    private List<Map<Card, Player>> previousTurns = new ArrayList<>();
    private Map<Card, Player> currentTurn = new HashMap<>();
    private Player previousWinner;
    private Player currentWinner;
    private Suite priority;

    public void playCard(Player player, int cardIndex) {
        Card card = player.playCard(cardIndex);
        if (card != null) {
            currentTurn.put(card, player); // Store the card with the player

            // Set priority if this is the first crad played
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
        Map<Card, Player> previousTurnMap = new HashMap<>(currentTurn);
        previousTurns.add(previousTurnMap); // save the previous turn
        currentTurn.clear(); // Clear current turn for the next round
    }

    private Player calculateWinner() {

        if (currentTurn.size() != 4) {
            return null;
        }

        // initialize winningCard
        Card winningCard = new Card(0, Suite.CLUB);

        for (Entry<Card, Player> entry : currentTurn.entrySet()) {

            Card card = entry.getKey();
            Suite suite = card.getSuite();

            // If card is a priority suite or a spade and if the cards value is greater than
            // the current winning card.
            if ((suite == priority || suite == Suite.SPADE) && card.getValue() > winningCard.getValue()) {

                winningCard = card;

            }
        }

        return currentTurn.get(winningCard); // return the entry for the winning card
    }

    public List<Map<Card, Player>> getPreviousTurns() {
        return previousTurns;
    }

    public void setPreviousTurns(List<Map<Card, Player>> previousTurns) {
        this.previousTurns = previousTurns;
    }

    public Map<Card, Player> getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Map<Card, Player> currentTurn) {
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
