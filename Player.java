import java.util.Scanner;
public class Player {
    char symbol;
    Board game;
    Scanner sc = new Scanner(System.in);
    
    /**
     * The constructor requires a symbol for the player (such as 'X'), as well
     * as the board the player will be playing in.
     */
    public Player(char symbol, Board board) {
        this.symbol = symbol;
        this.game = board;
    }

    /**
     * The play method takes in the cell number using the scanner and after checking that the
     * cell is within bounds and not already occupied it assigns the cell
     * the symbol concatenated with a space so that the board is printed nicely.
     * After the cell is assigned the symbol the board method checkFinish is
     * called given the cell number to check for a win/draw.
     */
    public int play() {
        int playerCellNum = sc.nextInt();
        if (playerCellNum < 1 || playerCellNum > game.cellNum) {
            return 2; // If out of bounds.
        } else if (game.cells[playerCellNum - 1] != null) {
            return 1; // If already occupied.
        } else {
            game.cells[playerCellNum - 1] = symbol + " ";
        }
        game.checkFinish(playerCellNum);
        return 0;
    }
}