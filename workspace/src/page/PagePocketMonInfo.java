package page;

import java.util.ArrayList;

import common.Player;
import common.PocketMon;
import enums.PageType;
import manager.PageManager;

public class PagePocketMonInfo extends Page {

	public PagePocketMonInfo() {
		name = "포켓몬 정보";
	}
	
	@Override
	public PageType getType() {
		return PageType.POCKET_MON_INFO;
	}
	
	@Override
	public void printAction() {
		ArrayList<PocketMon> list = Player.getInstance().pocketMonList;
		
		for (int i = 0; i < list.size(); i++) {
			PocketMon pm = list.get(i);
			
			if (Player.getInstance().isMainPocketMon(pm)) {
				System.out.printf("%-5s", "출전중");
			} else {
				System.out.printf("%-6s", " ");
			}
			
			System.out.printf("[%d] %s || LV : %d || HP : (%d / %d) || EXP : (%d / %d)\n", i + 1, pm.name, pm.level, pm.hp, pm.maxHp, pm.exp, pm.maxExp);
		}
		
		PageManager.getInstance().changePage(PageType.WORLD);
	}
}
