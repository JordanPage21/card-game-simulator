package com.jordan.page.projects.cardgamesimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

@SpringBootTest
class TurnTests {

    Player player1;
    Player player2;
    Player player3;
    Player player4;

    Deck deck;

    Turn turn;

    @BeforeEach
    void setup() {
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();

        deck = new Deck();
        deck.shuffle();
        deck.deal(player1, player2, player3, player4);

        turn = new Turn();
    }

    @Test
    void testPlayCard() {

        Card player1Card = player1.getHand().get(0);
        Card player2Card = player2.getHand().get(2);
        Card player3Card = player3.getHand().get(4);
        Card player4Card = player4.getHand().get(6);

        turn.playCard(player1, 0);
        turn.playCard(player2, 2);
        turn.playCard(player3, 4);
        turn.playCard(player4, 6);

        // assert the correct player and cards are mapped in the current turn
        assertEquals(player1, turn.getCurrentTurn().get(player1Card));
        assertEquals(player2, turn.getCurrentTurn().get(player2Card));
        assertEquals(player3, turn.getCurrentTurn().get(player3Card));
        assertEquals(player4, turn.getCurrentTurn().get(player4Card));

        // assert priority
        assertEquals(player1Card.getSuite(), turn.getPriority());
    }

    @Test
    void testCalculateWinner_FirstCardPlayer_NonSuiteCheck() {

        Card p1 = new Card(12, Suite.CLUB);
        Card p2 = new Card(2, Suite.CLUB);
        Card p3 = new Card(14, Suite.DIAMOND);
        Card p4 = new Card(14, Suite.HEART);

        Map<Card, Player> currentTurn = new HashMap<>(Map.of(p1, player1, p2, player2, p3, player3, p4, player4));
        turn.setCurrentTurn(currentTurn);
        turn.setPriority(Suite.CLUB);

        turn.determineWinner();

        assertEquals(player1, turn.getCurrentWinner());
        assertTrue(player1.getScore() > 0);
    }

    @Test
    void testCalculateWinner_LastCardPlayer_SpadeCut() {

        Card p1 = new Card(12, Suite.CLUB);
        Card p2 = new Card(2, Suite.CLUB);
        Card p3 = new Card(13, Suite.CLUB);
        Card p4 = new Card(15, Suite.SPADE);

        Map<Card, Player> currentTurn = new HashMap<>(Map.of(p1, player1, p2, player2, p3, player3, p4, player4));
        turn.setCurrentTurn(currentTurn);
        turn.setPriority(Suite.CLUB);

        turn.determineWinner();

        assertEquals(player4, turn.getCurrentWinner());
        assertTrue(player4.getScore() > 0);

    }

    @Test
    void testCalculateWinner_MiddleCardPlayer() {

        Card p1 = new Card(12, Suite.HEART);
        Card p2 = new Card(2, Suite.HEART);
        Card p3 = new Card(13, Suite.HEART);
        Card p4 = new Card(10, Suite.HEART);

        Map<Card, Player> currentTurn = new HashMap<>(Map.of(p1, player1, p2, player2, p3, player3, p4, player4));
        turn.setCurrentTurn(currentTurn);
        turn.setPriority(Suite.HEART);

        turn.determineWinner();

        assertEquals(player3, turn.getCurrentWinner());
        assertTrue(player3.getScore() > 0);
    }
}
