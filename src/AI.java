import java.util.ArrayList;

public class AI {
	
	int maxScore ;
	
	static String findHighestScoringWord(ArrayList<Tile> tiles, String currentWord){
		
		
		
		for (Tile startTile: tiles){
			
			
			if (Dictionary.trie.searchPrefix(currentWord + startTile.letter)){
				ArrayList<Tile> remainingTiles = tiles;
				remainingTiles.remove(startTile);
			}
			
		}
		
		return null;
	}
	
}
