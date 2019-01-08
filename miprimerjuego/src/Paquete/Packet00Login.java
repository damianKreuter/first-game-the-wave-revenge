package Paquete;

import AAConexion.ClienteJuego;
import AAConexion.ServidorJuego;

public class Packet00Login extends packet{

	private String user;
	
	public Packet00Login(byte[] data) {
		super(00);
		this.user = readData(data);
	}
	
	public Packet00Login(String user) {
		super(00);
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
		return ("00"+ this.user).getBytes();
	}
	
	public String getUser() {
		return user;
	}
}
