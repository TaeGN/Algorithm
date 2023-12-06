package practice;

public class HashMapTest {
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>(100);
		map.clear();
		for (int i = 0; i < 100; i++) {
			map.put("ssafy" + i, i);
//			map.put("ssafy" + 2 * i, i * 10);
		}
		System.out.println(map);
	}
}
