
public class HashEntry implements Comparable<HashEntry>{
	private int value;
	private String key;
	
	public HashEntry(String key, int value) {
		this.key = key;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(HashEntry o) {
		return this.getValue()-o.getValue();
	}
}
