package Model;

import java.io.Serializable;

import View.Window;

public class Horcruxs extends Items implements Serializable {

	public Horcruxs(int x, int y, boolean b, Game game, Window window) {
		super(x, y, b, game, window);
		// TODO Auto-generated constructor stub
	}

	//		

	@Override
	public void action() {
		// TODO Auto-generated method stub
	}

	@Override
	public void goInventory(Inventory inv) {
		 inv.hor.add(new Horcruxs(0,0,false, super.getGame(),super.getWindow()));
	}
	

}
