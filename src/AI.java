import java.util.ArrayList;

public class AI implements Constants {
	
	Player bot;
	int maxScore ;
	ArrayList<Tile> bestWord;
	Anchor currentAnchor;
	public AI(Player bot) {
		this.bot = bot;
	}

	void makeFirstMove(ArrayList<Tile> tiles){
		bestWord = new ArrayList<Tile>();
		getStartingWord( tiles, bestWord, "", 0);
		Move move = new Move(bestWord , 7 , 7 - (bestWord.size() / 2) , true , maxScore , bot);
		move.execute(Board.getInstance().tileArr);
		System.out.println(move.toString());
		Board.getInstance().reDraw();
		//Board.getInstance().print();
	}
	
	void makeSubsequentMove(){
		maxScore = 0;
		bestWord = new ArrayList<Tile>();
		for (Anchor anchor : findAnchors()){
			ArrayList<Tile> inputTiles = new ArrayList<Tile>(bot.letterRack.tiles);
			inputTiles.add(anchor.anchorTile);
			findHighestScoringWord(inputTiles, new ArrayList<Tile>(), "", 0, anchor);
		}
		
		int startCol;
		int startRow;
		
		if (currentAnchor.across){
			startCol = currentAnchor.col - getAnchorPosition(currentAnchor, bestWord);
			startRow = currentAnchor.row;
		} else {
			startCol = currentAnchor.col;
			startRow = currentAnchor.row - getAnchorPosition(currentAnchor, bestWord);
		}
		
		Move move = new Move(bestWord , startRow , startCol ,currentAnchor.across, maxScore , bot);
		move.execute(Board.getInstance().tileArr);
		System.out.println(move.toString());
		Board.getInstance().reDraw();
	}
	
	private int getAnchorPosition(Anchor anchor, ArrayList<Tile> word){
		//ToDo: allow for case of multiple anchor positions
		for (int c = 0 ; c < word.size() ; c++){
			if (word.get(c).letter == anchor.anchorTile.letter){
				return c;
			}
		}
		return -1000;
	}
	
	private boolean fitsOnBoard(Anchor anchor, ArrayList<Tile> word){
	//check if word would cause spilling off the edge of the board
		int anchorPos = getAnchorPosition(anchor, word);
		int prefixLength = anchorPos;
		int posfixLength = word.size() - anchorPos;
		
		if (anchor.prefixCap >= prefixLength && anchor.postfixCap >= posfixLength){
			return true;
		} else {
			return false;
		}
			
	}
	
