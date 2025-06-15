class Knight extends Piece {
    public Knight(String color) {
        super(color);
    }

	public boolean isValidPath(int xStart, int yStart, int xEnd, int yEnd, Piece endPiece) {
		if (Math.abs(xStart - xEnd) == 1 && Math.abs(yStart - yEnd) == 2 || Math.abs(xStart - xEnd) == 2 && Math.abs(yStart - yEnd) == 1) {
            return true;
        }
        return false;
	}

    @Override
    public String toString() {
        if ("white".equals(this.color)) {
            return "\u2658"; // White knight
        } else if ("black".equals(this.color)) {
            return "\u265E"; // Black knight
        } else {
            return "N"; // Neutral/unknown color
        }
    }
}