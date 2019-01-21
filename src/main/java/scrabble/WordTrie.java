package scrabble;


import java.util.HashMap;

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
