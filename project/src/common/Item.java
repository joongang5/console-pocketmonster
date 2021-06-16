package common;

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
	
	public void use() {
		System.out.printf("%s(을)를 사용했습니다.\n", name);
	}
}
