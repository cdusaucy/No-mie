

import java.io.IOException;
import java.awt.Dimension;

import View.WelcomeWindow;
import View.WelcomePanel;

/**
  classe main du projet, crée le panneau d'accueil.
 **/
public class Main {
	/*lance le menu d'accueil*/
	public static void main(String[] args) throws IOException {
		WelcomeWindow frameAccueil = new WelcomeWindow();
		WelcomePanel accueil = new WelcomePanel(frameAccueil);
		frameAccueil.setContentPane(accueil);
		frameAccueil.setPreferredSize(new Dimension(750, 575));
		frameAccueil.validate();
		frameAccueil.pack();
	}
}
