package main.java.minesweeper;

public class Board {
    private final int rows;
    private final int cols;
    private final int noOfMines;
    private final Cell[][] cellGrid;

    public Board(int boardSize, int noOfMines){
        this.rows = boardSize;
        this.cols = boardSize;
        this.noOfMines = noOfMines;
        this.cellGrid  = new Cell[boardSize][boardSize];
        createBoardWithMines();
    }

    public void createBoardWithMines(){
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                cellGrid[i][j] = new Cell();
            }
        }
    }

    public void showBoard(){
        System.out.println("Here is your minefield:");
        showXAxisTitle();

        for (int i = 0; i < rows; i++) {
            System.out.print(AlphabetConvertUtil.numToLetterResult(i) + "  ");
            for (int j = 0; j < cols; j++) {
                System.out.print(cellGrid[i][j] + " ");
            }
            System.out.println();
        }

    }

    private void showXAxisTitle(){
        System.out.print("   ");
        for (int x = 0; x < rows; x++) {
            System.out.print((x+1) + " ");
        }
        System.out.println();
    }

}
