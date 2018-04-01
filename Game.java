package Model;

import Model.Cell;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import View.Window;
import Model.InvisibilityCloak;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.Iterator;
/*
import java.util.Timer;
import java.util.TimerTask;
*/
import java.util.Random;

public class Game implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<HarryPotter> harrys = new ArrayList<HarryPotter>();
	private ArrayList<MangeMort> mm = new ArrayList<MangeMort> ();
	private ArrayList<InvisibilityCloak> ic = new ArrayList<InvisibilityCloak>();
	private ArrayList<Patronus> patr = new ArrayList<Patronus>();
	private ArrayList<BroomStick> bs  = new ArrayList<BroomStick>();
	private ArrayList<Fire> fire  = new ArrayList<Fire>();
	private ArrayList<Dragon> drg  = new ArrayList<Dragon>();
	private ArrayList<Horcruxs> hhor = new ArrayList<Horcruxs>();
	private ArrayList<MagicalPotion> mp = new ArrayList<MagicalPotion>();
	
	private int size =100;
	private Cell [][] matrix = new Cell [size][size];
	
	private Window window;
	
	private int viewSize = 20;
	private boolean isOver;
	private int xb;
	private int yb;

	public enum STATE{
		MENUFRAME,
		MENURULES,
		MENUCOMMAND,
		GAMEOVER,
		START,
	}
	
	
	private STATE state = STATE.START;  //modifie la page d'acceuil
	

	

	public Game(Window window,boolean b, int xb,int yb){
		this.window = window;
		this.isOver = b;
		this.xb=xb;
		this.yb=yb;
		for(int i = 0; i < size; i++){
			   for(int j = 0; j < size; j++){
					 matrix[i][j] = new Cell(i,j,false,this); 
				}
			}
			Random r = new Random();
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++)
					if(r.nextInt(4) == 0){
						//if ( i!= getHarry().getPosX() && j!= getHarry().getPosY()){
							matrix[i][j].setPresenceMur(true);
						//} else {
							//matrix[i][j].setPresenceMur(false);
						//}
					} else {
						 matrix[i][j].setPresenceMur(false);
					}
			}
			for(int i = 0; i < size ; i++){//mur
			    matrix[i][0].setPresenceMur(true);
			    matrix[i][size-1].setPresenceMur(true);
			}
					
			for(int j = 0; j < size ; j++){ //mur
			    matrix[0][j].setPresenceMur(true);
			    matrix[size-1][j].setPresenceMur(true);
			}
			 
	    // Harry Potter
		harrys.add(new HarryPotter(10,10,100,this));

	    // Mange-Morts
		mm.add(new MangeMort(this, 7, 3));
		mm.add(new MangeMort(this, 5,5));
		
		// Patronus
		patr.add(new Patronus(11,11,true,this,window));
		
		
				
		//cape invisible
		ic.add(new InvisibilityCloak(12,12,true,false,this,window));
		
		//Balais Magique
		bs.add(new BroomStick(4,16,true,this,window));
		// Dragon
		drg.add(new Dragon(2,2,this));
		
		mp.add(new MagicalPotion(4,4,true,false,this,window));
		
		
		window.draw(this.getMap(),this.getHarry());
		
		//Timer
		Timer timerappear = new Timer(700, new TimerTaskAppear(this, window));
		timerappear.setRepeats(true);
		timerappear.start();
		Timer timermove = new Timer(700, new TimerMove(this, window));
		timermove.setRepeats(true);
		timermove.start();
		Timer timerDrg = new Timer(2500, new TimerDragon(this, window));
		timerDrg.setRepeats(true);
		timerDrg.start();
		Timer timerItems = new Timer(1000, new TimerAppearItems(this, window));
		timerItems.setRepeats(true);
		timerItems.start();
		
	}	
	public Window getWindow(){
		return this.window;
	}
	public Cell[][] getMatrix(){
		return this.matrix;
	}
	public int getSize(){
		return this.size;
	}
	public boolean getIsOver(){
		return this.isOver;
	} 
	public int getXb(){
		return this.xb;
	}
	public int getYb(){
		return this.yb;
	}
	public void setIsOver(boolean b){
		this.isOver = b;
	}
	public void setSize(int s){
		this.size = s;
	}
	
	public void setMatrix(Cell[][] m ){
		this.matrix = m;
	}
	public void setXb (int x){
		this.xb = x;
	}
	public void setYb (int y){
		this.yb = y;
	}

	public HarryPotter getHarry() {
		return harrys.get(0);
	}
	public Cell getCell(int x, int y){
		return this.matrix[x][y];
	}
	
	public void moveMangeMorts(){
		for(MangeMort mechant : mm ){
			mechant.move();
			//window.draw(this.getMap(),this.getHarry());
		} 
	}
	public void instanceHorcruxs (int size,ArrayList<Horcruxs> hhor ){
		hhor.add(new Horcruxs(9,6,true,this,window));hhor.add(new Horcruxs(size-3,size/6,true,this,window));
		hhor.add(new Horcruxs(3*size/4,size/2,true,this,window));
		hhor.add(new Horcruxs(2*size/7,size-5,true,this,window));
		hhor.add(new Horcruxs(size/2 - 4,size/2 + 4 ,true,this,window));
		hhor.add(new Horcruxs(5*size/6, 6 ,true,this,window));
		hhor.add(new Horcruxs(4*size/6, 2*size/3 ,true,this,window));
	}
            
	public int[][] getMap(){
		int[][] mapi = new int[this.size][this.size];
		if (this.getIsOver()){
			return mapi = null;
		}else {
			for(int i = 0; i<this.size; i++)
				for(int j = 0; j<this.size; j++)
					mapi[i][j] = 0;
			
			for(HarryPotter player : harrys){
				int x = player.getPosX();
				int y = player.getPosY();
				mapi[x][y] = 2;
			}
			 
	    	for(int i = 0; i < size; i++){
			   for(int j = 0; j < size; j++){
				   if( matrix[i][j].isWall()){
					   mapi[i][j] = 1; 
				   } 
			   }
	    	}
	    	
			for(MangeMort mechant : mm){
				int x = mechant.getPosX();
				int y = mechant.getPosY();
				mapi[x][y] = 3;
			}
			
			for (Patronus patronus : patr){
				int x = patronus.getXPosition();
				int y = patronus.getYPosition();
				if (patronus.isVisible()){
					mapi[x][y] = 4;
				} else {
					mapi[x][y] = 0;
				}
			}
			for (InvisibilityCloak inv : ic){
					int x = inv.getXPosition();
					int y = inv.getYPosition();
					if (inv.isVisible()){
						mapi[x][y] = 5;
					} else {
						mapi[x][y] = 0;
					}
			}
			for (Horcruxs horcrux : hhor){
				int x = horcrux.getXPosition();
				int y = horcrux.getYPosition();
				if (horcrux.isVisible()){				
					mapi[x][y] = 9;
				} else {
					mapi[x][y] = 0;
				}
			}
			for (BroomStick broom : bs){
				int xb = broom.getXPosition();
				int yb = broom.getYPosition();
				if (broom.isVisible()){
					mapi[xb][yb] = 6;
				} else {
					mapi[xb][yb] = 0;
				}		
		}
			for(Dragon dragon : drg){
				int x = dragon.getPosX();
				int y = dragon.getPosY();
				mapi[x][y] = 7;
			}
			for(Fire fr : fire){
				int x = fr.getPosX();
				int y = fr.getPosY();
				mapi[x][y] = 8;
			}
			for (MagicalPotion hmp : mp){
				int xmp = hmp.getXPosition();
				int ymp = hmp.getYPosition();
				if (hmp.isVisible()){
					mapi[xmp][ymp] = 10;
				} else {
					mapi[xmp][ymp] = 0;
				}
		}
			int xh = getHarry().getPosX();
			int yh = getHarry().getPosY();
			if (xh != 0 && xh != 1 && xh != 2 && xh != size - 2 && xh != size -3 && xh != size - 4
			 && yh != 0 && yh != 1 && yh != 2 && yh != size - 2 && yh != size -3 && yh != size - 4){
				if (xh == xb-viewSize/2+2){
					xb -= 1;
				}else if(xh == xb+viewSize/2-4){
					xb += 1;
				}else if(yh == yb-viewSize/2+2){
					yb -= 1;
				}else if(yh == yb+viewSize/2-4){
					yb += 1;
				}
			}
			int[][] map = new int[20][20];
			for (int i =0; i<20;i++){
				for (int j = 0; j<20;j++){
					map[i][j]=mapi[xb-10+i][yb-10+j];
				}
			}
			return map;
		}
	}

	public void noMoreFire(){
		Iterator<Fire> it = fire.iterator();
		while(it.hasNext()){
			Fire fire = it.next();
			if (fire.getAgainstWall()==true){
				it.remove();
			}
		}
	}
	
	public ArrayList<HarryPotter> getHarryPotter(){
		return this.harrys;
	}

	public ArrayList<MangeMort> getMangeMorts(){
		return this.mm;
	}
	
	public ArrayList<Patronus> getPatronus(){
		return this.patr;
	}
	public ArrayList<Dragon> getDragon(){
		return this.drg;
	}
	public ArrayList<MagicalPotion> getMagicalPotion(){
		return this.mp;
	}
	public ArrayList<Horcruxs> getHorcruxs() {
		return hhor;
	}

	
	public ArrayList<BroomStick> getBroomStick(){
		return this.bs;
	}
	public ArrayList<Fire> getFire(){
		return this.fire;
	}
	public ArrayList<InvisibilityCloak> getInvCloak(){
		return this.ic;
	}
	
	public void actionPatronus(){
		ArrayList<Patronus> harryspatr = getHarry().getInventory().patr;
		if (harryspatr != null){
			harryspatr.get(0).action();
			harryspatr.remove(0);
			window.draw(this.getMap(), this.getHarry());
			window.map.repaint();
		}
		
		
	}
	public void actionMp(){
		ArrayList<MagicalPotion> harrysmp = getHarry().getInventory().mp;
		if (harrysmp != null){
			harrysmp.get(0).setActionTime(true);
			harrysmp.get(0).action();
			window.draw(this.getMap(), this.getHarry());
			window.map.repaint();
		}
		
	}
	public void actionIc(){
		ArrayList<InvisibilityCloak> harrysic = getHarry().getInventory().sic;
		if (harrysic != null){
			harrysic.get(0).setActionTime(true);
			harrysic.get(0).action();
			window.draw(this.getMap(), this.getHarry());
			window.map.repaint();
		}
		
	}
	
	public void actionBs(){
		ArrayList<BroomStick> harrysbs = getHarry().getInventory().sbs;
		if (harrysbs != null){
			harrysbs.get(0).action();
			harrysbs.remove(0);
		}
		
	}
	
	public void playerAttack(){
		Iterator<MangeMort> it = mm.iterator();
		while(it.hasNext()){
			MangeMort mechant = it.next();
			int xh = harrys.get(0).getPosX();
			int yh = harrys.get(0).getPosY();
			int xmm = mechant.getPosX();
			int ymm = mechant.getPosY();
			if ((Math.abs(ymm - yh) == 1 && xh==xmm) || (Math.abs(xmm - xh)== 1 && yh==ymm)){
				it.remove();
				window.draw(this.getMap(),this.getHarry());
			}
		}
	}
	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}
	
	public void setHarryPotterX(int x) {
		getHarry().setPosX(x);
	}
	
	public void setHarryPotterY(int y) {
		getHarry().setPosY(y);
	}
	public boolean hasCharacterAt(int x, int y) {
		for(MangeMort m : getMangeMorts())
			if(m.isAt(x,y))
				return true;
		
		for(HarryPotter hp : getHarryPotter())
			if(hp.isAt(x, y))
				return true;
		
		return false;
	}
	//Setteurs 
	
		public void setHarryPotter(ArrayList<HarryPotter> hp){
			this.harrys = hp;
		}

		public void setMangeMorts(ArrayList<MangeMort> mm){
			this.mm = mm ;
		}
		
		public void setPatronus(ArrayList<Patronus> patr){
			this.patr = patr;
		}
		public void setDragon(ArrayList<Dragon> drg){
			this.drg = drg ;
		}
		
		public void setBroomStick(ArrayList<BroomStick> bs){
			this.bs = bs;
		}
		public void setFire(ArrayList<Fire> fire){
			this.fire = fire;
		}
		public void setInvCloak(ArrayList<InvisibilityCloak> ic){
			 this.ic = ic ;
		}
		public void setMagicalPotion(ArrayList<MagicalPotion> mp){
			this. mp = mp;
		}
		public void setHorcrux(ArrayList<Horcruxs> hhor){
			this.hhor = hhor ;
		}
	private ArrayList<Horcruxs> getHorcrux() {
		return hhor;
	}
	
	public void SavedGame(Game elementSaved){
		this.setHarryPotter(elementSaved.getHarryPotter());
		this.setMangeMorts(elementSaved.getMangeMorts());
		this.setPatronus(elementSaved.getPatronus());
		this.setInvCloak(elementSaved.getInvCloak());
		this.setDragon(elementSaved.getDragon());
		this.setFire(elementSaved.getFire());
		this.setBroomStick(elementSaved.getBroomStick());
		this.setMagicalPotion(elementSaved.getMagicalPotion());
		this.setHorcrux(elementSaved.getHorcrux());
		this.setMatrix(elementSaved.getMatrix());
		this.setXb(elementSaved.getXb());
		this.setYb(elementSaved.getYb());
	}




}
	





