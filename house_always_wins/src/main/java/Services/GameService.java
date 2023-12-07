package Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class GameService {

    private int playerCredits = 10;
    private boolean isGameSessionActive = false;

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

    public ResponseEntity<String> rollSlots() {
        try {
            if (!isGameSessionActive) {
                return ResponseEntity.badRequest().body("Start a game session first.");
            }

            String[] symbols = { "C", "L", "O", "W" };
            Random random = new Random();
            String result = symbols[random.nextInt(symbols.length)];

            int winReward = 0;
            if (playerCredits >= 40 && playerCredits < 60) {
                if (random.nextDouble() <= 0.3) {
                    result = symbols[random.nextInt(symbols.length)];
                }
            } else if (playerCredits >= 60) {
                if (random.nextDouble() <= 0.6) {
                    result = symbols[random.nextInt(symbols.length)];
                }
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