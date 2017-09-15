import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryTest {

	@Test
	public void test() {
		new Dictionary();
		assertTrue( Dictionary.trie.searchWord("HELLO"));
	}

	@Test
	public void test2() {
		new Dictionary();
		assertTrue( Dictionary.trie.searchWord("hello"));
	}
	
	@Test
	public void test3() {
		new Dictionary();
		assertTrue( Dictionary.trie.searchWord("boat"));
	}
	
	
	@Test
	public void test4() {
		new Dictionary();
		assertFalse( Dictionary.trie.searchWord("esufsejfse"));
	}
	
	
	@Test
	public void test5() {
		new Dictionary();
		assertTrue( Dictionary.trie.searchPrefix("hell"));
	}

	@Test
	public void test6() {
		new Dictionary();
		assertTrue( Dictionary.trie.searchPrefix("HELL"));
	}
	
	@Test
	public void test7() {
		new Dictionary();
		assertFalse( Dictionary.trie.searchPrefix("eijf"));
	}
	
	
	@Test
	public void test8() {
		new Dictionary();
		assertTrue( Dictionary.trie.searchWord("terse"));
	}
}
