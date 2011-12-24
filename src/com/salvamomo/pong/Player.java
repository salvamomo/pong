package com.salvamomo.pong;

import java.awt.Graphics2D;

public class Player {
	
	Game game;
	private int y;
	private int x;
	
	public Player(Game game, int X, int Y) {
		this.game = game;
		this.x = X;
		this.setY(Y);
	}
	
	public void moveUp() {
		this.setY(this.getY() + 1);
	}
	public void moveDown() {
		this.setY(this.getY() - 1);
	}
	
	protected void update(int deltaTime) {
	}
	
	protected void render(Graphics2D g) {
		g.fillRect(this.x, this.y - 10, 10, 20); 
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if ((y < (this.game.HEIGHT) -10) && y > 10)
			this.y = y;
	}
	
}