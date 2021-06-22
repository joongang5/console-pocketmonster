package item;

import common.PocketMon;
import table.data.ItemData;

public class Item {

	public int id;
	public int type;
	public int value;
	public String name;
	public String desc;
	public int count;

	public Item(ItemData data) {
		id = data.id;
		type = data.type;
		value = data.value;
		name = data.name;
		desc = data.desc;
		count = 1;
	}
	
	public void use(PocketMon target) {
		System.out.printf("%s에게 %s(을)를 사용했다!\n", target.name, name);
	}
}
