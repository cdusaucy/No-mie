package Model;

import java.io.Serializable;

public class Fire extends Caracters implements Serializable{
	private int dir;
	private boolean againstWall;

	public Fire(int X, int Y, Game game, int dir, boolean b) {
		super(X, Y, game);
		this.dir = dir;
		this.againstWall = b;
	}
	public void setAgainstWall(boolean b){
		this.againstWall = b;
	}
	public boolean getAgainstWall (){
		return this.againstWall;
	}
	public void move(){
		if (dir ==0){
			if (accessibleCell(getPosX(), getPosY() - 1)){
				this.moveDir(0, -1);
			} else {
				setAgainstWall(true);
			}
		} else if (dir == 1){
			if (accessibleCell(getPosX(), getPosY() + 1)){
				this.moveDir(0, 1);
			} else {
				setAgainstWall(true);
			}
		} else if (dir == 2) {
			if (accessibleCell(getPosX() -1, getPosY() )){
				this.moveDir(-1, 0);
			} else {
				setAgainstWall(true);
			}
		} else if (dir ==3){
			if (accessibleCell(getPosX() +1, getPosY() )){
				this.moveDir(1, 0);
			} else {
				setAgainstWall(true);
			}
		}
		System.out.println("je te baise");
		
	}

}
