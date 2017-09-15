import java.util.ArrayList;

public class AI {
	
	public AI(Player bot) {
		this.bot = bot;
	}

	Player bot;
	int maxScore ;
	ArrayList<Tile> bestWord;
	
	
	void makeFirstMove(ArrayList<Tile> tiles){
		bestWord = new ArrayList<Tile>();
		findHighestScoringWord( tiles, bestWord, "", 0);
		Move move = new Move(bestWord ,  7, 7 - (bestWord.size() / 2), true, maxScore, bot);
		move.execute(Board.getInstance().tileArr);
		System.out.println(move.toString());
		Board.getInstance().reDraw();
		Board.getInstance().print();
	}
	
	void  findHighestScoringWord(ArrayList<Tile> inputTiles, ArrayList<Tile> tilesToBeUsed, String currentWord, int score){
		for (int tileNo = 0 ; tileNo < inputTiles.size() ; tileNo++){
			Tile curTile = inputTiles.get(tileNo);
			////System.out.println(currentWord + curTile.letter);
			if (Dictionary.trie.searchPrefix(currentWord + curTile.letter)){
				//System.out.println(currentWord + curTile.letter + " is prefix");
				ArrayList<Tile> remainingTiles = new ArrayList<Tile>( inputTiles);
				ArrayList<Tile> tilesInWord = new ArrayList<Tile>(tilesToBeUsed);
				remainingTiles.remove(tileNo);
				tilesInWord.add(curTile);
				findHighestScoringWord(remainingTiles, tilesInWord ,currentWord  + curTile.letter, score + curTile.points );
			
				if (Dictionary.trie.searchWord(currentWord + curTile.letter)){
					if (maxScore <= score + curTile.points){
						maxScore =  score + curTile.points;
						System.out.println(currentWord + curTile.letter + " is word" + "[" + (score + curTile.points) + "]");
						bestWord = tilesInWord;
					}	
				}
			}
		}
	}
	
	ArrayList<Anchor> findAnchors(){
		ArrayList<Anchor> anchors = new ArrayList<Anchor>();
		Tile[][] tileArr =  Board.getInstance().tileArr;
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				if (tileArr[row][col].letter != ' '){
					anchors.add(new Anchor(row, col, tileArr[row][col], 0, 0, false));
				}
			}
		}
		return anchors;
	}
	
}
