package page;

import common.Player;
import common.PocketMon;
import enums.PageType;
import manager.InputManager;

public class PageChangePocketMon extends Page {

	public PageChangePocketMon() {
		name = "포켓몬 교체";
	}
	
	@Override
	public PageType getType() {
		return PageType.CHANGE_POCKET_MON;
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public void printAction() {
		Player player = Player.getInstance();
		
		for (int i = 0; i < player.pocketMonList.size(); i++) {
			PocketMon pm = player.pocketMonList.get(i);

			if (pm == player.getMainPocketMon()) {
				System.out.printf("%-5s", "전투중");
			} else {
				System.out.printf("%-6s", " ");
			}
			
			System.out.printf("[%d] %s || LV : %d || HP : (%d / %d) || EXP : (%d / %d)\n", i + 1, pm.name, pm.level, pm.hp, pm.maxHp, pm.exp, pm.maxExp);
		}
		
		int cancelNumber = player.pocketMonList.size() + 1;
		System.out.printf("%-6s[%d] 취소\n", " ",cancelNumber);
		
		while (true) {
			System.out.print("교체할 포켓몬을 선택해주세요 : ");
			int input = InputManager.getInstance().getScanner().nextInt();
			int index = input - 1;
			
			if (input == cancelNumber) {
				System.out.println("취소되었습니다.");
				break;
			}
			
			PocketMon mainPocketMon = player.changeMainPocketMon(index);
			if (mainPocketMon != null) {
				System.out.printf("교체에 성공했습니다. 현재 포켓몬 : %s\n", mainPocketMon.name);
				break;
			}
		}
	}
}
