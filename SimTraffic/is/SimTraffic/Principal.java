/**
 * 
 */
package is.SimTraffic;

import is.SimTraffic.Vista.IVista;
import is.SimTraffic.Vista.Vista;

/**
 * Clase principal del programa.
 * <p>
 * Esta clase contiene el método main que se ejecuta al comenzar el programa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Principal {

	/**
	 * Método main principal del sistema.
	 * <p>
	 * Este método se encarga de crear las instancias de modelo, vista y
	 * controlador y asignarles los valores iniciales para que se pueda utilizar
	 * la aplicación. Además, una vez que está todo listo muestra la ventana al
	 * usuario para que pueda usar la aplicación.
	 * 
	 * @param args Argumentos de tipo string utilizados por defecto en el método 
	 * main.
	 */
	public static void main(String[] args) {
		IModelo modelo = new Modelo();
		IVista vista = new Vista(modelo);
		IControlador controlador = new Controlador();
		vista.setControlador(controlador);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.mostrar();
	}

}
