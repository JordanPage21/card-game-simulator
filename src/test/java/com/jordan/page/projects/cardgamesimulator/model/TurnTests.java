package com.jordan.page.projects.cardgamesimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jordan.page.projects.cardgamesimulator.enums.Suite;
import com.jordan.page.projects.cardgamesimulator.model.Player;
import com.jordan.page.projects.cardgamesimulator.model.Card;
import com.jordan.page.projects.cardgamesimulator.model.Turn;

@SpringBootTest
class TurnTests {

    @Autowired
    Deck deck;

    @Test
     void testPlayCard_ValidCardAndPlayer() {

        Player player1 =  new Player();
        Player player2 =  new Player();
        Player player3 =  new Player();
        Player player4 =  new Player();

        deck.shuffle();
        deck.deal(player1,player2,player3,player4);

        Turn turn = new Turn();
        
        turn.playCard(player1, 0);
        when(card.getSuite()).thenReturn(Suite.HEART);

        // Act
        turn.playCard(player1, 0);

        // Assert
        verify(player).playCard(0);
        assertEquals(1, turn.getCurrentTurn().size());
        assertTrue(turn.getCurrentTurn().containsKey(player));
        assertEquals(card, turn.getCurrentTurn().get(player));
        assertEquals(Suite.HEART, turn.getPriority());
    }

}
