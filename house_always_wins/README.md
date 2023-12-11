# Slot Machine Game

## Description

    This full-stack application simulates a slot machine game with a unique twist. The project is built using Java for the backend and a combination of HTML, CSS, and JavaScript for the frontend. The game twist includes a server-side logic to ensure that the house always has a chance to win.

## Dependencies

    1. Java(11 or higher)
    2. Maven(3.5)
    3. Spring Boot

## How to Run

    To run the project locally, follow these steps:

    1. Clone the repository:

    ```bash```
    git clone https://github.com/your-username/house_always_wins.git

    2. Navigate to the project directory:

        ```bash```
        cd house_always_wins

    3. Build and run the Spring Boot application(VSCode):

        ```bash```
        mvn spring-boot:run

    The application will be accessible at http://localhost:8080


## Usage

    - Open the application in a web browser
    - Start a game session by clicking the "Pull Lever" button
    - Roll the slots to try your luck and win credits
    - Use the "CASH OUT" button to move credits to your account

## Approach

 ### Technical Overview

    The Slot Machine Game project is a full-stack web application built using Java for the server-side logic and Spring Boot as the framework. The client-side is developed with HTML, CSS, and JavaScript to create an interactive user interface. The backend and frontend communicate through HTTP requests, and the game's logic is designed to ensure that the house always has an edge, ensuring win.

 ### Server-Side Implementation

  * GameService (com.example.Services.GameService)
        The GameService class  manages the game session, implements the slot machine's rules, and handles requests from the client. Following is an overview of the key functionalities:
        - Session Management: Tracks player credits and whether a game session is active
        - Rolling Slots: Simulates the slot machine's spin, applies the game's rules, and calculates the win or loss
        - Cash Out: Transfers credits from the game session to the user's account, ending the session

  * GameConfig (com.example.Services.GameConfig)
        The GameConfig class is a simple configuration class that provides a Random bean to the GameService. This ensures consistent random number generation during testing.

  * GameController (com.example.Controllers.GameController)
        The GameController class handles incoming HTTP requests from the client. It delegates these requests to the GameService, which then processes them and returns the appropriate response.

 ### Client-Side Implementation

  * HTML Template (src/main/resources/templates/index.html)
        The HTML template defines the structure of the web page. It includes elements for displaying the slot machine blocks, buttons for actions like pulling the lever and cashing out, and an area to show the player's credits and game results.

  * CSS Styles (src/main/resources/static/styles.css)
        The CSS styles enhance the visual presentation of the game. It includes styles for the slot machine blocks, buttons, and animations to create a spinning effect during the game.

  * JavaScript (src/main/resources/static/app.js)
        The JavaScript code is responsible for handling user interactions and making asynchronous requests to the server. Key functionalities include:
        - Button Click Listeners: Listens for clicks on buttons such as "Pull Lever", "Roll Slots", and "Cash Out"
        - API Requests: Uses the Fetch API to send requests to the server for starting the game, rolling the slots, and cashing out
        - UI Updates: Updates the UI elements based on the server's responses, providing feedback to the user

    **Code Interactions**

    - Starting a Game Session:
        User clicks the "Pull Lever" button - > JavaScript sends a request to the server's /start-game endpoint - > Server starts a new game session and responds with the initial credits and a welcome message

    - Rolling the Slots:
        User clicks the "Roll Slots" button - > JavaScript sends a request to the server's /roll-slots endpoint - > Server simulates the slot machine spin, determines the outcome, and responds with the result - > updates the UI to display the spinning animation and then reveals the result

    - Cashing Out:
        User clicks the "Cash Out" button - > JavaScript sends a request to the server's /cash-out endpoint - > Server transfers credits from the game session to the user's account and responds with a confirmation message - > JavaScript updates the UI to reflect the new balance