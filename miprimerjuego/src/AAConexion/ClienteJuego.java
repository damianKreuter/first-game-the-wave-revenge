package AAConexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Jugador.Player;
import Jugador.PlayerMP;
import Paquete.DesconectarPaquete;
import Paquete.Packet00Login;
import Paquete.packet;
import Paquete.packet.PacketTypes;
import Spawn.Spawn;
import base.Handler;
import base.ID;
import base.JuegoBase;

public class ClienteJuego extends Thread{
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private JuegoBase juego;
	
	public ClienteJuego(JuegoBase juegobase, String ip) {
		this.juego = juegobase;
		
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ip);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parsePacket(byte[] data, InetAddress direccion, int port) {
		String mensaje = new String(data).trim();interrupt();
		PacketTypes type = packet.lookupPacket(mensaje.substring(0, 2));
		switch(type) {
			default:
			case INVALID: 
				break;
			case LOGIN: 
				Packet00Login packetss = new Packet00Login(data);
				System.out.println("["+direccion.getHostAddress()+":"+port+"]");
				break;
			case DESCONECTADO: 
				DesconectarPaquete packetDes = new DesconectarPaquete(data);
				System.out.println("["+direccion.getHostAddress()+":"+port+"] " + packetDes.getUser() + " se fue");

				break;
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
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
			
			String mensaje = new String(packet.getData());
			System.out.println("SEVER: " + mensaje);
			
			caso000(mensaje);
			caso010(mensaje);
			caso020(mensaje);
			caso030(mensaje);
			casoXXX(mensaje);
		}
	}
	
	private void caso010(String mensaje) {
		if(mensaje.substring(0, 2).equalsIgnoreCase("01")) {
			//SE DEBE CREAR UN USUARIO
			String imagens = mensaje.substring(3, mensaje.length()-3);

			juego.establecerJug2(null);
			juego.nombreUserMP = null;
		}
	}
	
	private void casoXXX(String mensaje) {
		if(mensaje.substring(0, 3).equalsIgnoreCase("XXX")) {
			//SE DEBE CREAR UN USUARIO
			String imagens = mensaje.substring(3, mensaje.length()-3);
	//		int hasta = datos.indexOf(":");
	//		String direccion = mensaje.substring(0, hasta);
	//		String puerto = mensaje.substring(hasta+1, mensaje.length()-hasta);
			int imagen = Integer.valueOf(imagens.trim());
			juego.establecerJug2(new Player(250, 250, ID.Player, new Handler(), Spawn.getHandlerEnemigo(), imagen));
			juego.establecerJug1(new Player(350, 350, ID.Player, new Handler(), Spawn.getHandlerEnemigo(), 2));
		}
	}
	
	//MOVIMIENTOS DEL JUGADOR NO ES PROPIO DEL SISTEMA
	
	private void caso030(String mensaje) {
		if(mensaje.substring(0, 3).equalsIgnoreCase("030")) {
			//MOVIMIENTO HACIA ARRIBA
			juego.jugadorMP.setVelY(-10);
		}
		
		if(mensaje.substring(0, 3).equalsIgnoreCase("031")) {
			//MOVIMIENTO ABAJO
			juego.jugadorMP.setVelY(10);
		}
			
		
		if(mensaje.substring(0, 3).equalsIgnoreCase("032")) {
			//MOVIMIENTO IZQUIERDA
			juego.jugadorMP.setVelX(-10);
		}
		
		if(mensaje.substring(0, 3).equalsIgnoreCase("033")) {
			//MOVIMIENTO DERECHA
			juego.jugadorMP.setVelX(10);
		}
		if(mensaje.substring(0, 3).equalsIgnoreCase("034")) {
			//MOVIMIENTO DERECHA
			juego.jugadorMP.setVelY(0);
		}
		if(mensaje.substring(0, 3).equalsIgnoreCase("035")) {
			//MOVIMIENTO DERECHA
			juego.jugadorMP.setVelX(0);
		}
	}
	
	private void caso01 (String mensaje) {
		if(mensaje.substring(0, 2).equalsIgnoreCase("01")) {
			String nombre = mensaje.substring(2, mensaje.length()-2);
			//ELIMINA EL PERSONAJE QUE SE DESCONECTÓ
			
			//COMO SOLO HAY 2 PERSONAJES, Y SI RECIBE ESTE MENSAJE ENTONCES
			juego.jugadorMP = null;
			juego.nombreUserMP = null;
			
		}
	}
	
	private void caso020(String mensaje) {
		if(mensaje.substring(0, 3).equalsIgnoreCase("020")) {
			String nombre = mensaje.substring(3, mensaje.length()-3);
			//ORDENAR LOS NOMBRES, EL HOST ES EL JUGADOR 1, EL CLIENTE ES EL JUGADOR 2
			String tipo = nombre.substring(0, 1);
			if(tipo.contains("1")) {
				//ESPERO A MI CONTRINCANTE
				juego.esperarJugador = "Esperando otros jugadores";
			}
			if(tipo.contains("2")) {
				juego.esperarJugador = "Confirma la partida";
			}
			if(tipo.contains("3")) {
				//COMIENZA LA PARTIDA
				juego.esperarJugador = "";
				juego.iniciarElJuego();
			}
		}
	}
	
	private void caso000(String mensaje) {
		if(mensaje.substring(0, 3).equalsIgnoreCase("000")) {
			//SE PASA UN NOMBRE
			String nombre = mensaje.substring(3, mensaje.length()-3);
			//ORDENAR LOS NOMBRES, EL HOST ES EL JUGADOR 1, EL CLIENTE ES EL JUGADOR 2
			String tipo = nombre.substring(0, 1);
			boolean aaa = tipo.contains("1");
			if(aaa) {
				String nombress =  nombre.substring(1, nombre.length()-1).trim();
				juego.nombreUser = nombress;
			} else {
				juego.nombreUserMP = nombre.substring(1, nombre.length()-1).trim();
			}
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
