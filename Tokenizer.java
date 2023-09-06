import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @author Ashley Sah
 *
 */
public class Tokenizer {
	
	private ArrayList<String> wordList = new ArrayList<String>();
	
	
	public Tokenizer(String filepath) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String line;
			
			//For each line
			while((line = br.readLine())!=null) {
				//normalize alg
				
				//stores the current index in the line
				int i = 0;
				//will store a word in the line
				StringBuilder word = new StringBuilder();
				
				//while our index is not too high
				while(i<line.length()) {
					
					//while the character we look at is part of the current word we're looking at
					while( i <line.length() && (Character.isLetter(line.charAt(i)) || line.charAt(i) == '\'')) {
						//add it to the word we're building
						word.append(line.charAt(i));
						//move to the next character
						i++;
					}
					//by the time it exits this loop it should have the word (without any punctuation)
					
					//add word (given that it's not a blank space
					if (!(word.toString().toLowerCase().equals(' ') || word.toString().length()==0) )
						wordList.add(word.toString().toLowerCase());
					
					//reset the string builder
					word = new StringBuilder();
					//move to the next character
					i++;
				}
			}
			br.close();
			
		}
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		catch(IOException e) {
			throw new IllegalArgumentException();
		}
	}
	
	
	public Tokenizer(String... lines) {
		for(String line: lines) {
			//stores the current index in the line
			int i = 0;
			//will store a word in the line
			StringBuilder word = new StringBuilder();
			
			//while our index is not too high
			while(i<line.length()) {
				
				//while the character we look at is part of the current word we're looking at
				while( i <line.length() && (Character.isLetter(line.charAt(i)) || line.charAt(i) == '\'')) {
					//add it to the word we're building
					word.append(line.charAt(i));
					//move to the next character
					i++;
				}
				//by the time it exits this loop it should have the word (without any punctuation)
				
				//add word (given that it's not a blank space
				if (!(word.toString().toLowerCase().equals(' ') || word.toString().length()==0) )
					wordList.add(word.toString().toLowerCase());
				
				//reset the string builder
				word = new StringBuilder();
				//move to the next character
				i++;
			}
		}
		
	}
	
	
	ArrayList<String> wordList(){
		return wordList;
	}
	
	public static void main(String[] args) {
				
	}

}
