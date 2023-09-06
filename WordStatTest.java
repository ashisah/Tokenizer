import static org.junit.Assert.*;

import org.junit.Test;

public class WordStatTest {

	@Test
	public void testConstructor() {
		
		WordStat wordstat = new WordStat("/Users/monugoel/Downloads/Tuberculosis_Nurse.txt");
		
		//System.out.println(wordstat.wordListWithoutDuplicate());
		
		//System.out.println(wordstat.wordRank("again"));
		
		//System.out.println(wordstat.wordPairListWithoutDuplicate());
		
		//System.out.println(wordstat.wordPairRank("as", "is"));
		
		WordStat wordstat1 = new WordStat("Ashley", "is", "awesome");
		
		String[] words = {"Ashley", "is", "Awesome"};
		
		WordStat wordstat2 = new WordStat(words);
		
		
	}
	
	@Test
	public void testWordCount() {
		WordStat wordstat = new WordStat("Ashley is fun", "Ashley can jump", "Ashley is good at CS");
		assertEquals(3, wordstat.wordCount("Ashley"));
		assertEquals(2, wordstat.wordCount("is"));
		assertEquals(0, wordstat.wordCount("Cat"));
	}
	
	@Test
	public void testWordPairCount() {
		WordStat wordstat = new WordStat("Ashley is fun", "Ashley can jump", "Ashley is good at CS");
		assertEquals(2, wordstat.wordPairCount("Ashley", "is"));
		assertEquals(1, wordstat.wordPairCount("good", "At"));
		assertEquals(0, wordstat.wordPairCount("fun", "is"));
	}
	
	@Test
	public void testWordRank() {
		WordStat wordstat = new WordStat("Ashley is fun", "Ashley can jump", "Ashley is good at CS", "fun");
		assertEquals(1, wordstat.wordRank("ashley"));
		assertEquals(3, wordstat.wordRank("is"));
		assertEquals(3, wordstat.wordRank("fun"));
		assertEquals(8, wordstat.wordRank("can"));
		assertEquals(8, wordstat.wordRank("jump"));
		assertEquals(-1, wordstat.wordRank("cat"));
	}
	
	@Test
	public void testWordPairRank() {
		
		WordStat wordstat = new WordStat("Ashley is good with CS", "Ashley is fun", "Ashley is good with dogs");
		assertEquals(1, wordstat.wordPairRank("Ashley", "is"));
		assertEquals(3, wordstat.wordPairRank("is", "good"));
		assertEquals(3, wordstat.wordPairRank("good", "with"));
		assertEquals(8, wordstat.wordPairRank("with", "CS"));
		assertEquals(8, wordstat.wordPairRank("CS", "Ashley"));
		assertEquals(-1, wordstat.wordPairRank("cat", "Ashley"));
		 
	}
	
	@Test
	public void testMostCommonWords() {
		WordStat wordstat = new WordStat("Ashley is fun", "Ashley is cool", "Ashley is fun", "Ashley is nice");
		String[] mostCommonWord = wordstat.mostCommonWords(3);
		assertEquals("ashley", mostCommonWord[0]);
		assertEquals("is", mostCommonWord[1]);
		assertEquals("fun", mostCommonWord[2]);
	}
	
	@Test
	public void testLeastCommonWords() {
		WordStat wordstat = new WordStat("Ashley is fun", "Ashley is cool", "Ashley is fun", "Ashley is nice");
		String[] leastCommonWord = wordstat.leastCommonWords(3);
		assertEquals("nice", leastCommonWord[0]);
		assertEquals("cool", leastCommonWord[1]);
		assertEquals("fun", leastCommonWord[2]);
	}
	
	@Test
	public void testMostCommonWordsPairs() {
		WordStat wordstat = new WordStat("Ashley is fun", "Ashley is cool", "Ashley is fun", "Ashley is nice");
		String[] mostCommonWordPairs =  wordstat.mostCommonWordPairs(3);
		assertEquals("ashley is", mostCommonWordPairs[0]);
		assertEquals("fun ashley", mostCommonWordPairs[1]);
		assertEquals("is fun", mostCommonWordPairs[2]);
		
	}
	
	@Test
	public void testLeastCommonWordsPairs() {
		WordStat wordstat = new WordStat("Ashley is fun", "Ashley is cool", "Ashley is fun", "Ashley is");
		String[] leastCommonWordPairs = wordstat.leastCommonWordPairs(3);
		assertEquals("is cool", leastCommonWordPairs[0]);
		assertEquals("cool ashley", leastCommonWordPairs[1]);
		assertEquals("is fun", leastCommonWordPairs[2]);
		
	}
	
	@Test
	public void testMostCommonCollocs() {
		WordStat wordstat = new WordStat("fun Yoda is", "tired Yoda is", "learned lightsaber yoda did,", "tired Yoda is", "fun Yoda is", "tired Yoda is");
		String[] yodasPrecedingWords = wordstat.mostCommonCollocs(3, "Yoda", -1);
		//wordstat.wordPairByRankList();
		
		assertEquals("tired", yodasPrecedingWords[0]);
		assertEquals("fun", yodasPrecedingWords[1]);
		assertEquals("lightsaber", yodasPrecedingWords[2]);
		
		String[] yodasNextWords = wordstat.mostCommonCollocs(3, "Yoda", 1);
		
		
		assertEquals(2, yodasNextWords.length);
		assertEquals("is", yodasNextWords[0]);
		assertEquals("did", yodasNextWords[1]);
		
	}

}
