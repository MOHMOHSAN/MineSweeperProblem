package minesweeper;

public class AlphabetConvertUtil {
    public static String numToLetterResult(int number) {
       if(number < 0 || number > 25){
           throw new IllegalArgumentException("Grid is out of available row size");
       }

       return String.valueOf((char) ('A' + number));
    }

    public static int LetterToNumResult(char ch) {
        if (ch < 'A' || ch > 'Z') {
            throw new IllegalArgumentException("Character must be between A and Z");
        }

        return ch - 'A';
    }
}
