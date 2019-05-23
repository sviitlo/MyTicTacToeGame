import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static int index;
    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    static boolean canMakeMove() {
        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X') {
                if (board[i] != 'O') {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean hasUserWon() {
        for (int i = 0; i < 9; i += 3) {
            if ((board[i] == board[i + 1])
                    && (board[i + 1] == board[i + 2])
                    && (board[i] == 'O'))
                return true;
        }
        for (int i = 0; i < 3; i++) {
            if ((board[i] == board[i + 3])
                    && (board[i + 3] == board[i + 6])
                    && (board[i] == 'O'))
                return true;
        }
        if ((board[0] == board[4])
                && (board[4] == board[8])
                && (board[0] == 'O')) {
            return true;
        }
        if ((board[2] == board[4])
                && (board[4] == board[6])
                && (board[2] == 'O')) {
            return true;
        }
        return false;
    }

    static boolean hasComputerWon() {
        for (int i = 0; i < 9; i += 3) {
            if ((board[i] == board[i + 1])
                    && (board[i + 1] == board[i + 2])
                    && (board[i] == 'X'))
                return true;
        }
        for (int i = 0; i < 3; i++) {
            if ((board[i] == board[i + 3])
                    && (board[i + 3] == board[i + 6])
                    && (board[i] == 'X'))
                return true;
        }

        if ((board[0] == board[4])
                && (board[4] == board[8])
                && (board[0] == 'X')) {
            return true;
        }

        if ((board[2] == board[4])
                && (board[4] == board[6])
                && (board[2] == 'X')) {
            return true;
        }
        return false;
    }

    static void drawBoard() {

        System.out.println(board[0] + "|" + board[1] + "|" + board[2]);
        System.out.println("-----");
        System.out.println(board[3] + "|" + board[4] + "|" + board[5]);
        System.out.println("-----");
        System.out.println(board[6] + "|" + board[7] + "|" + board[8]);
        System.out.println();

    }


    // algorithm from wikipedia
    static int minimax(boolean flag) {

        int max_val = -1000, min_val = 1000;
        int i, j, value = 1;
        if (hasComputerWon()) {
            return 10;
        } else if (hasUserWon()) {
            return -10;
        } else if (!canMakeMove()) {
            return 0;
        }
        int[] score = {1, 1, 1, 1, 1, 1, 1, 1, 1};

        for (i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                if (min_val > max_val)
                {
                    if (flag) {
                        board[i] = 'X';
                        value = minimax(false);
                    } else {
                        board[i] = 'O';
                        value = minimax(true);
                    }
                    board[i] = ' ';
                    score[i] = value;
                }
            }
        }

        if (flag) {
            max_val = -1000;
            for (j = 0; j < 9; j++) {
                if (score[j] > max_val && score[j] != 1) {
                    max_val = score[j];
                    index = j;
                }
            }
            return max_val;
        }
        if (!flag) {
            min_val = 1000;
            for (j = 0; j < 9; j++) {
                if (score[j] < min_val && score[j] != 1) {
                    min_val = score[j];
                    index = j;
                }
            }
            return min_val;
        }

        return 0;
    }

    static void askUserToEnterTheMove() {
        System.out.println("Your move:");
        int move = getInputFromUser();
        if (board[move - 1] == ' ') {
            board[move - 1] = 'O';
            drawBoard();
        } else {
            System.out.println("Invalid move, try again");
            askUserToEnterTheMove();
        }
    }


    static int getInputFromUser() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }

    public static void main(String... args) {
        boolean userFirstMove = new Random().nextBoolean();

        if (userFirstMove) {
            drawBoard();
            askUserToEnterTheMove();
        }

        while (true) {

            System.out.println("Computer move....");
            minimax(true);
            board[index] = 'X';
            drawBoard();
            if (hasComputerWon()) {
                System.out.println("Computer won.....");
                break;
            }
            if (!canMakeMove()) {
                System.out.println("Draw....");
                break;
            }
            askUserToEnterTheMove();
            if (hasUserWon()) {
                System.out.println("You Won......");
                break;
            }
            if (!canMakeMove()) {
                System.out.println("Draw....");
                break;
            }
        }
    }
}
