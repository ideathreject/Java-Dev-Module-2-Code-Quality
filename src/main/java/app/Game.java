package app;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Scanner scan = new Scanner(System.in);
    private final char[] box = new char[9];
    private final Random random = new Random();

    public void start() {
        Arrays.fill(box, ' ');
        int winner = 0;

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            printBoard();

            if (winner != 0) {
                printGameOverMessage(winner);
                break;
            }

            playerMove();

            if (isWinner('X')) {
                winner = 1;
            } else if (isDraw()) {
                winner = 3;
            } else {
                computerMove();

                if (isWinner('O')) {
                    winner = 2;
                }
            }
        }
    }

    private void printBoard() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private void playerMove() {
        while (true) {
            byte input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private void computerMove() {
        while (true) {
            byte rand = (byte) (random.nextInt(9) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private boolean isWinner(char symbol) {
        return (box[0] == symbol && box[1] == symbol && box[2] == symbol) ||
                (box[3] == symbol && box[4] == symbol && box[5] == symbol) ||
                (box[6] == symbol && box[7] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[3] == symbol && box[6] == symbol) ||
                (box[1] == symbol && box[4] == symbol && box[7] == symbol) ||
                (box[2] == symbol && box[5] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[4] == symbol && box[8] == symbol) ||
                (box[2] == symbol && box[4] == symbol && box[6] == symbol);
    }

    private boolean isDraw() {
        for (char c : box) {
            if (c != 'X' && c != 'O') {
                return false;
            }
        }
        return true;
    }

    private void printGameOverMessage(int winner) {
        switch (winner) {
            case 1 -> System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case 2 -> System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case 3 -> System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            default -> System.out.println("Unexpected game status.");
        }
        System.exit(0);
    }

}
