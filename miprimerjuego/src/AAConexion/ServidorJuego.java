package AAConexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import Jugador.PlayerMP;
import Paquete.Packet00Login;
import Paquete.packet;
import Paquete.packet.PacketTypes;
import Spawn.Spawn;
import base.ID;
import base.JuegoBase;

public class ServidorJuego extends Thread{
	private DatagramSocket socket;
	private JuegoBase juego;
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public ServidorJuego(JuegoBase juegobase) {
		this.juego = juegobase;
		
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
			/*
			String mensaje = new String(packet.getData());
			System.out.println("CLIENTE: ["+packet.getAddress().getHostAddress()+":"+packet.getPort()+"]  " + mensaje);
			
			String mensaje2 = new String(packet.getData());
			if(mensaje2.trim().equalsIgnoreCase("ping") ) {
				System.out.print("Devuelvo pong");
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
			*/
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		// TODO Auto-generated method stub
		String mensaje = new String(data).trim();
		PacketTypes type = packet.lookupPacket(mensaje.substring(0, 2));
		switch(type) {
			default:
			case INVALID: break;
			case LOGIN: Packet00Login packet = new Packet00Login(data);
				System.out.println("["+address.getHostAddress()+":"+port+"] " + packet.getUser() + " ha entrado este virgo al combate");
				PlayerMP player = null;
				if(address.getHostName().equalsIgnoreCase("127.0.0.1")) {
					player = new PlayerMP(250, 250, ID.Player2, Spawn.getHandlerEnemigo(), address, port);
					
				} else {
					player = new PlayerMP(250, 250, ID.Player2, Spawn.getHandlerEnemigo(), address, port);
				}
				if(player != null) {
					this.connectedPlayers.add(player);
					JuegoBase.handler.addObject(player);
				}
				
				break;
			case DESCONECTADO: 
				break;
		}
	
	}

	public void sendData(byte[] data, InetAddress ipAddress, int puerto) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, puerto);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}

	public void sendDataToAllClient(byte[] data) {
		// TODO Auto-generated method stub
		for(PlayerMP p : connectedPlayers) {
			sendData(data, p.ipDireccion, p.puerto);
		}
	}
}
