package Paquete;

import AAConexion.ClienteJuego;
import AAConexion.ServidorJuego;
import Jugador.PlayerMP;
import Spawn.Spawn;
import base.ID;
import base.JuegoBase;

public class PaqueteMovimientoJugador extends packet{
	private String user;
	private int x, y;
	private JuegoBase juego;
	
	public PaqueteMovimientoJugador(byte[] data, JuegoBase juego) {
		super(03);
		this.juego = juego;
		String[] dataArray = readData(data).split(",");
		this.user = juego.nombreSistema;
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
	}

	public String moverJugador(String codigo) {
		if(codigo == "arriba") {
			return "030" + this.user;
		}
		if(codigo == "abajo") {
			return "031" + this.user;
		}
		if(codigo == "izquierda") {
			return "032" + this.user;
		}
		if(codigo == "derecha" ) {
			return "033" + this.user;
		}
		if(codigo == "vertical0") {
			return "034" + this.user;
		}
		/*
		if(codigo == "horizontal0") {
			return "035" + this.user;
		}*/
		return "035" + this.user;
	}
	
	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return ("03"+ this.user +","+ juego.jugadorSistema.getX() +","+ juego.jugadorSistema.getY()).getBytes();
	}
	
	public byte[] getDataArriba() {
		// TODO Auto-generated method stub
		return ("030"+ this.user).getBytes();
	}
	public byte[] getDataAbajo() {
		// TODO Auto-generated method stub
		return ("031"+ this.user).getBytes();
	}
	public byte[] getDataIzquierda() {
		// TODO Auto-generated method stub
		return ("032"+ this.user).getBytes();
	}
	public byte[] getDataDerecha() {
		// TODO Auto-generated method stub
		return ("033"+ this.user).getBytes();
	}
	
	public PaqueteMovimientoJugador(String user) {
		super(03);
		this.user = user;
	}
	
	public void mandarDatos(ClienteJuego cliente, String direccion) {
		cliente.sendData(moverJugador(direccion).getBytes());
	}
	
	@Override
	public void writeData(ClienteJuego cliente) {
		// TODO Auto-generated method stub
		cliente.sendData(getData());
		
	}

	@Override
	public void writeData(ServidorJuego server) {
		// TODO Auto-generated method stub
		server.sendDataToAllClient(getData());
	}

}
