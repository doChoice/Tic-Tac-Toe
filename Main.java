 package tictactoe;
 import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code
        Scanner scanner = new Scanner(System.in);
        char[][] characters = new char[3][3];

        fillingTheMatrix(characters);
        matrixDisplay(characters);
        game(scanner, characters);
    }

    private static void fillingTheMatrix(char[][] characters) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                characters[i][j] = ' ';
            }
        }
    }

    private static void matrixDisplay(char[][] characters) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %c %c %c |\n", characters[i][0], characters[i][1],characters[i][2]);
        }
        System.out.println("---------");
    }

    private static String gameState(char[][] characters) {
        String state;
        int countX = 0;
        int countO = 0;

        boolean checkXWins = false;
        boolean checkOWins = false;

        for (int i = 0; i < 3; i++) {
            int countRow = 0;
            int countColumn = 0;
            int countMainDiagonal = 0;
            int countSecondaryDiagonal = 0;
            for (int j = 0; j < 3; j++) {
                if (characters[i][j] == 'X') {
                    countX++;
                } else if (characters[i][j] == 'O') {
                    countO++;
                }
                countRow += characters[i][j];
                countColumn += characters[j][i];
                countMainDiagonal += characters[j][j];
                countSecondaryDiagonal += characters[j][2 - j];
            }
            checkXWins = checkXWins || countRow == 264 || countColumn == 264 || countMainDiagonal == 264 || countSecondaryDiagonal == 264;
            checkOWins = checkOWins || countRow == 237 || countColumn == 237 || countMainDiagonal == 237 || countSecondaryDiagonal == 237;
        }

        if (checkOWins && checkXWins || countO - countX > 1 || countX - countO > 1) {
            state = "Impossible";
        } else if (checkOWins) {
            state = "O wins";
        } else if (checkXWins) {
            state = "X wins";
        } else if ((countX + countO) == 9) {
            state = "Draw";
        } else {
            state = "Game not finished";
        }

        return state;
    }

    private static void usersMoves(char letter, Scanner scanner, char[][] characters) {
        int i, j;
        int coordinateColumn;
        int coordinateRow;
        while (true) {
            System.out.print("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                i = scanner.nextInt();
                j = scanner.nextInt();
                if (i < 4 && j < 4) {
                    coordinateColumn = Math.abs(j - 3);
                    coordinateRow = Math.abs(i - 1);
                    if (characters[coordinateColumn][coordinateRow] == ' ') {
                        characters[coordinateColumn][coordinateRow] = letter;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
        }
    }

    private static void game(Scanner scanner, char[][] characters) {
        char letter;
        String stats;
        for (int i = 1; true; i++) {
            int k = i % 2;
            if (k != 0) {
                    letter = 'X';
            } else {
                letter = 'O';
            }
            usersMoves(letter, scanner, characters);
            matrixDisplay(characters);
            stats = gameState(characters);
            if (stats.equals("Draw") || stats.equals("O wins") || stats.equals("X wins")) {
                System.out.println(stats);
                break;
            }
        }
    }
}