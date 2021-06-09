package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import enums.PageType;
import page.*;

public class PageManager {

	private HashMap<PageType, Page> pageMap = new HashMap<PageType, Page>();

	private Page currentPage;
	
	private static PageManager instance;
	public static PageManager getInstance() {
		if (instance == null) {
			instance = new PageManager(); 
		}
		return instance;
	}
	
	private PageManager() {
		pageMap.put(PageType.LOADING, new PageLoading());
		pageMap.put(PageType.PROLOGUE, new PagePrologue());
		pageMap.put(PageType.WORLD, new PageWorld());
		pageMap.put(PageType.BATTLE_ZONE, new PageBattleZone());
		pageMap.put(PageType.POCKET_MON_CENTER, new PagePocketMonCenter());
		pageMap.put(PageType.EXIT, new PageExit());
		pageMap.put(PageType.CHANGE_POCKET_MON, new PageChangePocketMon());
	}

	public PageType getCurrentPageType() {
		return currentPage.getType();
	}
	
	public void setCurrentPage(Page page) {
		currentPage = page;
	}
	
	public void setCurrentPage(PageType type) {
		setCurrentPage(pageMap.get(type));
	}

	public void setCurrentPage(int index) {
		PageType type = PageType.values()[index]; 
		setCurrentPage(type);
	}
	
	public void forceChangePage(Page page) {
		setCurrentPage(page);
		update();
	}
	
	public void forceChangePage(PageType type) {
		setCurrentPage(type);
		update();
	}
	
	public void worldMask() {
		
	}
	
	public void update() {
		currentPage.init();
		currentPage.printName();
		currentPage.printAction();
	}
	
	public ArrayList<Page> getWorldActionList() {
		ArrayList<Page> list = new ArrayList<Page>();
		
		for (Map.Entry<PageType, Page> entry : pageMap.entrySet()) {
			if (entry.getKey() == PageType.BATTLE_ZONE || 
				entry.getKey() == PageType.POCKET_MON_CENTER ||
				entry.getKey() == PageType.EXIT) {
				list.add(entry.getValue());
			}
		}

		Collections.sort(list, new Comparator<Page>() {
			@Override
			public int compare(Page p1, Page p2) {
				return p1.getType().getValue() - p2.getType().getValue();
			}
		});
		
		return list;
	}
}
