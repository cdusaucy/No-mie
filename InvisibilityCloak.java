package Model;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import View.Window;


public class InvisibilityCloak extends Items implements Runnable {
	private boolean actionTime;
	private int time  = 10000;
	private transient Thread thread;

	public InvisibilityCloak(int x, int y, boolean b,boolean n, Game game, Window window) {
		super(x, y, b, game, window);
		this.actionTime = n;
		this.thread = new Thread(this);
	}
	public boolean getActionTime(){
		return this.actionTime;
	}
	public void setActionTime(boolean b){
		this.actionTime = b;
	}

	public void appear() {
		// TODO Auto-generated method stub
	}
	
	public boolean posHarryCloak(){
		return getGame().getHarry().isAt(getXPosition(), getYPosition());
	}

	@Override
	public void goInventory(Inventory inv) {
		inv.sic.add(new InvisibilityCloak(0,0,false,false,getGame(),getWindow()));
	}

	@Override
	public void action() {
		thread.start();
	}
	@Override
	public void run() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getGame().getHarry().getInventory().sic.remove(0);
	}
}

