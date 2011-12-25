package com.salvamomo.pong;
/**
 * @file
 * Main file for Pong game \o/.
 * 
 *  Simple Pong game running in Java.
 *  Salva Molina | 12-2011
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game implements Runnable {
   // Variables regarding game execution and fps. Needed to keep the game loop running!
   long desiredFPS = 60;
   long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
   boolean running = true;
	
	// Static variables that won't vary during Game Execution;
	final int WIDTH = 650;
	final int HEIGHT = 400;
	// Scoreboard dimensions and other variables
	final int SB_HEIGHT = 25;
	final int COURT_START = SB_HEIGHT;
	final int PLAYERS_HEIGHT = 25;
	
	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	
	InputHandler input = new InputHandler(this);
	
	// TODO: Decide whether for this game the players should be two separate variables.
	Player players[] = new Player[2];// We have at least 2 players!!
	Ball ball; // And a ball...don't we?
	
	private boolean matchRunning = false;
	private int matchTime;
	
	public Game () {
		frame = new JFrame("Pong");
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		
		panel.add(canvas);
		canvas.addKeyListener(input);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		players[0] = new Player(this, 10, (HEIGHT / 2) - 10 + SB_HEIGHT);
		players[1] = new Player(this, WIDTH - 20, (HEIGHT / 2) - 10 + SB_HEIGHT);
		ball = new Ball(this, (WIDTH / 2) - 5, (HEIGHT / 2) - 5  + SB_HEIGHT, 50, 0.3f);
		
	    canvas.createBufferStrategy(2);
	    bufferStrategy = canvas.getBufferStrategy();
	    canvas.requestFocus();
	}

	@Override
	public void run() {
		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime = 0;
		long deltaLoop = 0;
		
		System.out.printf("Ey yo! \\o/.  Match running?: %b \n", matchRunning);
		while (running) {
			beginLoopTime = System.nanoTime();
			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			
			if (matchRunning && canvas.hasFocus()) {
				update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
				matchTime += 1;
			}
			render();
			
			endLoopTime = System.nanoTime();
			lastUpdateTime = endLoopTime - beginLoopTime; 
			
			if (lastUpdateTime < deltaLoop) {
				// Put the loop to sleep, is not time to update yet.
				try {
					Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
				} catch (InterruptedException ex) {
					// Don't do nothing
				}
			}
		}
	}
	
	private void render() {
	      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
	      g.clearRect(0, 0, WIDTH, HEIGHT);
	      render(g);
	      g.dispose();
	      bufferStrategy.show();
	}
	
	/*
	 * This function should call all entities that need to be updated in game time.
	 */
	protected void update(int deltaTime) {
		ball.update(deltaTime);
		if (input.s) players[0].moveDown();
		else if (input.w) players[0].moveUp();
		
		if (input.up) players[1].moveUp();
		else if (input.down) players[1].moveDown();
		
		for (int i = 0; i < players.length ; i++) {
			players[i].update(deltaTime);
		}
	}
	
	/*
	 * This function should call all entities that need to display data on render time.
	 */	
	protected void render(Graphics2D g) {
		this.renderScoreBoard(g);
		ball.render(g);
		for (int i = 0; i < players.length ; i++) {
			players[i].render(g);
		}
		if (!this.matchRunning) this.renderPauseBox(g);
		if (!canvas.hasFocus()) this.renderFocusMsg(g);
	}
	
	public boolean getMatchStatus() {
		return this.matchRunning;
	}
	
	public void setMatchStatus(boolean status) {
		this.matchRunning = status;
	}
	
	/**
	 * @function 
	 * Draws in screen the match data. (score for each players, math duration, etc...)
	 * This should be structured in a different way, but is in the main class to keep things simpler for now.
	 */
	public void renderScoreBoard(Graphics2D g) {
		String score1 = "SCORE Player 1: " + players[0].getScore();
		String score2 = "SCORE Player 2: " + players[1].getScore();
		
		g.fillRect(0, 0, WIDTH, SB_HEIGHT);
		g.setColor(Color.GREEN);
		g.drawString(score1, 10, 17);
		g.drawString(score2, WIDTH - 125 , 17);
		g.setColor(Color.WHITE);
		g.drawString("TIME:" + matchTime / (1000), WIDTH / 2 - 60, 17);
		g.drawString("Game Speed: " + ball.getSpeed(), WIDTH / 2 + 20, 17);
		
		g.setColor(Color.BLACK);
	}
	
	public void renderPauseBox(Graphics2D g) {		
		g.setColor(Color.BLUE);
		g.fillRect((WIDTH / 2) - 200, (HEIGHT / 2) + SB_HEIGHT - 40, 400, 80);
		g.setColor(Color.WHITE);
		g.fillRect((WIDTH / 2) - 190, (HEIGHT / 2) + SB_HEIGHT - 30, 380, 60);
		g.setColor(Color.BLACK);
		g.drawString("Match Paused. Press <enter> to resume!", (WIDTH / 2) - 130, (HEIGHT / 2) + SB_HEIGHT + 5);
	}
	
	public void renderFocusMsg(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect((WIDTH / 2) - 200, (HEIGHT / 2) + SB_HEIGHT - 40, 400, 80);
		g.setColor(Color.WHITE);
		g.fillRect((WIDTH / 2) - 190, (HEIGHT / 2) + SB_HEIGHT - 30, 380, 60);
		g.setColor(Color.BLACK);
		g.drawString("Click to focus, you bastard!", (WIDTH / 2) - 90, (HEIGHT / 2) + SB_HEIGHT + 5);
	}
	
	public void scoreGoal(int playerIndex) {
		players[playerIndex].setScore(players[playerIndex].getScore() + 1);
		System.out.printf("Player %d has scored a goal \n", playerIndex + 1);
		int score1 = players[0].getScore();
		int score2 = players[1].getScore();
		System.out.printf("Player 1 %d -- %d Player 2\n", score1, score2);
	}
	
	public static void main(String [] args) {
		Game game = new Game();
		new Thread(game).start();
	}
	
}