	private void  findHighestScoringWord(ArrayList<Tile> inputTiles, ArrayList<Tile> tilesToBeUsed, String currentWord, int score, Anchor anchor){
		for (int tileNo = 0 ; tileNo < inputTiles.size() ; tileNo++){
			Tile curTile = inputTiles.get(tileNo);
			////System.out.println(currentWord + curTile.letter);
			if (Dictionary.trie.searchPrefix(currentWord + curTile.letter)){
				//System.out.println(currentWord + curTile.letter + " is prefix");
				ArrayList<Tile> remainingTiles = new ArrayList<Tile>( inputTiles);
				ArrayList<Tile> tilesInWord = new ArrayList<Tile>(tilesToBeUsed);
				remainingTiles.remove(tileNo);
				tilesInWord.add(curTile);
				findHighestScoringWord(remainingTiles, tilesInWord ,currentWord  + curTile.letter, score + curTile.points , anchor);
				
				//need to check if anchor is in the word before we propose it as an answer
				if (tilesToBeUsed.contains(anchor.anchorTile) || curTile.equals(anchor.anchorTile)){
					if (Dictionary.trie.searchWord(currentWord + curTile.letter)){
						if (fitsOnBoard(anchor, tilesInWord)){
							if (maxScore < score + curTile.points){
								maxScore =  score + curTile.points;
								System.out.println(currentWord + curTile.letter + " is word" + "[" + (score + curTile.points) + "]");
								bestWord = tilesInWord;
								currentAnchor = anchor;
							}	
						}
					}
				}
			}
		}
	}
	
	
	void  getStartingWord(ArrayList<Tile> inputTiles, ArrayList<Tile> tilesToBeUsed, String currentWord, int score){
		for (int tileNo = 0 ; tileNo < inputTiles.size() ; tileNo++){
			Tile curTile = inputTiles.get(tileNo);
			
			if (Dictionary.trie.searchPrefix(currentWord + curTile.letter)){
				
				ArrayList<Tile> remainingTiles = new ArrayList<Tile>( inputTiles);
				ArrayList<Tile> tilesInWord = new ArrayList<Tile>(tilesToBeUsed);
				remainingTiles.remove(tileNo);
				tilesInWord.add(curTile);
				getStartingWord(remainingTiles, tilesInWord ,currentWord  + curTile.letter, score + curTile.points );
			
				if (Dictionary.trie.searchWord(currentWord + curTile.letter)){
					if (maxScore < score + curTile.points){
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
					
					//check not at edges - have to re-think algorithm for edges.
					if (row != 0 && row != BOARD_DIMENSIONS){
						int startCol = col;
						int endCol = col;
						
						//check how far left the word can go without collisions
						if (col > 0 && tileArr[row][col - 1].letter == ' '){
							while (startCol > 1){
								if (tileArr[row	   ][startCol - 2].letter != ' ' || 
									tileArr[row + 1][startCol - 1].letter != ' ' || 
									tileArr[row - 1][startCol - 1].letter != ' '){
									break;
								}
								
								startCol--;
							}
						}
						
						//check how far right the word can go without collisions
						if (col < 15 && tileArr[row][col + 1].letter == ' '){
							while (endCol < 14){
								if (
									tileArr[row + 1][endCol + 1].letter != ' ' || 
									tileArr[row - 1][endCol + 1].letter != ' '){
									break;
								}
								if (endCol == 13 || tileArr[row    ][endCol + 2].letter != ' '){
									break;
								}
								
								endCol++;
							}
						}
						if (col - startCol > 0 || endCol - col > 0){
							if (col - startCol > 0 && endCol - col > 0){
								anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col , true));
							} else {
								//if only one then we need to do additional checks
								if (col - startCol > 0){
									if (tileArr[row][col + 1].letter == ' '){
										anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col , false));
									}
								}
								if (endCol - col> 0){
									if (tileArr[row][col - 1 ].letter == ' '){
										anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col , false));
									}
								}
							}
						} 
					}		
					
					
					//check not at edges - have to re-think algorithm for edges.
					if (col != 0 && col != BOARD_DIMENSIONS){
						int startRow = row;
						int endRow = row;
						
						//check how high the word can go without collisions
						if (row > 0 && tileArr[row - 1][col].letter == ' '){
							while (startRow > 1){
								if (tileArr[startRow - 2][col    ].letter != ' ' || 
									tileArr[startRow - 1][col + 1].letter != ' ' || 
									tileArr[startRow - 1][col - 1].letter != ' '){
									break;
								}
								startRow--;
							}
							
						}
						
						//check how low the word can go without collisions
						if (row < 15 && tileArr[row + 1][col].letter == ' '){
							while (endRow < 14){
								if (
									tileArr[endRow + 1][col + 1].letter != ' ' || 
									tileArr[endRow + 1][col - 1].letter != ' '){
									break;
								}
								if (endRow == 13){
									endRow++;
									break;
								}
								if(tileArr[endRow + 2][col    ].letter != ' '){
									break;
								}
									
								endRow++;
							}
						}
					
						if (row - startRow > 0 || endRow - row > 0){
							if (row - startRow > 0 && endRow - row > 0){
								anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
							} else{
								//if only one then we need to do additional checks
								if (row - startRow > 0){
									if (tileArr[row+1][col ].letter == ' '){
										anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
									}
								}
								if (endRow - row > 0){
									if (tileArr[row-1][col ].letter == ' '){
										anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
									}
								}
							}
							
						}
					}			
				}
			}
		}
		return anchors;
	}
	
}
