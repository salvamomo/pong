package com.salvamomo.pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	Game game;
	private float y;
	private float x;
	private int score;
	private boolean lastShootCollided;
	private Color playerColor;
	private long colorReset;
	
	public Player(Game game, float X, float Y) {
		this.game = game;
		this.x = X;
		this.setY(Y);
		this.score = 0;
		this.playerColor = Color.BLACK;
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
		if (this.lastShootCollided) {
			this.playerColor = Color.RED;
			this.colorReset = 0;
			this.setLastShootCollided(false);
		}
		else if (this.colorReset > 40) {
			this.colorReset = 0;
			this.playerColor = Color.BLACK;
		}
		else this.colorReset += 1;
	}
	
	protected void render(Graphics2D g) {
		g.setColor(this.playerColor);
		g.fillRect((int) this.x, (int) this.y, 10, game.PLAYERS_HEIGHT);
	}

	public boolean getLastShootCollided() {
		return this.lastShootCollided;
	}
	
	public void setLastShootCollided(boolean collision) {
		this.lastShootCollided = collision;
	}
	
}