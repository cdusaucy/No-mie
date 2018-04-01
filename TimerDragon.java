package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Window;

public class TimerDragon implements ActionListener{
	private Game game;
	private Window window;
	
	public TimerDragon(Game game, Window w){
		this.game = game;
		this.window = w;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for (Dragon dragon : game.getDragon()){
			dragon.moveRandom();
			dragon.attack();
		}
		
	}

}
