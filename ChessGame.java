import java.util.*;

public class ChessGame {
	private boolean endCondition = false;
	private int turn = 0;

	public ChessGame() {
		Scanner keyboard = new Scanner(System.in);
        Board mainBoard = new Board();

		while (!endCondition) {
			System.out.println("Black's Captured Pieces: " + mainBoard.whiteCaptureArr);
			System.out.println(mainBoard);
			System.out.println("White's Captured Pieces: " + mainBoard.blackCaptureArr);
			// We will assume that even turns are white, and odd turns are black.
			System.out.println("Player " + (turn % 2 + 1) + ": Enter your move (format: a 2 b 3):");

				char char1 = keyboard.next().charAt(0);
				int num1 = keyboard.nextInt();
				char char2 = keyboard.next().charAt(0);
				int num2 = keyboard.nextInt();

				if (mainBoard.isMoveValid(char1, num1, char2, num2, turn)) {
					mainBoard.move(char1, num1, char2, num2, keyboard);
					System.out.println("Board after move:");
					turn++;



			}

		}
	}
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
	}
}
