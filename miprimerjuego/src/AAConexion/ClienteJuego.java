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
