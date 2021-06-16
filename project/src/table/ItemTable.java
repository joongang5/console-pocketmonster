package table;

import java.util.HashMap;

import table.data.ItemData;

public class ItemTable {

	private HashMap<Integer, ItemData> map = new HashMap<Integer, ItemData>();

	public ItemData get(int id) {
		return map.get(id);
	}
	
	public void load() {
		map.put(1, new ItemData(1, ItemData.TYPE_CUSTOM, 0, "몬스터볼", "포켓몬을 포획할 수 있습니다."));
		map.put(2, new ItemData(2, ItemData.TYPE_RECOVERY, 5, "상처약", "체력이 %d 회복됩니다."));
		map.put(3, new ItemData(3, ItemData.TYPE_CUSTOM, 0, "해독제", "중독 상태를 벗어납니다."));
	}
}
