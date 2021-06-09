package table;

import java.util.HashMap;

public class ExpTable {
	
	private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public int get(int level) {
		return map.get(level);
	}
	
	public void load() {
		map.put(1, 60);
		map.put(2, 70);
		map.put(3, 80);
		map.put(4, 90);
		map.put(5, 100);
		map.put(6, 120);
		map.put(7, 140);
		map.put(8, 160);
		map.put(9, 180);
		map.put(10, 200);
		map.put(11, 250);
		map.put(12, 300);
		map.put(13, 350);
		map.put(14, 400);
		map.put(15, 450);
		map.put(16, 500);
		map.put(17, 550);
		map.put(18, 600);
		map.put(19, 650);
		map.put(20, 700);
	}
}
