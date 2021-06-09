package manager;

import table.*;

public class TableDataManager {
	
	public MonsterTable monsterTable;
	public SkillTable skillTable;
	public ExpTable expTable;
	
	private static TableDataManager instance;
	public static TableDataManager getInstance() {
		if (instance == null) {
			instance = new TableDataManager();
		}
		return instance;
	}
	
	private TableDataManager() {
		monsterTable = new MonsterTable();
		skillTable = new SkillTable();
		expTable = new ExpTable();
	}
	
	public void load() {
		monsterTable.loadJon();
		skillTable.loadJon();
		expTable.load();
	}
}
