package main.java.minesweeper;

import java.util.Random;

public class Board {
    private final int rows;
    private final int cols;
    private final int noOfMines;
    private final Cell[][] cellGrid;

    int [] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    int [] dy = {0, 0, 0, -1, -1, -1, 1, 1, 1};

    public Board(int boardSize, int noOfMines){
        this.rows = boardSize;
        this.cols = boardSize;
        this.noOfMines = noOfMines;
        this.cellGrid  = new Cell[boardSize][boardSize];
        createBoardWithMines();
    }

    private void createBoardWithMines(){
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                cellGrid[i][j] = new Cell();
            }
        }
        setMinesField();
        setAllAdjacentMinesCount();
    }

    // Generate radom cell grid index and set isFlag value to that cell to be reserved as Mine
    private void setMinesField(){
        Random rand = new Random();
        int successfullyPlantedMinesCount = 0;
        while(successfullyPlantedMinesCount < noOfMines) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);
            if(!cellGrid[row][col].isFlag()){
                cellGrid[row][col].setFlag(true);
                successfullyPlantedMinesCount++;
            }
        }
    }

    // To check if the current grid is valid
    private boolean isValidGrid(int rowIndex, int colIndex) {
        return rowIndex >= 0 && rowIndex < rows && colIndex >=0 && colIndex < cols;
    }

    // Get All mines count at the adjacent cells except on mine cell
    private void setAllAdjacentMinesCount(){
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                Cell currentCell = cellGrid[i][j];
                if(!currentCell.isFlag()){
                    int getAdjacentMinesCountForCurrGrid = countAdjacentMinesForCurrGrid(i,j);
                    cellGrid[i][j].setAdjacentMinesCount(getAdjacentMinesCountForCurrGrid);
                }
            }
        }
    }

    // To get the adjacentMines Count from eight direction based on current grid
    private int countAdjacentMinesForCurrGrid(int rowIndex, int colIndex){
        int count = 0;
        for(int i=0; i < dx.length; i++){
            int xDirIndexForCurrGrid = rowIndex + dx[i];
            int yDirIndexForNextGrid = colIndex + dy[i];

            //Check grid from eight direction is valid and has mine
            if(isValidGrid(xDirIndexForCurrGrid, yDirIndexForNextGrid) &&
                    cellGrid[xDirIndexForCurrGrid][yDirIndexForNextGrid].isFlag()
            ){
                count++;
            }
        }
        return count;
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
