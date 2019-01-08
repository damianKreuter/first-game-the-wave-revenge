package base;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import personajes.GameObject;

public class keyinput extends KeyAdapter {
	private Handler handler;
	
	private boolean aIzquierda = false;
	private boolean aDerecha = false;
	private boolean aArriba = false;
	private boolean aAbajo = false;
	public keyinput(Handler newhandler) {
		this.handler = newhandler;
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//This gets the ASCII's code from every key pressed
//		System.out.println(key);
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//KEY EVENTS FOR PLAYER 1VK
				int velocidadExtra = 10;
				int velocidadInversa = velocidadExtra *-1;
				
				if(key == KeyEvent.VK_UP) {
					if(aAbajo) {
						aAbajo = false;
					}
					tempObject.setVelY(velocidadInversa);
					aArriba = true;
					
				}
				if(key == KeyEvent.VK_DOWN)
				{
					if(aArriba) {
						aArriba = false;
					}
					tempObject.setVelY(velocidadExtra);
					aAbajo = true;
				}
				if(key == KeyEvent.VK_LEFT) {
					if(aDerecha) {
						aDerecha = false;
					}
					tempObject.setVelX(velocidadInversa);
					aIzquierda = true;
				}
				if(key == KeyEvent.VK_RIGHT) {
					if(aIzquierda) {
						aIzquierda = false;
					}
					tempObject.setVelX(velocidadExtra);
					aDerecha = true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//KEY EVENTS FOR PLAYER 1VK
				if(key == KeyEvent.VK_UP && aArriba == true) tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN && aAbajo == true) tempObject.setVelY(0);
				if(key == KeyEvent.VK_LEFT && aIzquierda == true) tempObject.setVelX(0);
				if(key == KeyEvent.VK_RIGHT && aDerecha == true) tempObject.setVelX(0);
				
				/*
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			*/
			}
		}
		
		
	}
	
	
}
