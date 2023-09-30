/*------------------------------------------------------------------------------------------------------------------------
Team Name:  PATH
Members: 	Hoa Tuong Minh Nguyen
		    Tuan-Anh Ho
		    Paul Valdez
		    Ali Fayed
Team Leader: Hoa Tuong Minh Nguyen

------------------------------------------------------------------------------------------------------------------------*/
/**
 * A simple program allows two players playing Tic-Tac-Toe using command
 * @author  Tuan-Anh Ho
 * @version 1.0
 */

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        TicTacToeBoard board = new TicTacToeBoard();
        board.printBoard();
        while (!board.isFilled()) {
            getPlayerToken("X", board);
            if (!board.isFilled()) getPlayerToken("O", board);
        }
        System.out.println("It's a tie game!");
    }

    private static void getPlayerToken(String s, TicTacToeBoard board) {
        int row = 0;
        int column = 0;
        String token;
        Scanner input = new Scanner(System.in);
        //This loop to validate users input and ensure a smooth experience
        do {
            System.out.print("Enter a row (0, 1, or 2) for player " + s +": ");
            token = input.nextLine();
            if (!isValid(token)) System.out.println("Invalid input");
            else {
                row = Integer.parseInt(token);
            }
            System.out.print("Enter a column (0, 1, or 2) for player " + s +": ");
            token = input.nextLine();
            if (!isValid(token)) System.out.println("Invalid input");
            else {
                column = Integer.parseInt(token);
            }
            //Check cell's status
            if (board.cellIsOccupied(row, column)) System.out.println("Cell is occupied");
        } while (!isValid(token) || board.cellIsOccupied(row, column));
        switch (s) {
            case "X": board.addX(row, column); break;
            case "O": board.addO(row, column); break;
        }
        board.printBoard();
        if (isWin(s, row, column, board)) {
            System.out.println(s + " player has won!!!");
            System.exit(0);
        }
    }

    private static boolean isWin(String s, int row, int col, TicTacToeBoard board) {
        return (straightRow(s, row, board) ||
                straightCol(s, col, board) ||
                straightUpperDiagonal(s, board) ||
                straightLowerDiagonal(s, board));
    }

    private static boolean straightRow(String s, int row, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            if (!board.cells[row][i].matches(s)) return false;
        }
        return true;
    }

    private static boolean straightCol(String s, int col, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            if (!board.cells[i][col].matches(s)) return false;
        }
        return true;
    }

    private static boolean straightUpperDiagonal(String s, TicTacToeBoard board) {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            if (!board.cells[i][i].matches(s)) result = false;
        }
        return result;
    }

    private static boolean straightLowerDiagonal(String s, TicTacToeBoard board) {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            if (!board.cells[i][2-i].matches(s)) result = false;
        }
        return result;
    }

    private static boolean isValid(String token) {
        return (token.matches("[0-2]"));
    }

    private static class TicTacToeBoard {
        private String[][] cells = new String[3][3];

        public TicTacToeBoard() {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    cells[i][j] = " ";
                }
            }
        }

        public String[][] getCells() {
            return cells;
        }

        public void addX(int row, int col) {
            cells[row][col] = "X";
        }
        public void addO(int row, int col) {
            cells[row][col] = "O";
        }

        public boolean isFilled() {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].matches(" ")) return false;
                }
            }
            return true;
        }

        public boolean cellIsOccupied(int row, int col) {
            return (cells[row][col] != " ");
        }

        public void printBoard() {
            for (int i = 0; i < cells.length; i++) {
                System.out.println("_____________");
                System.out.print("| ");
                for (int j = 0; j < cells.length; j++) {
                    System.out.print(cells[i][j] + " | ");
                }
                System.out.println();
            }
            System.out.println("_____________");
        }
    }
}