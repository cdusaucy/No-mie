package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import View.Window;

public class TimerMove implements ActionListener {
	private Game game;
	private Window window;
	
	public TimerMove(Game game, Window w){
		this.game = game;
		this.window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		game.moveMangeMorts();
		if(game.getFire().size()!=0){
			for (Fire fr : game.getFire()){
				fr.move();
			}
		}
		game.noMoreFire();
		window.draw(game.getMap(),game.getHarry());
		window.map.repaint();
		

	}

}