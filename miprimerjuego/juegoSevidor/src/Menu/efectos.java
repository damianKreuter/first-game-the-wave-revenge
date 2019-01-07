package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import HUD.HUDPrincipal;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;
import base.JuegoBase.ESTADO;

public class efectos extends JFrame implements MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics dbg;
	private Image dbImage;
 
	private int x, y0, y1, y2, y3, ancho, altura, mx, my;
	boolean mouseDrag, enPosicion;
	boolean enCaja1, enCaja2, enCaja3, enCaja0;
	
	Handler handler;
	
	Menu2 menu;
	
	public efectos(int posX, int ancho, int alto, int posy0, int posy1, int posy2, int posy3, Handler handler, Menu2 m) {
		
		this.handler = handler;
		x = posX;
		this.ancho = ancho;
		altura = alto;
		y0 = posy0;
		y1 = posy1;
		y2 = posy2;
		y3 = posy3;
		menu = m;
		addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Graphics g) {

		dbImage = createImage(JuegoBase.ANCHO, JuegoBase.ALTO);
		dbg = dbImage.getGraphics();
		paintComponent(g);
		
	}
	
	public void paintComponent(Graphics g) {

		if(enCaja1) {
			menu.cambiarDegrade1(10);
//			handler.addObject(new reflejoBoton(x, y1, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10));
		} else {
			menu.cambiarDegrade1(30);
//			handler.addObject(new reflejoBoton(x, y1, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30));
		} 
		
		if(enCaja2) {
			menu.cambiarDegrade2(10);
//			handler.addObject(new reflejoBoton(x, y2, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10)); 
		} else {
			menu.cambiarDegrade2(30);
//			handler.addObject(new reflejoBoton(x, y2, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30)); 
		}
		if(enCaja3){
			menu.cambiarDegrade3(10);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10)); 
		} else {
			menu.cambiarDegrade3(30);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30)); 
		}
		if(enCaja0) {
			menu.cambiarDegrade0(10); 
		} else {
			menu.cambiarDegrade0(30); 
		}
		
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		
		enCaja0 = false;
		enCaja1 = false;
		enCaja2 = false;
		enCaja3 = false;
		
		//EN CAJA 1
				if(adentro(y1, mx, my)) 
				{
					enCaja1 = true;
					enCaja2 = false;
					enCaja3 = false;
					enCaja0 = false;
				} else
				//EN CAJA 2
				if(adentro(y2, mx, my)) 
				{
					enCaja2 = true;
					enCaja1 = false;
					enCaja3 = false;
					enCaja0 = false;
				} else
				//EN CAJA 3
				if(adentro(y3, mx, my)) 
				{
					enCaja3 = true;
					enCaja2 = false;
					enCaja1 = false;
					enCaja0 = false;
				} else 
					//EN CAJA 0
				if(adentro(y0, mx, my)) {
					enCaja0 = true;
					enCaja2 = false;
					enCaja1 = false;
					enCaja3 = false;
				}
		
		if(enCaja1) {
			menu.cambiarDegrade1(10);
//			handler.addObject(new reflejoBoton(x, y1, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10));
		} else {
			menu.cambiarDegrade1(30);
//			handler.addObject(new reflejoBoton(x, y1, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30));
		} 
		
		if(enCaja2) {
			menu.cambiarDegrade2(10);
//			handler.addObject(new reflejoBoton(x, y2, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10)); 
		} else {
			menu.cambiarDegrade2(30);
//			handler.addObject(new reflejoBoton(x, y2, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30)); 
		}
		if(enCaja3){
			menu.cambiarDegrade3(10);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10)); 
		} else {
			menu.cambiarDegrade3(30);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30)); 
		}
		if(enCaja0) {
			menu.cambiarDegrade0(10); 
		} else {
			menu.cambiarDegrade0(30); 
		}
	//	e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		
		enCaja0 = false;
		enCaja1 = false;
		enCaja2 = false;
		enCaja3 = false;
		
		menu.cambiarXY(mx, my);
		
		//EN CAJA 0
		if(adentro(y0, mx, my)) {
			enCaja0 = true;
			enCaja1 = false;
			enCaja2 = false;
			enCaja3 = false;
		}
		
		
		//EN CAJA 1
		if(adentro(y1, mx, my)) 
		{
			enCaja0 = false;
			enCaja1 = true;
			enCaja2 = false;
			enCaja3 = false;
		} else
		//EN CAJA 2
		if(adentro(y2, mx, my)) 
		{
			enCaja0 = false;
			enCaja2 = true;
			enCaja1 = false;
			enCaja3 = false;
		} else
		//EN CAJA 3
		if(adentro(y3, mx, my)) 
		{
			enCaja0 = false;
			enCaja3 = true;
			enCaja2 = false;
			enCaja1 = false;
		}
		
		if(enCaja0){
			menu.cambiarDegrade0(10);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10)); 
		} else {
			menu.cambiarDegrade0(30);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30)); 
		}
		
		if(enCaja1) {
			menu.cambiarDegrade1(10);
//			handler.addObject(new reflejoBoton(x, y1, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10));
		} else {
			menu.cambiarDegrade1(30);
//			handler.addObject(new reflejoBoton(x, y1, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30));
		} 
		
		if(enCaja2) {
			menu.cambiarDegrade2(10);
//			handler.addObject(new reflejoBoton(x, y2, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10)); 
		} else {
			menu.cambiarDegrade2(30);
//			handler.addObject(new reflejoBoton(x, y2, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30)); 
		}
		if(enCaja3){
			menu.cambiarDegrade3(10);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 10)); 
		} else {
			menu.cambiarDegrade3(30);
//			handler.addObject(new reflejoBoton(x, y3, ID.Cola, new Color(255, 27, 27), 200, altura, 0.05f, handler, 30)); 
		}
	//	e.consume();
	}
	
	private boolean adentro(int y, int mx, int my) {
		if(mx > x && (mx -x) < ancho) {
			if(my > y && (my -y)<altura) {
				return true;
			}
		}
		return false;
	}

}
