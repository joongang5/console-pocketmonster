package page;

import java.util.ArrayList;

import common.Player;
import common.PocketMon;
import enums.PageType;
import item.Item;
import manager.InputManager;
import manager.PageManager;

class PageInventoryData extends PageData {
	public PocketMon target;
	
	public PageInventoryData(PocketMon target) {
		this.target = target;
	}
}

public class PageInventory extends Page {

	private PageInventoryData pageData;
	
	public PageInventory() {
		name = "가방";
	}
	
	@Override
	public PageType getType() {
		return PageType.INVENTORY;
	}
	
	@Override
	public void setPageData(PageData data) {
		this.pageData = (PageInventoryData)data;
	}
	
	@Override
	public void printAction() {
		ArrayList<Item> itemList = Player.getInstance().inventory.getItemList();
		
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			System.out.printf("[%d] %s x %d || %s\n", i + 1, item.name, item.count, item.desc);
		}
		int cancelNumber = itemList.size() + 1;
		System.out.printf("[%d] 취소\n", cancelNumber);
		
		while (true) {
			System.out.print("사용할 아이템을 선택해주세요 : ");
			int input = InputManager.getInstance().getScanner().nextInt();
			int index = input - 1;
			
			System.out.println();
			
			if (input == cancelNumber) {
				System.out.println("취소되었습니다.");
				break;
			}
			
			Item item = itemList.get(index);
			if (item != null) {
				item.use(pageData.target);
				break;
			}
		}
		
		PageManager.getInstance().popPage();
	}
}
