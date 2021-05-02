/**
 * Author: Rishi Vaya
 * Revised: 11 April 2021
 * 
 * Description: ADT that dfines the game board 
 */

package src;

import java.util.*;

/**
 * @brief An ADT that represents a game board.
 */
public class BoardT {

    public int[][] board;
    public int board_size;
    public int score;
    public boolean win;
    public boolean lose;

    /**
   * @brief Initializes a Board object.
   * @detail Sets all the values in the board to 0 except for 2 random cells which can be 2 or 4.
   * @param s Size of the game board.
   */
    public BoardT(int s) {
        board_size = s;
        board = new int[board_size][board_size];
        for (int[] i : board) {
            for (int j = 0; j < board_size; j++) {
                i[j] = 0;
            }
        }
        Random rand = new Random();
        int[] inserter = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
        for (int i = 0; i < 2; i++) {
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);
            int ins = inserter[rand.nextInt(10)];
            board[x][y] = ins;
        }
        score = 0;
        win = false;
        lose = false;
    }

    /**
   * @brief Initializes a Course object.
   * @param given_board A grid that is the start of the game.
   * @throws IllegalArgumentException Exception thrown when the dimensions of the game board do not equal the board size.
   */
    public BoardT(int[][] given_board) {
        board_size = given_board.length;
        if (given_board.length != board_size || given_board[0].length != board_size || given_board[1].length != board_size
             || given_board[2].length != board_size || given_board[3].length != board_size) {
            throw new IllegalArgumentException("Input size is invalid");
        }

        board = given_board;
        score = 0;
        win = false;
        lose = false;
    }

    /**
   * @brief Checks if the user lost.
   * @details transitions lose to true when the user has no possible moves left.
   */
    public void lost() {
        if (this.find_empty().isEmpty() == false) {
            return;
        }
        for (int y = 0; y < board_size-1; y++) {
            if (board[0][y] == board[0][y+1]) {
                return;
            }
        }

        for (int i = 1; i < board_size; i++ ) {
            for (int x = 0; x < board_size; x++) {
                if (x == 0) {
                    if (board[i][x] == board[i-1][x]) {
                        return;
                    }
                }
                else {
                    if (board[i][x] == board[i-1][x] || board[i][x] == board[i][x-1]) {
                        return;
                    }
                }
            }
        }
        lose = true;
    }
    
    /**
   * @brief Finds empty positions in the board.
   * @details Finds the coordinates of 0s in the board grid.
   * @return A list of lists containing the coordinates of empty spots.
   */
    public ArrayList<Integer[]> find_empty() {    
        ArrayList<Integer[]> answer = new ArrayList<>();
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                if (board[i][j] == 0) {
                    answer.add(new Integer[] {i, j});
                }
                if (board[i][j] == 2048) {
                    win = true;
                }
            }
        } 
        return answer;
    }

    /**
   * @brief Compares two different BoardT objects.
   * @param s the BoardT to compare with this board.
   * @param n The number of cells in the grid that can be different.
   * @return True if the number of differences betweeen the 2 boards are less than or equal to n.
   * Otherwise, returns false.
   */
    public boolean equal_board(BoardT s, int n) {

        if (board_size != s.board_size) {
            return false;
        }

        int counter = 0;
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                if (board[i][j] != s.board[i][j]) {
                    counter++;
                }
            }
        }
        if (counter > n) {
            return false;
        }
        return true;
    }

    /**
   * @brief Pushes all board values to left.
   * @details Moves all 0s in the board to the right.
   */
    public void push_left() {

        for (int[] i : board) {
            int counter = 0;
            for (int x = 0; x < board_size; x++) {
                if (i[x] != 0) {
                    i[counter++] = i[x];
                }
            }
            while (counter < board_size) {
                i[counter++] = 0;
            }
        }
    }

    /**
   * @brief Pushes all board values to right.
   * @details Moves all 0s in the board to the left.
   */
    public void push_right() {

        for (int[] i : board) {
            int counter = board_size-1;
            for (int x = board_size-1; x >= 0; x--) {
                if (i[x] != 0) {
                    i[counter--] = i[x];
                }
            }
            while (counter >= 0) {
                i[counter--] = 0;
            }
        }
    }

    /**
   * @brief Pushes all board values up.
   * @details Moves all 0s in the board to the bottom.
   */
    public void push_up() {

        for (int i = 0; i < board_size; i++) {
            int counter = 0;
            for (int x = 0; x < board_size; x++) {
                if (board[x][i] != 0) {
                    board[counter++][i] = board[x][i];
                }
            }
            while (counter < board_size) {
                board[counter++][i] = 0;
            }
        }
    }

    /**
   * @brief Pushes all board values down.
   * @details Moves all 0s in the board to the top.
   */
    public void push_down() {

        for (int i = 0; i < board_size; i++) {
            int counter = board_size-1;
            for (int x = board_size-1; x >= 0; x--) {
                if (board[x][i] != 0) {
                    board[counter--][i] = board[x][i];
                }
            }
            while (counter >= 0) {
                board[counter--][i] = 0;
            }
        }
    }

    /**
   * @brief Gets the score value.
   * @return An integer that represents the score.
   */
    public int get_score() {

        return score;
    }

    /**
   * @brief Gets the win value.
   * @return A boolean that represents the win status.
   */
    public boolean get_win() {

        return win;
    }

    /**
   * @brief Gets the lose value.
   * @return A boolean that represents the lose status.
   */
    public boolean get_lose() {

        return lose;
    }

    /**
   * @brief Resets the board, win, lose and score values just like the constructor.
   */
    public void reset() {
        board = new int[board_size][board_size];
        for (int[] i : board) {
            for (int j = 0; j < board_size; j++) {
                i[j] = 0;
            }
        }
        Random rand = new Random();
        int[] inserter = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
        for (int i = 0; i < 2; i++) {
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);
            int ins = inserter[rand.nextInt(10)];
            board[x][y] = ins;
        }
        score = 0;
        win = false;
        lose = false;
    }

    //public ArrayList<Integer[]> empty_right() {     // when a swipe right move is made
    //    ArrayList<Integer[]> answer = new ArrayList<>();
    //    for (int i = 0; i < board_size; i++) {
    //        if (board[i][0] == 0) {
    //            answer.add(new Integer[] {i, 0});
    //        }
    //    } 
    //    return answer;
    //}

    //public ArrayList<Integer[]> empty_left() {  // when a swipe left move is made
    //    ArrayList<Integer[]> answer = new ArrayList<>();
    //    for (int i = 0; i < board_size; i++) {
    //        if (board[i][board_size-1] == 0) {
    //            answer.add(new Integer[] {i, board_size-1});
    //        }
    //    } 
    //    return answer;
    //}

    //public ArrayList<Integer[]> empty_bottom() { // when a swipe dowm move is made
    //    ArrayList<Integer[]> answer = new ArrayList<>();
    //    for (int i = 0; i < board_size; i++) {
    //        if (board[0][i] == 0) {
    //            answer.add(new Integer[] {0, i});
    //        }
    //    } 
    //    return answer;
    //}

    //public ArrayList<Integer[]> empty_top() {  // when the swipe up move is made
    //    ArrayList<Integer[]> answer = new ArrayList<>();
    //    for (int i = 0; i < board_size; i++) {
    //        if (board[board_size-1][i] == 0) {
    //            answer.add(new Integer[] {board_size-1, i});
    //        }
    //    } 
    //    return answer;
    //}

    


}
