package com.jordan.page.projects.cardgamesimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

    }

    @Test
    void testPlayCard_NoPriorityCheck() {

        turn = new Turn(false);

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

    }

    @Test
    void testPlayCard_PriorityCheckSuccess() {

        List<Player> players = new ArrayList<>(List.of(player1, player2, player3, player4));

        players.forEach(p -> {
            List<Card> hand = new ArrayList<>();
            for (int i = 1; i <= 13; i++) {
                Card card = new Card(i + 1, Suite.CLUB);
                hand.add(card);
            }
            p.setHand(hand);
        });

        turn = new Turn(true);

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
    }

    @Test
    void testPlayCard_PriorityCheckOffSuite_IncorrectCut() {

        List<Player> players = new ArrayList<>(List.of(player1, player2, player3, player4));

        IntStream.range(0, players.size())
                .forEach(idx -> {
                    List<Card> hand = new ArrayList<>();
                    for (int i = 1; i <= 13; i++) {
                        Card card = new Card(idx == 3 ? i + 13 : i + 1, idx == 3 ? Suite.SPADE : Suite.CLUB);
                        hand.add(card);
                    }
                    players.get(idx).setHand(hand);

                    // Make sure player 4 has one club that he has to play
                    player4.getHand().set(1, new Card(2, Suite.CLUB));
                });

        turn = new Turn(true);

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
        assertEquals(turn.getCurrentTurn().size(), 3);
        assertNull(turn.getCurrentTurn().get(player4Card));
    }

    @Test
    void testPlayCard_PriorityCheckOffSuite_SpadeCut() {

        List<Player> players = new ArrayList<>(List.of(player1, player2, player3, player4));

        IntStream.range(0, players.size())
                .forEach(idx -> {
                    List<Card> hand = new ArrayList<>();
                    for (int i = 1; i <= 13; i++) {
                        Card card = new Card(idx == 3 ? i + 13 : i + 1, idx == 3 ? Suite.SPADE : Suite.CLUB);
                        hand.add(card);
                    }
                    players.get(idx).setHand(hand);
                });

        turn = new Turn(true);

        Card player1Card = player1.getHand().get(0);
        Card player2Card = player2.getHand().get(2);
        Card player3Card = player3.getHand().get(4);
        Card player4Card = player4.getHand().get(6);

        turn.playCard(player1, 0);
        turn.playCard(player2, 2);
        turn.playCard(player3, 4);
        turn.playCard(player4, 6);

        System.out.println(player4Card.getValue());

        // assert the correct player and cards are mapped in the current turn
        assertEquals(player1, turn.getCurrentTurn().get(player1Card));
        assertEquals(player2, turn.getCurrentTurn().get(player2Card));
        assertEquals(player3, turn.getCurrentTurn().get(player3Card));
        assertEquals(player4, turn.getCurrentTurn().get(player4Card));

        turn.determineWinner();

        // make sure player 4 wins with spade cut that is correct
        assertEquals(turn.getCurrentWinner(), player4);
    }

    @Test
    void testPlayCard_PriorityCheckOffSuite_OffSuiteCut() {

        List<Player> players = new ArrayList<>(List.of(player1, player2, player3, player4));

        IntStream.range(0, players.size())
                .forEach(idx -> {
                    List<Card> hand = new ArrayList<>();
                    for (int i = 1; i <= 13; i++) {
                        Card card = new Card(i + 1, idx == 3 ? Suite.HEART : Suite.CLUB);
                        hand.add(card);
                    }
                    players.get(idx).setHand(hand);
                });

        turn = new Turn(true);

        Card player1Card = player1.getHand().get(0);
        Card player2Card = player2.getHand().get(2);
        Card player3Card = player3.getHand().get(8);
        Card player4Card = player4.getHand().get(10);

        turn.playCard(player1, 0);
        turn.playCard(player2, 2);
        turn.playCard(player3, 8);
        turn.playCard(player4, 10);

        // assert the correct player and cards are mapped in the current turn
        assertEquals(player1, turn.getCurrentTurn().get(player1Card));
        assertEquals(player2, turn.getCurrentTurn().get(player2Card));
        assertEquals(player3, turn.getCurrentTurn().get(player3Card));
        assertEquals(player4, turn.getCurrentTurn().get(player4Card));

        turn.determineWinner();

        // make sure player 4 wins with spade cut that is correct
        assertEquals(turn.getCurrentWinner(), player3);
    }

    @Test
    void testPlayCard_SetPriority() {

        turn = new Turn(true);

        Card player1Card = player1.getHand().get(0);

        turn.playCard(player1, 0);

        // assert the correct player and cards are mapped in the current turn
        assertEquals(player1, turn.getCurrentTurn().get(player1Card));

        // assert priority
        assertEquals(player1Card.getSuite(), turn.getPriority());
    }

    @Test
    void testCalculateWinner_FirstCardPlayer_NonSuitePriorityCheck() {

        turn = new Turn(true);
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

        turn = new Turn(true);

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
    void testCalculateWinner_MiddleCardPlayer_NormalSuitePlay() {

        turn = new Turn(true);

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
