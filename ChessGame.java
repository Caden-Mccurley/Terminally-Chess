import java.util.*;

public class ChessGame {
	private boolean endCondition = false;
	private int turn = 0;

	public ChessGame() {
		Scanner keyboard = new Scanner(System.in);
        Board mainBoard = new Board();

		while (!endCondition) {
			System.out.println(mainBoard);
			if (turn % 2 == 0) {
				System.out.println("Player 1: Enter your move (format: a 2 b 3):");

				char char1 = keyboard.next().charAt(0);
				int num1 = keyboard.nextInt();
				char char2 = keyboard.next().charAt(0);
				int num2 = keyboard.nextInt();

				if (mainBoard.isMoveValid(char1, num1, char2, num2)) {
					mainBoard.move(char1, num1, char2, num2);
					System.out.println("Board after move:");
					turn++;
				}


			}
			else {
				System.out.println("Player 2: Enter your move (format: a 2 b 3):");

				char char1 = keyboard.next().charAt(0);
				int num1 = keyboard.nextInt();
				char char2 = keyboard.next().charAt(0);
				int num2 = keyboard.nextInt();

				if (mainBoard.isMoveValid(char1, num1, char2, num2)) {
					mainBoard.move(char1, num1, char2, num2);
					System.out.println("Board after move:");
					turn++;
				}


			}

		}
	}
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
	}
}
