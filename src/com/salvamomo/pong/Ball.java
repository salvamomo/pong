package com.salvamomo.pong;

import java.awt.Graphics2D;

public class Ball {
	
	Game game;
	private float x, y;
	
	private boolean moveY;
	private boolean moveX;
	
	private float speed = 0.5f;
	private int radius;
	
	public Ball(Game game, int X, int Y, int radius) {
		this.x = (float) X;
		this.y = (float) Y;
		this.radius = radius;
		this.game = game;
		
		this.moveX = true;
		this.moveY = true;
	}
	
	public void update(int deltaTime) {
		move();
	}
	
	public void render(Graphics2D g) {
		g.fillRoundRect((int) x, (int) y, 10, 10, radius, radius);
	}
	
	public void move() {
		// Y-axis movement
		if (moveY) {
			if ((y + speed) < game.HEIGHT - 10) setY(y + speed);
			else moveY = !moveY;
		}
		else if (!moveY) {
			if ((y - speed) > game.COURT_START) setY(y - speed);
			else moveY = !moveY;
		}
		
		// X-axis movement. This is more complex, as needs to check for collisions with players
		if (moveX) {
			if ((x + speed + 10) < game.WIDTH - 20) setX(x + speed);
			else if ((x + speed + 10) >= game.WIDTH - 20) {
				if (checkPlayerCollision(1)) moveX = !moveX;
			}
			else moveX = !moveX;
		}
		else if (!moveX) {
			if ((x - speed) > 20) setX(x - speed);
			else if ((x - speed) <= 20) {
				if (checkPlayerCollision(0)) moveX = !moveX;
			}
			else moveX = !moveX;
		}
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setY(float Y) {
		this.y = Y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public void setX(float X) {
		this.x = X;
	}
	
	public boolean checkPlayerCollision(int playerIndex) {
		return true;
	}
	
}
