package com.example.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

@SpringBootTest
@ComponentScan("com.example.Services")
public class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    public void setUp() {
        gameService = new GameService(new TestRandom());
    }

    @Test
    public void testStartGame() {
        ResponseEntity<String> response = gameService.startGame();
        assertEquals("Game session started. Good luck! You have 10 credits.", response.getBody());
    }

    @Test
    public void testRollSlotsWinningScenario() {
        // Set up a winning scenario
        gameService.startGame();
        ResponseEntity<String> response = gameService.rollSlots();
        assertEquals("Nice, you won! Result: C. You earned 10 credits.", response.getBody());
    }

    @Test
    public void testRollSlotsLosingScenario() {
        // Set up a losing scenario
        gameService.startGame();
        ResponseEntity<String> response = gameService.rollSlots();
        assertEquals("Better luck next time! Try again. Result: L. You lost 1 credit.", response.getBody());
    }

    @Test
    public void testCashOut() {
        gameService.startGame();
        ResponseEntity<String> response = gameService.cashOut();
        assertEquals("You've cashed out, thank you for playing,10 credits.", response.getBody());
    }

    @Configuration
    static class TestConfig {
        @Bean
        public Random random() {
            return new TestRandom();
        }
    }

    static class TestRandom extends Random {
        private static final long serialVersionUID = 1L;

    @Override
    public double nextDouble() {
        // Simulate a scenario where the user has between 40 and 60 credits
        double rerollProbability = 0.3;

        // 70% chance of winning roll (no re-roll)
        if (Math.random() > rerollProbability) {
            return 0.7;
        }

        // 30% chance of re-roll
        return 0.2; // Can be adjusted as needed for different scenarios
        }
    }
}