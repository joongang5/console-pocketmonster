package enums;

public enum PageType {
	NONE(0),
	LOADING(1),
	BATTLE_ZONE(2),
	POCKET_MON_CENTER(3),
	POCKET_MON_INFO(4),
	WORLD(5),
	PROLOGUE(6),
	EXIT(7),
	CHANGE_POCKET_MON(8),
	INVENTORY(9);
	
	private int value;
	
	PageType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
