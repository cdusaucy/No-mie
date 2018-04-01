package View;
import javax.swing.JFrame;

public class WelcomeWindow  extends JFrame{

		/*constructeur*/
		public WelcomeWindow(){
			super("Bienvenue à Poudlard");
			this.setSize(750, 575);
			this.setLocationRelativeTo(null);
			this.setResizable(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}
}



