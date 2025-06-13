class Queen extends Piece {
    public Queen(String color) {
        super(color);
    }

    public boolean isValidPath(int xStart, int yStart, int xEnd, int yEnd) {
        if (Math.abs(xEnd - xStart) == Math.abs(yEnd - yStart) && xStart != xEnd) {
            return true;
        } else if (xStart == xEnd || yStart == yEnd) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if ("white".equals(this.color)) {
            return "\u2655"; // White queen
        } else if ("black".equals(this.color)) {
            return "\u265B"; // Black queen
        } else {
            return "Q"; // Neutral/unknown color
        }
    }
}