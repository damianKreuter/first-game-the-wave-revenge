package AyudaExtra;

import java.util.LinkedList;
import java.util.Random;

import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import personajes.Enemigo;
import personajes.GameObject;

public interface ayudaExtra {
	
	LinkedList<ID> enemigos = new LinkedList<ID>();
	
	public static boolean esUnEnemigo(Enemigo tempObject) {
		iniciarEsEnemigo();
		if(enemigos.contains(tempObject.getId())) {
			return true;
		}
		return false;
	}
	
	public static int enemigoEsDeID(HandlerEnemigo enemigo, ID id) {
		int cantidad = 0;
		if(enemigo.object.size() > 0) {
			for(int i = 0; i < enemigo.object.size(); i++) {
				if(enemigo.object.get(i).getId() == id) {
					cantidad++;
				}
			}
		}
		return cantidad;
	}
	
	public static boolean esUnEnemigoQuePersigue(GameObject tempObject) {
		enemigoQueNoPersigue();
		if(enemigos.contains(tempObject.getId())) {
			return true;
		}
		return false;
	}
	
	public static void vaciarHandlers(Handler h1, HandlerEnemigo h2) {
		h1.vaciarObjecto();
		h2.vaciarObjecto();
	}
	
	public static int numeroRandomInt(int minimo, int maximo) {
		Random r = new Random();
		int low = minimo;
		int high = maximo;
		return r.nextInt(high-low) + low;
	}
	
	public static void enemigoQueNoPersigue() {
		enemigos.add(ID.BasicEnemy);
		enemigos.add(ID.EnemigoRapido);
		enemigos.add(ID.EnemigoQueVuelve);
		enemigos.add(ID.EnemigoLineal);
		enemigos.add(ID.EnemigoMuyDificil);
	}
	
	public static void iniciarEsEnemigo() {
		enemigos.add(ID.BasicEnemy);
		enemigos.add(ID.EnemigoBorde);
		enemigos.add(ID.EnemigoRapido);
		enemigos.add(ID.EnemigoJefe);
		enemigos.add(ID.EnemigoQueVuelve);
		enemigos.add(ID.EnemigoLineal);
		enemigos.add(ID.EnemigoQuePersigue);
		enemigos.add(ID.EnemigoMuyDificil);
		enemigos.add(ID.Laser);
		enemigos.add(ID.JefeComun);
		enemigos.add(ID.ProyectilesJefe);
	}
	
	public static float vectorXHaciaPersonaje(float x, float y, float xPOsicion, float yPosicion) {
		//OBTENGO LA DIFERENCIA DE LAS POSICIONES X e Y RESPECTO DEL JUGADOR Y DEL OBJETO AL CUAL TOMO REFERENCIA
		float diffx = xPOsicion - x -8;
		
		float xxx = xPOsicion - x;
		float yyy = yPosicion - y;
		
		//ESTO DA LA HIPOTENUSA, O SEA DA LA DISTANCIA REAL QUE EXISTE ENTRE EL JUGADOR Y EL ENEMIGO
		float distance = (float) Math.sqrt(xxx*xxx + yyy*yyy);
		float res = (float) ((-1.0/distance) * diffx);
		return res;
	}
	
	public static float vectorYHaciaPersonaje(float x, float y, float xPOsicion, float yPosicion) {
		//OBTENGO LA DIFERENCIA DE LAS POSICIONES X e Y RESPECTO DEL JUGADOR Y DEL OBJETO AL CUAL TOMO REFERENCIA
		float diffy = yPosicion - y -8;
		
		float xxx = xPOsicion - x;
		float yyy = yPosicion - y;
		
		//ESTO DA LA HIPOTENUSA, O SEA DA LA DISTANCIA REAL QUE EXISTE ENTRE EL JUGADOR Y EL ENEMIGO
		float distance = (float) Math.sqrt(xxx*xxx + yyy*yyy);
		
		float res = (float) ((-1.0/distance) * diffy);
		return res;
	}
	
	public static float modificarValorVelocidad() {
		Random r = new Random();
		Random intASumar = new Random();
		if(r.nextInt(2) == 0) {
			//DEBE RESTAR EL VALOR DE INTASUMAR A LA VELOCIDAD X
			return (float) intASumar.nextInt(10);
		} else {
			return (float) (intASumar.nextInt(10))*(-1);
		}
	}
	
	public static boolean ubicadoYSuperior(float y) {
		return y < 200;
	}
	
	public static boolean ubicadoXIzquierda(float x) {
		return x < 200;
	}
	
}
