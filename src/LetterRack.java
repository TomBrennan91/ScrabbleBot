import java.util.ArrayList;

public class LetterRack implements Constants{
	ArrayList<Tile> tiles;
	
	public void refill(){
		while (tiles.size() < TILES_IN_RACK){
			tiles.add(TileBag.getInstance().takeOutTile());
		}
	}
	
	
}
