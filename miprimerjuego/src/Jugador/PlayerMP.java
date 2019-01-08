package Jugador;

import java.net.InetAddress;

import base.Handler;
import base.HandlerEnemigo;
import base.ID;

public class PlayerMP extends Player{

	public InetAddress ipDireccion;
	public int puerto;
	
	//JUGADOR CONECTADO DESDE UNA IP EXTERNA
	public PlayerMP(float x, float y, ID id, Handler hand, HandlerEnemigo handEnemigo, InetAddress IpAddress, int port) {
		super(x, y, id, hand, handEnemigo);
		// TODO Auto-generated constructor stub
		
		this.ipDireccion = IpAddress;
		this.puerto = port;
	}
	
	//JUGADOR CONECTADO DE FORMA LOCAL
	public PlayerMP(float x, float y, ID id, HandlerEnemigo handEnemigo, InetAddress IpAddress, int port) {
		super(x, y, id, null, handEnemigo);
		// TODO Auto-generated constructor stub
		
		this.ipDireccion = IpAddress;
		this.puerto = port;
	}

	@Override
	public void tick() {
		super.tick();
	}
}
