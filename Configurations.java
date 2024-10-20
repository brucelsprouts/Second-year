/* This class implements all the methods needed by algorithm computerPlay */

import javax.xml.crypto.Data;

public class Configurations {
    private char[][] board;
    private int boardsize;
    private int lengthToWin;
    private int max_levels;

    /* Constructor */
    public Configurations (int boardsize, int lengthToWin, int max_levels) {
        this.boardsize = boardsize;
        this.lengthToWin = lengthToWin;
        this.max_levels = max_levels;
        this.board = new char[boardsize][boardsize];
        for (int i = 0; i < boardsize; i++) {           // Initialize the board with empty spaces
            for (int j = 0; j < boardsize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /* Returns an empty hash */
    public HashDictionary createDictionary() {
        return new HashDictionary(9973); 
    }

    /* Converts the board to a String, then checks if it exists in the hashTable and returns the associated score if found, otherwise returns -1 */
    public int repeatedConfiguration(HashDictionary configurations) {
        StringBuilder keyBuilder = new StringBuilder();
        for (int i = 0; i < boardsize; i++) {        // Convert the board to a string
            for (int j = 0; j < boardsize; j++) {
                keyBuilder.append(board[i][j]);
            }
        }
        String key = keyBuilder.toString();         // Convert the StringBuilder to a String
        return configurations.get(key);             // Get the score associated with the key
        return -1;                                  // Return -1 if the key is not found
    }

    /* Represents content of board as String then inserts this String and score in hashDictionary */
    public void addConfiguration(HashDictionary configurations, int score) {
        StringBuilder keyBuilder = new StringBuilder();
        for (int i = 0; i < boardsize; i++) {        // Convert the board to a string
            for (int j = 0; j < boardsize; j++) {
                keyBuilder.append(board[i][j]);
            }
        }
        String key = keyBuilder.toString();         // Convert the StringBuilder to a String
        Data data = new Data(key, score);           // Create a new Data object with the key and score
        configurations.put(data);                   // Add the Data object to the hashDictionary
    }

    /* Stores a symbol in the board */
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    /* Returns true if board is empty otherwise false */
    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    /* Returns true is there is a continuous sequence of length of at least k formed by tiles of the same kind of symbol on the board 
     * by checking rows, columns and diagonals
    */
    public boolean wins(char symbol) {
        // Check rows
        for (int i = 0; i < boardsize; i++) {               // Iterate through the rows
            int count = 0;
            for (int j = 0; j < boardsize; j++) {           // Iterate through the rows
                if (board[i][j] == symbol) {
                    count++;
                    if (count == lengthToWin) return true;  // Return true if a winning sequence is found
                } else {
                    count = 0;
                }
            }
        }

        // Check columns
        for (int j = 0; j < boardsize; j++) {               // Iterate through the columns
            int count = 0;
            for (int i = 0; i < boardsize; i++) {           // Iterate through the rows
                if (board[i][j] == symbol) {
                    count++;
                    if (count == lengthToWin) return true;  // Return true if a winning sequence is found
                } else {
                    count = 0;
                }
            }
        }

        // Check diagonals (top left to bottom right)
        for (int i = 0; i <= boardsize - lengthToWin; i++) {        // Start from the top left corner
            for (int j = 0; j <= boardsize - lengthToWin; j++) {    // Check diagonals from left to right
                int count = 0;
                for (int k = 0; k < lengthToWin; k++) {             // Check the diagonal
                    if (board[i + k][j + k] == symbol) {
                        count++;
                        if (count == lengthToWin) return true;      // Return true if a winning sequence is found
                    } else {
                        break;
                    }
                }
            }
        }

        // Check diagonals (bottom left to top right)
        for (int i = lengthToWin - 1; i < boardsize; i++) {         // Start from the bottom left corner
            for (int j = 0; j <= boardsize - lengthToWin; j++) {    // Check diagonals from left to right
                int count = 0;
                for (int k = 0; k < lengthToWin; k++) {             // Check the diagonal
                    if (board[i - k][j + k] == symbol) {
                        count++;
                        if (count == lengthToWin) return true;      // Return true if a winning sequence is found
                    } else {
                        break;
                    }
                }
            }
        }

        return false;   // Return false if no winning sequence is found
    }

    /*  Returns true if board has no empty positions left and no player has won the game. */
    public boolean isDraw() {
        for (int i = 0; i < boardsize; i++) {       // Iterate through the board
            for (int j = 0; j < boardsize; j++) { 
                if (board[i][j] == ' ') {
                    return false;                   // Return false if there are empty positions
                }
            }
        }

        if (wins('X') || wins('O')) {
            return false;                           // Return false if a player has won the game
        }

        return true;    // Return true if the game is a draw
    }

    /* Returns the evaluation of the board */
    public int evalBoard() {
        // Check if the human player has won
        if (wins('X')) {
            return 1;  // Return 1 if the human player has won
        }
    
        // Check if the computer player has won
        if (wins('O')) {
            return -1;  // Return -1 if the computer player has won
        }
    
        // Check if the game is a draw
        if (isDraw()) {
            return 0;  // Return 0 if the game is a draw
        }
    
        // Return 0 if the game is still ongoing
        return 0;
    }
}