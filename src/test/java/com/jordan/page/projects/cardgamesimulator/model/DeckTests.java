package com.jordan.page.projects.cardgamesimulator.model;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeckTests {

    @Autowired
    Deck deck;

    @BeforeEach
    void setup() {
        deck.reset();
    }

    @Test
    void testLoadTotalSize() {
        assertEquals(52, deck.getCards().size());
    }

    @Test
    void testLoadSpades() {
        List<Card> spades = deck.getCards().stream()
                .filter(card -> card.getSuite() == Suite.SPADE)
                .collect(Collectors.toList());

        assertEquals(15, spades.size());
        for (int i = 0; i < 15; i++) {
            assertEquals(15 + i, spades.get(i).getValue());
        }

    }

    @Test
    void testLoadHearts() {
        List<Card> hearts = deck.getCards().stream()
                .filter(card -> card.getSuite() == Suite.HEART)
                .collect(Collectors.toList());

        assertEquals(12, hearts.size());
        for (int i = 3; i <= 14; i++) {
            int value = i;
            assertTrue(hearts.stream().anyMatch(card -> card.getValue() == value));
        }
    }

    @Test
    void testLoadDiamonds() {
        List<Card> diamonds = deck.getCards().stream()
                .filter(card -> card.getSuite() == Suite.DIAMOND)
                .collect(Collectors.toList());

        assertEquals(12, diamonds.size());
        for (int i = 3; i <= 14; i++) {
            int value = i;
            assertTrue(diamonds.stream().anyMatch(card -> card.getValue() == value));
        }
    }

    @Test
    void testLoadClubs() {
        List<Card> clubs = deck.getCards().stream()
                .filter(card -> card.getSuite() == Suite.CLUB)
                .collect(Collectors.toList());

        assertEquals(13, clubs.size());
        for (int i = 2; i <= 14; i++) {
            int value = i;
            assertTrue(clubs.stream().anyMatch(card -> card.getValue() == value));
        }
    }

    @Test
    void testShuffle() {
        String before = deck.toString();
        deck.shuffle();
        String after = deck.toString();
        assertNotEquals(before, after);

        System.out.println(before);
        System.out.println("\n\n" + after);
    }

    @Test
    void testDeal() {
        Player one = new Player();
        Player two = new Player();
        Player three = new Player();
        Player four = new Player();

        deck.deal(one, two, three, four);

        assertEquals(13, one.getHand().size());
        assertEquals(13, two.getHand().size());
        assertEquals(13, three.getHand().size());
        assertEquals(13, four.getHand().size());

        List<Card> allDealtCards = new ArrayList<>();
        allDealtCards.addAll(one.getHand());
        allDealtCards.addAll(two.getHand());
        allDealtCards.addAll(three.getHand());
        allDealtCards.addAll(four.getHand());

        assertEquals(52, allDealtCards.size());
        assertTrue(allDealtCards.containsAll(deck.getCards()));
        assertTrue(deck.getCards().containsAll(allDealtCards));
    }

}
