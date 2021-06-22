package page;

import enums.PageType;

public abstract class Page {

	protected String name;
	
	public abstract PageType getType();
	
	public void setPageData(PageData data) {}
	
	public void onEnable() {
		if (name != null) {
			System.out.printf("\n[==%s==]\n", name);
		}
	}
	
	public void onDisable() {}

	public void onReturned(Page prevPage) {}
	
	public abstract void printAction();
}