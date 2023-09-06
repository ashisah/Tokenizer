import static org.junit.Assert.*;

import org.junit.Test;

public class TokenizerTests {

	@Test
	public void testConstructor() {
		Tokenizer tn = new Tokenizer("/Users/monugoel/Downloads/Tuberculosis_Nurse.txt");
		System.out.println(tn.wordList());
		tn = new Tokenizer("Ashley (i's having", "a hard3 time with this project");
		System.out.println(tn.wordList());
		
	}

}
