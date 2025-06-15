import java.util.*;
import java.lang.reflect.Constructor;

public class Board {
   Piece[][] board;
   public ArrayList<Piece> whiteCaptureArr = new ArrayList<Piece>();
   public ArrayList<Piece> blackCaptureArr = new ArrayList<Piece>();

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        board = new Piece[][]{
            {new Rook("black"), new Knight("black"), new Bishop("black"), new Queen("black"), new King("black"), new Bishop("black"), new Knight("black"), new Rook("black")},
            {new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black")},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white")},
            {new Rook("white"), new Knight("white"), new Bishop("white"), new Queen("white"), new King("white"), new Bishop("white"), new Knight("white"), new Rook("white")}
        };
    }

    private static final Map<String, Class<? extends Piece>> promotionMap = new HashMap<>();
    static {
        promotionMap.put("queen", Queen.class);
        promotionMap.put("rook", Rook.class);
        promotionMap.put("bishop", Bishop.class);
        promotionMap.put("knight", Knight.class);
    }

    public boolean move(char xStartChar, int yStart, char xEndChar, int yEnd, Scanner scanner) {
		int xStart = xStartChar - 'a';
        int xEnd = xEndChar - 'a';
		yStart = 8 - yStart;
		yEnd = 8 - yEnd;
		if (board[yEnd][xEnd] instanceof Piece) {
		    addCapture(board[yEnd][xEnd], board[yEnd][xEnd].color);
		}
        board[yEnd][xEnd] = board[yStart][xStart];
        board[yStart][xStart] = null;
        if (board[yEnd][xEnd] instanceof Pawn) {
            if ((board[yEnd][xEnd].color.equals("black") && yEnd == 7) ||
                    (board[yEnd][xEnd].color.equals("white") && yEnd == 0)) {

                String pieceColor = board[yEnd][xEnd].color; // Store color before pawn is replaced
                String promotionChoice = "";

                // Loop until user enters a valid choice from the map keys
                while (!promotionMap.containsKey(promotionChoice)) {
                    System.out.println("Enter piece to promote pawn to (Queen, Bishop, Knight, Rook): ");
                    promotionChoice = scanner.next().toLowerCase();
                    if (!promotionMap.containsKey(promotionChoice)) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }

                try {
                    // Create a new piece of the chosen class
                    Class<? extends Piece> promotedPieceClass = promotionMap.get(promotionChoice);
                    Constructor<? extends Piece> constructor = promotedPieceClass.getConstructor(String.class);
                    board[yEnd][xEnd] = constructor.newInstance(pieceColor); // Overwrite the pawn
                } catch (Exception e) {
                    // If it fails, default to a Queen
                    e.printStackTrace();
                    board[yEnd][xEnd] = new Queen(pieceColor);
                }
            }
        }
        return true;
    }


    
    public void addCapture(Piece p, String color) {
        if (color.equals("white")) {
            whiteCaptureArr.add(p);
        } else {
            blackCaptureArr.add(p);
        }
    }
    
    public boolean isMoveValid(char xStartChar, int yStart, char xEndChar, int yEnd, int turn) {
        int xStart = xStartChar - 'a';
        int xEnd = xEndChar - 'a';
		yStart = 8 - yStart;
		yEnd = 8 - yEnd;
		Piece startingPiece = board[yStart][xStart];
        Piece endingPiece = board[yEnd][xEnd];
        if (startingPiece == null) {
            System.out.println("Cannot move non-existent piece.");
            return false;
        }
        if (startingPiece.color.equals("white") && turn % 2 == 1 || startingPiece.color.equals("black") && turn % 2 == 0) {
            System.out.println("You cannot move piece of opposite color.");
            return false;
        }
	    if (xEnd < 0 || xEnd > 7 || yEnd < 0 || yEnd > 7) {
            System.out.println("Move out of bounds.");
            return false;
        }
        if (xStart == xEnd && yStart == yEnd) {
            System.out.println("Move cannot have same starting and end position.");
            return false;
        }
        if (endingPiece != null && startingPiece.color.equals(endingPiece.color)) {
            System.out.println("You cannot capture a same-color piece.");
            return false;
        } else {
            if (startingPiece.isValidPath(xStart, yStart, xEnd, yEnd, endingPiece)) {
                if (startingPiece instanceof Rook || startingPiece instanceof Bishop || startingPiece instanceof Queen || startingPiece instanceof Pawn) { // Rooks, Bishops, and Queens can have collisions in their paths (and Pawns when moving forward by two), so this code checks each square along the path for collisions.
                    // Find the direction that x and y take. This includes a 'magnitude' that we get rid of next.
                    int dx = xEnd - xStart;
                    int dy = yEnd - yStart;
                    // Creates a step variable that represents the step the path takes on x and y. This can either be 1, -1, or 0 for x and y.
                    int stepX = (dx == 0) ? 0 : (dx / Math.abs(dx));
                    int stepY = (dy == 0) ? 0 : (dy / Math.abs(dy));
                    // Sets the starting position to check for collisions as one step forward from starting position (otherwise the piece would collide on itself).
                    int currentX = xStart + stepX;
                    int currentY = yStart + stepY;
                    // Increments by one step on currentX and currentY (a step of 1, -1, or 0) until either the ending position is reached, or a piece is found, meaning there is a collision.
                    // Imagine a Rook going up 5 squares, with a Pawn directly before the final square it wants to end, an illegal move. We would find the direction of Y, which is 5, and the direction of X, which is 0.
                    // Then we would break this down into a step of 1 for Y, and a step of 0 for X. We step and check each piece along this path until we reach step 4, where our Pawn is, and throw a collision detection.
                    while (currentX != xEnd || currentY != yEnd) {
                        if (board[currentY][currentX] != null) {
                            System.out.println("Path blocked by other pieces.");
                            return false;
                        }
                        currentX += stepX;
                        currentY += stepY;
                    }
                }

                     return true;
            } else {
                System.out.println("Invalid path");
                return false;
            }
        }
    }

	public String toString() {
        StringBuilder result = new StringBuilder();
        
        // Add column labels at top
        result.append("    a   b   c   d   e   f   g   h\n");
        
        // Add top border
        result.append("  +---+---+---+---+---+---+---+---+\n");
        
        // Iterate through rows (8 to 1 for proper chess notation)
        for (int row = 0; row < 8; row++) {
            // Add row number and left border
            result.append((8 - row) + " |");
            
            // Add pieces for this row
            for (int col = 0; col < 8; col++) {
                String piece;
                if (board[row][col] == null) {
                    piece = " "; // Empty space for empty squares
                } else {
                    piece = board[row][col].toString();
                }
                
                // Center the piece in the cell with padding
                result.append(" " + piece + " ");
                
                // Add vertical separator
                result.append("|");
            }
            
            // Add row number at the end
            result.append(" " + (8 - row) + "\n");
            
            // Add horizontal separator (except for last row)
            if (row < 7) {
                result.append("  +---+---+---+---+---+---+---+---+\n");
            }
        }
        
        // Add bottom border
        result.append("  +---+---+---+---+---+---+---+---+\n");
        
        // Add column labels at bottom
        result.append("    a   b   c   d   e   f   g   h");
        
        return result.toString();
    }
}

