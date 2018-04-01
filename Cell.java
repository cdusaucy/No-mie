package Model;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Cell implements Serializable{
	private int posX;
	private int posY;
	private boolean presenceMur;
	private Game game;
	
	

	
	public Cell(int X, int Y, boolean acc,Game game){
		
		this.posX = X;
		this.posY = Y;
		this.presenceMur = acc;
		this.game = game;
		
	
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public boolean isWall(){
		return this.presenceMur;
	}
	
	public void setPresenceMur(boolean bool){
		this.presenceMur = bool;

	}

	public boolean getPresenceCaracters(int x, int y){
		return game.getMap()[x][y] == 2 || game.getMap()[x][y] == 3;
	}
	public boolean getPresencePatronus(int x, int y){
		return game.getMap()[x][y] == 4;
	}
	
	
}
