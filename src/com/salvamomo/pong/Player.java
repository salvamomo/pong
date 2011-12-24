package com.salvamomo.pong;

import java.awt.Graphics2D;

public class Player {
	
	Game game;
	private int y;
	private int x;
	private int score;
	
	public Player(Game game, int X, int Y) {
		this.game = game;
		this.x = X;
		this.setY(Y);
		this.setScore(0);
	}
	
	public void moveUp() {
		this.setY(this.getY() - 1);
	}
	public void moveDown() {
		this.setY(this.getY() + 1);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if ((y < (this.game.HEIGHT) -10) && y > 10  + game.SB_HEIGHT)
			this.y = y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	protected void update(int deltaTime) {
	}
	
	protected void render(Graphics2D g) {
		g.fillRect(this.x, this.y - 10, 10, 20); 
	}
	
}