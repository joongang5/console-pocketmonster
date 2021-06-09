package common;

import java.util.ArrayList;

import enums.TeamType;
import manager.TableDataManager;

public class PocketMon extends Character implements Cloneable {

	public int id;
	public int level;
	public int hp;
	public int maxHp;
	public int sp;
	public int maxSp;
	public int exp;
	public int maxExp;
	
	public ArrayList<Skill> skillList = new ArrayList<Skill>();
	
	public PocketMon(int id, String name, int level, int hp, int sp, int exp, ArrayList<Skill> skillList) {
		this.id = id;
		this.name = name;
		this.teamType = TeamType.NEUTRAL;
		this.level = level;
		this.maxHp = hp;
		this.hp = maxHp;
		this.maxSp = sp;
		this.sp = maxSp;
		this.maxExp = exp;
		this.exp = maxExp;
		
		this.skillList = skillList;
	}

	public PocketMon clone() throws CloneNotSupportedException {
		return (PocketMon)super.clone();
	}
	
	public void onDamaged(int value) {
		hp = Math.max(0, hp - value);
	}
	
	public void onRecovery(int value) {
		hp = Math.min(hp + value, maxHp);
	}
	
	public void addExp(int value) {
		int totalExp = exp + value;
		
		while (totalExp / maxExp >= 1) {
			totalExp -= maxExp;
			
			levelUp();
		}
		
		exp = totalExp;
	}
	
	public void levelUp() {
		level++;
		
		maxExp = TableDataManager.getInstance().expTable.get(level);
		
		System.out.printf("%s의 레벨이 올랐습니다!!\n", name);
	}
	
	public boolean isAlive() {
		if (hp <= 0) {
			return false;
		}
		return true;
	}
	
	public void fireSkill(int skillIndex, PocketMon target) {
		skillList.get(skillIndex).fire(this, target);
	}
	
	public void fireSkillByRandom(PocketMon target) {
		int randomIndex = (int)(Math.random() * skillList.size());
		fireSkill(randomIndex, target);
	}
}
