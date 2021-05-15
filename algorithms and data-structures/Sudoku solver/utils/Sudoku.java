package utils;
/**
 * A class that represents a classical 9x9 Sudoku grid. The grid can be solved via a backtracking algorithm.
 */

import java.util.stream.IntStream;

public class Sudoku {
    private int[][] board;
    private static int BOARD_START_INDEX = 0;
    private static int BOARD_SIZE = 9;
    private static int MIN_VALUE = 1;
    private static int MAX_VALUE = 9;
    private static int NO_VALUE = 0;
    private static int SUBSECTION_SIZE = 3;

    /**
     * Create a new Sudoku.
     *
     * @param board 2D Integer Array representing the board.
     */
    public Sudoku(int[][] board) {
        this.board = board;
    }

    /**
     * Solves the Sudoku.
     *
     * @return true sudoku is solved, false else
     */
    public boolean solve() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (board[row][column] == NO_VALUE) {
                    for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve()) {
                            return true;
                        }
                        board[row][column] = NO_VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validate a configuration.
     *
     * @param board  the grid
     * @param row    the current row
     * @param column the current column
     * @return true if the configuration is valid, false else
     */
    private boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column)
                && subsectionConstraint(board, row, column));
    }


    /**
     * Checks if a row is valid.
     *
     * @param board the grid
     * @param row   the row to check
     * @return true if row is valid, false else
     */
    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    /**
     * Checks if a column is valid.
     *
     * @param board  the grid
     * @param column the column to cheeck
     * @return true if column is valid, false else
     */
    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    /**
     * Checks if a subsection is valid.
     *
     * @param board  the grid
     * @param row    the starting row
     * @param column the starting column
     * @return true is subsection is valid, false else
     */
    private boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    /**
     * Check whether a board still contains unsolved fields.
     *
     * @param board      the grid
     * @param row        the current row
     * @param constraint boolean array to mark constraints
     * @param column     the current column
     * @return true if all fields are filled, false else
     */
    private boolean checkConstraint(
            int[][] board,
            int row,
            boolean[] constraint,
            int column) {
        if (board[row][column] != NO_VALUE) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the board printable.
     *
     * @return String representation of the board.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                result.append(board[row][column]);
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
