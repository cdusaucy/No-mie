package Model;

import java.io.Serializable;
import java.util.Random;
@SuppressWarnings("serial")
public class Caracters implements Serializable{
	private int posX;
	private int posY;
	private Game game;
	
	public Caracters(int X, int Y,Game game){
		this.posX = X;
		this.posY = Y;
		this.game = game;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}

	protected void setPosX(int X){
		this.posX = X;
	}
	
	protected void setPosY(int Y){
		this.posY = Y;
	}
    
    public boolean isOutside(int x, int y) {
		return x > game.getSize() -1  || x < 0 || y > game.getSize()-1 || y < 0;
	}
	
	public boolean accessibleCell(int x, int y){
		return !isOutside(x, y) && !game.getCell(x, y).isWall();
	}
	
	  public void moveDir( int X, int Y) {
	        this.posX = this.posX + X;
	        this.posY = this.posY + Y;
	    }

	
	public void moveRandom(){
		int dir = new Random().nextInt(4);
		if (dir ==0){
			if(accessibleCell(getPosX()+1,getPosY()))
				this.setPosX(this.getPosX() + 1);
		}
		else if(dir == 1){
			if(accessibleCell(getPosX()-1,getPosY()))
				this.setPosX(this.getPosX() - 1);
		}
		else if (dir == 2){
			if(accessibleCell(getPosX(),getPosY()+1))
				this.setPosY(this.getPosY() + 1);
		}
		else{
			if(accessibleCell(getPosX(),getPosY()-1))
				this.setPosY(this.getPosY() - 1);
		}	
	}
	public void moveToHarry(){
		HarryPotter harry = game.getHarryPotter().get(0);
		int xh = harry.getPosX();
		int yh = harry.getPosY();
		int xmm = this.getPosX();
		int ymm = this.getPosY();
		if (ymm < yh && xmm < xh) {
			if (xmm-xh <= ymm-yh) {
				if(accessibleCell(getPosX(),getPosY()+1))
					this.setPosY(ymm + 1);
			} else {
				if(accessibleCell(getPosX()+1,getPosY()))
					this.setPosX(xmm + 1);
			}
		} else if(ymm < yh && xmm > xh) {
			if (xmm-xh <= ymm-yh){
				if(accessibleCell(getPosX(),getPosY()+1))
					this.setPosY(ymm + 1);
		    } else {
		    	if(accessibleCell(getPosX()-1,getPosY()))
		    		this.setPosX(xmm - 1);	
		    }
		} else if (ymm > yh && xmm < xh){
			if (xmm-xh <= ymm-yh){
				if(accessibleCell(getPosX(),getPosY()-1))
					this.setPosY(ymm - 1);
			} else {
				if(accessibleCell(getPosX()+1,getPosY()))
					this.setPosX(xmm + 1);
			}
		}else if (ymm > yh && xmm > xh){
			if (xmm-xh <= ymm-yh){
				if(accessibleCell(getPosX(),getPosY()-1))
					this.setPosY(ymm - 1);
			}else {
				if(accessibleCell(getPosX()-1,getPosY()))
					this.setPosX(xmm - 1);
			}
		}else if(xmm == xh){
			if (ymm > yh){
				if(accessibleCell(getPosX(),getPosY()-1))
					this.setPosY(ymm - 1);
			} else {
				if(accessibleCell(getPosX(),getPosY()+1))
					this.setPosY(ymm + 1);
			}
		}else if(ymm == yh){
			if (xmm > xh){
				if(accessibleCell(getPosX()-1,getPosY()))
					this.setPosX(xmm - 1);
			} else {
				if(accessibleCell(getPosX()+1,getPosY()))
					this.setPosX(xmm + 1);
			}
		}
	}

	public Game getGame() {
		return game;
	}
	
	public boolean isAt(int x, int y) {
		return posX == x && posY == y;
	}
	
	public boolean isAtSamePos(Caracters c) {
		return isAt(c.getPosX(), c.getPosY());
	}

}
   

		