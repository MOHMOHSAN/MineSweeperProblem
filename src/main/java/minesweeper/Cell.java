package main.java.minesweeper;

public class Cell {
    private boolean isFlag = false;
    private int adjacentMinesCount = 0;

    // Getter and Setter for isFlag
    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        this.isFlag = flag;
    }

    // Getter and Setter for adjacentMinesCount
    public int getAdjacentMinesCount() {
        return adjacentMinesCount;
    }

    public void setAdjacentMinesCount(int adjacentMinesCount) {
        this.adjacentMinesCount = adjacentMinesCount;
    }

    @Override
    public String toString(){
        if(isFlag){ return "f"; }
        if(adjacentMinesCount != 0){ return adjacentMinesCount+""; }
        return "_";
    }
}
