package page;

import java.util.ArrayList;

import common.Player;
import common.PocketMon;
import enums.PageType;
import manager.InputManager;
import manager.PageManager;

public class PagePocketMonCenter extends Page {

	private final int HEAL_POWER = 999999999;
	
	public PagePocketMonCenter() {
		name = "포켓몬 센터";
	}
	
	@Override
	public PageType getType() {
		return PageType.POCKET_MON_CENTER;
	}
	
	@Override
	public void init() {

	}
	
	@Override
	public void printAction() {
		System.out.println("안녕하세요. 포켓몬센터입니다!");
		System.out.println("이곳에서는 포켓몬의 체력을 회복합니다.");
		System.out.println("당신의 포켓몬을 치료하시겠습니까?");
		System.out.println("[1] 예 [2] 아니오");
		
		int input = InputManager.getInstance().getScanner().nextInt();
		switch (input) {
		case 1:
			recovery();
			exit();
			break;
		case 2:
			exit();
			break;
		}	
		System.out.println();
	}
	
	private void recovery() {
		ArrayList<PocketMon> list = Player.getInstance().pocketMonList;
		for (PocketMon pm : list) {
			pm.onRecovery(HEAL_POWER);;
		}
		
		System.out.println("치료가 완료되었습니다.");
	}
	
	private void exit() {
		System.out.println("안녕히가세요!");
		System.out.println("철컥-");
		
		PageManager.getInstance().setCurrentPage(PageType.WORLD);
	}
}
