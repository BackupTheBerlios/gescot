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
	 * Este metodo se encarga de crear las instancias de modelo, vista y
	 * controlador y asignarles los valores iniciales para que se puede utilizar
	 * la aplicación. Además, una vez que esta todo listo muestra la ventana al
	 * usuario para que pueda usar la aplicación.
	 * 
	 * @param args
	 *            Argumentos de tipo string utilizados por defecto en el método
	 *            main
	 */
	public static void main(String[] args) {
		/*
		 * no se por que estaba este codigo, pero la ventana se ve mejor sin el
		 * eso es cuestion de gustos porque a mi me gusta mas de la otra manera, pero en fin...
		 * try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		IModelo modelo = new Modelo();
		IVista vista = new Vista(modelo);
		IControlador controlador = new Controlador();
		vista.setControlador(controlador);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.mostrar();
	}

}
