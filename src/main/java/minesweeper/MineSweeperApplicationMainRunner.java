package main.java.minesweeper;

import java.util.Scanner;

public class MineSweeperApplicationMainRunner{
    public static void main(String [] args){
        System.out.println("Welcome to Minesweeper!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
        int boardSize = scanner.nextInt();

        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
        int noOfMines = scanner.nextInt();

        Board board = new Board(boardSize, noOfMines);
        board.showBoard();


    }

}