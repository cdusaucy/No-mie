package Model;

public class Room {
	private int xo;
	private int yo;
	private int width;
	private int height;
	private Cell[][] matrix;
	
	public Room(int xo, int yo, int width, int height, Cell[][] matrix){
		this.xo = xo;
		this.yo = yo;
		this.width = width;
		this.height = height;
		this.matrix = matrix;
	}
	public void create(){
		for (int i=xo; i< xo +width ; i++){
			for (int j=yo; j< yo +height; j++){
				matrix[i][j].setPresenceMur(false);
			}
		}
	}
}
