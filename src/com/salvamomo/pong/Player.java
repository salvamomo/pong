package com.salvamomo.pong;

import java.awt.Graphics2D;

public class Player {
	
	Game game;
	private float y;
	private float x;
	private int score;
	
	public Player(Game game, float X, float Y) {
		this.game = game;
		this.x = X;
		this.setY(Y);
		this.setScore(0);
	}
	
	public void moveUp() {
		this.setY(this.getY() - 0.5f);
	}
	public void moveDown() {
		this.setY(this.getY() + 0.5f);
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		if ((y + game.PLAYERS_HEIGHT < (game.HEIGHT)) && y > game.SB_HEIGHT)
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
		g.fillRect((int) this.x, (int) this.y, 10, game.PLAYERS_HEIGHT); 
	}
	
}