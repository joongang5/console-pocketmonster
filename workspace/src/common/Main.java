package common;

import manager.GameManager;

public class Main {

	public static void main(String[] args) {

		GameManager gameManager = new GameManager();
		gameManager.init();
		gameManager.start();
		gameManager.release();		
	}
}
