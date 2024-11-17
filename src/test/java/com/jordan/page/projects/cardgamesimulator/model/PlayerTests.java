package com.jordan.page.projects.cardgamesimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;

class PlayerTests {

    Player player;
    List<Card> hand;

    @BeforeEach
    void setup() {
        player = new Player();
        hand = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i + 1, Suite.CLUB);
            hand.add(card);
        }
        player.setHand(hand);
    }

    @Test
    void testPlayCard() {

        int originalSize = player.getHand().size();

        //out of bounds
        assertNull(player.playCard(-1));
        assertNull(player.playCard(hand.size()));
        assertEquals(originalSize, player.getHand().size());

        //in bounds
        assertNotNull(player.playCard(hand.size() - 1));
        assertNotNull(player.playCard(0));
        assertNotEquals(originalSize, player.getHand().size());
    }
}
