### Prerequisite
Ensure that out/main/java folder is existed from root directory to put java compiled class
cd MineSweeperProblem
- mkdir out\main
- mkdir out\test

### To Compile and Run the program
Step1 : Go To root directory 
- cd MineSweeperProblem

Step2 : Compile all java files and put it in out directory (Note: out folder should exist)
- javac -d out/main src/main/java/minesweeper/*.java

Step3 : Run the Runner file
- java -cp out/main minesweeper.MineSweeperApplicationMainRunner

### To Compile and Run the test
Step1 : At the root directory compiles test files together with Java files and lib
- javac -cp "lib/*;out/main" -d out/test src/test/java/minesweeper/MineSweeperTest.java

Step2 : Run the test file
- java -jar lib/junit-platform-console-standalone-1.10.2.jar -cp "out/main;out/test" --scan-class-path

### Future Implementation 
1. Need to valid grid size input (eg. Row index is more than Z letter or enter invalid type rather than integer)
2. Need to Handle exception throw from selectedGrid input if user enter invalid grid index
3. Need test cases to check user inputs





