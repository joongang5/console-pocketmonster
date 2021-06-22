package page;

import java.util.ArrayList;

import enums.PageType;
import manager.PageManager;
import manager.InputManager;

public class PageWorld extends Page {

	public PageWorld() {
		name = "월드맵";
	}
	
	@Override
	public PageType getType() {
		return PageType.WORLD;
	}
	
	@Override
	public void printAction() {
		ArrayList<Page> areaList = PageManager.getInstance().getWorldActionList();
		
		while (true) {
			System.out.println("입장할 지역을 선택해주세요.");
			for (int i = 0; i < areaList.size(); i++) {
				System.out.printf("[%d] %s ", i + 1, areaList.get(i).name);
			}
			System.out.println();
			
			int input = InputManager.getInstance().getScanner().nextInt();
			int areaIndex = input - 1;
			if (areaIndex >= 0 && areaIndex < areaList.size()) {
				PageManager.getInstance().changePage(areaList.get(areaIndex), null);
				break;
			}
			
			System.out.println("잘못된 입력입니다.");
		}
	}
}
