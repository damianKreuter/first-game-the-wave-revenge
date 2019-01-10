package Paquete;

import java.net.ServerSocket;

import AAConexion.ClienteJuego;
import AAConexion.ServidorJuego;

public abstract class packet {

	public static enum PacketTypes {
		INVALID(-1), LOGIN(00), DESCONECTADO(01), ESPERARJUGADORES(02), MOVIMIENTOJUGADOR(03);
		private int packetId;
		private PacketTypes(int packetId) {
			this.packetId = packetId;
		}
		
		public int getId() {
			return packetId;
		}
	}
	
	public byte packetId;

	public packet(int packetId) {
		this.packetId = (byte) packetId;
	}
	
	public abstract byte[] getData();
	
	public abstract void writeData(ClienteJuego cliente);
	
	public abstract void writeData(ServidorJuego server);

	public String readData(byte[] data) {
		String mensaje = new String(data).trim();
		return mensaje.substring(2);
	}
	
	public static PacketTypes lookupPacket(String id) {
		try {
			return lookupPacket(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			return PacketTypes.INVALID;
			// TODO: handle exception
		}
		/*
		for(PacketTypes p : PacketTypes.values()) {
			return p;
		}
		return PacketTypes.INVALID;*/
	}
	
	public static PacketTypes lookupPacket(int id) {
		for(PacketTypes p : PacketTypes.values()) {
			if(p.packetId == id) {
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
}

