package scrabble;//I define an anchor as a tile on the board from which a new word could be 'anchored' as a move.
//in this primitive definition, it must be possible to add a word while only adding a single word to an anchor

import scrabble.Tile;

public class Anchor {
	
	int row;
	int col;
	Tile anchorTile;
	int prefixCap;
	int postfixCap;
	boolean across;
	
	public Anchor(int row, int col, Tile anchorTile, int prefixCap, int postfixCap, boolean across) {
		super();
		this.row = row;
		this.col = col;
		this.anchorTile = anchorTile;
		this.prefixCap = prefixCap;
		this.postfixCap = postfixCap;
		this.across = across;
	}
	
	@Override
	public String toString() {
		return "Anchor [" + row + "," + col + "] " + anchorTile.letter + ", pre: " + prefixCap
				+ ", post: " + postfixCap + ", across=" + across + "]";
	}

	
}
