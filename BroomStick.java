package Model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import View.Window;

public class BroomStick extends Items {

	private Random r = new Random();

	public BroomStick(int x, int y,boolean b, Game game,Window window){
		super(x, y, b, game, window);
	}
	

	public void appear() {
		// TODO Auto-generated method stub
	}

	@Override
	public void action(){
		HarryPotter harry = super.getGame().getHarryPotter().get(0);
		MangeMort mechant = super.getGame().getMangeMorts().get(0);
		
		int x = getGame().getXb() - 7 + r.nextInt(getGame().getXb() + 6);
		int y = getGame().getYb() - 7 + r.nextInt(getGame().getYb() + 6);
		while(getGame().hasCharacterAt(x,y)) {
			x = r.nextInt(getGame().getSize());
			y = r.nextInt(getGame().getSize());
		}
		
		super.getGame().setHarryPotterX(x);
		super.getGame().setHarryPotterY(y);
	}


	@Override
	public void goInventory(Inventory inv){
		inv.sbs.add(new BroomStick(0,0,false,super.getGame(),super.getWindow()));
	}
	
}
