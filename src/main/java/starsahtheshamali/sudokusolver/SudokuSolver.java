package starsahtheshamali.sudokusolver;

import java.util.stream.IntStream;

/**
 * This class represents a Sudoku solver using a backtracking algorithm.
 * It solves a given Sudoku puzzle and prints the solution.
 */
public class SudokuSolver {

    // Constants representing the size of the boxes and the grid in the Sudoku puzzle.
    private static final int BOX_SIZE = 3;
    private static final int GRID_SIZE = BOX_SIZE * BOX_SIZE;

    /**
     * The main method to execute the Sudoku solver.
     * It initializes a Sudoku board and attempts to solve it.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // [Initialize board with a predefined puzzle]
         int[][] board = {
                 {9, 0, 0, 3, 1, 0, 0, 0, 0},
                 {0, 8, 0, 0, 0, 0, 3, 0, 0},
                 {2, 0, 0, 0, 0, 0, 0, 0, 7},
                 {0, 6, 4, 8, 0, 0, 0, 0, 0},
                 {0, 0, 7, 0, 0, 4, 2, 0, 0},
                 {0, 0, 0, 0, 0, 6, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 7, 4, 6},
                 {0, 0, 0, 0, 5, 0, 0, 8, 0},
                 {0, 0, 0, 0, 8, 9, 0, 2, 0}
         };
        // [Solve the puzzle and print the result]
         if(solve(board)) {
             System.out.println("😎 SOLVED!!! EASY PEASY WHATS NEXT ?");
             printResult(board);
         } else {
             System.out.println("☹️ WHAT went wrong");
         }
    }

    /**
     * Prints the Sudoku board.
     *
     * @param board The Sudoku board to be printed.
     */
    private static void printResult(int[][] board) {
       for (int row = 0;  row < GRID_SIZE; row++) {
           if ((row % BOX_SIZE == 0) && (row != 0)) {
               System.out.println("----------------------------");
           }
           for(int col = 0; col < GRID_SIZE; col++) {
               if ((col % BOX_SIZE == 0) && (col != 0)) {
                   System.out.print("|");
               }
               final int cellValue = board[row][col];
               System.out.print(" ");
               if (cellValue == 0) {
                   System.out.print(" ");
               } else {
                   System.out.print(cellValue);
               }
               System.out.print(" ");
           }
           System.out.println();
       }
    }

    /**
     * Checks if a given number is allowed in a specified row.
     *
     * @param board The Sudoku board.
     * @param row The row to check.
     * @param number The number to check.
     * @return true if the number is not present in the row; false otherwise.
     */
    private static boolean allowedInRow(int[][] board, int row, int number) {
        //logic to distinguished between rows
        for (int i = 0; i < GRID_SIZE; i++){
            if(board[row][i] == number){
                return false;
            }
        }
        return true;
    } // end of allowedInRow

    /**
     * Checks if a given number is allowed in a specified column.
     *
     * @param board The Sudoku board.
     * @param col The column to check.
     * @param number The number to check.
     * @return true if the number is not present in the column; false otherwise.
     */
    private static boolean allowedInCol(int[][] board, int col, int number) {
        //logic to distinguished between col
        return IntStream.range(0, GRID_SIZE)
                .noneMatch(row -> board[row][col] == number);
    } // end of allowedInCol

    /**
     * Checks if a given number is allowed in the 3x3 box that contains the specified cell.
     *
     * @param board The Sudoku board.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param number The number to check.
     * @return true if the number is not present in the box; false otherwise.
     */
    private static boolean allowedInBox(int[][] board, int row, int col, int number ) {
        // logic is that if col is in position 5 - 5 % 3 = 5 - 2 = 3
        final int boxCol = col - (col % BOX_SIZE);
        final int boxRow = row - (row % BOX_SIZE);
        //iterating over rows and columns
        for (int i = 0; i < BOX_SIZE; i++){
            for(int j = 0; j < BOX_SIZE; j++){
                //where on the board we are currently are and the number we are looking for.
                if (board[boxRow + i][boxCol + j] == number){
                   return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a given number is allowed in a specified cell considering Sudoku rules.
     *
     * @param board The Sudoku board.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param number The number to check.
     * @return true if the number is allowed; false otherwise.
     */
    private static boolean isAllowed(int[][] board, int row, int col, int number) {
        return  allowedInRow(board, row, number) &&
                allowedInCol(board, col, number) &&
                allowedInBox(board, row, col, number);
    }

    /**
     * Solves the Sudoku puzzle using a backtracking algorithm.
     *
     * @param board The Sudoku board to be solved.
     * @return true if the puzzle is solved; false if no solution exists.
     */
    private static boolean solve(int[][] board) {
        for(int row = 0; row < GRID_SIZE; row++) {
            for(int col = 0; col < GRID_SIZE; col++){
                if(board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isAllowed(board, row, col, num)) {
                           board[row][col] = num;
                           //using recursion
                            if(solve(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
