package com.salvamomo.pong;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	Game game;
	
	// For now, using 4 static variables for up-down moves for 2 players
	public boolean up;
	public boolean down;
	public boolean w;
	public boolean s;
	
	
	public InputHandler(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
			// Fires/Pauses the match	
			case KeyEvent.VK_ENTER:
				if (game.getMatchStatus()) {
					System.out.println("Match Paused. Press <enter> to resume the match");
					game.setMatchStatus(false);
				}
				else {
					System.out.println("Match is on fire! -- Press <enter> to Pause it");
					game.setMatchStatus(true);
				}
				break;
			case KeyEvent.VK_UP:
				up = true;
				if (down == true) down = false;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				if (up == true) up = false;
				break;
			case KeyEvent.VK_W:
				w = true;
				if (s == true) s = false;
				break;
			case KeyEvent.VK_S:
				s = true;
				if (w == true) w = false;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_W:
			w = false;
			break;
		case KeyEvent.VK_S:
			s = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// Not used for now!
	}
	
}