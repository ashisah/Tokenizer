import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTests {

	@Test
	public void testConstructor() {
		HashTable ash = new HashTable();
		assertEquals(7919, ash.capacity());
		ash = new HashTable(100);
		assertEquals(100, ash.capacity());
	}
	
	@Test
	public void testPut() {
		HashTable ash = new HashTable();
		
		//testing plain insert
		ash.put("Ash", 5, 7);
		assertEquals(5, ash.get("Ash", 7));
		
		//testing collisions
		ash.put("Bash", 4, 7);
		assertEquals(4, ash.get("Bash", 11));
		
		//testing identical keys
		ash.put("Bash", 1, 7);
		assertEquals(5, ash.get("Bash", 7));
		assertEquals(2, ash.size());
		
		
		//testing resize algorithim
		ash = new HashTable(79);
		
		int i = 0;
		
		while(i<79) {
			ash.put("Ash",0, i);
			i++;
		}
		//at this point hashTable is filled up
		assertEquals(79, ash.size());
		
		//should trigger the resize algorthim
		ash.put("Bash", 3, 4);
		
		//properly resized to next prime number after 100
		assertEquals(101, ash.capacity());
		assertEquals(2, ash.size());
		
		//have not written get function at this point of test yet
		assertEquals(78, ash.get( "Ash", "Ash".hashCode()%ash.capacity() ));
		assertEquals(78, ash.get("Ash"));
		assertEquals(3, ash.get("Bash", 4));
		
		
		
	}
	
	@Test
	public void testGet() {
		HashTable ash = new HashTable();
		
		ash.put("ASH", 20);
		//testing it returns the correct value for key entered in table
		assertEquals(20, ash.get("ASH"));
		
		//testing it returns the correct value for non-existent key in table
		assertEquals(-1000, ash.get("BASH"));
		
		//testing get() can handle collisions
		ash.put("CASH", 30, "BASH".hashCode()); 
		ash.put("BASH", 20);
		
		assertEquals(20, ash.get("BASH"));
		assertEquals(30, ash.get("CASH", "BASH".hashCode()));		
	}
	

}
