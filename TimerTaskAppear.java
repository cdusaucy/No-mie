package Model;

import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;

public class TimerTaskAppear extends TimerTask implements ActionListener {
	private Game game;
	private Window window;
	private Random r;

	public TimerTaskAppear(Game game, Window w){
		this.game = game;
		this.window = w;
		r = new Random();
	}
	
	
	public void run() {
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hello"); // syso
		int posx = 1 + r.nextInt(game.getSize() - 2);
		int posy = 1 + r.nextInt(game.getSize() - 2);
		
		game.getMangeMorts().add(new MangeMort(game,posx,posy));
		window.draw(game.getMap(),game.getHarry());
		window.map.repaint();
	}

}
