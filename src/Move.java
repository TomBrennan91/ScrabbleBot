import java.util.ArrayList;

public class Move {
	
	public Move(ArrayList<Tile> tiles, int startRow, int startCol, boolean across, int score, Player player) {
		this.tiles = tiles;
		this.startRow = startRow;
		this.startCol = startCol;
		this.across = across;
		this.score = score;
		this.player = player;
		this.anchor = null;
		//TODO: try to remember how to chain constructors
	}
	
	
	public Move(ArrayList<Tile> tiles, int startRow, int startCol, boolean across, int score, Player player, Anchor anchor) {
		
		this.tiles = tiles;
		this.startRow = startRow;
		this.startCol = startCol;
		this.across = across;
		this.score = score;
		this.player = player;
		this.anchor = anchor;
	}

	@Override
	public String toString() {
		StringBuilder word = new StringBuilder();
		for (Tile tile: tiles){
			word.append(tile.letter);
		}
		return player.name + " places '" + word.toString() + "' for " + score + " points \n";
	}

	ArrayList<Tile> tiles;
	int startRow;
	int startCol;
	boolean across;
	int score;
	Player player;
	Anchor anchor;
	
	void execute(Tile[][] tileArr){
		
		System.out.println(toString());
		
		int row = startRow;
		int col = startCol;
		
		for (Tile tile: tiles){
			tileArr[row][col] = tile;
			if (across){
				col++;
			} else {
				row++;
			}
			player.letterRack.tiles.remove(tile);
		}
		player.awardPoints(score);
		
		player.getMoreTiles();
		Scrabble.log.append(this.toString());
		
	}
	
	
}
