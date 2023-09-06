import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

public class WordStat {
	
	private Tokenizer WordListContainer;
	private ArrayList<String> wordListWithoutDuplicates = new ArrayList<String>();
	private ArrayList<String> wordPairListWithoutDuplicates = new ArrayList<String>();
	
	private HashTable wordHashTable = new HashTable();
	private HashTable wordPairHashTable = new HashTable();
	
	private ArrayList<HashEntry> wordByRank = new ArrayList<HashEntry>();
	private ArrayList<HashEntry> wordPairByRank = new ArrayList<HashEntry>();
	
	public WordStat(String filename) {
		
		//constuct tokenizer
		WordListContainer = new Tokenizer(filename);
		
		//construct wordHashTable
		for(int i = 0; i<WordListContainer.wordList().size(); i++) {
			wordHashTable.put(WordListContainer.wordList().get(i), 1);
		}
		
		//creating ArrayList of words in file without duplicates
		
		for(int i = 0; i<WordListContainer.wordList().size(); i++) {
			wordListWithoutDuplicates.add(WordListContainer.wordList().get(i));
		}
		
		wordListWithoutDuplicates.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		//removing duplicates loop
		for(int index = 0; index<wordListWithoutDuplicates.size(); index++) {
			int x = index+1;
			while( x<wordListWithoutDuplicates.size() && wordListWithoutDuplicates.get(index).equals(wordListWithoutDuplicates.get(x) ) ) {
				wordListWithoutDuplicates.remove(x);
			}
		}
		
		//wordByRank ArrayList
		for(int i = 0; i<wordListWithoutDuplicates.size(); i++) {
			int value = wordHashTable.get(wordListWithoutDuplicates.get(i));
			wordByRank.add(new HashEntry(wordListWithoutDuplicates.get(i), value));
		}
		
		wordByRank.sort((o1,  o2)-> o2.compareTo(o1));
			
		
		//construct wordPairHashTable
		for(int i = 0; i<WordListContainer.wordList().size()-1; i++) {
			wordPairHashTable.put(WordListContainer.wordList().get(i) + " " + WordListContainer.wordList().get(i+1), 1);
		}
		
		//creating wordPairListWithoutDuplicates
		for(int i = 0; i<WordListContainer.wordList().size()-1; i++) {
			wordPairListWithoutDuplicates.add(WordListContainer.wordList().get(i) + " " + WordListContainer.wordList().get(i+1));
		}
		
		wordPairListWithoutDuplicates.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		//removing duplicates
		for(int index = 0; index<wordPairListWithoutDuplicates.size(); index++) {
			int x = index+1;
			while( x<wordPairListWithoutDuplicates.size() && wordPairListWithoutDuplicates.get(index).equals(wordPairListWithoutDuplicates.get(x) ) ) {
				wordPairListWithoutDuplicates.remove(x);
			}
		}
		
		//wordPairByRank ArrayList
		for (int i = 0; i < wordPairListWithoutDuplicates.size(); i++) {
			int value = wordPairHashTable.get(wordPairListWithoutDuplicates.get(i));
			wordPairByRank.add(new HashEntry(wordPairListWithoutDuplicates.get(i), value));
		}

		wordPairByRank.sort((HashEntry o1, HashEntry o2) -> o2.compareTo(o1));
			

	}
	//////////////////////////////////END OF CONSTRUCTOR////////////////////////////////////////
	ArrayList<String> wordListWithoutDuplicate(){
		return wordListWithoutDuplicates;
	}
	
	ArrayList<String> wordPairListWithoutDuplicate(){
		return wordPairListWithoutDuplicates;
	}
	
	void wordPairByRankList(){
		for(int i = 0; i<wordPairByRank.size(); i++) {
			System.out.print(wordPairByRank.get(i).getKey() + ", ");
		}
	}
	
	int wordCount(String word) {
		int value =  wordHashTable.get(word.toLowerCase());
		if(value == -1000)
			return 0;
		return value;
	}
	
	int wordPairCount(String w1, String w2) {
		String wordPair = w1.toLowerCase() + " " + w2.toLowerCase();
		int value =  wordPairHashTable.get(wordPair);
		if(value == -1000)
			return 0;
		return value;
	}
	
