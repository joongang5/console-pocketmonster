package page;

import enums.PageType;

public abstract class Page {

	protected String name;
	
	public abstract PageType getType();
	
	public abstract void init();
	
	public void printName() {
		if (name == null) {
			return;
		}
		System.out.printf("[==%s==]\n", name);
	}
	
	public abstract void printAction();
}
