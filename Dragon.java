package Model;

import java.io.Serializable;
import java.util.Random;

import javax.swing.Timer;

public class Dragon extends Caracters implements Serializable{

	public Dragon(int X, int Y, Game game) {
		super(X, Y, game);
	}
	public void move(){
		int dir = new Random().nextInt(4);
		// nextPos = getPos() + vecteur(dir)
		// if accessibleCell(nextPos)
		//    setPos(nextPos)
		if (dir == 0){
			if(accessibleCell(getPosX()+1,getPosY()))
				super.setPosX(super.getPosX() + 1);
		}
		else if(dir == 1){
			if(accessibleCell(getPosX()-1,getPosY()))
				super.setPosX(super.getPosX() - 1);
		}
		else if (dir == 2){
			if(accessibleCell(getPosX(),getPosY()+1))
				super.setPosY(super.getPosY() + 1);
		}
		else{
			if(accessibleCell(getPosX(),getPosY()-1))
				super.setPosY(super.getPosY() - 1);
		}
	}
	public void attack(){
		HarryPotter harry = getGame().getHarryPotter().get(0);
		int xh = harry.getPosX();
		int yh = harry.getPosY();
		int xd = this.getPosX();
		int yd = this.getPosY();
		if (yh>yd && Math.abs(yd-yh)<=Math.abs(xd-xh)){
			getGame().getFire().add(new Fire(xd,yd,getGame(),1,false));
		} else if (yh<yd && Math.abs(yd-yh)<=Math.abs(xd-xh)){
			getGame().getFire().add(new Fire(xd,yd,getGame(),0,false));
		} else if (xh<xd &&Math.abs(yd-yh)>=Math.abs(xd-xh)){
			getGame().getFire().add(new Fire(xd,yd,getGame(),2,false));
		} else if (xh>xd &&Math.abs(yd-yh)>=Math.abs(xd-xh)){
			getGame().getFire().add(new Fire(xd,yd,getGame(),3,false));
		}
	}
}
