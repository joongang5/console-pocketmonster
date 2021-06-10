package page;

import enums.PageType;

public abstract class Page {

	protected String name;
	
	public abstract PageType getType();
	
	public void onEnable() {
		if (name != null) {
			System.out.printf("[==%s==]\n", name);
		}
	}
	
	public void onDisable() {}
	
	public abstract void printAction();
	
}
