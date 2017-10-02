import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionary {
	static WordTrie smallTrie;
	static WordTrie bigTrie;
	static HashMap<Character, Integer> pointsPerLetter;
	
	public Dictionary() {
		smallTrie = new WordTrie();
		bigTrie = new WordTrie();
		ReadWordList("wordlist.txt", smallTrie);
		ReadWordList("longwordlist.txt", bigTrie);
		setupLetterScores();
	}
	
	void ReadWordList(String fileName, WordTrie trie){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String word;
			while ( (word  = br.readLine()) != null ){
				trie.insertWord(word.toUpperCase());
			}
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static int getWordScore(String word){
		int score = 0;
		for (char c : word.toCharArray()){
			score += pointsPerLetter.get(c);
		}
		return score;
	}
	
	void setupLetterScores(){
		pointsPerLetter = new HashMap<>();
		pointsPerLetter.put('A',1);
		pointsPerLetter.put('B',3);
		pointsPerLetter.put('C',3);
		pointsPerLetter.put('D',2);
		pointsPerLetter.put('E',1);
		pointsPerLetter.put('F',4);
		pointsPerLetter.put('G',2);
		pointsPerLetter.put('H',4);
		pointsPerLetter.put('I',1);
		pointsPerLetter.put('J',8);
		pointsPerLetter.put('K',5);
		pointsPerLetter.put('L',1);
		pointsPerLetter.put('M',3);
		pointsPerLetter.put('N',1);
		pointsPerLetter.put('O',1);
		pointsPerLetter.put('P',3);
		pointsPerLetter.put('Q',10);
		pointsPerLetter.put('R',1);
		pointsPerLetter.put('S',1);
		pointsPerLetter.put('T',1);
		pointsPerLetter.put('U',1);
		pointsPerLetter.put('V',4);
		pointsPerLetter.put('W',4);
		pointsPerLetter.put('X',8);
		pointsPerLetter.put('Y',4);
		pointsPerLetter.put('Z',10);

	}
	
}

class TrieNode {
    Character c;
    Boolean isLeaf = false;
    HashMap<Character, TrieNode> children = new HashMap<>();
    public TrieNode() {}
    public TrieNode(Character c) {
        this.c = c;
    }
}

class WordTrie {
    private TrieNode root;
   
    public WordTrie() {this.root = new TrieNode();}
	
    public void insertWord(String word) {
        if (word == null) return;
        TrieNode cur = root;
        for (char c : word.toCharArray()){
            HashMap<Character, TrieNode> child = cur.children;
            if (child.containsKey(c)){
                cur = child.get(c);
            } else{
                cur = new TrieNode(c);
                child.put(c, cur);
            }
        }
        cur.isLeaf = true;
    }
    
    public Boolean searchWord(String word) {
        if (word == null|| word == "") return false;
        word = word.toUpperCase();
        TrieNode cur = root;
        for (char c: word.toCharArray()){
            HashMap<Character, TrieNode> child = cur.children;
            if (child.containsKey(c)){
                cur = child.get(c);
            } else{
                return false;
            }
        }
        if (cur.isLeaf){
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean searchPrefix(String word) {
        if (word == null) return false;
        word = word.toUpperCase();
        TrieNode cur = root;
        for (char c: word.toCharArray()){
            HashMap<Character, TrieNode> child = cur.children;
            if (child.containsKey(c)){
                cur = child.get(c);
            } else{
                return false;
            }
        }
        return true;
    }
}

