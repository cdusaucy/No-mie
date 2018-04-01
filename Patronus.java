package Model;

import java.util.ArrayList;
import java.util.Iterator;
import View.Window;

public class Patronus extends Items {
	private int rayon = 5;
	
	public Patronus(int x, int y, boolean b, Game game, Window window) {
		super(x, y, b, game, window);
		// TODO Auto-generated constructor stub
	}



	public void appear() {
		// TODO Auto-generated method stub

	}


	public void action() {
		HarryPotter harry = getGame().getHarry();
		int xh = harry.getPosX();
		int yh = harry.getPosY();
		ArrayList <MangeMort> mm = getGame().getMangeMorts();
		Iterator<MangeMort> it = mm.iterator();
		while(it.hasNext()){
			MangeMort mechant = it.next();
			int xmm = mechant.getPosX();
			int ymm = mechant.getPosY();
			if (Math.abs(xmm - xh) <= rayon && Math.abs(ymm - yh) <= rayon){
				it.remove();
			}
		}
	}

	@Override
	public void goInventory(Inventory inv){
		inv.patr.add(new Patronus(0,0,false,super.getGame(),super.getWindow()));
	}

}
