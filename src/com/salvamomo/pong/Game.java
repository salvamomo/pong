package com.salvamomo.pong;
/**
 * @file
 * Main file for Pong game \o/.
 * 
 *  Simple Pong game running in Java.
 *  Salva Molina | 12-2011
 */

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game implements Runnable {
   // Variables regarding game execution and fps. Needed to keep the game loop running!
   long desiredFPS = 60;
   long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
   boolean running = true;
	
	// Static variables that won't vary during Game Execution;
	private final int WIDTH = 600;
	private final int HEIGHT = 400;
	
	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	
	// TODO: Decide whether for this game the players should be two separate variables.
	Player players[]; // We have at least 2 players!!
	
	public Game () {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Ey yo! \\o/");
		while (running) {
			
		}
	}
	
	/*
	 * This function should call all entities that need to be updated in game time.
	 */
	protected void update(int deltaTime) {
		
	}
	
	/*
	 * This function should call all entities that need to display data on render time.
	 */	
	protected void render(Graphics2D g) {
		
	}
	
	public static void main(String [] args) {
		Game game = new Game();
		new Thread(game).start();
	}
	
}