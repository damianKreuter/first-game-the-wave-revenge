package Menu;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JFrame;

import HUD.HUDPrincipal;
import base.HandlerEnemigo;
import base.ID;
import base.Handler;
import base.JuegoBase;
import base.JuegoBase.ESTADO;
import base.Ventana;
import personajes.Player;




public class Menu2 extends JFrame implements MouseListener
{
	private Graphics dbg;
	private Graphics gg;
	private Image dbImage;
	
	
	private static Handler handler;
	private HandlerEnemigo handlerEnemigo;
	private HUDPrincipal HUD;
//	private MenuEfectos eventosMouseArribaDeCaja;
	
	//DIMENSIONES DE CAJA EN X, ANCHO Y ALTO
	private int anchoCaja =200, altoCaja =50, xCaja  = (JuegoBase.ANCHO/2)-anchoCaja/2;
	
	//POSICION Y DE CAJA 1
	private int ycaja1 = 300;
	
	//POSICION Y DE CAJA 2
	private int ycaja2 = 400;
		
	//POSICION Y DE CAJA 1
	private int ycaja3 = 500;
	
	private int xcuadrado, ycuadrado;
	
	private static int degradeCaja1;
	private static int degradeCaja2;
	private static int degradeCaja3;
	
	private boolean enCaja1, enCaja2, enCaja3;
	private String estado = "";
	
	public Menu2(Handler handler, HandlerEnemigo handlerEnemigo, HUDPrincipal hud) {
		this.handler = handler;
		this.handlerEnemigo = handlerEnemigo;
		HUD = hud;
		
		degradeCaja1 = 30;
		degradeCaja2 = 30;
		degradeCaja3 = 30;
		
	}
	
	public Handler darHandler() {
		return handler;
	}
	
	public HUDPrincipal hudPrincipalMenu() {
		return HUD;
	}
	
	public void cambiarXY(int x, int y) {
		xcuadrado = x;
		ycuadrado = y;
		
	}
	
	public static int getDegrade1() {
		return degradeCaja1;
	}
	
	public static int getDegrade2() {
		return degradeCaja2;
	}
	
	public static int getDegrade3() {
		return degradeCaja3;
	}
	
	public void cambiarDegrade1(int deg) {
		degradeCaja1 = deg;
	}
	
	public void cambiarDegrade2(int deg) {
		degradeCaja2 = deg;
	}
	
	public void cambiarDegrade3(int deg) {
		degradeCaja3 = deg;
	}
	
	public void mousePressed(MouseEvent m) {
		
		int mx = m.getX();
		int my = m.getY();
		
		//VENTANA DE VICTORIA
				if(JuegoBase.estadoJuego() == ESTADO.Victoria) {
					//BOTON SIGUIENTE NIVEL
					if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
						HUD.setNivel(1);
						HUD.setPuntaje(0);
						JuegoBase.cambiarEstado(ESTADO.Juego);
						handlerEnemigo.vaciarObjecto();
						handler.vaciarPersonaje();
						handler.addObject(new Player((JuegoBase.ANCHO/2)-32, (JuegoBase.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo));
					}
					
					//BOTON VOLVER A MENU
					if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
						handlerEnemigo.vaciarObjecto();
						handler.vaciarPersonaje();
						JuegoBase.cambiarEstado(ESTADO.Menu);
					}
				}
		
		//BOTON COMENZAR
		if(JuegoBase.estadoJuego() == ESTADO.Menu) {
			if(mouseOver(mx, my, xCaja, ycaja1, anchoCaja, altoCaja)) {
			
			JuegoBase.cambiarEstado(ESTADO.Juego);
			HUD.setNivel(1);
			HUD.setPuntaje(0);
			handlerEnemigo.vaciarObjecto();
			handler.addObject(new Player((JuegoBase.ANCHO/2)-32, (JuegoBase.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo));
		}
			}
		
		//BOTON AYUDA
		if(JuegoBase.estadoJuego() == ESTADO.Menu) {
			if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
				JuegoBase.cambiarEstado(ESTADO.Ayuda);
			}
			
		}
		
