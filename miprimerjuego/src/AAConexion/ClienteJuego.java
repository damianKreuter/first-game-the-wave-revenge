package AAConexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
			System.out.println("SEVER: " + mensaje);
			
			caso000(mensaje);
			caso020(mensaje);
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
