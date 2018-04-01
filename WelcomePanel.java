package View;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JComboBox;


import Controller.Keyboard;
import Model.Cell;
import Model.Game;
import View.WelcomeWindow;
import View.Map;
import View.Window;
import View.RulesWindow;

public class WelcomePanel extends JPanel{

		/**
		 * classe qui crée le pannel d'accueil et actionne ses boutons
		 **/

		private static final long serialVersionUID = 1L;	
		private JComboBox<String> boxTaille = new JComboBox<String>(); //menu deroulant
		private JComboBox<String> boxJoueur = new JComboBox<String>(); 
		private JLabel labelTaille = new JLabel("Taille du plateau :");//nom du menu deroulant
		private JLabel labelJoueur = new JLabel("Choix du joueur :");
		private JButton boutonRegle = new JButton("Règles et commandes du jeu"); //bouton
		private JButton bouton = new JButton("Commencer a jouer !"); 
		private JButton boutonContinue = new JButton("Continuer le jeu !");
		private WelcomeWindow frameAccueil;
		private Image image;

		
		/*constructeur*/
		public WelcomePanel(WelcomeWindow frameAccueil){
			this.frameAccueil = frameAccueil;
			JPanel boxT = new JPanel();//creation d'un box
			boxTaille.addItem("50 x 50");
			boxTaille.addItem("100 x 100");
			boxTaille.addItem("200 x 200");
			boxT.add(labelTaille);//permet d'afficher le nom du box
		    boxT.add(boxTaille);//ajoute les informations dans le box
		    JPanel boxJ = new JPanel();
		    boxJoueur.addItem("Schtroumpf");
		    boxJoueur.addItem("Grand Schtroumpf");
		    boxJoueur.addItem("Schtroumpf Coquet");
		    boxJoueur.addItem("Schtroumpf à lunettes");
		    boxJ.add(labelJoueur);
		    boxJ.add(boxJoueur);
			this.add(boxT);
			this.add(boxJ);
			this.add(boutonRegle);
			this.add(bouton);//bouton pour démarrer le jeu
			this.add(boutonContinue);
		    boutonRegle.addActionListener(new ActionBouton(this));//ajoute l'action aux boutons
		    bouton.addActionListener(new ActionBouton(this)); 
			boutonContinue.addActionListener(new ActionBouton(this));

		}

		public void paintComponent(Graphics g){//telecharge l'image de fond
			try{
				image = ImageIO.read(new File("images/poudlard.png"));
			} catch (IOException e1) {
				System.out.println("Une image est mal chargée");
				System.exit(1);
			}
			g.drawImage(image, 0, 0,750, 575, null);
		}
		
		public class ActionBouton implements ActionListener {

			private WelcomePanel accueil;
			
			public ActionBouton(WelcomePanel accueil){
				this.accueil = accueil;
			}
			
			public void actionPerformed(ActionEvent e){
				int numeroJoueur = 0;
				int niveau = 1;
				if (e.getActionCommand() == "Commencer a jouer !"){  //action du bouton "Commencer"
					String chSize = boxTaille.getSelectedItem().toString();
					int size = 0;
					if (chSize == "50 x 50"){
						size = 20;
					}
					else if (chSize == "100 x 100"){
						size = 100;
					}
					else if (chSize == "200 x 200"){
						size = 200;
					}
					String resJoueur = boxJoueur.getSelectedItem().toString();
					
					if (resJoueur == "Schtroumpf"){
						numeroJoueur = 1;
					}
					else if (resJoueur == "Grand Schtroumpf"){
						numeroJoueur = 2;
					}
					else if (resJoueur == "Schtroumpf Coquet"){
						numeroJoueur = 3;
					}
					else if (resJoueur == "Schtroumpf à lunettes"){
						numeroJoueur = 4;
					}
					Window window = new Window();
					
					Game game = new Game(window,false,10,10);
					game.setSize(size);
					Cell [][] matrix = new Cell[size][size];
					for(int i = 0; i < size; i++){
						   for(int j = 0; j < size; j++){
								 matrix[i][j] = new Cell(i,j,false,game); 
							}
						}
						Random r = new Random();
						for(int i = 0; i < size; i++) {
							for(int j = 0; j < size; j++)
								if(r.nextInt(4) == 0){
									//if ( i!= getHarry().getPosX() && j!= getHarry().getPosY()){
										matrix[i][j].setPresenceMur(true);
									//} else {
										//matrix[i][j].setPresenceMur(false);
									//}
								} else {
									 matrix[i][j].setPresenceMur(false);
								}
						}
						for(int i = 0; i < size ; i++){//mur
						    matrix[i][0].setPresenceMur(true);
						    matrix[i][size-1].setPresenceMur(true);
						}
								
						for(int j = 0; j < size ; j++){ //mur
						    matrix[0][j].setPresenceMur(true);
						    matrix[size-1][j].setPresenceMur(true);
						}
						game.setMatrix(matrix);
						game.instanceHorcruxs(size, game.getHorcruxs());
						window.draw(game.getMap(), game.getHarry());
						Keyboard keyboard = new Keyboard(game);
						window.setKeyListener(keyboard);
				
				}
				else if(e.getActionCommand() == "Continuer le jeu !"){
					
					Window window = new Window();
					
					Game game = new Game(window,false,10,10);
					Keyboard keyboard = new Keyboard(game);
					window.setKeyListener(keyboard);
					Game gameLoaded = null;
				      try
				      {
				         FileInputStream fileIn = new FileInputStream("game.serial");
				         ObjectInputStream in = new ObjectInputStream(fileIn);
				         gameLoaded = (Game) in.readObject();
				         in.close();
				         fileIn.close();
				      }catch(IOException i)
				      {
				         i.printStackTrace();
				         return;
				      }catch(ClassNotFoundException c)
				      {
				         System.out.println("Game class not found");
				         c.printStackTrace();
				         return;
				      }
				      (game).SavedGame(gameLoaded);
				
				}
				else if (e.getActionCommand() == "Règles et commandes du jeu"){ //action du bouton "regles et commandes du jeu"
					RulesWindow regles = new RulesWindow(accueil, frameAccueil);
					frameAccueil.setContentPane(regles);
					frameAccueil.repaint();
					frameAccueil.pack();
				}
			}
		}
}
