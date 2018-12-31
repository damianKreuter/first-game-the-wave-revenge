package personajes;

import java.awt.Color;

import base.Handler;
import base.HandlerEnemigo;
import base.ID;

public abstract class Enemigo extends GameObject{
	protected float danio;
	protected HandlerEnemigo handlerEnemigo;
	protected Handler handlerNormal;
	public Enemigo(float x, float y, ID ids, HandlerEnemigo handE,float danio, Handler hand) {
		super(x, y, ids);
		handlerEnemigo = handE;
		this.danio = danio;
		this.handlerNormal = hand;
		// TODO Auto-generated constructor stub
	}
	
	public abstract float haceDanio();
	
	public abstract void desaparece();
	

}
