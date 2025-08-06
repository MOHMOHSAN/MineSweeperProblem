package main.java.minesweeper;

import java.util.Scanner;

public class MineSweeperApplicationMainRunner{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        boolean isReplay = true;
        while(isReplay) {
            playMineSweeper(scanner);

            System.out.print("Press any key to play again...");
            String response = scanner.next().trim().toLowerCase();
            isReplay = !response.equalsIgnoreCase("q");
        }

        scanner.close();
    }

    public static void playMineSweeper(Scanner scanner) {
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
        int boardSize = scanner.nextInt();

        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
        int noOfMines = scanner.nextInt();

        Board board = new Board(boardSize, noOfMines);
        boolean isInitBoardDisplay = true;

        while(!board.isLoss() && !board.isWin()) {
            if(isInitBoardDisplay) {
                board.showBoard("");
            } else{
                board.showBoard("updated");
            }
            isInitBoardDisplay = false;

            System.out.print("\nSelect a square to reveal (e.g. A1): ");
            String selectedSquare = scanner.next();

            board.printCurrMinesCount(selectedSquare);
            boolean showAdjacentResult = board.showAdjacentMinesResult(selectedSquare);

            if(!showAdjacentResult){
                System.out.println("Oh no, you detonated a mine! Game over.");
                break;
            }

            if (board.isWin()) {
                board.showBoard("updated");
                System.out.println("Congratulations, you have won the game!");
                break;
            }
        }
    }
}