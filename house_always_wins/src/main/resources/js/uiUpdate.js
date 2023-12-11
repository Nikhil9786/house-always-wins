// Function to update the UI based on the response data
function updateUI(response) {
    try {
        // Update the UI elements based on the response data
        document.getElementById('credits').innerText = response.credits;
        document.getElementById('result').innerText = response.result;
        
        // Clear spinning class after animation
        setTimeout(() => {
          const blocks = document.querySelectorAll('.block');
          blocks.forEach((block) => {
              block.classList.remove('spinning');
          });
      }, 4000); // Clear animation after 4 seconds
      
    } catch (error) {
        console.error('Error updating UI:', error.message);
        throw error;
    }
}

export { updateUI };