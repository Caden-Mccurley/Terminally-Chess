class Pawn extends Piece {
    public Pawn(String color) {
        super(color);
    }

	public boolean isValidPath(int xStart, int yStart, int xEnd, int yEnd) {
        if (this.color.equals("white")) {
            // Standard one-square forward for white pawn
            if (xStart == xEnd && yStart - yEnd == 1) {
                return true;
            }
            // Special two-square move from starting position for white pawn
            if (yStart == 6 && xStart == xEnd && yStart - yEnd == 2) {
                return true;
            }
        } else if (this.color.equals("black")) {
            // Standard one-square forward for black pawn
            if (xStart == xEnd && yEnd - yStart == 1) {
                return true;
            }
            // Special two-square move from starting position for black pawn
            if (yStart == 1 && xStart == xEnd && yEnd - yStart == 2) {
                return true;
            }
        }
        return false;
	}

    @Override
    public String toString() {
        if ("white".equals(this.color)) {
            return "\u2659"; // White pawn
        } else if ("black".equals(this.color)) {
            return "\u265F"; // Black pawn
        } else {
            return "P"; // Neutral/unknown color
        }
    }
}
