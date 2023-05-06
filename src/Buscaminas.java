import java.util.Scanner;

public class Buscaminas {
    private static final char BOMB = 'B';
    private static final char UNCHECKED = '■';
    private static final char CHECKED = ' ';
    private static final int SIZE = 8;
    private static final int BOMBS = 10;
    private static char[][] board = new char[SIZE][SIZE];
    private static int[][] bombs = new int[BOMBS][2];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];
    private static boolean gameOver = false;

    public static void main(String[] args) {
        initializeBoard();
        placeBombs();
        playGame();
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = UNCHECKED;
                revealed[i][j] = false;
            }
        }
    }

    private static void placeBombs() {
        int bombsPlaced = 0;
        while (bombsPlaced < BOMBS) {
            int x = (int) (Math.random() * SIZE);
            int y = (int) (Math.random() * SIZE);
            if (board[x][y] != BOMB) {
                board[x][y] = BOMB;
                bombs[bombsPlaced][0] = x;
                bombs[bombsPlaced][1] = y;
                bombsPlaced++;
            }
        }
    }

    private static boolean hasWon() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] != BOMB && !revealed[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }
    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            printBoard();
            System.out.println("Enter row:");
            int row = scanner.nextInt() - 1;
            System.out.println("Enter column:");
            int col = scanner.nextInt() - 1;
            if (board[row][col] == BOMB) {
                printFinalBoard();
                System.out.println("Game over!");
                gameOver = true;
            } else {
                revealCell(row, col);
                if (hasWon()) {
                    System.out.println("Congratulations! You won!");
                    gameOver = true;
                }
            }
        }
    }

    private static void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else{
                    System.out.print(UNCHECKED + " ");
                }

            }
            System.out.println();
        }
    }

    private static void revealCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || revealed[row][col]) {
            return;
        }
        revealed[row][col] = true;
        if (board[row][col] != BOMB) {
            int count = 0;
            board[row][col] = CHECKED;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (row + i >= 0 && row + i < SIZE && col + j >= 0 && col + j < SIZE); }}}}

    private static void printFinalBoard() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == BOMB) {
                    System.out.print(BOMB);
                } else if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print(UNCHECKED + " ");
                }
            }
            System.out.println();
        }
    }

}