import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class PongPaddle extends Rectangle{
	
	int playerID;
	int ySpeed;
	int speed = 10;
	
	PongPaddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int playerID) {
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.playerID=playerID;
	}
	
	public void keyPressed(KeyEvent e) {
		switch (playerID) {
		case 1:
			if (e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_A) {
				y=0;
			}
			if (e.getKeyCode()==KeyEvent.VK_D) {
				y=PongGamePlay.GAME_HEIGHT;
			}
			break;
		case 2:
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				y=0;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				y=PongGamePlay.GAME_HEIGHT;
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (playerID) {
		case 1:
			if (e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			break;
		case 2:
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}
	}
	public void setYDirection (int yDirection) {
		ySpeed = yDirection;
	}
	public void move() {
		y += ySpeed;
	}
	public void draw(Graphics g) {
		if (playerID==1)
			g.setColor(Color.cyan);
		else
			g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		
	}

}
