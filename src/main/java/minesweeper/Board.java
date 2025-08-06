package main.java.minesweeper;

import java.util.Objects;
import java.util.Random;

public class Board {
    private final int rows;
    private final int cols;
    private final int noOfMines;
    private final Cell[][] cellGrid;
    private final int [] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    private final int [] dy = {0, 0, 0, -1, -1, -1, 1, 1, 1};
    private boolean isLoss = false;

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

        //Testing to put Mine
        //cellGrid[0][1].setFlag(true);
        //cellGrid[1][1].setFlag(true);
        //cellGrid[2][0].setFlag(true);
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

    public void showBoard(String boardType){
        String boardTitle = Objects.equals(boardType, "updated") ? " updated " : " ";
        System.out.printf("Here is your%sminefield:\n", boardTitle);
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

    // To display hidden adjacent count based on user selected grid
    public boolean showAdjacentMinesResult(String selectedSquare){
        char [] c =  selectedSquare.toCharArray();

        int selectedRow = AlphabetConvertUtil.LetterToNumResult(c[0]);
        int selectedCol = c[1] - '0' - 1;

        return recursiveAdjacentMinesResult(selectedRow, selectedCol);
    }

    public boolean recursiveAdjacentMinesResult(int selectedRow, int selectedCol){
        // To exit recursive when there is no valid grid and all reveal
        if (!isValidGrid(selectedRow, selectedCol) || cellGrid[selectedRow][selectedCol].isRevealed()) return true;
        Cell currentCell = cellGrid[selectedRow][selectedCol];
        currentCell.setRevealed(true);

        // User selected Mines and game over without result display
        if(currentCell.isFlag()){
            isLoss = true;
            return false;
        }

        // If current cell is not near to Mines, it will recursively loop until there is Mine
        if(currentCell.getAdjacentMinesCount() == 0){
            for(int i=0; i < dx.length; i++){
                int xDirIndexForCurrGrid = selectedRow + dx[i];
                int yDirIndexForNextGrid = selectedCol + dy[i];
                recursiveAdjacentMinesResult(xDirIndexForCurrGrid, yDirIndexForNextGrid);
            }
        }
        return true;
    }

    // Return Loss field
    public boolean isLoss() { return isLoss; }

    // Return Win
    public boolean isWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!cellGrid[i][j].isFlag() && !cellGrid[i][j].isRevealed())
                    return false;
            }
        }
        return true;
    }

    // Print out Adjacent Number
    public void printCurrMinesCount(String selectedSquare){
        char [] c =  selectedSquare.toCharArray();
        int selectedRow = AlphabetConvertUtil.LetterToNumResult(c[0]);
        int selectedCol = c[1] - '0' - 1;
        int result = cellGrid[selectedRow][selectedCol].getAdjacentMinesCount();
        System.out.printf("This square contains %s adjacent mines.\n\n", result);
    }
}
