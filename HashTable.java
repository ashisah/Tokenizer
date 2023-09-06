import java.util.NoSuchElementException;
/**
 * 
 * @author Ashley Sah
 * Class represents a HashTable
 * Uses double hashing to handle collisions 
 * Decided to use 
 */
public class HashTable {
	private int numItems = 0;
	private int TableSize;
	private HashEntry[] hashTable;
	
	public HashTable() {
		hashTable = new HashEntry[7919];
		TableSize = 7919;
	}

	public HashTable(int TableSize) {
		hashTable = new HashEntry[TableSize];
		this.TableSize = TableSize;
	}

	public int capacity() {
		return TableSize;
	}
	
	public int size() {
		return numItems;
	}
	
	private int abs(int value) {
		if (value < 0) {
			return value * -1;
		}
		return value;
	}
	
	

	public void put(String key, int value, int hashCode) {
		int n = 0;
		//mod the hashCode by TableSize
		hashCode = abs(hashCode) % TableSize;

		//while n is less than the table size and there is not an empty position in the hashTable
		while( n<TableSize && hashTable[hashCode] != null) {
			if(hashTable[hashCode].getKey().equals(key)) {
				hashTable[hashCode].setValue(hashTable[hashCode].getValue() +1);
				break;
			}
			n++;
			//here is where I double hash
			hashCode = (hashCode + n*key.length())%TableSize;
		}
		//by the time we exit this loop three things have happened:

		//1: we couldn't find a place for the key (resize table algorithim, then run insert function again)
		if(n==TableSize) {
			//store the old hashTable and old TableSize
			int oldSize = TableSize;
			HashEntry[] oldTable = hashTable;
			numItems = 0;
			
			//find next prime number for the new TableSize and create a hashTable w/ that TableSize
			TableSize = nextPrime(TableSize+1, TableSize*2);
			hashTable = new HashEntry[TableSize];
			
			
			//rehash everything from the old hashTable to the new hashTable
			for(int i = 0; i<oldSize; i++) {
				if(oldTable[i] != null) {
					put(oldTable[i].getKey(), oldTable[i].getValue(), abs(oldTable[i].getKey().hashCode()) );
				}
			}
			
			//insert the value
			put(key, value, hashCode);	
		}
		
		//2: we found an empty spot (insert)
		if(hashTable[hashCode]==null) {
			HashEntry insert = new HashEntry(key, value);
			hashTable[hashCode] = insert;
			numItems++;
		}
		
		//3: we found an identical key and just updated the value (do nothing)
		
	}
	
	public void put(String key, int value) {
		put( key, value, abs(key.hashCode()) );
		//I don't give it key.hashCode()%TableSize because it already does that in the other insert function
	}
	
	/**
	 * Finds the next prime number between TableSize and TableSize*2
	 * @return the next prime number between TableSize and TableSize*2
	 */
	private int nextPrime(int lowerLimit, int upperLimit) {
		
		//The only input this goes in
		if(lowerLimit<100) {
			lowerLimit = 99;
			upperLimit = 203;
		}
		
		if(lowerLimit%2 == 0) {
			lowerLimit = lowerLimit+3;
		}
		
		int numCheck;
		
		for(numCheck = lowerLimit; numCheck<upperLimit; numCheck = numCheck+2) {
			boolean isPrime = true;
			for(int factor = numCheck/2; factor>1; factor = factor -1) {
				if(numCheck%factor == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				return numCheck;
			}
		}
		
		throw new NoSuchElementException();
		
	}
	
	public int get(String key, int hashCode) {
		try {
			return hashTable[abs(hashCode)%TableSize].getValue();
		}
		catch(NullPointerException e) {
			return -1000;
			//the current way we plan on using hashTable means negative values are not possible
		}
	}
	

	
	
	public int get(String key) {
		int hashCode = abs(key.hashCode())%TableSize;
		int n = 0;
		
		//not removing anything so can say it's not there if we run into null value
		while(n<TableSize && hashTable[hashCode] != null) {
			if( hashTable[hashCode].getKey().equals(key) ) {
				return hashTable[hashCode].getValue();
			}
			n++;
			hashCode = (abs(key.hashCode()) + n*key.length())%TableSize;
		}

		//if we exit this loop, we know key does not exist, return a value not possible
		return -1000;
	}
	
	
	

	public static void main(String[] args) {
		HashTable ash = new HashTable();
		
		int i = 0;
		while(i<7919) {
			i++;
			ash.put("ASH", 0, i);
		}
		
		System.out.println(ash.size());
		
	}
}
	


