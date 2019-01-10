package base;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Paquete.DesconectarPaquete;

public class windowHandler implements WindowListener{

	private final JuegoBase juego;
	
	public windowHandler(JuegoBase juego) {
		this.juego = juego;
		this.juego.vent.frame.addWindowListener(this);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		DesconectarPaquete packet = new DesconectarPaquete(this.juego.nombreSistema);
		packet.writeData(this.juego.socketClie);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
