package Model;
import java.util.Random;

public class Corridor {
	private int xo;
	private int yo;
	private int size;
	private int direction;
	private Cell[][] matrix;
	
	public Corridor (int xo, int yo,int size, int d, Cell[][] matrix){
		this.xo = xo;
		this.yo = yo;
		this.direction = d;
		this.size = size;
		this.matrix = matrix;
	}
	
	public int getSize(){
		return this.size;
	}
	public int getDirection(){
		return this.direction;
	}
	
	public void  create(){
		if (direction==0){
			for ( int y =0; y< size; y++){
				matrix[xo][y].setPresenceMur(false);
			}
		} else if ( direction == 1){
			for (int x =0; x< size ;x++){
				matrix[x][yo].setPresenceMur(false);
			}
		}
	}
}
