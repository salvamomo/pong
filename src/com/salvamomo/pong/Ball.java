package com.salvamomo.pong;

import java.awt.Graphics2D;

public class Ball {
	
	Game game;
	private int x, y;
	
	private boolean moveY;
	private boolean moveX;
	
	private int speed;
	private int radius;
	
	public Ball(int X, int Y) {
		this.x = X;
		this.y = Y;
	}

	public Ball(Game game, int X, int Y, int radius) {
		this.x = X;
		this.y = Y;
		this.radius = radius;
		this.game = game;
	}
	
	public void update(int deltaTime) {
		
	}
	
	public void render(Graphics2D g) {
		g.fillRoundRect((game.WIDTH / 2) -5 , (game.HEIGHT / 2) - 5, 10, 10, radius, radius);
	}
	
}
