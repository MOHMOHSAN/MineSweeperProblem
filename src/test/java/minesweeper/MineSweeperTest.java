package minesweeper;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class MineSweeperTest {
    @Test
     public void testCells(){
        Board board = new Board(4,3);
        Cell[][] allCellsGrid = board.getAllCellsGrid();

        System.out.println("Test case1 : Checking cell grids are created properly");
        assertEquals(4,allCellsGrid[0].length);
        assertEquals(4,allCellsGrid[1].length);

        System.out.println("Test case2 : Checking cell grids should be display blank initially");
        assertEquals("_", allCellsGrid[0][0].toString());
        assertEquals("_", allCellsGrid[3][1].toString());

        System.out.println("Test case3 : Checking the cell grid is valid");
        assertTrue(board.isValidGrid(3, 3));
        assertFalse(board.isValidGrid(0, 4));
        assertFalse(board.isValidGrid(-1, 3));
    }

    @Test
    public void testMinesLogic(){
        Board board = new Board(4,3);
        Cell[][] allCellsGrid = board.getAllCellsGrid();
        int mineCount = 0;
        int adjacentMinesCount = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Cell currGrid = allCellsGrid[row][col];
                if (currGrid.isFlag()) {
                    mineCount++;
                    adjacentMinesCount = currGrid.getAdjacentMinesCount();
                }
            }
        }
        System.out.println("Test case4 : Checking number of mines are created properly");
        assertEquals(3, mineCount);

        System.out.println("Test case5 : Checking current grid has Mine, there is no adjacent count");
        assertEquals(-1, adjacentMinesCount);
    }

    @Test
    public void testMineSweeperLossAndWinStage() {
        Board board = new Board(4,3);
        ArrayList<ArrayList<Integer>> minesLoc = new ArrayList<>();
        ArrayList<ArrayList<Integer>> nonMinesLoc = new ArrayList<>();
        Cell[][] cellGrid = board.getAllCellsGrid();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Cell currGrid = cellGrid[row][col];
                ArrayList<Integer> currGridLoc = new ArrayList<>();
                currGridLoc.add(row);
                currGridLoc.add(col);
                if (currGrid.isFlag()) {
                    minesLoc.add(currGridLoc);
                } else {
                    nonMinesLoc.add(currGridLoc);
                }
            }
        }

        System.out.println("Test case6 : Test the ability to show the adjacent cells if current grid is not Mine");
        ArrayList <Integer> nonMinesGrid =  nonMinesLoc.get(0);
        assertTrue(board.recursiveAdjacentMinesResult(nonMinesGrid.get(0), nonMinesGrid.get(1)));

        System.out.println("Test case7 : Test that the game is not loss since no Mine is selected yet");
        assertFalse(board.isLoss());


        System.out.println("Test case8 : Test the ability not to show the adjacent cells if current grid is not Mine");
        ArrayList <Integer> mineGrid = minesLoc.get(0);
        assertFalse(board.recursiveAdjacentMinesResult(mineGrid.get(0), mineGrid.get(1)));


        System.out.println("Test case9 : Test that the game is lost since Mine Grid is chosen");
        assertFalse(board.isWin());
        assertTrue(board.isLoss());
    }



}
