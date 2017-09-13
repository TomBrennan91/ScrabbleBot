import java.util.ArrayList;

public class LetterRack implements Constants{
	ArrayList<Tile> tiles;
	
	LetterRack(){
		tiles = new ArrayList<Tile>();
		refill();
	}
	
	public void refill(){
		while (tiles.size() < TILES_IN_RACK){
			tiles.add(TileBag.getInstance().takeOutTile());
		}
	}
	
	
}
