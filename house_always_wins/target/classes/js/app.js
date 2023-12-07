import { startGame, rollSlots, cashOut } from './apiRequests.js';
import { updateUI } from './uiUpdate.js';

// Event listeners for buttons
document.getElementById('startButton').addEventListener('click', async () => {
    try {
        const response = await startGame();
        updateUI(response);
    } catch (error) {
        displayErrorMessage(error);
    }
});

document.getElementById('rollButton').addEventListener('click', async () => {
    try {
        const response = await rollSlots();
        updateUI(response);
    } catch (error) {
        displayErrorMessage(error);
    }
});

document.getElementById('cashOutButton').addEventListener('click', async () => {
    try {
        const response = await cashOut();
        updateUI(response);
    } catch (error) {
        displayErrorMessage(error);
    }
});

// Function to display error messages to the user
function displayErrorMessage(error) {
    // Update UI to display an error message to the user
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.innerText = 'An error occurred. Please try again later.';
    // Log the error for debugging purposes
    console.error('Error:', error.message);
}
