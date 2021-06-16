package table.data;

public class ItemData {
	
	public int id;
	public int type;
	public int value;
	public String name;
	public String desc;

	public static final int TYPE_RECOVERY = 0;
	public static final int TYPE_CUSTOM = 1;
	
	public ItemData(int id, int type, int value, String name, String desc) {
		this.id = id;
		this.type = type;
		this.value = value;
		this.name = name;
		this.desc = desc;
	}
}
