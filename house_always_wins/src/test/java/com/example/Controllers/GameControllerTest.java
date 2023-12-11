package com.example.Controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import com.example.Services.GameService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    public void testStartGame() {
        when(gameService.startGame()).thenReturn(ResponseEntity.ok("Game session started. Good luck! You have 10 credits."));
        ResponseEntity<?> response = gameController.startGame();
        assertEquals("Game session started. Good luck! You have 10 credits.", response.getBody());
    }

    @Test
    public void testRollSlots() {
        when(gameService.rollSlots()).thenReturn(ResponseEntity.ok("You won! Result: C. You earned 10 credits."));
        ResponseEntity<?> response = gameController.rollSlots();
        assertEquals("You won! Result: C. You earned 10 credits.", response.getBody());
    }

    @Test
    public void testCashOut() {
        when(gameService.cashOut()).thenReturn(ResponseEntity.ok("You've cashed out 50 credits."));
        ResponseEntity<?> response = gameController.cashOut();
        assertEquals("You've cashed out 50 credits.", response.getBody());
    }
}