import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PongGamePlay extends JPanel implements Runnable{
	
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	PongPaddle paddle1;
	PongPaddle paddle2;
	PongBall ball;
	PongScoreBoard score;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	PongSound ps = new PongSound();
	
	
	PongGamePlay() {
		newPaddles();
		newBall();
		score = new PongScoreBoard(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newPaddles() {
		paddle1 = new PongPaddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new PongPaddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	public void newBall() {
		random = new Random();
		ball = new PongBall((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision(){
		//bounces ball off window edges
		if (ball.y<=0) {
			ball.setYDirection(-ball.ySpeed);
		}
		if (ball.y>=GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.ySpeed);
		}
		//bounces ball off paddles
		if (ball.intersects(paddle1)) {
			ps.play1();
			paddle2.speed++;
			ball.xSpeed = Math.abs(ball.xSpeed);
			ball.xSpeed++; //increases speed after bounce off paddle
			if (ball.ySpeed>0) {
				ball.ySpeed++; //increases speed after bounce off paddle
			}
			else {
				ball.ySpeed--;
			}
			ball.setXDirection(ball.xSpeed);
			ball.setYDirection(ball.ySpeed);
		}
		if (ball.intersects(paddle2)) {
			ps.play1();
			paddle1.speed++;
			ball.xSpeed = Math.abs(ball.xSpeed);
			ball.xSpeed++; //increases speed after bounce off paddle
			if (ball.ySpeed>0) {
				ball.ySpeed++; //increases speed after bounce off paddle
			}
			else {
				ball.ySpeed--;
			}
			ball.setXDirection(-ball.xSpeed);
			ball.setYDirection(ball.ySpeed);
		}
		
		//stops paddles at edge of window
		if (paddle1.y<=0) {
			paddle1.y=0;
		}
		if (paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT)) {
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		}
		if (paddle2.y<=0) {
			paddle2.y=0;
		}
		if (paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT)) {
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		}
		//loops after end of one round
		if (ball.x<=0) {
			ps.play2();
			score.player2++;
			newPaddles();
			newBall();
		}
		if (ball.x>=GAME_WIDTH-BALL_DIAMETER) {
			ps.play2();
			score.player1++;
			newPaddles();
			newBall();
		}
	}
	
	public void run() {
		//game loop
		long lastLoopTime = System.nanoTime();
		double targetFps = 60.0;
		double optimalTime = 1000000000/targetFps;
		double lastFpsTime = 0;
		while(true) {
			long now = System.nanoTime();
			lastFpsTime += (now-lastLoopTime)/optimalTime;
			lastLoopTime = now;
			if (lastFpsTime>=1) {
				move();
				checkCollision();
				repaint();
				lastFpsTime--;
			}
		}
	}
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}

}
