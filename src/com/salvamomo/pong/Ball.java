package com.salvamomo.pong;

import java.awt.Graphics2D;

public class Ball {
	
	Game game;
	private float x, y;
	
	// TRUE => positive move. FALSE => negative move.
	private boolean moveY;
	private boolean moveX;
	
	private float speed;
	private int radius;
	
	public Ball(Game game, int X, int Y, int radius, float speed) {
		this.x = (float) X;
		this.y = (float) Y;
		this.radius = radius;
		this.game = game;
		this.speed = speed;
		
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
			else if (((x + speed + 10) >= game.WIDTH - 20) && ((x + speed + 10) <= game.WIDTH - 18)) {
				if (checkPlayerCollision(1)) moveX = !moveX;
				else {
					setX(x + speed);
				}
			}
			else if ((x + speed + 10) > game.WIDTH - 18) {
				if (!checkPlayerCollision(1)) scoreGoal(0);
			}
		}
		else if (!moveX) {
			if ((x - speed) > 20) setX(x - speed);
			else if (((x - speed) <= 20) && ((x - speed) >= 18)) {
				if (checkPlayerCollision(0)) moveX = !moveX;
				else {
					setX(x - speed);
				}
			}
			else if ((x - speed) < 18) {
				if (!checkPlayerCollision(0)) scoreGoal(1);
			}
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
		float playerY = game.players[playerIndex].getY();
		if ((playerY <= y + game.PLAYERS_HEIGHT) && (playerY + game.PLAYERS_HEIGHT >= y)) {
			game.players[playerIndex].setLastShootCollided(true);
			return true;
		}
		else return false;
	}
	
	public void scoreGoal(int playerIndex) {
		game.scoreGoal(playerIndex);
		moveX = !moveX;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float gameSpeed) {
		this.speed += gameSpeed;
		System.out.printf("GameSpeed increased by %f -- Actual GameSpeed %f \n", gameSpeed, this.speed);
	}
	
}
