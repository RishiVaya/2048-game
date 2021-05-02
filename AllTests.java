/**
 * Author: Rishi Vaya
 * Revised: 11 April 2021
 * 
 * Description: Tests for all the modules
 */

package src;

import java.util.*;
import org.junit.*;



import static org.junit.Assert.*;


public class AllTests
{
    BoardT ex1;
    BoardT ex2;
    BoardT ex3;
    BoardT ex4;
    BoardT ex5;
    BoardT ex6;
    BoardT ex7;
    BoardT ex8;
    BoardT ex9;


    @Before
    public void setUp() {
        ex1 = new BoardT(4);
        ex2 = new BoardT(new int[][] {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        ex3 = new BoardT(new int[][] {{2, 4, 8, 16}, {32, 64, 128, 256}, {512, 1024, 2048, 4096}, {2, 4, 8, 16}});
        ex4 = new BoardT(new int[][] {{2, 2, 4, 1}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        ex5 = new BoardT(new int[][] {{2, 3, 4, 1}, {5, 6, 7, 8}, {5, 10, 11, 12}, {13, 14, 15, 16}});
        ex6 = new BoardT(new int[][] {{2, 3, 4, 0}, {5, 6, 7, 8}, {9, 10, 12, 12}, {13, 14, 15, 16}});
        ex7 = new BoardT(new int[][] {{0, 2, 4, 1}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        ex8 = new BoardT(new int[][] {{0, 2, 0, 2}, {2, 0, 0, 4}, {8, 4, 16, 16}, {0, 0, 0, 0}});
        ex9 = new BoardT(new int[][] {{0, 2, 0, 2}, {2, 2, 0, 4}, {8, 4, 16, 16}, {0, 4, 0, 0}});

    }

    @After
    public void tearDown() {
        ex1 = null;
        ex2 = null;
        ex3 = null;
        ex4 = null;
        ex5 = null;
        ex6 = null;
        ex7 = null;
        ex8 = null;
        ex9 = null;


    }

	@Test
    public void construct1()
    {
        assertTrue(ex1.equal_board(ex2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void construct2()
    {
        BoardT err = new BoardT(new int[][] {{1, 0, 3}, {2, 0, 4}, {7, 8, 9, 6}});
    }

    @Test
    public void lost1()
    {   
        ex2.lost();
        assertTrue(ex2.get_lose() == false);
    }

    @Test
    public void lost2()
    {   
        ex3.lost();
        assertTrue(ex3.get_lose());
    }

    @Test
    public void lost3()
    {   
        ex4.lost();
        assertTrue(ex4.get_lose() == false);
    }

    @Test
    public void lost4()
    {   
        ex5.lost();
        assertTrue(ex5.get_lose() == false);
    }

    @Test
    public void lost5()
    {   
        ex6.lost();
        assertTrue(ex6.get_lose() == false);
    }

    @Test
    public void find_empty1(){
        assertTrue(ex3.find_empty().size() == 0);
    }

    @Test
    public void find_empty2(){
        assertTrue(ex6.find_empty().get(0)[0] == 0 &&  ex6.find_empty().get(0)[1] == 3);
    }

    @Test
    public void equals() {
        assertTrue(ex7.equal_board(ex4, 1));
    }

    @Test
    public void pushleft() {
        ex8.push_left();
        assertTrue(ex8.equal_board(new BoardT(new int[][] {{2, 2, 0, 0}, {2, 4, 0, 0}, {8, 4, 16, 16}, {0, 0, 0, 0}}), 0));
    }

    @Test
    public void pushright() {
        ex8.push_right();
        assertTrue(ex8.equal_board(new BoardT(new int[][] {{0, 0, 2, 2}, {0, 0, 2, 4}, {8, 4, 16, 16}, {0, 0, 0, 0}}), 0));
    }

    @Test
    public void pushup() {
        ex8.push_up();
        assertTrue(ex8.equal_board(new BoardT(new int[][] {{2, 2, 16, 2}, {8, 4, 0, 4}, {0, 0, 0, 16}, {0, 0, 0, 0}}), 0));
    }

    @Test
    public void pushdown() {
        ex8.push_down();
        assertTrue(ex8.equal_board(new BoardT(new int[][] {{0, 0, 0, 0}, {0, 0, 0, 2}, {2, 2, 0, 4}, {8, 4, 16, 16}}), 0));
    }

    @Test
    public void getscore1() {
        assertTrue(ex3.get_score() == 0);
    }

    @Test
    public void getscore2() {
        Moves.swipe_left(ex8);
        assertTrue(ex8.get_score() == 36);
    }    

    @Test
    public void getlost1() {
        ex3.lost();
        assertTrue(ex3.get_lose());
    }    

    @Test
    public void getlost2() {
        ex8.lost();
        assertTrue(ex8.get_lose() == false);
    }    

    @Test
    public void getwin1() {
        Moves.swipe_left(ex5);
        assertTrue(ex5.get_win() == false);
    }    

    @Test
    public void getwin2() {
        Moves.swipe_left(ex3);
        assertTrue(ex3.get_win());
    }    

    // No tests for reset as it has the exact same functionality as declaring a new BoardT object

    // TESTS for MOVES

    @Test
    public void swipeleft1() {
        Moves.swipe_left(ex8);
        assertTrue(ex8.equal_board(new BoardT(new int[][] {{4, 0, 0, 0}, {2, 4, 0, 0}, {8, 4, 32, 0}, {0, 0, 0, 0}}), 1));
    }

    @Test
    public void swipeleft2() {
        Moves.swipe_left(ex3);
        assertTrue(ex3.equal_board(ex3, 0));
    }

    @Test
    public void swiperight1() {
        Moves.swipe_right(ex8);
        assertTrue(ex8.equal_board(new BoardT(new int[][] {{0, 0, 0, 4}, {0, 0, 2, 4}, {0, 8, 4, 32}, {0, 0, 0, 0}}), 1));
    }

    @Test
    public void swiperight2() {
        Moves.swipe_right(ex3);
        assertTrue(ex3.equal_board(ex3, 0));
    }

    @Test
    public void swipeup1() {
        Moves.swipe_up(ex9);
        assertTrue(ex9.equal_board(new BoardT(new int[][] {{2, 4, 16, 2}, {8, 8, 0, 4}, {0, 0, 0, 16}, {0, 0, 0, 0}}), 1));
    }

    @Test
    public void swipeup2() {
        Moves.swipe_up(ex3);
        assertTrue(ex3.equal_board(ex3, 0));
    }

    @Test
    public void swipedown1() {
        Moves.swipe_down(ex9);
        assertTrue(ex9.equal_board(new BoardT(new int[][] {{0, 0, 0, 0}, {0, 0, 0, 2}, {2, 4, 0, 4}, {8, 8, 16, 16}}), 1));
    }

    @Test
    public void swipedown2() {
        Moves.swipe_down(ex3);
        assertTrue(ex3.equal_board(ex3, 0));
    }
}