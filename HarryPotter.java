package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class HarryPotter extends Caracters implements Serializable{
	private int doseVie;
	private Game game;
	private int perteVie = 1;
	private Inventory inventory;

	public HarryPotter(int X, int Y, int doseVie, Game game) {
        super(X, Y, game);
        inventory = new Inventory();
        this.doseVie = doseVie;
        this.game = game;
        // TODO Auto-generated constructor stub
    }

    public void move(int X, int Y) {
        this.setPosX(X + getPosX());
        this.setPosY(Y + getPosY());
    }
    
   public int getDoseVie(){
		return this.doseVie;
	}
	public void setDoseVie (int dose){
	    this.doseVie = dose;
	}
	
	public boolean posHarryMm(){
		int xh = this.getPosX();
		int yh = this.getPosY();
		ArrayList<MangeMort> mm = game.getMangeMorts();
		for(MangeMort mechant : mm)
			if(mechant.isAt(xh, yh))
				return true;
		return false;
	}
	public boolean posHarryFire(){
		int xh = this.getPosX();
		int yh = this.getPosY();
		ArrayList<Fire> fr = game.getFire();
		for(Fire f : fr)
			if(f.isAt(xh, yh))
				return true;
		return false;
	}
	
	public void dimVie (){
		if (posHarryMm() || posHarryFire()){
			if (this.doseVie == 0){
				game.setIsOver(true);
			}else {
				setDoseVie(this.doseVie - this.perteVie);
				System.out.println("lose");
			}
		}
	}
	public static int LEFT = 0;
	public static int RIGHT = 1;
	public static int UP = 2;
	public static int DOWN = 3;
	
	public void movePlayer(int direction){
		// Harry prend patr sur son passage 
		for (Patronus patronus : game.getPatronus()){
			patronus.goInPocket();
		}
		Iterator<Patronus> it = game.getPatronus().iterator();
		while(it.hasNext()){
			Patronus patronus = it.next();
			if (patronus.isVisible()==false){
				it.remove();
			}
		}
		//Harry prend ic sur son passage
		for (InvisibilityCloak inv : game.getInvCloak()){
			inv.goInPocket();
			
		}
		Iterator<InvisibilityCloak> it1 = game.getInvCloak().iterator();
		while(it1.hasNext()){
			InvisibilityCloak inv = it1.next();
			if (inv.isVisible()==false){
				it1.remove();
			}
		}
		//Harry prend bs sur son passage
		for (BroomStick broom : game.getBroomStick()){
			broom.goInPocket();
		}
		Iterator<BroomStick> it3 = game.getBroomStick().iterator();
		while(it3.hasNext()){
			BroomStick bs = it3.next();
			if (bs.isVisible()==false){
				it3.remove();
			}
		}
		for(MagicalPotion hmp : game.getMagicalPotion()){
			hmp.goInPocket();
		}
		Iterator<MagicalPotion> it2 = game.getMagicalPotion().iterator();
		while(it2.hasNext()){
			MagicalPotion mp = it2.next();
			if (mp.isVisible()== false){
				it2.remove();
			}
		}
		for (Horcruxs hor : game.getHorcruxs()){
			hor.goInPocket();
			
		}
		Iterator<Horcruxs> it4 = game.getHorcruxs().iterator();
		while(it4.hasNext()){
			Horcruxs hor = it4.next();
			if (hor.isVisible()==false){
				it4.remove();
			}
		}
		HarryPotter harry = game.getHarry();
		
		int xBefore = harry.getPosX();
		int yBefore = harry.getPosY();
		
		int[] newPos = getNewPos(xBefore, yBefore, direction);
		ArrayList<MagicalPotion> mp = game.getHarry().getInventory().mp;
		if (mp.size() != 0){

			if (mp.get(0).getActionTime()){
				harry.move(newPos[0] - xBefore, newPos[1] - yBefore);
			}else{
				if (isOutside(newPos) || game.getMatrix()[newPos[0]][newPos[1]].isWall()){
					harry.move(0, 0);
				}else{
					harry.move(newPos[0] - xBefore, newPos[1] - yBefore);
				}
			}
		}else {
			if (isOutside(newPos) || game.getMatrix()[newPos[0]][newPos[1]].isWall()){
				harry.move(0, 0);
			}else{
				harry.move(newPos[0] - xBefore, newPos[1] - yBefore);
			}}
		game.getWindow().draw(game.getMap(),game.getHarry());			
	}
	
	private boolean isOutside(int[] position){
		return isOutside(position[0], position[1]);
	}
	public boolean isOutside(int xh, int yh) {
		return xh > game.getSize() || xh < 0 || yh > game.getSize() || yh < 0;
	}
	
	int[] getNewPos(int xBefore, int yBefore, int direction){
		int xAfter = xBefore;
		int yAfter = yBefore;
		if(direction == LEFT){
			xAfter = xBefore - 1;
		} else if(direction == RIGHT){
			xAfter = xBefore + 1;
		} else if(direction == UP){
			yAfter = yBefore - 1;
		} else if(direction == DOWN){
			yAfter = yBefore + 1;
		}
		return new int[]{xAfter, yAfter};
	}
	

	public void movePlayerRight(){
		movePlayer(RIGHT);
	}
	

	public void movePlayerDown(){
		movePlayer(DOWN);
	}

	public void movePlayerUp(){
		movePlayer(UP);
	}
	

	public void movePlayerLeft(){
		movePlayer(LEFT);
	}

	public Inventory getInventory() {
		return inventory;
	}
}