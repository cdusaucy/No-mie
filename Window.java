package View;



import java.awt.Color;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JFrame;
import Model.HarryPotter;

public class Window implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public transient Map map = new Map();
	
	public Window(){	    
	    JFrame window = new JFrame("Game");
	   
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, 1300, 1000);
	    window.getContentPane().setBackground(Color.gray); 
	    // window.add(new MenuFrame(map));
	   // window.setVisible(true);
	    //window.add(new MenuRules(map));
	    
	    window.getContentPane().add(this.map);
	    window.setVisible(true);
	}	
	
	public void draw(int[][] mapMatrix, HarryPotter hp){
		map.setMapMatrix(mapMatrix,hp);
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	}
}
