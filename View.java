/**
 * Author: Rishi Vaya
 * Revised: 11 April 2021
 * 
 * Description: Printer for the game
 */

 package src;

 /**
 * @brief An class that represents the moves on the game board.
 */
public class View {
    
    /**
    * @brief Prints the given game board on the terminal.
    * @param B The game board to be printed.
    */
    public static void printer(BoardT B) {
        System.out.println("- - - - - - - - - - - - -");
        for (int[] i : B.board) {
            System.out.print("|  ");
            for (int x : i) {
                if (x == 0) {
                    System.out.print("   |  ");
                }
                else {
                System.out.print(x + "  |  ");
                }
            }
            System.out.println("");
            System.out.println("- - - - - - - - - - - - -");

        } 
    }

}


/*

- - - - - - - - - - - - -
|  2  |  2  |  2  |  2  |
- - - - - - - - - - - - -
|  2  |  2  |  2  |  2  |
- - - - - - - - - - - - -
|  2  |  2  |  2  |  2  |
- - - - - - - - - - - - -
|  2  |  2  |  2  |  2  |
- - - - - - - - - - - - -

*/
