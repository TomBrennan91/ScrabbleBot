import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Dictionary {
	static ArrayList<String> wordList;
	
	public void ReadWordList(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			wordList = new ArrayList<>();
			String word;
			while ( (word  = br.readLine()) != null ){
				wordList.add(word);
			}
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	static boolean isRealWord(String word){
		return (java.util.Collections.binarySearch(wordList, word) > 0);
	}
	
	static int isPrefixOfWord(String prefix){
		//0 if is not prefix of any word
		//1 if is valid prefix of a larger word
		//2 if prefix is exact match but not the prefix to a larger word
		//3 if is an exact match and also the prefix to a larger word.
		prefix = prefix.toLowerCase();
		int result = 0;
		int pos = java.util.Collections.binarySearch(wordList, prefix);
		
		if (pos > 0){
			result+=2;
			pos++;
		} else {
			pos = -pos;
			pos--;
		}
		
		String closest = wordList.get(pos);
		
		if (closest.length() > prefix.length()){
			if (closest.substring(0, prefix.length()).equals(prefix)){
				result++;
			}
		}
		return result;
	}
}

class wordLengthComparator implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) {
		
		return s2.length() - s1.length();
	}
}
