package item;

import common.Player;
import common.PocketMon;
import table.data.ItemData;

public class MonsterBall extends Item {

	public MonsterBall(ItemData data) {
		super(data);
		
		count = 99;
	}
	
	@Override
	public void use(PocketMon target) {
		super.use(target);
		
		if (isSuccess()) {
			System.out.printf("신난다!\n%s(을)를 잡았다.\n", target.name);
			
			Player.getInstance().addPocketMon(target);
			
		} else {
			System.out.printf("%s(은)는 격렬히 저항했다!\n", target.name);
		}
	}
	
	private boolean isSuccess() {
		
		return true;
	}
}
