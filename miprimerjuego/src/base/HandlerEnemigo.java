package base;

import java.awt.Graphics;
import java.util.LinkedList;

import personajes.Enemigo;
import personajes.GameObject;

public class HandlerEnemigo {
	public LinkedList<Enemigo> object = new LinkedList<Enemigo>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			
			Enemigo tempObject = object.get(i);
			
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < object.size(); i++) {
			Enemigo tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(Enemigo g) {
		this.object.add(g);
		
	}
	
	public void vaciarObjecto() {
		while(object.size() > 0) {
			GameObject g = object.get(0);
			removeObject(g);
		}
	}
	
	public void removeObject(GameObject g) {
		this.object.remove(g);
	}
}
