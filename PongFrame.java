import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PongFrame extends JFrame{
	
	PongGamePlay game;
	
	PongFrame() {
		game = new PongGamePlay();
		this.add(game);
		this.setTitle("Abhi's Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
