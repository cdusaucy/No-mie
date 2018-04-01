package Model;


import View.Window;

public class MagicalPotion extends Items implements Runnable{
	private boolean actionTime;
	private int time  = 10000;
	private transient Thread thread;
	
	
	public MagicalPotion(int x, int y, boolean b,boolean m, Game game, Window window) {
		super(x, y, b, game, window);
		this.actionTime = m ;
		this.thread = new Thread(this);
	}

	public void setActionTime(boolean b){
		this.actionTime = b;
	}

	
	@Override
	public void action() {
		thread.start();
	}
	
	@Override
	public void goInventory(Inventory inv){
		inv.mp.add(new MagicalPotion(0,0,false,false,super.getGame(),super.getWindow()));
	}
	
	public boolean getActionTime(){
		return this.actionTime;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getGame().getHarry().getInventory().mp.remove(0);
		
	}
	

	
}