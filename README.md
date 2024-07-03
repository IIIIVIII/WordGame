Word Game Project
Project Description
This project implements a word guessing game where players attempt to guess a word based on certain rules and constraints. The game includes functionalities for validating guesses, managing game state, and handling exceptions for invalid guesses and words.

File Structure
Main.java: The main entry point of the project, responsible for starting the game.
WordGame.java: Contains the core logic of the word guessing game.
WordGuesser.java: Handles the guessing logic and user interactions.
WordLibrary.java: Manages the collection of valid words for the game.
InvalidGuessException.java: Custom exception for invalid guesses.
InvalidWordException.java: Custom exception for invalid words.
Prerequisites
Java Version: JDK 8 or higher
How to Run
Place all .java files (Main.java, WordGame.java, WordGuesser.java, WordLibrary.java, InvalidGuessException.java, InvalidWordException.java) in the same directory.
Compile the Java files using the following command:
sh
Copy code
javac Main.java WordGame.java WordGuesser.java WordLibrary.java InvalidGuessException.java InvalidWordException.java
Run the program using the following command:
sh
Copy code
java Main
Code Overview
Main.java
This file is the entry point of the program. Its main functions include:

Initializing the word guessing game.
Starting the game and managing the game loop.
WordGame.java
This file contains the core logic of the word guessing game. Its main functions include:

Managing the game state.
Validating guesses and updating the game accordingly.
WordGuesser.java
This file handles the guessing logic and user interactions. Its main functions include:

Capturing and processing user guesses.
Interacting with the WordGame class to update the game state.
WordLibrary.java
This file manages the collection of valid words for the game. Its main functions include:

Storing and retrieving words for the game.
Ensuring that the words used in the game are valid.
InvalidGuessException.java
This custom exception is thrown when a user makes an invalid guess. It helps in managing and responding to invalid guesses appropriately.

InvalidWordException.java
This custom exception is thrown when an invalid word is encountered in the game. It helps in managing and responding to invalid words appropriately.

Sample Output
After running the program, it will prompt the player to make guesses and provide feedback based on the correctness of the guesses. The game continues until the player correctly guesses the word or exhausts their allowed attempts.

