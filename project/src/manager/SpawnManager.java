package manager;

import java.util.ArrayList;

import common.PocketMon;
import common.Skill;
import table.data.MonsterData;
import table.data.SkillData;

public class SpawnManager {

	private static SpawnManager instance;
	public static SpawnManager getInstance() {
		if (instance == null) {
			instance = new SpawnManager();
		}
		return instance;
	}
	
	private SpawnManager() {}
	

	public PocketMon spawnPocketMon(String name) {
		MonsterData data = TableDataManager.getInstance().monsterTable.getData(name);
		if (data == null) {
			return null;
		}

		return spawnPocketMon(data);
	}
	
	public PocketMon spawnPocketMon(int id) {
		MonsterData data = TableDataManager.getInstance().monsterTable.getData(id);
		if (data == null) {
			return null;
		}
		
		return spawnPocketMon(data);
	}
	
	public PocketMon spawnPocketMon(MonsterData data) {
		ArrayList<Skill> skillList = new ArrayList<Skill>();
		for (int id : data.skillIds) {
			skillList.add(spawnSkill(id));
		}
		
		return new PocketMon(data.id, data.name, data.level, data.hp, data.sp, data.exp, skillList);
	}
	
	public PocketMon spawnRandomPocketMon() {
		MonsterData data = TableDataManager.getInstance().monsterTable.getRandomData();

		return spawnPocketMon(data);
	}
	
	public Skill spawnSkill(int id) {
		SkillData data = TableDataManager.getInstance().skillTable.get(id);
		
		return new Skill(data.id, data.name, data.type, data.level, data.value, data.sp);
	}
}
