/**
 * The Board class deals with the storage of the moves by using a 1d string
 * array and has the checkFinish method which checks if the game is finished
 * and changes the public finished variable accordingly; it also has the
 * printBoard method which prints the board onto the console.
 */
public class Board {
    public int finished = 0; // 0 not finished, 1 win/loss, 2 draw.
    int col, row, cellNum;
    String[] cells;

    public Board(int col, int row) {
        this.col = col;
        this.row = row;
        this.cellNum = col * row;
        cells = new String[cellNum];
    }

    /**
     * The checkFinish method first checks for a win, by looking at
     * the 3 possible wins for every axis at the cell the last move was
     * played at, this is done by concatenating the cell contents and comparing
     * it to a local string win. In the case that no win was found, the method
     * will then finally check for a draw; modifying the finished variable accordingly.
     */
    public void checkFinish(int playerCellNum) {

        int x = (playerCellNum - 1) % col; // Get x coordinate using cell number.
        int y = (playerCellNum - 1) / col; // Get y coordinate using cell number.
        int a = playerCellNum, b, c; // Variables to hold the cell number of the current cells.

        // If the player symbol was X, win will have the string "X X X ".
        String win = String.format("%s%s%s", cells[playerCellNum - 1], cells[playerCellNum - 1], cells[playerCellNum - 1]);

        /**
         * The if conditions for each possible win ensures the code is only
         * executed if the coordinates are within the board boundaries.
         * after which the coordinates of the cells are converted back to
         * cell numbers (to use the 1d array); using the equation:
         * (y * col) + x + 1.
         * All but the first if conditions contain `finished == 0` to avoid
         * unnecessary processing after already finding a win.
         */

        // Horizontal axis.
        if (x + 2 < col) {
            b = y * col + (x + 1) + 1;
            c = y * col + (x + 2) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (x + 1 < col && x - 1 >= 0)) {
            b = y * col + (x + 1) + 1;
            c = y * col + (x - 1) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (x - 2 >= 0)) {
            b = y * col + (x - 1) + 1;
            c = y * col + (x - 2) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }

        // Vertical axis.
        if (finished == 0 && (y + 2 < row)) {
            b = (y + 1) * col + x + 1;
            c = (y + 2) * col + x + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (y + 1 < row && y - 1 >= 0)) {
            b = (y + 1) * col + x + 1;
            c = (y - 1) * col + x + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (y - 2 >= 0)) {
            b = (y - 1) * col + x + 1;
            c = (y - 2) * col + x + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }

        // Diagonal top right - bottom left.
        if (finished == 0 && (x + 2 < col && y - 2 >= 0)) {
            b = (y - 1) * col + (x + 1) + 1;
            c = (y - 2) * col + (x + 2) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (x + 1 < col && y - 1 >= 0 && x - 1 >= 0 && y + 1 < row)) {
            b = (y + 1) * col + (x - 1) + 1;
            c = (y - 1) * col + (x + 1) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (x - 2 >= 0 && y + 2 < row)) {
            b = (y + 1) * col + (x - 1) + 1;
            c = (y + 2) * col + (x - 2) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }

        // Diagonal top left - bottom right.
        if (finished == 0 && (x - 2 >= 0 && y - 2 >= 0)) {
            b = (y - 1) * col + (x - 1) + 1;
            c = (y - 2) * col + (x - 2) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (x - 1 >= 0 && y - 1 >= 0 && x + 1 < col && y + 1 < row)) {
            b = (y - 1) * col + (x - 1) + 1;
            c = (y + 1) * col + (x + 1) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }
        if (finished == 0 && (x + 2 < col && y + 2 < row)) {
            b = (y + 1) * col + (x + 1) + 1;
            c = (y + 2) * col + (x + 2) + 1;
            if (win.equals(cells[a - 1] + cells[b - 1] + cells[c - 1])) {
                finished = 1;
            }
        }

        /**
         * Check draw.
         * Only run if a win was not found, otherwise would override
         * the variable finished to a 0 or 2.
         */
        if (finished == 0) {
            finished = 2;
            for (int l = 0; l < cellNum; l++) {
                if (cells[l] == null) {
                    finished = 0;
                }
            }
        }

    }

    /**
     * The printBoard method prints the board neatly, if the cell is occupied
     * it prints its value, if the cell is not occupied it prints its
     * cell number which provides an intuitive user experience.
     */
    public void printBoard() {
        System.out.println("");
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                if (i == 0) {
                    if (cells[(i + j * col)] == null) {
                        System.out.format("%02d", (i + j * col) + 1);
                        System.out.flush();
                    } else {
                        System.out.print(cells[i + j * col]);
                        System.out.flush();
                    }
                } else {
                    System.out.print("|");
                    System.out.flush();
                    if (cells[(i + j * col)] == null) {
                        System.out.format("%02d", (i + j * col) + 1);
                        System.out.flush();
                    } else {
                        System.out.print(cells[i + j * col]);
                        System.out.flush();
                    }
                }
            }
            if (j != row - 1) {
                System.out.println("");
                for (int k = 0; k < col - 1; k++) {
                    System.out.print("--+");
                    System.out.flush();
                }
                System.out.println("--");
            }

        }
        System.out.println("");
    }
}