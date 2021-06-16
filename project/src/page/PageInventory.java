package page;

import enums.PageType;
import manager.InputManager;
import manager.PageManager;

public class PageInventory extends Page {

	public PageInventory() {
		name = "가방";
	}
	
	@Override
	public PageType getType() {
		return PageType.INVENTORY;
	}
	
	@Override
	public void printAction() {
		System.out.println("1. 몬스터볼 x 10");
		System.out.println("2. 상처약 x 3");
		System.out.println("3. 해독체 x 3");
		System.out.println("4. 취소");
		
		InputManager.getInstance().getScanner().nextInt();
		
		System.out.println("취소되었습니다.");
		
		PageManager.getInstance().popPage();
	}
}
