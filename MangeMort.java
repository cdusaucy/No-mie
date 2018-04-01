package Model;
import Model.Game;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import java.lang.Double;
import View.*;
public class MangeMort extends Caracters implements Serializable{
    private Game game;
    private int rayon = 5;
    private Window window;
    private Cell cell;
	
    public MangeMort(Game game, int X, int Y) {
		super(X, Y, game);
		this.game = game;
	}
    
	public void move() {
		HarryPotter harry = game.getHarryPotter().get(0);
		harry.dimVie(); // harry perde de la vie quand mange mort bouge
		int xh = harry.getPosX();
		int yh = harry.getPosY();
		System.out.println(1);
		if (harry.getInventory().sic.size() != 0){
			System.out.println(2);
			if (harry.getInventory().sic.get(0).getActionTime()){
				System.out.println(3);
				this.moveRandom();
			} else {
				this.moveInt();
			}
		} else {
			this.moveInt();
		}
	}
	
	public boolean isOutside(int x, int y) {
		return x > game.getSize() -1  || x < 0 || y > game.getSize()-1 || y < 0;
	}
	
	public boolean accessibleCell(int x, int y){
			if(!isOutside(x,y)){
				return !game.getCell(x, y).isWall();
			}else {
				return false;
			}
	}
	
	
	
	
	public void moveRandom(){
		int dir = (int)(Math.random() * (3));
		if (dir ==0){
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
	public void moveToHarry(){
		HarryPotter harry = game.getHarryPotter().get(0);
		int xh = harry.getPosX();
		int yh = harry.getPosY();
		int xmm = super.getPosX();
		int ymm = super.getPosY();
		if (ymm < yh && xmm < xh) {
			if (xmm-xh <= ymm-yh) {
				if(accessibleCell(getPosX(),getPosY()+1))
				super.setPosY(ymm + 1);
			} else {
				if(accessibleCell(getPosX()+1,getPosY()))
				super.setPosX(xmm + 1);
			}
		} else if(ymm < yh && xmm > xh) {
			if(accessibleCell(getPosX()-1,getPosY()))
			if (xmm-xh <= ymm-yh){
				if(accessibleCell(getPosX(),getPosY()+1))
					super.setPosY(ymm + 1);
		    } else {
		    	if(accessibleCell(getPosX()-1,getPosY()))
		    		super.setPosX(xmm - 1);	
		    }
		} else if (ymm > yh && xmm < xh){
			if (xmm-xh <= ymm-yh){
				if(accessibleCell(getPosX(),getPosY()-1))
					super.setPosY(ymm - 1);
			} else {
				if(accessibleCell(getPosX()+1,getPosY()))
					super.setPosX(xmm + 1);
			}
		}else if (ymm > yh && xmm > xh){
			if (xmm-xh <= ymm-yh){
				if(accessibleCell(getPosX(),getPosY()-1))
					super.setPosY(ymm - 1);
			}else {
				if(accessibleCell(getPosX()-1,getPosY()))
					super.setPosX(xmm - 1);
			}
		}else if(xmm == xh){
			if (ymm > yh){
				if(accessibleCell(getPosX(),getPosY()-1))
					super.setPosY(ymm - 1);
			} else {
				if(accessibleCell(getPosX(),getPosY()+1))
					super.setPosY(ymm + 1);
			}
		}else if(ymm == yh){
			if (xmm > xh){
				if(accessibleCell(getPosX()-1,getPosY()))
					super.setPosX(xmm - 1);
			} else {
				if(accessibleCell(getPosX()+1,getPosY()))
					super.setPosX(xmm + 1);
			}
		}
	}
	public void moveInt (){
		HarryPotter harry = game.getHarryPotter().get(0);
		int xh = harry.getPosX();
		int yh = harry.getPosY();
		int xmm = this.getPosX();
		int ymm = this.getPosY();
		if (Math.abs(xmm - xh) <= rayon && Math.abs(ymm - yh) <= rayon){
			this.moveToHarry();	
		} else {
			this.moveRandom();
		}
	}

}