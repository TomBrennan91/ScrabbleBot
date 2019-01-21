package scrabble;

import java.util.ArrayList;
import java.util.Random;

public class TileBag {
	ArrayList<Tile> TileSet;
	
	private static final TileBag instance;
	static{
		instance = new TileBag();
	}
	
	public static TileBag getInstance(){
		return instance;
	}
	
	TileBag(){

		
		TileSet = new ArrayList<Tile>();
		AddMultipleTiles('A',1,9);
		AddMultipleTiles('B',3,2);
		AddMultipleTiles('C',3,2);
		AddMultipleTiles('D',2,4);
		AddMultipleTiles('E',1,12);
		AddMultipleTiles('F',4,2);
		AddMultipleTiles('G',2,3);
		AddMultipleTiles('H',4,2);
		AddMultipleTiles('I',1,9);
		AddMultipleTiles('J',8,1);
		AddMultipleTiles('K',5,1);
		AddMultipleTiles('L',1,4);
		AddMultipleTiles('M',3,2);
		AddMultipleTiles('N',1,6);
		AddMultipleTiles('O',1,8);
		AddMultipleTiles('P',3,2);
		AddMultipleTiles('Q',10,1);
		AddMultipleTiles('R',1,6);
		AddMultipleTiles('S',1,4);
		AddMultipleTiles('T',1,6);
		AddMultipleTiles('U',1,4);
		AddMultipleTiles('V',4,2);
		AddMultipleTiles('W',4,2);
		AddMultipleTiles('X',8,1);
		AddMultipleTiles('Y',4,2);
		AddMultipleTiles('Z',10,1);
	}
	
	public Tile takeOutTile(){
		if (isEmpty()) return null;
		Random rnd = new Random();
		int idx = rnd.nextInt(TileSet.size());
		Tile tile = TileSet.get(idx);
		TileSet.remove(idx);
		Scrabble.lettersInBag.setText(TileSet.size() + " tiles left");
		return tile;
	}
	
	void AddMultipleTiles(char letter, int points ,int quantity){
		for (int i = 0 ; i < quantity ; i++){
			TileSet.add(new Tile(letter, points));
		}
	}
	
	boolean isEmpty(){
		return (TileSet.size() == 0);
	}
	
}
