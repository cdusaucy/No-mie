package Model;

import java.io.Serializable;

import View.Window;

@SuppressWarnings("serial")
public abstract class Items implements Serializable{
	private int x;
	private int y;
	private boolean isVisible;
	private Window window;
	private Game game;
	
	public Items(int x, int y,boolean b, Game game,Window window){
		this.x = x;
		this.y = y;
		this.game = game;
		this.window = window;
		this.isVisible = b;
	}
	public int getXPosition() {
		return this.x;
	}

	public int getYPosition() {
		return this.y;
	}
	
	public boolean isVisible(){
		return this.isVisible;
	}
	public Game getGame(){
		return this.game;
	}
	public Window getWindow(){
		return this.window;
	}
	
	public void setIsVisible(boolean b){
		this.isVisible = b;
	}
	public void goInPocket(){
		if(game.getHarry().isAt(getXPosition(), getYPosition())){
			this.setIsVisible(false);
			this.goInventory(game.getHarry().getInventory());
			//window.draw(game.getMap(),game.getHarry());
		} 
	}
	public abstract void goInventory(Inventory inv);
	public abstract void action();
}
