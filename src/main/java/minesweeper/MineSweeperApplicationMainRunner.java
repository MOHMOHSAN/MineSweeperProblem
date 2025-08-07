package minesweeper;

import java.util.Scanner;

public class MineSweeperApplicationMainRunner{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        boolean isReplay = true;
        while(isReplay) {
            playMineSweeper(scanner);

            System.out.print("Press any key to play again or q to quit...");
            String response = scanner.next().trim().toLowerCase();
            isReplay = !response.equalsIgnoreCase("q");
        }

        scanner.close();
    }

    public static void playMineSweeper(Scanner scanner) {
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
        int boardSize = scanner.nextInt();

        int noOfMines = getMinesInput(scanner, boardSize);

        Board board = new Board(boardSize, noOfMines);
//        board.generateBoard();
//        board.setMinesField();
//        board.setAllAdjacentMinesCount();
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

    public static int getMinesInput(Scanner scanner, int gridSize) {
        int maxMinesCount = (int) Math.floor(gridSize * gridSize * 0.35);
        int selectedMinesCount;
        while(true) {
            System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
            String input = scanner.next();

            try{
                selectedMinesCount = Integer.parseInt(input);
                if(selectedMinesCount <= 0){
                    System.out.println("Number of Mines should not be zero");
                } else if(selectedMinesCount > maxMinesCount){
                    System.out.println("Exceed max Mine amount");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return selectedMinesCount;
    }
}