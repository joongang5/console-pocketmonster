package page;

import java.util.ArrayList;

import common.Player;
import common.PocketMon;
import common.Skill;
import enums.PageType;
import enums.TeamType;
import manager.PageManager;
import manager.SpawnManager;
import manager.InputManager;

public class PageBattleZone extends Page {

	private PocketMon mainPocketMon;
	private PocketMon wildPocketMon;
	
	public PageBattleZone() {
		name = "전투 지역";
	}

	@Override
	public PageType getType() {
		return PageType.BATTLE_ZONE;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		mainPocketMon = Player.getInstance().getMainPocketMon();
		wildPocketMon = SpawnManager.getInstance().spawnRandomPocketMon();
		wildPocketMon.teamType = TeamType.ENEMY;

		System.out.printf("앗! 야생의 %s(이)가 나타났다!\n", wildPocketMon.name);
	}
	
	@Override
	public void printAction() {
		while (true) {
			if (pauseOrEndBattle())
				return;
			
			printPocketMonInfo();
			
			System.out.println("[1] 싸우다 [2] 포켓몬 교체 [3] 가방 [4] 도망치다");
			int input = InputManager.getInstance().getScanner().nextInt();
			switch (input) {
			case 1:
				battle();
				break;
			case 2:
				changePocketMon();
				break;
			case 3:
				openInventory();
				break;
			case 4:
				escape();
				return;
			}
		}
	}
	
	private boolean pauseOrEndBattle() {
		if (PageManager.getInstance().getCurrentPageType() != getType())
			return true;
		
		if (Player.getInstance().hasPocketMon(wildPocketMon)) {
			PageManager.getInstance().changePage(PageType.WORLD, null);
			return true;	
		}
		
		return false;
	}
	
	private void printPocketMonInfo() {
		System.out.printf("%s || LV : %d || HP : (%d / %d)\n", wildPocketMon.name, wildPocketMon.level, wildPocketMon.hp, wildPocketMon.maxHp);
		System.out.printf("%s || LV : %d || HP : (%d / %d)\n", mainPocketMon.name, mainPocketMon.level, mainPocketMon.hp, mainPocketMon.maxHp);
	}
	
	private void battle() {
		ArrayList<Skill> skillList = mainPocketMon.skillList; 
		
		System.out.println("[스킬 목록]");
		for(int i = 0; i < skillList.size(); i++) {
			System.out.printf("[%d] %s ", i + 1, skillList.get(i).name);
		}
		System.out.printf("[%d] %s\n", skillList.size() + 1, "취소");

		int input = InputManager.getInstance().getScanner().nextInt();
		int skillIndex = input - 1;
		if (skillIndex < 0 || skillIndex >= skillList.size()) {
			return;
		}
		
		Skill skill = skillList.get(skillIndex);
		skill.fire(mainPocketMon, wildPocketMon);
		
		System.out.println("====================");
		
		if (wildPocketMon.isAlive() == false) {
			System.out.printf("적의 %s는(은) 쓰러졌다!!\n", wildPocketMon.name);

			mainPocketMon.addExp(wildPocketMon.exp);
			System.out.printf("경험치를 %d 획득했습니다.\n", wildPocketMon.exp);
			
			PageManager.getInstance().changePage(PageType.WORLD, null);
			return;
		}
		
		wildPocketMon.fireSkillByRandom(mainPocketMon);
		
		System.out.println();
		
		if (mainPocketMon.isAlive() == false) {
			System.out.printf("%s가(이) 쓰러졌다!!\n", mainPocketMon.name);
			PageManager.getInstance().changePage(PageType.WORLD, null);
			return;
		}
	}
	
	private void changePocketMon() {
		PageManager.getInstance().pushPage(PageType.CHANGE_POCKET_MON, null);
	}
	
	private void openInventory() {
		PageManager.getInstance().pushPage(PageType.INVENTORY, new PageInventoryData(wildPocketMon));
	}
	
	private void escape() {
		System.out.println("성공적으로 도망쳤다.");
		
		PageManager.getInstance().changePage(PageType.WORLD, null);
	}
}
