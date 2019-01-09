package Paquete;

import AAConexion.ClienteJuego;
import AAConexion.ServidorJuego;

public class paqueteEsperarJugador extends packet{
	private String user;
	
	public paqueteEsperarJugador(byte[] data) {
		super(02);
		this.user = readData(data);
	}
	
	public paqueteEsperarJugador(String user) {
		super(02);
		this.user = user;
	}
	
	@Override
	public void writeData(ClienteJuego cliente) {
		cliente.sendData(getData());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeData(ServidorJuego server) {
		server.sendDataToAllClient(getData());
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return ("02"+ this.user).getBytes();
	}
	
	public String getUser() {
		return user;
	}
}
