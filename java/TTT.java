import java.util.Scanner; // Standard I/O

public class TTT {
    // This is the entry point of the program
    // The JVM (Java Virtual Machine) looks for this exact line to start execution
    public static void main(String[] args) {
        char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char currentPlayer = 'X';
        boolean gameRunning = true;
        Scanner input = new Scanner(System.in); // To read keyboard data

        while(gameRunning) {
            displayBoard(board);
            print("Player " + currentPlayer + ", pick a free spot: ");

            int choice = input.nextInt() - 1; // (0-8) positions

            if(valid_choice(board, choice)) {
                board[choice] = currentPlayer;

                if (checkWinner(board, currentPlayer)) {
                    displayBoard(board);
                    println("Player " + currentPlayer + " won!");
                    gameRunning = false;
                } else if (isBoardFull(board)) {
                    displayBoard(board);
                    println("Game Draw!");
                    gameRunning = false;
                } else currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else println("Invalid move!");
        }
        input.close();
    }

    // In Java, we don't need the prototypes at the top like in C/C++
    // The compiler reads the whole class before starting.

    // Both of these functions make our lives easier
    public static void println(Object message){
        System.out.println(message);
    }
    public static void print(Object message){
        System.out.print(message);
    }

    public static boolean valid_choice(char[] board, int choice){
        if (choice >= 0 && choice <= 8 && board[choice] != 'X' && board[choice] != 'O') return true;
        return false; 
    }

    public static boolean checkWinner(char[] board, char player){
        int [][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] pattern : winPatterns){
            if (board[pattern[0]] == player && board[pattern[1]] == player && board[pattern[2]] == player) return true;
        }
        return false;
    }

    public static boolean isBoardFull(char[] board){
        for (char i : board) {
            if (i != 'X' && i != 'O') return false;
        }
        return true;
    }

    public static void displayBoard(char[] board){
        clearScreen();
        println("\n " + board[0] + " | " + board[1] + " | " + board[2]);
        println("-----------");
        println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        println("-----------");
        println(" " + board[6] + " | " + board[7] + " | " + board[8] + "\n");
    }

    public static void clearScreen() {
        try {
            if(System.getProperty("os.name").contains("windows")) {
                // Runs the 'cls' command inside the Windows cmd shell
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Runs the 'clear command on Unix/Mac/Linux'
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // If it fails, just print new lines as a fallback
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}
