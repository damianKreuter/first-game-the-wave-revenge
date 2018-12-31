package base;

import base.JuegoBase;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Ventana extends Canvas{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1545022781462869201L;
	
	public JFrame frame;
	
	public Ventana(int ancho, int alto, String titulo, JuegoBase juego) {
		frame = new JFrame(titulo);
		
		//CONFIGURACIÓN DE TAMAÑO DE LA PANTALLA, DEFAUTL, MAXIMO Y MINIMO
		frame.setPreferredSize(new Dimension(ancho, alto));
		frame.setMaximumSize(new Dimension(ancho, alto));
		frame.setMinimumSize(new Dimension(ancho, alto));
		
		//SI EL JEUGO SE CIERRA, SE CIERRA POR COMPLETO
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ESTO ES PARA QUE EL USUARIO PUEDA O NO MODIFICAR LA DIMENSION
		//DE LA VENTANA
		frame.setResizable(false);
		//PONE LA PANTALLA EN EL CENTRO POR DEFAULT
		frame.setLocationRelativeTo(null);
		frame.add(juego);
		frame.setVisible(true);
		juego.start();
	}
	
	public JFrame getJFrame() {
		return frame;
	}
	
	public void setFrame(JFrame fr) {
		frame = fr;
	}
}
