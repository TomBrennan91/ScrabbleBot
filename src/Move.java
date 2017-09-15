import java.util.ArrayList;

public class Move {
	
	public Move(ArrayList<Tile> tiles, int startRow, int startCol, boolean across, int score) {
		super();
		this.tiles = tiles;
		this.startRow = startRow;
		this.startCol = startCol;
		this.across = across;
		this.score = score;
	}

	@Override
	public String toString() {
		StringBuilder word = new StringBuilder();
		for (Tile tile: tiles){
			word.append(tile.letter);
		}
		return "word=" + word.toString() + ", startRow=" + startRow + ", startCol=" + startCol + ", across=" + across + ", for " + score + " points";
	}

	ArrayList<Tile> tiles;
	int startRow;
	int startCol;
	boolean across;
	int score;
	
	void execute(Tile[][] tileArr){
		int row = startRow;
		int col = startCol;
		
		for (Tile tile: tiles){
			tileArr[row][col] = tile;
			if (across){
				col++;
			} else {
				row++;
			}
		}
	}
}
