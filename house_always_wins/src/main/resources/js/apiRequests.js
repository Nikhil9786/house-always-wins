// Function to handle API requests for starting the game
async function startGame() {
    try {
        const response = await fetch('/start-game', { method: 'POST' });
        if (!response.ok) {
            throw new Error('Unable to start the game. Please try again.');
        }
        return await response.json();
    } catch (error) {
        console.error('Error starting the game:', error.message);
        throw error;
    }
}

// Function to handle API requests for rolling slots
async function rollSlots() {
    try {
        const response = await fetch('/roll-slots', { method: 'POST' });
        if (!response.ok) {
            throw new Error('Unable to roll slots. Please try again.');
        }
        return await response.json();
    } catch (error) {
        console.error('Error rolling slots:', error.message);
        throw error;
    }
}

// Function to handle API requests for cashing out
async function cashOut() {
    try {
        const response = await fetch('/cash-out', { method: 'POST' });
        if (!response.ok) {
            throw new Error('Unable to cash out. Please try again.');
        }
        return await response.json();
    } catch (error) {
        console.error('Error cashing out:', error.message);
        throw error;
    }
}

export { startGame, rollSlots, cashOut };