package scrabble;

class Bonus {
    int row;
    int col;

    public boolean equals(Bonus other) {
        return (row == other.row && col == other.col);
    }

    public Bonus(int row, int col) {
        this.row = row;
        this.col = col;
    }
}