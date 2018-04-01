package View;
import java.awt.Image;
import java.util.ArrayList;
import Model.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import Model.HarryPotter;
import Model.Horcruxs;
import Model.Inventory;
public class Map extends JPanel {
	private int[][] mapMatrix;
	private HarryPotter hp;// Game game; instead of mapMatrix
	private Graphics2D g2d;
	private int width = 40;
	private int height = 40;
	Game game;
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	
	public void paint(Graphics g) { 
		this.g2d=(Graphics2D)g;
		Image harry = new ImageIcon("Images/harry.png").getImage();
		Image sol = new ImageIcon("Images/solpou.jpg").getImage();
		Image mur = new ImageIcon("Images/mur - couloir.png").getImage();
		Image mmf = new ImageIcon("Images/mmface.png").getImage();
		Image patr = new ImageIcon("Images/cerf_patronus.png").getImage();
		Image cape = new ImageIcon("Images/capeho.png").getImage();
		Image balais = new ImageIcon("Images/balaismagique.png").getImage();
		Image fire = new ImageIcon("Images/fireball.png").getImage();
		Image dragon = new ImageIcon("Images/dragon.png").getImage();
		Image potion = new ImageIcon("Images/potion.png").getImage();
		Image horcrux = new ImageIcon("Images/horcrux.png").getImage();
		Image winpanel = new ImageIcon("Images/Winpanel.jpg").getImage();
			if(mapMatrix == null){
				Image gameover = new ImageIcon("Images/GameOver.png").getImage();
				g2d.drawImage(sol,0, 0, 1000, 1000,null);
				g2d.drawImage(gameover,0, 0, 1000, 1000,null);		
			}else {
				for(int i = 0; i<20; i++){
					for(int j = 0; j<20; j++){
						int x = i;
						int y = j;
						int color = mapMatrix[i][j];
						
						if(color == 0){
							g2d.drawImage(sol,x*width, y*height, width, height,null);
						} else if(color == 1){
							g2d.drawImage(mur,x*width, y*height, width, height,null);
							
						} else if(color == 2){
							g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(harry,x*width, y*height, width, height,null);
							
			     		} else if(color == 3){
			     			g2d.drawImage(mmf,x*width, y*height, width, height,null);
				    	
			     		} else if(color == 4){
				    		g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(patr,x*width, y*height, width, height,null);
							
				    	} else if(color==5) {
				    		g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(cape,x*width, y*height, width, height,null);
				    	
				    	}else if(color == 6) {
				    		g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(balais,x*width, y*height, width, height,null);
				    	
						}else if(color == 8) {
				    		g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(fire,x*width, y*height, width, height,null);
				    	
						}else if(color == 7) {
				    		g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(dragon,x*width, y*height, width, height,null);
							
						}else if(color == 9) {
				    		g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(horcrux,x*width, y*height, width, height,null);		
							
				    	} else if (color == 10){
				    		g2d.drawImage(sol,x*width, y*height, width, height,null);
							g2d.drawImage(potion,x*width, y*height, width, height,null);
				    	}
								
						System.out.print(color);
						System.out.print(" ");
					}
					System.out.println("");
					
				}
			
			} 
			Font f = new Font ("Courier", Font.BOLD,24);
			g.setFont(f);
			g.setColor(Color.red);
			Image fond = new ImageIcon("Images/fondblanc.png").getImage();
			g2d.drawImage(fond, 20*width, 0, 600, 20*height,null);
			g2d.drawString("Patronus :", 20*width + 10, 3*height +10);
			g2d.drawString("Invisibility Cloak :", 20*width + 10, 5*height +10);
			g2d.drawString("Magical Potion :", 20*width + 10, 7*height +10);
			g2d.drawString("Broom Stick :", 20*width + 10, 9*height +10);
			g.setColor(Color.blue);
			g2d.drawString("Horcrux :", 20*width + 10, 11*height +10);
			
			Inventory inv = hp.getInventory();
			
			if (inv.patr.size() != 0){
				for (int i =0; i< inv.patr.size();i++){
					g2d.drawImage(patr,20*width + 10 + i*width , 3*height + 20, width, height, null);
				}
				}for (int i =0; i<inv.sic.size();i++){
					g2d.drawImage(cape,20*width + 10 + i*width , 5*height + 20, width, height, null);
				}for (int i =0; i< inv.mp.size();i++){
					g2d.drawImage(potion,20*width + 10 + i*width , 7*height + 20, width, height, null);
				}for (int i =0; i< inv.sbs.size();i++){
					g2d.drawImage(balais,20*width + 10 + i*width ,9* height + 20, width, height, null);
				}for (int i =0; i< inv.hor.size();i++){
					g2d.drawImage(horcrux,20*width + 10 + i*width , 11*height + 20, width, height, null);
					if(inv.hor.size() == 7){
						System.out.println("win");
						g2d.drawImage(winpanel,0, 0, 1000, 1000,null);	
					}
				}
				
				int vie = hp.getDoseVie();
				g.drawRoundRect(20*width + 10, (height +10), 200, 50, 10, 10);
				g.fillRoundRect(20*width + 10, (height +10), 200, 50, 10, 10);
				g.setColor(Color.red);
				g.fillRoundRect(20*width + 10, (height +10), vie*2, 50, 10, 10);
				
				
		}
	
	public void setMapMatrix(int[][] mapMatrix, HarryPotter hp){
		this.mapMatrix = mapMatrix;
		this.hp = hp;
		this.repaint();
	}
	
	
	
}