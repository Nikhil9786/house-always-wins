import { startGame, rollSlots, cashOut } from './js/apiRequests.js';
import { updateUI } from './js/uiUpdate.js';
import './styles.css';

// Event listeners for buttons
document.getElementById('startButton').addEventListener('click', async () => {
    try {
        const response = await startGame();
        const blocks = document.querySelectorAll('.block');
        blocks.forEach((block, index) => {
        block.classList.add('spinning');
        });
        updateUI(response);
    } catch (error) {
        displayErrorMessage(error);
    }
});

document.getElementById('rollButton').addEventListener('click', async () => {
    try {
        const response = await rollSlots();
        const blocks = document.querySelectorAll('.block');
        blocks.forEach((block, index) => {
            block.classList.add('spinning');
        });
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

// Function to handle CASH OUT button behavior
function handleCashOutButton(){
    const button = document.getElementById('cashOutButton');
    // On hover, check for random movement and unclickable state
  button.addEventListener('mouseenter', () => {
    const shouldMove = Math.random() > 0.5;
    const shouldDisable = Math.random() > 0.6;

    if (shouldMove) {
      const randomX = Math.floor(Math.random() * 300);
      button.style.transform = `translateX(${randomX}px)`;
    }

    if (shouldDisable) {
      button.disabled = true;
      setTimeout(() => {
        button.disabled = false;
      }, 2000);
    }
  });
}

// Call the function
window.onload = () => {
    handleCashOutButton();
  };
