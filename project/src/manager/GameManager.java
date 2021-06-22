package manager;

import enums.PageType;

import java.io.File;
import java.io.FileWriter;

import org.json.simple.JSONObject;

import common.Player;

public class GameManager {

	public void init() {
		PageManager.getInstance().changePage(PageType.LOADING, null);
	}
	
	public void release() {
		InputManager.getInstance().release();
		saveJson();
	}
	
	public void start() {
		PageManager pm = PageManager.getInstance();
		
		while (true) {
			PageManager.getInstance().update();
			
			if (pm.getCurrentPageType() == PageType.EXIT) {
				break;
			}
		}
	}

	private void saveJson() {
		String dirPath = System.getProperty("user.dir") + "/data";
		String filePath = dirPath + "/Save.json";
		
		File dir = new File(dirPath);
		if (dir.exists() == false) {
			dir.mkdir();
		}
		
		JSONObject obj = Player.getInstance().getJsonData();
		
		try (FileWriter fw = new FileWriter(filePath)) {
			fw.write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}