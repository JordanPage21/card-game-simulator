package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Turn {
    private List<Map<Card, Player>> previousTurns;
    private Map<Card, Player> currentTurn;
    private Player previousWinner;
    private Player currentWinner;
    private Suite priority;
    private boolean priorityCheck;

    public Turn(boolean priorityCheck) {
        previousTurns = new ArrayList<>();
        currentTurn = new HashMap<>();
        this.priorityCheck = priorityCheck;
    }

    public void playCard(Player player, int cardIndex) {

        if (currentTurn.size() > 3) {
            log.error("Each player has already made a move this turn. {}", currentTurn.toString());
        }

        Card card = player.getCard(cardIndex);

        if (card != null) {

            // Set priority if this is the first card played
            if (currentTurn.size() == 0 && priorityCheck) {

                log.info("Priority set to {}", card.getSuite());
                priority = card.getSuite();

                // if the card is not the priority then we need to check if the player's hand
                // contains a priority suite card
            } else if (card.getSuite() != priority && priorityCheck) {

                log.info("Cut attempted, determining if a valid cut.");
                boolean containsPriorityCard = player.getHand().stream().anyMatch(c -> c.getSuite() == priority);

                if (containsPriorityCard) {
                    log.error(
                            "Player attempted to play a card that is not the priority, but they contains a priority suite card! Attempt to play another card.");

                    return;
                }

            }

            // if here, play card as normal
            player.playCard(cardIndex);
            currentTurn.put(card, player);

            log.info("Player successfully played card. {}", card.toString());

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
            if ((suite == priority || suite == Suite.SPADE) && card.getValue() > winningCard.getValue()
                    && priorityCheck) {

                winningCard = card;

            }

            // non priority case
            else if (card.getValue() > winningCard.getValue() && !priorityCheck) {
                winningCard = card;
            }
        }

        log.info("Out of current turn \n{}\nThe winning card is {}", toString(), winningCard);
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

    public String toString(){

        StringBuilder sb = new StringBuilder();

        for(Card card: currentTurn.keySet()){
            sb.append(card.toString());
        }

        return sb.toString();
    }

}