	int wordRank(String word) {
		int rank = 0;
		int wordCount = wordCount(word);
		int i = 0;
		
		while(i<wordByRank.size()) {
			int value = wordByRank.get(i).getValue();
			if(value == wordCount) {
				int x = i;
				while(x<wordByRank.size() && wordByRank.get(i).getValue() == wordByRank.get(x).getValue()) {
					rank++;
					x++;
				}
				return rank;
			}
			rank++;
			i++;
		}
		return -1;
	}
	
	int wordPairRank(String w1, String w2) {
		int rank = 0;
		int wordCount = wordPairCount(w1, w2);
		int i = 0;
		
		while(i<wordPairByRank.size()) {
			int value = wordPairByRank.get(i).getValue();
			if(value == wordCount) {
				int x = i;
				while(x<wordPairByRank.size() && wordPairByRank.get(i).getValue() == wordPairByRank.get(x).getValue()) 
				{
					x++;
					rank++;
				}
				return rank;
			}
			rank++;
			i++;
		}
		return -1;
	}
	
	String[] mostCommonWords(int k) {
		String[] kthMostCommonWords = new String[k];
		for(int i = 0; i<k; i++) {
			kthMostCommonWords[i] = wordByRank.get(i).getKey();
		}
		return kthMostCommonWords;
	}
	
	String[] leastCommonWords(int k) {
		String[] kthLeastCommonWords = new String[k];
		for(int i=0; i<k; i++) {
			kthLeastCommonWords[i] = wordByRank.get(wordByRank.size()-1-i).getKey();
		}
		return kthLeastCommonWords;
	}
	
	String[] mostCommonWordPairs(int k) {
		String[] kthMostCommonWordPairs = new String[k];
		for(int i = 0; i<k; i++) {
			kthMostCommonWordPairs[i] = wordPairByRank.get(i).getKey();
		}
		return kthMostCommonWordPairs;
	}
	
	String[] leastCommonWordPairs(int k) {
		String[] kthLeastCommonWordPairs = new String[k];
		for(int i = 0; i<k; i++) {
			kthLeastCommonWordPairs[i] = wordPairByRank.get(wordPairByRank.size()-1-i).getKey();;
		}
		return kthLeastCommonWordPairs;
	}
	
	String[] mostCommonCollocs(int k, String baseWord, int j) {
		
		baseWord = baseWord.toLowerCase();
		ArrayList<String> allcollocsStrings = new ArrayList<String>();
		int numCollocsFound = 0;
		int index = 0;
		
		//Step one: seperate out by if the j is positive or negative
		if(j==-1) {//words preceding
			while(index<wordPairByRank.size() && numCollocsFound<k) {
				String[] words = wordPairByRank.get(index).getKey().split(" ");
				if(words[1].equals(baseWord)) {
					allcollocsStrings.add(words[0]);
					//collocs[numCollocsFound] = words[0];
					numCollocsFound++;
				}
				index++;
			}
			
		}
		else {
			while(index<wordPairByRank.size() && numCollocsFound<k) {
				String[] words = wordPairByRank.get(index).getKey().split(" ");
				if(words[0].equals(baseWord)) {
					allcollocsStrings.add(words[1]);
					//collocs[numCollocsFound] = words[1];
					numCollocsFound++;
				}
				index++;
			}
			
		}
		
		String[] collocs = new String[allcollocsStrings.size()];
		for(int i= 0; i<allcollocsStrings.size(); i++) {
			collocs[i] = allcollocsStrings.get(i);
		}
		
		return collocs;
		
		
	}
	
