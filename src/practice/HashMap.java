package practice;

public class HashMap<K, V> {
	private Node<K, V>[] hashMap;
	private int size;
	
	public HashMap(int size){
		this.hashMap = new Node[size];
		this.size = size;
	}
	
	public void put(K key, V value) {
		int hash = getHash(key);
		Node<K, V> next = null;
		if(this.hashMap[hash] != null) {
			next = this.hashMap[hash];
		}
		Node<K, V> node = new Node<>(hash, key, value, next);
		this.hashMap[hash] = node;
	}
	
	public V get(K key) {
		int hash = getHash(key);
		Node<K, V> node = this.hashMap[hash];
		while(node != null && !node.key.equals(key)) node = node.next;
		return node.value;
	}
	
	public int getHash(K key) {
		return Math.abs(key.hashCode()) % size;
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			this.hashMap[i] = null;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			if(this.hashMap[i] == null) continue;
			sb.append(this.hashMap[i].toString()).append("\n");
//			Node<K, V> node = this.hashMap[i];
//			while(node != null) {
//				sb.append(node).append(",");
//				node = node.next;
//			}
		}
		return sb.toString();
	}
	
	class Node<K, V> {
		final int hash;
		final K key;
		V value;
		Node<K, V> next;
		
		public Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (Node<K, V> node = this; node != null; node = node.next) {
				sb.append(this.key).append(" : ").append(this.value).append(", ");
			}
			return sb.toString();
		}
	}
}


