package page;

import common.Player;
import enums.PageType;
import manager.PageManager;
import manager.TableDataManager;

public class PageLoading extends Page {

	public PageLoading() {
		name = null;
	}
	
	@Override
	public PageType getType() {
		return PageType.LOADING;
	}
	
	@Override
	public void init() {
		// do nothing.
	}

	@Override
	public void printAction() {
		TableDataManager.getInstance().load();
		
		Player.getInstance().loadSaveFile();
		
		if (Player.getInstance().wasInit()) {
			PageManager.getInstance().setCurrentPage(PageType.WORLD);	
		} else {
			PageManager.getInstance().setCurrentPage(PageType.PROLOGUE);
		}
	}
}
