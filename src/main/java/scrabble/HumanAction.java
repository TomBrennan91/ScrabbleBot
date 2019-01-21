package scrabble;


class HumanAction{
    public HumanAction(Tile movedTile, int row, int col) {
        this.movedTile = movedTile;
        this.row = row;
        this.col = col;
    }
    @Override
    public String toString() {
        return "HumanAction [movedTile=" + movedTile + ", row=" + row + ", col=" + col + "]";
    }

    Tile movedTile;
    int row;
    int col;
}