import java.util.*;

public class VanishingTicTacToe {
    static char[][] board = new char[3][3];
    static Queue<int[]> movesQueue = new LinkedList<>();
    static int MAX_MOVES = 6; // Only last 6 moves stay

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeBoard();

        char currentPlayer = 'X';

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter row and column (0-2): ");
            int r = sc.nextInt();
            int c = sc.nextInt();

            if (r < 0 || r > 2 || c < 0 || c > 2 || board[r][c] != ' ') {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            // Place move
            board[r][c] = currentPlayer;
            movesQueue.add(new int[]{r, c});

            // Remove oldest move if limit exceeded
            if (movesQueue.size() > MAX_MOVES) {
                int[] oldMove = movesQueue.poll();
                board[oldMove[0]][oldMove[1]] = ' ';
            }

            // Check winner
            if (checkWinner(currentPlayer)) {
                printBoard();
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                break;
            }

            // Switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        sc.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    static void printBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    static boolean checkWinner(char p) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == p && board[i][1] == p && board[i][2] == p) ||
                (board[0][i] == p && board[1][i] == p && board[2][i] == p))
                return true;
        }

        // Diagonals
        if ((board[0][0] == p && board[1][1] == p && board[2][2] == p) ||
            (board[0][2] == p && board[1][1] == p && board[2][0] == p))
            return true;

        return false;
    }
}
