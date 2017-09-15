import java.util.ArrayList;

public class Move {
	
	public Move(ArrayList<Tile> tiles, int startRow, int startCol, boolean across) {
		super();
		this.tiles = tiles;
		this.startRow = startRow;
		this.startCol = startCol;
		this.across = across;
	}

	ArrayList<Tile> tiles;
	int startRow;
	int startCol;
	boolean across;
	
	void execute(Tile[][] tileArr){
		int row = startRow;
		int col = startCol;
		
		for (Tile tile: tiles){
			tileArr[row][col] = tile;
			if (across){
				row++;
			} else {
				col++;
			}
		}
		
	}
}
