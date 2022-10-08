public class Main {
    public static void main(String[] args) {
        Board game = new Board(7, 6); // Create a board with 7 columns and 6 rows.
        Player player1 = new Player('X', game);
        Player player2 = new Player('O', game);
        int status; // To store the return value of the player's play method.

        while (game.finished == 0) {
            game.printBoard();
            System.out.print("Player 1's turn (" + player1.symbol + "): ");
            System.out.flush();
            status = player1.play();

            if (status == 0) {
                game.printBoard();
            } else if (status == 1) {
                System.out.println("The cell is already occupied.");
                continue;
            } else {
                System.out.println("The cell is outside boundaries.");
                continue;
            }

            if (game.finished == 0) {
                status = 1;

                while (status != 0) {
                    System.out.print("Player 2's turn (" + player2.symbol + "): ");
                    System.out.flush();
                    status = player2.play();

                    if (status == 1) {
                        System.out.println("The cell is already occupied.");
                        game.printBoard();
                        continue;
                    } else if (status == 2) {
                        System.out.println("The cell is outside boundaries.");
                        game.printBoard();
                        continue;
                    }
                }

                if (game.finished == 1) {
                    game.printBoard();
                    System.out.println("Player 2 (" + player2.symbol + ") has won!");
                } else if (game.finished == 2) {
                    game.printBoard();
                    System.out.println("It is a draw!");
                }
            } else if (game.finished == 1) {
                System.out.println("Player 1 (" + player1.symbol + ") has won!");
            } else {
                System.out.println("It is a draw!");
            }
        }

    }
}