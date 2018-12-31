package HUD;

import base.JuegoBase;
import java.awt.Color;
import java.awt.Graphics;

public class HUDPrincipal {
	
	public static int VIDA;
	
	private double verdeValor = 255;
	
	private long tiempoSegundos = 0;
	private int puntaje = 0;
	private int nivel = 1;
	
	public static long vidaEnemigoHUD = 1;
	private long milliseconds = 1000000;
	private static long vidaOriginalEnemigo = 1;;
	public static boolean hayJefe = false;
	
	public HUDPrincipal() {
		VIDA = 10000;
	}
	
	public void tick() {
	//	VIDA--;
		
		VIDA = JuegoBase.clamp(VIDA, 0, 10000);
		verdeValor = JuegoBase.clamp((int)verdeValor, 0, 255);
		verdeValor = VIDA * 0.02;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(10, 15, 200, 15);
		g.setColor(Color.white);
		g.fillRect(8, 14, 202, 17);
		g.drawString("Puntaje: " + puntaje, 250, 20);
		g.drawString("Nivel: " + VIDA, 250, 30);
		
		
		g.drawString("VIDA JEFE " + vidaEnemigoHUD, 450, 30);
		
		
		g.setColor(new Color(75, (int)verdeValor, 0));
		double barraDeVida = VIDA*0.02;
		int barra = (int)barraDeVida;
		g.fillRect(10, 15, barra, 15);
		
		if(hayJefe) {
			//ESTO LO HAGO PARA EVITAR LA DIVISIÓN POR 0
			if(vidaEnemigoHUD == 0) {
				vidaEnemigoHUD = -1;
			}
			float anchoDeVidaEnemigo = 200/vidaOriginalEnemigo;
			barraDeVida = vidaEnemigoHUD*anchoDeVidaEnemigo;
			barra = (int)barraDeVida;
			g.setColor(new Color(200, 0, 0));
			g.fillRect(650, 15, barra, 15);
			g.setColor(Color.gray);
			g.fillRect(650, 15, 200, 15);
			g.setColor(Color.white);
			g.fillRect(648, 14, 202, 17);
		}
		
	}
	
	public static void ponerVidaJefe(long vidaEnemigo) {
		vidaEnemigoHUD = vidaEnemigo;
		vidaOriginalEnemigo = vidaEnemigo;
		hayJefe = true;
	}
	
	public static void muereJefe() {
		vidaEnemigoHUD = 1;
		vidaOriginalEnemigo = 1;
	}
	
	public long getTiempo() {
		return tiempoSegundos;
	}

	public double getVerdeValor() {
		return verdeValor;
	}

	public void setVerdeValor(double verdeValor) {
		this.verdeValor = verdeValor;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	

}
