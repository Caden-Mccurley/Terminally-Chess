class Bishop extends Piece {
    public Bishop(String color) {
        super(color);
    }

	public boolean isValidPath(int xStart, int yStart, int xEnd, int yEnd, Piece endPiece) {
		if (Math.abs(xEnd - xStart) == Math.abs(yEnd - yStart) && xStart != xEnd) {
            return true;
        }
        return false;
	}

    @Override
    public String toString() {
        if ("white".equals(this.color)) {
            return "\u2657"; // White bishop
        } else if ("black".equals(this.color)) {
            return "\u265D"; // Black bishop
        } else {
            return "B"; // Neutral/unknown color
        }
    }
}