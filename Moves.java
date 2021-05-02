/**
 * Author: Rishi Vaya
 * Revised: 11 April 2021
 * 
 * Description: Moves for the BoardT class
 */

 package src;

import java.util.*;
import java.util.Random;

/**
 * @brief An class that represents the moves on the game board.
 */
public class Moves {

    private static int[] inserter = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 4};

    /**
   * @brief Adds a random value to the board.
   * @detail Adds a value of 2 or 4 to any one random empty spot on the board.
   * @param pick A list of lists of coordinates of the empty spots on the board. 
   * @param B The BoardT object that the operation is performed on.
   */
    private static void randomizer(ArrayList<Integer[]> pick, BoardT B) {
        if (pick.size() == 0) {
            return;
        }
        Random rand = new Random();
        Integer[] slot = pick.get(rand.nextInt(pick.size()));
        int ins = inserter[rand.nextInt(10)];
        B.board[slot[0]][slot[1]] = ins;
    }

    /**
   * @brief Performs the move left operation on the given board.
   * @detail Combines 2 left adjacent cells that contain the same values.
   * @param B The BoardT object that the operation is performed on.
   */
    public static void swipe_left(BoardT B) {

        B.push_left();

        for (int[] i : B.board) {
            for (int x = 0; x < (B.board_size-1); x++) {
                if (i[x] == i[x+1]) {
                    i[x] = i[x]*2;
                    B.score += i[x];
                    for (int y = x+1; y < (B.board_size-1); y++) {
                        i[y] = i[y+1];
                    }
                    i[B.board_size-1] = 0;
                }
            }
        }

        randomizer(B.find_empty(), B);
        
        
    }

    /**
   * @brief Performs the move right operation on the given board.
   * @detail Combines 2 right adjacent cells that contain the same values.
   * @param B The BoardT object that the operation is performed on.
   */
    public static void swipe_right(BoardT B) {

        B.push_right();

        for (int[] i : B.board) {
            for (int x = B.board_size-1; x > 0; x--) {
                if (i[x] == i[x-1]) {
                    i[x] = i[x]*2;
                    B.score += i[x];
                    for (int y = x-1; y > 0; y--) {
                        i[y] = i[y-1];
                    }
                    i[0] = 0;
                }
            }
        }

        randomizer(B.find_empty(), B);
        
    }

    /**
   * @brief Performs the move up operation on the given board.
   * @detail Combines 2 vertical adjacent cells that contain the same values.
   * @param B The BoardT object that the operation is performed on.
   */
    public static void swipe_up(BoardT B) {

        B.push_up();

        for (int i = 0; i < B.board_size; i++) {
            for (int x = 0; x < B.board_size-1; x++) {
                if (B.board[x][i] == B.board[x+1][i]) {
                    B.board[x][i] = B.board[x][i]*2;
                    B.score += B.board[x][i];
                    B.board[x+1][i] = 0;
                }
            }
        }

        B.push_up();
        randomizer(B.find_empty(), B);
        
    }

    /**
   * @brief Performs the move down operation on the given board.
   * @detail Combines 2 vertical adjacent cells that contain the same values.
   * @param B The BoardT object that the operation is performed on.
   */
    public static void swipe_down(BoardT B) {

        B.push_down();

        for (int i = 0; i < B.board_size; i++) {
            for (int x = B.board_size-1; x > 0 ; x--) {
                if (B.board[x][i] == B.board[x-1][i]) {
                    B.board[x][i] = B.board[x][i]*2;
                    B.score += B.board[x][i];
                    B.board[x-1][i] = 0;
                }
            }
        }

        B.push_down();
        randomizer(B.find_empty(), B);
        
    }


}

