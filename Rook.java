class Rook extends Piece {
    public Rook(String color) {
        super(color);
    }

	public boolean isValidPath(int xStart, int yStart, int xEnd, int yEnd) {
		if (xStart == xEnd || yStart == yEnd) {
            return true;
        }
        return false;
	}

    @Override
    public String toString() {
        if ("white".equals(this.color)) {
            return "\u2656"; // White rook
        } else if ("black".equals(this.color)) {
            return "\u265C"; // Black rook
        } else {
            return "R"; // Neutral/unknown color
        }
    }
}