	public WordStat(String... lines) {
		
		//constuct tokenizer
		WordListContainer = new Tokenizer(lines);
		
		//construct wordHashTable
		for(int i = 0; i<WordListContainer.wordList().size(); i++) {
			wordHashTable.put(WordListContainer.wordList().get(i), 1);
		}
		
		//creating ArrayList of words in file without duplicates
		
		for(int i = 0; i<WordListContainer.wordList().size(); i++) {
			wordListWithoutDuplicates.add(WordListContainer.wordList().get(i));
		}
		
		wordListWithoutDuplicates.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		//removing duplicates loop
		for(int index = 0; index<wordListWithoutDuplicates.size(); index++) {
			int x = index+1;
			while( x<wordListWithoutDuplicates.size() && wordListWithoutDuplicates.get(index).equals(wordListWithoutDuplicates.get(x) ) ) {
				wordListWithoutDuplicates.remove(x);
			}
		}
		
		//wordByRank ArrayList
		for(int i = 0; i<wordListWithoutDuplicates.size(); i++) {
			int value = wordHashTable.get(wordListWithoutDuplicates.get(i));
			wordByRank.add(new HashEntry(wordListWithoutDuplicates.get(i), value));
		}
		
		wordByRank.sort(new Comparator<HashEntry>() {
			public int compare(HashEntry o1, HashEntry o2) {	
				return o2.compareTo(o1);
			}
		});
		
		//construct wordPairHashTable
		for(int i = 0; i<WordListContainer.wordList().size()-1; i++) {
			wordPairHashTable.put(WordListContainer.wordList().get(i) + " " + WordListContainer.wordList().get(i+1), 1);
		}
		
		//creating wordPairListWithoutDuplicates
		for(int i = 0; i<WordListContainer.wordList().size()-1; i++) {
			wordPairListWithoutDuplicates.add(WordListContainer.wordList().get(i) + " " + WordListContainer.wordList().get(i+1));
		}
		
		wordPairListWithoutDuplicates.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		//removing duplicates
		for(int index = 0; index<wordPairListWithoutDuplicates.size(); index++) {
			int x = index+1;
			while( x<wordPairListWithoutDuplicates.size() && wordPairListWithoutDuplicates.get(index).equals(wordPairListWithoutDuplicates.get(x) ) ) {
				wordPairListWithoutDuplicates.remove(x);
			}
		}
		
		//wordPairByRank ArrayList
		for (int i = 0; i < wordPairListWithoutDuplicates.size(); i++) {
			int value = wordPairHashTable.get(wordPairListWithoutDuplicates.get(i));
			wordPairByRank.add(new HashEntry(wordPairListWithoutDuplicates.get(i), value));
		}

		wordPairByRank.sort(new Comparator<HashEntry>() {
			public int compare(HashEntry o1, HashEntry o2) {
				return o2.compareTo(o1);
			}
		});

	}
	
	public static void main(String[] args) {
		//WordStat wordstat = new WordStat("Ashley is good with CS", "Ashley is fun", "Ashley is good with dogs");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to WordStat, please enter the FULL file path (from the root directory)");
		System.out.println("An example of a full filepath on a mac would be: /Users/monugoel/Downloads/Tuberculosis_Nurse.txt");
		System.out.println("Filepath: ");
		String filepath = scan.next();
		
		try {
			WordStat wordstat = new WordStat(filepath);
			System.out.println("Please enter a word (prefereably one you know is in the file: ");
			String word = scan.next();
			
			System.out.println("Your word appears in the text " + wordstat.wordCount(word) + " times");
			System.out.println("The rank of your word is: " + wordstat.wordRank(word));
			
			String[] commonWords = wordstat.mostCommonWords(3);
			System.out.println("The three most common words in your text are: ");
			
			for(String s: commonWords) {
				System.out.println(s);
			}
			
			String[] smallWords = wordstat.leastCommonWords(3);
			
			System.out.println("The three least common words in your text are: ");
			
			for(String s: smallWords) {
				System.out.println(s);
			}
			
			String[] commonWordPairs = wordstat.mostCommonWordPairs(3);
			
			System.out.println("The three most common words pairs in your text are: ");
			
			for(String s: commonWordPairs) {
				System.out.println(s);
			}
			
			String[] leastWordPairs = wordstat.leastCommonWordPairs(3);
			System.out.println("The three most least common words pairs in your text are: ");
			
			for(String s: leastWordPairs) {
				System.out.println(s);
			}
			
			System.out.println("Please enter another word that you know is in the text file: ");
			word = scan.next();
			
			String[] mostCommonCollocs = wordstat.mostCommonCollocs(3, word,-1);
			
			System.out.println("The words that most often precede " + word + " are:");
			for(String s: mostCommonCollocs) {
				System.out.println(s);
			}
			
			mostCommonCollocs = wordstat.mostCommonCollocs(3, word, 1);
			
			System.out.println("The words that come after " + word + " most often are:");
			for(String s: mostCommonCollocs) {
				System.out.println(s);
			}
			scan.close();
			
			
			
		}
		catch(Exception e) {
			System.out.println("Please check your filepath");
			scan.close();
		}	 
		
	}

}
