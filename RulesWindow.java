package View;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RulesWindow extends JPanel{
	
		/**
		 *  classe qui cr�e le pannel de r�gles du jeu, il utilise FrameAccueil
		 **/

		private static final long serialVersionUID = 1L;
		private JButton bouton = new JButton("Retour");
		private Image image;
		
		/*constructeur*/
		public RulesWindow(final WelcomePanel accueil, final WelcomeWindow frameAccueil){
			this.add(bouton);
			bouton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					frameAccueil.add(accueil);
					frameAccueil.setContentPane(accueil);
					frameAccueil.repaint();
					frameAccueil.pack();
				}
			});
		}
		
		public void paintComponent(Graphics g){
			try{
				image = ImageIO.read(new File("images/Regles.png"));
			} catch (IOException e1) {
				System.out.println("Une image est mal charg�e");
				System.exit(1);
			}
			g.drawImage(image, 0, 0, 750, 575, null);

		}
	}


