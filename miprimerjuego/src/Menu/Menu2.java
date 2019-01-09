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
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JFrame;

import HUD.HUDPrincipal;
import Jugador.Player;
import Paquete.Packet00Login;
import Paquete.paqueteEsperarJugador;
import Spawn.Spawn;
import base.HandlerEnemigo;
import base.ID;
import base.Handler;
import base.JuegoBase;
import base.JuegoBase.ESTADO;
import hojaDeSprites.Sprites;
import hojaDeSprites.cargarImagen;
import base.Ventana;
import manejoArchivos.datosDeGuardado;
import musicayefectosdesonido.audioplayer;




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
	private int ycaja0 = 200;
	
	//POSICION Y DE CAJA 1
	private int ycaja1 = 300;
	
	//POSICION Y DE CAJA 2
	private int ycaja2 = 400;
		
	//POSICION Y DE CAJA 1
	private int ycaja3 = 500;
	
	private int xcuadrado, ycuadrado;
	
	private static int degradeCaja0;
	private static int degradeCaja1;
	private static int degradeCaja2;
	private static int degradeCaja3;
	
	private boolean enCaja1, enCaja2, enCaja3;
	private String estado = "";
	private JuegoBase juego;
	
	private boolean cargoVista;
	BufferedImage imagenTeclasDireccionales;
	
	private datosDeGuardado dat;
	private String mensajeEsperaParaJugar;
	
	public Menu2(Handler handler, HandlerEnemigo handlerEnemigo, HUDPrincipal hud, JuegoBase juegoBase) {
		this.handler = handler;
		this.handlerEnemigo = handlerEnemigo;
		HUD = hud;
		degradeCaja0 = 30;
		degradeCaja1 = 30;
		degradeCaja2 = 30;
		degradeCaja3 = 30;
		
		mensajeEsperaParaJugar = "";
		
		juego = juegoBase;
		cargarImagen cargarImagenes = new cargarImagen();
		imagenTeclasDireccionales = cargarImagenes.cargarImagen("/teclasdireccionales.png");
		
		dat = new datosDeGuardado();
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
	
	public static int getDegrade0() {
		return degradeCaja0;
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
	
	public void cambiarDegrade0(int deg) {
		degradeCaja0 = deg;
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
		
		if(cargoVista) {
			cargoVista = false;
		//VENTANA DE VICTORIA
				if(juego.estadoJuego() == ESTADO.Victoria) {
					//BOTON VOLVER A JUGAR
					if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
						HUD.setNivel(1);
						HUD.setPuntaje(0);
						juego.cambiarEstado(ESTADO.Juego);
						HUD.comenzarDeNuevo();
						handlerEnemigo.vaciarObjecto();
						handler.vaciarPersonaje();
						juego.comienzaElJuego();
						juego.jugador = new Player((juego.ANCHO/2)-32, (juego.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo);
						handler.addObject(juego.jugador);
					}
					
					//BOTON VOLVER A MENU
					if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
						handlerEnemigo.vaciarObjecto();
						handler.vaciarPersonaje();
						juego.cambiarEstado(ESTADO.Menu);
					}
				}
		
		if(juego.estadoJuego() == ESTADO.EsperarJugadores) {
			//BOTON VOLVER A JUGAR
			if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
				HUD.setNivel(1);
				HUD.setPuntaje(0);
				juego.cambiarEstado(ESTADO.Juego);
				HUD.comenzarDeNuevo();
				handlerEnemigo.vaciarObjecto();
				handler.vaciarPersonaje();
				juego.comienzaElJuego();
				juego.jugador = new Player((juego.ANCHO/2)-32, (juego.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo);
				handler.addObject(juego.jugador);
				if(juego.nombreUserMP!="") {
					juego.jugadorMP = new Player((juego.ANCHO/2)-32, (juego.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo);
					handler.addObject(juego.jugadorMP);
				}
				
			}
			
			//BOTON VOLVER A MENU
			if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
				handlerEnemigo.vaciarObjecto();
				handler.vaciarPersonaje();
				juego.cambiarEstado(ESTADO.Menu);
			}
		}
	/*			
		//BOTON COMENZAR CON MULTI
		if(juego.estadoJuego() == ESTADO.Menu) {
			if(mouseOver(mx, my, xCaja, ycaja1, anchoCaja, altoCaja)) {
		//		juego.jugador = new Player((JuegoBase.ANCHO/2)-32, (JuegoBase.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo);
		//		handler.addObject(juego.jugador);
				juego.cambiarEstado(ESTADO.EsperarJugadores);
	//			HUD.setNivel(1);
	//			HUD.setPuntaje(0);
	//			HUD.comenzarDeNuevo();
	//			juego.comienzaElJuego();
	//			handlerEnemigo.vaciarObjecto();
			
			}
		}*/
		
		//BOTON COMENZAR SOLO
		if(juego.estadoJuego() == ESTADO.Menu) {
			if(mouseOver(mx, my, xCaja, ycaja1, anchoCaja, altoCaja)) {
				String nombreUserMPa = juego.nombreUserMP;
					if(nombreUserMPa == null) {
						juego.jugador = new Player((juego.ANCHO/2)-32, (juego.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo);
						handler.addObject(juego.jugador);
						juego.cambiarEstado(ESTADO.Juego);
						HUD.setNivel(1);
						HUD.setPuntaje(0);
						HUD.comenzarDeNuevo();	
						juego.comienzaElJuego();
						handlerEnemigo.vaciarObjecto();	
					} else {
						if(juego.nombreSistema == juego.nombreUser) {
							//EL JUGADOR HOST QUIERE JUGAR
							paqueteEsperarJugador esperarPaquete = new paqueteEsperarJugador(juego.nombreSistema);
							esperarPaquete.writeData(juego.socketClie);
						} else {
							paqueteEsperarJugador esperarPaquete = new paqueteEsperarJugador(juego.nombreSistema);
							esperarPaquete.writeData(juego.socketClie);
						}
						
					
				}
				
					
			}
		}
		
		//BOTON AYUDA
		if(juego.estadoJuego() == ESTADO.Menu) {
			if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
				juego.cambiarEstado(ESTADO.Ayuda);
			}
			
		}
		
		/* CONTINUAR LUEGO CON ESTO, TIENE UNA ISSUE ESTA PARTE
		//BOTON CONTROLES
		if(JuegoBase.estadoJuego() == ESTADO.Ayuda) {
			if(mouseOver(mx, my, xCaja, 200, anchoCaja, altoCaja)) {
				juego.cambiarEstado(ESTADO.Controles);
		//		Estado estado = JuegoBase.estadoJuego();
			}
		}
		
		//BOTON ESTADISTICAS
		if(JuegoBase.estadoJuego() == ESTADO.Ayuda) {
			if(mouseOver(mx, my, xCaja, ycaja1, anchoCaja, altoCaja)) {
				juego.cambiarEstado(ESTADO.Estadisticas);
			}
		}
		
		//BOTON CAMBIAR PERSONAJE
				if(JuegoBase.estadoJuego() == ESTADO.Ayuda) {
					if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
						juego.cambiarEstado(ESTADO.Personalizar);
					}
				}
		
		//BOTON VOLVER DESDE CONTROLES, ESTADISTICAS O CAMBIAR PERSONAJE
		if(JuegoBase.estadoJuego() == ESTADO.Personalizar || JuegoBase.estadoJuego() == ESTADO.Controles || JuegoBase.estadoJuego() == ESTADO.Estadisticas) {
			if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
				juego.cambiarEstado(ESTADO.Ayuda);
			}
		}		
		*/
		
		//BOTON VOLVER DESDE AYUDA, FIN DE JUEGO Y GANADOR
		if(juego.estadoJuego() == ESTADO.Ayuda || juego.estadoJuego() == ESTADO.Fin) {
			if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
				juego.cambiarEstado(ESTADO.Menu);
			}
		}
		
		 else {
			//BOTON SALIR
			if(mouseOver(mx, my, xCaja, ycaja3, anchoCaja, altoCaja)) {
				System.exit(1);
			}
		}
		
		
		//BOTON VOLVER A INTENTAR
		if(juego.estadoJuego() == ESTADO.Fin) {
			if(mouseOver(mx, my, xCaja, ycaja2, anchoCaja, altoCaja)) {
				HUD.setNivel(1);
				HUD.setPuntaje(0);
				HUD.comenzarDeNuevo();
				juego.cambiarEstado(ESTADO.Juego);
				handlerEnemigo.vaciarObjecto();
				juego.comienzaElJuego();
				juego.jugador = new Player((JuegoBase.ANCHO/2)-32, (JuegoBase.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo);
				handler.addObject(juego.jugador);
			}
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
		
		Font fuenteMasPequenia = new Font("Arial", 1, 10);

		
		//VENTANA DE CONFIGURAR, SOLO MUESTRA INFO RELEVANTE DEL JUEGO
		// DESDE PERSONALISAR PERSONAJE, VER TOP 10 MEJORES PUNTAJES, 
		if(juego.estadoJuego() == ESTADO.Ayuda) {
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Configuración", JuegoBase.ANCHO/2 - 155, 100);
			fnt = new Font("Arial", 1, 25);
			/*
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Usa las teclas direccionales para mover y esquivar los ataques", 75, 225);
			*/
			//AYUDA BOTONES
			
			g.setColor(Color.white);
			g.drawRect(xCaja, 200, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, 200, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja0)); 
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Controles", 400, 235);
			
			//ESTADISTICAS (TOP 10)
			
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja1, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja1, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja1)); 
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Estadisticas", 385, 335);
			
			//PERSONALISAR PERSONAJE
			
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja2, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja2, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja2)); 
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Cambiar", 410, 420);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("personaje", 400, 445);
			
			//CAJA VOLVER
		
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja3)); 
			
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Volver", 415, 535);
		}
		//VENTANA MENU PRINCIPAL
				else if(juego.estadoJuego() == ESTADO.Menu) {
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
				    g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Jugar", 380, 335);
					
					if(juego.esperarJugador != "") {
					    g.setFont(fnt);
						g.setColor(Color.white);
						g.drawString(juego.esperarJugador, 380, 235);						
					}
					/*
					//CAJA DE MAS ARRIBA
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString(estado, 415, 335);
					
					g.setColor(Color.WHITE);
				    g.drawRect(xCaja, ycaja1, anchoCaja, altoCaja);
					
				    //RESPLANDOR QUE TIENE CADA CAJA, SI SE PASA EL MOUSE POR ENCIMA SE VUELVE MAS BLANCO EL RESPLANDOR
				    handler.addObject(new reflejoBoton(xCaja, ycaja1, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, degradeCaja1)); 
				    g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Multiplayer", 380, 335);
				    */
					
					//2º caja AYUDA (AHORA LLAMADO CONFIGURAR PORQUE VAN DESDE PERSONALISAR PERSONAJE, DIFICULTAD, Y ESTADISTICAS)
					g.setColor(Color.white);
					g.drawRect(xCaja, ycaja2, anchoCaja, altoCaja);
					
					handler.addObject(new reflejoBoton(xCaja, ycaja2, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, degradeCaja2));
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Configurar", 380, 435);
					
					//3º caja SALIR
				
					g.setColor(Color.white);
					g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
					
					handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, degradeCaja3));
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Salir", 425, 535);
					
					fnt = new Font("Arial", 1, 15);
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Jugador 1: "+ juego.nombreUser, 55, JuegoBase.BASEALTURAHUD+ 175);
					
					g.setFont(fnt);
					g.setColor(Color.white);
					if(juego.nombreUserMP != null) {
						g.drawString("Jugador 2: "+ juego.nombreUserMP, 55, JuegoBase.BASEALTURAHUD+ 235);
					}
				}
		
		//VENTANA ESPERAR JUGADORES
		
		else if(juego.estadoJuego() == ESTADO.EsperarJugadores) {
			
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Esperando jugadores", 100, 100);
			fnt = new Font("Arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Jugador 1: "+ juego.nombreUser, 125, JuegoBase.BASEALTURAHUD+ 175);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			if(juego.nombreUserMP != "") {
				g.drawString("Jugador 2: "+ juego.nombreUser, 125, JuegoBase.BASEALTURAHUD+ 235);
			}
			

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
			g.drawString("Jugar", 360, 435);
			
			
		} 
		//VENTANA FIN DE PARTIDA, SE ACTIVA CUANDO EL, O LOS JUGADORES MUEREN
		else if(juego.estadoJuego() == ESTADO.Fin) {
		
			String mensajePrincipal = "GAME OVER";
			//PANTALLA DE FIN DE JUEGO, MUESTRA PUNTAJE Y NIVEL
			if(juego.haGanado()) {
				mensajePrincipal = "HAS GANADO";
			}
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString(mensajePrincipal, 100, 100);
			fnt = new Font("Arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Puntaje: "+ HUD.getPuntaje(), 125, JuegoBase.BASEALTURAHUD+ 235);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			try {
				g.drawString("Mejor Puntaje: "+ dat.obtenerPuntajeDeArchivo(), 325, JuegoBase.BASEALTURAHUD+ 235);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		else if(juego.estadoJuego() == ESTADO.Victoria) {
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("HAS GANADO", 100, 100);
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
		
		
		
		//VISTA PARA SABER LOS CONTROLES
		else if(juego.estadoJuego() == ESTADO.Controles) {
			Font fnt = new Font("Arial", 1, 50);
			
			g.drawImage(imagenTeclasDireccionales, 50, 100, null);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Controles", JuegoBase.ANCHO/2 - 95, 100);
			
			fnt = new Font("Arial", 1, 25);
			
			//3º caja VOLER
			
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
			
			handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, degradeCaja3));
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Volver", 425, 535);
		}
		
		//VISTA PARA VER ESTADISTICAS
				else if(juego.estadoJuego() == ESTADO.Estadisticas) {
					Font fnt = new Font("Arial", 1, 50);
					
					g.drawImage(imagenTeclasDireccionales, 50, 100, null);
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Estadisticas", JuegoBase.ANCHO/2 - 95, 100);
					
					fnt = new Font("Arial", 1, 25);
					
					//3º caja VOLER
					
					g.setColor(Color.white);
					g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
					
					handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, degradeCaja3));
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Volver", 425, 535);
				}
		
		//VISTA CAMBIAR PERSONAJE
				else if(juego.estadoJuego() == ESTADO.Personalizar) {
					Font fnt = new Font("Arial", 1, 50);
					
					g.drawImage(imagenTeclasDireccionales, 50, 100, null);
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Estadisticas", JuegoBase.ANCHO/2 - 95, 100);
					
					fnt = new Font("Arial", 1, 25);
					
					//3º caja VOLER
					
					g.setColor(Color.white);
					g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
					
					handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, degradeCaja3));
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Volver", 425, 535);
				}
		cargoVista = true;
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
