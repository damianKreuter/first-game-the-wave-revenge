package base;
import java.awt.Graphics;
import java.util.LinkedList;

import personajes.GameObject;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject g) {
		this.object.add(g);
		
	}
	
	public void vaciarPersonaje() {
		boolean x = true;
		for(int i = 0; i < object.size(); i++) {
			if(x == true) {
				GameObject tempObject = object.get(i);
				if(tempObject.getId() == ID.Player) {
					removeObject(tempObject);
					x = false;
				}
			} else {
				return;
			}
		} 
	}
	
	public void vaciarObjecto() {
		while(object.size() > 0) {
			GameObject g = object.get(1);
			removeObject(g);
		}
	}
	
	public void removeObject(GameObject g) {
		this.object.remove(g);
	}
}
