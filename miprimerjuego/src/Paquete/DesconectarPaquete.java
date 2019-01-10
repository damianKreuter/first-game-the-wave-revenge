package Paquete;

import AAConexion.ClienteJuego;
import AAConexion.ServidorJuego;

public class DesconectarPaquete extends packet{

	private String user;
	
	public DesconectarPaquete(byte[] data) {
		super(04);
		this.user = readData(data);
		// TODO Auto-generated constructor stub
	}
	
	public String getUser() {
		return user;
	}
	
	public DesconectarPaquete(String nombre) {
		super(04);
		user = nombre;
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return ("01" + this.user).getBytes();
	}

	@Override
	public void writeData(ClienteJuego cliente) {
		// TODO Auto-generated method stub
		cliente.sendData(getData());
	}

	@Override
	public void writeData(ServidorJuego server) {
		// TODO Auto-generated method stub
		server.sendDataToAllClient(getData());;
	}

}
