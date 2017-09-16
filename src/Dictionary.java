import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionary {
	static WordTrie trie;
	
	public Dictionary() {
		ReadWordList("wordlist.txt");
	}
	
	public void ReadWordList(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			trie = new WordTrie();
			String word;
			while ( (word  = br.readLine()) != null ){
				trie.insertWord(word.toUpperCase());
			}
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
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

