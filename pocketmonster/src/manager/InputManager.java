package manager;

import java.util.Scanner;

public class InputManager {
	
	private Scanner sc;
	
	private static InputManager instance;
	public static InputManager getInstance() {
		if (instance == null) {
			instance = new InputManager();
		}
		return instance;
	}
	
	private InputManager() {
		sc = new Scanner(System.in);
	}
	
	public void release() {
		sc.close();
	}
	
	public Scanner getScanner() {
		return sc;
	}
}
