package page;

import enums.PageType;

public class PageExit extends Page {

	public PageExit() {
		name = "종료";
	}
	
	@Override
	public PageType getType() {
		return PageType.EXIT;
	}
	
	@Override
	public void init() {
		// do nothing.
	}
	
	@Override
	public void printAction() {
		System.out.println("프로그램이 종료되었습니다.");
	}
}
