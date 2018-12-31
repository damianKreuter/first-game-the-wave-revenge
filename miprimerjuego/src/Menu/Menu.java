package Menu;


import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import HUD.HUDPrincipal;
import base.HandlerEnemigo;
import base.ID;
import base.Handler;
import base.JuegoBase;
import base.JuegoBase.ESTADO;
import personajes.Player;


public class Menu extends MouseAdapter
{
	
	private Handler handler;
	private HandlerEnemigo handlerEnemigo;
	private HUDPrincipal HUD;
	
	//DIMENSIONES DE CAJA EN X, ANCHO Y ALTO
	private int anchoCaja =200, altoCaja =50, xCaja  = (JuegoBase.ANCHO/2)-anchoCaja/2;
	
	//POSICION Y DE CAJA 1
	private int ycaja1 = 300;
	
	//POSICION Y DE CAJA 2
	private int ycaja2 = 400;
		
	//POSICION Y DE CAJA 1
	private int ycaja3 = 500;
	
	public Menu(
		//	Handler handler, HandlerEnemigo handlerEnemigo, HUDPrincipal hud
			) {
//		this.handler = handler;
//		this.handlerEnemigo = handlerEnemigo;
//		HUD = hud;
	}
	
	public void mousePressed(MouseEvent m) {
		
		int mx = m.getX();
		int my = m.getY();
		
		//BOTON COMENZAR
		if(mouseOver(mx, my, 50, 200, 200, 50)) {
			
			JuegoBase.cambiarEstado(ESTADO.Juego);
			handlerEnemigo.vaciarObjecto();
			handler.addObject(new Player((JuegoBase.ANCHO/2)-32, (JuegoBase.ALTURAJUEGO/2)-32, ID.Player, handler, handlerEnemigo));
		}
		
		//BOTON AYUDA
		if(JuegoBase.estadoJuego() == ESTADO.Menu) {
			if(mouseOver(mx, my, 50, 300, 200, 50)) {
				JuegoBase.cambiarEstado(ESTADO.Ayuda);
			}
			
		}
		
		//BOTON VOLVER
		if(JuegoBase.estadoJuego() == ESTADO.Ayuda || JuegoBase.estadoJuego() == ESTADO.Fin) {
			if(mouseOver(mx, my, 50, 400, 200, 50)) {
				JuegoBase.cambiarEstado(ESTADO.Menu);
		}
		
		} else {
			//BOTON SALIE
			if(mouseOver(mx, my, 50, 400, 200, 50)) {
				System.exit(1);
			}
		}
		
		//BOTON VOLVER A INTENTAR
		if(JuegoBase.estadoJuego() == ESTADO.Fin) {
			if(mouseOver(mx, my, 300, 400, 220, 50)) {
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

	@Override 
	public void mouseMoved(MouseEvent m) {
		
		//AUN FALTA DECIDIR LA ANIMACIÓN, DESPUES SE CONTINUA
		int mx = m.getX();
		int my = m.getY();
		
				//BOTON COMENZAR
				if(mouseOver(mx, my, 50, 200, 200, 50)) {
					
				}
				
				//BOTON AYUDA
				if(JuegoBase.estadoJuego() == ESTADO.Menu) {
					if(mouseOver(mx, my, 50, 300, 200, 50)) {
						
					}
					
				}
				
				//BOTON VOLVER
				if(JuegoBase.estadoJuego() == ESTADO.Ayuda || JuegoBase.estadoJuego() == ESTADO.Fin) {
					if(mouseOver(mx, my, 50, 400, 200, 50)) {
						
					}
				
				} else {
					//BOTON SALIR
					if(mouseOver(mx, my, 50, 400, 200, 50)) {
						
					}
				}
				
				//BOTON VOLVER A INTENTAR
				if(JuegoBase.estadoJuego() == ESTADO.Fin) {
					if(mouseOver(mx, my, 300, 400, 220, 50)) {
						
					}
				}
	}   
	  
	@Override 
	public void mouseDragged(MouseEvent m) {
		int mx = m.getX();
		int my = m.getY();
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
	//	mouseMoved();
	}
	
	public void render(Graphics g) {
/*
		if(JuegoBase.estadoJuego() == ESTADO.Ayuda) {
			Font fnt = new Font("Arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("AYUDA", 100, 100);
			fnt = new Font("Arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Usa las teclas direccionales para mover y esquivar los ataques", 125, 225);
			*/
			//3º caja
		/*
			g.setColor(Color.white);
			g.drawRect(50, 400, 200, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Volver", 125, 435);
		}
		
		else if(JuegoBase.estadoJuego() == ESTADO.Fin) {
		*/
			//PANTALLA DE FIN DE JUEGO, MUESTRA PUNTAJE Y NIVEL
			/*
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
			
*/
			//caja VOLVER A MENU
		/*
			g.setColor(Color.white);
			g.drawRect(50, 400, 200, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Volver al menu", 70, 435);
			*/
			//caja iNTENTAR DE NUEVO
		/*
			g.setColor(Color.white);
			g.drawRect(300, 400, 220, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Intentar de nuevo", 305, 435);
			
			
		} 
		else if(JuegoBase.estadoJuego() == ESTADO.Menu) {
			Font fnt = new Font("Arial", 1, 50);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("MENU", JuegoBase.ANCHO/2 - 75, 100);
			*/
			//CAJA DE MAS ARRIBA
		/*
			g.setColor(Color.WHITE);
		    g.drawRect(xCaja, ycaja1, anchoCaja, altoCaja);
			*/
		    //RESPLANDOR QUE TIENE CADA CAJA, SI SE PASA EL MOUSE POR ENCIMA SE VUELVE MAS BLANCO EL RESPLANDOR
	//	    handler.addObject(new reflejoBoton(xCaja, ycaja1, ID.Cola, new Color(255, 27, 27), 200, altoCaja, 0.05f, handler, 30)); 
			/*
		    fnt = new Font("Arial", 1, 25);
			
			//2º caja AYUDA
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja2, anchoCaja, altoCaja);
			*/
	//		handler.addObject(new reflejoBoton(xCaja, ycaja2, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, 30));
			/*
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Estadísticas", 100, 335);
			*/
			//3º caja SALIR
		/*
			g.setColor(Color.white);
			g.drawRect(xCaja, ycaja3, anchoCaja, altoCaja);
			*/
	//		handler.addObject(new reflejoBoton(xCaja, ycaja3, ID.Cola, new Color(255, 27, 27), anchoCaja, altoCaja, 0.05f, handler, 30));
			/*
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Salir", 125, 435);
			
			*/
			
			//1º caja
		/*
			g.setColor(Color.white);
			g.drawRect(50, 200, 220, 50);
			
	        
			//.drawRect(50, 200, 200, 50);
			
			fnt = new Font("Arial", 1, 25);
			
			
			
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Comenzar", 100, 235);
			
			
		} */
		
	}
}
