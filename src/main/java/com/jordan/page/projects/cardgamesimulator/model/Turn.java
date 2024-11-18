package com.jordan.page.projects.cardgamesimulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Turn {
    private List<Map<Card, Player>> previousTurns = new ArrayList<>();
    private Map<Card, Player> currentTurn = new HashMap<>();
    private Player previousWinner;
    private Player currentWinner;
    private Suite priority;
    private boolean priorityCheck;

    public Turn(boolean priorityCheck) {

        // if not the first turn, store the current turn in previous turns and clear.
        if (!currentTurn.isEmpty()) {
            Map<Card, Player> previousTurnMap = new HashMap<>(currentTurn);
            previousTurns.add(previousTurnMap);
            currentTurn.clear();
        }

        // if the current winner exists, assign to previous winner
        if (currentWinner != null) {
            previousWinner = currentWinner;
        }
        this.priorityCheck = priorityCheck;
    }

    public void playCard(Player player, int cardIndex) {

        if (currentTurn.size() >= 4) {
            throw new IllegalArgumentException("Maximum players have already played this turn.");
        }
        Card card = player.getCard(cardIndex);
        if (card != null) {

            // Set priority if this is the first card played
            if (currentTurn.size() == 0 && priorityCheck) {
                log.info("Priority set to {}", card.getSuite());
                priority = card.getSuite();

                // card is not priority, make sure it is a valid cut
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

            log.info("Player " + player.getName() + " successfully played card. {}", card.getDisplayValue());

        }
    }

    public void determineWinner() {

        currentWinner = calculateWinner();
        if (currentWinner != null) {
            currentWinner.incrementScore(); // Increment winner's score
        }

    }

    private Player calculateWinner() {

        // initialize winningCard
        Card winningCard = new Card(0, Suite.CLUB);

        for (Entry<Card, Player> entry : currentTurn.entrySet()) {

            Card card = entry.getKey();
            Suite suite = card.getSuite();

            // Check if the card is eligible to become the new winning card
            boolean isEligibleWinningCard = card.getValue() > winningCard.getValue() &&
                    ((!priorityCheck) || (suite == priority || suite == Suite.SPADE));

            if (isEligibleWinningCard) {
                winningCard = card;
            }
        }
        Player winner = currentTurn.get(winningCard);
        log.info("\n{}\nWinner: {} with the {}", this, winner.getName(), winningCard.getDisplayValue());
        return winner; // return the entry for the winning card
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


    public void setPriority(Suite priority) {
        this.priority = priority;
    }

    public Suite getPriority() {
        return priority;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder("Current Turn:\n");
        currentTurn.forEach((card, player) -> sb.append(player.getName())
                .append(" played ")
                .append(card.getDisplayValue())
                .append("\n"));
        return sb.toString();
    }

}
