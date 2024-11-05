package com.jordan.page.projects.cardgamesimulator.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jordan.page.projects.cardgamesimulator.model.Deck;

@Configuration
public class AppConfig {
    

    @Bean
    public Map<Integer, String> cardMap(){
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "2");
        map.put(3,"3");
        map.put(4,"4");
        map.put(5,"5");
        map.put(6,"6");
        map.put(7,"7");
        map.put(8,"8");
        map.put(9,"9");
        map.put(10,"10");
        map.put(11,"Jack");
        map.put(12,"Queen");
        map.put(13,"King");
        map.put(14,"Ace");
        map.put(15,"Deuce");
        map.put(16,"Little Joker");
        map.put(17,"Big Joker");
        return map;
    }

    @Bean
    public Deck deck() {
        return new Deck(cardMap());
    }
}
