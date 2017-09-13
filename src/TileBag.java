import java.util.ArrayList;

public class TileBag {
	ArrayList<Tile> TileSet;
	
	TileBag(){
		AddMultipleTiles('A',1,9);
		AddMultipleTiles('B',3,2);
		AddMultipleTiles('C',3,2);
		AddMultipleTiles('D',2,4);
		AddMultipleTiles('E',1,12);
		AddMultipleTiles('F',4,12);
		AddMultipleTiles('G',2,3);
		
		AddMultipleTiles('I',1,9);
		
		AddMultipleTiles('L',1,4);
		
		AddMultipleTiles('N',1,6);
		AddMultipleTiles('M',3,2);
		AddMultipleTiles('O',1,8);
		AddMultipleTiles('P',3,2);
		
		AddMultipleTiles('R',1,6);
		AddMultipleTiles('S',1,4);
		AddMultipleTiles('T',1,6);
		
		AddMultipleTiles('U',1,4);
		
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
