 package Controller;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import Model.Patronus;
import java.awt.event.KeyListener;

import Model.Game;

public class Keyboard implements KeyListener{
	private Game game;

	
	public Keyboard(Game game){
		this.game = game;

	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		switch (key){
			case KeyEvent.VK_RIGHT:
				game.getHarry().movePlayerRight();
				break;
			case KeyEvent.VK_LEFT:
				game.getHarry().movePlayerLeft();
				break;
			case KeyEvent.VK_DOWN:
				game.getHarry().movePlayerDown();
				break;
			case KeyEvent.VK_UP:
				game.getHarry().movePlayerUp();
				break;	
				
			case KeyEvent.VK_SPACE:
				game.playerAttack();
				break;
			case KeyEvent.VK_C:
				game.actionPatronus();
			case KeyEvent.VK_V:
				game.actionIc();
			case KeyEvent.VK_B:
				game.actionBs();
			case KeyEvent.VK_S:
				System.out.println("Saving");
				
			try {
				FileOutputStream fileOut = new FileOutputStream("game.serial");
				ObjectOutputStream oos = new ObjectOutputStream(fileOut);
				oos.writeObject(game);
				oos.flush();
				oos.close();
				fileOut.close();
				JOptionPane.showMessageDialog(null, "Game Saved!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
				System.out.println("Saved");
				break;
	
		
		}
		// leJPanel.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}