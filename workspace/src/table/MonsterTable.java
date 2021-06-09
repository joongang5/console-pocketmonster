package table;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.PocketMon;
import common.Skill;
import manager.TableDataManager;

class MonsterData {
	public int id;
	public String name;
	public int level;
	public int hp;
	public int sp;
	public int exp;
	public int[] skillIds;
}

public class MonsterTable {

	private HashMap<Integer, MonsterData> map = new HashMap<Integer, MonsterData>();
	
	private MonsterData getData(String name) {
		for (Map.Entry<Integer, MonsterData> entry : map.entrySet()) {
			MonsterData data = entry.getValue();
			if (data.name.equals(name)) {
				return data; 
			}
		}
		return null;
	}
	
	private MonsterData getData(int id) {
		return map.get(id);
	}
	
	public PocketMon spawn(String name) {
		MonsterData data = getData(name);
		if (data == null) {
			return null;
		}

		return spawn(data);
	}
	
	public PocketMon spawn(int id) {
		MonsterData data = getData(id);
		if (data == null) {
			return null;
		}
		
		return spawn(data);
	}
	
	public PocketMon spawn(MonsterData data) {
		ArrayList<Skill> skillList = TableDataManager.getInstance().skillTable.getSkillList(data.skillIds);
		
		return new PocketMon(data.id, data.name, data.level, data.hp, data.sp, data.exp, skillList);
	}
	
	public PocketMon spawnRandom() {
		int random = (int)(Math.random() * map.size());

		int index = 0;
		for (int key : map.keySet()) {
			if (index++ == random) {
				return spawn(key);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJon() {
		String path = System.getProperty("user.dir") + "/data/MonsterTable.json";
		
		JSONParser parser = new JSONParser();
		
		try (Reader reader = new FileReader(path)) {
			JSONObject rootObj = (JSONObject)parser.parse(reader);
			
			JSONArray pocketMonList = (JSONArray)rootObj.get("array");
			
			Iterator<JSONObject> iter = pocketMonList.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();
				
				MonsterData data = new MonsterData();
				data.id = ((Long)obj.get("id")).intValue();
				data.name = (String)obj.get("name");
				data.level = ((Long)obj.get("level")).intValue();
				data.hp = ((Long)obj.get("hp")).intValue();
				data.sp = ((Long)obj.get("sp")).intValue();
				data.exp = ((Long)obj.get("exp")).intValue();
				
				JSONArray skillIds = (JSONArray)obj.get("skillIds");
				Iterator<JSONObject> skillIter = skillIds.iterator();
				data.skillIds = new int[skillIds.size()];
				int index = 0;
				while (skillIter.hasNext()) {
					JSONObject skillObj = skillIter.next();
					data.skillIds[index++] = ((Long)skillObj.get("id")).intValue();
				}
				
				map.put(data.id, data);
			}
		} catch (IOException e) {
			// do nothing.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}