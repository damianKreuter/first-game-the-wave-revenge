package manejoArchivos;

import java.io.*;
import java.lang.*;
import java.util.*;

public class datosDeGuardado {

	private Formatter archivo;
	private Scanner escaner;
	private File file = new File("infoJugador.txt");
	private int puntosOriginales;
	
	public void abrirArchivo() {
		try {
			archivo = new Formatter("infoJugador.txt");
		} catch(Exception e) {
			System.out.println("Error al iniciar el juego");
		}
	}
	
	public boolean leerArchivo() {
		try {
			escaner = new Scanner(file);
			if(escaner.hasNext()) {
				String a = escaner.next();
				String puntos = escaner.next();
				puntosOriginales = Integer.parseInt(puntos);		
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
	//		try {
	//		archivo = new Formatter("datosyestadisticas/infoJugador.txt");
	//		} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error al iniciar el juego");
				return false;
	//		}
		}
	}
	
	public String obtenerPuntajeDeArchivo() throws FileNotFoundException {
		try {
			escaner = new Scanner(file);
			String a = obtenerPuntaje();
			escaner.close();
			return a;
		} catch(Exception e) {
	//		try {
	//		archivo = new Formatter("datosyestadisticas/infoJugador.txt");
	//		} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error al iniciar el juego");
				return "0";
	//		}
		}
		
	}
	
	public String obtenerPuntaje() {
		String puntaje = "0";
		boolean encontroPuntaje = false;
		boolean encontroFinal = false;
		int i = 0;
		while(escaner.hasNext() && !encontroPuntaje) {
			puntaje = escaner.next();
			if(i == 1) {
				//ENCONTRO LA POSICION DEL PUNTAJE
				encontroPuntaje = true;
			}
			i++;
		}
		return puntaje;
	}
	
	public String obtenerNivel() {
		String nivel = "";
		boolean encontroFinal = false;
		int i = 0;

		while(escaner.hasNext() && !encontroFinal) {
			nivel = escaner.next();
			if(i == 1) {
				//ENCONTRO LA POSICION DEL PUNTAJE
				encontroFinal = true;
			}
			i++;
		}
		return nivel;
	}
	
	private int comparaPuntaje(int p) {

		if(puntosOriginales >= p) {
			return puntosOriginales;
		} else {
			return p;
		}
	}
	
	private int comparaNivel(int n) {
		String nivel = obtenerNivel();
		int nn = Integer.parseInt(nivel);
		if(nn >= n) {
			return nn;
		} else {
			return n;
		}
		
	}
	
	
	public void agregarNuevoPuntajeYNivelMaximo(int puntaje, int nivel) {
		if(leerArchivo()) {
			abrirArchivo();
			archivo.format("Puntaje %s Nivel: %s", comparaPuntaje(puntaje), nivel);
			cerrarArchivo();
			
		} else {
			abrirArchivo();
			archivo.format("Puntaje %s Nivel: %s", puntaje, nivel);
			
			cerrarArchivo();
		}
		escaner.close();
		
	}
	
	public void cerrarArchivo() {
		archivo.close();
	}
}
