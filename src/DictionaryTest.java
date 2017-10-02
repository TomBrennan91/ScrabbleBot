import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class DictionaryTest {

	@Test
	public void test() {
		new Dictionary();
		assertTrue( Dictionary.smallTrie.searchWord("HELLO"));
	}

	@Test
	public void test2() {
		new Dictionary();
		assertTrue( Dictionary.smallTrie.searchWord("hello"));
	}
	
	@Test
	public void test3() {
		new Dictionary();
		assertTrue( Dictionary.smallTrie.searchWord("boat"));
	}
	
	
	@Test
	public void test4() {
		new Dictionary();
		assertFalse( Dictionary.smallTrie.searchWord("esufsejfse"));
	}
	
	
	@Test
	public void test5() {
		new Dictionary();
		assertTrue( Dictionary.smallTrie.searchPrefix("hell"));
	}

	@Test
	public void test6() {
		new Dictionary();
		assertTrue( Dictionary.smallTrie.searchPrefix("HELL"));
	}
	
	@Test
	public void test7() {
		new Dictionary();
		assertFalse( Dictionary.smallTrie.searchPrefix("eijf"));
	}
	
	
	@Test
	public void test8() {
		new Dictionary();
		assertTrue( Dictionary.smallTrie.searchWord("terse"));
	}
	
	@Test 
	public void test9(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("wordlist.txt"));
			String word;
			while ( (word  = br.readLine()) != null ){
				if (!Dictionary.bigTrie.searchWord(word)){
					System.err.println(word);
				}
			}
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
}
