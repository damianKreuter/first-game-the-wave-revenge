package HUD;

import java.util.concurrent.Semaphore;

public class Semaforo implements Runnable {
	 
    /**
     * Indica el n&uacute;mero de procesos que se pueden ejecutar al tiempo.
     */
    private static final Semaphore DISPONIBILIDAD = new Semaphore(3);
    /**
     * Nombre del proceso.
     */
    private final String nombre;
     
    public Semaforo(String nombre) {
        this.nombre = nombre;
    }
     
    @Override
    public void run() {
        try {
            // Solicita disponibilidad.
            DISPONIBILIDAD.acquire();
 
            System.out.println("El proceso [ " + this.nombre + " ] dormira " +
                "por 5 segundos");
 
            Thread.sleep(5000);
 
            System.out.println("Finaliza el proceso [ " + this.nombre + " ]");
 
            // Libera disponibilidad.
            DISPONIBILIDAD.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}