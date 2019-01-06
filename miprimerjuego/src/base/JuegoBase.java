package base;

import personajes.GameObject;
import personajes.basicEnemy;
import HUD.HUDBARRA;
import HUD.HUDPrincipal;
import Jugador.Player;
import Menu.Menu;
import Menu.Menu2;
import Menu.efectos;
import Menu.efectos;
import Menu.particulasDeMenu;
import personajes.enemigoBorde;
import Spawn.Spawn;
import musicayefectosdesonido.audioplayer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class JuegoBase extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5162841791879620559L;

	public static final int BASEALTURAHUD = 40;
	public static final int ANCHO = 900, ALTO = ANCHO / 12 * 9, ALTURAJUEGO = ALTO - BASEALTURAHUD; 
	
	private Thread thread;
	private boolean running = false;
	public static Handler handler;

	private Spawn spawneo;
	private static HandlerEnemigo handlerEnemigo;
	private Menu2 menuss;
	
	private HUDPrincipal hud;
	private HUDBARRA hudbarra;
	private Random r;
	
	private boolean comienzaElJuego;
	private audioplayer audio;
	public static boolean haGanado;
	
//	private MenuEfectos efectos;
	private Ventana vent;
	private boolean vienePartidaTerminada;
	
	public enum ESTADO{
		Menu,
		Juego,
		Ayuda,
		Fin,
		Victoria
	};
	
	public static ESTADO EstadoJuego = ESTADO.Menu;
	
	public static void cambiarEstado(ESTADO nuevoEstado) {
		EstadoJuego = nuevoEstado;
	}
	
	public static ESTADO estadoJuego() {
		return EstadoJuego;
	}
	
	
	public JuegoBase() {
		haGanado = false;
		handler = new Handler();
		handlerEnemigo = new HandlerEnemigo();
		
		comienzaElJuego = false;
		hud = new HUDPrincipal();
		hudbarra = new HUDBARRA(BASEALTURAHUD, ANCHO);
		
		audio = new audioplayer();
		audioplayer.load();
		
		
		
		vent = new Ventana(ANCHO, ALTO, "MI PRIMER JUEGO", this);
		this.addKeyListener(new keyinput(handler));
		
		r = new Random();
		
		menuss = new Menu2(handler, handlerEnemigo, hud, this, audio);
		
		spawneo = new Spawn(handlerEnemigo, hud, handler, menuss, audio);
		
		this.addMouseListener(menuss);
		this.addMouseMotionListener(new efectos((ANCHO/2)-200/2, 200, 50, 300, 400, 500, handler, menuss));
		vienePartidaTerminada = false;
	
		if(EstadoJuego == ESTADO.Juego) {
			//PANTALLA DEL JUEGO, AQUI EMPIEZA A CREAR TODOS LOS ELEMENTOS DEL NIVEL
			
		} else  // if(EstadoJuego == ESTADO.Menu)
			{
			enemigosDeMenu();
		}
		
		/*
		for(int i = 0; i < 10; i++) {
			handlerEnemigo.addObject(new basicEnemy(r.nextInt(ANCHO), spawneo.noEnHUB(), handlerEnemigo, handler));		
		//	handler.addObject(new basicEnemy((ANCHO/2)-32, (ALTO/2)-32, ID.BasicEnemy, handler));		
		}*/

			
				
	//	handler.addObject(new Player(100, 100, ID.Player));
		//handler.addObject(new Player(150, 150, ID.Player));
		
		audioplayer.getMusic("musica").loop();
	}

	public static Handler obtenerHandler() {
		return handler;
	}
	

	
	public void crearEnemigosBorde() {}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private void enemigosDeMenu() {
		handlerEnemigo.vaciarObjecto();
		for(int i= 0; i < 20; i++) {
			handlerEnemigo.addObject(new particulasDeMenu(r.nextInt(JuegoBase.ANCHO)+50, spawneo.noEnHUB(), handlerEnemigo, handler));
		}
		
	}
	
	public void eliminarEnemigos() {
		handlerEnemigo.vaciarObjecto();
	}
	
	public static HandlerEnemigo obtenerHandlerEnemigo() {
		return handlerEnemigo;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		new JuegoBase();
	//	new MenuEfectos();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) 
			return var = max;
		else if(var <= min) return var = min;
		else 
			return var;
	}
	
	//GAME LOOP
		public void run()
	    {
			this.requestFocus();
	        long lastTime = System.nanoTime();
	        double amountOfTicks = 60.0;
	        double ns = 1000000000 / amountOfTicks;
	        double delta = 0;
	        long timer = System.currentTimeMillis();
	        int frames = 0;
	        while(running)
	        {
	                    long now = System.nanoTime();
	                    delta += (now - lastTime) / ns;
	                    lastTime = now;
	                    while(delta >=1)
	                            {
	                                tick();
	                                delta--;
	                            }
	                            if(running)
	                                render();
	                            frames++;
	                            
	                            if(System.currentTimeMillis() - timer > 1000)
	                            {
	                                timer += 1000;
	                    //            System.out.println("FPS: "+ frames);
	                                frames = 0;
	                            }
	        }
	                stop();
	    }
		public void comienzaElJuego() {
			comienzaElJuego = true;
		}
		
		private void tick() {
			handler.tick();
			if(EstadoJuego == ESTADO.Juego) {
				
				handlerEnemigo.tick();
				hud.tick();
				spawneo.tick();
				
				if(enJuego()) {
					if(comienzaElJuego) {
						spawneo.vaciarHandlerEnemigo();
						comienzaElJuego = false;
					}
					if(HUDPrincipal.VIDA <=0) {
					//	if(scriptDeJugadorMuereTerminado()) {
						handlerEnemigo.vaciarObjecto();
						eliminarEnemigos();
						handler.vaciarPersonaje();
						spawneo.vaciarHandlerEnemigo();
							vienePartidaTerminada =true;
							cambiarEstado(ESTADO.Fin);
							HUDPrincipal.VIDA = 10000;
							
							if(vienePartidaTerminada) {
								enemigosDeMenu();
								vienePartidaTerminada = false;
							}
					//	}
					}
					
					if(HUDPrincipal.vidaJefeHUD <= 0 && HUDPrincipal.hayJefe == true) {
						HUDPrincipal.hayJefe = false;
						vienePartidaTerminada =true;
					//	HUDPrincipal.muereJefe();
						haGanado = true;
						handlerEnemigo.vaciarObjecto();
						handler.vaciarPersonaje();
						cambiarEstado(ESTADO.Fin);	
						if(vienePartidaTerminada) {
							enemigosDeMenu();
							vienePartidaTerminada = false;
						}
					}
				}
				
				
			} else if(EstadoJuego == ESTADO.Menu || EstadoJuego == ESTADO.Fin || EstadoJuego == ESTADO.Ayuda || EstadoJuego == ESTADO.Victoria) {
				menuss.tick();
				handlerEnemigo.tick();
			}	
		}
		
		public static boolean haGanado() {
			return haGanado;
		}
		
		private boolean scriptDeJugadorMuereTerminado() {
			return jugadorMuere();
		}
		
		private boolean jugadorMuere() {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					Player jug = (Player) handler.object.get(i);
					return jug.haMuerto();
				}
			}
			return false;
		}
		
		private boolean obtenerJugador() {
			Player jug;
			for(int i = 0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.Player) {
					jug = (Player) handler.object.get(i);
					return jug.haMuerto();
				}
			}
			return false;
		}
		
		
		private boolean enJuego() {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					return true;
				}
			}
			return false;
		}
		
		private void render() {
			BufferStrategy bs = this.getBufferStrategy();
			if(bs == null) {
				this.createBufferStrategy(3);
				return;
				
			}

			Graphics g = bs.getDrawGraphics();
			
			g.setColor(Color.black);
			g.fillRect(0, 0, ANCHO, ALTO);
			
			
			
			if(EstadoJuego == ESTADO.Juego) {
				hudbarra.render(g);
				handler.render(g);
				handlerEnemigo.render(g);
				hud.render(g);
			} else if(EstadoJuego == ESTADO.Menu || EstadoJuego == ESTADO.Ayuda || EstadoJuego == ESTADO.Fin) {
				menuss.render(g);
				handlerEnemigo.render(g);
				handler.render(g);
				
				
			}
			
			g.dispose();
			bs.show();
		}
}