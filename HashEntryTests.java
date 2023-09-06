import static org.junit.Assert.*;

import org.junit.Test;

public class HashEntryTests {

	@Test
	public void testGetandSetValue() {
		HashEntry hash = new HashEntry("Ashley", 5);
		assertEquals(5, hash.getValue());
		hash.setValue(8);
		assertEquals(8, hash.getValue());
	}
	
	@Test
	public void testGetKey() {
		HashEntry hash = new HashEntry("Ashley", 5);
		assertEquals("Ashley", hash.getKey());
	}

}
