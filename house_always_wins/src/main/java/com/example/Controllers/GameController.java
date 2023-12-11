package com.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Services.GameService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public ResponseEntity<?> startGame(){
        try {
            return gameService.startGame();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while starting the game.");
        }
    }

    @PostMapping("/roll-slots")
    public ResponseEntity<?> rollSlots() {
        try {
            return gameService.rollSlots();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while rolling the slots.");
        }
    }

    @PostMapping("/cash-out")
    public ResponseEntity<?> cashOut() {
        try {
            return gameService.cashOut();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while cashing out.");
        }
    }
}