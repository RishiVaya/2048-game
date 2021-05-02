package src;

import java.util.Arrays;

public class Tester {

    public static void main(String args[]){
        BoardT a = new BoardT(new int[][] {{0, 2, 0, 2}, {2, 2, 0, 4}, {8, 4, 16, 16}, {0, 4, 0, 0}});
        View.printer(a);
        Moves.swipe_down(a);
        View.printer(a);
        //System.out.println(a.get_score());
        //System.out.println(a.get_win());
        //System.out.println(a.get_lose());
    }
    
}
