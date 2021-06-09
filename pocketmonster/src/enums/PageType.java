package enums;

public enum PageType {
	NONE(0),
	LOADING(1),
	BATTLE_ZONE(2),
	POCKET_MON_CENTER(3),
	WORLD(4),
	PROLOGUE(5),
	EXIT(6),
	CHANGE_POCKET_MON(7);
	
	private int value;
	
	PageType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
