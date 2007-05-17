/**
 * 
 */
package is.SimTraffic;

import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Vista.IVista;
import is.SimTraffic.Vista.Vista;

/**
 * Clase principal del programa.
 * <p>
 * Esta clase contiene el m�todo main que se ejecuta al comenzar el programa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Principal {

	/**
	 * M�todo main principal del sistema.
	 * <p>
	 * Este m�todo se encarga de crear las instancias de modelo, vista y
	 * controlador y asignarles los valores iniciales para que se pueda utilizar
	 * la aplicaci�n. Adem�s, una vez que est� todo listo muestra la ventana al
	 * usuario para que pueda usar la aplicaci�n.
	 * 
	 * @param args Argumentos de tipo string utilizados por defecto en el m�todo 
	 * main.
	 */
	public static void main(String[] args) {
		new Messages();
		HCargarMapa.p.validate();
		IModelo modelo = new Modelo();
		IVista vista = new Vista(modelo);
		IControlador controlador = new Controlador();
		vista.setControlador(controlador);
		modelo.setControlador(controlador);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.mostrar();
	}

}
