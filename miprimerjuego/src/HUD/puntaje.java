package HUD;

import java.awt.Graphics;
import java.awt.Rectangle;

import base.ID;
import personajes.GameObject;

public class puntaje extends GameObject {
	public int puntos;
	public String tex = "Puntos: ";
	
	public puntaje(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle perimetro() {
		// TODO Auto-generated method stub
		return null;
	}
}
