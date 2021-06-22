package common;

import java.util.ArrayList;

import item.Item;

public class Inventory {

	private ArrayList<Item> itemList;
	
	public final int MAX_SIZE = 10;
	
	public Inventory() {
		itemList = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public void removeItem(Item item) {
		itemList.remove(item);
	}
}
