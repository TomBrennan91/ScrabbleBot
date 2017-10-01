import java.util.ArrayList;
import java.util.Collections;

public class LetterRack implements Constants{
	ArrayList<Tile> tiles;
	Player owner;
	LetterRack(Player owner){
		this.owner = owner;
		tiles = new ArrayList<Tile>();
		refill();
	}

	
	public void readTiles(){
		for (Tile tile : tiles ){
			if (tile != null){
				System.out.println(tile.toString());
			}
		}
	}
	
	public void refill(){
		while (tiles.size() < TILES_IN_RACK){
			tiles.add(TileBag.getInstance().takeOutTile());
		}
		//System.out.println("bot has tiles:" + tiles.toString());
	}
	
	public void SwapTiles(){
		for (int i = 0 ; i < TILES_IN_RACK ; i++){
			Tile tile =tiles.get(0) ; 
			if (tile != null){
				TileBag.getInstance().TileSet.add(tile);
				//tilePanel.remove(tiles.get(0).icon);
			}
			tiles.remove(0);
		}
		refill();
		//readTiles();	
	}
	
	
	public void ShuffleTiles(){
		Collections.shuffle(tiles);
	}
	
}
