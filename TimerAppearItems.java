package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import View.Window;

public class TimerAppearItems implements ActionListener{
	private Game game;
	private Window window;
	private Random r;
	
	public TimerAppearItems(Game game, Window w){
		this.game = game;
		this.window = w;
		this.r = new Random();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int x = r.nextInt(game.getSize());
		int y = r.nextInt(game.getSize());
		if (!game.getMatrix()[x][y].isWall()){
			int it = r.nextInt(5);
			if (it ==0){
				game.getPatronus().add(new Patronus(x, y, true, game, window));
			} else if (it ==1){
				game.getBroomStick().add(new BroomStick(x, y, true, game, window));
			} else if (it == 2){
				game.getInvCloak().add(new InvisibilityCloak(x, y, true,false, game, window));
			} else if (it == 3){
				game.getMagicalPotion().add(new MagicalPotion(x, y, true,false, game, window));
			} else if (it == 4){
				game.getDragon().add(new Dragon(x, y, game));
			}
		}
		
	}

}
