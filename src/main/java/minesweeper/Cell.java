package main.java.minesweeper;

public class Cell {
    private boolean isFlag = false;
    private int adjacentMinesCount = 0;
    private boolean isRevealed = false;

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

    // Getter and Setter for isRevealed
    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        this.isRevealed = revealed;
    }

    @Override
    public String toString(){
        if(!isRevealed) return "_";
        // if(isFlag){ return "Bomb"; }
        return adjacentMinesCount + "";
    }
}
