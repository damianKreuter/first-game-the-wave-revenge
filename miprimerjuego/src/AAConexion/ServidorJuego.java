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
	private PlayerMP jugadorMP;
	private PlayerMP jugador1;
	
	private boolean jug1ParaJugar;
	private boolean jug2ParaJugar;
	
	private int cantMaxJugadores;
	private int cantiJugadoresEsperando;
	
	public ServidorJuego(JuegoBase juegobase) {
		this.juego = juegobase;
		cantMaxJugadores = 2;
		cantiJugadoresEsperando = 2;
		jug1ParaJugar = false;
		jug2ParaJugar = false;
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
			
			
			
			String mensaje = new String(packet.getData());
			System.out.println("CLIENTE: ["+packet.getAddress().getHostAddress()+":"+packet.getPort()+"]  " + mensaje);
			if(cantMaxJugadores > 0) {
				parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
			}
			
			String codigo = mensaje.substring(0, 2).trim();
			caso00(codigo, mensaje);
			caso02(codigo, mensaje);
			
			
			String mensaje2 = new String(packet.getData());
			if(mensaje2.trim().equalsIgnoreCase("ping") ) {
				System.out.print("Devuelvo pong\n");
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
			
		}
	}
	
	private void caso02(String code, String mensaje) {
		if(code.trim().equalsIgnoreCase("02")) {
			//SE PASA UNA PETICION PARA JUGAR
			String nombre = mensaje.substring(2, mensaje.length()-2);
			cantiJugadoresEsperando++;
			
			if(jug1ParaJugar == true || jug2ParaJugar == true) {
				//JUGAR
				sendDataToAllClient(("0203").getBytes());
			} else
			//ORDENAR LOS NOMBRES, EL HOST ES EL JUGADOR 1, EL CLIENTE ES EL JUGADOR 2
			if(nombre == juego.nombreUser) {
				//JUGADOR HOST
				sendData(("0201esperar").getBytes(), jugador1.ipDireccion, jugador1.puerto);
				sendData(("0202confirmar").getBytes(), jugadorMP.ipDireccion, jugadorMP.puerto);
				jug1ParaJugar = true;
			} else {
				jug2ParaJugar = true;
				sendData(("0202confirmar").getBytes(), jugador1.ipDireccion, jugador1.puerto);
				sendData(("0201esperar").getBytes(), jugadorMP.ipDireccion, jugadorMP.puerto);
			}
	//		ordenarNombresDeJugadores(nombre);
		}
	}
	
	private void caso00(String code, String mensaje) {
		if(code.trim().equalsIgnoreCase("00")) {
			//SE PASA UN NOMBRE
			String nombre = mensaje.substring(2, mensaje.length()-2);
			
			//ORDENAR LOS NOMBRES, EL HOST ES EL JUGADOR 1, EL CLIENTE ES EL JUGADOR 2
			ordenarNombresDeJugadores(nombre);
		}
	}
	
	
	
	private void ordenarNombresDeJugadores(String nombre) {
		String nombre1 = JuegoBase.nombreUser;
		
		sendDataToAllClient(("0001"+nombre1).getBytes());
		if(cantMaxJugadores == 1) {
			sendDataToAllClient(("0002"+nombre).getBytes());
		}
		cantMaxJugadores--;
		
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
					if(cantMaxJugadores == 2) {
						jugador1 = new PlayerMP(250, 250, ID.Player, Spawn.getHandlerEnemigo(), address, port);
					} else {
						jugadorMP = new PlayerMP(250, 250, ID.Player, Spawn.getHandlerEnemigo(), address, port);
					}
					
					
				} else {
					if(cantMaxJugadores == 2) {
						jugador1 = new PlayerMP(250, 250, ID.Player, Spawn.getHandlerEnemigo(), address, port);
					} else {
						jugadorMP = new PlayerMP(250, 250, ID.Player, Spawn.getHandlerEnemigo(), address, port);
					}
				}
				if(player != null) {
					juego.jugadorMP = jugadorMP;
					
		//			this.connectedPlayers.add((PlayerMP) juego.jugadorMP);
					JuegoBase.handler.addObject(juego.jugadorMP);
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
	//	for(PlayerMP p : connectedPlayers) {
		if(jugadorMP != null) {
			String dir = jugadorMP.ipDireccion.getHostAddress();
			int port =  jugadorMP.puerto;
			sendData(data, jugadorMP.ipDireccion, jugadorMP.puerto);
		}
		String dir = jugador1.ipDireccion.getHostAddress();
		int port =  jugador1.puerto;
		sendData(data, jugador1.ipDireccion, jugador1.puerto);
			
	//	}
	}
}
