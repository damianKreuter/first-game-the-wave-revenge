package AAConexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import base.JuegoBase;

public class ServidorJuego extends Thread{
	private DatagramSocket socket;
	private JuegoBase juego;
	private int puerto = 1331;
	
	public ServidorJuego(JuegoBase juegobase) {
		this.juego = juegobase;
		
		try {
			this.socket = new DatagramSocket(puerto);
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
			
			String mensaje2 = new String(packet.getData());
			if(mensaje2.trim().equalsIgnoreCase("ping") ) {
				System.out.print("Devuelvo pong");
				sendData("pong".getBytes(), packet.getAddress());
			}
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAddress) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, puerto);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
