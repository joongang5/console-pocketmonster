package common;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enums.TeamType;
import manager.TableDataManager;

public class Player extends Character {

	public ArrayList<PocketMon> pocketMonList = new ArrayList<PocketMon>();
	
	private final int MAIN_SLOT_INDEX = 0;

	private static Player instance;
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}
	
	private Player() {
		teamType = TeamType.FRIENDLY;
	}
	
	public boolean wasInit() {
		if (pocketMonList.size() > 0) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			return;
		}
		
		this.name = name;
	}
	
	public PocketMon getMainPocketMon() {
		return pocketMonList.get(MAIN_SLOT_INDEX);
	}
	
	public PocketMon changeMainPocketMon(int index) {
		if (index < 0 || index >= pocketMonList.size())
			return null;
		
		PocketMon temp = pocketMonList.get(index);
		pocketMonList.set(index, pocketMonList.get(MAIN_SLOT_INDEX));
		return pocketMonList.set(MAIN_SLOT_INDEX, temp);
	}
	
	public void addPocketMon(PocketMon pocketMon) {
		pocketMonList.add(pocketMon);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getJsonData() {
		JSONObject innerObj = new JSONObject();
		innerObj.put("name", name);
		
		JSONArray arr = new JSONArray();
		for (PocketMon pm : pocketMonList) {
			JSONObject obj = new JSONObject();
			obj.put("id", pm.id);
			obj.put("level", pm.level);
			obj.put("hp", pm.hp);
			obj.put("maxHp", pm.maxHp);
			obj.put("sp", pm.sp);
			obj.put("maxSp", pm.maxSp);
			obj.put("exp", pm.exp);
			obj.put("maxExp", pm.maxExp);

			arr.add(obj);
		}
		innerObj.put("pocketMonList", arr);
		
		JSONObject outerObj = new JSONObject();
		outerObj.put("player", innerObj);
		
		return outerObj;
	}

	@SuppressWarnings("unchecked")
	public void loadSaveFile() {
		String path = System.getProperty("user.dir") + "/data/Save.json";
		
		JSONParser parser = new JSONParser();
		
		try (Reader reader = new FileReader(path)) {
			JSONObject rootObj = (JSONObject)parser.parse(reader);
			
			JSONObject playerObj = (JSONObject)rootObj.get("player");
			String playerName = (String)playerObj.get("name");
			Player.getInstance().setName(playerName);
			
			JSONArray pocketMonList = (JSONArray)playerObj.get("pocketMonList");
			Iterator<JSONObject> iter = pocketMonList.iterator();
			while (iter.hasNext()) {
				JSONObject pocketMonObj = iter.next();
				
				int id = ((Long)pocketMonObj.get("id")).intValue();
				
				PocketMon pocketMon = TableDataManager.getInstance().monsterTable.spawn(id);
				pocketMon.level = ((Long)pocketMonObj.get("level")).intValue();
				pocketMon.hp = ((Long)pocketMonObj.get("hp")).intValue();
				pocketMon.maxHp = ((Long)pocketMonObj.get("maxHp")).intValue();
				pocketMon.sp = ((Long)pocketMonObj.get("sp")).intValue();
				pocketMon.maxSp = ((Long)pocketMonObj.get("maxSp")).intValue();
				pocketMon.exp = ((Long)pocketMonObj.get("exp")).intValue();
				pocketMon.maxExp = TableDataManager.getInstance().expTable.get(pocketMon.level);
				pocketMon.teamType = TeamType.FRIENDLY;

				addPocketMon(pocketMon);
			}
		} catch (IOException e) {
			// do nothing.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
