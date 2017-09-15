import java.util.ArrayList;

public class AI {
	
	int maxScore ;
	String bestWord;
	
	void makeFirstMove(ArrayList<Tile> tiles){
		
		findHighestScoringWord( tiles, "", 0);
		
		
		
		Move move = new Move(getTilesToBeUsedInMove(tiles), 7, 6, true);
		move.execute(Board.getInstance().tileArr);
		
	}
	
	ArrayList<Tile> getTilesToBeUsedInMove(ArrayList<Tile> inputTiles){
		ArrayList<Tile> tilesToBeUsedInMove  = new ArrayList<Tile>();
		
		for (char c: bestWord.toCharArray()){
			tilesToBeUsedInMove.add(inputTiles.))
		}
		
		return null;
	}
	

	void  findHighestScoringWord(ArrayList<Tile> inputTiles, ArrayList<Tile> tilesToBeUsed, String currentWord, int score){

		
		for (int tileNo = 0 ; tileNo < inputTiles.size() ; tileNo++){
			Tile curTile = inputTiles.get(tileNo);
			////System.out.println(currentWord + curTile.letter);
			if (Dictionary.trie.searchPrefix(currentWord + curTile.letter)){
				//System.out.println(currentWord + curTile.letter + " is prefix");
				ArrayList<Tile> remainingTiles = new ArrayList<Tile>( inputTiles);
				remainingTiles.remove(tileNo);
				findHighestScoringWord(remainingTiles, null ,currentWord  + curTile.letter, score + curTile.points );
			
				if (Dictionary.trie.searchWord(currentWord + curTile.letter)){
					if (maxScore < score + curTile.points){
						maxScore =  score + curTile.points;
						System.out.println(currentWord + curTile.letter + " is word" + "[" + (score + curTile.points) + "]");
						bestWord = currentWord + curTile.letter;
					}	
				}
			}
		}
	}
	
}
