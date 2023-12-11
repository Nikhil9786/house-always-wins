package com.example.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class GameService {

    private int playerCredits = 10;
    private boolean isGameSessionActive = false;
    private Random random;
    private static final double reRollProab40To60 = 0.3;
    private static final double reRollProab60Plus = 0.6;

    public GameService(Random random){
        this.random = new Random();
    }

    public ResponseEntity<String> startGame() {
        try {
            if (!isGameSessionActive) {
                isGameSessionActive = true;
                playerCredits = 10;
                return ResponseEntity.ok("Game session started. Good luck! You have 10 credits.");
            } else {
                return ResponseEntity.badRequest().body("Game session is already active.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while starting the game. Sometimes bad start is lucky, keep up the spirit.");
        }
    }

    private String performRoll(){
        String[] symbols = { "C", "L", "O", "W" };
        return symbols[random.nextInt(symbols.length)];

    }

    public ResponseEntity<String> rollSlots() {
        try {
            if (!isGameSessionActive) {
                return ResponseEntity.badRequest().body("Start a game session first.");
            }

            String result = performRoll();
            int winReward = 0;

            double rerollProbability = (playerCredits >= 60) ? reRollProab60Plus : (playerCredits >=40) ? reRollProab40To60 : 0.0;
            
            if (random.nextDouble() <= rerollProbability) {
                result = performRoll();  
            }

            switch (result) {
                case "C":
                    winReward = 10;
                    break;
                case "L":
                    winReward = 20;
                    break;
                case "O":
                    winReward = 30;
                    break;
                case "W":
                    winReward = 40;
                    break;
            }

            if (winReward > 0) {
                playerCredits += winReward;
                return ResponseEntity.ok("Nice, you won! Result: " + result + ". You earned " + winReward + " credits.");
            } else {
                playerCredits -= 1;
                return ResponseEntity.ok("Better luck next time! Try again. Result: " + result + ". You lost 1 credit.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while rolling the slots.");
        }
    }

    public ResponseEntity<String> cashOut() {
        try {
            if (!isGameSessionActive) {
                return ResponseEntity.badRequest().body("No active game session.");
            }

            int userAccount = playerCredits;
            playerCredits = 0;
            isGameSessionActive = false;

            return ResponseEntity.ok("You've cashed out, thank you for playing," + userAccount + " credits.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while cashing out.");
        }
    }
}