		//BOTON VOLVER
		if(JuegoBase.estadoJuego() == ESTADO.Ayuda || JuegoBase.estadoJuego() == ESTADO.Fin) {
			if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
				JuegoBase.cambiarEstado(ESTADO.Menu);
		}
		
		} else {
			//BOTON SALIE
			if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
				System.exit(1);
			}
		}
		
		//BOTON VOLVER A INTENTAR
		if(JuegoBase.estadoJuego() == ESTADO.Fin) {
			if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
				HUD.setNivel(1);
				HUD.setPuntaje(0);
				JuegoBase.cambiarEstado(ESTADO.Juego);
				handlerEnemigo.vaciarObjecto();
				handler.addObject(new Player((JuegoBase.ANCHO/2)-32, (JuegoBase.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo));
			}
		}
	}
	
	public void mouseReleased(MouseEvent m) {
		
	}

	private boolean mouseOver(int mx, int my, int x, int y, int ancho, int alto) {
		if(mx > x && mx < x + ancho) {
			if(my > y && my < y + alto) {
				return true;
			}
		}
		return false;
	}

	public void tick() {
	}
	
	public void render(Graphics g) {
		Font fnts = new Font("Arial", 1, 10);
		g.setFont(fnts);
		g.setColor(Color.white);
		g.drawString(xcuadrado + " " + ycuadrado, 10, 30);

		
		//VENTANA DE AYUDA, SOLO MUESTRA INFO RELEVANTE DEL JUEGO
		if(JuegoBase.estadoJuego() == ESTADO.Ayuda) {
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("AYUDA", JuegoBase.ANCHO/2 - 75, 100);
			fnt = new Font("Arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Usa las teclas direccionales para mover y esquivar los ataques", 125, 225);
			
			//CAJA VOLVER
		
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
			
			
			
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Volver", 415, 535);
		}
		
		//VENTANA FIN DE PARTIDA, SE ACTIVA CUANDO EL, O LOS JUGADORES MUEREN
		else if(JuegoBase.estadoJuego() == ESTADO.Fin) {
		
			//PANTALLA DE FIN DE JUEGO, MUESTRA PUNTAJE Y NIVEL
			
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GAME OVER", 100, 100);
			fnt = new Font("Arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Puntaje: "+ HUD.getPuntaje(), 125, JuegoBase.BASEALTURAHUD+ 235);
			
					
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Nivel: " + HUD.getNivel(), 125, JuegoBase.BASEALTURAHUD+ 275);
			

			//caja VOLVER A MENU
		
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja3)); 
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Volver al menu", 360, 535);
			
			//caja iNTENTAR DE NUEVO
		
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja2, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja2, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja2)); 

			fnt = new Font("Arial", 1, 21);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Intentar de nuevo", 360, 435);
			
			
		} 
		//VENTANA DE VICTORIA, PARECIDA A LA VENTANA FIN
		else if(JuegoBase.estadoJuego() == ESTADO.Victoria) {
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GAME OVER", 100, 100);
			fnt = new Font("Arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Puntaje: "+ HUD.getPuntaje(), 125, JuegoBase.BASEALTURAHUD+ 235);
			
					
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Nivel: " + HUD.getNivel(), 125, JuegoBase.BASEALTURAHUD+ 275);
			

			//caja VOLVER A MENU
		
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja3)); 
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Volver al menu", 360, 535);
			
			//caja SIGUIENTE NIVEL
			//Al apretar va directo al siguiente nivel del juego
			
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja2, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja2, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja2)); 

			fnt = new Font("Arial", 1, 21);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Siguiente nivel", 360, 435);
		}
		
		//VENTANA MENU PRINCIPAL
		else if(JuegoBase.estadoJuego() == ESTADO.Menu) {
			Font fnt = new Font("Arial", 1, 50);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("MENU", JuegoBase.ANCHO/2 - 75, 100);
			
			fnt = new Font("Arial", 1, 25);
			
			//CAJA DE MAS ARRIBA
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString(estado, 415, 335);
			
			g.setColor(Color.WHITE);
		    g.drawRect(xCaja, ycaja1, anchoCaja, altoCaja);
			
		    //RESPLANDOR QUE TIENE CADA CAJA, SI SE PASA EL MOUSE POR ENCIMA SE VUELVE MAS BLANCO EL RESPLANDOR
		    handler.addObject(new reflejoBoton(xCaja, ycaja1, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja1)); 
			
		    
			
			//2º caja AYUDA
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja2, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja2, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, degradeCaja2));
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Estadísticas", 380, 435);
			
			//3º caja SALIR
		
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, degradeCaja3));
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Salir", 425, 535);
			
		}
		
	}
	
	//NO RELLENARLOS, NO SON NECESARIOS

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
