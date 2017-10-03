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
			Tile newTile = TileBag.getInstance().takeOutTile();
			if (newTile == null){
				return;
			}
			tiles.add(newTile);
		}
		//System.out.println("bot has tiles:" + tiles.toString());
	}
	
	public void SwapTiles(){
		for (int i = 0 ; i < tiles.size() ; i++){
			Tile tile =tiles.get(i) ; 
			if (tile != null){
				TileBag.getInstance().TileSet.add(tile);
				//tilePanel.remove(tiles.get(0).icon);
			}
		}
		tiles.clear();
		
		refill();
		//readTiles();	
	}
	
	
	public void ShuffleTiles(){
		Collections.shuffle(tiles);
	}
	
}
