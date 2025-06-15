class Piece {
    protected String color;

    public Piece(String color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

	public boolean isValidPath(int xStart, int yStart, int xEnd, int yEnd, Piece endPiece) {
		return false;
	}

    @Override
    public String toString() {
        return "?"; 
    }
}