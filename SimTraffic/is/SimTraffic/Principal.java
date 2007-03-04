/**
 * 
 */
package is.SimTraffic;

import is.SimTraffic.Vista.IVista;
import is.SimTraffic.Vista.Vista;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

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
	 * Este metodo se encarga de crear las instancias de modelo, vista y
	 * controlador y asignarles los valores iniciales para que se puede utilizar
	 * la aplicaci�n. Adem�s, una vez que esta todo listo muestra la ventana al
	 * usuario para que pueda usar la aplicaci�n.
	 * 
	 * @param args
	 *            Argumentos de tipo string utilizados por defecto en el m�todo
	 *            main
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IModelo modelo = new Modelo();
		IVista vista = new Vista(modelo);
		IControlador controlador = new Controlador();
		vista.setControlador(controlador);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.mostrar();
	}

}
